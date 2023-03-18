package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contracts;
import com.example.demo.repository.KintaiMapper;
import com.example.demo.service.ContractService;

/**
 * 契約関連Service継承クラス
 * 
 * @author sotatk25
 *
 */
@Service
public class ContractServiceImpl implements ContractService {

	@Autowired
	private KintaiMapper kintaiMapper;

	@Override
	public int insertCont(Contracts contract) {
		return kintaiMapper.insertCont(contract);
	}

	@Override
	public List<Contracts> findAllCont(int userId) {
		return kintaiMapper.findAllCont(userId);
	}

	@Override
	public Contracts findOneCont(int contractId) {
		return kintaiMapper.findOneCont(contractId);
	}

	@Override
	public Contracts findCurrentCont(int userId) {
		return kintaiMapper.findCurrentCont(userId);
	}

}
