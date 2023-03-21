package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.WorkTimes;
import com.example.demo.repository.KintaiMapper;
import com.example.demo.service.WorkTimesService;

/**
 * 
 * @author sotatk25
 *
 */
@Service
public class WorkTimesServiceImpl implements WorkTimesService {

	@Autowired
	private KintaiMapper kintaiMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insertWorkTimes(WorkTimes workTime) {
		return kintaiMapper.insertWorkTimes(workTime);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<WorkTimes> findAllWorkTimes(int monthId) {
		return kintaiMapper.findAllWorkTimes(monthId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WorkTimes findOneWorkTimes(int workTimeId) {
		return kintaiMapper.findOneWorkTimes(workTimeId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int updateWorkTimes(WorkTimes work) {
		return kintaiMapper.updateWorkTimes(work);
	}

}
