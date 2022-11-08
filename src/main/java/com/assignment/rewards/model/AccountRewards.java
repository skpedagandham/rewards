package com.assignment.rewards.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "ACCOUNT_REWARDS")
public class AccountRewards {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "ACCOUNT_ID")
	private String accountId;
	
	@Column(name = "PURCHASE_PRICE")
	private double purchasePrice;
	
	@Column(name = "CURRENT_REWARDS")
	private long currentRewards;	
	
	@Column(name = "LAST_UPDATE_DATE")
	private LocalDateTime lastUpdateDate = LocalDateTime.now();
	
	public AccountRewards() {
		
	}
	
	public AccountRewards(long id, String firstName, String lastName, 
			String accountId, double purchasePrice, long currentRewards, LocalDateTime lastUpdateDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountId = accountId;
		this.purchasePrice = purchasePrice;
		this.currentRewards = currentRewards;
		this.lastUpdateDate = lastUpdateDate;		
	}
	
	public AccountRewards(String firstName, String lastName, 
			double purchasePrice) {	
		this.firstName = firstName;
		this.lastName = lastName;		
		this.purchasePrice = purchasePrice;			
	}	
}	

	
	
	
