package com.assignment.rewards.util;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.assignment.rewards.model.AccountRewards;
import com.assignment.rewards.vo.AccountRewardsVO;

public class RewardsUtil {
	public static final Integer HUNDRED = 100;
	public static final Integer FIFTY = 50;
	public static final Double ZERO_AMOUNT = 0.0;
	public static final Integer TW0 = 2;
	public static final Integer ONE = 1;
	public static final String UNDER_SCORE = "_";
	public static final String EXCEPTION_MESSAGE = "Application Error! while processing the request.";
	
	public static final String convertExceptionStackTraceToString(Exception exception) {
		return ExceptionUtils.getStackTrace(exception);		
	}
	
	public static AccountRewardsVO convertAccountRewardsToVO(AccountRewards accountRewards) {
		AccountRewardsVO accountRewardsVO = new AccountRewardsVO();
		accountRewardsVO.setAccountId(accountRewards.getAccountId());
		accountRewardsVO.setCurrentRewards(accountRewards.getCurrentRewards());
		accountRewardsVO.setFirstName(accountRewards.getFirstName());
		accountRewardsVO.setLastName(accountRewards.getLastName());
		accountRewardsVO.setLastUpdateDate(accountRewards.getLastUpdateDate());
		accountRewardsVO.setPurchasePrice(accountRewards.getPurchasePrice());
		return accountRewardsVO;
	}
}
