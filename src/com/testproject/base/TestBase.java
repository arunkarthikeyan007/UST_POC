package com.testproject.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.testproject.util.Xls_Reader;

public class TestBase {
	
	//public to private
	public static Properties CONFIG = null;
	public static FileInputStream  file = null;
	public static Xls_Reader xls = null;
	public static Logger Applog = null;
	public static Boolean isInitialize = false;
	public static String[] runMode = null;
	public static String executionStatus = null;
	public static String testsuiteStatus = null;
	public static String testcaseStatus = null;
	//public static WebDriver driver = null;
	
	public static void intialize() throws IOException 
	{	
		if(!isInitialize)
		{
		Applog = Logger.getLogger("devpinoyLogger");
		Applog.debug("Initializing........");
		Applog.debug("Loading Properties file...........");
		
		CONFIG = new Properties();
		file = new FileInputStream (System.getProperty("user.dir")+"\\src\\com\\testproject\\config\\config.properties");
		CONFIG.load(file);
		
		Applog.debug("Sucessfully Loaded Properties file");
		isInitialize = true;
		}
		}
		
	public static Xls_Reader xlsObjectIntialize(String xlsname) throws IOException
	{
		Applog.debug("Initializing xls object for the sheet "+xlsname);
		return xls = new Xls_Reader(System.getProperty("user.dir")+CONFIG.getProperty(xlsname));
	}
	}
