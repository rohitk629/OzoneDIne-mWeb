Feature: One Dine - mWeb Application Regression Scenarios
	As a Registered user, I want to test regression sceanrios of the application
  
  	
  	@RegressionTest
	Scenario: Curbside flow type-3 - Pay Now 
		Given I open the Onedine application in the mobile browser 
		And I enter the spot code as 'PAI11CU' in the seven digit spot code field
		And I tap on submit button 
		And I verify that it navigate to check-in screen and Sign in button is displayed 
		Then I tap on Sign In button 
		And I navigate to the Sign In screen 
		And I tap on the link Sign in with a password 
		And I enter valid login credentials from the excel sheet name "Login_Success" 
		And I tap on Sign In button present in Login Screen 
		Then I navigate to spot number screen and verify the message displayed on the screen 
		And I enter the Spot Number from the excel sheet "SpotNo_Success" in the Spot Number field 
		And I tap on next arrow button and submit the spot number
		Then I should be able to navigate to 'Order' screen 
		And I tap on 'Entrees' tab and verify the page header title
		And I scroll down the page till 'Cheese ravioli with marinara and chicken' menu and select the menu 
		And I tap on order button with 'Cheese ravioli with marinara and chicken' item to add it to the menu 
		Then I should be able to navigate to menu details screen 
		And I select 'Fettuccini' , 'Sausage' and 'Alfredo' as the required modifiers 
		And I tap on Add to Bag button 
		And I tap on 'My Bag' from bottom footer 
		Then I should be able to navigate to 'My Bag' screen 
		When I click on the next buttton 
		Then I should be able to navigate to checkout screen 
		And I verify that added menu is being displayed in the screen 
		And I tap on Pay Now button 
		Then I should be able to navigate to Payment screen of the application 
		And I enter the valid card details into the fields from the excel sheet name "Payment_Success"
		And I select '15%' as a tip amount 
		And I tap on Pay Now button available on payment screen 
		And I tap on Confirm button to proceed the payment 
		Then I am on payment confirmation screen 
		
	@RegressionTest
	Scenario: Curbside flow type-3 - Pay Later
		Given I open the Onedine application in the mobile browser 
		And I enter the spot code as 'PAI11CU' in the seven digit spot code field
		And I tap on submit button 
		And I verify that it navigate to check-in screen and Sign in button is displayed 
		Then I tap on Sign In button 
		And I navigate to the Sign In screen 
		And I tap on the link Sign in with a password 
		And I enter valid login credentials from the excel sheet name "Login_Success" 
		And I tap on Sign In button present in Login Screen 
		Then I navigate to spot number screen and verify the message displayed on the screen 
		And I enter the Spot Number from the excel sheet "SpotNo_Success" in the Spot Number field 
		And I tap on next arrow button and submit the spot number
		Then I should be able to navigate to 'Order' screen 
		And I tap on 'Entrees' tab and verify the page header title 
		And I scroll down the page till 'Cheese ravioli with marinara and chicken' menu and select the menu 
		And I tap on order button with 'Cheese ravioli with marinara and chicken' item to add it to the menu 
		Then I should be able to navigate to menu details screen 
		And I select 'Fettuccini' , 'Sausage' and 'Alfredo' as the required modifiers 
		And I tap on Add to Bag button 
		And I tap on 'My Bag' from bottom footer 
		Then I should be able to navigate to 'My Bag' screen 
		When I click on the next buttton 
		Then I should be able to navigate to checkout screen 
		And I verify that added menu is being displayed in the screen 
		And I tap on Pay at the Restaurant button available on the Payment screen 
		And I tap on Order Now button 
		And I tap on Confirm button to proceed the payment 
		Then I am on payment confirmation screen
		
	@RegressionTest
	Scenario: Online Pickup - Pay Now
		Given I open the Onedine application in the mobile browser 
		And I enter the spot code as 'PAI11OL' in the seven digit spot code field
		And I tap on submit button 
		And I verify that it navigate to check-in screen and Sign in button is displayed 
		Then I tap on Sign In button 
		And I navigate to the Sign In screen 
		And I tap on the link Sign in with a password 
		And I enter valid login credentials from the excel sheet name "Login_Success" 
		And I tap on Sign In button present in Login Screen
		Then I should be able to navigate to Pickup screen
		And I enter the Name as 'John Doe' , select Pickup Date as 'Tomorrow' and select Pickup time as '11:45 PM'
		When I submit the Next button with all the mandatory fields
		Then I should be able to navigate to 'Order' screen
		And I tap on 'Entrees' tab and verify the page header title 
		And I scroll down the page till 'Cheese ravioli with marinara and chicken' menu and select the menu 
		And I tap on order button with 'Cheese ravioli with marinara and chicken' item to add it to the menu 
		Then I should be able to navigate to menu details screen 
		And I select 'Fettuccini' , 'Sausage' and 'Alfredo' as the required modifiers 
		And I tap on Add to Bag button 
		And I tap on 'My Bag' from bottom footer 
		Then I should be able to navigate to 'My Bag' screen 
		When I click on the next buttton
		Then I should be able to navigate to checkout screen 
		And I verify that added menu is being displayed in the screen 
		And I tap on Pay Now button 
		Then I should be able to navigate to Payment screen of the application 
		And I enter the valid card details into the fields from the excel sheet name "Payment_Success"
		And I select '15%' as a tip amount
		And I tap on Pay Now button available on payment screen 
		And I tap on Confirm button to proceed the payment 
		Then I am on payment confirmation screen 
	
	@RegressionTest
	Scenario: Online Pickup - Pay Later
		Given I open the Onedine application in the mobile browser 
		And I enter the spot code as 'PAI11OL' in the seven digit spot code field
		And I tap on submit button 
		And I verify that it navigate to check-in screen and Sign in button is displayed 
		Then I tap on Sign In button 
		And I navigate to the Sign In screen 
		And I tap on the link Sign in with a password 
		And I enter valid login credentials from the excel sheet name "Login_Success" 
		And I tap on Sign In button present in Login Screen
		Then I should be able to navigate to Pickup screen
		And I enter the Name as 'John Doe' , select Pickup Date as 'Tomorrow' and select Pickup time as '11:45 PM'
		When I submit the Next button with all the mandatory fields
		Then I should be able to navigate to 'Order' screen
		And I tap on 'Entrees' tab and verify the page header title 
		And I scroll down the page till 'Cheese ravioli with marinara and chicken' menu and select the menu 
		And I tap on order button with 'Cheese ravioli with marinara and chicken' item to add it to the menu 
		Then I should be able to navigate to menu details screen 
		And I select 'Fettuccini' , 'Sausage' and 'Alfredo' as the required modifiers 
		And I tap on Add to Bag button 
		And I tap on 'My Bag' from bottom footer 
		Then I should be able to navigate to 'My Bag' screen 
		When I click on the next buttton 
		Then I should be able to navigate to checkout screen 
		And I verify that added menu is being displayed in the screen  
		And I tap on Pay at the Restaurant button available on the Payment screen 
		And I tap on Order Now button 
		And I tap on Confirm button to proceed the payment 
		Then I am on payment confirmation screen
		
	@RegressionTest
	Scenario Outline: Online Delivery - Pay Later
		Given I open the Onedine application in the mobile browser 
		And I enter the spot code as 'PAI11OL' in the seven digit spot code field
		And I tap on submit button 
		And I verify that it navigate to check-in screen and Sign in button is displayed 
		Then I tap on Sign In button 
		And I navigate to the Sign In screen 
		And I tap on the link Sign in with a password 
		And I enter valid login credentials from the excel sheet name "Login_Success" 
		And I tap on Sign In button present in Login Screen
		Then I should be able to navigate to Pickup screen
		And I tap on the Delivery tab
		And I enter or select the Name as <Guest Name> , Delivery Date as <Delivery Date> , Delivery Time as <Delivery Time> , Address as <Address> , City as <City> , State as <State>, Zip as <Zip Code>
		When I submit the Next button with all the mandatory fields
		Then I should be able to navigate to 'Order' screen
		And I tap on 'Entrees' tab and verify the page header title 
		And I scroll down the page till 'Cheese ravioli with marinara and chicken' menu and select the menu 
		And I tap on order button with 'Cheese ravioli with marinara and chicken' item to add it to the menu 
		Then I should be able to navigate to menu details screen 
		And I select 'Fettuccini' , 'Sausage' and 'Alfredo' as the required modifiers 
		And I tap on Add to Bag button 
		And I tap on 'My Bag' from bottom footer 
		Then I should be able to navigate to 'My Bag' screen
		When I click on the next buttton 
		Then I should be able to navigate to checkout screen 
		And I verify that added menu is being displayed in the screen  
		And I tap on Pay at the Restaurant button available on the Payment screen 
		And I tap on Order Now button 
		And I tap on Confirm button to proceed the payment 
		Then I am on payment confirmation screen
	
  Examples: 
    |	Guest Name	|	Delivery Date	|	Delivery Time	|	Address					|	City		|	State		|	Zip Code	|
    |	'John Doe'	|	'Tomorrow'		|	'11:45 PM'		|	'221 B, Baker Street'	|	'London'	|	'LA'		|	'85284'		|
    
   
  @RegressionTest
  Scenario Outline: Online Delivery - Pay Now
		Given I open the Onedine application in the mobile browser 
		And I enter the spot code as 'PAI11OL' in the seven digit spot code field
		And I tap on submit button 
		And I verify that it navigate to check-in screen and Sign in button is displayed 
		Then I tap on Sign In button 
		And I navigate to the Sign In screen 
		And I tap on the link Sign in with a password 
		And I enter valid login credentials from the excel sheet name "Login_Success" 
		And I tap on Sign In button present in Login Screen
		Then I should be able to navigate to Pickup screen
		And I tap on the Delivery tab
		And I enter or select the Name as <Guest Name> , Delivery Date as <Delivery Date> , Delivery Time as <Delivery Time> , Address as <Address> , City as <City> , State as <State>, Zip as <Zip Code>
		When I submit the Next button with all the mandatory fields
		Then I should be able to navigate to 'Order' screen
		And I tap on 'Entrees' tab and verify the page header title
		And I scroll down the page till 'Cheese ravioli with marinara and chicken' menu and select the menu 
		And I tap on order button with 'Cheese ravioli with marinara and chicken' item to add it to the menu 
		Then I should be able to navigate to menu details screen 
		And I select 'Fettuccini' , 'Sausage' and 'Alfredo' as the required modifiers 
		And I tap on Add to Bag button 
		And I tap on 'My Bag' from bottom footer 
		Then I should be able to navigate to 'My Bag' screen 
		When I click on the next buttton
		Then I should be able to navigate to checkout screen 
		And I verify that added menu is being displayed in the screen 
		And I tap on Pay Now button 
		Then I should be able to navigate to Payment screen of the application 
		And I enter the valid card details into the fields from the excel sheet name "Payment_Success"
		And I select '15%' as a tip amount
		And I tap on Pay Now button available on payment screen 
		And I tap on Confirm button to proceed the payment 
		Then I am on payment confirmation screen 
	
  Examples: 
    |	Guest Name	|	Delivery Date	|	Delivery Time	|	Address					|	City		|	State		|	Zip Code	|
    |	'John Doe'	|	'Tomorrow'		|	'11:45 PM'		|	'221 B, Baker Street'	|	'London'	|	'LA'		|	'85284'		|
  	
  	
  @RegressionTest
  Scenario: Walkup: flow - Pay Later
		Given I open the Onedine application in the mobile browser 
		And I enter the spot code as 'PAI11WU' in the seven digit spot code field
		And I tap on submit button 
		And I verify that it navigate to check-in screen and Sign in button is displayed 
		Then I tap on Sign In button 
		And I navigate to the Sign In screen 
		And I tap on the link Sign in with a password 
		And I enter valid login credentials from the excel sheet name "Login_Success" 
		And I tap on Sign In button present in Login Screen
		Then I should be able to navigate to Guest Spot screen
		And I enter the guest name as "John Doe"
		When I submit the Next button with all the mandatory fields
		Then I should be able to navigate to 'Order' screen 
		And I tap on 'Entrees' tab and verify the page header title
		And I scroll down the page till 'Cheese ravioli with marinara and chicken' menu and select the menu 
		And I tap on order button with 'Cheese ravioli with marinara and chicken' item to add it to the menu 
		Then I should be able to navigate to menu details screen 
		And I select 'Fettuccini' , 'Sausage' and 'Alfredo' as the required modifiers 
		And I tap on Add to Bag button 
		And I tap on 'My Bag' from bottom footer 
		Then I should be able to navigate to 'My Bag' screen
		When I click on the next buttton 
		Then I should be able to navigate to checkout screen 
		And I verify that added menu is being displayed in the screen  
		And I tap on Pay at the Restaurant button available on the Payment screen 
		And I tap on Order Now button 
		And I tap on Confirm button to proceed the payment 
		Then I am on payment confirmation screen
		
  @RegressionTest
  Scenario: Walkup flow - Pay Now with coupon
		Given I open the Onedine application in the mobile browser 
		And I enter the spot code as 'PAI11WU' in the seven digit spot code field
		And I tap on submit button 
		And I verify that it navigate to check-in screen and Sign in button is displayed 
		Then I tap on Sign In button 
		And I navigate to the Sign In screen 
		And I tap on the link Sign in with a password 
		And I enter valid login credentials from the excel sheet name "Login_Success" 
		And I tap on Sign In button present in Login Screen
		Then I should be able to navigate to Guest Spot screen
		And I enter the guest name as "John Doe"
		When I submit the Next button with all the mandatory fields
		Then I should be able to navigate to 'Order' screen
		And I tap on 'Entrees' tab and verify the page header title
		And I scroll down the page till 'Cheese ravioli with marinara and chicken' menu and select the menu 
		And I tap on order button with 'Cheese ravioli with marinara and chicken' item to add it to the menu 
		Then I should be able to navigate to menu details screen 
		And I select 'Fettuccini' , 'Sausage' and 'Alfredo' as the required modifiers 
		And I tap on Add to Bag button 
		And I tap on 'My Bag' from bottom footer 
		Then I should be able to navigate to 'My Bag' screen
		When I click on the next buttton
		Then I should be able to navigate to checkout screen 
		And I verify that added menu is being displayed in the screen 
		And I tap on Pay Now button 
		Then I should be able to navigate to Payment screen of the application
		And I enter the AVE Coupon number as 'yelp27-mx'
		Then I tap on apply coupon button
		And I enter the valid card details into the fields from the excel sheet name "Payment_Success"
		And I select '15%' as a tip amount 
		And I tap on Pay Now button available on payment screen 
		And I tap on Confirm button to proceed the payment 
		Then I am on payment confirmation screen


  @RegressionTest
  @Ignored
  Scenario: Table Payment - Split Evenly and Pay
		Given I open the Onedine application in the mobile browser 
		And I enter the spot code as 'aloha51' in the seven digit spot code field
		And I tap on submit button 
		And I verify that it navigate to check-in screen and Sign in button is displayed 
		Then I tap on Sign In button 
		And I navigate to the Sign In screen 
		And I tap on the link Sign in with a password 
		And I enter valid login credentials from the excel sheet name "Login_Success" 
		And I tap on Sign In button present in Login Screen
		Then I should be able to navigate to 'Home' screen
		And I tap on 'Order' from bottom footer 
		Then I should be able to navigate to 'Order' screen 
		And I verify that selected category is 'Sandwiches'
		And I scroll down the page till 'Billionaire Bacon Burger' menu and select the menu 
		And I tap on order button with 'Billionaire Bacon Burger' item to add it to the menu 
		Then I should be able to navigate to menu details screen 
		And I select 'Sub Beyond' , 'Sub Gouda - Cheddar' and 'No BBQ - Sand' as the required modifiers 
		And I tap on Add to Bag button 
		And I tap on 'My Bag' from bottom footer 
		Then I should be able to navigate to 'My Bag' screen
		When I click on the next buttton
		Then I should be able to navigate to checkout screen 
		And I verify that added menu is being displayed in the screen 
		And I tap on Order Now button
		Then I enter 'John Doe' as a name for this order and submit the yes button
		Then I am on payment confirmation screen
		When I click on the next buttton
		Then I should be able to navigate to 'Home' screen
		When I tap on Click to Pay button
		And I tap on Split Evenly button
		Then I verify if it navigates to Split Evenly screen
		Then I split the check by 4
		And I tap on the Continue button
		Then I should be able to navigate to Payment screen of the application
		And I enter the valid card details into the fields from the excel sheet name "Payment_Success"
		And I scroll down the page till 'Add a tip' section
		And I select '17%' as a tip amount
		And I tap on Pay Now button available on payment screen 
		And I tap on Confirm button to proceed the payment 
		Then I am on payment confirmation screen
		
			 
  @RegressionTest
  Scenario: Table Payment - Pay Remaining Balances
		Given I open the Onedine application in the mobile browser 
		And I enter the spot code as 'aloha51' in the seven digit spot code field
		And I tap on submit button 
		And I verify that it navigate to check-in screen and Sign in button is displayed 
		Then I tap on Sign In button 
		And I navigate to the Sign In screen 
		And I tap on the link Sign in with a password 
		And I enter valid login credentials from the excel sheet name "Login_Success" 
		And I tap on Sign In button present in Login Screen
		Then I should be able to navigate to 'Home' screen
		And I tap on 'Order' from bottom footer 
		Then I should be able to navigate to 'Order' screen 
		And I verify that selected category is 'Sandwiches'
		And I scroll down the page till 'Billionaire Bacon Burger' menu and select the menu 
		And I tap on order button with 'Billionaire Bacon Burger' item to add it to the menu 
		Then I should be able to navigate to menu details screen 
		And I select 'Sub Beyond' , 'Sub Gouda - Cheddar' and 'No BBQ - Sand' as the required modifiers 
		And I tap on Add to Bag button 
		And I tap on 'My Bag' from bottom footer 
		Then I should be able to navigate to 'My Bag' screen
		When I click on the next buttton
		Then I should be able to navigate to checkout screen 
		And I verify that added menu is being displayed in the screen 
		And I tap on Order Now button
		Then I enter 'John Doe' as a name for this order and submit the yes button
		Then I am on payment confirmation screen
		When I click on the next buttton
		Then I should be able to navigate to 'Home' screen
		When I tap on Click to Pay button
		And I tap on Pay Remaining Balance button
		Then I should be able to navigate to Payment screen of the application
		And I enter the valid card details into the fields from the excel sheet name "Payment_Success"
		And I scroll down the page till 'Add a tip' section
		And I select '17%' as a tip amount
		And I tap on Pay Now button available on payment screen 
		And I tap on Confirm button to proceed the payment 
		Then I am on payment confirmation screen
		
  @RegressionTest
  Scenario: Table Payment - Pay Full check or Remaining Balances
		Given I open the Onedine application in the mobile browser 
		And I enter the spot code as 'aloha51' in the seven digit spot code field
		And I tap on submit button 
		And I verify that it navigate to check-in screen and Sign in button is displayed 
		Then I tap on Sign In button 
		And I navigate to the Sign In screen 
		And I tap on the link Sign in with a password 
		And I enter valid login credentials from the excel sheet name "Login_Success" 
		And I tap on Sign In button present in Login Screen
		Then I should be able to navigate to 'Home' screen
		And I tap on 'Order' from bottom footer 
		Then I should be able to navigate to 'Order' screen 
		And I verify that selected category is 'Sandwiches'
		And I scroll down the page till 'Billionaire Bacon Burger' menu and select the menu 
		And I tap on order button with 'Billionaire Bacon Burger' item to add it to the menu 
		Then I should be able to navigate to menu details screen 
		And I select 'Sub Beyond' , 'Sub Gouda - Cheddar' and 'No BBQ - Sand' as the required modifiers 
		And I tap on Add to Bag button 
		And I tap on 'My Bag' from bottom footer 
		Then I should be able to navigate to 'My Bag' screen
		When I click on the next buttton
		Then I should be able to navigate to checkout screen 
		And I verify that added menu is being displayed in the screen 
		And I tap on Order Now button
		Then I enter 'John Doe' as a name for this order and submit the yes button
		Then I am on payment confirmation screen
		When I click on the next buttton
		Then I should be able to navigate to 'Home' screen
		When I tap on Click to Pay button
		And I tap on Full Check button
		Then I should be able to navigate to Payment screen of the application
		And I enter the valid card details into the fields from the excel sheet name "Payment_Success" 
		And I scroll down the page till 'Add a tip' section
		And I select '17%' as a tip amount
		And I tap on Pay Now button available on payment screen 
		And I tap on Confirm button to proceed the payment 
		Then I am on payment confirmation screen
		
  @RegressionTest
  Scenario Outline: USAE-Pay: Pay Now to make a successful order
		Given I open the Onedine application in the mobile browser 
		And I enter the spot code as 'PAI23WU' in the seven digit spot code field
		And I tap on submit button 
		And I verify that it navigate to check-in screen and Sign in button is displayed 
		Then I tap on Sign In button 
		And I navigate to the Sign In screen 
		And I tap on the link Sign in with a password 
		And I enter valid login credentials from the excel sheet name "Login_Success" 
		And I tap on Sign In button present in Login Screen
		Then I should be able to navigate to Guest Spot screen
		And I enter the guest name as "John Doe"
		When I submit the Next button with all the mandatory fields
		Then I should be able to navigate to 'Order' screen 
		And I verify that selected category is 'Sandwiches' 
		And I scroll down the page till 'Roast beef sandwich' menu and select the menu 
		And I tap on order button with 'Roast beef sandwich' item to add it to the menu 
		Then I should be able to navigate to menu details screen
		And I tap on Add to Bag button 
		And I tap on 'My Bag' from bottom footer
		Then I should be able to navigate to checkout screen 
		And I verify that added menu is being displayed in the screen 
		And I tap on Pay Now button
		And I add the valid card details with <Card Number> , <Expiry Date> , <CVV> , <Full Name> and <Zip Code>
		Then I tap on Add Card button
		And I select '15%' as a tip amount
		And I tap on Pay Now button available on payment screen 
		And I tap on Confirm button to proceed the payment 
		Then I am on payment confirmation screen
		
	Examples:
		|	Card Number			|	Expiry Date	|	CVV		|	Full Name		|	Zip Code	|
		|	'4012881888818888'	|	'12/22'		|	'999'	|	'John Doe'		|	'85284'		|
		
 
  @RegressionTest 
  Scenario Outline: SHift4 Pay: Pay Now to make a successful order
		Given I open the Onedine application in the mobile browser 
		And I enter the spot code as 'PAI22WU' in the seven digit spot code field
		And I tap on submit button 
		And I verify that it navigate to check-in screen and Sign in button is displayed 
		Then I tap on Sign In button 
		And I navigate to the Sign In screen 
		And I tap on the link Sign in with a password 
		And I enter valid login credentials from the excel sheet name "Login_Success" 
		And I tap on Sign In button present in Login Screen
		Then I should be able to navigate to Guest Spot screen
		And I enter the guest name as "John Doe"
		When I submit the Next button with all the mandatory fields
		Then I should be able to navigate to 'Order' screen 
		And I verify that selected category is 'Entrees' 
		And I scroll down the page till 'Firecracker shrimp' menu and select the menu 
		And I tap on order button with 'Firecracker shrimp' item to add it to the menu 
		Then I should be able to navigate to menu details screen
		And I tap on Add to Bag button 
		And I tap on 'My Bag' from bottom footer
		Then I should be able to navigate to checkout screen 
		And I verify that added menu is being displayed in the screen 
		And I tap on Pay Now button
		And I select the card as <Card Type> and enter all other mandatory fields <Card Number> , <Expiration Month> , <Expiration Year> , <CCV> and <Zip Code>
		Then I tap on Add Card button for Shift Pay
		And I select '15%' as a tip amount 
		And I tap on Pay Now button available on payment screen 
		And I tap on Confirm button to proceed the payment 
		Then I am on payment confirmation screen
		
	Examples:
		|	Card Type	|	Card Number				|	Expiration Month			|	Expiration Year	|	CCV		|	Zip Code	|
		|	'Visa'		|	'4012000098765439'		|	'12 - December'				|	'2020'			|	'999'	|	'75019'		|	
	
	
	
	
	
	
# And I verify that selected category is 'Entrees' 
# And I verify that selected category is 'Entrees' 