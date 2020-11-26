package com.intelligentLibrary.dev.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.intelligentLibrary.dev.Dao.BookSortDAO;
import com.intelligentLibrary.dev.domain.BookSort;

public class SimpleAllSortTypeAdapter extends TypeAdapter<List<BookSort>> {
	
	@Resource
	BookSortDAO bookSortDAO;
	
	
	@Override
	public List<BookSort> read(JsonReader reader) throws IOException {
		List<BookSort> booksort = new ArrayList<BookSort>();
		reader.beginArray();
		while(reader.hasNext()){
			booksort.add(readSort(reader));
		}
		return null;
	}
	public BookSort readSort(JsonReader in) throws IOException{
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
	public void write(JsonWriter out, List<BookSort> books) throws IOException {
		out.beginArray();
		for (BookSort book : books) {
			writeSort(out,book);
		}
		out.endArray();
	}
	
	public void writeSort(JsonWriter out, BookSort bookSort) throws IOException {
		out.beginObject();
		out.name("sortid").value(bookSort.getSortid());
		out.name("ilBooksort");
		if(bookSort.getIlBooksort() != null){
			out.value(bookSort.getIlBooksort().getSortid());
		}else{
			out.value("");
		}
		out.name("sortname").value(bookSort.getSortname());
		out.endObject();
	}

}
