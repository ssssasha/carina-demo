package com.qaprosoft.carina.demo.newgui;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AbstractPage {

    @FindBy(className = "cart_item_label")
    private List<ExtendedWebElement> cartItems;

    @FindBy(className = "inventory_item_name")
    private List<ExtendedWebElement> cartItemNames;

    public CartPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("https://www.saucedemo.com/cart.html");

    }

    public Boolean checkProductInCart(String productName){
        return cartItemNames.get(0).isElementWithTextPresent(productName);
    }
}
