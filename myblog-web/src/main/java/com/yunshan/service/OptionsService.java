package com.yunshan.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yunshan.domain.Options;
import com.yunshan.repository.OptionsDao;

@Service("optionsService")
public class OptionsService {
	private OptionsDao optionsDao;
	
	public OptionsDao getOptionsDao() {
		return optionsDao;
	}
	
	@Resource
	public void setOptionDao(OptionsDao optionsDao) {
		this.optionsDao = optionsDao;
	}

	@Transactional 
	public Options findById(long optionId){
		return optionsDao.findOne(optionId);
	}
	
	@Transactional 
	public Options addNewOption(Options options){
		return optionsDao.save(options);
	}
	
	@Transactional 
	public Options updateOption(Options options){
		return optionsDao.save(options);
	}
	
	@Transactional 
	public HashMap<String, String> findAllOptions() {
		HashMap<String, String> options = new HashMap<String, String>();
		for (Options option : optionsDao.getOptions()){
			options.put(option.getName(), option.getValue());
		}
		return options;
	}
	
	@Transactional 
	public List<Options> findOptions() {
		return optionsDao.getOptions();
	}
	
	@Transactional 
	public boolean removeOption(Options options){
		Options option = optionsDao.findOne(options.getId());
		if(option == null){
			return false;
		} else {
			optionsDao.delete(option);
			return true;
		}
	}
	
	@Transactional 
	public boolean removeOptionById(long optionId){
		Options g = optionsDao.findOne(optionId);
		if(g == null){
			return false;
		} else {
			optionsDao.delete(g);
			return true;
		}
	}

}
