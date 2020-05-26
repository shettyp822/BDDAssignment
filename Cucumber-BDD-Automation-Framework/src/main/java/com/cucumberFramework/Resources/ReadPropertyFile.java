package com.cucumberFramework.Resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
 
public class ReadPropertyFile {
 
 private Properties properties;
 private final String propertyFilePath= System.getProperty("user.dir") + "/src/test/resources/configs/Configuration.properties";
 
 
 public ReadPropertyFile(){
 BufferedReader reader;
 try {
 reader = new BufferedReader(new FileReader(propertyFilePath));
 properties = new Properties();
 try {
 properties.load(reader);
 reader.close();
 } catch (IOException e) {
 e.printStackTrace();
 }
 } catch (FileNotFoundException e) {
 e.printStackTrace();
 throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
 } 
 }
 
 public String getURL(String arg1) {
	 
	 String redBus = "redbusURL";
	 String amazon = "amazonURL";
	if (redBus.equals(arg1)) {
		 String url = properties.getProperty("redbusURL");
		  return url;
	 }
	else if (amazon.equals(arg1)) {
		String url = properties.getProperty("amazonURL");
		  return url;
	}
	else throw new RuntimeException("url not specified in the Configuration.properties file.");
 }
 
 public String getFromDestination(String arg1) {
	 String fromDestination;
	 fromDestination = properties.getProperty(arg1);
	 return fromDestination;
 }
 
 public String getToDestination(String arg1) {
	 String toDestination;
	 toDestination = properties.getProperty(arg1);
	 return toDestination;
 }
 
 public String getUserName(String arg1) {
	 String amazonUser = "amazonUserName";
	 String userName = null;
	 if (amazonUser.equals(arg1)) {
		 userName = properties.getProperty(arg1);
		 return userName;
	}
	else {
	 return arg1;
	}
 }
 
 public String getPassword(String arg1) {
	 String amazonPassword = "amazonPassword";
	 String password = null;
	 if (amazonPassword.equals(arg1)) {
		 password = properties.getProperty(arg1);
		 return password;
	}
	else {
	 return arg1;
	}
 }
}