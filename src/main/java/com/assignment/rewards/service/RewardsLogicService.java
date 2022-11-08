package com.assignment.rewards.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.assignment.rewards.model.AccountRewards;
import com.assignment.rewards.util.RewardsUtil;

@Service
public class RewardsLogicService {
	public AccountRewards calculateRewardPoints(AccountRewards accountReward) {
		long rewardPoints = 0;
		if(accountReward != null) {
			double purchasePrice = accountReward.getPurchasePrice();
			if(purchasePrice > RewardsUtil.ZERO_AMOUNT) {
				if(purchasePrice > RewardsUtil.HUNDRED) {
					long differenceAmount = ((long) Math.ceil(purchasePrice - Double.valueOf(RewardsUtil.HUNDRED)));
					rewardPoints += (RewardsUtil.TW0 * differenceAmount);
				}
				
				if(purchasePrice > RewardsUtil.FIFTY) {
					rewardPoints += (RewardsUtil.ONE * RewardsUtil.FIFTY);
				}
			}
			
			accountReward.setCurrentRewards(rewardPoints);
		}	
		
		return accountReward;
	}
	
	public AccountRewards generateAccountId(AccountRewards accountReward) {
		if(accountReward != null) {
		String accountId = accountReward.getAccountId();
			if(accountId == null || accountId.isEmpty()) {
				String l_accountId = accountReward.getFirstName().substring(0, 1) + accountReward.getLastName().subSequence(0, 1) + UUID.randomUUID();
				accountReward.setAccountId(l_accountId);
			}			
		}
		
		
		return accountReward;
	}
}
