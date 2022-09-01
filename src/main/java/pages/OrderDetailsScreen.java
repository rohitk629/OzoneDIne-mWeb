package pages;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;
import utilities.CommonUtility;
import utilities.WebForm;

public class OrderDetailsScreen extends TestBase {

	TestBase base = new TestBase();

	CommonUtility commonUtility = new CommonUtility();

	WebForm webForm = new WebForm();

	final String selectedTab = "//div[@class='text-primary od-text-primary tab-icon']/div";

	final String selectedCategory = "//button[@type='button'][contains(@class, 'orders_selectedCategory')]";

//	Changed XPATH
	final String menu_Item = "//div[text()='temp']/ancestor::div[2]";

	final String orderMenu = "//div[text()='temp']/ancestor::div[1]/descendant::button[@type='button']";

	String categoryToClick = "//button[text()='temp']";

	String modifier_Xpath = "//div[text()='temp']";

	final String orderTab_Xpath = "//div[text()='temp']";

	final String menuTab_Xpath = "//div[contains(@class, 'tab-icon')]/div[text()='temp']";

	final String addToBag = "//button[text()='Add to Bag']";

	final String menuItemName = "//div[@id='itemName']";

	String storedMenuItem = "";

	public void verifyActiveScreen(String expectedTabName) {
		if (base.getElement(XPATH, selectedTab) != null) {
			String actual_SelectedTab = base.gettext(selectedTab);
			Assert.assertEquals(actual_SelectedTab.trim(), expectedTabName.trim(),
					"Selected tab name do not matche with the expected tab name");
		} else {
			Assert.assertNull(base.getElement(XPATH, selectedTab), "Tab is not selected");
		}
	}

	public void verifySelectedCategory(String expectedCategoryName) {

		if (base.getElement(XPATH, selectedCategory) != null) {
			String actual_SelectedCategory = base.gettext(selectedCategory);
			if (!actual_SelectedCategory.toLowerCase().trim()
					.equalsIgnoreCase(expectedCategoryName.toLowerCase().trim())) {
				categoryToClick = categoryToClick.replace("temp", expectedCategoryName);
				base.tapElementUsingJS(categoryToClick);
				actual_SelectedCategory = base.gettext(selectedCategory);
				Assert.assertEquals(actual_SelectedCategory.toLowerCase().trim(),
						expectedCategoryName.toLowerCase().trim(),
						"Selected Category name matches with the expected Category name");
			}
			Assert.assertEquals(actual_SelectedCategory.toLowerCase().trim(), expectedCategoryName.toLowerCase().trim(),
					"Selected Category name matches with the expected Category name");
		} else {
			Assert.assertNull(base.getElement(XPATH, selectedCategory), "Category is not selected");
		}
	}

	public void scrollDownToMenuItem(String menuItem) {

		storedMenuItem = menuItem;
		String menuItemToBeClicked = menu_Item.replace("temp", menuItem);
		if (base.getElement(XPATH, menuItemToBeClicked) != null) {
			base.delay(1000L);
			base.scrollToElementUsingJS(menuItemToBeClicked);
			base.delay(1000L);
			base.tapElementUsingJS(menuItemToBeClicked);
		} else {
			Assert.assertNull(base.getElement(XPATH, menuItemToBeClicked), menuItem + " item is not available");
		}

	}

	public void tapOrderMenuButton(String input) {

		String order_Menu = orderMenu.replace("temp", input);

		if (base.getElement(XPATH, order_Menu) != null) {
			base.tapElementUsingJS(order_Menu);
		} else {
			Assert.assertNull(base.getElement(XPATH, order_Menu), "Order Menu button is not available");
		}

	}

	public void verifyMenuDetailsScreen() {

		if (base.getElement(XPATH, menuItemName) != null) {
			if (base.isDisplayed(menuItemName)) {
				String actualMenuItemName = base.gettext(menuItemName);
				Assert.assertEquals(storedMenuItem.toLowerCase().trim(), actualMenuItemName.toLowerCase().trim(),
						"Actual and Expected Menu item names do not match");
			} else {
				Assert.assertFalse(base.isDisplayed(menuItemName), "Menu item name is not displayed");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, menuItemName), "Menu item details are not available");
		}
	}

	public void selectRequiredModifiers(String modifier1, String modifier2, String modifier3) {

		String modifier1_Xpath = modifier_Xpath.replace("temp", modifier1);
		String modifier2_Xpath = modifier_Xpath.replace("temp", modifier2);

		String modifier3_Xpath = modifier_Xpath.replace("temp", modifier3);

		if (base.getElement(XPATH, modifier1_Xpath) != null && base.isDisplayed(modifier1_Xpath)) {
			base.delay(1000L);
			base.tapElementUsingJS(modifier1_Xpath);
			base.delay(1000L);
			if (base.getElement(XPATH, modifier2_Xpath) != null) {
				base.scrollToElementUsingJS(modifier1_Xpath
						+ "/ancestor::div/div[contains(@class, 'modifiers_modifierNameContainer')]/descendant::span[contains(text(),'Pick')]");
				base.tapElementUsingJS(modifier2_Xpath);
				base.delay(1000L);
				if (base.getElement(XPATH, modifier3_Xpath) != null) {
					base.scrollToElementUsingJS(modifier2_Xpath
							+ "/ancestor::div/div[contains(@class, 'modifiers_modifierNameContainer')]/descendant::span[contains(text(),'Pick')]");
					base.tapElementUsingJS(modifier3_Xpath);
					base.delay(1000L);
				} else {
					Assert.fail(modifier3_Xpath + " element is not found");
				}
			} else {
				Assert.fail(modifier2_Xpath + " element is not found");
			}
		} else {
			Assert.fail(modifier1_Xpath + " element is not found");
		}
	}

	public void tapAddToBagButton() {

		if (base.getElement(XPATH, addToBag) != null) {
			if (base.isDisplayed(addToBag)) {
				base.tapElementUsingJS(addToBag);
				wait = new WebDriverWait(this.driver, 5);
			} else {
				Assert.assertFalse(base.isDisplayed(addToBag), "Not able to click Add to Bag button");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, addToBag), "Add to Bag button is not available");
		}

	}
	
	
	// Tap on bottom menu tab
	public void tapOnRequiredTab(String tabToClick) {
		// TODO Auto-generated method stub

		String bottomMenu_Card = menuTab_Xpath.replace("temp", tabToClick);

		if (base.getElement(XPATH, bottomMenu_Card) != null) {
			if (base.isDisplayed(bottomMenu_Card)) {
				base.tapElementUsingJS(bottomMenu_Card);
			} else {
				Assert.assertFalse(base.isDisplayed(bottomMenu_Card), "Not able to click " + tabToClick + " tab");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, bottomMenu_Card), tabToClick + " Tab is not available");
		}
		
	}

	public void selectRequiredMenu(String selectMenuTab) {
		// TODO Auto-generated method stub
		String orderMenu_Card = orderTab_Xpath.replace("temp", selectMenuTab);

		if (base.getElement(XPATH, orderMenu_Card) != null) {
			if (base.isDisplayed(orderMenu_Card)) {
				base.tapElementUsingJS(orderMenu_Card);
			} else {
				Assert.assertFalse(base.isDisplayed(orderMenu_Card), "Not able to click " + selectMenuTab + " tab");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, orderMenu_Card), selectMenuTab + " Tab is not available");
		}
	}




}
