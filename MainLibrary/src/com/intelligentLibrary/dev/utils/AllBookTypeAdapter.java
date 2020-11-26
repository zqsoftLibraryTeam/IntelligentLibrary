package com.intelligentLibrary.dev.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.service.ManageBookService;

public class AllBookTypeAdapter extends TypeAdapter<List<Book>> {

	@Resource
	ManageBookService manageBookService;
	
	@Override
	public List<Book> read(JsonReader reader) throws IOException {
		List<Book> books = new ArrayList<Book>();
		reader.beginArray();
		while(reader.hasNext()){
			books.add(readBook(reader));
		}
		return null;
	}
	public Book readBook(JsonReader reader) throws IOException{
		Book book = null;
		reader.beginObject();
		while(reader.hasNext()){
			switch (reader.nextName()) {
	            case "bookid":
	            	book=manageBookService.findBookByid(reader.nextInt());
	                break;
	            case "ilBookshell":
	                (book.getIlBookshell()).setLoc(reader.nextString()); 
	                break;
	            case "ilBooksort":
	            	(book.getIlBooksort()).setSortname(reader.nextString());
	            	break;
	            case "bookname":
	            	book.setBookname(reader.nextString());
	            	break;
	            case "isbn":
	                book.setIsbn(reader.nextString());
	                break;
	            case "author":
	                book.setAuthor(reader.nextString());
	                break;
	            case "press":
	                book.setPress(reader.nextString());
	                break;
	            case "picture":
	                book.setPicture(reader.nextString());
	                break;
	            case "status":
	                book.setStatus(reader.nextString());
	                break;
	            case "booknum":
	                book.setBooknum(reader.nextInt());
	                break;
			}
			if(book==null){
				break;
			}
		}
		return book;
		
	}
	@Override
	public void write(JsonWriter out, List<Book> books) throws IOException {
		out.beginArray();
		for (Book book : books) {
			writeBook(out,book);
		}
		out.endArray();
	}
	
	public void writeBook(JsonWriter out, Book book) throws IOException {
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
