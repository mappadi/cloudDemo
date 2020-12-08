package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAPlayPage {

	public static By objJoyStickTag = By.xpath("//*[contains(@class,'cardJoystickContent')]");
	
	public static By objPlayNowButton = By.xpath("//*[contains(@class,'slick-active')]//span[text()='Play Now']");

}
