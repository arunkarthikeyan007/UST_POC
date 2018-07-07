package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Sample_tc {

	@Test
	public void LoginOrion()
	{
		WebDriver driver = new FirefoxDriver();
		driver.get("https://orion.ust-global.com");
		
		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userid")));
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				
		driver.findElement(By.id("userid")).clear();
		driver.findElement(By.id("userid")).sendKeys("u37506");
		driver.findElement(By.id("pwd")).clear();
		driver.findElement(By.id("pwd")).sendKeys("Django@ww");
		driver.findElement(By.className("psloginbutton")).click();
	//Invalid login
		//Assert.assertEquals(driver.findElement(By.id("login_error")).getText(), "Your User ID and/or Password are invalid.");
	//Login validation
		Assert.assertEquals(true, (driver.findElement(By.id("pthdr2logout")).isDisplayed()));
		
		driver.findElement(By.id("pthnavbc_PORTAL_ROOT_OBJECT")).click();
		driver.findElement(By.id("fldra_CO_EMPLOYEE_SELF_SERVICE")).click();
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
		driver.findElement(By.id("fldra_HC_TIME_REPORTING")).click();
		driver.findElement(By.id("fldra_HC_VIEW_TIME")).click();
		driver.findElement(By.cssSelector("#crefli_ABSENCE_BALANCE_REALTIME")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.switchTo().frame(0);
		driver.findElement(By.id("UST_LVBL_WRK_VIEW_DETAILS")).click();
		
		//driver.close();
		
	}
	
}
