package guru99bank;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginExample {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		Util utl = new Util();
		String uname = utl.username;
		String psswd = utl.password;
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.demo.guru99.com/V4/");
		driver.manage().timeouts().implicitlyWait(1000L,TimeUnit.SECONDS);
		driver.findElement(By.name("uid")).sendKeys(uname);
		driver.findElement(By.name("password")).sendKeys(psswd);
		driver.findElement(By.name("btnLogin")).click();
		
		//Thread.sleep(10000L);
		//driver.close();
		//driver.quit();
	
	}

}
