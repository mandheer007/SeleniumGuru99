package day02;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;

import day01.Util;

public class VerfiyLoginWithOuput {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public void ffProfile(String uname, String pass, String url, String ffpath, int iwait) 
	{
		FirefoxProfile profile = new FirefoxProfile();
		
		
		WebDriver driver = new FirefoxDriver(new FirefoxBinary(new File(ffpath)),profile);
		
		driver.get(url);

		String actualTittle = "Guru99 Bank Manager HomePage";

		//Thread.sleep(1000L);
		driver.manage().timeouts().implicitlyWait(iwait,TimeUnit.SECONDS);
		
		driver.findElement(By.name("uid")).sendKeys(uname);
		driver.findElement(By.name("password")).sendKeys(pass);
		driver.findElement(By.name("btnLogin")).click();

		String expectedTittle = driver.getTitle();

		System.out.println(expectedTittle);

		if(actualTittle.equals(expectedTittle))
		{
			System.out.println("Test case: Passed");
		}
		else
		{
			System.out.println("Test case: Failed");
		}	
		
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		Util utl = new Util();
		String uname = utl.username;
		String psswd = utl.password;
		String url = utl.baseUrl;
		String ffpath = utl.firfoxpath;
		int iwait = utl.impwait;

		VerfiyLoginWithOuput vf = new VerfiyLoginWithOuput();
		vf.ffProfile(uname, psswd, url, ffpath, iwait);
	
	}
}



