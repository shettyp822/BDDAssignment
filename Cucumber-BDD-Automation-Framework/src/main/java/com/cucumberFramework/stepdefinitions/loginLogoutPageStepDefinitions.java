package com.cucumberFramework.stepdefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cucumberFramework.helper.WaitHelper;
import com.cucumberFramework.pageObjects.LoginLogoutPage;
import com.cucumberFramework.testBase.TestBase;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;

import static com.cucumberFramework.helper.Excel.getExcelValue;
import static com.cucumberFramework.helper.Excel.readExcel;	
import com.cucumberFramework.Resources.ReadPropertyFile;

public class loginLogoutPageStepDefinitions extends TestBase {

	public  static HashMap<String,String> ExcelData;										 
	LoginLogoutPage loginPage = new LoginLogoutPage(driver);
	WaitHelper waitHelper = new WaitHelper(driver);
	WebDriverWait waitElement = new WebDriverWait(driver , 30);
	ReadPropertyFile prop = new ReadPropertyFile();
	
	@Given("^user is on Login page URL \"([^\"]*)\"$")
    public void user_in_on_login_page_url_something(String arg1) throws Throwable {
		driver.get(prop.getURL(arg1));
		driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
	}
		
	@Given("^user is on the Login page URL \"([^\"]*)\"$")
    public void user_is_on_the_login_page_url_something(String arg1) throws Throwable {
		int row = Integer.parseInt(arg1.substring(3));
		readExcel(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata","TestData.xlsx","TestData",row);
		driver.get(getExcelValue("url"));
		waitHelper = new WaitHelper(driver);												   																											  
	}

	@When("^user click on sign in button$")
    public void user_click_on_sign_in_button() throws Throwable {
		loginPage.clickSignInButton(loginPage.SignInfromNav);
		waitHelper.WaitForElement(loginPage.userName, 60);
	}

	@Then("^user should see Sign In Page$")
    public void user_should_see_sign_in_page() throws Throwable {
		loginPage.userName.isDisplayed();
	}

	@When("^enter username as \"([^\"]*)\"$")
    public void enter_username_as_something(String arg1) throws Throwable {
		loginPage.enterUserName(arg1);
	}
	
	@When("^enter username through excel as \"([^\"]*)\"$")
	public void enter_username_through_excel_as_something(String arg1) throws Throwable {
		loginPage.enterUserName(getExcelValue(arg1));
	}

	@And("^click on Continue button$")
    public void click_on_continue_button() throws Throwable {
		loginPage.clickContinueButton(loginPage.Continue);
		waitHelper.WaitForElement(loginPage.password, 60);
	}

	@And("^enter password as \"([^\"]*)\"$")
    public void enter_password_as_something(String arg1) throws Throwable {
		loginPage.enterPassword(arg1);
	}
	
	@And("^enter password through excel as \"([^\"]*)\"$")
	public void enter_password_through_excel_as_something(String arg1) throws Throwable {
		loginPage.enterPassword(getExcelValue(arg1));
	}

	@When("^click on login button$")
	public void click_on_login_button() throws Throwable {
		loginPage.clickLoginButton(loginPage.loginButton);
	}
	
	@Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
		loginPage.isElementNotPresent(loginPage.SignInLink);											  
	}

	@And("^click on Sign out$")
    public void click_on_sign_out() throws Throwable {
	    loginPage.clickLogoutButton();
	    waitHelper.WaitForElement(loginPage.userName, 60);
	}

	@Then("^user is log out from the application$")
    public void user_is_log_out_from_the_application() throws Throwable {
		loginPage.userName.isDisplayed();
	}
	
	@And("^choose Electronincs after that Headphones$")
    public void choose_electronincs_after_that_headphones() throws Throwable {
	    loginPage.clickHeadphonesLnk();
	}
	
	@Then("^add first availabe headphone to cart$")
    public void add_first_availabe_headphone_to_cart() throws Throwable {
	    loginPage.AddHeadphoneToCart();
	}
	
	@Then("^search \"([^\"]*)\" and add second available item to cart$")
    public void search_something_and_add_second_available_item_to_cart(String arg1) throws Throwable {
	    loginPage.enterSearchItemandAddToCart(arg1);
	}
	
	@And("^clear cart items if any$")
    public void clear_cart_items_if_any() throws Throwable {
	    loginPage.clearCartItemifExist();
	}
	
	@And("^select cart from home and remove the earlier added headphones$")
    public void elect_cart_from_home_and_remove_the_earlier_added_headphones() throws Throwable {
	    loginPage.cartButton.click();
	    loginPage.itemList.get(1).click();
	}
	
	@Then("^reduce the quantity of the macbook pro product to one and proceed to checkout$")
    public void reduce_the_quantity_of_the_macbook_pro_product_to_one_and_proceed_to_checkout() throws Throwable {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("arguments[0].innerText='1'",driver.findElement(By.className("a-dropdown-prompt")));
	}
	
	@And("^search different \"([^\"]*)\" from the search bar$")
    public void search_different_something_from_the_search_bar(String arg1) throws Throwable {
	    loginPage.itemSearchField.sendKeys(getExcelValue(arg1));
	    loginPage.itemSearchField.submit();
	    Thread.sleep(5000);
	}
	
	
	@When("^user enter from destination as \"([^\"]*)\"$")
    public void user_enter_from_destination_as_something(String strArg1) throws Throwable {
		loginPage.enterFromDestination(strArg1);        
	}

	@And("^enter to destination as \"([^\"]*)\"$")
    public void enter_to_destination_as_something(String strArg1) throws Throwable {
	     loginPage.enterToDestination(strArg1);
	}

	@And("^enter \"([^\"]*)\" travel Month as \"([^\"]*)\" and date as \"([^\"]*)\"$")
    public void enter_something_travel_month_as_something_and_date_as_something(String strArg1, String strArg2, String strArg3) throws Throwable {
		loginPage.checkMonthYear(strArg1 , strArg2 );
		loginPage.clickOnDateforOnward(strArg3);
    }
	


	 @And("^enter \"([^\"]*)\" travel Month for return as \"([^\"]*)\" and date as \"([^\"]*)\"$")
	 public void enter_something_travel_month_for_return_as_something_and_date_as_something(String strArg11, String strArg21, String strArg31) throws Throwable {
		 loginPage.checkMonthYear(strArg11 , strArg21 );
		 loginPage.clickOnDateForReturn(strArg31);
		 
	 }
	 
	 @Then("^click on Search Buses button$")
	 public void click_on_search_buses_button() throws Throwable {
		     loginPage.searchBusesButton.click();
		     loginPage.waitForElement(loginPage.onwardModifySearchButton);
	}
}