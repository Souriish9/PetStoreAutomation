package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {


		
		public String readExcel(int columnNumber) throws IOException {
				String filePath = System.getProperty("user.dir")+"\\testData\\UserData.xlsx";
				FileInputStream fileInputStream = new FileInputStream(filePath);	
				XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
				XSSFSheet sheet = workbook.getSheetAt(0);
				XSSFRow row = sheet.getRow(0);
				XSSFCell cell = row.getCell(columnNumber);
				String data = String.valueOf(cell);
				workbook.close();
				fileInputStream.close();
				return data;
			}
}
