package org.tcloud.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tcloud.domain.GlobalSetting;
import org.tcloud.repository.GlobalSettingDao;

@Service("globalSettingService")
public class GlobalSettingService {
	private GlobalSettingDao globalSettingDao;
	
	public GlobalSettingDao getGlobalSettingDao() {
		return globalSettingDao;
	}
	
	@Resource
	public void setGlobalSettingDao(GlobalSettingDao globalSettingDao) {
		this.globalSettingDao = globalSettingDao;
	}

	@Transactional 
	public GlobalSetting findById(long globalSettingId){
		return globalSettingDao.findOne(globalSettingId);
	}
	
	@Transactional 
	public GlobalSetting addNewGlobalSetting(GlobalSetting globalSettings){
		return globalSettingDao.save(globalSettings);
	}
	
	@Transactional 
	public GlobalSetting UpdateGlobalSetting(GlobalSetting globalSettings){
		return globalSettingDao.save(globalSettings);
	}
	
	@Transactional 
	public HashMap<String, String> findAllGlobalSettings() {
		HashMap<String, String> globalSettings = new HashMap<String, String>();
		for (GlobalSetting g : globalSettingDao.findAllGlobalSettings()){
			globalSettings.put(g.getName(),g.getValue());
		}
		return globalSettings;
	}
	
	@Transactional 
	public List<GlobalSetting> findAllGlobalSetting() {
		return globalSettingDao.findAllGlobalSettings();
	}
	
	@Transactional 
	public boolean removeGlobalSetting(GlobalSetting globalSettings){
		GlobalSetting g = globalSettingDao.findOne(globalSettings.getId());
		if(g == null){
			return false;
		} else {
			globalSettingDao.delete(g);
			return true;
		}
	}
	
	@Transactional 
	public boolean removeGlobalSettingById(long globalSettingId){
		GlobalSetting g = globalSettingDao.findOne(globalSettingId);
		if(g == null){
			return false;
		} else {
			globalSettingDao.delete(g);
			return true;
		}
	}

}
