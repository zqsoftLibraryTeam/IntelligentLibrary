package com.intelligentLibrary.dev.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intelligentLibrary.dev.Dao.SeatDAO;
import com.intelligentLibrary.dev.Dao.UserDAO;
import com.intelligentLibrary.dev.domain.Occupyinfo;
import com.intelligentLibrary.dev.domain.Seat;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService{
	
	@Resource
	SeatDAO seatDAO;
	@Resource
	UserDAO userDAO;
	
	@Override
	public boolean Order(Integer userid, Integer deskid, Integer seatid,Integer zoneid) {
		
		int realseatid=seatDAO.getRealSeatid(deskid,seatid,zoneid);
		
		if(seatDAO.whetherordered(userid)||seatDAO.whetheroccupied(realseatid)){
			return false;
		}
		//更新座位信息
		seatDAO.updateSeatStatus(realseatid);
		//添加占座信息
		userDAO.addOccupiedinfo(userid, realseatid);
		return true;
	}

	@Override
	public boolean ReverseOrder(Integer userid, Integer deskid, Integer seatid) {
		seatDAO.updateSeatStatus(seatid);
		userDAO.deleteOccupiedinfo(userid,seatid);
		return true;
	}
	

	@Override
	public List<Seat> getCurrentZoneSeatStatus(Integer zoneid) {
		
		return seatDAO.getAllSeatIntheZone(zoneid);
	}

	@Override
	public List<Occupyinfo> occupyInfo(User user) {
		
		return userDAO.getOccpyInfo(user);
	}

	@Override
	public void reverseOrder(int userid) {
		User user=userDAO.getById(userid);
		seatDAO.reverseOrderSeat(user);
	}

}
