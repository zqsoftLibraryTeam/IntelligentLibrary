package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.domain.Seat;
import com.intelligentLibrary.dev.domain.User;

public interface SeatDAO {
	/**
	 * 根据id找位置
	 * @param id 唯一标识这个位置
	 * @return
	 */
	Seat getSeatById(int id);
	/**
	 * 更新座位的状态
	 * @param seat
	 */
	void updateSeatStatus(int seatid);
	/**
	 * 判断是否有预定信息，有代表说明当前用户还未归还位置
	 * @param userid
	 * @return
	 */
	boolean whetherordered(int userid);
	/**
	 * 当前作为是否被占据了,考虑并发量的问题
	 * @param seatid
	 * @return
	 */
	boolean whetheroccupied(int seatid);
	
	/**
	 * 根据区域的id获取所有的位置
	 * @param zoneid 区域的id
	 * @return
	 */
	List<Seat> getAllSeatIntheZone(int zoneid);
	
	/**
	 * 根据桌子的id和位置的id获取真实的位置的id
	 * @param deskid
	 * @param seatid
	 * @return
	 */
	int getRealSeatid(Integer deskid, Integer seatid,Integer zoneid);
	
	/**
	 * 取消占座
	 * @param user
	 */
	void reverseOrderSeat(User user);
}
