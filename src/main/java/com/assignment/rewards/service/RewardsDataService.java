package com.assignment.rewards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.assignment.rewards.model.AccountRewards;
import com.assignment.rewards.repository.RewardsRepository;

@Service
public class RewardsDataService {
	
	@Autowired
	private RewardsRepository rewardsRepository;
	
	public List<AccountRewards> findAccountRewardsByAccountId(String accountId) {		
		return rewardsRepository.findAccountRewardsByAccountId(accountId);
	}
	
	public Long findTotalRewardPointsByAccountId(String accountId) {
		return rewardsRepository.findTotalRewardPointsByAccountId(accountId);
	}
	
	public Long findMonthlyRewardPointsByAccountId(String accountId, String monthName) {
		return rewardsRepository.findMonthlyRewardPointsByAccountId(accountId, monthName);
	}
	
	public AccountRewards createAccountReward(AccountRewards accountReward) {
		rewardsRepository.save(accountReward);
		return accountReward;
	}
	
	public void deleteAccountReward(Long id) {
		rewardsRepository.deleteById(id);
	}
	
}
