package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MyAccountPage;
import pageObjects.loginPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is valid -> login is success -> test pass -> logout
   Data is valid -> login failed -> test fail
   
   Data is invalid -> login is success -> test fail -> logout
   Data is invalid -> login failed -> test pass
 */

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven") //getting data providers from different class
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		logger.info("****Starting TC003_LoginDDT ****");
		
		//HomePage
		HomePage hp =  new HomePage(driver);
		hp.CliclMyAccount();
		hp.ClickLogin();
		
		//loginPage
		loginPage lp = new loginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		
		lp.clickLogin();
		
		//MyAccountPage
		MyAccountPage macc = new MyAccountPage(driver);
		boolean tragetPage = macc.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(tragetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(tragetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		logger.info("****Starting TC003_LoginDDT ****");
	}
}
