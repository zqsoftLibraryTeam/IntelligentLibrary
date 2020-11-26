package com.intelligentLibrary.dev.controller;

import java.util.List;

/**
 * ��������û������࣬�û����͵�������������д���
 */

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.intelligentLibrary.dev.Dao.UserDAO;
import com.intelligentLibrary.dev.domain.BorrowInfo;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.service.LoginService;
import com.intelligentLibrary.dev.service.RecommandService;
import com.intelligentLibrary.dev.service.SeatService;
import com.intelligentLibrary.dev.service.UserService;

@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserController {

	@Resource
	private LoginService loginService;
	@Resource
	private UserService userService;
	@Resource
	UserDAO userDAO;
	@Resource
	SeatService seatService;
	@Resource
	RecommandService recommandService;
	
	
	
	/**
	 * ���鷽��
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/borrowBook.do")
	public ModelAndView borrowBook(HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute("currentUser");
		String bookid = request.getParameter("bookid");
		userService.borrowBook(user.getUserid(), bookid);
		return new ModelAndView("redirect:/user/borrowinfo.do");
	}
	/**
	 * �����鼮
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/orderBook.do")
	public ModelAndView orderBook(HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute("currentUser");
		String bookid = request.getParameter("bookid");
		userService.orderBook(user.getUserid(), bookid);
		return new ModelAndView("redirect:/user/borrowinfo.do");
	}
	/**
	 * ȡ������
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/cancelorder.do")
	public ModelAndView cancelorderBook(HttpServletRequest request) {
		String orderid = request.getParameter("orderid");
		userService.cancelorderbook(Integer.parseInt(orderid));
		return new ModelAndView("redirect:/user/borrowinfo.do");
	}
	/**
	 * �û����������Ϣ
	 * 
	 * @param model 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/borrowinfo.do")
	public String getUserInfo(Model model, HttpServletRequest request, HttpSession session) {
		User user = (User) session.getAttribute("currentUser");
		model.addAttribute("userborrowinfo", userService.borrowInfo(user));
		model.addAttribute("occupyinfos", seatService.occupyInfo(user));
		//Ĭ���û���ϲ�ò������ı�
		model.addAttribute("recommandinfos",recommandService.recommand(user));
		return "userpanel";
	}

	/**
	 * �޸�����
	 * 
	 * @param model
	 * @param request
	 * @param session
	 * @param oldpass
	 * @param newpass
	 * @return
	 */
	@RequestMapping(value="/changepass.do",method = RequestMethod.POST)
	public @ResponseBody String changepass(Model model, HttpServletRequest request, HttpSession session, @RequestParam String oldpass,
			@RequestParam String newpass) {
		User user = (User) session.getAttribute("currentUser");
		model.addAttribute("userborrowinfo", userService.borrowInfo(user));
		if (userService.changepassword(oldpass, newpass, user)) {
			user.setPassword(newpass);
			session.setAttribute("currentUser", user);
			return "�޸ĳɹ�";
		} else {
			return "�޸�ʧ��";
		}
	}

	/**
	 * �˳�����
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/loginout.do")
	public String loginout(Model model, HttpSession session) {
		session.invalidate();
		return "userAction/findBookandborrow";
	}

	/**
	 * ͼ���ѯ���ѡ���Ӧ��ͼ���������Ϣ
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/borrowbookdetail.do", method = RequestMethod.GET)
	public String borrowbookdetail(Model model, HttpServletRequest request, HttpSession session) {
		String bookid = request.getParameter("bookid").toString();
		model.addAttribute("book", userService.getBook(bookid));
		List<BorrowInfo> bookdetail = userService.BookborrowInfo(bookid);
		model.addAttribute("bookdetail", bookdetail);
		model.addAttribute("bookborrownum", bookdetail.size());
		return "userAction/bookdetail";
	}
	
	/**
	 * �����������ҳmain.jsp��ת��ѡ�����ʱ,��Ҫʵ�ֵĹ����Ǵ��ݵ�ǰ����ÿһ��λ�õ���Ϣ
	 * @return
	 */
	@RequestMapping(value="detailselect.do")
	public ModelAndView detailselectpage(ModelAndView mv,int zoneid)
	{
		mv.setViewName("orderseatAction/select");
		mv.addObject("seatsinfo",seatService.getCurrentZoneSeatStatus(zoneid));
		return mv;
	}
	
	
	/**
	 * �˷�������������ѡ��ʱ�����õķ��� ע��:userid��ҳ��ͽ����ж��Ƿ����,�����Ǹ����򣬾���˵���û�Ԥ��һ��λ��ʱҲҪ����λ�õ�����
	 * 
	 * @param userid
	 *            ���ڵ�id�����������û�����session�л�ȡ,
	 * @param deskid
	 *            ���ӵ�id
	 * @param seatid
	 *            ��λ��id
	 * @return
	 */
	@RequestMapping(value = "/orderseat.do")
	@ResponseBody
	public String orderseat(@RequestParam int userid, @RequestParam int deskid, @RequestParam String seatid,@RequestParam int zoneid) {
		/**
		 * �����ajax��������ǰ,useridΪ������ʾ�û�û�е��� userid��${user.userid}����
		 * ���Ծ���˵���ʱ��useridһ�����и�ȷ����ֵ��
		 */
		if (!seatService.Order(userid, deskid, Integer.parseInt(seatid),zoneid)) {
			return "Ԥ��ʧ��";
		}
		return "Ԥ���ɹ�";
	}
	
	
	/**
	 * �˷����������û��黹λ�õ�����,����ע��
	 * �и�ȱ�ݾ��ǳɹ������¼���һ��ҳ�����Ϣ
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="/reverseorder.do")
	public  String reverseorder(@RequestParam int userid)
	{
		seatService.reverseOrder(userid);
		return "redirect:borrowinfo.do";
	}
	
}
