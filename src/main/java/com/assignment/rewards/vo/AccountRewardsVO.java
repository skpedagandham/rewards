package com.assignment.rewards.vo;

import java.time.LocalDateTime;

public class AccountRewardsVO {	
	
	private long id;	
	private String firstName;	
	private String lastName;	
	private String accountId;	
	private double purchasePrice;	
	private long currentRewards;	
	private LocalDateTime lastUpdateDate = LocalDateTime.now();
	
	public AccountRewardsVO() {		
	}
	
	public long getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public long getCurrentRewards() {
		return currentRewards;
	}

	public void setCurrentRewards(long currentRewards) {
		this.currentRewards = currentRewards;
	}

	public LocalDateTime getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
}	

	
	
	
