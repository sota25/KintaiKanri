package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.WorkTimes;

@Service
public interface WorkTimesService {

	public int insertWorkTimes(WorkTimes workTime);

	public List<WorkTimes> findAllWorkTimes(int monthId);

	public WorkTimes findOneWorkTimes(int workTimeId);

	public int updateWorkTimes(WorkTimes work);

	public int deleteWorkTimes(int workTimeId);
}
