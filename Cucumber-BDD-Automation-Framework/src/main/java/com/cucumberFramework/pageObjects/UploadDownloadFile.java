package com.cucumberFramework.pageObjects;

import static com.cucumberFramework.helper.Excel.readExcel;
import static com.cucumberFramework.helper.MiscellaneousFunctions.*;
import com.cucumberFramework.helper.WaitHelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.io.IOException;

import static com.cucumberFramework.stepdefinitions.ServiceHooks.*;

import static com.cucumberFramework.helper.Excel.getExcelValue;

public class UploadDownloadFile {
    private WebDriver driver;

    @FindBy(xpath = "//span[contains(text(), 'Choose Files')]")
    public WebElement uploadButton;

    @FindBy(xpath = "//span[contains(text(), 'Download')]")
    public WebElement downloadButton;

    @FindBy(xpath = "//div[contains(text(), 'Nice! We have successfully converted your Excel file to PDF!')]")
    public WebElement conversionSuccess;

    WaitHelper waitHelper;

    public UploadDownloadFile(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    public void uploadFile() throws InterruptedException, AWTException {
        Thread.sleep(5000);
        uploadButton.click();
        Thread.sleep(5000);
        sendText(prop.get("filename") + getExcelValue("fileName"));
    }

    public void downloadFile() throws InterruptedException {
        clickElement(downloadButton);
        Thread.sleep(1000);
    }

    public String getUrl(String fileName, String sheetName, String url) throws IOException {
        int row = Integer.parseInt(url.substring(3));
        readExcel(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata","UploadDownloadTestData.xlsx","UploadDownloadFile",row);
        String url1 = getExcelValue("url");
        return url1;
    }

}

