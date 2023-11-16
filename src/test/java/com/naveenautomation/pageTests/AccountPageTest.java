package com.naveenautomation.pageTests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.naveenautomation.base.TestBase;
import com.naveenautomation.pages.AccountPage;
import com.naveenautomation.pages.AccountSucessPage;
import com.naveenautomation.pages.ChangePasswordPage;
import com.naveenautomation.pages.ConsumerSideNavigationBar;
import com.naveenautomation.pages.DownloadsPage;
import com.naveenautomation.pages.EditAffiliatePage;
import com.naveenautomation.pages.EditPage;
import com.naveenautomation.pages.LoginPage;
import com.naveenautomation.pages.NewsLetterSubscriptionPage;
import com.naveenautomation.pages.OrderHistoryPage;
import com.naveenautomation.pages.OrderInfoPage;
import com.naveenautomation.pages.ProductReturnsPage;
import com.naveenautomation.pages.RecurringPayments;
import com.naveenautomation.pages.RegisterPage;
import com.naveenautomation.pages.RewardPage;
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
	private RegisterPage registerPage;
	private AccountSucessPage accountSucessPage;
	private OrderHistoryPage orderHistoryPage;
	private RewardPage rewardPage;
	private EditAffiliatePage editAffiliatePage;
	private OrderInfoPage orderInfoPage;

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
	public void validateUserCanUpdateFirstName() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		// editPage = accountPage.clickEditInfoLink();
		editPage = (EditPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.EDIT_ACCOUNT);
		editPage.enterFName("Nav");
		accountPage = editPage.clickSubmitBtn();
		Assert.assertEquals(accountPage.getSuccessMessageForEditInfo(),
				"Success: Your account has been successfully updated.", "Info not updated");
	}

	@Test
	public void validateUserCanUpdateLastName() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		// editPage = accountPage.clickEditInfoLink();
		editPage = (EditPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.EDIT_ACCOUNT);
		editPage.enterLName("Neil");
		accountPage = editPage.clickSubmitBtn();
		Assert.assertEquals(accountPage.getSuccessMessageForEditInfo(),
				"Success: Your account has been successfully updated.", "Info not updated");
	}

	@Test
	public void validateUserCanUpdateEmail() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		// editPage = accountPage.clickEditInfoLink();
		editPage = (EditPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.EDIT_ACCOUNT);
		editPage.enterEmail("navenncxe@gmail.com");
		accountPage = editPage.clickSubmitBtn();
		Assert.assertEquals(accountPage.getSuccessMessageForEditInfo(),
				"Success: Your account has been successfully updated.", "Info not updated");
	}

	@Test
	public void validateUserCanUpdateNumber() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		// editPage = accountPage.clickEditInfoLink();
		editPage = (EditPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.EDIT_ACCOUNT);
		editPage.enterTelephone("9876543210");
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

	@Test
	public void validateUserCanRegisterNewAccount() {
		registerPage = loginPage.clickContinueRegisterButton();
		accountSucessPage = registerPage.clickSubmitButton();
		accountPage = accountSucessPage.clickContinueBtn();
		Assert.assertEquals(accountPage.getMyAccountText(), "My Account", "User registration failed");
	}

	/* Test for validating whether user can update password */
	@Test
	public void validateUserCanUpdatePassword() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		// changePasswordPage = accountPage.clickChangePwdLink();
		changePasswordPage = (ChangePasswordPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.CHANGE_PASSWORD);
		accountPage = changePasswordPage.clickSubmitBtn("abcd", "abcd");
		Assert.assertEquals(accountPage.getSuccessMessageForPwdChange(),
				"Success: Your password has been successfully updated.", "Password not updated");
	}

	/* Test for validating whether user can subscribe to newsletter */
	@Test
	public void validateUserCanSubscribeNewsLetter() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		// subscriptionPage = accountPage.clickNewsLetterLink();
		subscriptionPage = (NewsLetterSubscriptionPage) new SideNavBar(wd, false)
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
		Assert.assertEquals(returnsPage.getMessageForProductReturn(), "Product Returns",
				"You do not have any product returns");
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
	public void validateUserAbleToViewOrderHistory() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		orderHistoryPage = (OrderHistoryPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.ORDER_HISTORY);
		Assert.assertEquals(orderHistoryPage.getBannerForOdrerHistoryPage(), "Order History",
				"You are in the wrong page!!!");
		accountPage = orderHistoryPage.clickContinueBtn();
		Assert.assertEquals(accountPage.getMyAccountText(), "My Account", "User is failed to reach My Account Page");
	}

	@Test
	public void validateUserAbleToViewRewardPoint() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		rewardPage = (RewardPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.REWARD_POINTS);
		Assert.assertEquals(rewardPage.getMessageForRewardPoints(), "Your total number of reward points is: 0.",
				"You are in the wrong page!!!");
		accountPage = rewardPage.clickContinueBtn();
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

	@Test
	public void validateUserCanUpdateAffiliateInfo() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		editAffiliatePage = accountPage.clickEditAffiliateLink();
		accountPage = editAffiliatePage.clickSubmitBtn("Naveen");
		Assert.assertEquals(accountPage.getSuccessMessageForEditAffiliateInfo(),
				"Success: Your account has been successfully updated.", "Info not updated");
	}

	@Test
	public void validateUserAbleToViewPreviousOrderDetails() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		orderHistoryPage = (OrderHistoryPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.ORDER_HISTORY);
		Assert.assertEquals(orderHistoryPage.getBannerForOdrerHistoryPage(), "Order History",
				"You are in the wrong page!!!");
		orderInfoPage = orderHistoryPage.clickViewBtn();
		Assert.assertEquals(orderInfoPage.getHeaderForOdrerHistory(), "Order Details", "You are in the wrong page!!!");
		orderHistoryPage = orderInfoPage.clickContinueBtn();
		accountPage = orderHistoryPage.clickContinueBtn();
		Assert.assertEquals(accountPage.getMyAccountText(), "My Account", "User is failed to reach My Account Page");
	}

	@Test
	public void validateUserCanUnsubscribeNewsLetter() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		// subscriptionPage = accountPage.clickNewsLetterLink();
		subscriptionPage = (NewsLetterSubscriptionPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.NEWSLETTER_SUBSCRIPTION);
		subscriptionPage.clickNoOnRadioBtn();
		accountPage = subscriptionPage.clickSubmitBtn();
		Assert.assertEquals(accountPage.getSuccessMessageForNewsLetter(),
				"Success: Your newsletter subscription has been successfully updated!",
				"Your subscription is not updated");

	}

	@Test
	public void validateUserCannotUpdatePasswordIfPwdMismatch() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		changePasswordPage = (ChangePasswordPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.CHANGE_PASSWORD);
		changePasswordPage.clickSubmitBtnWithPwdMismatch("abcd", "aaaa");
		Assert.assertEquals(changePasswordPage.getAlertForPwdMismatch(),
				"Password confirmation does not match password!", "Password is updated");
		accountPage = changePasswordPage.clickBackBtn();
		Assert.assertEquals(accountPage.getMyAccountText(), "My Account", "Wrong Page");
	}

	@Test
	public void validateUserCannotUpdateInfoWithEmptyField() {
		accountPage = loginPage.SubmitLogin(emailId, password);
		editPage = (EditPage) new SideNavBar(wd, false)
				.OpenPageByClickOnSideNavBar(ConsumerSideNavigationBar.EDIT_ACCOUNT);
		editPage.enterFName("Naveen");
		editPage.enterTelephone("9876543210");
		editPage.clickSubmitBtnWithEmptyField();
		Assert.assertEquals(editPage.getAlertForEmptyLastName(), "Last Name must be between 1 and 32 characters!",
				"Personal Info is updated");
		accountPage = editPage.clickBackBtn();
		Assert.assertEquals(accountPage.getMyAccountText(), "My Account", "Wrong Page");
	}

	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}
}
