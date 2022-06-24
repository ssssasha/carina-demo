package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.Priority;
import com.qaprosoft.carina.core.foundation.utils.tag.TestPriority;
import com.qaprosoft.carina.demo.newgui.MainPage;
import com.qaprosoft.carina.demo.newgui.RegistrationPage;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MyWebTest implements IAbstractTest {
    @Test()
    @MethodOwner(owner = "qpsdemo")
    @TestPriority(Priority.P1)
    @TestLabel(name = "feature", value = {"web"})
    public void testErrorMessage() {
        // Open GSM Arena home page and verify page is opened
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
        // Open GSM Arena home page and verify page is opened
        RegistrationPage registrationPage = new RegistrationPage(getDriver());
        registrationPage.open();
        Assert.assertTrue(registrationPage.isPageOpened(), "Registration page is not opened");
        registrationPage.typeUsername("standard_user");
        registrationPage.typePassword("secret_sauce");
        registrationPage.clickLoginButton();
        //MainPage mainPage = registrationPage.logIn();
        MainPage mainPage = new MainPage(getDriver());
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
        registrationPage.clickLoginButton();
        MainPage mainPage = new MainPage(getDriver());

    }


}
