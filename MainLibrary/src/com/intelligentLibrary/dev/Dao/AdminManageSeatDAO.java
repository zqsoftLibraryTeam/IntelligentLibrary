package com.intelligentLibrary.dev.Dao;

import com.intelligentLibrary.dev.domain.Seat;

public interface AdminManageSeatDAO {
	Seat getSeatById(int id);
	
	/**
	 * ������λ��״̬
	 * @param seat
	 */
	void updateSeatStatus(Seat seat);
}
