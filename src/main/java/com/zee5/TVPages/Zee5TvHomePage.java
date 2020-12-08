package com.zee5.TVPages;

import org.openqa.selenium.By;

public class Zee5TvHomePage {

	public static By objHighlightedTab = By
			.xpath("//*[@id='menu_items_title' and @focused='true']");

	public static By objSelectTab(String str) {
		return By.xpath("//*[@id='menu_items_title' and @text='" + str + "']");
	}

	public static By objSearchIcon = By.xpath("//*[@id='search_icon_menu']");

}
