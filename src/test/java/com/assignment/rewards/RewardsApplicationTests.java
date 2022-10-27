package com.assignment.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.assignment.rewards.model.AccountRewards;
import com.assignment.rewards.service.RewardsLogicService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class RewardsApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;	
	
	@Autowired
	private RewardsLogicService rewardsLogicService;
	
	/*
	 * This is an integration test
	 */
	@Test
	void caculateActualRewardPoints() throws Exception {
		AccountRewards actualReward = new AccountRewards("Alberto", "Pereira", 
				120.00);
		 mockMvc.perform(MockMvcRequestBuilders.post("/rewards/createAccountReward")
		            .contentType("application/json")		            
		            .content(objectMapper.writeValueAsString(actualReward)))
		            .andExpect(MockMvcResultMatchers.status().isOk())
		            .andExpect(MockMvcResultMatchers.jsonPath("$.currentRewards").value(90));
	}
	
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
