package com.intelligentLibrary.dev.service;

import com.intelligentLibrary.dev.domain.User;

public interface LoginService {
	/**
	 * �û���¼
	 * @param user
	 * @return
	 */
	public User Login(User user);
}
