package com.qaprosoft.carina.demo.newgui;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class MainPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final String mainPageUrl = "https://www.saucedemo.com/inventory.html";

    @FindBy(className = "inventory_list")
    private ExtendedWebElement inventoryList;

    @FindBy(className = "inventory_item")
    private List<ExtendedWebElement> inventoryItems;

    @FindBy(className = "inventory_item_img")
    private List<ExtendedWebElement> inventoryItemImages;

    @FindBy(className = "inventory_item_name")
    private List<ExtendedWebElement> inventoryItemNames;

    @FindBy(className = "inventory_item_desc")
    private List<ExtendedWebElement> inventoryItemDescriptions;

    @FindBy(className = "inventory_item_price")
    private List<ExtendedWebElement> inventoryItemPrices;

    @FindBy(className = "btn btn_primary btn_small btn_inventory")
    private List<ExtendedWebElement> inventoryItemButtons;

    public MainPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(mainPageUrl);
    }

    public void checkProductImage(){
        for (ExtendedWebElement image : inventoryItemImages) {
            image.isElementPresent();
        }
    }

    public void checkProductDescription(){
        for (ExtendedWebElement decription : inventoryItemDescriptions) {
            decription.isElementPresent();
        }
    }

    public void checkProductName(){
        for (ExtendedWebElement name : inventoryItemNames) {
            name.isElementPresent();
        }
    }



    public void checkProductPrice(){
        for (ExtendedWebElement price : inventoryItemPrices) {
            price.isElementPresent();
        }
    }

    public void checkProductButton(){
        for (ExtendedWebElement button : inventoryItemButtons) {
            button.isElementPresent();
        }
    }




    public ProductPage selectItem(String item) {
        LOGGER.info("selecting '" + item + "' item...");
        for (ExtendedWebElement itemLink : inventoryItemNames) {
            String currentItem = itemLink.getText();
            LOGGER.info("currentItem: " + currentItem);
            if (item.equalsIgnoreCase(currentItem)) {
                itemLink.click();
                return new ProductPage(driver);
            }
        }
        throw new RuntimeException("Unable to open item: " + item);
    }
}
