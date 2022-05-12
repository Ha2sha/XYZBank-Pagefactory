package com.bank.testsuite;

import com.bank.customlisteners.CustomListeners;
import com.bank.pages.*;
import com.bank.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)

public class BankTest extends TestBase {

    AccountPage accountPage;
    AddCustomerPage addCustomerPage;
    BankManagerLoginPage bankManagerLoginPage;
    CustomerLoginPage customerLoginPage;
    CustomersPage customersPage;
    Homepage homePage;
    OpenAccountPage openAccountPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        accountPage = new AccountPage();
        addCustomerPage = new AddCustomerPage();
        bankManagerLoginPage = new BankManagerLoginPage();
        customerLoginPage = new CustomerLoginPage();
        customersPage = new CustomersPage();
        homePage = new Homepage();
        openAccountPage = new OpenAccountPage();
    }


    @Test(priority = 1, groups = {"sanity", "regression"})
    public void bankManagerShouldAddCustomerSuccessfully() {
        // click On "Bank Manager Login" Tab
        homePage.clickBankManagerLoginButton();
        //  click On "Add Customer" Tab
        bankManagerLoginPage.clickAddCustomerButton();
        //	enter FirstName
        addCustomerPage.enterFirstName("Smita");
        //	enter LastName
        addCustomerPage.enterLastName("Pipaliya");
        //	enter PostCode
        addCustomerPage.enterPostCode("HA45RH");
        //	click On "Add Customer" Button
        addCustomerPage.clickAddCustomerButton();
        //	popup display
        //	verify message "Customer added successfully"
        String expectedMessage = "Customer added successfully";
        String actualMessage = addCustomerPage.textFromAlert();
        Assert.assertEquals(expectedMessage, actualMessage, "Actual text does not match expected text");
        //	click on "ok" button on popup.
        addCustomerPage.clickOkOnAlert();
    }

    @Test(priority = 2, groups = {"sanity", "smoke", "regression"})
    public void bankManagerShouldOpenAccountSuccessfully() {
        // click On "Bank Manager Login" Tab
        homePage.clickBankManagerLoginButton();
        //	click On "Open Account" Tab
        bankManagerLoginPage.clickOpenAccountButton();
        //	Search customer that created in first test
        openAccountPage.selectCustomerJustAddedDropDown("Harry Potter");
        //	Select currency "Pound"
        openAccountPage.selectCurrencyDropDown("Pound");
        //	click on "process" button
        openAccountPage.clickProcessButton();
        //	popup displayed
        //	verify message "Account created successfully"
        String expectedMessage = "Account created successfully";
        String actualMessage = openAccountPage.textFromAlert();
        Assert.assertEquals(expectedMessage, actualMessage, "Actual text does not match expected text");
        //	click on "ok" button on popup.
        openAccountPage.clickOkOnAlert();
    }

    @Test(priority = 3, groups = {"smoke", "regression"})
    public void customerShouldLoginAndLogoutSuccessfully() {
        // click on "Customer Login" Tab
        homePage.clickCustomerLoginBtnButton();
        //	search customer that you created.
        customerLoginPage.selectNameFromDropDownMenu("Harry Potter");
        //	click on "Login" Button
        customerLoginPage.clickLoginButton();
        //	verify "Logout" Tab displayed.
        String expectedMessage = "logout";
        String actualMessage = customersPage.verifyLogoutButton();
        Assert.assertEquals(expectedMessage, actualMessage, "User has not logged in successfully");
        //	click on "Logout"
        customersPage.clickLogoutButton();
        //	verify "Your Name" text displayed.
        String expectedMessage1 = "Your Name";
        String actualMessage1 = customerLoginPage.getYourNameText();
        Assert.assertEquals(expectedMessage1, actualMessage1, "User has not logged out successfully");
    }

    @Test(priority = 4, groups = {"smoke", "regression"})
    public void customerShouldDepositMoneySuccessfully() {
        //  click on "Customer Login" Tab
        homePage.clickCustomerLoginBtnButton();
        //	search customer that you created.
        customerLoginPage.selectNameFromDropDownMenu("Harry Potter");
        //	click on "Login" Button
        customerLoginPage.clickLoginButton();
        //	click on "Deposit" Tab
        accountPage.clickDepositButton();
        //	Enter amount 100
        accountPage.enterDepositAmount("100");
        //	click on "Deposit" Button
        accountPage.clickSmallDepositButton();
        //	verify message "Deposit Successful"
        String expectedMessage = "Deposit Successful";
        String actualMessage = accountPage.verifyDepositSuccessfulText();
        Assert.assertEquals(expectedMessage, actualMessage, "Actual text does not match expected text");
    }

    @Test(priority = 5, groups = {"regression"})
    public void customerShouldWithdrawMoneySuccessfully() throws InterruptedException {
        // click on "Customer Login" Tab
        homePage.clickCustomerLoginBtnButton();
        //	search customer that you created.
        customerLoginPage.selectNameFromDropDownMenu("Harry Potter");
        //	click on "Login" Button
        customerLoginPage.clickLoginButton();

        accountPage.clickDepositButton();
        accountPage.enterDepositAmount("100");
        accountPage.clickSmallDepositButton();

        //	click on "Withdrawl" Tab
        accountPage.clickWithdrawalButton();
        //	Enter amount 50
        accountPage.enterWithdrawalAmount("50");
        //	click on "Withdraw" Button
        accountPage.clickSmallWithdrawalButton();
        //	verify message "Transaction Successful"
        String expectedMessage = "Transaction successful";
        String actualMessage = accountPage.verifyWithdrawalSuccessfulText();
        Assert.assertEquals(expectedMessage, actualMessage, "Actual text does not match expected text");
    }
}




