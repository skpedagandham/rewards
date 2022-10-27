package com.assignment.rewards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assignment.rewards.model.AccountRewards;

public interface RewardsRepository extends JpaRepository<AccountRewards, Long>{
	
	public List<AccountRewards> findAccountRewardsByAccountId(String accountId);
	
	@Query("SELECT SUM(rewards.currentRewards) FROM AccountRewards rewards WHERE rewards.accountId = :accountId")
	public Long findTotalRewardPointsByAccountId(@Param("accountId") String accountId);
	
	@Query(value = "SELECT SUM(CURRENT_REWARDS) FROM ACCOUNT_REWARDS WHERE MONTHNAME(LAST_UPDATE_DATE) = :monthName and YEAR(LAST_UPDATE_DATE) = YEAR(CURRENT_DATE) and account_id = :accountId", nativeQuery = true)
	public Long findMonthlyRewardPointsByAccountId(@Param("accountId") String accountId, @Param("monthName")String monthName);
}
