package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testproject.OR.Login_OR;

public class test2 {
	
	@DataProvider
	public String[][] getTestData()
	{
		String credentials[][] = new String[1][2];
		
		credentials[0][0] = "a";
		credentials[0][1] = "b";
				
		return credentials;
	}
	
	@Test(dataProvider="getTestData")
	public void InvalidLoginOrion(String username, String password)
	{
		//System.out.println("**********");
	
		WebDriver driver = new FirefoxDriver();
		driver.get("https://orion.ust-global.com");
		
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userid")));
				
		Login_OR OR = new Login_OR(driver);
		OR.login(username,password);
					
		Assert.assertEquals(driver.findElement(By.id("login_error")).getText(), "Your User ID and/or Password are invalid.");
	}
	
	
	@AfterTest
	public void Closebrowser()
	{
	}
	
	}

