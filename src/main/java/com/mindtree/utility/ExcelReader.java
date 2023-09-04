package com.mindtree.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExcelReader {

	WebDriver driver;
	WebDriverWait wait;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell;
	String testData = "";
	// Import excel sheet.
	public File src = new File("C:\\Users\\Admin\\workspace\\testDataDrivenFramework\\src\\test\\resources\\ExcelSheet\\dataDriven_testData.xlsx");

	public String getCellValue(String testcaseTitle , int cellIndex) throws IOException
	{
		try
		{
			// Load the file.
			FileInputStream finput = new FileInputStream(src);
			// Load he workbook.
			workbook = new XSSFWorkbook(finput);
			// Load the sheet in which data is stored.
			sheet= workbook.getSheetAt(0);
			for(int i=1; i<= sheet.getLastRowNum(); i++)
			{
				//Get data for test cases base on Test Case Name 
				cell = sheet.getRow(i).getCell(0);
				//cell.setCellType(CellType.STRING);
				if(cell.getStringCellValue().equalsIgnoreCase(testcaseTitle))
				{
					XSSFCell cellData = sheet.getRow(i).getCell(cellIndex);
					//cellData.setCellType(CellType.STRING);
					testData = cellData.getStringCellValue();
				}
			}
			return testData;
		}
		catch (Exception e){
			return testData;
		}
	}

	//Write result in excel sheet 
	public void WriteResult(String testcaseTitle, Boolean result) throws IOException
	{
		/*
		 * FileInputStream finput;
		 * 
		 * try { // Load the file. finput = new FileInputStream(src); // Load he
		 * workbook. workbook = new XSSFWorkbook(finput); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); } // Load the workbook.
		 * workbook = new XSSFWorkbook(); // Load the sheet in which data is stored.
		 * sheet = workbook.getSheetAt(0);
		 */
		
		// Load the file.
		FileInputStream finput = new FileInputStream(src);
		// Load he workbook.
		workbook = new XSSFWorkbook(finput);
		// Load the sheet in which data is stored.
		sheet= workbook.getSheetAt(0);
		for(int i=1; i<=sheet.getLastRowNum(); i++)
		{
			//Get data for test cases base on Test Case Name 
			cell = sheet.getRow(i).getCell(0);
			//cell.setCellType(CellType.STRING);
			if(cell.getStringCellValue().equalsIgnoreCase(testcaseTitle))
			{
				if(result == true)
					sheet.getRow(i).createCell(1).setCellValue("Pass");
				else
					sheet.getRow(i).createCell(1).setCellValue("Fail");
			}
		}
		try {
			
			FileOutputStream foutput=new FileOutputStream(src);
			workbook.write(foutput);
			foutput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]) {
		
		ExcelReader excel = new ExcelReader();
		try {
			System.out.println(excel.getCellValue("verifyLogin",1));
			System.out.println(excel.getCellValue("verifyLogin",2));
			excel.WriteResult("verifyLogin",false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}