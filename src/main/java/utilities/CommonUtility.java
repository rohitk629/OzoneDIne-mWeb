package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;

import base.TestBase;

public class CommonUtility extends TestBase {

	TestBase base = new TestBase();

	public static String checkInPlace;

	final String iOS_Settings_BundleID = "com.apple.Preferences";

	final String iOS_Safari_BundleID = "com.apple.mobilesafari";

	final String iOS_Settings_General = "//*[@name='General'][@value='General']";

	final String iOS_Settings_Accessibility = "//*[@name='Accessibility'][@value='Accessibility']";

	final String iOS_Settings_LargeText = "//*[@name='Larger Text'][@value='Larger Text']";

	final String iOS_Settings_SwitchLargeAccessibility = "//*[@name='Larger Accessibility Sizes'][2]";

	String iOS_Settings_SwitchLargeAccessibility_ON = "//*[@name='Larger Accessibility Sizes'][2][@value='temp']";

	final String changeFontTo = "//*[@name='Font size']/XCUIElementTypeSlider";

	public void changeFontOfDevice(String osName, String scaleFactor) {
		// TODO Auto-generated method stub
		if (osName.equalsIgnoreCase("iOS".toLowerCase())) {
			base.activateApp(iOS_Settings_BundleID);
			base.setContext("NATIVE");
			if (base.isDisplayed(iOS_Settings_General)) {
				base.tapElement(iOS_Settings_General);
				if (base.isDisplayed(iOS_Settings_Accessibility)) {
					base.tapElement(iOS_Settings_Accessibility);
					if (base.isDisplayed(iOS_Settings_LargeText)) {
						base.tapElement(iOS_Settings_LargeText);
						if (base.isDisplayed(iOS_Settings_SwitchLargeAccessibility)) {
							String text = base.gettext(iOS_Settings_SwitchLargeAccessibility, "value");
							if (text.equals("0")) {
								iOS_Settings_SwitchLargeAccessibility_ON = iOS_Settings_SwitchLargeAccessibility_ON
										.replace("temp", text);
								base.tapElement(iOS_Settings_SwitchLargeAccessibility_ON);
							}
							driver.findElement(By.xpath(changeFontTo)).sendKeys(scaleFactor);
						}
					}
				}
				base.activateApp(iOS_Safari_BundleID);
				base.setContext("WEBVIEW");
			}
		}
	}

	/**
	 * get coloumns from excel which has data
	 * 
	 * @param data
	 * @param FEILDS
	 * @return
	 */
	public List<String> getFinalFeilds(String[] formData, String[] FIELDS) {
		List<String> formFeild = new ArrayList<>();
		for (int i = 0; i <= formData.length - 1; i++) {
			if (formData[i] != "") {
				formFeild.add(FIELDS[i]);
			}
		}
		return formFeild;
	}

	/**
	 * get coloumns from excel which has data
	 * 
	 * @param data
	 * @param FIELDS
	 * @return
	 */
	public List<String> getFinalData(String[] formData, String[] FIELDS) {
		List<String> data = new ArrayList<>();
		for (int i = 0; i <= formData.length - 1; i++) {
			if (formData[i] != "") {
				data.add(formData[i]);
			}
		}
		return data;
	}

	public String[] flatten(String[][] data) {
		ArrayList<String> list = new ArrayList<String>();

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				list.add(data[i][j]);
			}
		}

		return list.toArray(new String[0]);
	}

}
