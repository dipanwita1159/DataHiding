package com.mohs10.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.mohs10.base.StartBrowser;

public class dataMasking extends StartBrowser {
	
	
	
	public static String screenShot() throws WebDriverException {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

	public static void maskEmail(By locator, String email, String command) {
	    try {
	        // Mask the email address
	        String[] emailParts = email.split("@");
	        String username = emailParts[0];
	        String domain = emailParts[1];
	        String maskedUsername = "";
	        for (int i = 0; i < username.length() - 1; i++) {
	            maskedUsername += "*";
	        }
	        maskedUsername += username.charAt(username.length() - 1);
	        String maskedEmail = maskedUsername + "@" + domain;

	        // Store the original email
	        String originalEmail = email;

	        // Pass the masked email to the field
	        WebElement ele = StartBrowser.driver.findElement(locator);
	        ele.sendKeys(maskedEmail);

	        // Store the original email as an attribute in the element
	        ((JavascriptExecutor) StartBrowser.driver).executeScript(
	            "arguments[0].setAttribute('data-original-email', arguments[1]);", ele, originalEmail);
	        //ele.sendKeys(originalEmail);

	        StartBrowser.childTest.pass("Successfully performed type action on: " + maskedEmail +
	            " with test data: " + command);
	    } catch (Exception e) {
	        StartBrowser.childTest.fail("Unable to perform type action on: " + email +
	            " with test data: " + command, MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
	        StartBrowser.childTest.info(e);
	        throw e;
	    }
	}

	public static void masktext(By locator, String text, String command) {
		try {
			// Replace characters with asterisks except for the last character
			String maskedFirstName = "";
			int length = text.length();
			for (int j = 0; j < length - 1; j++) {
				maskedFirstName += "*";
			}
			maskedFirstName += text.charAt(length - 1);
			
			// Store the original text
						String originaltext = text;

			// Pass the masked first name to the field
			System.out.println(maskedFirstName);
			WebElement ele = StartBrowser.driver.findElement(locator);
			ele.sendKeys(maskedFirstName);
			
			// Store the original email in runtime
			((JavascriptExecutor) StartBrowser.driver).executeScript(
					"arguments[0].setAttribute('data-original-email', arguments[1]);", ele, originaltext);
	              System.out.println(originaltext);
	            //  ele.sendKeys(originaltext);

			StartBrowser.childTest
					.pass("Successfully performed type action on :" + text + " With test data :" + command);
		} catch (Exception e) {

			StartBrowser.childTest.fail(
					"Unable to performe type action action on :" + text + " With test data :" + command,
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
			StartBrowser.childTest.info(e);
			throw e;
		}
	}

	/*
	 * public static void maskNumbers(By locator, int number) { // Convert the
	 * number to a string String numberString = String.valueOf(number);
	 * 
	 * // Replace digits with asterisks except for the last character String
	 * maskedNumber = ""; int length = numberString.length(); for (int j = 0; j <
	 * length - 1; j++) { maskedNumber += "*"; } maskedNumber +=
	 * numberString.charAt(length - 1);
	 * 
	 * // Pass the masked number to the field System.out.println(maskedNumber);
	 * WebElement ele = StartBrowser.driver.findElement(locator);
	 * ele.sendKeys(maskedNumber); }
	 */

	public static void maskExpectChar(By locator, String text, String command) {
		try {

			// Replace characters with asterisks except for the first and last character
			String maskedFirstName = text.charAt(0) + "";
			int length = text.length();
			for (int j = 1; j < length - 2; j++) {
				maskedFirstName += "*";
			}
			maskedFirstName += text.charAt(length - 2);

			// Pass the masked first name to the field
			System.out.println(maskedFirstName);
			WebElement ele = StartBrowser.driver.findElement(locator);
			ele.sendKeys(maskedFirstName);
			StartBrowser.childTest
					.pass("Successfully performed type action on :" + text + " With test data :" + command);
		} catch (Exception e) {

			StartBrowser.childTest.fail(
					"Unable to performe type action action on :" + text + " With test data :" + command,
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());
			StartBrowser.childTest.info(e);
			throw e;
		}
	}
;

	
//hide the entered data & input box after sendkeys operation.
    public static void HideData( By locator, String text, String command) {
        try {
            // Locate the input field
            WebElement inputField = driver.findElement(locator);

            // Hide the input field by modifying its visibility using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'none';", inputField);

            // Set the value attribute of the input field using JavaScript
            String value = text;
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].setAttribute('value', arguments[1]);", inputField, value);

            // Perform sendKeys using the hidden data
            inputField.sendKeys(inputField.getAttribute("value"));

            

            System.out.println("Successfully performed type action on: " + value + " with test data: " + command);
        } catch (Exception e) {
            System.out.println("Unable to perform type action on: " + text + " with test data: " + command);
            e.printStackTrace();
        }
    }
}

