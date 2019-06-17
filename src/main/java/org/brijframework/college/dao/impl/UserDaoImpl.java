package org.brijframework.college.dao.impl;

import java.util.List;

import org.brijframework.college.dao.UserDao;
import org.brijframework.college.model.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("userDao")
public class UserDaoImpl extends DAOImpl<Integer, User> implements UserDao {

	public User getUserByUsername(String username) {
		return (User) (sessionFactory.getCurrentSession().createCriteria(
				User.class).add(Restrictions.eq("username", username)))
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> searchUsertByName(String userName) {
		return (List<User>) (sessionFactory.getCurrentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("enabled", true)).add(Restrictions.like(
				"username", userName + "%"))).list();
	}

	@Override
	public User getUserByUsername(String username, String password,
			int retryCount) {
		return (User) (sessionFactory.getCurrentSession().createCriteria(
				User.class).add(Restrictions.eq("username", username)))
				.add(Restrictions.eq("password", password))
				.add(Restrictions.eq("retryCount", retryCount)).uniqueResult();
	}

	@Override
	public User getUserByPassword(String password) {
		return (User) (sessionFactory.getCurrentSession().createCriteria(
				User.class).add(Restrictions.eq("password", password)))
				.uniqueResult();
	}

	@Override
	public User checkUserByUserName(String userName, String password) {
		return (User) (sessionFactory.getCurrentSession().createCriteria(
				User.class).add(Restrictions.eq("username", userName))).add(
				Restrictions.eq("password", password)).uniqueResult();
	}
}
