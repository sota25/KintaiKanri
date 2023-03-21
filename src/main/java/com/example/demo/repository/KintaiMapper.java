package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.example.demo.common.KintaiConstants;
import com.example.demo.entity.Contracts;
import com.example.demo.entity.Months;
import com.example.demo.entity.Users;
import com.example.demo.entity.WorkTimes;

/**
 * Mapperインターフェース
 * 
 * @author S.TAKASUGI
 *
 */
@Mapper
public interface KintaiMapper {

	/**
	 * ユーザー登録処理Mapper
	 * 
	 * @param users
	 */
	public int insertUser(Users users);

	/**
	 * ログインユーザー取得Mapper(1件)
	 * 
	 * @param email ログインメールアドレス
	 * @return usersテーブルデータ(1件)
	 */
	public Users findLoginUser(String email);

	/**
	 * 契約登録処理Mapper
	 * 
	 * @param contracts contractsテーブル
	 * @return 登録可否
	 */
	public int insertCont(Contracts contracts);

	/**
	 * 
	 * @param userId ログインユーザーID
	 * @return contractテーブルリスト
	 */
	public List<Contracts> findAllCont(int userId);

	/**
	 * 
	 * @param contractId
	 * @return
	 */
	public Contracts findOneCont(int contractId);

	/**
	 * 現在の契約取得Mapper
	 * 
	 * @param userId ログインユーザーID
	 * @return contractテーブル
	 */
	public Contracts findCurrentCont(int userId);

	/**
	 * 月登録Mapper
	 * 
	 * @param month
	 * @return 登録可否
	 */
	public int insertMonths(Months month);

	/**
	 * Monthsテーブルデータ取得Mapper(複数)
	 * 
	 * @param contractId contractsテーブルID
	 * @return Monthsテーブルデータ取得(複数)
	 */
	public List<Months> findAllMonths(int contractId);

	/**
	 * 
	 * @param contractId
	 * @param year
	 * @param month
	 * @return
	 */
	public Months findMonthOfWorkTime(@Param(KintaiConstants.PARAM_CONTRACT_ID) int contractId,
			@Param(KintaiConstants.PARAM_YEAR) int year, @Param(KintaiConstants.PARAM_MONTH) int month);

	/**
	 * 
	 * @param workTime
	 * @return
	 */
	public int insertWorkTimes(WorkTimes workTime);

	/**
	 * 
	 * @param monthId
	 * @return
	 */
	public List<WorkTimes> findAllWorkTimes(int monthId);

	/**
	 * 
	 * @param workTimeId
	 * @return
	 */
	public WorkTimes findOneWorkTimes(int workTimeId);

	public int updateWorkTimes(WorkTimes work);

}
