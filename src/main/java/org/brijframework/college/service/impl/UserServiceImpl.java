package org.brijframework.college.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.brijframework.college.dao.LoginRoleDao;
import org.brijframework.college.dao.UserDao;
import org.brijframework.college.dao.UserRoleDao;
import org.brijframework.college.model.LoginRole;
import org.brijframework.college.model.User;
import org.brijframework.college.model.UserRole;
import org.brijframework.college.model.UserRolePrimaryKey;
import org.brijframework.college.model.util.PasswordEncoder;
import org.brijframework.college.models.dto.ChangePasswordDTO;
import org.brijframework.college.models.dto.UserDTO;
import org.brijframework.college.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl extends CRUDServiceImpl<Integer, User, UserDao>
		implements UserService {

	@Autowired
	public UserServiceImpl(UserDao dao) {
		super(dao);
	}

	@Autowired
	LoginRoleDao roleDao;
	@Autowired
	UserRoleDao userRoleDao;

	@Transactional
	public User getUserByUserName(String userName) {
		return dao.getUserByUsername(userName);

	}

	@Transactional
	public String checkUserByUserName(String userName) {
		User user = dao.getUserByUsername(userName);
		if (user == null) {
			return "no";
		} else {
			return "yes";
		}
	}

	@Transactional
	public String crateUser(UserDTO dto) {
		if (dao.getUserByUsername(dto.getUsername()) == null) {
			LoginRole role = roleDao.getUserRoleByName(dto.getRole());
			User user = new User();
			user.setEnabled(true);
			user.setPassword(PasswordEncoder.getEcodedPassword(dto
					.getPassword()));
			user.setUsername(dto.getUsername());
			UserRolePrimaryKey key = new UserRolePrimaryKey();
			key.setRole(role);
			key.setUser(dao.create(user));
			UserRole userRole = new UserRole();
			userRole.setUserRolePrimaryKey(key);
			userRoleDao.create(userRole);
			return "success";

		} else {
			return "exist";
		}

	}

	@Transactional
	public List<UserDTO> searchUsertByName(String userName) {
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		for (User user : dao.searchUsertByName(userName)) {
			UserDTO dto = new UserDTO();
			dto.setUsername(user.getUsername());
			dto.setRole(user.getUserRole().getUserRolePrimaryKey().getRole()
					.getName());
			dto.setId(user.getId());
			dtos.add(dto);

		}
		return dtos;
	}

	@Transactional
	public void disableUserById(int id) {
		dao.get(id).setEnabled(false);

	}

	@Transactional
	public UserDTO getById(int id) {
		User user = dao.get(id);
		UserDTO dto = new UserDTO();
		dto.setUsername(user.getUsername());
		dto.setRole(user.getUserRole().getUserRolePrimaryKey().getRole()
				.getName());
		dto.setId(user.getId());
		dto.setPassword(user.getPassword());
		return dto;
	}

	@Transactional
	public User getByUserId(int id) {
		return dao.get(id);
	}

	@Transactional
	public void updateUserById(UserDTO dto) {

		User user = dao.get(dto.getId());
		user.setUsername(dto.getUsername());
	}

	@Override
	public void changeAdminPassword(ChangePasswordDTO dto, User user) {
		user.setPassword(PasswordEncoder.getEcodedPassword(dto.getNewPassword()));
		dao.update(user);

	}

	@Override
	public User checkUserByUserName(String userName, String password,
			int retryCount) {
		String encodedPassword = PasswordEncoder.getEcodedPassword(password);
		return dao.getUserByUsername(userName, encodedPassword, retryCount);
	}

	@Override
	public User getUserByPassword(String password) {
		String encodedPassword = PasswordEncoder.getEcodedPassword(password);
		return dao.getUserByPassword(encodedPassword);
	}

	@Override
	public User checkUserByUserName(String userName, String password) {
		String encodedPassword = PasswordEncoder.getEcodedPassword(password);
		return dao.checkUserByUserName(userName, encodedPassword);
	}

}