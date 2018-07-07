package com.testproject.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Login_OR {
	
	WebDriver driver;
	private By userName = By.name("userid");
	private By password = By.name("pwd");
	private By submit =  By.className("psloginbutton");
	    
	public Login_OR(WebDriver driver)
	{
	        this.driver = driver;
	}
	  	  
	public void login(String strusername,String strpassword)
	{
	driver.findElement(userName).sendKeys(strusername);
	driver.findElement(password).sendKeys(strpassword);
	driver.findElement(submit).click();
	}

	
}
