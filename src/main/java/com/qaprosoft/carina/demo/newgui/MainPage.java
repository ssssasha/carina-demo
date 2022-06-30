package com.qaprosoft.carina.demo.newgui;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

    @FindBy(xpath = "//button[(contains(@class, 'btn_primary btn_small btn_inventory'))]")
    private List<ExtendedWebElement> inventoryItemButtons;

    @FindBy(xpath = "//select")
    private ExtendedWebElement filter;

    @FindBy(xpath = "//select/option")
    private List<ExtendedWebElement> filterOptions;

    @FindBy(className = "active_option")
    private ExtendedWebElement activeOption;

    @FindBy(xpath = "//a[(contains(@class, 'shopping_cart_link'))]")
    private ExtendedWebElement cart;

    @FindBy(id = "react-burger-menu-btn")
    private ExtendedWebElement burgerMenu;

    @FindBy(id = "logout_sidebar_link")
    private ExtendedWebElement logOutButton;

    public MainPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(mainPageUrl);
    }

    public Boolean isProductImagePresent(){
        return inventoryItemImages.get(0).isElementPresent();
    }

    public Boolean isProductNamePresent(){
        return inventoryItemNames.get(0).isElementPresent();
    }

    public Boolean isProductDescriptionPresent(){
        return inventoryItemDescriptions.get(0).isElementPresent();
    }

    public Boolean isProductPricePresent(){
        return inventoryItemPrices.get(0).isElementPresent();
    }

    public Boolean isAddToCartButtonPresent(){
        return inventoryItemButtons.get(0).isElementPresent();
    }


    public String getDefaultFilter(){
        return activeOption.getText();
    }

    public MainPage filterClick(){
        filter.click();
        return new MainPage(driver);
    }

    public Boolean isFilterMenuContainsOption (String option){
        filterClick();
        return filter.isElementWithTextPresent(option);
    }

    public String changeFilterOption(String chosenOption){
        filterClick();
        for (ExtendedWebElement option : filterOptions) {
            if (chosenOption.equalsIgnoreCase(option.getText())) {
                option.click();
                return activeOption.getText();
            }
        }
        throw new RuntimeException("Unable to chose option: " + chosenOption);
    }

    public List<String> getItemsNameList(){
        ArrayList<String> itemsNamesList = new ArrayList<String>();
        for (ExtendedWebElement item : inventoryItemNames) {
            itemsNamesList.add(item.getText());
            LOGGER.info(item.getText());
        }
        return itemsNamesList;
    }

    public List<String> getNamesInAlphabeticalOrder(){
        ArrayList<String> names = new ArrayList<String>();
        for (ExtendedWebElement item : inventoryItemNames) {
            names.add(item.getText());
        }
        List<String> sortedNamesList = names.stream().sorted().collect(Collectors.toList());
        return sortedNamesList;
    }

    public ProductPage selectItem(String item) {
        LOGGER.info("selecting '" + item + "' item...");
        for (ExtendedWebElement itemLink : inventoryItemNames) {
            if (StringUtils.equalsIgnoreCase(itemLink.getText(), item)) {
                itemLink.click();
                return new ProductPage(driver);
            }
        }
        throw new RuntimeException("Unable to open item: " + item);
    }

    public CartPage openCartPage(){
        cart.click();
        return new CartPage(driver);
    }

    public int getProductIndexByName(String productName) {
        int index=0;
        for (int i = 0; i < inventoryItemNames.size(); i++) {
            if (productName.equalsIgnoreCase(inventoryItemNames.get(i).getText())) {
               index = i;
            }
        }
        return index;
    }

    public MainPage addProductToCart(int indx){
        inventoryItemButtons.get(indx).click();
        return new MainPage(driver);
    }

    public RegistrationPage logOut(){
        burgerMenu.click();
        logOutButton.click();
        return new RegistrationPage(driver);
    }
}
