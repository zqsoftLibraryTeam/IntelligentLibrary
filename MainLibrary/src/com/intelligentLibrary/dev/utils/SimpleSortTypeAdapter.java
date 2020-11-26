package com.intelligentLibrary.dev.utils;

import java.io.IOException;

import javax.annotation.Resource;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.intelligentLibrary.dev.Dao.BookSortDAO;
import com.intelligentLibrary.dev.domain.BookSort;

public class SimpleSortTypeAdapter extends TypeAdapter<BookSort>{

	
	@Resource
	BookSortDAO bookSortDAO;
	
	
	@Override
	public BookSort read(JsonReader in) throws IOException {
		BookSort booksort = new BookSort();
		in.beginObject();
		while(in.hasNext()){
			switch (in.nextName()) {
	            case "sortid":
	                booksort.setSortid(in.nextInt());
	                break;
	            case "ilBooksort":
	            	booksort.setIlBooksort(bookSortDAO.getById(in.nextInt()));
	            	break;
	            case "sortname":
	                booksort.setSortname(in.nextString());
	                break;
			}
		}
		in.endObject();
		return booksort;
	}

	@Override
	public void write(JsonWriter out, BookSort bookSort) throws IOException {
		out.beginObject();
		out.name("sortid").value(bookSort.getSortid());
		out.name("ilBooksort").value(bookSort.getIlBooksort().getSortid());
		out.name("sortname").value(bookSort.getSortname());
		out.endObject();
	}

}
