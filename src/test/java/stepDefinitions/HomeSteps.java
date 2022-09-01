package stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomeScreen;

public class HomeSteps {

	HomeScreen homeScreen = new HomeScreen();

	@When("I tap on Click to Pay button")
	public void i_tap_on_Click_to_Pay_button() {
	    // Write code here that turns the phrase above into concrete actions
		homeScreen.tapOnClickToPay();
	}
	
	@When("I tap on Pay Remaining Balance button")
	public void i_tap_on_Pay_Remaining_Balance_button() {
	    // Write code here that turns the phrase above into concrete actions
		homeScreen.tapPayBalance();
	}
	
	@When("I tap on Full Check button")
	public void i_tap_on_Full_Check_button() {
	    // Write code here that turns the phrase above into concrete actions
		homeScreen.tapPayBalance();
	}
	
	@When("I tap on Split Evenly button")
	public void i_tap_on_Split_Evenly_button() {
	    // Write code here that turns the phrase above into concrete actions
		homeScreen.tapSplitEvenlyButton();
	}

	@Then("I verify if it navigates to Split Evenly screen")
	public void i_verify_if_it_navigates_to_Split_Evenly_screen() {
	    // Write code here that turns the phrase above into concrete actions
		homeScreen.verifySplitEvenlyScreen();
	}

	@Then("I split the check by {int}")
	public void i_split_the_check_by(Integer num) {
	    // Write code here that turns the phrase above into concrete actions
		homeScreen.splitCheckBy(num);
	}

	@Then("I tap on the Continue button")
	public void i_tap_on_the_Continue_button() {
	    // Write code here that turns the phrase above into concrete actions
		homeScreen.tapOnContinueButton();
	}

	
}
