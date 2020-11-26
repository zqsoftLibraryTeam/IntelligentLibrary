package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.domain.Seat;
import com.intelligentLibrary.dev.domain.User;

public interface SeatDAO {
	/**
	 * ����id��λ��
	 * @param id Ψһ��ʶ���λ��
	 * @return
	 */
	Seat getSeatById(int id);
	/**
	 * ������λ��״̬
	 * @param seat
	 */
	void updateSeatStatus(int seatid);
	/**
	 * �ж��Ƿ���Ԥ����Ϣ���д���˵����ǰ�û���δ�黹λ��
	 * @param userid
	 * @return
	 */
	boolean whetherordered(int userid);
	/**
	 * ��ǰ��Ϊ�Ƿ�ռ����,���ǲ�����������
	 * @param seatid
	 * @return
	 */
	boolean whetheroccupied(int seatid);
	
	/**
	 * ���������id��ȡ���е�λ��
	 * @param zoneid �����id
	 * @return
	 */
	List<Seat> getAllSeatIntheZone(int zoneid);
	
	/**
	 * �������ӵ�id��λ�õ�id��ȡ��ʵ��λ�õ�id
	 * @param deskid
	 * @param seatid
	 * @return
	 */
	int getRealSeatid(Integer deskid, Integer seatid,Integer zoneid);
	
	/**
	 * ȡ��ռ��
	 * @param user
	 */
	void reverseOrderSeat(User user);
}
