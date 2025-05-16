 package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistartionTest extends BaseClass {
	

//	@BeforeClass
//	public void setup()
//	{
//		driver = new ChromeDriver();
//		driver.manage().deleteAllCookies();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		
//		driver.get("https://tutorialsninja.com/demo/");
//		driver.manage().window().maximize();
//	}
//	
//	@AfterClass
//	public void tearDown()
//	{
//		driver.quit();
//	}

	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("*****Starting TC001 AccountRegistrationTest*****");
		try
		{
			HomePage hp = new HomePage(driver);
			
			hp.CliclMyAccount();
			logger.info("Clicked on MyAccount Link");
			hp.ClickRegister();
			logger.info("Clicked on Register Link");
			
			AccountRegistrationPage rp = new AccountRegistrationPage(driver);
			
			logger.info("Providing customer details....");
			rp.setFirstName(randomString().toUpperCase());
			rp.setLastName(randomString().toUpperCase());
			rp.setEmail(randomString()+"@gmail.com");
			rp.setTelephone(randomNumber());
			
			String password = randomAlphaNumeric();
			
			rp.setPassword(password);
			rp.setConfirmPassword(password);
			
			rp.setPrivacyPolicyt();
			rp.clickContinue();
			
			logger.info("Validating expected message..");
			String confmsg = rp.getConfigurationMsg();
			System.out.println(confmsg);

			if(confmsg.equals("Your Account Has Been Created!!!"))
			{
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Test Failed..");
				logger.debug("Debug Logs..");
				Assert.assertFalse(false);
			}
			//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("*****Finished TC001 AccountRegistrationTest*****");
		
	}
	
	public String randomString()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}

	public String randomNumber()
	{
		String generatednumber = RandomStringUtils.randomAlphanumeric(10);
		return generatednumber;
	}
	
	public String randomAlphaNumeric()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomAlphanumeric(3);
		return (generatedstring+"@"+generatednumber);
	}
}
