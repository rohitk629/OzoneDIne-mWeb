package stepDefinitions;

import java.io.IOException;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.LandingScreen;
import pages.LoginScreen;
import utilities.StaticDataProvider;

public class LoginSteps {

	LoginScreen loginScreen = new LoginScreen();

	LandingScreen landingScreen = new LandingScreen();

	@Then("I verify the check-in messages displayed on the check-in screen")
	public void i_verify_the_check_in_messages_displayed_on_the_check_in_screen() {
		loginScreen.verifyCheckInMessages();
	}

	@And("I tap on Cancel check-in link present in the Check-in screen")
	public void clickCancelCheckInLink() {
		loginScreen.tapCancelCheckInLink();
	}

	@And("I get redirected back to Landing screen")
	public void navigateBackToLandingScreen() {
		landingScreen.verifyLandingScreen();
	}

	@Then("I tap on Sign In button")
	public void i_tap_on_Sign_In_button() {
		loginScreen.tapSignInButton();
	}

	@Then("I verify the checked-in place displayed on Login screen is the same to that of check-in screen")
	public void verify_the_checked_in_place_displayed_is_the_same_to_that_of_check_in_screen() {
		loginScreen.verifyCheckedInPlace();
	}

	@Then("I navigate to the Sign In screen")
	public void navigateToSignInScreen() {
		loginScreen.navigateToSignInScreen();
	}

	@Then("I tap on the link Sign in with a password")
	public void i_tap_on_the_link_Sign_in_with_a_password() {
		loginScreen.tapSignInWithPasswordLink();
		}

	@Then("I enter valid login credentials from the excel sheet name {string}")
	public void i_enter_valid_login_credentials_from_the_excel_sheet_name(String sheetName) throws IOException {
		String[][] testData = StaticDataProvider.getExcelData(sheetName);
		loginScreen.populateLoginScreenFields(testData);
	}
	
	@Then("I tap on Sign In button present in Login Screen")
	public void i_tap_on_Sign_In_button_present_in_Login_Screen() {
		loginScreen.tapSignInWButton_LoginScreen();
	}


	@Then("I verify the error message\\(s) displayed on Login Screen")
	public void i_verify_the_error_message_s_displayed_on_Login_Screen() {
	    // Write code here that turns the phrase above into concrete actions 
		loginScreen.verifyScreenErrorMessage();
	}

}
