package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDClubPage {
	
	public static By objClubVODContent = By.xpath("(//*[@resource-id='com.graymatrix.did:id/item_primary_text'])[4]");
	public static By objClubIcon = By.xpath("//*[@id='special_image_1' and (./preceding-sibling::* | ./following-sibling::*)[@id='itemImageParent']]");
	public static By objDownloadIcon = By.xpath("//*[@resource-id='com.graymatrix.did:id/downlowd_image']");
	public static By objPremiumTab = By.xpath("//*[@text='Premium']");
	public static By objClubTab = By.xpath("//*[@text='Club']");
	public static By objAllAccessPack = By.xpath("(//*[@resource-id='com.graymatrix.did:id/tv'])[1]");
	public static By objClubPack = By.xpath("(//*[@resource-id='com.graymatrix.did:id/tv'])[1]");
	public static By objTrendingSearchesTray = By.xpath("//*[@text='Trending Searches']");
	public static By objTopSearchesTray = By.xpath("//*[@text='Top Searches']");
	public static By objCarouselContentClubCard = By.xpath("(//*[@resource-id='com.graymatrix.did:id/hero_1_cell_parent']//following-sibling::*)[2]");
	public static By objContinueButton = By.xpath("//*[@id='btnContinue_PackSelection']");
	public static By objSelectedPackSection = By.xpath("//*[@resource-id='com.graymatrix.did:id/layoutSelectedPack']");
	public static By objAccountInfoSection = By.xpath("//*[@resource-id='com.graymatrix.did:id/layoutSelectedUser']");
	public static By objPaymentOptionsSection = By.xpath("//*[@id='payment_radio_button_container']");
	public static By objAccountInfoScreenContinueButton = By.xpath("//*[@id='btnContinue_paymentdetails']");
	public static By objSelectedPackName = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_selected_pack']");
	public static By objSelectedPackValidity = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_pack_year']");
	public static By objPlanPriceINR = By.xpath("//*[@resource-id='com.graymatrix.did:id/value_plan_price']");
	public static By objDiscountPlanINR = By.xpath("//*[@resource-id='com.graymatrix.did:id/value_discount']");
	public static By objRoundOffValue = By.xpath("//*[@resource-id='com.graymatrix.did:id/value_round_off']");
	public static By objRevisedBillingSection = By.xpath("//*[@resource-id='com.graymatrix.did:id/revised_billing_cycle_decription']");
    public static By objPostDiscountInINR = By.xpath("//*[@resource-id='com.graymatrix.did:id/tv_selected_pack_description']");
	public static By objEmailIdSection = By.xpath("//*[@resource-id='com.graymatrix.did:id/selectedUserDetailsName']");
    public static By objSelectPaymentOption = By.xpath("(//*[@id='payment_radio_button_container']/*[@class='android.widget.RadioButton'])[1]") ; 
 public static By objDownloadVideoQualityPopup = By.xpath("//*[@id='popup_title']");
    
    public static By objSearchedClubContent = By.xpath("(//*[@id='special_image_1' and (./preceding-sibling::* | ./following-sibling::*)[@id='itemImageParent']]//following-sibling::*)[1]");  
}
