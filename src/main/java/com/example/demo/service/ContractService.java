package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Contracts;

/**
 * 契約関連Serviceクラス
 * 
 * @author sotatk25
 *
 */
@Service
public interface ContractService {

	/**
	 * 契約登録処理
	 * 
	 * @param contracts
	 * @return 契約登録Mapper
	 */
	public int insertCont(Contracts contract);

	/**
	 * 
	 * @param userId ログインユーザーID
	 * @return 契約情報全建取得Mapper
	 */
	public List<Contracts> findAllCont(int userId);

	/**
	 * 
	 * @param contractId
	 * @return
	 */
	public Contracts findOneCont(int contractId);

	/**
	 * 現在の契約取得処理
	 * 
	 * @param userId ログインユーザーID
	 * @return 現在の契約取得Mapper
	 */
	public Contracts findCurrentCont(int userId);

	/**
	 * 
	 * @param contract
	 * @return
	 */
	public int updateCont(Contracts contract);

}
