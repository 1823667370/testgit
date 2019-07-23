package com.lz.libarary.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel2010Operation {
	


    public void readExcel(String filePath,String fileName,String sheetName) throws IOException{

    //Create a object of File class to open xlsx file

    File file =    new File(filePath+"\\"+fileName);

    //Create an object of FileInputStream class to read excel file

    FileInputStream inputStream = new FileInputStream(file);

    Workbook guru99Workbook = null;

    //Find the file extension by spliting file name in substring and getting only extension name

    String fileExtensionName = fileName.substring(fileName.indexOf("."));

    //Check condition if the file is xlsx file

    if(fileExtensionName.equals(".xlsx")){


    guru99Workbook = new XSSFWorkbook(inputStream);

    }

    //Check condition if the file is xls file

    else if(fileExtensionName.equals(".xls")){

        //If it is xls file then create object of XSSFWorkbook class

        guru99Workbook = new HSSFWorkbook(inputStream);

    }

    //Read sheet inside the workbook by its name

    Sheet guru99Sheet = guru99Workbook.getSheet(sheetName);

    //Find number of rows in excel file

    int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();

    //Create a loop over all the rows of excel file to read it

    for (int i = 0; i < rowCount+1; i++) {

        Row row = guru99Sheet.getRow(i);

        //Create a loop to print cell values in a row

        for (int j = 0; j < row.getLastCellNum(); j++) {

            //Print excel data in console
      
            try {
				System.out.print(row.getCell(j).getStringCellValue()+"|| ");
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				double passwordDouble =row.getCell(j).getNumericCellValue();
				System.out.print(String.valueOf((int)passwordDouble)+"|| ");
			}
            
            
        }

        System.out.println();

    }

//    for (int numSheet = 0; numSheet < guru99Workbook.getNumberOfSheets(); numSheet++) {
//               HSSFSheet hssfSheet = (HSSFSheet) guru99Workbook.getSheetAt(numSheet);
//    	             if (hssfSheet == null) {
//    	                continue;
//    	             }
//    	             // Read the Row
//    	            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
//    	                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
//    	                if (hssfRow != null) {
//    	                	 System.out.print(hssfRow.getCell(0).getStringCellValue()+"|| ");
//    	                }
//    	            }
//    	        }
//
    }

    

    //Main function is calling readExcel function to read data from excel file

    public static void main(String[] s) throws IOException{

    //Create a object of ReadGuru99ExcelFile class

    	Excel2010Operation objExcelFile = new Excel2010Operation();

    //Prepare the path of excel file

    String filePath = "D:\\works\\lzmh";

    //Call read file method of the class to read data

    objExcelFile.readExcel(filePath,"dataLibrary.xlsx","dataTest");

    }
	
}

