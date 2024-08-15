package driverFactory;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript
{
	public static WebDriver driver;
	static String inputpath="./FileInput/Controller.xlsx";
	static String outputpath = "./FileOutput/HybridResults.xlsx";
	static String tcsheet="MasterTestCases";
	static String TCModule ="";
	ExtentReports reports;
	ExtentTest Logger;

	public void startTest() throws Throwable 
	{
		String Module_status ="";
		String Module_new="";

		//create object for excelfileutil
		//ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//iterate all the rows in the tc sheet

		for (int i=1;i<=xl.rowCount(tcsheet);i++) 
		{
			if(xl.getCellData(tcsheet, i, 2).equalsIgnoreCase("Y"))
				//define path for html reports
				
				/*reports =new ExtentReports("./target/ExtentReports/"+TCModule+FunctionLibrary.generateDate()+".html");
			java.util.logging.Logger.assignAuthor("Ranga");
		*/	{
				//store corresponding sheetname or testcases into TCmodule 
				TCModule =xl.getCellData(tcsheet, i, 1);
				//iterate corresponding sheet
				for (int j=1; j<=xl.rowCount(TCModule);j++)
				{
					//read each cell from TCModule sheet
					String Discripton =xl.getCellData(TCModule, j, 0);
					String Object_Type=xl.getCellData(TCModule, j, 1);
					String Ltype = xl.getCellData(TCModule, j, 2);
					String Lvalue=xl.getCellData(TCModule, j, 3);
					String TData=xl.getCellData(TCModule, j, 4);
					try {
						if(Object_Type.equalsIgnoreCase("startBrowser"))
						{
							driver=FunctionLibrary.startBrowser();
							
						}if(Object_Type.equalsIgnoreCase("openUrl"))
						{
							FunctionLibrary.openUrl();
						}if(Object_Type.equalsIgnoreCase("waitForElement "))
						{
							FunctionLibrary.waitForElement(Ltype, Lvalue, TData);
						}if(Object_Type.equalsIgnoreCase("typeAction"))
								{
							FunctionLibrary.typeAction(Ltype, Lvalue, TData);
								}
						if(Object_Type.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickAction(Ltype, Lvalue);
						}
						if(Object_Type.equalsIgnoreCase("validateTitle"))
						{
							FunctionLibrary.validateTitle(TData);
						}
						if(Object_Type.equalsIgnoreCase("closeBrowser"))
						{
							FunctionLibrary.closeBrowser();
						}
						//write a spass into status cell TCModule
						xl.setCellData(TCModule, j, 5, "Pass", outputpath);
						Module_status="True";
					}catch(Exception e)
					{
						System.out.println(e.getMessage());
						//write as Fail into status cell TCModule
						xl.setCellData(TCModule, j, 5, "Fail", outputpath);
						Module_new="False";
					}
					if(Module_status.equalsIgnoreCase("True"))
					{
						//write as pass into TCSheet
						xl.setCellData(tcsheet, i, 3, "Pass", outputpath);
					}
					if(Module_new.equalsIgnoreCase("False"))
					{
						//write as Fail into TCSheet
					xl.setCellData(tcsheet, i, 3, "Fail", outputpath);
					}
			}
			{
			//write as blocked into status cell in TCSheet
				xl.setCellData(tcsheet, i, 3, "Blocked", outputpath);
			
		}
			}	
			
		}
	}	
}
