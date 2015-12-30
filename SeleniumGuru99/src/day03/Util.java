package day03;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class Util {

	/**
	 * @param args
	 */
		public static final String username = "mngr26059";
		public static final String password = "EzatygA";
		
	    public static final String FILE_PATH = "D:\\data2.xls"; 
	    public static final String SHEET_NAME = "testsheet";
	    public static final String TABLE_NAME = "TestData"; 

	    public static final int WAIT_TIME = 30; 
					
	    public static final String BASE_URL = "http://www.demo.guru99.com/";

	    public static final String EXPECT_TITLE = "Guru99 Bank Manager HomePage";
	    public static final String EXPECT_ERROR = "User or Password is not valid";

		public static final String FIREFOX_PATH = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
		
	    public static String[][] getDataFromExcel(String xlFilePath,String sheetName, String tableName) throws Exception {
			
	    	String[][] tabArray = null;
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);

			int startRow, startCol, endRow, endCol, ci, cj;
			Cell tableStart = sheet.findCell(tableName);
			startRow = tableStart.getRow();
			startCol = tableStart.getColumn();
			Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1,100, 64000, false);
			endRow = tableEnd.getRow();
			endCol = tableEnd.getColumn();
			tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
			ci = 0;

			for (int i = startRow + 1; i < endRow; i++, ci++) 
			{
				cj = 0;
				for (int j = startCol + 1; j < endCol; j++, cj++) 
				{
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}

			return (tabArray);
		}

}

