package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.testproject.base.TestBase;

public class Testsample extends TestBase{

	@Test
	public void one() throws IOException
	{ 
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.google.co.in");
		
		driver.findElement(By.id("sb_ifc0")).click();
		driver.findElement(By.id("sb_ifc0")).sendKeys("amazon");
		driver.findElement(By.id("sb_ifc0")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("_fZl")).click();
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//*[@id='rso']/div[1]/div/div/div/div/h3/a")).click();
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("moto x4 play");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
		//driver.findElement(By.id("nav-search-submit-text")).click();
		driver.findElement(By.id("twotabsearchtextbox")).click();
		
		driver.close();
		
	}
	
	
	
}


