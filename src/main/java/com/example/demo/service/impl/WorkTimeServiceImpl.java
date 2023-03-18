package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.WorkTimes;
import com.example.demo.repository.KintaiMapper;
import com.example.demo.service.WorkTimeService;

/**
 * 
 * @author sotatk25
 *
 */
@Service
public class WorkTimeServiceImpl implements WorkTimeService {

	@Autowired
	private KintaiMapper kintaiMapper;

	@Override
	public int insertWorkTimes(WorkTimes workTime) {
		return kintaiMapper.insertWorkTimes(workTime);
	}

}
