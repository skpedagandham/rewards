package com.assignment.rewards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.assignment.rewards.model.AccountRewards;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class RewardsApplicationIntegrationTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;		
	
	/*
	 * This is an integration test
	 */
	@Test
	void caculateActualRewardPoints() throws Exception {
		
		//Amount spent above 100$
		AccountRewards actualRewardMorethan100 = new AccountRewards("Alberto", "Pereira", 
				120.00);
		mockMvc.perform(MockMvcRequestBuilders.post("/rewards/createAccountReward")
				.contentType("application/json")		            
				.content(objectMapper.writeValueAsString(actualRewardMorethan100)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.currentRewards").value(90));
		 
		//Amount spent below 100$
		AccountRewards actualRewardLessThan100 = new AccountRewards("Pierre", "Albert", 
					99.25);
		mockMvc.perform(MockMvcRequestBuilders.post("/rewards/createAccountReward")
				 .contentType("application/json")		            
				 .content(objectMapper.writeValueAsString(actualRewardLessThan100)))
		 		 .andExpect(MockMvcResultMatchers.status().isOk())
		 		 .andExpect(MockMvcResultMatchers.jsonPath("$.currentRewards").value(50));
			 
		
		//Amount spent 0$
		AccountRewards actualRewardFor0 = new AccountRewards("Sparrow", "Robert", 
					 0.00);
		mockMvc.perform(MockMvcRequestBuilders.post("/rewards/createAccountReward")
				.contentType("application/json")		            
				.content(objectMapper.writeValueAsString(actualRewardFor0)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.currentRewards").value(0));
		
		//Amount spent negative
		AccountRewards actualRewardForNegative = new AccountRewards("Bob", "Jacks", 
				 -1.00);
		mockMvc.perform(MockMvcRequestBuilders.post("/rewards/createAccountReward")
			.contentType("application/json")		            
			.content(objectMapper.writeValueAsString(actualRewardForNegative)))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.currentRewards").value(0));
	}	
}
