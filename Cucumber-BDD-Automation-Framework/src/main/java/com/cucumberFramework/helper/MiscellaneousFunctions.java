package com.cucumberFramework.helper;

import com.cucumberFramework.testBase.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import static com.cucumberFramework.helper.Excel.getExcelValue;
import static com.cucumberFramework.helper.Excel.readExcel;
import static com.cucumberFramework.stepdefinitions.ServiceHooks.prop;


public class MiscellaneousFunctions extends TestBase {

    public static void getUrl(Object fileName, Object sheetName, String url) throws IOException {
        int row = Integer.parseInt(url.substring(3));
        readExcel(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata",fileName.toString(),sheetName.toString(),row);
        String url1 = getExcelValue("url");
        driver.get(url1);
    }

    public static void clickElementJavascript() {

    }

    public static void clickElement(WebElement element) {
           element.click();
    }

    public static void enterText( String text) {

    }

    public static void sendText(String textTOSend) throws AWTException, InterruptedException{
        StringSelection stringSelection= new StringSelection(textTOSend);
        Clipboard clipboard= Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection,stringSelection);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
    }

    public static void clearText() {


    }

    public static void selectDateFromDatePicker() {


    }

    public static void uploadFile() {

    }

    public static File getLastModifiedFile(File directory) {
        File[] files = directory.listFiles();
        if (files.length == 0) return null;
        Arrays.sort(files, new Comparator<File>() {
            public int compare(File o1, File o2) {
                return new Long(o2.lastModified()).compareTo(o1.lastModified());
            }});
        return files[0];
    }

    public static void downloadFileSuccess() {
        File file = new File(prop.get("downloadedFilePath").toString());
        File downloadedfile = getLastModifiedFile(file);
        boolean downloadStatus = downloadedfile.toString().contains(getExcelValue("downloadedFileName"));
        Assert.assertTrue(downloadStatus);
        System.out.println("Downloaded file location: " + downloadedfile + "\nDownload successful: " + downloadStatus);
    }

}
