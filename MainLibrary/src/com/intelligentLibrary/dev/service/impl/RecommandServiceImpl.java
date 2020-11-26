package com.intelligentLibrary.dev.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intelligentLibrary.dev.Dao.AdminManageBookDAO;
import com.intelligentLibrary.dev.Dao.AdminManageUserDAO;
import com.intelligentLibrary.dev.Dao.BorrowAndReturnDAO;
import com.intelligentLibrary.dev.Dao.RecommandDAO;
import com.intelligentLibrary.dev.Dao.RuleBookDAO;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BorrowInfo;
import com.intelligentLibrary.dev.domain.Recommandinfo;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.service.RecommandService;
import com.intelligentLibrary.recommand.entity.RuleBook;

@Service
public class RecommandServiceImpl implements RecommandService {
	
	@Resource
	RecommandDAO recommandDAO;
	@Resource
	BorrowAndReturnDAO borrowAndReturnDAO;
	@Resource
	AdminManageBookDAO adminManageBookDAO;
	@Resource
	AdminManageUserDAO adminManageUserDAO;
	@Resource
	RuleBookDAO ruleBookDAO;
	@Override
	public String requesthandle(String input, int userid) {
		
		return "illegalrequest";
		
	}

	@Override
	public void writeintorecommand(User user) {
		//遍历规则，写入推荐表中
				List<RuleBook> rules=ruleBookDAO.findAll();
				List<Book> booksuserhaveread=borrowAndReturnDAO.getBooksWhichUserHaveRead(user);
				List<Book> booksrecommands=new ArrayList<Book>();
				for(Book book:booksuserhaveread)
				{
					for(RuleBook rule:rules)
					{
						if(book.getBookname().equals(rule.getTargetBook().getBookname()))
						{
							booksrecommands.add(rule.getCurrentBook());
						}
						if(book.getBookname().equals(rule.getCurrentBook().getBookname()))
						{
							booksrecommands.add(rule.getTargetBook());
						}
					}
				}
				booksrecommands.removeAll(booksuserhaveread);
				for(Book book:booksrecommands)
				{
				Recommandinfo recommandinfo=new Recommandinfo();
				recommandinfo.setUser(user);
				recommandinfo.setBook(book);
				//判断是否存在
				if(recommandDAO.getRecommandInfoByuserandbook(user, book)>0)
				{
					continue;
				}
				recommandDAO.save(recommandinfo);
				}
	}

	@Override
	public List<Book> recommand(User user) {
		
		List<Book> books=recommandDAO.BooksToRecommand(user);
		
		return books;
	}

	@Override
	public List<BorrowInfo> AprioriArithmeticProvider() {
		
		return borrowAndReturnDAO.findAll();
	}

	@Override
	public List<Book> AprioriArithmeticProvider_Books() {
		return adminManageBookDAO.findAll();
	}

	@Override
	public List<User> AprioriArithmeticProvider_Users() {
		return adminManageUserDAO.findAll();
	}

}
