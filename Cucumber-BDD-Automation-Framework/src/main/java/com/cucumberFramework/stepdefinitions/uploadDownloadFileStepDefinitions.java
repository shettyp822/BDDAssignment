package com.cucumberFramework.stepdefinitions;

import com.cucumberFramework.helper.WaitHelper;

import com.cucumberFramework.pageObjects.UploadDownloadFile;
import com.cucumberFramework.testBase.TestBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.cucumberFramework.helper.Excel.getExcelValue;
import static com.cucumberFramework.helper.Excel.readExcel;

public class uploadDownloadFileStepDefinitions extends  TestBase {


    UploadDownloadFile uploadDownloadFile = new UploadDownloadFile(driver);
    WaitHelper waitHelper = new WaitHelper(driver);

    @Given("^I am on the pdf converter page (.+)$")
    public void i_am_on_the_pdf_converter_page(String url) throws Throwable {
        int row = Integer.parseInt(url.substring(3));
        readExcel(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata","UploadDownloadTestData.xlsx","UploadDownloadFile",row);
        driver.get(getExcelValue("url"));
        waitHelper = new WaitHelper(driver);
    }

    @When("^I click on upload button to upload file$")
    public void i_click_on_upload_button_to_upload_file() throws Throwable {
        uploadDownloadFile.uploadFile();
    }

    @When("^I select download button to download converted file$")
    public void i_select_download_button_to_download_converted_file() throws Throwable {
        uploadDownloadFile.downloadFile();
        Thread.sleep(1000);
    }

    @Then("^file conversion is in progress$")
    public void file_conversion_is_in_progress() throws Throwable {
        waitHelper.WaitForElement(uploadDownloadFile.conversionSuccess, 1000);
        uploadDownloadFile.conversionSuccess.isDisplayed();
    }

    @Then("^file is downloaded$")
    public void file_is_downloaded() throws Throwable {
        uploadDownloadFile.downloadFileSuccess();
    }

}
