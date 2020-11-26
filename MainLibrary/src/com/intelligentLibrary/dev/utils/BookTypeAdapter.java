package com.intelligentLibrary.dev.utils;

import java.io.IOException;

import javax.annotation.Resource;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.service.ManageBookService;

public class BookTypeAdapter extends TypeAdapter<Book>{

	
	@Resource
	ManageBookService manageBookService;
	
	
	@Override
	public Book read(JsonReader in) throws IOException {
		Book book = new Book();
		in.beginObject();
		while(in.hasNext()){
			switch (in.nextName()) {
	            case "bookid":
	                book.setBookid(in.nextInt());
	                break;
	                //先不进行Book的bookshell以及booksort的修改
//	            case "ilBookshell":
//	                book.setIlBookshell(manageBookService.getBookShellByLoc(in.nextString())); 
//	                break;
//	            case "ilBooksort":
//	            	book.setIlBooksort(manageBookService.getBookSortBySortName(in.nextString()));
//	            	break;
	            case "bookname":
	            	book.setBookname(in.nextString());
	            	break;
	            case "isbn":
	                book.setIsbn(in.nextString());
	                break;
	            case "author":
	                book.setAuthor(in.nextString());
	                break;
	            case "press":
	                book.setPress(in.nextString());
	                break;
	            case "picture":
	                book.setPicture(in.nextString());
	                break;
	            case "status":
	                book.setStatus(in.nextString());
	                break;
	            case "booknum":
	                book.setBooknum(in.nextInt());
	                break;
			}
		}
		in.endObject();
		return book;
	}

	@Override
	public void write(JsonWriter out, Book book) throws IOException {
		out.beginObject();
		out.name("bookid").value(book.getBookid());
		out.name("bookname").value(book.getBookname());
		out.name("isbn").value(book.getIsbn());
		out.name("author").value(book.getAuthor());
		out.name("ilBookshell");
		if(book.getIlBookshell()!=null){
			out.value(book.getIlBookshell().getLoc());
		}else{
			out.value(GsonMessage.BOOK_SHELL_NULL);
		}
		out.name("ilBooksort");
		if(book.getIlBooksort()!=null){
			out.value(book.getIlBooksort().getSortname());
		}else{
			out.value(GsonMessage.BOOK_SORT_NULL);
		}
		out.name("press").value(book.getPress());
		out.name("picture").value(book.getPicture());
		out.name("status").value(book.getStatus());
		out.name("booknum").value(book.getBooknum());
		out.endObject();
	}

}
