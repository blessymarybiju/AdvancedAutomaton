package com.naveenautomation.pageTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.naveenautomation.base.TestBase;
import com.naveenautomation.pages.AccountPage;
import com.naveenautomation.pages.AddressAddPage;
import com.naveenautomation.pages.AddressPage;
import com.naveenautomation.pages.ConsumerSideNavigationBar;
import com.naveenautomation.pages.EditAddressPage;
import com.naveenautomation.pages.LoginPage;
import com.naveenautomation.pages.SideNavBar;

public class AddressBookTest extends TestBase {

	private LoginPage loginPage;
	private AddressPage addressPage;
	private AccountPage accountPage;
	private EditAddressPage editAddressPage;
	private AddressAddPage addressAddPage;

	@BeforeMethod
	public void launch() {
		intialisation();
		loginPage = new LoginPage(wd, false);
	}

	@Test
	public void validateUserCanUpdateAddress() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		addressPage = (AddressPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.ADDRESS_BOOK);
		editAddressPage = addressPage.clickEditAddressBtn();
		editAddressPage.enterFName("Naveen");
		addressPage = editAddressPage.clickSubmitBtn();
		Assert.assertEquals(addressPage.getSuccessMessageForEditAddessInfo(),
				"Your address has been successfully updated", "Info not updated");
	}

//	@Test
//	public void validateUserCanAddAddress() {
//		accountPage = loginPage.SubmitLogin(emailId, password);
//		addressPage = (AddressPage) new SideNavBar(wd, false)
//				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.ADDRESS_BOOK);
//		addressAddPage=addressPage.clickAddAddressBtn();
//		addressPage = addressAddPage.clickSubmitButton();
//		Assert.assertEquals(addressPage.getSuccessMessageForEditAddessInfo(),
//				"Your address has been successfully added", "Address not added");
//	}

	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}
}
