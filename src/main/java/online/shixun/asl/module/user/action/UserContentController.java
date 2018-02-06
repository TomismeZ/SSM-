/*****************************************************************************
 * Copyright (c) 2016, www.qingshixun.com
 *
 * All rights reserved
 *
*****************************************************************************/
package online.shixun.asl.module.user.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import online.shixun.asl.core.BaseAction;
import online.shixun.asl.core.ResponseData;
import online.shixun.asl.dto.UserDTO;
import online.shixun.asl.module.department.service.DepartmentServiceImpl;
import online.shixun.asl.module.role.service.RoleServiceImpl;
import online.shixun.asl.module.user.service.UserServiceImpl;

@Controller
@RequestMapping("/user/content")
public class UserContentController extends BaseAction{
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@Autowired
	private DepartmentServiceImpl departmentService;
	
	/**
	 * 娣诲姞鐢ㄦ埛
	 * @param model
	 * @param userId
	 * @return
	 */
	@PostMapping("/add")
	public String main(Model model, @RequestParam("userId") Long userId) {
		model.addAttribute("user", userService.getUser(userId));
		model.addAttribute("roles", roleService.getSimpleRoles());
		model.addAttribute("departments", departmentService.getSimpleDepartments());
		
		return "/user/content/add";
	}
	
	/**
	 * 客户端访问服务器，登录
	 * @param user
	 * @return
	 */
	@RequestMapping("/login.action")
	@ResponseBody
	public Map<String,Object> login(@ModelAttribute UserDTO user) {
		Map<String,Object> result=null;
		if(userService.userLogin(user.getName(), user.getPassword())) {
			result = getSuccessResult(null);
		}else {
            result = getFailResult(-1,"用户名或者密码错误");
        }
		return result;
	}
	
	/**
	 * 鏂板鎴栨洿鏂扮敤鎴�
	 * @param user
	 * @return
	 */
	@PostMapping("/save")
	@ResponseBody
	public ResponseData save(@ModelAttribute UserDTO user) {
		userService.saveOrUpdateUser(user);
		
		return new ResponseData();
	}
	
	/**
	 * 鍒犻櫎鐢ㄦ埛
	 * @param userId
	 * @return
	 */
	@PostMapping("/remove")
	@ResponseBody
	public ResponseData remove(@RequestParam("userId") Long userId) {
		userService.removeUser(userId);
		
		return new ResponseData();
	}
	
	/**
	 * 鍒犻櫎澶氫釜鐢ㄦ埛
	 * @param userIds
	 * @return
	 */
	@PostMapping("/removes")
	@ResponseBody
	public ResponseData removes(@RequestParam("userIds") String userIds) {
		userService.removeUsers(userIds);
		
		return new ResponseData();
	}
	
	//处理客户端单个用户信息请求，返回json格式请求查询数据
    @RequestMapping("/getInfo.json")
    public void getInfoFromClient(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("id") Long id) {

        UserDTO user = new UserDTO();
        user.setId(id);

        response.setContentType("application/json");
        PrintWriter out = null;
        
      //  JSONObject json = new JSONObject();
        
      /*  try {   
            out = response.getWriter();

//            user = userService.queryByID(user);
            user = userService.getUser(id)
            if (user != null) {
                json.put("status", 1);
                json.put("user", user);
                out.write(json.toString());
            } else {        
                json.put("status", 0);
                json.put("user", null);
                out.write(json.toString());
            }
        } catch (Exception e) {
            e.toString();
            json.put("status", -1);
            json.put("user", null);
            out.write(json.toString());
        } finally{
            out.flush();
            out.close();
        }*/
    }  
    
    

}
