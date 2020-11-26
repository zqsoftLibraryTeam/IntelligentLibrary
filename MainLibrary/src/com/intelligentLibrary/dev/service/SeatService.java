package com.intelligentLibrary.dev.service;

import java.util.List;
import com.intelligentLibrary.dev.domain.Occupyinfo;
import com.intelligentLibrary.dev.domain.Seat;
import com.intelligentLibrary.dev.domain.User;

public interface SeatService {
	/**
	 * 规则:一个用户一次只能预定一个座位:先去查找Occupy表,查找当前用户有没有占座信息，若有返回false
	 *     :如果当前用户没有预定信息,就去找当前位置的状态	
	 * @param userid 用户的id
	 * @param deskid 桌子的id
	 * @param seatid 位置的id
	 * @return
	 */
	boolean Order(Integer userid,Integer deskid,Integer seatid,Integer zoneid);
	
	boolean ReverseOrder(Integer userid,Integer deskid,Integer seatid);
	
	
	/**
	 * 该方法为根据当前区域的id返回当前区域所有位置的状态
	 * @param zoneid
	 * @return
	 */
	List<Seat> getCurrentZoneSeatStatus(Integer zoneid);
	
	List<Occupyinfo> occupyInfo(User user);
	
	void reverseOrder(int userid);
}
