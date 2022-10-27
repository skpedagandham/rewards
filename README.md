Steps for Running the application

1. Download the project to any folder in the system.
2. Import the project into Eclipse workspace by using the 'Existing Maven Projects' under Maven option.
3. Once the default build process is completed, navigate to package com.assignment.rewards.RewardsApplication under rewards project.
4. Right cick on the RewardsApplication in the left hand pane section src/main/java.
5. Under Run As option click Java Application to start Rewards Application.


Pushing and Fetching data to and from application.
1. Use postman tool to point to the below url with post method being selected
http://localhost:8080/rewards/createAccountReward

in the request body section of postman use the following json with json option being selected from drop down and also raw option.

{
	"firstName" : "XYZ",
	"lastName" : "ABC",	
    "purchasePrice" : "120.0"
}

Once the request is sent, response will be received something like this
{
    "id": 5,
    "firstName": "XYZ",
    "lastName": "ABC",
    "accountId": "XA1ed9a711-4017-4960-917a-e60a473bd26f",
    "purchasePrice": 120.0,
    "currentRewards": 90,
    "lastUpdateDate": "2022-10-27T15:37:18.1234094"
}

Use the account id received from the response for future requests for the user, execute
{
	"firstName" : "XYZ",
	"lastName" : "ABC",	
    "purchasePrice" : "120.0",
	"accountId" : "XA1ed9a711-4017-4960-917a-e60a473bd26f",
}

2. Reward Points earned for every month can be fetched by using following url with get method in postman
http://localhost:8080/rewards/getMonthlyRewardPointsByAccountId?accountId=SP1ed9a711-4017-4960-917a-e60a473bd26f&monthName=August

3. Total Reward Points earned can be fetched by using following url with get method in postman
http://localhost:8080/rewards/getTotalRewardPointsByAccountId?accountId=SP1ed9a711-4017-4960-917a-e60a473bd26f



Steps for Viewing data in H2 in memory database
1. H2 console can be viewed from the following url
http://localhost:8080/h2-console

2. Table creation statement
CREATE TABLE ACCOUNT_REWARDS(ID BIGINT PRIMARY KEY, FIRST_NAME VARCHAR(255), LAST_NAME VARCHAR(255), ACCOUNT_ID VARCHAR(100), 
PURCHASE_PRICE NUMERIC(20, 2), CURRENT_REWARDS INT, LAST_UPDATE_DATE TIMESTAMP);

3. Fetching records from the table
SELECT * FROM ACCOUNT_REWARDS;

4. Inserting records into table for other than current months as application only pushes data based on current timestamp,this is 
used for testing.
INSERT INTO ACCOUNT_REWARDS(ID, FIRST_NAME, LAST_NAME, ACCOUNT_ID, 
PURCHASE_PRICE, CURRENT_REWARDS, LAST_UPDATE_DATE) VALUES(111, 'abc', 'xyz', 'SP1ed9a711-4017-4960-917a-e60a473bd26f', 200.25, 80, '2022-08-27 08:01:00');
INSERT INTO ACCOUNT_REWARDS(ID, FIRST_NAME, LAST_NAME, ACCOUNT_ID, 
PURCHASE_PRICE, CURRENT_REWARDS, LAST_UPDATE_DATE) VALUES(222, 'abc', 'xyz', 'SP1ed9a711-4017-4960-917a-e60a473bd26f', 100.25, 81, '2022-09-27 08:01:00');


Steps for running the test cases
1. Right click on RewardsApplicationTests (com.asignment.rewards) in the left hand pane section src/test/java to execute Tests.
