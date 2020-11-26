/*package com.intelligentLibrary.recommand.corearithmetic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BookSort;
import com.intelligentLibrary.dev.domain.Comment;
import com.intelligentLibrary.dev.domain.SingleBookBorrowInfo;
import com.intelligentLibrary.dev.domain.Tag;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.recommand.data.GetData;

*//**
 * 杩欎釜绫讳富瑕佹槸涓�浜涙瘮杈冩簮鐢熺殑鏂规硶 绮楁毚鐨勯偅绉�
 * 
 * @author 1214
 *
 *//*
public class RawRecommand {

	GetData data = new GetData();

	*//**
	 * 閫氳繃鐭湡鐨勫�熼槄閲忚繘琛屾帓搴�
	 * 
	 * @param bookSort
	 * @param user
	 * @param month
	 *            琛ㄧず鏈�杩戝嚑涓湀
	 * @return
	 *//*
	public List<Book> getRecommandByBorrowNum(BookSort currentSort, User user, Integer month) {
		// 璁＄畻杩欎釜绫讳腑杩戝嚑涓湀鏉ユ瘡鏈功鐨勫�熼槄閲�
		*//**
		 *
		 * 杩欓噷瑕佽璁′竴涓柟娉� getBorrowNumByMonthAndBookSort(Integer month,BookSort
		 * bookSort) 杩斿洖鍗曟湰涔﹀嚑涓湀鍐呭�熼槄鐨勬暟閲�
		 *//*
		List<Book> booksInThisSort = new ArrayList<Book>();
		for (Book book : data.getBooks()) {
			BookSort tempsort = book.getIlBooksort();
			while (tempsort.getIlBooksort() != null) {
				if (tempsort.getSortname().equals(currentSort.getSortname())) {
					booksInThisSort.add(book);
					break;
				}
				tempsort = tempsort.getIlBooksort();
			}
		}
		List<SingleBookBorrowInfo> sbbis = new ArrayList<>();
		for (Book book : booksInThisSort) {
			*//**
			 * 鏂板缓涓�涓被鐢ㄤ簬瀛樺偍鏁版嵁锛岀被鍚�:SingleBookBorrowInfo
			 * 2涓睘鎬э紝涔﹀悕锛屽�熼槄閲�
			 *//*
			int borrownum = 0;
			for (Comment comment : data.getComments()) {
				if (comment.getBook().equals(book) && comment.getUser().equals(user)) {
					if (comment.getCommenttime().getYear() == new Date().getYear()) {
						if (new Date().getMonth() - comment.getCommenttime().getMonth() <= month) {
							borrownum++;
						}
					}
					if (new Date().getYear() - comment.getCommenttime().getYear() == 1) {
						if (new Date().getMonth() + 12 - comment.getCommenttime().getMonth() <= month) {
							borrownum++;
						}
					}
				}
			}
			SingleBookBorrowInfo sbbi = new SingleBookBorrowInfo();
			sbbi.setBook(book);
			sbbi.setBorrownumber(borrownum);
			sbbis.add(sbbi);
		}
		*//**
		 * 閬嶅巻sbbis
		 * 鏍规嵁borrownum鐨勬暟閲忚繘琛屾帓搴忓苟娣诲姞鍒伴泦鍚堥噷
		 *//*
		for (int i = 0; i < sbbis.size(); i++) {
			for (int j = i + 1; j <= sbbis.size() - 1; j++) {
				if (sbbis.get(i).getBorrownumber() < sbbis.get(j).getBorrownumber()) {
					SingleBookBorrowInfo temp = new SingleBookBorrowInfo();
					temp.setBook(sbbis.get(i).getBook());
					temp.setBorrownumber(sbbis.get(i).getBorrownumber());
					sbbis.get(i).setBook(sbbis.get(j).getBook());
					sbbis.get(i).setBorrownumber(sbbis.get(j).getBorrownumber());
					sbbis.get(j).setBook(temp.getBook());
					sbbis.get(j).setBorrownumber(temp.getBorrownumber());
				}
			}
		}
		*//**
		 * 鎺掑簭鍚庣殑List
		 *//*
		booksInThisSort.clear();
		for (int i = 0; i < sbbis.size(); i++) {
			booksInThisSort.add(sbbis.get(i).getBook());
		}
		return booksInThisSort;
	}

	*//**********************************************************************
	 * 鏍规嵁鍊熻繃鐨勪功鐨勬爣绛惧拰鍒殑涔︾殑鏍囩鐨勭浉浼兼�ц繘琛屾帓搴� * 姝ゆ柟娉曚富瑕佺敤浜庣敤鎴锋病鏈夐�夋嫨鏈�杩戞兂鐪嬬殑涔﹂偅涓」锛屾槸绂荤嚎鐨勬帹鑽� * 瀹氭湡鏇存柊 *
	 **********************************************************************//*
	public List<Book> tagSimilarity(User user) {
		*//**
		 * 鑾峰彇鐢ㄦ埛杩戞湡鐪嬭繃鐨勪功锛岀劧鍚庡姣旀瘡鏈功鍜屽綋鍓嶄功鍒嗙被閲岀殑鍏跺畠涔� 妫�鏌ユ爣绛炬槸鍚︾浉浼硷紝鏍规嵁鐩镐技鐨勭▼搴﹁繘琛屾帹鑽�
		 *//*
		List<Book> userHaveReadRecently = new ArrayList<Book>();
		for (Comment comment : data.getComments()) {
			if (comment.getUser().equals(user) && new Date().getMonth() - comment.getCommenttime().getMonth() <= 3) {
				userHaveReadRecently.add(comment.getBook());
			}
		}
		List<Book> booksToRecommand = new ArrayList<Book>();


		*//**
		 * 鏌ユ壘杩欐湰涔︽墍鍦ㄤ功鐨勫垎绫�
		 *//*
		for (Book book : userHaveReadRecently) {
			*//**
			 * 鎵惧埌杩欐湰涔︽墍鍦ㄧ殑澶у垎绫�
			 *//*
			BookSort currentBookSort = book.getIlBooksort();

			while (currentBookSort.getIlBooksort() != null) {
				currentBookSort = currentBookSort.getIlBooksort();
			}
			// 鎵惧嚭杩欎釜澶у垎绫讳笅鎵�鏈夌殑涔�
			List<Book> booksInThisBS = new ArrayList<Book>();
			for (Book temp : data.getBooks()) {
				BookSort tempsort = temp.getIlBooksort();
				while (tempsort != null) {
					if (tempsort.getSortname().equals(currentBookSort.getSortname())) {
						booksInThisBS.add(book);
						break;
					}
					tempsort = tempsort.getIlBooksort();
				}
			}

			for (Book canditate : booksInThisBS) {
				int similarity = 0;
				*//**
				 * 瀵规瘮褰撳墠涔﹀拰褰撳墠澶у垎绫讳笅鎵�鏈変功鐨勬爣绛� 濡傛灉鏍囩閲嶅绋嬪害瓒呰繃涓�涓暟灏辫〃绀鸿繖涓ゆ湰涔︽瘮杈冪浉浼硷紝鍙互杩涜鎺ㄨ崘
				 *//*
				for (Tag tag1 : canditate.getTags()) {
					for (Tag tag2 : book.getTags()) {
						if (tag1.getKeyword().equals(tag2.getKeyword())) {
							similarity++;
						}
					}
				}
				if (similarity >= 2) {
					booksToRecommand.add(canditate);
				}

			}

		}

		return booksToRecommand;
	}
}
*/