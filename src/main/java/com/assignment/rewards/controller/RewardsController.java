package com.assignment.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.rewards.exception.RewardsException;
import com.assignment.rewards.model.AccountRewards;
import com.assignment.rewards.service.RewardsDataService;
import com.assignment.rewards.service.RewardsLogicService;
import com.assignment.rewards.util.RewardsUtil;
import com.assignment.rewards.vo.AccountRewardsVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiva
 *
 */
@RestController
@RequestMapping("/rewards")
@Slf4j
public class RewardsController {
	
	@Autowired
	private RewardsDataService rewardsDataService;
	
	@Autowired
	RewardsLogicService rewardsLogicService;
	
	
	@GetMapping("/getAccountRewardsByAccountId")
	public ResponseEntity<List<AccountRewards>> getAccountRewardsByAccountId(@RequestParam(required = true) String accountId) throws RewardsException {
		try {			
			log.info("accountId:" + accountId);			
			return new ResponseEntity<>(rewardsDataService.findAccountRewardsByAccountId(accountId), HttpStatus.OK);
		} catch(Exception exception) {
			log.info(RewardsUtil.convertExceptionStackTraceToString(exception));
			throw new RewardsException(RewardsUtil.EXCEPTION_MESSAGE);			
		}		
	}
	
	@GetMapping("/getTotalRewardPointsByAccountId")
	public ResponseEntity<Long> getTotalRewardPointsByAccountId(@RequestParam(required = true) String accountId) throws RewardsException {
		try {
			log.info("accountId:" + accountId);			
			return new ResponseEntity<>(rewardsDataService.findTotalRewardPointsByAccountId(accountId), HttpStatus.OK);
		} catch(Exception exception) {
			log.info(RewardsUtil.convertExceptionStackTraceToString(exception));
			throw new RewardsException(RewardsUtil.EXCEPTION_MESSAGE);
		}	
	}
	
	@GetMapping("/getMonthlyRewardPointsByAccountId")
	public ResponseEntity<Long> getMonthlyRewardPointsByAccountId(@RequestParam(required = true) String accountId, @RequestParam(required = true)String monthName) throws RewardsException {
		try {
			log.info("accountId:" + accountId + ", monthName:" + monthName);			
			return new ResponseEntity<>(rewardsDataService.findMonthlyRewardPointsByAccountId(accountId, monthName), HttpStatus.OK);
		} catch(Exception exception) {
			log.info(RewardsUtil.convertExceptionStackTraceToString(exception));
			throw new RewardsException(RewardsUtil.EXCEPTION_MESSAGE);
		}
	}
	
	@PostMapping("/createAccountReward")
	public ResponseEntity<AccountRewardsVO> createAccountReward(@RequestBody AccountRewards accountReward) throws RewardsException {
		try {			
			accountReward = rewardsLogicService.generateAccountId(accountReward);
			accountReward = rewardsLogicService.calculateRewardPoints(accountReward);
			return new ResponseEntity<>(RewardsUtil.convertAccountRewardsToVO(rewardsDataService.createAccountReward(accountReward))
					, HttpStatus.OK);
		} catch(Exception exception) {			
			log.info(RewardsUtil.convertExceptionStackTraceToString(exception));
			throw new RewardsException(RewardsUtil.EXCEPTION_MESSAGE);
		}
	}
	
	@DeleteMapping("/deleteAccountReward/{id}")
	public ResponseEntity<HttpStatus> deleteAccountReward(@PathVariable("id") Long id) throws RewardsException {
		try {
			log.info("id:" + id);
			rewardsDataService.deleteAccountReward(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(Exception exception) {
			log.info(RewardsUtil.convertExceptionStackTraceToString(exception));
			throw new RewardsException(RewardsUtil.EXCEPTION_MESSAGE);
		}
	}
	
	@ExceptionHandler
	public ResponseEntity<String> handleRewardsException(RewardsException rewardsException) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RewardsUtil.EXCEPTION_MESSAGE);
	}
}
