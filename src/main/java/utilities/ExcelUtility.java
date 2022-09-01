package utilities;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	@SuppressWarnings("resource")
	public static String[][] readData(String sheetName) throws IOException{
		String[][] inputData;
	
		InputStream input = ExcelUtility.class.getClassLoader().getResourceAsStream("TestData/InputData.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(input);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int totalRows = sheet.getLastRowNum()-sheet.getFirstRowNum();
		int totalCols = sheet.getRow(0).getPhysicalNumberOfCells();
		
		inputData = new String[totalRows][totalCols];
		//loop over row
		for(int i=0;i<totalRows;i++) {
			Row row = sheet.getRow(i+1);
			for(int j=0;j<totalCols;j++) {
				String data = new DataFormatter().formatCellValue(row.getCell(j));
				if(data!=null) {
//					System.out.println("inside");
					inputData[i][j] = data;
				}
			}
		}
	
		return inputData;
		
	}

}
