package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Months;
import com.example.demo.repository.KintaiMapper;
import com.example.demo.service.MonthService;

@Service
public class MonthServiceImpl implements MonthService {

	@Autowired
	private KintaiMapper kintaiMapper;

	@Override
	public int insertMonts(Months month) {
		return kintaiMapper.insertMonths(month);
	}

	@Override
	public List<Months> findAllMonth(int contractId) {
		return kintaiMapper.findAllMonths(contractId);
	}

	@Override
	public Months findMonthOfWorkTime(int contractId, int year, int month) {
		return kintaiMapper.findMonthOfWorkTime(contractId, year, month);
	}

}
