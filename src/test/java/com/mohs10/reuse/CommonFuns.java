package com.mohs10.reuse;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mohs10.base.StartBrowser;
import com.mohs10.ActionDriver.Action;
import com.mohs10.ActionDriver.SSreuseclass;
import com.mohs10.ActionDriver.dataEncrypt_Decrypt2;
import com.mohs10.ActionDriver.dataMasking;

import com.mohs10.or.HomePage;

public class CommonFuns extends Action {

	public static Action aDriver;
	public WebDriver driver;

	public CommonFuns() {
		aDriver = new Action();
		driver = StartBrowser.driver;
	}

	// *****************************************************Data Masking Script*******************************************************//

	public void login(String url, String Userid, String Pwd) throws Exception {

		StartBrowser.childTest = StartBrowser.parentTest.createNode("login for data Masking");
		aDriver.navigateToApplication(url);
		
		dataMasking.HideData(HomePage.LoginId, Userid, "enter user id");//
		
		
		aDriver.type(HomePage.Password, Pwd, "Password Entered");
	
	
		aDriver.click(HomePage.Login, "button for login clicked");
		SSreuseclass.SSReusablemethod(driver, "login");
		Thread.sleep(5000);
		
	}

	public void DataEntry_masked(String name, String location, String email, String password, String role)
			throws Exception {
		StartBrowser.childTest = StartBrowser.parentTest.createNode("Enter Masked Data");

		aDriver.click(HomePage.addAdmin, "Add Admin button clicked");

		dataMasking.HideData(HomePage.adminName, name, "Adminname entered");
		aDriver.selectDropDown(HomePage.Location, "value", location);
        
		dataMasking.HideData(HomePage.Email, email, "email enterd");
		dataMasking.HideData(HomePage.password, password, "password entered");
		aDriver.type(HomePage.Role, role, "enter role");
		Thread.sleep(5000);
		SSreuseclass.SSReusablemethod(driver, "data enterd");
		WebElement ele = driver.findElement(By.xpath("//button[@type='submit']"));

		JavascriptExecutor ja = (JavascriptExecutor) driver;
		ja.executeScript("window.scrollBy(0,500)");
		ja.executeScript("arguments[0].click();", ele);

		Thread.sleep(5000);

		
	}

	public void logout() throws Exception {
		StartBrowser.childTest = StartBrowser.parentTest.createNode("Log-Out");
		
		aDriver.click(HomePage.suplogout, "log out button clicked");
		aDriver.click(HomePage.singout, "sing out button clicked");
		SSreuseclass.SSReusablemethod(driver, "sucessfully logged out");
		Thread.sleep(5000);
	}

}
