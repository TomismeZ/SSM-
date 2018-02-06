/*****************************************************************************
 * Copyright (c) 2016, www.qingshixun.com
 *
 * All rights reserved
 *
*****************************************************************************/
package online.shixun.asl.module.user.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.shixun.asl.dto.UserDTO;
import online.shixun.asl.module.department.service.DepartmentServiceImpl;
import online.shixun.asl.module.user.dao.UserDaoMyBatis;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserDaoMyBatis userDao;
	
	@Autowired
	private DepartmentServiceImpl departmentService;

	/**
	 * 鑾峰彇鐢ㄦ埛鍒楄〃
	 * @return
	 */
	public List<UserDTO> getUsers() {
		return userDao.getUsers();
	}
	
	/**
	 * 鏍规嵁鐢ㄦ埛id鑾峰彇鐢ㄦ埛淇℃伅
	 * @param userId
	 * @return
	 */
	public UserDTO getUser(Long userId) {
		if (userId == -1L) {
			return new UserDTO();
		}
		
		return userDao.getUser(userId);
	}
	
	/**
	 * 鏂板鎴栨洿鏂扮敤鎴�
	 * @param user
	 */
	@Transactional
	public void saveOrUpdateUser(UserDTO user) {
		// 鏂板骞跺～鍏卛d
		userDao.saveOrUpdateUser(user);
		
		// 鑾峰彇閮ㄩ棬缂栫爜
		String code = departmentService.getDepartmentCode(user.getDepartment().getId());
		
		// 鏍规嵁鐢ㄦ埛id鍜岄儴闂╟ode锛屾洿鏂扮敤鎴穋ode
		userDao.updateUserCode(user.getId(), code + "-" + user.getId());
	}

	/**
	 * 鏍规嵁鐢ㄦ埛id鍒犻櫎鐢ㄦ埛
	 * @param userId
	 */
	public void removeUser(Long userId) {
		userDao.removeUser(userId);
	}
	
	/**
	 * 鏍规嵁鐢ㄦ埛id鍒犻櫎澶氫釜鐢ㄦ埛
	 * @param userIds
	 */
	public void removeUsers(String userIds) {
		userDao.removeUsers(userIds);
	}
	
	public boolean userLogin(String username,String password) {
		UserDTO userDTO=userDao.userLogin(username, password);
		if(userDTO!=null) {
			return true;
		}else {
			return false;
		}
		
	}
	
}
