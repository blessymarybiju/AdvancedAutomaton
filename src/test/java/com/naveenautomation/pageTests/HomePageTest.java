package com.naveenautomation.pageTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.naveenautomation.base.TestBase;
import com.naveenautomation.pages.AccountPage;
import com.naveenautomation.pages.AddReturnProduct;
import com.naveenautomation.pages.ApplePage;
import com.naveenautomation.pages.ConsumerSideNavigationBar;
import com.naveenautomation.pages.ContactPage;
import com.naveenautomation.pages.HomePage;
import com.naveenautomation.pages.LoginPage;
import com.naveenautomation.pages.LogoutPage;
import com.naveenautomation.pages.ManufactorsPage;
import com.naveenautomation.pages.ProductReturnsSuccessPage;
import com.naveenautomation.pages.ShoppingCartPage;
import com.naveenautomation.pages.SideNavBar;

public class HomePageTest extends TestBase {

	private LoginPage loginPage;
	private AccountPage accountPage;
	private ShoppingCartPage cartPage;
	private ContactPage contactPage;
	private LogoutPage logoutPage;
	private HomePage homePage;
	private AddReturnProduct returnProduct;
	private ProductReturnsSuccessPage returnsSuccessPage;
	private ManufactorsPage manufactorsPage;
	private ApplePage applePage;

	@BeforeMethod
	public void launch() {
		intialisation();
		loginPage = new LoginPage(wd,false);
	}

	@Test
	public void validateUserCanLogout() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		logoutPage = (LogoutPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.LOGOUT);
		Assert.assertEquals(logoutPage.getSuccessMessageForLogout(), "Account Logout", "User is not logged out");
		homePage = logoutPage.clickContinueBtn();
		Assert.assertEquals(homePage.getHomePageTitle(), "Your Store", "User is not on the home page");

	}

	@Test
	public void validateEmptyCartMessage() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		cartPage = accountPage.clickCartLink();
		Assert.assertEquals(cartPage.getMessageForShoppingCartPage(), "Your shopping cart is empty!",
				"You have products in cart");
		homePage = cartPage.clickContinueBtn();
		Assert.assertEquals(homePage.getHomePageTitle(), "Your Store", "User is not on the home page");
	}

	@Test
	public void validateContactUsEnquiryMessage() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		contactPage = accountPage.clickContactUsLink();
		contactPage.clickSubmitBtn();
		Assert.assertEquals(contactPage.getMessageForShoppingCartPage(),
				"Your enquiry has been successfully sent to the store owner!", "Your enquiry is not send");
		homePage = contactPage.clickContinueBtn();
		Assert.assertEquals(homePage.getHomePageTitle(), "Your Store", "User is not on the home page");
	}
	
	@Test
	public void validateUserCanReturnProducts() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		 returnProduct= accountPage.clickReturnLink();
		returnsSuccessPage=returnProduct.clickSubmitButton();
		Assert.assertEquals(returnsSuccessPage.getSuccessMessageForProductReturn(),
				"Thank you for submitting your return request. Your request has been sent to the relevant department for processing.", "Your request is not send");
		homePage = returnsSuccessPage.clickContinueBtn();
		Assert.assertEquals(homePage.getHomePageTitle(), "Your Store", "User is not on the home page");
	}
	
	@Test
	public void validateUserCanSortProductByBrand() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		 manufactorsPage= accountPage.clickOnBrandLink();
		applePage=manufactorsPage.clickOnAppleLink();
		Assert.assertEquals(applePage.getApplePageTitle(),
				"Apple", "You are on wrong page");
		homePage = applePage.clickNaveenLogo();
		Assert.assertEquals(homePage.getHomePageTitle(), "Your Store", "User is not on the home page");
	}

	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}

}
