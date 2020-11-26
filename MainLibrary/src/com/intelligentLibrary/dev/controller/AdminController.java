package com.intelligentLibrary.dev.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BookSort;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.service.ManageBookService;
import com.intelligentLibrary.dev.service.ManageUserService;
import com.intelligentLibrary.dev.utils.AdminConfig;
import com.intelligentLibrary.dev.utils.AllBookTypeAdapter;
import com.intelligentLibrary.dev.utils.BookTypeAdapter;
import com.intelligentLibrary.dev.utils.GsonExclusion;
import com.intelligentLibrary.dev.utils.GsonMessage;
import com.intelligentLibrary.dev.utils.SimpleAllSortTypeAdapter;
import com.intelligentLibrary.dev.utils.SplitPage;

/**
 * 
 * @author 游卓霖
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class AdminController {
	
	@Resource
	ManageUserService manageUserService;
	
	@Resource
	ManageBookService manageBookService;
	
//	Gson gson = new Gson();
	
	@RequestMapping("/writeintoRFID")
	@ResponseBody
	public void RFIDwriter()
	{
		System.out.println("success");
	}
	
	@RequestMapping("/adminpanel.do")
	public String adminPanel(Model model,HttpSession session){
		
		return "adminpanel";
	}
	
	@RequestMapping("/addUser.do")
	public ModelAndView addUser(User user)
	{
		user.setUsername(user.getUsername());
		user.setPassword(user.getPassword());
		manageUserService.addUser(user);
		return new ModelAndView("redirect:listUsers.do");
	}
	
	@RequestMapping("/listUsers.do")
	public ModelAndView ListUsers()
	{
		ModelAndView mv= new ModelAndView();
		mv.addObject("users", manageUserService.getUsers());
		mv.setViewName("adminAction/listUser");
		return mv;
	}
	
	
	@RequestMapping("/deleteUser.do")
	public ModelAndView DeleteUser(HttpServletRequest request)
	{
		Integer id=Integer.parseInt(request.getParameter("id"));
		manageUserService.deleteUser(id);
		return new ModelAndView("redirect:listUsers.do");
	}
	
	@RequestMapping("/editUser.do")
	public ModelAndView EditUser(User user)
	{
		user.setUsername(user.getUsername());
		user.setPassword(user.getPassword());
		manageUserService.editUser(user);
		return new ModelAndView("redirect:listUsers.do");
	}
	
	@RequestMapping("/getPersonInfo.do")
	public ModelAndView GetPersonInfo(HttpServletRequest request)
	{
		Integer id=Integer.parseInt(request.getParameter("id"));
		request.setAttribute("personInfo", manageUserService.findUserByid(id));
		return new ModelAndView("adminAction/personal");
	}
	
	@RequestMapping("/addbooks.do")
	public ModelAndView AddBook(Book book,HttpServletRequest request) throws UnsupportedEncodingException
	{
		book.setBookname(book.getBookname());
		book.setAuthor(book.getAuthor());
		book.setIsbn(book.getIsbn());
		manageBookService.addBook(book);
		System.out.println(book.getBookname()+"------>"+book.getAuthor()+"_------->"+book.getIsbn());
		return new ModelAndView("adminAction/booktestforadd");
	}
	
	@RequestMapping("/booklist.do")
	public ModelAndView listBooks()
	{
		ModelAndView mv=new ModelAndView();
		mv.addObject("books", manageBookService.getBooks());
		mv.setViewName("adminAction/listBook");
		return mv;
	}
	
	
	@RequestMapping("/deleteBook.do")
	public ModelAndView deleteBook(HttpServletRequest request)
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		manageBookService.deleteBook(id);
		return new ModelAndView("redirect:booklist.do");
	}
	
	@RequestMapping("/editBook.do")
	public ModelAndView EditBook(Book book)
	{
		book.setBookname(book.getBookname());
		book.setIsbn(book.getIsbn());
		book.setAuthor(book.getAuthor());
		manageBookService.editBook(book);
		return new ModelAndView("redirect:booklist.do");
	}
	
	@RequestMapping("/getBookInfo.do")
	public ModelAndView GetBookInfo(HttpServletRequest request)
	{
		Integer id=Integer.parseInt(request.getParameter("id"));
		request.setAttribute("bookInfo", manageBookService.findBookByid(id));
		return new ModelAndView("adminAction/bookinfo");
	}
	/**
	 * json格式所有用户
	 * @return
	 */
	@RequestMapping(value="/gsonuserlist.do",method=RequestMethod.GET)
	public @ResponseBody String gsonListUsers()
	{
		String[] userattr = {"ilDept","ilBorrowinfos","orderbooks","orderbooks","password","email","age","occupyinfos","recommandinfos","orderSeatInfos"};
		List<User> users = manageUserService.getUsers();
		Gson usersgson = new GsonBuilder().setExclusionStrategies(new GsonExclusion(userattr)).create();
		return  usersgson.toJson(users);
	}
	/**
	 * json格式所有书本
	 * @return
	 */
	@RequestMapping(value="/gsonbooklist.do",method = RequestMethod.GET)
	public @ResponseBody String GsonlistBooks()
	{	
//		String[] bookattr = {"bookSort","bookShell","borrowInfos","picture"};
		List<Book> books = manageBookService.getBooks();
//		for (Book book : books) {
//			System.out.println(book.getBookname());
//		}
//		Gson bookgson = new GsonBuilder().setExclusionStrategies(new GsonExclusion(bookattr)).create();
//		String jsonbook = bookgson.toJson(books);
		//System.out.println(jsonbook);
		Type type = new TypeToken<List<Book>>(){}.getType();
		Gson booksgson = new GsonBuilder().registerTypeAdapter(type, new AllBookTypeAdapter()).create();
		String jsonbook = booksgson.toJson(books,type);
		//System.out.println(jsonbook);
		return  jsonbook;
	}
	@RequestMapping(value="/gsonuser.do",method = RequestMethod.GET)
	public @ResponseBody String Gsonuser(@RequestParam("id")Integer id){
		String[] userattr = {"ilDept","ilBorrowinfos","orderbooks","comments","password","email","age","occupyinfos","recommandinfos","orderSeatInfos"};
		User user = manageUserService.getUserById(id);
		Gson usergson = new GsonBuilder().setExclusionStrategies(new GsonExclusion(userattr)).create();
		return usergson.toJson(user);
	}
	@RequestMapping(value="/gsonbook.do",method = RequestMethod.GET)
	public @ResponseBody String Gsonbook(@RequestParam("id")Integer id){
		//String[] exstr = {"bookSort","bookShell","borrowInfos"};
		Book book = manageBookService.getBookById(id);
		Gson bookgson = new GsonBuilder().registerTypeAdapter(Book.class, new BookTypeAdapter()).create();
		return bookgson.toJson(book);
	}
	@RequestMapping(value="/adminEditNoData{type}.do",method = RequestMethod.POST)
	public @ResponseBody String adminEditobj(HttpServletRequest request,@PathVariable("type")String type,@RequestParam("editobj")String editobj){
//		System.out.println(editobj);
//		System.out.println("到达controller");
		if(type.equals("book")){
			Book book = new Gson().fromJson(editobj, Book.class);
			if(manageBookService.editBook(book)){
				 return GsonMessage.BOOK_EDIT_SUCCEED;
			 }else{
				 return GsonMessage.BOOK_EDIT_FAIL;
			 }
		}else if(type.equals("user")){
			User user = new Gson().fromJson(editobj, User.class);
			 if(manageUserService.editUser(user)){
				 return GsonMessage.USER_EDIT_SUCCEED;
			 }else{
				 return GsonMessage.USER_EDIT_FAIL;
			 }
		}
		return GsonMessage.EDIT_FAIL;
	}
	@RequestMapping(value="/adminEditHasData{type}.do",method = RequestMethod.POST)
	public @ResponseBody String adminEditobjHasData(HttpServletRequest request,@PathVariable("type")String type,
						@RequestParam("editobj")String editobj,@RequestParam("file") MultipartFile file){
		String filedir = request.getSession().getServletContext().getRealPath("/") + "img/bookimg";
		File dir = new File(filedir);
		  if (!dir.exists()) //如果目录不存在就创建目录
			   dir.mkdirs();
		try {
			if(!file.isEmpty()){
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filedir,file.getOriginalFilename()));
				System.out.println("文件名为"+file.getOriginalFilename());
			}else{
				System.out.println("是空的文件，无法创建文件");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(type.equals("book")){
			Book book = new Gson().fromJson(editobj, Book.class);
			if(manageBookService.editBook(book)){
				 return "修改书本成功";
			 }else{
				 return "修改书本失败";
			 }
		}else if(type.equals("user")){
			User user = new Gson().fromJson(editobj, User.class);
			 if(manageUserService.editUser(user)){
				 return "修改用户成功";
			 }else{
				 return "修改用户失败";
			 }
		}
		return "修改失败";
	}
	@RequestMapping(value="/adminaddNoData{type}.do",method=RequestMethod.POST)
	public @ResponseBody String adminAddobj(@PathVariable("type")String type,@RequestParam("addobj")String addobj){
		
		if(type.equals("user")){
			User user = new Gson().fromJson(addobj, User.class);
		 	if(manageUserService.addUser(user)){
		 		return "添加用户成功";
		 	}else{
		 		return "添加用户失败";
		 	}
		}else if(type.equals("book")){
			Book book = new Gson().fromJson(addobj, Book.class);
			 if(manageBookService.addBook(book)){
				 return "添加书本成功";
			 }else{
				 return "添加书本失败";
			 }
		}else{
			return "添加失败";
		}
		
	}
	
	@RequestMapping(value="/adminaddHasData{type}.do",method=RequestMethod.POST)
	public @ResponseBody String adminAddobjHasData(HttpServletRequest request,@PathVariable("type")String type,@RequestParam("addobj")String addobj,@RequestParam("file") MultipartFile file){
		
		String filedir = request.getSession().getServletContext().getRealPath("/") + "img/bookimg";
		File dir = new File(filedir);
		  if (!dir.exists()) //如果目录不存在就创建目录
			   dir.mkdirs();
		try {
			if(!file.isEmpty()){
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filedir,file.getOriginalFilename()));
				System.out.println("文件名为"+file.getOriginalFilename());
			}else{
				System.out.println("空的文件夹");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(type.equals("user")){
			User user = new Gson().fromJson(addobj, User.class);
		 	if(manageUserService.addUser(user)){
		 		return "添加用户成功";
		 	}else{
		 		return "添加用户失败";
		 	}
		}else if(type.equals("book")){
			Book book = new Gson().fromJson(addobj, Book.class);
			 if(manageBookService.addBook(book)){
				 return "添加书本成功";
			 }else{
				 return "添加书本失败";
			 }
		}else{
			return "添加失败";
		}
		
	}
	@RequestMapping(value="/admindel{type}.do",method=RequestMethod.POST)
	public @ResponseBody String admindelobjs(@PathVariable("type")String type,@RequestParam("dellist")String dellist){
		String[] list = (dellist.substring(1, dellist.length()-1)).split(",");
		List<Integer> dellistid = new ArrayList<Integer>();
		for (String string : list) {
			dellistid.add(Integer.parseInt(string));
		}
//		for (int i = 0; i < dellistid.size(); i++) {
//			System.out.println("shi"+dellistid.get(i)+"   "+i);
//		}
		if(type.equals("user")){
			if(manageUserService.deleteUsers(dellistid)){
				return "删除用户成功";
			}else{
				return "删除用户失败";
			}
		}else if(type.equals("book")){
			 if(manageBookService.deleteBooks(dellistid)){
				 return "删除书本成功";
			 }else{
				 return "删除书本失败";
			 }
		}else{
			return "删除失败";
		}
	}
	@RequestMapping(value="/uploadpicture.do",method=RequestMethod.POST)
	public @ResponseBody String upLoadPicture(@RequestParam("file") MultipartFile file){
		try {
			if(!file.isEmpty()){
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File("img\\bookimg",file.getOriginalFilename()));
				return file.getOriginalFilename();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "defaulterror.jpg";
		}
		return "default.jpg";
	}
	@RequestMapping(value="/get{type}splitnum.do",method=RequestMethod.GET)
	public @ResponseBody String getsplitnum(HttpServletRequest request,@PathVariable("type")String type){
		SplitPage p= new SplitPage();
		p.setCurrentpage(1);
		if(type.equals("user")){
			manageUserService.gettotalPageNum(p);
		}else if(type.equals("book")){
			manageBookService.gettotalPageNum(p);
		}
		request.getSession().setAttribute(type+"splitpage", p);
		//System.out.println("controller"+p.getTotalnum());
		return String.valueOf(p.getTotalnum());
	}
	@RequestMapping(value="/getsub{type}list.do")
	public @ResponseBody String getSubBooks(@PathVariable("type")String type,HttpServletRequest request,@RequestParam(value="currentpage",defaultValue="1")String currentpage){
		SplitPage p = (SplitPage) request.getSession().getAttribute(type+"splitpage");
		p.setCurrentpage(Integer.parseInt(currentpage));
		String jsonobj = null;
		if(type.equals("user")){
			String[] userattr = {"ilDept","ilBorrowinfos","orderbooks","comments","password","email","age","occupyinfos","recommandinfos","orderSeatInfos"};
			List<User> users = manageUserService.getsubBook(p);
			Gson usersgson = new GsonBuilder().setExclusionStrategies(new GsonExclusion(userattr)).create();
			jsonobj = usersgson.toJson(users);
		}else if(type.equals("book")){
			Type objtype = new TypeToken<List<Book>>(){}.getType();
			Gson booksgson = new GsonBuilder().registerTypeAdapter(objtype, new AllBookTypeAdapter()).create();
			jsonobj = booksgson.toJson(manageBookService.getsubBook(p),objtype);
		}
		return jsonobj;
	}
	@RequestMapping(value="/getalltopsortname.do")
	public @ResponseBody String getAllTopSortName(){
		List<BookSort> sorts = manageBookService.getAllTopSortName();
		Type sorttype = new TypeToken<List<BookSort>>(){}.getType();
		Gson sortgson = new GsonBuilder().registerTypeAdapter(sorttype, new SimpleAllSortTypeAdapter()).create();
		return sortgson.toJson(sorts,sorttype);
	}
	@RequestMapping(value="/getsubsortbyparid.do")
	public @ResponseBody String getSubSortByParId(@RequestParam("pid")String pid){
		List<BookSort> sorts = manageBookService.getSubSortByParId(Integer.parseInt(pid));
		Type sorttype = new TypeToken<List<BookSort>>(){}.getType();
		Gson sortgson = new GsonBuilder().registerTypeAdapter(sorttype, new SimpleAllSortTypeAdapter()).create();
		return sortgson.toJson(sorts,sorttype);
	}
}
