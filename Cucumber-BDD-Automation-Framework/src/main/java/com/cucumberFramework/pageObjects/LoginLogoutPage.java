package com.cucumberFramework.pageObjects;

import java.util.Iterator;


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumberFramework.helper.WaitHelper;
import com.cucumberFramework.Resources.ReadPropertyFile;

public class LoginLogoutPage {
	
	private WebDriver driver;
	Alert alert;
	ReadPropertyFile prop = new ReadPropertyFile();
	
	@FindBy(xpath="//input[@type='email']")
	public WebElement userName;
	
	@FindBy(xpath="//input[@id='continue']")
	public WebElement Continue;
	
	@FindBy(xpath="//input[@type='password']")
	public WebElement password;
	
	@FindBy(xpath="//input[@id='signInSubmit']")
	public WebElement loginButton;
	
	@FindBy(xpath="//div[@id='nav-tools']/a[@data-nav-role='signin']")
	public WebElement SignInfromNav;
	
	@FindBy(xpath="//span[contains(text(),'Sign in')]")
	public WebElement SignInLink;												
	@FindBy(xpath="//span[contains(text(),'Sign')]/parent::a")
	public WebElement logoutBtn;
	
	@FindBy(xpath="//div[@id='nav-shop']/a")
	public WebElement allShopNav;

	@FindBy(xpath="//span[@data-nav-panelkey='TvApplElecPanel']")
	public WebElement TvApplElecPanel;
	
	@FindBy(xpath="//span[contains(text(),'Headphones')]/parent::a")
	public WebElement headPhonesCatLnk;
	
	@FindBy(xpath="//div[@id='mainResults']/ul/li[1]/div/div/div/a[contains(@class,'access-detail-page')]")
	public WebElement firstHeadPhoneLnk;
	
	@FindBy(xpath="//input[@id='add-to-cart-button']")
	public WebElement addToCartBtn;
	
	@FindBy(xpath="//a[@id='nav-cart']")
	public WebElement cartButton;
	
	@FindBy(xpath="//form[@id='activeCartViewForm']/div[@data-name='Active Items' or contains(@class,'sc-list-body')]//input[@value='Delete']")
	public List<WebElement> itemList;
	
	//form[@id='activeCartViewForm']/div[@data-name='Active Items' or contains(@class,'sc-list-body')]//input[@value='Delete']
	
	@FindBy(xpath="//div[contains(@class,'nav-search-field')]/input")
	public WebElement itemSearchField;
	
	@FindBy(xpath="//div[starts-with(@class,'sg-col-4')]/div[@class='sg-col-inner']/div/h5/a")
	public WebElement secondMacbookItem;
	
	@FindBy(xpath="//select[@id='quantity' or @name='quantity']")
	public List<WebElement> qtyField;
	
	@FindBy(xpath="//div[contains(@class,'search-box')]/div/input[contains(@id,'src')]")
	public WebElement fromDestination;
	
	@FindBy(xpath="//div[contains(@class,'search-box')]/div/input[contains(@id,'dest')]")
	public WebElement toDestination;
	
	@FindBy(xpath="//div[contains(@class,'search-box')]/div/ul[contains(@class,'autoFill')]/li[contains(@class,'selected')]")
	public WebElement autoFillSelection;
	
	@FindBy(xpath="//div[contains(@class,'search-box date')]/div/label[contains(text(),'Onward Date')]")
	public WebElement onwardDatePicker;
	
	@FindBy(xpath="//div[contains(@class,'search-box date')]/div/label[contains(text(),'Return Date')]")
	public WebElement returnDatePicker;
	
	@FindBy(xpath="//div[contains(@id,'rb-calendar_onward_cal')]/table/tbody/tr[contains(@class,'monthHeader')]/td[2]")
	public WebElement monthtitleForOnwardCal;
	
	@FindBy(xpath="//div[contains(@id,'rb-calendar_onward_cal')]/table/tbody/tr[contains(@class,'monthHeader')]/td[3]")
	public WebElement nextIconOnMonthtitleForOnwardCal;
	
	@FindBy(xpath="//div[contains(@id,'rb-calendar_return_cal')]/table/tbody/tr[contains(@class,'monthHeader')]/td[2]")
	public WebElement monthtitleForReturnCal;
	
	@FindBy(xpath="//div[contains(@id,'rb-calendar_return_cal')]/table/tbody/tr[contains(@class,'monthHeader')]/td[3]")
	public WebElement nextIconOnMonthtitleForReturnCal;
	
	@FindBy(xpath="//button[contains(@id,'search_btn')]")
	public WebElement searchBusesButton;
	
	@FindBy(xpath="//div[contains(@class,'onward-modify-btn')]")
	public WebElement onwardModifySearchButton;
	
	WaitHelper waitHelper;
	
	public LoginLogoutPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		//waitHelper.WaitForElement(userName, 60);
	}
	
	public void enterUserName(String userName){
	this.userName.sendKeys(prop.getUserName(userName));
	}
	
	public void enterPassword(String password){
	this.password.sendKeys(prop.getPassword(password));
	}
	
	public void clickLoginButton(WebElement Element1){
		Element1.click();
	}
	
	public void enterSearchItemandAddToCart(String item){
		String mainWindow=driver.getWindowHandle();
		this.itemSearchField.sendKeys(item);
		this.itemSearchField.submit();
		secondMacbookItem.click();
		Set<String> set =driver.getWindowHandles();
		Iterator<String> itr= set.iterator();
		while(itr.hasNext()){
		 String childWindow=itr.next();
		 if(!mainWindow.equals(childWindow)){
			 driver.switchTo().window(childWindow);
			 System.out.println(driver.switchTo().window(childWindow).getTitle());
			 if(qtyField.size()>=1){
				 
				 Select sel = new Select(qtyField.get(0));
				 sel.selectByValue("2");
			 }
			 
			 JavascriptExecutor js = (JavascriptExecutor)driver;
			 js.executeScript("arguments[0].scrollIntoView(true);",addToCartBtn);
			 js.executeScript("arguments[0].click();", addToCartBtn);
			 if(driver.findElements(By.xpath("//div[@class='a-popover-inner']//button[contains(text(),'Skip')]")).size()>=1){
				 
				 driver.findElements(By.xpath("//div[@class='a-popover-inner']//button[contains(text(),'Skip')]")).get(0).click();
			 }
			 //addToCartBtn.click();
			 //driver.close();
		 }
		}
		driver.switchTo().window(mainWindow);
	}
	
	public void clickSignInButton(WebElement Element1){
		Actions builder = new Actions(driver);
		builder.moveToElement(Element1).build().perform();
		Element1.click();
	}
	
	public void clearCartItemifExist(){
		cartButton.click();
		int i = itemList.size();
		if(i>=1){
			itemList.get(0).click();
			i = itemList.size();
		}
	}
	
	public void clickHeadphonesLnk(){
		Actions builder = new Actions(driver);
		builder.moveToElement(allShopNav).build().perform();
		builder.moveToElement(TvApplElecPanel).build().perform();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", headPhonesCatLnk);
		
	}
	 public boolean isElementNotPresent(WebElement locatorKey) {
		try {
			locatorKey.isDisplayed();
			return false;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return true;
		}
	}  
	 
	public void AddHeadphoneToCart(){
		
		firstHeadPhoneLnk.click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("arguments[0].click();", addToCartBtn);
	}
	
	public void clickContinueButton(WebElement Element1){
		Element1.click();
	}
	
	
	public void clickLogoutButton(){
		Actions builder = new Actions(driver);
		builder.moveToElement(SignInfromNav).build().perform();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("arguments[0].click();", logoutBtn);;
	}
	
	public void clickContinueWithEmail(WebElement Element1 ) {
		driver.switchTo().frame("overlayRegFrame");
		Element1.sendKeys(Keys.ENTER);
	}
	
	public void waitForElement(WebElement item) {
	    WebDriverWait wait = new WebDriverWait(driver,90);
	    WebElement Ele = wait.until(ExpectedConditions.visibilityOf(item));
	    //return Ele;
	}
	
	public void enterFromDestination(String fromDestination) {
		this.fromDestination.sendKeys(prop.getFromDestination(fromDestination));	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		autoFillSelection.click();
	}
	
	public void enterToDestination(String toDestination) {
		this.toDestination.click();
		this.toDestination.sendKeys(prop.getToDestination(toDestination));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		autoFillSelection.click();
	}
		
	public void checkMonthYear(String strArg1 , String strArg2 ) throws InterruptedException {
	
		
		String onward1 = "onward";
		
		if(onward1.equals(strArg1)) {
			onwardDatePicker.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			while(true) {
				 String text1 = monthtitleForOnwardCal.getText();
				 if(text1.equals(strArg2)) {
					 break;
				 }
				 else {
					 nextIconOnMonthtitleForOnwardCal.click();
					 Thread.sleep(5000);
				 }
			 }
		}
		else {
			returnDatePicker.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 while(true) {
				 String text1 = monthtitleForReturnCal.getText();
				 if(text1.equals(strArg2)) {
					 break;
				 }
				 else {
					 nextIconOnMonthtitleForReturnCal.click();
					 Thread.sleep(5000);
				 }
			 }
		}
		 	
	}

	public void clickOnDateforOnward(String strArg3) {
		driver.findElement(By.xpath("//div[contains(@id,'rb-calendar_onward_cal')]/table/tbody/tr/td[contains(text(),"+strArg3+")]")).click();
		
	}
	public void clickOnDateForReturn(String strArg3) {
		driver.findElement(By.xpath("//div[contains(@id,'rb-calendar_return_cal')]/table/tbody/tr/td[contains(text(),"+strArg3+")]")).click();
		
	}

	
}
