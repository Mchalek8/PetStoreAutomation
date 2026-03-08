package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "Data")
	public String[][] getAllData() throws Exception {
		
		String path = System.getProperty("user.dir") + "//testData//userNew.xlsx";
		XLUtility xlutil = new XLUtility(path);
		
		int totalRows = xlutil.getRowCount("Sheet1");
		int totalCols = xlutil.getCellCount("Sheet1", 1);
		
		String apidata[][] = new String[totalRows][totalCols];
		
		for(int i=1; i<=totalRows; i++) {
			for(int j=0; j<totalCols; j++) {
				apidata[i-1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		return apidata;
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws Exception {
		
		String path = System.getProperty("user.dir") + "//testData//userNew.xlsx";
		XLUtility xlutil = new XLUtility(path);
		
		int totalRows = xlutil.getRowCount("Sheet1");
		
		String apidata[] = new String[totalRows];
		
		for(int i=1; i<=totalRows; i++) {
			apidata[i-1] = xlutil.getCellData("Sheet1", i, 1);
		}
		
		return apidata;
	}

}
