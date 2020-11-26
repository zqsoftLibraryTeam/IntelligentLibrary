/*package com.intelligentLibrary.recommand.data;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.intelligentLibrary.dev.Dao.AdminManageBookDAO;
import com.intelligentLibrary.dev.Dao.AdminManageUserDAO;
import com.intelligentLibrary.dev.Dao.BookSortDAO;
import com.intelligentLibrary.dev.Dao.CommentDAO;
import com.intelligentLibrary.dev.Dao.DeptDAO;
import com.intelligentLibrary.dev.Dao.KeyWordDAO;
import com.intelligentLibrary.dev.Dao.TagDAO;
import com.intelligentLibrary.dev.Dao.WeightDAO;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BookSort;
import com.intelligentLibrary.dev.domain.Comment;
import com.intelligentLibrary.dev.domain.Dept;
import com.intelligentLibrary.dev.domain.Keyword;
import com.intelligentLibrary.dev.domain.Tag;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.domain.Weight;

@Service
public class GetData {
	@Autowired
	AdminManageBookDAO bookDAO;
	@Resource
	BookSortDAO bookSortDAO;
	@Resource
	CommentDAO commentDAO;
	@Resource
	DeptDAO deptDAO;
	@Resource
	KeyWordDAO keyWordDAO;
	@Resource
	AdminManageUserDAO adminManageUserDAO;
	@Resource
	TagDAO tagDAO;
	@Resource
	WeightDAO weightDAO;
	private List<Book> books = new ArrayList<Book>();
	private List<BookSort> booksorts = new ArrayList<BookSort>();
	private List<Comment> comments = new ArrayList<Comment>();
	private List<Dept> depts = new ArrayList<Dept>();
	private List<Keyword> keywords = new ArrayList<Keyword>();
	private List<Tag> tags = new ArrayList<Tag>();
	private List<User> users = new ArrayList<User>();
	private List<Weight> weights = new ArrayList<Weight>();

	public void init() {
		this.books = bookDAO.findAll();
		this.booksorts = bookSortDAO.findAll();
		this.comments = commentDAO.findAll();
		this.depts = deptDAO.findAll();
		this.keywords = keyWordDAO.findAll();
		this.tags = tagDAO.findAll();
		this.users = adminManageUserDAO.findAll();
		this.weights = weightDAO.findAll();
	}

	
	 * private ApplicationContext ctx = new
	 * ClassPathXmlApplicationContext("applicationContext-spring.xml"); private
	 * SessionFactory sessionfactory = ctx.getBean(SessionFactory.class);
	 * Session session = sessionfactory.openSession();
	 
	public List<Book> findBooks() {
		return this.books;
	}

	public List<BookSort> findBooksorts() {
		return this.booksorts;
	}

	public List<Comment> findComments() {
		return this.comments;
	}

	public List<Dept> findDepts() {
		return this.depts;
	}

	public List<Keyword> findKeywords() {
		return this.keywords;
	}

	public List<Tag> findTags() {
		return this.tags;
	}

	public List<User> findUsers() {
		return this.users;
	}

	public List<Weight> findWeights() {
		return this.weights;
	}


}
*/