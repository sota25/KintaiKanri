package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contracts;
import com.example.demo.repository.KintaiMapper;
import com.example.demo.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService {

	@Autowired
	private KintaiMapper kintaiMapper;

	@Override
	public int insertCont(Contracts contract) {
		return kintaiMapper.insertCont(contract);
	}

	@Override
	public Contracts findCurrentCont(int userId) {
		return kintaiMapper.findCurrentCont(userId);
	}

}
