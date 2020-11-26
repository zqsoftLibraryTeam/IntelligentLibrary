/*package com.intelligentLibrary.recommand.corearithmetic;

import java.util.ArrayList;
import java.util.List;

import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BookSort;
import com.intelligentLibrary.dev.domain.Comment;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.recommand.data.StaticData;

*//**
 * 杩欎釜绫讳富瑕佽礋璐nn绠楁硶鍊肩殑璁＄畻
 * 
 * @author 12146
 *
 *//*
@Component
public class KNNarithmetic {
	*//***
	 * 濡傛灉鐢ㄦ埛鏇剧粡鐪嬭繃姝ょ被鍒殑涔︼紝骞朵笖杩涜杩囩浉搴旂殑璇勫垎锛堜笉鑰冭檻绋�鐤忥級鐨勬儏鍐典笅锛屽崗鍚岃繃婊ょ殑鏂规硶 杩涜鐩稿簲鐨勬帹鑽�
	 * bookSort:涔︾殑绫诲埆锛屽鏋滅敤鎴烽�夋嫨杩欎釜绫诲埆鐨勪功锛屾垜浠氨鍙互鍘绘壘杩欑被涔︿腑鐢ㄦ埛鐪嬭繃骞朵笖璇勪环杩囩殑涔� 鍜岃繖涓�绫讳腑鍏跺畠鐨勪功杩涜姣斿
	 * 闄勫姞锛氬鏋滄绫诲浘涔﹁祫婧愯繃灏戯紝寰�鐖跺垎绫婚噷杩涜鏌ユ壘 
	 * user:鐩爣鐢ㄦ埛
	 *//*
	
	public List<Book> BookToRecommand(BookSort bookSort, User user) {
		data.init();
		
		for(Comment comment : StaticData.comments)
		{
			System.out.println(comment.getBook().getBookname());
		}
		
		
		List<Book> booksToRecommand = new ArrayList<Book>();

		*//**
		 * 鍒ゆ柇杩欎釜绫诲埆閲岀殑涔︽槸鍚﹁冻澶熷锛屼笉澶熷灏卞幓鐖跺垎绫婚噷鏌ユ壘 鍏堟壘鍑鸿繖涓被鍒笅鎵�鏈夌殑涔︼紝闄ゅ幓鐢ㄦ埛宸茬粡鐪嬭繃鐨�
		 *//*
		while (bookSort.getBooknumber() < 20 && bookSort.getIlBooksort() != null) {
			bookSort = bookSort.getIlBooksort();
		}
		// 褰撳墠绫诲埆閲岀殑涔﹀皬浜庣瓑浜�20鏈椂鍊�,灏辨妸褰撳墠绫诲埆鐨勪功鐨勭埗绫讳紶缁欏畠
		*//**
		 * userHaveReadBefore:鐢ㄦ埛鍦ㄨ繖涓被鍒�(current)鐨勪功涓凡缁忕湅杩囩殑涔�
		 *//*
		List<Book> userHaveReadBefore = new ArrayList<Book>();
		for (Comment comment : StaticData.comments) {
			if (comment.getUser().getUsername().equals(user.getUsername())) {
				Book tempbook = comment.getBook();
				// 褰撴涔︾睄鐨勭埗绫荤殑鐖剁被鐨勭埗...涓哄綋鍓峜urrentsort鏃讹紝璇存槑杩欐湰涔﹀睘浜庤繖涓垎绫讳笅
				while (tempbook.getIlBooksort().getIlBooksort() != null) {
					if (tempbook.getIlBooksort().getSortname().equals(bookSort.getSortname())) {
						// 杩欐湰涔﹀湪杩欎釜鍒嗙被涓�
						userHaveReadBefore.add(tempbook);
						break;
					}
					tempbook.setIlBooksort(tempbook.getIlBooksort().getIlBooksort());
				}
			}
		}
		// 鎵惧嚭褰撳墠绫诲埆涓嬫墍鏈夌殑涔�
		List<Book> booksInThisSort = new ArrayList<Book>();
		for (Book book : StaticData.books) {
			BookSort tempsort = book.getIlBooksort();
			while (tempsort.getIlBooksort() != null) {
				if (tempsort.getSortname().equals(bookSort.getSortname())) {
					booksInThisSort.add(book);
					break;
				}
				tempsort = tempsort.getIlBooksort();
			}
		}

		booksInThisSort.removeAll(userHaveReadBefore);

		List<Book> candidate = booksInThisSort;

		List<Book> similarBook = new ArrayList<Book>();// 鍦ㄥ綋鍓嶇被鍒腑鎵剧浉浼煎害鏈�楂樼殑鍑犳湰涔︽妸瀹冧滑鏀惧埌杩欎釜闆嗗悎涓�
		for (Book book : candidate) {
			for (Book userRead : userHaveReadBefore) {
				// 鍦ㄧ洰鏍囩敤鎴疯瘎杩囧垎鐨勬绫讳功涓紝鎵惧嚭k涓笌涔ook鐩镐技搴︽渶楂樼殑涔︼紝骞剁敤N(u,
				// m)琛ㄧず杩檏鏈功鐨勯泦鍚堛��
				if (Pearson(book, userRead) >= 0.8) {
					similarBook.add(userRead);
				}
			}
			double totalvalue = (double) 0;
			double totalSimilarValue = (double) 0;
			int tempassess = 0;
			for (Book buk : similarBook) {
				double Similarvalue = Pearson(buk, book);
				// 棰勪及鐩爣鐢ㄦ埛瀵硅繖鏈功鐨勮瘎鍒�
				for (User myuser : StaticData.users) {
					if (myuser.getUserid().equals(user.getUserid())) {
						for (Comment comment : StaticData.comments) {
							if (comment.getBook().getBookid().equals(buk.getBookid())
									&& comment.getUser().getUserid().equals(myuser.getUserid())) {
								tempassess = comment.getCommentvalue();
								break;
							}

						}
						break;
					}
				}

				
				 * Assessment assessment = new Assessment();
				 * 
				 * int tempassess = (int)
				 * assessment.getAssessmentByUserAndBook(user, buk);
				 
				totalvalue += Similarvalue * tempassess;
				totalSimilarValue += Similarvalue;
			}
			System.out.println(totalvalue / totalSimilarValue);
			if (totalvalue / totalSimilarValue >= 7.0) {

				booksToRecommand.add(book);
			}

			similarBook.clear(); // 娓呯┖闆嗗悎
		}

		return booksToRecommand;
	}

	*//**
	 * 鐨皵妫叕寮�
	 * 
	 * @param b1
	 *            涔�1
	 * @param b2
	 *            涔�2
	 * @return 杩斿洖鍊间负鐩稿叧绯绘暟
	 *//*
	public Double Pearson(Book b1, Book b2) {
		// 姹傚嚭涓ゆ湰涔︾殑骞冲潎璇勫垎
				// 1.鍏堟壘鍑哄杩欎袱鏈功閮借瘎浠疯繃鐨勭敤鎴�
				*//**
				 * user1琛ㄧず璇勪环杩嘼1涔︾殑鐢ㄦ埛闆� user2琛ㄧず璇勪环杩嘼2涔︾殑鐢ㄦ埛闆� users涓洪兘璇勪环杩囩殑鐢ㄦ埛闆�
				 *//*
		data.init();
		List<User> user1 = new ArrayList<User>();
		List<User> user2 = new ArrayList<User>();
		for (Comment book : StaticData.comments) {
			if (book.getBook().equals(b1)) {
				user1.add(book.getUser());
			}
			if (book.getBook().getBookname().equals(b2.getBookname())) {
				user2.add(book.getUser());
			}
		}
		List<User> users = new ArrayList<User>();
		user1.retainAll(user2);
		users = user1;
		// 2.鍒嗗埆姹傚嚭杩欎簺鐢ㄦ埛瀵硅繖浜涗功鐨勮瘎鍒�,鐒跺悗姹傚钩鍧囧垎
				*//**
				 * 杩欓噷闇�瑕佷竴涓被锛岀敤浜庤〃绀烘瘡涓敤鎴峰姣忔湰涔︾殑璇勫垎 鎵�浠ヨ瘎鍒嗘瘡鏈功鐨勮瘎鍒嗕篃瑕佽鎴愪竴涓疄浣撶被 Assessment assessment=new
				 * Assessment(); 閲岄潰鏈�3涓垪锛寀ser浠ｈ〃鐢ㄦ埛锛宐ook浠ｈ〃涔︼紝assessment琛ㄧず璇勪环
				 * 鏈変竴涓柟娉曪紝杩斿洖鍊肩被鍨媎ouble锛歡etAssessmentByUserAndBook(User user,Book book)
				 *//*
		double b1avg = 0;
		double b2avg = 0;
		int b1total = 0;
		int b2total = 0;
		for (User user : users) {
			for (Comment comment : StaticData.comments) {
				if (comment.getUser().getUsername().equals(user.getUsername())) {
					if (comment.getBook().getBookid().equals(b1.getBookid())) {
						b1total += comment.getCommentvalue();
					} else if (comment.getBook().getBookid().equals(b2.getBookid())) {
						b2total += comment.getCommentvalue();
					}
				}
			}
		}
		b1avg = (double) b1total / users.size();
		b2avg = (double) b2total / users.size();

		// 璁＄畻pearson绯绘暟
		Double temptotal = (double) 0;
		Double u1temptotalvariance = (double) 0;
		Double u2temptotalvariance = (double) 0;
		int u1assessment = 0;
		int u2assessment = 0;
		for (User user : users) {
			for (Comment comment : StaticData.comments) {
				if (comment.getUser().getUserid().equals(user.getUserid())) {
					if (comment.getBook().getBookid().equals(b1.getBookid())) {
						u1assessment = comment.getCommentvalue();
					} else if (comment.getBook().getBookid().equals(b2.getBookid())) {
						u2assessment = comment.getCommentvalue();
					}
					temptotal += ((int) u1assessment - b1avg) * ((int) u2assessment - b2avg);
					u1temptotalvariance += Math.pow((int) u1assessment - b1avg, 2);
					u2temptotalvariance += Math.pow((int) u2assessment - b2avg, 2);
				}
			}

		}
		Double u1deviation = Math.sqrt(u1temptotalvariance / users.size());
		Double u2deviation = Math.sqrt(u2temptotalvariance / users.size());
		Double 鍗弙ariance = temptotal / users.size();
		if(users.size()==0)
		{
			return 0.0;
		}
		double result = 鍗弙ariance / (u1deviation * u2deviation);
		return result;
	}

}
*/