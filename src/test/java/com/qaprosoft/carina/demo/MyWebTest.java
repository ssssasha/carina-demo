package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.qaprosoft.carina.demo.newgui.CartPage;
import com.qaprosoft.carina.demo.newgui.MainPage;
import com.qaprosoft.carina.demo.newgui.RegistrationPage;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class MyWebTest implements IAbstractTest {
    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    @TestLabel(name = "feature", value = {"web"})
    public void testErrorMessage() {
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.open();
        Assert.assertTrue(registrationPage.isPageOpened(), "Registration page is not opened");
        registrationPage.typeUsername("user1");
        registrationPage.typePassword("secret_sauce");
        registrationPage.clickLoginButton();
        String errorText = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertTrue(errorText.equalsIgnoreCase(registrationPage.readErrorMessage()));
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    @TestLabel(name = "feature", value = {"web"})
    public void testRegistration() {
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.open();
        Assert.assertTrue(registrationPage.isPageOpened(), "Registration page is not opened");
        registrationPage.typeUsername("standard_user");
        registrationPage.typePassword("secret_sauce");
        MainPage mainPage = registrationPage.clickLoginButton();
        Assert.assertTrue(mainPage.isPageOpened(), "Home page is not opened");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    @TestLabel(name = "feature", value = {"web"})
    public void testProductItemVerifying() {
        // Open GSM Arena home page and verify page is opened
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.open();
        Assert.assertTrue(registrationPage.isPageOpened(), "Registration page is not opened");
        registrationPage.typeUsername("standard_user");
        registrationPage.typePassword("secret_sauce");
        MainPage mainPage = registrationPage.clickLoginButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(mainPage.isProductImagePresent(), "Product image is not present in the product card");
        softAssert.assertTrue(mainPage.isProductNamePresent(), "Product name is not present in the product card");
        softAssert.assertTrue(mainPage.isProductDescriptionPresent(), "Product description is not present in the product card");
        softAssert.assertTrue(mainPage.isProductPricePresent(), "Product price is not present in the product card");
        softAssert.assertTrue(mainPage.isAddToCartButtonPresent(), "Add to cart button is not present in the product card");
        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    @TestLabel(name = "feature", value = {"web"})
    public void testDropDownFilterMenuVerifying() {
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.open();
        Assert.assertTrue(registrationPage.isPageOpened(), "Registration page is not opened");
        registrationPage.typeUsername("standard_user");
        registrationPage.typePassword("secret_sauce");
        MainPage mainPage = registrationPage.clickLoginButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(mainPage.isFilterMenuContainsOption("Name (A to Z)"), "Drop down filter menu doesn't contain " +
                "Name (A to Z) option");
        softAssert.assertTrue(mainPage.isFilterMenuContainsOption("Name (Z to A)"), "Drop down filter menu doesn't contain " +
                "Name (Z to A) option");
        softAssert.assertTrue(mainPage.isFilterMenuContainsOption("Price (low to high)"), "Drop down filter menu doesn't contain " +
                "Price (low to high) option");
        softAssert.assertTrue(mainPage.isFilterMenuContainsOption("Price (high to low)"), "Drop down filter menu doesn't contain " +
                "Price (high to low)");
        softAssert.assertAll();
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    @TestLabel(name = "feature", value = {"web"})
    public void testFilterMenu() {
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.open();
        Assert.assertTrue(registrationPage.isPageOpened(), "Registration page is not opened");
        registrationPage.typeUsername("standard_user");
        registrationPage.typePassword("secret_sauce");
        MainPage mainPage = registrationPage.clickLoginButton();
        Assert.assertTrue(mainPage.getDefaultFilter().equalsIgnoreCase("Name (A to Z)"),
                "Default filter option is not correct");
        String newOption = "Price (low to high)";
        Assert.assertTrue(mainPage.changeFilterOption(newOption).equalsIgnoreCase(newOption),
                newOption + " filter is not selected");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    @TestLabel(name = "feature", value = {"web"})
    public void testSortingByAlphabetical() {
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.open();
        Assert.assertTrue(registrationPage.isPageOpened(), "Registration page is not opened");
        registrationPage.typeUsername("standard_user");
        registrationPage.typePassword("secret_sauce");
        MainPage mainPage = registrationPage.clickLoginButton();
        Assert.assertTrue(mainPage.getDefaultFilter().equalsIgnoreCase("Name (A to Z)"),
                "Default filter option is not correct");
        List<String> itemNamesToCheck = mainPage.getItemsNameList();
        List<String> itemNamesInAlphabeticalOrder = mainPage.getNamesInAlphabeticalOrder();
        Assert.assertEquals(itemNamesToCheck,itemNamesInAlphabeticalOrder,"products aren't sorted by alphabetical");
        //version 2
        //Assert.assertTrue(mainPage.checkAlphabeticalOrder(),"products aren't sorted by alphabetical");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    @TestLabel(name = "feature", value = {"web"})
    public void testProductPageOpening() {
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.open();
        Assert.assertTrue(registrationPage.isPageOpened(), "Registration page is not opened");
        registrationPage.typeUsername("standard_user");
        registrationPage.typePassword("secret_sauce");
        MainPage mainPage = registrationPage.clickLoginButton();
        String productName = "Sauce Labs Fleece Jacket";
        Assert.assertTrue(mainPage.selectItem(productName).isPageOpened(), "The " + productName +
                " is not opened");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    @TestLabel(name = "feature", value = {"web"})
    public void testAddingProductToCart() {
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.open();
        Assert.assertTrue(registrationPage.isPageOpened(), "Registration page is not opened");
        registrationPage.typeUsername("standard_user");
        registrationPage.typePassword("secret_sauce");
        MainPage mainPage = registrationPage.clickLoginButton();
        String productName = "Sauce Labs Backpack";
        int productIndex = mainPage.getProductIndexByName(productName);
        mainPage = mainPage.addProductToCart(productIndex);
        CartPage cartPage = mainPage.openCartPage();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened");
        Assert.assertTrue(cartPage.isProductInCart(productName, productIndex), "The " + productName +
                " is not added to the cart");

    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    @TestLabel(name = "feature", value = {"web"})
    public void testDeletingProductFromCart() {
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.open();
        Assert.assertTrue(registrationPage.isPageOpened(), "Registration page is not opened");
        registrationPage.typeUsername("standard_user");
        registrationPage.typePassword("secret_sauce");
        MainPage mainPage = registrationPage.clickLoginButton();
        String productName = "Sauce Labs Backpack";
        int productIndex = mainPage.getProductIndexByName(productName);
        mainPage = mainPage.addProductToCart(productIndex);
        CartPage cartPage = mainPage.openCartPage();
        Assert.assertTrue(cartPage.isPageOpened(), "Cart page is not opened");
        cartPage = cartPage.removeProduct();
        Assert.assertFalse(cartPage.isProductInCart(productName, productIndex), "The " + productName +
                " is not daleted from cart");
    }

    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    @TestLabel(name = "feature", value = {"web"})
    public void testLogOut() {
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.open();
        Assert.assertTrue(registrationPage.isPageOpened(), "Registration page is not opened");
        registrationPage.typeUsername("standard_user");
        registrationPage.typePassword("secret_sauce");
        MainPage mainPage = registrationPage.clickLoginButton();
        Assert.assertTrue(mainPage.logOut().isPageOpened(), "Registration page is not opened. LogOut failed");
    }







}
