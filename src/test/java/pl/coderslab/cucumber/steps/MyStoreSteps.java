package pl.coderslab.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.coderslab.mystore.*;

import java.io.File;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

public class MyStoreSteps {

    private WebDriver driver;
    private final String REGISTERED_EMAIL = "testowanko@test.pl";
    private final String REGISTERED_PASSWORD = "Test1234";
    private final String USER_FULL_NAME = "Pat Verde";

    @Given("the user is logged into his account")
    public void userAccountLogin(){
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get("https://mystore-testlab.coderslab.pl");
        MainPage mainPage = new MainPage(this.driver);
        mainPage.goToLoginPage();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.userLogin(REGISTERED_EMAIL, REGISTERED_PASSWORD);
    }

    @And("an open browser at create a new address")
    public void openACreateANewAddressSection(){
        AccountPage accountPage = new AccountPage(this.driver);
        accountPage.goToAddressPage();
        AddressesPage addressesPage = new AddressesPage(this.driver);
        addressesPage.goToNewAddressPage();
    }

    @When("the user adds a new address with {word} {string} {word} {word} {word}")
    public void addingANewAddress(String alias, String address, String city, String zipCode, String phone){
        NewAddressPage newAddressPage = new NewAddressPage(this.driver);
        newAddressPage.completeAndSubmitNewAddressForm(alias, address, city, zipCode, phone);
    }

    @Then("new address added with correct {word} {string} {word} {word} {string} {word}")
    public void addressValidation(String alias, String address, String city, String zipCode, String country, String phone){
        AddressesPage addressesPage = new AddressesPage(this.driver);
        assertEquals(alias + "\n" + USER_FULL_NAME + "\n" + address + "\n" + city + "\n" + zipCode + "\n" + country + "\n" + phone + "\n" + "\uE254 Update \uE872 Delete", addressesPage.getWholeAddressText());
    }

    @And("quit browser")
    public void quitBrowser(){
        this.driver.quit();
    }

    @Given("an open browser at logged user's address page")
    public void openALoggedUserAddressPage(){
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get("https://mystore-testlab.coderslab.pl");
        MainPage mainPage = new MainPage(this.driver);
        mainPage.goToLoginPage();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.userLogin(REGISTERED_EMAIL, REGISTERED_PASSWORD);
        AccountPage accountPage = new AccountPage(this.driver);
        accountPage.goToAddressPage();
    }

    @When("the user deletes one of additional addresses")
    public void deletingAddress(){
        AddressesPage addressesPage = new AddressesPage(this.driver);
        addressesPage.deleteAddress();
    }

    @Then("the address was removed successfully")
    public void checkingSuccessfullyDeleting(){
        AddressesPage addressesPage = new AddressesPage(this.driver);
        assertEquals("Address successfully deleted!", addressesPage.getSuccessfullyDeletingAlert());
    }

    @Given("the logged user has open browser at clothes category")
    public void openAsLoggedUserPageWithClothes(){
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get("https://mystore-testlab.coderslab.pl");
        MainPage mainPage = new MainPage(this.driver);
        mainPage.goToLoginPage();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.userLogin(REGISTERED_EMAIL, REGISTERED_PASSWORD);
        AccountPage accountPage = new AccountPage(this.driver);
        accountPage.goToPageWithClothes();
    }

    @When("the user buys {int} piece(s) of sweater with size {word}")
    public void buyingASweater(int quantity, String size) {
        ClothesPage clothesPage = new ClothesPage(this.driver);
        clothesPage.goToClotheDetails();
        SweaterDetailsPage sweaterDetailsPage = new SweaterDetailsPage(this.driver);
        assumeTrue(sweaterDetailsPage.correctDiscount());
        sweaterDetailsPage.addingToCartSelectedProduct(quantity, size);
        sweaterDetailsPage.proceedToCheckout();
        CartPage cartPage = new CartPage(this.driver);
        cartPage.proceedToCheckout();
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(this.driver);
        orderDetailsPage.placingTheOrder();
    }

    @Then("the order is placed")
    public void orderConfirmation(){
        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(this.driver);
        assertEquals("\uE876YOUR ORDER IS CONFIRMED", orderConfirmationPage.getOrderConfirmationAlert());
    }

    @And("the screenshot is taken")
    public void takingAScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("orderConfirmationImages/placed_order.png"));
    }

    @And("the order status is Awaiting check payment and the amount is the same as in the confirmation")
    public void confirmationOfOrderStatusAndAmount(){
        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(this.driver);
        String orderAmount = orderConfirmationPage.getTotalOrderAmount();
        orderConfirmationPage.goToAccountPage();
        AccountPage accountPage = new AccountPage(this.driver);
        accountPage.goToOrderHistoryPage();
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(this.driver);
        assertEquals(orderAmount, orderHistoryPage.getLastOrderTotalPrice());
        assertEquals("Awaiting check payment", orderHistoryPage.getLastOrderStatus());
    }
}
