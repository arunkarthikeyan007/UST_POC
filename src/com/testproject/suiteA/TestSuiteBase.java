package com.testproject.suiteA;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import com.testproject.base.TestBase;
import com.testproject.util.TestUtil;


public class TestSuiteBase extends TestBase {
	@BeforeSuite
	public void checkSuiteSkip() throws IOException	
	{	  
		intialize();
		Applog.debug("Loading Testsuites sheet");
		xlsObjectIntialize("Testsuites");
		Applog.debug("Checking Run mode of Suite A");
		
		if(!TestUtil.isRunnable(xls, "Testsuites", "Testsuite_A"))
		{
			Applog.debug("Skipping--> Suite A");
			throw new SkipException("Run mode set to NO | Skipping TestSuite A |");
		}
				
		Applog.debug("Executing Suite A........");
		Applog.debug("Loading Testsuite_A");
		xls = xlsObjectIntialize("Testsuite_A");
		}

@AfterSuite
public void SuiteExecution(){
	Applog.debug("Test Suite A Execution completed");
}

}
