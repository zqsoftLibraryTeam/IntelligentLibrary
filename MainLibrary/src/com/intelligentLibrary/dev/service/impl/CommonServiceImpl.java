package com.intelligentLibrary.dev.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.intelligentLibrary.dev.Dao.CommonDAO;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BookBorrowNum;
import com.intelligentLibrary.dev.service.CommonService;


@Service
public class CommonServiceImpl implements CommonService {

	@Resource
	CommonDAO commonDAO;
	@Override
	public List<Book> getsubBook(int num) {
		return commonDAO.getSubBooks(num);
	}
	@Override
	public int getBookAllNum() {
		return (int)commonDAO.getAllBookRecall();
	}
	@Override
	public List<Book> getHotBooks(int num) {
		List<BookBorrowNum> topbook= commonDAO.getAllBookBorrowNumList();
		List<Book> books = new ArrayList<Book>();
		for(int i= 0;i<(topbook.size()>num?num:topbook.size());i++){
			books.add(commonDAO.getBookById(topbook.get(i).getBookid()));
		}
		return books;
	}
}
