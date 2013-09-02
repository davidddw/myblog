package org.tcloud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tcloud.domain.User;
import org.tcloud.support.StringHelper;

@Controller("userController")  
@RequestMapping("/admin")
public class UserController extends BaseController {
	
	/** 保存新增 */  
	@RequestMapping(method=RequestMethod.POST, value="/user")
	public @ResponseBody User addUser(@RequestBody User user) {
		return userService.addNewUser(user);
	}
	
	/** 显示 **/ 
	@RequestMapping(method=RequestMethod.GET, value="/user/{id}")
	public @ResponseBody User getUser(@PathVariable String id) {
		User user = userService.findById(Integer.parseInt(id));
		return user;
	}
	
	/** 显示全部  **/
	@RequestMapping(method=RequestMethod.GET, value="/user")
	public @ResponseBody Map<String, Object> getAllUsers(HttpServletRequest request,
			@ModelAttribute("globalSetting") HashMap<String, String> globalSetting) {
		int page = StringHelper.parseWithDefault(request.getParameter("page"), 0);
		int pageSize = StringHelper.parseWithDefault(globalSetting.get("adminPageSize"),0);
		Page<User> users = userService.findAllUsers(page, pageSize);
		Map<String, Object> returnValues = new HashMap<String, Object>();
		List<Object> userList = new ArrayList<Object>();
		
		for (User user : users.getContent()){
			HashMap<String, String> userJson = new HashMap<String, String>();
			userJson.put("id", user.getId()+"");
			userJson.put("name", user.getName());
			userJson.put("status", user.getUserStatus().toString());
			userJson.put("date", StringHelper.dateToString(user.getRegisteredDate()));
			userJson.put("email", user.getEmail());
			userList.add(userJson);
		}
		returnValues.put("user", userList);
		returnValues.put("totalElements", users.getTotalElements());
		returnValues.put("static", getGlobalSettings());
		return returnValues;
	}
	
	/** 保存更新 */ 
	@RequestMapping(method=RequestMethod.PUT, value="/user/{id}")
	public @ResponseBody User updateUser(@RequestBody User user, @PathVariable String id) {
		return userService.updateUser(user);
	}
	
	/** 删除 */  
	@RequestMapping(method=RequestMethod.DELETE, value="/user/{id}")
	public @ResponseBody Map<String, Object> removeUser(@PathVariable String id) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		returnValues.put("result", userService.removeUserById(Integer.parseInt(id)));
		return returnValues;
	}
	
	/** 批量删除 */  
    @RequestMapping(method=RequestMethod.DELETE, value="/user")  
    public @ResponseBody void batchDelete(@RequestParam("items") Integer[] items) {  
        for(int i = 0; i < items.length; i++) {    
        	articleService.removeArticleById(items[i]);  
        }  
    }  
}