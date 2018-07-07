package com.testproject.util;

public class TestUtil {
//*************************************************************************************************************
	//To find the runmode of suite/testcase
	public static boolean isRunnable(Xls_Reader xls,String sheetname,String testName)
	{	
		boolean isExecutable = false;
		for(int i=2;i<=xls.getRowCount(sheetname);i++)
			{
			if(xls.getCellData(sheetname, 1, i).equalsIgnoreCase(testName))
				{	
				if(xls.getCellData(sheetname, "Run Mode", i).equalsIgnoreCase("Y"))
					{					
					isExecutable = true;break;
					}
					else
					isExecutable = false; 						
				}
			}	
		
		xls =null;
		return isExecutable;
	}
	
//*************************************************************************************************************	
	//To get test data for testcase
	public static Object[][] getdata(Xls_Reader xls,String Sheetname)
	{
		if(!xls.isSheetExist(Sheetname))
		{
			xls =null;
			return new Object[1][0];
		}
		//get row number of run mode
		int colno = xls.getrownum(Sheetname, "Run Mode");
		//System.out.println("coll NO "+colno);
		
		int row = xls.getRowCount(Sheetname);
		//int col = xls.getColumnCount(Sheetname);
				
		/*Object[][] data = new Object[row-1][col-3];
	
		for(int rownum=2;rownum<=row;rownum++)
			for(int colnum=1;colnum<=col-3;colnum++)
			{
				data[rownum-2][colnum-1] = xls.getCellData(Sheetname, colnum, rownum);
								
			}
		*/
		
		Object[][] data = new Object[row-1][colno-1];
		
		for(int rownum=2;rownum<=row;rownum++)
			for(int colnum=1;colnum<=colno-1;colnum++)
			{
				data[rownum-2][colnum-1] = xls.getCellData(Sheetname, colnum, rownum);
								
			}
		
			return data;
		}
//*************************************************************************************************************	
	// To write result into sheet
	public static void reportDataSetResult(Xls_Reader xls,String testCaseName, int rowNum, String result)
	{
		xls.setCellData(testCaseName,"Result", rowNum, result);
	}
//*************************************************************************************************************	
	// To write failure reason into sheet
		public static void reportfailureReason(Xls_Reader xls,String testCaseName, int rowNum, String result)
		{
			xls.setCellData(testCaseName,"Failure Reason", rowNum, result);
		}
//*************************************************************************************************************	
		
	// To get run modes of dataset
	public static String[] dataSetRunMode(Xls_Reader xls,String sheetName )
	{
		if(!xls.isSheetExist(sheetName))
		return null;
		
		String[] runmode = new String[xls.getRowCount(sheetName)-1];
		for(int i=2 ; i<=xls.getRowCount(sheetName);i++)
		{
			runmode [i-2]= xls.getCellData(sheetName, "Run Mode", i);
		}
		
		return runmode;
	}

//*************************************************************************************************************	
	public static int getrownum(Xls_Reader xls,String sheetName, String testcasename)
	{	
		for( int i=2 ; i<=xls.getRowCount(sheetName);i++)
		{
			String celldata= xls.getCellData(sheetName, "Test Case Name", i);
			if(celldata.equalsIgnoreCase(testcasename))
			return i ;
		}
		return -1;
	}
//*************************************************************************************************************	

	public static String testcasestatus(String executionStatus, String testcaseStatus )
	{ 
		int Noskip =0;
		if (executionStatus.equalsIgnoreCase("Fail"))
		{
			testcaseStatus = "Fail";
		}
		else if ((executionStatus.equalsIgnoreCase("Pass")) && !(testcaseStatus.equalsIgnoreCase("Fail")))
		{
			testcaseStatus = "Pass";
			 Noskip = 1;
		}
		else if ((executionStatus.equalsIgnoreCase("Skip")) && !(Noskip == 0) && !(testcaseStatus.equalsIgnoreCase("Fail")))
		{
			testcaseStatus = "Skip";
		}
		
		return testcaseStatus;
		}
//*************************************************************************************************************	
	
}
	
