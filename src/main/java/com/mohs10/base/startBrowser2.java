package com.mohs10.base;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class startBrowser2 {
	 public static WebDriver driver;
		 //set up report
		 public static ExtentReports extent;
		 public static ExtentTest parentTest;
		 public static ExtentTest childTest;
		 ExtentSparkReporter sparkReporter;
		 //keyword
		 ExtentReports reports;
			ExtentTest test;
		 
		 @BeforeTest
		 public void generateReport()
		 {
			 sparkReporter = new ExtentSparkReporter("Report/AutomationReport.html");
			 extent = new ExtentReports();
			 extent.attachReporter(sparkReporter);
		 }
		 
		 @BeforeMethod
		 public void methodName(Method method)
		 {
			parentTest = extent.createTest(method.getName()); 
		 }
		  @BeforeClass
		  public void beforeClass() {
			
			          // Set the path to the chromedriver executable
			          System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

			          // Configure Chrome options
			          ChromeOptions chromeOptions = new ChromeOptions();
			          chromeOptions.addArguments("--disable-extensions"); // Disable browser extensions
			          chromeOptions.addArguments("--disable-plugins-discovery"); // Disable plugin discovery
			          chromeOptions.addArguments("--disable-bundled-ppapi-flash"); // Disable bundled Flash Player
			          chromeOptions.addArguments("--disable-infobars"); // Disable infobars
			          chromeOptions.addArguments("--disable-popup-blocking"); // Disable popup blocking
			          chromeOptions.addArguments("--disable-translate"); // Disable translation prompt
			          chromeOptions.addArguments("--ignore-certificate-errors"); // Ignore certificate errors
			          chromeOptions.addArguments("--allow-insecure-localhost"); // Allow insecure localhost connections

			          // Create a new instance of the Chrome driver
			          WebDriver driver = new ChromeDriver(chromeOptions);
			         // driver.get("http://jyotsnaprod.ap-south-1.elasticbeanstalk.com/login?logout");
			          
			          // Retrieve the current URL and check if it's using HTTPS or HTTP
			          String currentUrl = driver.getCurrentUrl();
			          if (currentUrl.startsWith("https")) {
			              System.out.println(currentUrl +":The website is secure.");
			          } else {
			              System.out.println(currentUrl+":The website is not secure.");
			          }

			          // Close the browser window when you're done
			          driver.quit();
			      }
			  

			 // WebDriverManager.chromedriver().setup();
			 // WebDriverManager.firefoxdriver().setup();
			  //WebDriverManager.edgedriver().setup();
			 // driver = new ChromeDriver();
			//  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 // driver.manage().window().maximize();
		  
		  @AfterClass
		  public void afterClass() {
			  
			  driver.quit();
			  extent.flush();
			 // extent.endTest(test);
		  }
		  

		}


