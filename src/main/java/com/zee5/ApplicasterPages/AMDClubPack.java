package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDClubPack {

	public static By objupgradeIcon = By.xpath("//*[@resource-id='com.graymatrix.did:id/subscribeiconlayout']");
	public static By objCrownIcon = By.xpath("//*[@text='P']");
	public static By objBuySubscriptionScreen = By.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title']");	
	public static By objUpgradeCTAOnCarousel = By.xpath("//*[@resource-id='com.graymatrix.did:id/get_premium_hero_component']");
	public static By objUpgradeCTABelowPlayer = By.xpath("//*[@class='android.widget.FrameLayout' and ./*[@text='Upgrade']]");
	public static By objYouneedpremiumtextonPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/static_premium_text']");
	
	public static By objUpgradepopuptitle = By.xpath("//*[@resource-id='com.graymatrix.did:id/popup_title']");
	public static By objProceedbutton = By.xpath("//*[@resource-id='com.graymatrix.did:id/proceed']");
	public static By objTermsofuseinUpgradepopup= By.xpath("//*[@resource-id='com.graymatrix.did:id/terms_of_use']");
	public static By objprivacypolicyinUpgradePopup = By.xpath("//*[@resource-id='com.graymatrix.did:id/privacy_policy']");
	public static By objPremiumPlanDescinUpgradepopup = By.xpath("//*[@resource-id='com.graymatrix.did:id/containerForPremiumTypeNote']");
	public static By objClubpackDescinupgradepopup = By.xpath("//*[@resource-id='com.graymatrix.did:id/containerForClubTypeNote']");
	public static By objplan1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_packDescription'])[1]");
	public static By objplan2 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_packDescription'])[2]");	
	
	 public static By objSubscribeinfoOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/static_premium_text']");
		public static By objSubscribetoClubCTAOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/subscribe_now_action']");
		public static By objLoginCTAOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/login_button']");
		public static By objGetClubCTABelowPlayer = By.xpath("//*[@class='android.widget.FrameLayout' and ./*[@text='Get Club']]");
		public static By objSubscribePopup = By.xpath("//*[@class='android.view.ViewGroup' and ./*[@text='Subscribe']]");
		public static By objLoginScreen = By.xpath("//*[@text='Login/Register']");
		public static By objPlanlistonSubscribePopup = By.xpath("//*[@id='listview_sub']");
		public static By objClubPackPlan = By.xpath("//*[@id='background_container' and (./preceding-sibling::* | ./following-sibling::*)[@id='imgOnlyClubPack']]");
		public static By objClubIconforClubPlan = By.xpath("//*[@id='imgOnlyClubPack']");
		public static By objPack1InSubscribePopup = By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_packDescription'])[1]");
		public static By objPack2InSubscribePopup = By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_packDescription'])[2]");
		public static By objPack3InSubscribePopup = By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_packDescription'])[3]");
		public static By objPack4InSubscribePopup = By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_packDescription'])[4]");
		public static By objPack5InSubscribePopup = By.xpath("(//*[@resource-id='com.graymatrix.did:id/txt_packDescription'])[5]");
		public static By objPremiumIconInSubscribePopup = By.xpath("(//*[@resource-id='com.graymatrix.did:id/imgPremiumPack'])[1]");
		public static By objClubIconInSubscribePopup = By.xpath("(//*[@resource-id='com.graymatrix.did:id/imgClubPack'])[1]");
		public static By objProceedButtonInSubscribePopup = By.xpath("//*[@resource-id='com.graymatrix.did:id/proceed']");
		public static By objAcountInfoInSubscribePage = By.xpath("//*[@text='Account Info' and ./parent::*[@id='user_inputs_details_layout']]");
		public static By objSubscribetoPremiumCTAOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/subscribe_now_action']");
		
		public static By objpaymentScreenInSubscribepopup = By.xpath("//*[@text='Payment Options']");
		public static By objBestOfZee5OriginalsTray = By.xpath("//*[@text='Best of ZEE5 Originals in Kannada']");
		public static By objClubIconOnFirstCardOfTray = By.xpath("(//*[@resource-id='com.graymatrix.did:id/special_image_1'])[1]");
		public static By objclubIconInContentListingScreen = By.xpath("(//*[@id='recyclerView' and ./parent::*[./parent::*[@class='android.widget.RelativeLayout' and ./parent::*[@id='content_frame']]]]/*/*/*[@id='special_image_1'])[1]");
		public static By objRecoMovieTray = By.xpath("//*[@text='Recommended Movies']");
		public static By objClubicononRecoTrays = By.xpath("((//*[@id='recyclerView']/*/*[@id='recyclerView'])[3]/*/*/*[@id='special_image_1'])[1]");
		
		public static By objBeforeZeeKannadaTray = By.xpath("//*[@text='Premiere Episodes | Before Zee Kannada']");
		public static By objKannadaFamilyDrama = By.xpath("//*[@text='Kannada Family Dramas']");
		
		public static By objUpgradeToPremiumLinkOnPlayer = By.xpath("//*[@resource-id='com.graymatrix.did:id/subscribe_now_action']");
		public static By objUpgradePopUp = By.xpath("//*[@resource-id='com.graymatrix.did:id/popup_title']");
}
