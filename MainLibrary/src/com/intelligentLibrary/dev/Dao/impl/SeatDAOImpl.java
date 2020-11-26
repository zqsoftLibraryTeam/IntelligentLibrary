package com.intelligentLibrary.dev.Dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intelligentLibrary.dev.Dao.SeatDAO;
import com.intelligentLibrary.dev.domain.Occupyinfo;
import com.intelligentLibrary.dev.domain.Seat;
import com.intelligentLibrary.dev.domain.User;

@Repository
@Transactional
public class SeatDAOImpl implements SeatDAO {

	@Resource
	SessionFactory sessionFactory;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Seat getSeatById(int id) {
		return (Seat) getSession().createQuery("From Seat Where id =?").setParameter(0, id).uniqueResult();
	}

	@Override
	public void updateSeatStatus(int seatid) {
		Seat seat = getSeatById(seatid);
		if (seat.getOccupied().equals((short) 0)) {
			seat.setOccupied((short) 1);
		} else {
			seat.setOccupied((short) 0);
		}
		getSession().update(seat);
	}

	@Override
	public boolean whetherordered(int userid) {
		@SuppressWarnings("unchecked")
		List<Occupyinfo> lists = getSession().createQuery("from Occupyinfo where userid=?").setParameter(0, userid)
				.list();
		if (lists.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean whetheroccupied(int seatid) {
		Seat seat = getSeatById(seatid);
		if (seat.getOccupied().equals((short) 1)) {
			return true;
		}
		return false;
	}

	@Override
	public List<Seat> getAllSeatIntheZone(int zoneid) {

		List<Seat> seats = getSession().createQuery("from Seat s where s.desk.floor = ?").setParameter(0, zoneid)
				.list();
		return seats;
	}

	@Override
	public int getRealSeatid(Integer deskid, Integer seatid, Integer zoneid) {
		String position = "";
		switch (seatid) {
		case 1:
			position = "左上";
			break;
		case 2:
			position = "左下";
			break;
		case 3:
			position = "右下";
			break;
		case 4:
			position = "右上";
			break;
		}

		return (int) getSession()
				.createSQLQuery("select  il_seat.id  from il_seat join il_desk on deskid=desknum where "
						+ "floor = ? and il_seat.position = ? and deskid = ?")
				.setParameter(0, zoneid).setParameter(1, position).setParameter(2, deskid).uniqueResult();
	}

	@Override
	public void reverseOrderSeat(User user) {
		Occupyinfo occupyinfo=(Occupyinfo) getSession().createQuery("from Occupyinfo where user = ?").setParameter(0, user).uniqueResult();
		updateSeatStatus(occupyinfo.getSeat().getId());
		getSession().delete(occupyinfo);
		
	}

}
