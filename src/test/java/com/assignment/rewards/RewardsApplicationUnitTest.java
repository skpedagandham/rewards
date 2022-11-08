package com.assignment.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.assignment.rewards.model.AccountRewards;
import com.assignment.rewards.service.RewardsLogicService;

@SpringBootTest
public class RewardsApplicationUnitTest {
	
	@Autowired
	private RewardsLogicService rewardsLogicService;
	
	/*
	 * This is an unit test, checking logic from service layer
	 */
	@Test
	void testRewardPointsLogic_WithoutPersistenceLayer() throws Exception {
		AccountRewards actualRewardMorethan100 = new AccountRewards("Alberto", "Pereira", 
				120.00);
		assertEquals(rewardsLogicService.calculateRewardPoints(actualRewardMorethan100).getCurrentRewards(), 90);		
		
		AccountRewards actualRewardLessThan100 = new AccountRewards("Pierre", "Albert", 
				99.25);
		assertEquals(rewardsLogicService.calculateRewardPoints(actualRewardLessThan100).getCurrentRewards(), 50);
		
		AccountRewards actualRewardFor0 = new AccountRewards("Sparrow", "Robert", 
				0.00);
		assertEquals(rewardsLogicService.calculateRewardPoints(actualRewardFor0).getCurrentRewards(), 0);
		
		AccountRewards actualRewardForNegative = new AccountRewards("Bob", "Jacks", 
				-1.00);
		assertEquals(rewardsLogicService.calculateRewardPoints(actualRewardForNegative).getCurrentRewards(), 0);
		
	}
	
}
