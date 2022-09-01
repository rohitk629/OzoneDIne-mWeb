package stepDefinitions;

import cucumber.api.java.en.Then;
import pages.GuestSpotScreen;

public class GuestSpotSteps {
	
	GuestSpotScreen guestSpotScreen = new GuestSpotScreen();
	
	@Then("I should be able to navigate to Guest Spot screen")
	public void i_should_be_able_to_navigate_to_Guest_Spot_screen() {
	    // Write code here that turns the phrase above into concrete actions
		guestSpotScreen.verifyGuestSpotScreen();
	}

	@Then("I enter the guest name as {string}")
	public void i_enter_the_guest_name_as(String guestName) {
	    // Write code here that turns the phrase above into concrete actions
		guestSpotScreen.populateGuestSpotScreenFields(guestName);
	}

}


