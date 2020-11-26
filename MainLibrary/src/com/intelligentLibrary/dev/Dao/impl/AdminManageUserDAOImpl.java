package com.intelligentLibrary.dev.Dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.intelligentLibrary.dev.Dao.AdminManageUserDAO;
import com.intelligentLibrary.dev.base.BaseDAOImpl;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.utils.SplitPage;

@Repository
public class AdminManageUserDAOImpl extends BaseDAOImpl<User> implements
		AdminManageUserDAO {

	@Override
	public boolean deleteUser(String username) {
		try {
			Query query = getSession().createQuery("from User s where s.username = :username");
			query.setString("username", username);
			User user = (User)query.uniqueResult();
			getSession().delete(user);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	

	@Override
	public boolean addUser(User user) {
		try {
			getSession().save(user);
			return true;
		} catch (Exception e) {
			System.out.println("ÃÌº””√ªß ßŒÛ");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean alterUser(User user) {
		try {
			Query query = getSession().createQuery("update User s set s.username=?,s.password=?,s.sex=?,s.describtion=?,s.email=?,s.phone=?,s.name=?,s.timelimit=?,s.age=?,s.identify=? where s.userid=?");
			query.setParameter(0, user.getUsername());
			query.setParameter(1, user.getPassword());
			query.setParameter(2, user.getSex());
			query.setParameter(3, user.getDescribtion());
			query.setParameter(4, user.getEmail());
			query.setParameter(5, user.getPhone());
			query.setParameter(6, user.getName());
			query.setParameter(7, user.getTimelimit());
			query.setParameter(8, user.getAge());
			query.setParameter(9, user.getIdentify());
			query.setParameter(10, user.getUserid());
			query.executeUpdate(); 
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryUser() {
		return getSession().createQuery("from User").list();
	}


	@Override
	public User getUserById(Integer id) {
		try {
			Query query = getSession().createQuery("from User s where s.userid = :id");
			query.setLong("id", id);
			User user = (User)query.uniqueResult();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public long getAllUserRecall() {
		Query query = getSession().createQuery("select count(*) from User");
		return (long) query.uniqueResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<User> getSubUsers(SplitPage p) {
		Query query = getSession().createQuery("from User");
		query.setFirstResult(p.getStarindex());
		query.setMaxResults(p.getObjnum());
		return query.list();
	}


	@Override
	public User getUserByUserName(String username) {
		
		return (User) getSession().createQuery("from User where username = ?").setParameter(0, username).uniqueResult();
	}


	
}
