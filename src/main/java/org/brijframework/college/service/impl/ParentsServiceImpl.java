package org.brijframework.college.service.impl;

import org.brijframework.college.commom.util.RandomPasswordUtil;
import org.brijframework.college.dao.LoginRoleDao;
import org.brijframework.college.dao.ParentsDao;
import org.brijframework.college.dao.StudentsAdmissionDao;
import org.brijframework.college.dao.UserDao;
import org.brijframework.college.dao.UserRoleDao;
import org.brijframework.college.model.LoginRole;
import org.brijframework.college.model.Parents;
import org.brijframework.college.model.Students;
import org.brijframework.college.model.User;
import org.brijframework.college.model.UserRole;
import org.brijframework.college.model.UserRolePrimaryKey;
import org.brijframework.college.model.util.EmailUtility;
import org.brijframework.college.model.util.PasswordEncoder;
import org.brijframework.college.models.dto.ParentsDTO;
import org.brijframework.college.service.ParentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("parentsService")
public class ParentsServiceImpl extends
		CRUDServiceImpl<Integer, Parents, ParentsDao> implements ParentsService {

	@Autowired
	public ParentsServiceImpl(ParentsDao dao) {
		super(dao);
	}

	@Autowired
	LoginRoleDao roleDao;
	@Autowired
	UserDao userDao;
	@Autowired
	UserRoleDao userRoleDao;
	@Autowired
	StudentsAdmissionDao studentsAdmissionDao;

	@Transactional
	public String parentsRegistration(ParentsDTO dto) throws Exception {

		if (studentsAdmissionDao.get(dto.getStudentAdmissionNumber())
				.getParents() == null) {
			User user = new User();
			String userName = dto.getParentsName().substring(0, 3)
					+ RandomPasswordUtil.getRandomString();
			String password = RandomPasswordUtil.getRandomString();
			user.setEnabled(true);
			user.setPassword(PasswordEncoder.getEcodedPassword(password));
			user.setUsername(userName);
			LoginRole role = roleDao.getUserRoleByName("ROLE_PARENTS");
			UserRolePrimaryKey key = new UserRolePrimaryKey();
			key.setRole(role);
			key.setUser(userDao.create(user));
			UserRole userRole = new UserRole();
			userRole.setUserRolePrimaryKey(key);
			userRoleDao.create(userRole);

			Parents parents = new Parents();
			parents.setParentsEmail(dto.getParentsEmail());
			parents.setParentsMobile(dto.getParentsMobile());
			parents.setParentsName(dto.getParentsName());
			parents.setStudents(studentsAdmissionDao.getActive(Students.class,
					dto.getStudentAdmissionNumber()));
			parents.setUser(userDao.create(user));
			dao.create(parents);
			String msg = "You Are Successsfully Registered. Your Login Details is given below:<br>Username:"
					+ " " + userName + "<br>Password:" + " " + password;
			EmailUtility.sendEmailWithAttachment(dto.getParentsEmail(),
					"Parents Login Details", msg);
			return "success";
		} else {
			return "unsuccess";
		}
	}

}
