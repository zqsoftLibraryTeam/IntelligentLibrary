package com.intelligentLibrary.dev.controller;

import java.util.List;

/**
 * 这个类是用户控制类，用户发送的请求都在这里进行处理
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
	 * 借书方法
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
	 * 订阅书籍
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
	 * 取消订阅
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
	 * 用户界面借书信息
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
		//默认用户的喜好不发生改变
		model.addAttribute("recommandinfos",recommandService.recommand(user));
		return "userpanel";
	}

	/**
	 * 修改密码
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
			return "修改成功";
		} else {
			return "修改失败";
		}
	}

	/**
	 * 退出方法
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
	 * 图书查询结果选择对应的图书的详情信息
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
	 * 这个方法是主页main.jsp跳转至选择界面时,需要实现的功能是传递当前区域每一个位置的信息
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
	 * 此方法是用于用于选座时所调用的方法 注意:userid在页面就进行判断是否存在,桌子是个区域，就是说当用户预定一个位置时也要传该位置的桌子
	 * 
	 * @param userid
	 *            用于的id，用于区别用户，从session中获取,
	 * @param deskid
	 *            桌子的id
	 * @param seatid
	 *            座位的id
	 * @return
	 */
	@RequestMapping(value = "/orderseat.do")
	@ResponseBody
	public String orderseat(@RequestParam int userid, @RequestParam int deskid, @RequestParam String seatid,@RequestParam int zoneid) {
		/**
		 * 如果在ajax发送请求前,userid为空则提示用户没有登入 userid从${user.userid}中拿
		 * 所以就是说这个时候userid一定是有个确定的值的
		 */
		if (!seatService.Order(userid, deskid, Integer.parseInt(seatid),zoneid)) {
			return "预定失败";
		}
		return "预定成功";
	}
	
	
	/**
	 * 此方法是用于用户归还位置的设置,类似注销
	 * 有个缺陷就是成功会重新加载一边页面的信息
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
