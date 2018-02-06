/*****************************************************************************
 * Copyright (c) 2016, www.qingshixun.com
 *
 * All rights reserved
 *
*****************************************************************************/
package online.shixun.asl.module.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import online.shixun.asl.core.MyBatisRepository;
import online.shixun.asl.dto.UserDTO;

@MyBatisRepository
public interface UserDaoMyBatis {

	/**
	 * 鑾峰彇鐢ㄦ埛鍒楄〃
	 * @return
	 */
	List<UserDTO> getUsers();
	
	/**
	 * 鏍规嵁鐢ㄦ埛id鑾峰彇鐢ㄦ埛淇℃伅
	 * @param userId
	 * @return
	 */
	UserDTO getUser(@Param("userId") Long userId);
	
	/**
	 * 鏂板鎴栨洿鏂扮敤鎴�
	 * @param user
	 */
	void saveOrUpdateUser(UserDTO user);
	
	/**
	 * 鏇存柊鐢ㄦ埛缂栫爜
	 * @param userId
	 * @param code
	 */
	void updateUserCode(@Param("userId") Long userId, @Param("code") String code);
	
	/**
	 * 鏍规嵁鐢ㄦ埛id鍒犻櫎鐢ㄦ埛
	 * @param userId
	 */
	void removeUser(@Param("userId") Long userId);
	
	/**
	 * 鏍规嵁鐢ㄦ埛id鍒犻櫎澶氫釜鐢ㄦ埛
	 * @param userId
	 */
	void removeUsers(@Param("userIds") String userIds);
	
	/**
	 * 客户端验证，用户登录
	 * @param username
	 * @param password
	 */
	UserDTO userLogin(@Param("name") String username,@Param("password") String password);
	
}
