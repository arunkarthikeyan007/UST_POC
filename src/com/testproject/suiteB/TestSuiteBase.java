package com.testproject.suiteB;

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
		Applog.debug("Checking Run mode of Suite B");
		
		if(!TestUtil.isRunnable(xls, "Testsuites", "Testsuite_B"))
		{
			Applog.debug("Skipping--> Suite B");
			throw new SkipException("Run mode is NO | Skipping TestSuite B |");
		}
		
		Applog.debug("Executing Suite B........");
		Applog.debug("Loading Testsuite_B");
		xls = xlsObjectIntialize("Testsuite_B");
		}

@AfterSuite
public void SuiteExecution(){
	Applog.debug("Test Suite B Execution completed");
}

}
