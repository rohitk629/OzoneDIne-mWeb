package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class StaticDataProvider {

	//Data provider returns each row as one string object
	@DataProvider(name = "ExcelData")
	public static String[][] getExcelData(String sheetName) throws IOException{			
			String[][] formData = ExcelUtility.readData(sheetName);
			return formData;
	}
}