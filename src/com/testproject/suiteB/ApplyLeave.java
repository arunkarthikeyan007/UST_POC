package com.testproject.suiteB;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.testproject.OR.Login_OR;
import com.testproject.util.TestUtil;

//Leave Request
public class ApplyLeave extends TestSuiteBase {
	
	String executionStatus = "Pass";
	String f = null;
	
	@BeforeTest
	public void checkTestSkip() throws IOException 
	{
		Applog.debug("Checking Run mode of | Suite - B | Testcase -"+this.getClass().getSimpleName()+" |");
		
		if(!TestUtil.isRunnable(xls, "Testcases",this.getClass().getSimpleName()))	
		{	executionStatus = "Skip";
			Applog.debug("Run mode - N|Skipping| Suite A | Testcase "+this.getClass().getSimpleName()+" |");
			throw new SkipException("Run mode is NO | Skipping "+this.getClass().getSimpleName());
		}
		
	}
	
	@Test
	public void LeaveRequest()
	{
		
		try{
			
			WebDriver driver = new FirefoxDriver();
			driver.get("https://orion.ust-global.com");
			
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userid")));
					
			Login_OR OR = new Login_OR(driver);
						
			xls = xlsObjectIntialize("TestData");
			String uname= xls.getCellData("Leave_type", "User Name", 2);
			String pswd= xls.getCellData("Leave_type", "Pswd", 2);
			
			OR.login(uname,pswd);
			
			//Apply leave
				
			driver.findElement(By.id("pthnavbc_PORTAL_ROOT_OBJECT")).click();
			driver.findElement(By.id("fldra_CO_EMPLOYEE_SELF_SERVICE")).click();
			driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
			driver.findElement(By.id("fldra_HC_TIME_REPORTING")).click();
			driver.findElement(By.id("fldra_HC_RECORD_TIME")).click();
			driver.findElement(By.id("crefli_HC_GP_ABS_EESS_REQ_GBL")).click();
					
			driver.switchTo().frame(0);
			Select BGNDate = new Select(driver.findElement(By.id("DERIVED_ABS_SS_PIN_TAKE_NUM")));
			BGNDate.selectByVisibleText("Sick Leave");
			
			/*driver.findElement(By.id("DERIVED_ABS_SS_BGN_DT")).clear();
			driver.findElement(By.id("DERIVED_ABS_SS_BGN_DT")).sendKeys(Keys.ALT,"5");
			
			Select month = new Select(driver.findElement(By.name("PTMonth")));
			month.selectByVisibleText("June");
			
			Select year = new Select(driver.findElement(By.name("PTYear")));
			year.selectByVisibleText("2018");
			
			driver.findElement(By.xpath(".//*[@id='bodyCalendar']/tbody/tr[3]/td[3]/a")).click();
			*/
					
			driver.findElement(By.id("DERIVED_ABS_SS_BGN_DT")).clear();
			driver.findElement(By.id("DERIVED_ABS_SS_BGN_DT")).sendKeys("06/12/2017");	
			driver.findElement(By.id("DERIVED_ABS_SS_END_DT")).clear();
			driver.findElement(By.id("DERIVED_ABS_SS_END_DT")).sendKeys("06/13/2017");	
			
			driver.findElement(By.id("DERIVED_ABS_SS_FCST_PB")).click();
			String eligibity = driver.findElement(By.xpath(".//*[@id='ACE_DERIVED_ABS_SS_GRPB_LABEL']/tbody/tr[13]/td[2]/div")).getText();
		
			boolean  b = eligibity.startsWith("Returned Value: ELIGIBLE");  
			if(b)
			{
				Applog.debug("Leave Request Status : ELIGIBLE");
			}
			
			driver.findElement(By.id("DERIVED_ABS_SS_COMMENTS")).sendKeys("Testing - Selenium");
			driver.findElement(By.id("DERIVED_ABS_SS_SUBMIT_BTN")).click();
			
			Applog.debug(" Aborting......Leave Request");
			Thread.sleep(3000);
			
			driver.switchTo().defaultContent();
				
			driver.findElement(By.id("pthdr2logout")).click();
			driver.findElement(By.id("ptpopupmsgbtn2")).click();
			driver.close();
						
			}catch(Throwable t) 
			{	
			executionStatus = "Fail";
			f= t.toString(); 
			//System.out.println(f);
			System.out.println(t.getMessage());
			Applog.debug("Testcase FAILED");
			throw new Error("failure",t);
			}
		
	}
	
	
	@AfterTest
	public void AfterTest() throws IOException
	{
		xls = xlsObjectIntialize("TestData");
		//clear failure Reason
		TestUtil.reportfailureReason(xls,"Leave_type",2,"");
		
		if (executionStatus.equalsIgnoreCase("Fail"))
		{
			TestUtil.reportfailureReason(xls,"Leave_type",2,f);
		}
			
		TestUtil.reportDataSetResult(xls,"Leave_type",2,executionStatus);
		
		xls = xlsObjectIntialize("Testsuite_B");
		int rownum = TestUtil.getrownum(xls,"Testcases",this.getClass().getSimpleName());
		TestUtil.reportDataSetResult(xls,"Testcases",rownum,executionStatus);
		Applog.debug("Execution completed for the testcase "+this.getClass().getSimpleName());
		
	}
	
	
}
