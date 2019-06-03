package com.sample.abc;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;


import io.github.bonigarcia.wdm.WebDriverManager;

public class Loginpage {

	static String type = "Headless";
	static WebDriver driver;
	static String myBrowser = "Chrome";
	String username1 = "opensourcecms";
	String password1 = "opensourcecms";
	String username2 = "opensourcecms";
	String password2 = "opensourcecm";
	
	static Logger log = Logger.getLogger("Loginpage");
	
	

	
	@Parameters({"myBrowser", "type_of_run"})
	@BeforeMethod
	public static void getBrowser(String myBrowser, String type_of_run) {

		System.out.print("You selected to run the script with Headless ");
		if (type_of_run.equalsIgnoreCase("Headless")) {

			if (myBrowser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_52);
				log.info("The head-less firefox browser is intilalised and opened");
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

			}
			else if (myBrowser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new HtmlUnitDriver(BrowserVersion.CHROME);
				log.info("The head-less chrome browser is intilalised and opened");
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}

			else if (myBrowser.equalsIgnoreCase("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new HtmlUnitDriver(BrowserVersion.INTERNET_EXPLORER);
				log.info("The head-less IE  browser is intilalised and opened");
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}

		}
		

		else {
			System.out.print("You selected to run the script with browser ");

			if (myBrowser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("The firefox browser is intilalised and opened");
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}
			else if (myBrowser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.info("The chrome browser is intilalised and opened");
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}
			else if (myBrowser.equalsIgnoreCase("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				log.info("The IE  browser is intilalised and opened");
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}

		}
		driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-login.php");
		log.info("fetching the website url");
		log.info("The login page is opened");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}	
	


	@Test(priority=1)

	public void validatelogin() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#user_login")).click();
		driver.findElement(By.cssSelector("#user_login")).sendKeys("opensourcecms");
		log.info("Given correct username");
		driver.findElement(By.cssSelector("#user_pass")).click();
		driver.findElement(By.cssSelector("#user_pass")).sendKeys("opensourcecms");
		log.info("Given correct password");
		driver.findElement(By.xpath("//input[@id='wp-submit']")).click();
		log.info("The login page is validated");

	}


	@Test(priority=2)
	public void validateincorrectlogin() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("#user_login")).click();
		driver.findElement(By.cssSelector("#user_login")).sendKeys("opensourccm");
		log.info("Given in-correct username");
		driver.findElement(By.cssSelector("#user_pass")).click();
		driver.findElement(By.cssSelector("#user_pass")).sendKeys("opensourcecms");
		log.info("Given correct password");
		driver.findElement(By.xpath("//input[@id='wp-submit']")).click();
		log.info("The login page is  not validated");

		
	
	}
	
	
	@Test(priority=3)
	public void validateLogo() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Powered by WordPress")).isDisplayed();
		log.info("The logo is getting displayedand it is validated");
	}



	@AfterMethod

	public void closeBrowser() {
		driver.quit();
		log.info("Driver is ready to quit");
	}

}
