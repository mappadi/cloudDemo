package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDUserSessionManagement {
	
	
	/***  Web  ***/
	public static By objLoginPageHeader = By.xpath("//div[@class='formHeader loginHeaderIn' and contains(text(), 'Login to ZEE5')]");
	public static By objDateOfBirthValue(String value) {
	  return By.xpath("((//div[@class='itemContent']/child::div[contains(text(),'"+value+"')]/following-sibling::*/child::*/child::*)[2]/child::*/child::*)[1]/child::span");
	}
	public static By objSubscriptionBannerInMyProfileSection = By.xpath("//*[@class='bannerTitle']");
	public static By objEpisodesTabInMyWatchlist = By.xpath("//div[contains(@class, 'noSelect tabMenuItem') and @id='episodes']");
	public static By objMoviesTabInMyWatchlist = By.xpath("//div[contains(@class, 'noSelect tabMenuItem') and @id='movies']");
	public static By objVideosTabInMyWatchlist = By.xpath("//div[contains(@class, 'noSelect tabMenuItem') and @id='videos']");
	public static By objcontentsInAllTheTabs = By.xpath("//h3[@class='cardTitle overflowEllipsis ']/child::a");
	public static By objNoActiveSubscriptionText = By.xpath("//div[contains(text(),'No Active Subscription')]");
	public static By objDownloadInvoiceBtn = By.xpath("//p[@class='downloadBtn']");
	public static By objNoTransactionsText = By.xpath("//div[contains(text(),'No Transaction')]");
	public static By objTransactionPackName = By.xpath("//p[@class='packTitle']");
	public static By objTransactionPackPrice = By.xpath("(//p[@class='packPrice']/child::span)[2]");
	public static By objPackDetails(String parameter) {
		return By.xpath("//p[contains(text(), '"+parameter+"')]/following-sibling::p[@class='value']");
	}
	public static By objselectedDisplayLanguage = By.xpath("//div[@class='checkboxWrap checkedHighlight']/child::*/child::div[@class='checkboxText']/span[@class='commonName']");
    public static By objEmptyWatchListMessage = By.xpath("//div[contains(text(), 'Uh-Oh! Nothing to watch')]");
    public static By objEmptyRemindersmessage = By.xpath("//div[contains(text(), 'We have nothing to remind you.')]");
    public static By objMoviesTab = By.xpath("//a[contains(text(),'Movies')]");
    public static By objFirstContentInFirstTrayofShowsTab = By.xpath("(//div[@class='trayHeader']/following-sibling::*/child::*/child::div[@class='slick-list']/child::*/child::*)[1]");
    public static By objGenderValue = By.xpath("//input[@checked]/following-sibling::span[@class='labelText']");
	public static By objMyPlanProfile = By.xpath("//div[@class='myPlanProfile']");
	public static By objPremiumPackPrice = By.xpath("(//div[@class='packPrice']/text())[3]");
	public static By objPremiumPackValidityDate = By.xpath("//div[@class='renewDate']");
	public static By objPremiumPackInMySubscriptionScreen = By.xpath("//h2[@class='packName']");
	public static By objPemiumPackPriceInMySubscriptionScreen = By.xpath("//p[@class='price']");
	public static By objPremiumPackDuration = By.xpath("(//p[@class='packDuration']/text())[4]");
	public static By objPremiumPackdateOfPurchase = By.xpath("//div[@class='packDuration']");
	public static By objPackdetailsAtMySubscriptionpage(String key) {
		return By.xpath("//p[contains(text(), '"+key+"')]/following-sibling::p[@class='value']");
	}
	public static By objPackExpieryDate = By.xpath("(//p[@class='packExpiery']/text())[3]");
	public static By objBrowseAllPacksBtn = By.xpath("//div[contains(text(),'Browse All Packs')]");
	
	
	/***  App  ***/
	public static By objShowsTabUnderWatchList = By.xpath("//*[@text='Shows']");
	public static By objMoviesTabUnderWatchList = By.xpath("//*[@text='Movies']");
	public static By objVideosTabUnderWatchList = By.xpath("//*[@text='Videos']");
	public static By objcontentTitleInWatchListAndReminders = By.xpath("//*[@resource-id='com.graymatrix.did:id/txt_reminder_item_title']");
	public static By objSubscriptionPackName = By.xpath("//*[@resource-id='com.graymatrix.did:id/subs_Title']");
	public static By objPurchaseDate = By.xpath("//*[@resource-id='com.graymatrix.did:id/pack_purchase_date']");
	public static By objAutoRenewalvalue = By.xpath("//*[@resource-id='com.graymatrix.did:id/auto_renewal']");
	public static By objTransPackPrice = By.xpath("//*[@resource-id='com.graymatrix.did:id/mytran_amount']");
	public static By objNothingToWatchOrReminder = By.xpath("//*[@resource-id='com.graymatrix.did:id/txt_no_reminder']");
	public static By objSelectedRestrictAllContentOption = By.xpath("//*[@resource-id='com.graymatrix.did:id/selectionImageSelector']/parent::*/preceding-sibling::*/child::*[@text='Restrict All Content']");
	public static By objNoTransaction = By.xpath("//*[@text='No Transactions']");
	

}
