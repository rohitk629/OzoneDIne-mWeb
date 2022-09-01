package stepDefinitions;

import java.io.IOException;

import cucumber.api.java.en.Given;
import pages.LandingScreen;
import utilities.CommonUtility;
import utilities.StaticDataProvider;

public class LandingSteps {

	LandingScreen landingScreen = new LandingScreen();

	CommonUtility commonUtility = new CommonUtility();

	@Given("I open the Onedine application in the mobile browser")
	public void i_open_the_Onedine_application_in_the_mobile_browser() {
		landingScreen.verifyWelcomeMessage();
	}

	@Given("I change the font size of {string} with the scale factor of {string}")
	public void i_change_the_font_size_of_with_the_scale_factor_of(String osName, String scaleFactor) {
		commonUtility.changeFontOfDevice(osName, scaleFactor);
	}

	@Given("I verify that QR code scanner and Spot Code fields are displayed")
	public void i_verify_that_QR_code_scanner_and_Spot_Code_fields_are_displayed() {
		landingScreen.verifyQRScannerDisplayed();
	}

	@Given("I enter the Spot Code from the excel sheet {string} in the Spot Code field")
	public void i_enter_the_Spot_Code_from_the_excel_sheet_in_the_Spot_Code_field(String sheetName) throws IOException {
		// To get the data from excel sheet
		String[][] testData = StaticDataProvider.getExcelData(sheetName);
		// To populate the spot code field
		landingScreen.populateLandingScreenFields(testData);
	}

	@Given("I enter the spot code as {string} in the seven digit spot code field")
	public void i_enter_the_spot_code_as_in_the_seven_digit_spot_code_field(String spot_Code) {
		landingScreen.enter7digitCode(spot_Code);
	}


	@Given("I tap on submit button")
	public void i_tap_on_submit_button() {
		// Tap on Next arrow mark button
		landingScreen.tapNextButton();
	}

	@Given("I verify that it navigate to check-in screen and Sign in button is displayed")
	public void i_verify_that_it_navigate_to_check_in_screen_and_Sign_in_button_is_displayed() {
		landingScreen.isSignInButtonDisplayed();
	}

	@Given("I verify that the error message\\(s) are displayed")
	public void i_verify_that_the_error_message_s_are_displayed() {
		landingScreen.verifyScreenErrorMessage();
	}

}