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

    @FindBy(xpath = "//button[(contains(@class, 'btn btn_secondary btn_small cart_button'))]")
    private ExtendedWebElement productRemoveButton;

    public CartPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("https://www.saucedemo.com/cart.html");

    }

    public Boolean isProductInCart(String productName, int index){
        if(cartItems.size()==0)
            return false;
        return cartItemNames.get(index).isElementWithTextPresent(productName);
    }

    public CartPage removeProduct(){
        productRemoveButton.click();
        return new CartPage(driver);
    }
}
