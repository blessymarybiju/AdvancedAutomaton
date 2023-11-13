package com.naveenautomation.pages;

public enum ConsumerSideNavigationBar {
	EDIT_ACCOUNT("Edit Account", EditPage.class), 
	MY_ACCOUNT("My Account", AccountPage.class),
	CHANGE_PASSWORD("Password",ChangePasswordPage.class),
	WISH_LIST("Wish List", WishListPage.class),
	DOWNLOADS("Downloads", DownloadsPage.class),
	ADDRESS_BOOK("Address Book", AddressPage.class),
	ORDER_HISTORY("Order History",OrderHistoryPage.class),
	REWARD_POINTS("Reward Points",RewardPage.class),
	PAYMENTS("Recurring payments", RecurringPayments.class),
	RETURN("Returns", ProductReturnsPage.class),
	TRANSACTIONS("Transactions", Transactions.class),
	NEWSLETTER_SUBSCRIPTION("Newsletter",NewsLetterSubscriptionPage.class),
	LOGOUT("Logout",LogoutPage.class);

	private Class<? extends Page> _pageClass;
	private String item;

	ConsumerSideNavigationBar(String item, Class<? extends Page> pageClass) {
		this._pageClass = pageClass;
		this.item = item;
	}

	public Class<? extends Page> getpageClass() {
		return _pageClass;
	}

	public String getItem() {
		return item;
	}

	public static ConsumerSideNavigationBar getItemByText(String text) {
		ConsumerSideNavigationBar[] all = ConsumerSideNavigationBar.values();
		for (int i = 0; i < all.length; i++) {
			if (all[i].name().toLowerCase().equalsIgnoreCase(text)) {
				return all[i];
			}
		}
		return null;
	}

}
