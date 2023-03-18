package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Months;

@Service
public interface MonthService {

	/**
	 * 
	 * @param month
	 * @return
	 */
	public int insertMonts(Months month);

	/**
	 * 
	 * @param contractId
	 * @return
	 */
	public List<Months> findAllMonth(int contractId);

	/**
	 * 
	 * @param contractId
	 * @param year
	 * @param month
	 * @return
	 */
	public Months findMonthOfWorkTime(int contractId, int year, int month);

}
