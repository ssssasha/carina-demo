package com.qaprosoft.carina.demo.newgui;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public Boolean checkImageItem(){
        return inventoryItemImages.get(0).isElementPresent();
    }

    public Boolean checkNameItem(){
        return inventoryItemNames.get(0).isElementPresent();
    }

    public Boolean checkDescriptionItem(){
        return inventoryItemDescriptions.get(0).isElementPresent();
    }

    public Boolean checkPriceItem(){
        return inventoryItemPrices.get(0).isElementPresent();
    }

    public Boolean checkButtonItem(){
        return inventoryItemButtons.get(0).isElementPresent();
    }

//    public Boolean checkProductItems(){
//        return inventoryItemNames.get(0).isElementPresent() && inventoryItemImages.get(0).isElementPresent()
//                && inventoryItemDescriptions.get(0).isElementPresent() && inventoryItemPrices.get(0).isElementPresent()
//                && inventoryItemButtons.get(0).isElementPresent();
//    }

    public Boolean checkFilterMenu(String option){
        filter.click();
        return filter.isElementWithTextPresent(option);
    }

    public String changeFilterOption(String chosenOption){
        filter.click();
        for (ExtendedWebElement option : filterOptions) {
            String currentOption = option.getText();
            LOGGER.info("currentItem: " + currentOption);
            if (chosenOption.equalsIgnoreCase(currentOption)) {
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
        String temp;
        for (int i = 0; i < names.size(); i++) {
            for (int j = i + 1; j < names.size(); j++) {
                if (names.get(i).compareTo(names.get(j)) > 0) {
                    temp = names.get(i);
                    names.set(i,names.get(j));
                    names.set(j,temp);
                }
            }
        }
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i));
        }
        return names;
    }

    //version 2 of testSortingByAlphabetical
//    public boolean checkAlphabeticalOrder(){
//        ArrayList<String> inventoryNamesListToCheck = new ArrayList<String>();
//        ArrayList<String> names = new ArrayList<String>();
//        for (ExtendedWebElement item : inventoryItemNames) {
//            names.add(item.getText());
//            inventoryNamesListToCheck.add(item.getText());
//        }
//        String temp;
//        for (int i = 0; i < names.size(); i++) {
//            for (int j = i + 1; j < names.size(); j++) {
//                if (names.get(i).compareTo(names.get(j)) > 0) {
//                    temp = names.get(i);
//                    names.get(i).equals(names.get(j));
//                    names.get(j).equals(temp);
//                }
//            }
//        }
//        return inventoryNamesListToCheck.equals(names);
//    }

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

    public CartPage addProductToCart(String productName){
        for (ExtendedWebElement item : inventoryItemNames) {
            if (productName.equalsIgnoreCase(item.getText())) {
                inventoryItemButtons.get(0).click();
                cart.click();
                return new CartPage(driver);
            }
        }
        throw new RuntimeException("Unable to open cart");
    }

//    public void addProductToCart(String productName){
//        int indx;
//        for(int i=0; i<inventoryItemNames.size(); i++) {
//            if (productName.equalsIgnoreCase(inventoryItemNames.get(i).getText())) {
//                for (int j = 0; j < inventoryItemButtons.size(); j++) {
//                    if (j == i) {
//                        inventoryItemButtons.get(j).click();
//                    }
//                }
//            }
//        }
//    }

    public RegistrationPage logOut(){
        burgerMenu.click();
        logOutButton.click();
        return new RegistrationPage(driver);
    }
}
