package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.loginPage;
import testBase.BaseClass;


public class TC002_Login_Test extends BaseClass {
	
	@Test(groups= {"Sanity" , "Master"})
	public void verify_login()
	{
		logger.info("****Starting TC002)Login_Test****");
		
		try
		{
			//HomePage
			HomePage hp =  new HomePage(driver);
			hp.CliclMyAccount();
			hp.ClickLogin();
			
			//loginPage
			loginPage lp = new loginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			
			lp.clickLogin();
			
			//MyAccountPage
			MyAccountPage macc = new MyAccountPage(driver);
			boolean tragetPage = macc.isMyAccountPageExists();
			
			//Assert.assertEquals(tragetPage, true, "Login failed");
			Assert.assertTrue(tragetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("****Finished TC002)Login_Test****");
		
	}

}
