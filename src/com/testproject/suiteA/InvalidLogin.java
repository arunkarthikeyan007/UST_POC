package com.testproject.suiteA;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testproject.OR.Login_OR;
import com.testproject.util.TestUtil;

//invalid login
public class InvalidLogin extends TestSuiteBase{

	static int count = -1;
	String testcaseStatus = "Pass";
	String executionStatus = "Pass";
	String f = null;
	
	//WebDriver driver = new FirefoxDriver();
	
	@BeforeTest
	public void checkTestSkip() throws IOException 
	{
		Applog.debug("Checking Run mode of | Suite - A | Testcase -"+this.getClass().getSimpleName()+" |");
		
		if(!TestUtil.isRunnable(xls, "Testcases",this.getClass().getSimpleName()))	
		{	executionStatus = "Skip";
			Applog.debug("Run mode set to NO |Skipping| Suite A | Testcase "+this.getClass().getSimpleName()+" |");
			throw new SkipException("Run mode set to NO  NO | Skipping "+this.getClass().getSimpleName());
		}
		
		xls = xlsObjectIntialize("TestData");
		runMode = TestUtil.dataSetRunMode(xls,"Test_data_Invalid");
	}
	
	@DataProvider
	public Object[][] getTestData()
	{		
		return TestUtil.getdata(xls,"Test_data_Invalid");
	}
	
	@Test(dataProvider="getTestData")
	public void InvalidLoginOrion(String username, String password)
	{
		count++;
		if(!runMode[count].equalsIgnoreCase("Y")){
			executionStatus = "Skip";
			Applog.debug("Skipping | testcase -"+this.getClass().getSimpleName()+ " | data set "+ count + " | Run mode set to NO");
			throw new SkipException("Skipping "+this.getClass().getSimpleName()+ "| for data set "+count +" | Run mode set to NO ");
			}
		
		Applog.debug("Executing |Testcase - "+this.getClass().getSimpleName()+" |for data set "+count+" ........");
		
		try{
		
			WebDriver driver = new FirefoxDriver();
			//driver = new FirefoxDriver();
			driver.get("https://orion.ust-global.com");
			
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userid")));
					
			Login_OR OR = new Login_OR(driver);
			OR.login(username,password);
						
			Assert.assertEquals(driver.findElement(By.id("login_error")).getText(), "Your User ID and/or Password are invalid.");
			driver.close();
		
		}catch(Throwable t) 
			{	
			executionStatus = "Fail";
			f= t.toString(); 
			//System.out.println(f);
			System.out.println(t.getMessage());
			Applog.debug("Testcase FAILED for the data set "+count);
			throw new Error("failure",t);
			}
		
		}
		
	@AfterMethod
	public void Aftermethod() throws IOException
	{	
		//driver.close();
		xls = xlsObjectIntialize("TestData");
		//clear failure Reason
		TestUtil.reportfailureReason(xls,"Test_data_Invalid", count+2,"");
		
		if (executionStatus.equalsIgnoreCase("Fail"))
		{
			TestUtil.reportfailureReason(xls,"Test_data_Invalid", count+2,f);
		}
			
		TestUtil.reportDataSetResult(xls,"Test_data_Invalid", count+2,executionStatus);
		testcaseStatus = TestUtil.testcasestatus(executionStatus,testcaseStatus);

		executionStatus = "Pass";
	}
	
	@AfterTest
	public void AfterTest() throws IOException
	{
		xls = xlsObjectIntialize("Testsuite_A");
		int rownum = TestUtil.getrownum(xls,"Testcases",this.getClass().getSimpleName());
		TestUtil.reportDataSetResult(xls,"Testcases",rownum,testcaseStatus);
		Applog.debug("Execution completed for the testcase "+this.getClass().getSimpleName());
	}
}
		
		//driver.navigate().refresh();s
