package com.intelligentLibrary.dev.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intelligentLibrary.dev.Dao.UserDAO;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService {
	
	@Resource
	UserDAO userDAO;
	
	@Override
	public User Login(User user) { 
		User daouser = userDAO.getUser(user.getUsername());
		if(daouser != null){
			if(daouser.equals(user)){
				
				return daouser;
			}
		}
		return null;
	}
}
