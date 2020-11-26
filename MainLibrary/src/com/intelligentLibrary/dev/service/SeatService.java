package com.intelligentLibrary.dev.service;

import java.util.List;
import com.intelligentLibrary.dev.domain.Occupyinfo;
import com.intelligentLibrary.dev.domain.Seat;
import com.intelligentLibrary.dev.domain.User;

public interface SeatService {
	/**
	 * ����:һ���û�һ��ֻ��Ԥ��һ����λ:��ȥ����Occupy��,���ҵ�ǰ�û���û��ռ����Ϣ�����з���false
	 *     :�����ǰ�û�û��Ԥ����Ϣ,��ȥ�ҵ�ǰλ�õ�״̬	
	 * @param userid �û���id
	 * @param deskid ���ӵ�id
	 * @param seatid λ�õ�id
	 * @return
	 */
	boolean Order(Integer userid,Integer deskid,Integer seatid,Integer zoneid);
	
	boolean ReverseOrder(Integer userid,Integer deskid,Integer seatid);
	
	
	/**
	 * �÷���Ϊ���ݵ�ǰ�����id���ص�ǰ��������λ�õ�״̬
	 * @param zoneid
	 * @return
	 */
	List<Seat> getCurrentZoneSeatStatus(Integer zoneid);
	
	List<Occupyinfo> occupyInfo(User user);
	
	void reverseOrder(int userid);
}
