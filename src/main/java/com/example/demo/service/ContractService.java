package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Contracts;

@Service
public interface ContractService {

	/**
	 * 契約登録処理
	 * 
	 * @param contracts
	 * @return
	 */
	public int insertCont(Contracts contract);

	/**
	 * 現在の契約取得処理
	 * 
	 * @param userId ログインユーザーID
	 * @return contractテーブル
	 */
	public Contracts findCurrentCont(int userId);
}
