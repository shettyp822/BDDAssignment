package com.cucumberFramework.stepdefinitions;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import com.cucumberFramework.enums.Browsers;
import com.cucumberFramework.helper.LoggerHelper;
import com.cucumberFramework.testBase.TestBase;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ServiceHooks {

	TestBase testBase;

	Logger log = LoggerHelper.getLogger(ServiceHooks.class);
	public static Properties prop;
	@Before
	public void initializeTest() throws IOException {
		testBase = new TestBase();
		testBase.selectBrowser(Browsers.CHROME.name());
		readProp();
	}
	public static void readProp() throws IOException {
		FileInputStream fis = null;
		 fis = new FileInputStream(System.getProperty("user.dir") +"\\config.properties");
			prop = new Properties();
			prop.load(fis);
	}

	@After
	public void endTest(Scenario scenario) {
		if (scenario.isFailed()) {

			try {
				log.info(scenario.getName() + " is Failed");
				final byte[] screenshot = ((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png"); // ... and embed it in
			} catch (WebDriverException e) {
				e.printStackTrace();
			}

		} else {
			try {
				log.info(scenario.getName() + " is pass");
				scenario.embed(((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.BYTES), "image/png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		TestBase.driver.quit();
	}
}
