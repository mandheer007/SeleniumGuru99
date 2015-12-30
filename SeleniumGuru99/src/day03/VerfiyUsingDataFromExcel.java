package day03;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class VerfiyUsingDataFromExcel {

	/**
	 * @param args
	 */
	

    static WebDriver driver; 
    private static String baseUrl; 

    public static void setUp() throws Exception
    {
		File pathToBinary = new File(Util.FIREFOX_PATH);
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);
		baseUrl = Util.BASE_URL;
		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
		driver.get(baseUrl + "/V4/");
    }
	  
    public static void main(String[] args) throws Exception {
    	
        String[][] testData = Util.getDataFromExcel(Util.FILE_PATH,Util.SHEET_NAME, Util.TABLE_NAME);
    	String username, password;
    	String actualTitle;
    	String actualBoxtitle;

    	
    	for (int i = 0; i < testData.length; i++) 
    	{
    	    username = testData[i][0]; 
    	    password = testData[i][1]; 
    	    
    	    setUp();
    	    // Enter username
    	    driver.findElement(By.name("uid")).clear();
    	    driver.findElement(By.name("uid")).sendKeys(username);

    	    // Enter Password
    	    driver.findElement(By.name("password")).clear();
    	    driver.findElement(By.name("password")).sendKeys(password);

    	    // Click Login
    	    driver.findElement(By.name("btnLogin")).click();
           
            try
            { 
    	    
    	       	Alert alt = driver.switchTo().alert();
    			actualBoxtitle = alt.getText();
    			System.out.println(actualBoxtitle);
    			alt.accept();
    			if (actualBoxtitle.contains(Util.EXPECT_ERROR)) 
    			{ 
    			    System.out.println("Test case SS[" + i + "]: Passed"); 
    			} 
    			else 
    			{
    			    System.out.println("Test case SS[" + i + "]: Failed");
    			}
    		}    
    	    catch (NoAlertPresentException Ex)
    	    { 
    	    	actualTitle = driver.getTitle();
    	    	System.out.println(actualTitle);
    			
    			if (actualTitle.contains(Util.EXPECT_TITLE)) 
    			{
    			    System.out.println("Test case SS[" + i + "]: Passed");
    			} 
    			else 
    			{
    			    System.out.println("Test case SS[" + i + "]: Failed");
    			}
    			
            } 
    	    driver.close();
    	 } 
           
    		
    }
    	
  }