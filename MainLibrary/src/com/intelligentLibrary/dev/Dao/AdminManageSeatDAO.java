package com.intelligentLibrary.dev.Dao;

import com.intelligentLibrary.dev.domain.Seat;

public interface AdminManageSeatDAO {
	Seat getSeatById(int id);
	
	/**
	 * 更新座位的状态
	 * @param seat
	 */
	void updateSeatStatus(Seat seat);
}
