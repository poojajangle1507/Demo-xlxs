package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	Workbook wb;

	// write the constructor for reading excel path
	public ExcelFileUtil(String ExcelPath) throws Throwable 
	{
		FileInputStream fi = new FileInputStream(ExcelPath);
 
		wb = WorkbookFactory.create(fi);
		//XSSFWorkbook wp = new XSSFWorkbook(fi);
		
//		FileInputStream fi = new FileInputStream(ExcelPath);
//		wb = WorkbookFactory.create(fi);
		//XSSFSheet 
	}

//write the method for counting the no. of the row
	public int rowCount(String SheetName) 
	{
		return wb.getSheet(SheetName).getLastRowNum();

	}
    
//method for reading cell data
	public String getCellData(String SheetName, int row, int Column)
	{
		String data = " ";
		if (wb.getSheet(SheetName).getRow(row).getCell(Column).getCellType() == CellType.NUMERIC) {
			int celldata = (int) wb.getSheet(SheetName).getRow(row).getCell(Column).getNumericCellValue();
			data = String.valueOf(celldata);
		} else 
		{
			data = wb.getSheet(SheetName).getRow(row).getCell(Column).getStringCellValue();
		}
		return data;
	}

//method for writing 
	public void setCellData(String sheetName, int row, int column, String status, String writeExcel) throws Throwable 
	{
//get sheet from wb
		Sheet ws = wb.getSheet(sheetName);
		// get sheet from sheet
		Row rowNum = ws.getRow(row);
		// create cell
		Cell cell = rowNum.createCell(column);
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("Pass")) 
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);

		} else if (status.equalsIgnoreCase("Fail")) 
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);

		} else if ((status.equalsIgnoreCase("Blocked")))
			
			
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		
		FileOutputStream fo = new FileOutputStream(writeExcel);
		wb.write(fo);
		

	}

	public static void main(String[] args) throws Throwable 
	{
		ExcelFileUtil xl = new ExcelFileUtil("C:/Users/Panchal/Project/Hybrid_ERP/FileInput/sample.xlsx");
//count no.of rows in EMP
		int rc = xl.rowCount("EMP");
		System.out.println(rc);
		for (int i = 1; i <= rc; i++) {
			String fname = xl.getCellData("EMP", i, 0);
			String mname = xl.getCellData("EMP", i, 1);
			String lname = xl.getCellData("EMP", i, 2);
			String eid = xl.getCellData("EMP", i, 3);
			//String ed = xl.getCell("EMP", i, 4);
			//String md = xl.getCell("EMP", i, 5);
			System.out.println(fname + " " + mname + " " + lname + " " + eid);
			xl.setCellData("EMP", i, 5, "pass", "‪C:\\Users\\Panchal\\Project\\Hybrid_ERP\\FileOutput\\Resultout.xlsx");
		}
	}
}
