package com.intelligentLibrary.recommand.corearithmetic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BorrowInfo;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.service.RecommandService;

/**
 * 鍐欏叆鏂囦欢鏂逛究apriori杩涜璋冪敤
 * 
 * @author 12146
 *
 */
@Component
public class ReadIntoText {
	@Resource
	RecommandService recommandService;
	
	public void writeIntoFile() throws IOException {
		File file = new File("D:/干他妈的RFID/干他妈的工作空间/MainLibrary/sample.txt");
		try {
			FileOutputStream fib = new FileOutputStream(file);
			List<Book> books = recommandService.AprioriArithmeticProvider_Books();
			List<User> users = recommandService.AprioriArithmeticProvider_Users();
			List<BorrowInfo> borrowInfos = recommandService.AprioriArithmeticProvider();
			StringBuilder strbuf = new StringBuilder();
			strbuf.append("USER/BOOK ");
			for (Book book : books) {
				strbuf.append(book.getBookname() + " ");
			}
			strbuf.append("\n");
			for (User user : users) {
				strbuf.append(user.getUsername() + " ");
				for (Book book : books) {
					boolean flag = false;
					for (BorrowInfo borrowInfo : borrowInfos) {
						if (user.getUsername().equals(borrowInfo.getIlUser().getUsername())
								&& book.getBookname().equals(borrowInfo.getIlBook().getBookname())) {
							strbuf.append("T ");
							flag = true;
							break;
						}
					}
				if(flag)
				{
					continue;
				}
				else
				{
					strbuf.append("F ");
				}
				}
				strbuf.append("\n");
			}
			System.out.println(strbuf.toString());
			fib.write(strbuf.toString().getBytes());
			fib.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
