package com.intelligentLibrary.dev.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intelligentLibrary.dev.Dao.AdminManageBookDAO;
import com.intelligentLibrary.dev.Dao.BookSortDAO;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BookShell;
import com.intelligentLibrary.dev.domain.BookSort;
import com.intelligentLibrary.dev.service.ManageBookService;
import com.intelligentLibrary.dev.utils.AdminConfig;
import com.intelligentLibrary.dev.utils.SplitPage;

@Service
public class ManageBookServiceImpl implements ManageBookService {
	
	@Resource
	AdminManageBookDAO adminManageBookDAO;
	
	@Resource
	BookSortDAO bookSortDAO;

	@Override
	public boolean addBook(Book book) {
		return adminManageBookDAO.addBook(book);
		
	}

	@Override
	public List<Book> getBooks() {
		return adminManageBookDAO.findAll();
	}

	@Override
	public void deleteBook(Integer id) {
		adminManageBookDAO.delete(id);
		
	}

	@Override
	public Book findBookByid(Integer id) {
		return adminManageBookDAO.getById(id);
	}

	@Override
	public boolean editBook(Book book) {
		return adminManageBookDAO.alertBook(book);
		
	}

	@Override
	public Book getBookById(Integer id) {
		return adminManageBookDAO.getById(id);
	}

	@Override
	public boolean deleteBooks(List<Integer> bookidlist) {
		try {
			for(int i=0;i<bookidlist.size();i++){
				deleteBook((Integer)bookidlist.get(i));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public BookSort getBookSortBySortName(String sortname) {
		return adminManageBookDAO.getBookSortBySortName(sortname);
	}

	@Override
	public BookShell getBookShellByLoc(String loc) {
		return adminManageBookDAO.getBookShellByShellName(loc);
	}

	@Override
	public List<Book> getsubBook(SplitPage p) {
		p.setTotalnum((adminManageBookDAO.getAllBookRecall())/(double)p.getObjnum());
		return adminManageBookDAO.getSubBooks(p);
	}

	@Override
	public void gettotalPageNum(SplitPage p) {
		p.setTotalnum((adminManageBookDAO.getAllBookRecall())/(double)p.getObjnum());
	}

	@Override
	public List<BookSort> getAllTopSortName() {
		return bookSortDAO.getAllTopSortName();
	}

	@Override
	public List<BookSort> getSubSortByParId(Integer pid) {
		return bookSortDAO.getSubSortByParId(pid);
	}
}
