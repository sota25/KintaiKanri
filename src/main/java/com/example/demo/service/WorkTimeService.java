package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.WorkTimes;

@Service
public interface WorkTimeService {

	public int insertWorkTimes(WorkTimes workTime);

}
