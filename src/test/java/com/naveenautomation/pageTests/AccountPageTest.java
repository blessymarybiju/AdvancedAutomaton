package com.naveenautomation.pageTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.naveenautomation.base.TestBase;
import com.naveenautomation.pages.AccountPage;
import com.naveenautomation.pages.ChangePasswordPage;
import com.naveenautomation.pages.ConsumerSideNavigationBar;
import com.naveenautomation.pages.DownloadsPage;
import com.naveenautomation.pages.EditPage;
import com.naveenautomation.pages.LoginPage;
import com.naveenautomation.pages.NewsLetterSubscriptionPage;
import com.naveenautomation.pages.ProductReturnsPage;
import com.naveenautomation.pages.RecurringPayments;
import com.naveenautomation.pages.SideNavBar;
import com.naveenautomation.pages.Transactions;
import com.naveenautomation.pages.WishListPage;
import com.naveenautomation.utils.ExcelUtils;

public class AccountPageTest extends TestBase {

	private LoginPage loginPage;
	private AccountPage accountPage;
	private EditPage editPage;
	private ChangePasswordPage changePasswordPage;
	private NewsLetterSubscriptionPage subscriptionPage;
	private ProductReturnsPage returnsPage;
	private WishListPage wishListPage;
	private Transactions transactions;
	private RecurringPayments recurringPayments;
	private DownloadsPage downloadsPage;

	@BeforeMethod
	public void launch() {
		intialisation();
		loginPage = new LoginPage(wd, false);
	}

	@Test(dataProvider = "UserCredentials")
	public void validateLoginWithMultipleCredentials(String useName, String password) {
		accountPage = loginPage.SubmitLogin(useName, password);
		Assert.assertEquals(accountPage.getMyAccountText(), "My Account", "User not logged in");
	}

	@DataProvider(name = "UserCredentials")
	public Object[][] getDataFromExcelSheet() throws Exception {
		String fileName = "C:\\Users\\Dijish\\Desktop\\LoginNaveen.xlsx";
		String sheetName = "Sheet1";
		int rowCount = ExcelUtils.getRowCount(fileName, sheetName);
		int cellCount = ExcelUtils.getCellCount(fileName, sheetName, rowCount);
		String[][] virtualSheet = new String[rowCount][cellCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < cellCount; j++) {
				virtualSheet[i - 1][j] = ExcelUtils.getCellData(fileName, sheetName, i, j);
			}
		}
		return virtualSheet;
	}

	/* Test for validating whether user can update personal Info */
	@Test
	public void validateUserCanUpdatePersonalInfo() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		//editPage = accountPage.clickEditInfoLink();
		editPage = (EditPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.EDIT_ACCOUNT);
		editPage.enterFName("Nav");
		accountPage = editPage.clickSubmitBtn();
		Assert.assertEquals(accountPage.getSuccessMessageForEditInfo(),
				"Success: Your account has been successfully updated.", "Info not updated");
	}

	/* Test for validating whether user can login with valid credentials */
	@Test
	public void validateUserCanLoginWithValidCredentials() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		Assert.assertEquals(accountPage.getMyAccountText(), "My Account", "User not logged in");
	}

	/* Test for validating whether user can update password */
	@Test
	public void validateUserCanUpdatePassword() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		//changePasswordPage = accountPage.clickChangePwdLink();
		changePasswordPage=(ChangePasswordPage) new SideNavBar(wd, false)
		.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.CHANGE_PASSWORD);
		accountPage = changePasswordPage.clickSubmitBtn("abcd", "abcd");
		Assert.assertEquals(accountPage.getSuccessMessageForPwdChange(),
				"Success: Your password has been successfully updated.", "Password not updated");
	}

	/* Test for validating whether user can subscribe to newsletter */
	@Test
	public void validateUserCanSubscribeNewsLetter() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		//subscriptionPage = accountPage.clickNewsLetterLink();
		subscriptionPage=(NewsLetterSubscriptionPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.NEWSLETTER_SUBSCRIPTION);
		subscriptionPage.clickYesOnRadioBtn();
		accountPage = subscriptionPage.clickSubmitBtn();
		Assert.assertEquals(accountPage.getSuccessMessageForNewsLetter(),
				"Success: Your newsletter subscription has been successfully updated!",
				"Your subscription is not updated");

	}

	@Test
	public void validateProductReturnMessage() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		returnsPage = (ProductReturnsPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.RETURN);
		Assert.assertEquals(returnsPage.getMessageForProductReturn(), "You have not made any previous returns!",
				"You have product returns");
		accountPage = returnsPage.clickContinueBtn();
		Assert.assertEquals(accountPage.getMyAccountText(), "My Account", "User is failed to reach My Account Page");
	}

	@Test
	public void validateWishListMessage() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		wishListPage = (WishListPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.WISH_LIST);
		Assert.assertEquals(wishListPage.getMessageForWishList(), "Your wish list is empty.",
				"You have items in wish list");
		accountPage = wishListPage.clickContinueBtn();
		Assert.assertEquals(accountPage.getMyAccountText(), "My Account", "User is failed to reach My Account Page");
	}

	@Test
	public void validateAccountDownloadsMessage() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		downloadsPage = (DownloadsPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.DOWNLOADS);
		Assert.assertEquals(downloadsPage.getMessageForDownloadPage(),
				"You have not made any previous downloadable orders!", "You have items in downloads");
		accountPage = downloadsPage.clickContinueBtn();
		Assert.assertEquals(accountPage.getMyAccountText(), "My Account", "User is failed to reach My Account Page");
	}

	@Test
	public void validatePaymentsMessage() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		recurringPayments = (RecurringPayments) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.PAYMENTS);
		Assert.assertEquals(recurringPayments.getMessageForPaymentsPage(), "No recurring payments found!",
				"Recurring payments found!");
		accountPage = recurringPayments.clickContinueBtn();
		Assert.assertEquals(accountPage.getMyAccountText(), "My Account", "User is failed to reach My Account Page");
	}

	@Test
	public void validateTransactionsPageMessage() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		transactions = (Transactions) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.TRANSACTIONS);
		Assert.assertEquals(transactions.getMessageForTransactions(), "Your current balance is: $0.00.",
				"Your balance is not zero");
		accountPage = transactions.clickContinueBtn();
		Assert.assertEquals(accountPage.getMyAccountText(), "My Account", "User is failed to reach My Account Page");
	}

	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}
}
