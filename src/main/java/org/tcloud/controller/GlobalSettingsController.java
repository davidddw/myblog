package org.tcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tcloud.domain.GlobalSetting;

@Controller("globalSettingsController")  
@RequestMapping("/admin")
public class GlobalSettingsController extends BaseController {
	
	/** 保存新增 */  
	@RequestMapping(method=RequestMethod.POST, value="/global")
	public @ResponseBody GlobalSetting addGlobalSetting(@RequestBody GlobalSetting globalSettings) {
		return globalSettingService.addNewGlobalSetting(globalSettings);
	}
	
	/** 显示 **/ 
	@RequestMapping(method=RequestMethod.GET, value="/global/{id}")
	public @ResponseBody GlobalSetting getGlobalSetting(@PathVariable String id) {
		GlobalSetting globalSetting = globalSettingService.findById(Integer.parseInt(id));
		return globalSetting;
	}
	
	/** 显示全部  **/
	@RequestMapping(method=RequestMethod.GET, value="/global")
	public @ResponseBody List<GlobalSetting> getAllGlobalSettings(HttpServletRequest request) {
		return globalSettingService.findAllGlobalSetting();
	}
	
	/** 保存更新 */ 
	@RequestMapping(method=RequestMethod.PUT, value="/global/{id}")
	public @ResponseBody GlobalSetting updateGlobalSetting(@RequestBody GlobalSetting globalSettings, @PathVariable String id) {
		return globalSettingService.UpdateGlobalSetting(globalSettings);
	}
	
	/** 删除 */  
	@RequestMapping(method=RequestMethod.DELETE, value="/global/{id}")
	public @ResponseBody Map<String, Object> removeGlobalSetting(@PathVariable String id) {
		Map<String, Object> returnValues = new HashMap<String, Object>();
		returnValues.put("result", globalSettingService.removeGlobalSettingById(Integer.parseInt(id)));
		return returnValues;
	}
	
	/** 批量删除 */  
    @RequestMapping(method=RequestMethod.DELETE, value="/global")  
    public @ResponseBody void batchDelete(@RequestParam("items") Integer[] items) {  
        for(int i = 0; i < items.length; i++) {    
        	globalSettingService.removeGlobalSettingById(items[i]);  
        }  
    }  
}
