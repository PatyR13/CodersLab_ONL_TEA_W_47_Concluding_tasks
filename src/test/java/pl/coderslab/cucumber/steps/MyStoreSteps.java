package pl.coderslab.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
    private MainPage mainPage;
    private LoginPage loginPage;
    private AccountPage accountPage;
    private AddressesPage addressesPage;
    private NewAddressPage newAddressPage;
    private ClothesPage clothesPage;
    private SweaterDetailsPage sweaterDetailsPage;
    private CartPage cartPage;
    private OrderDetailsPage orderDetailsPage;
    private OrderConfirmationPage orderConfirmationPage;
    private OrderHistoryPage orderHistoryPage;

    @Given("the user is logged into his account")
    public void userAccountLogin(){
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get("https://mystore-testlab.coderslab.pl");
        mainPage = new MainPage(this.driver);
        mainPage.goToLoginPage();
        loginPage = new LoginPage(this.driver);
        loginPage.userLogin(REGISTERED_EMAIL, REGISTERED_PASSWORD);
    }

    @And("an open browser at create a new address")
    public void openACreateANewAddressSection(){
        accountPage = new AccountPage(this.driver);
        accountPage.goToAddressPage();
        addressesPage = new AddressesPage(this.driver);
        addressesPage.goToNewAddressPage();
    }

    @When("the user adds a new address with {word} {string} {word} {word} {word}")
    public void addingANewAddress(String alias, String address, String city, String zipCode, String phone){
        newAddressPage = new NewAddressPage(this.driver);
        newAddressPage.completeAndSubmitNewAddressForm(alias, address, city, zipCode, phone);
    }

    @Then("new address added with correct {word} {string} {word} {word} {string} {word}")
    public void addressValidation(String alias, String address, String city, String zipCode, String country, String phone){
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
        mainPage = new MainPage(this.driver);
        mainPage.goToLoginPage();
        loginPage = new LoginPage(this.driver);
        loginPage.userLogin(REGISTERED_EMAIL, REGISTERED_PASSWORD);
        accountPage = new AccountPage(this.driver);
        accountPage.goToAddressPage();
    }

    @When("the user deletes one of additional addresses")
    public void deletingAddress(){
        addressesPage = new AddressesPage(this.driver);
        addressesPage.deleteAddress();
    }

    @Then("the address was removed successfully")
    public void checkingSuccessfullyDeleting(){
        assertEquals("Address successfully deleted!", addressesPage.getSuccessfullyDeletingAlert());
    }

    @Given("the logged user has open browser at clothes category")
    public void openAsLoggedUserPageWithClothes(){
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.driver.get("https://mystore-testlab.coderslab.pl");
        mainPage = new MainPage(this.driver);
        mainPage.goToLoginPage();
        loginPage = new LoginPage(this.driver);
        loginPage.userLogin(REGISTERED_EMAIL, REGISTERED_PASSWORD);
        accountPage = new AccountPage(this.driver);
        accountPage.goToPageWithClothes();
    }

    @When("the user buys {int} piece(s) of sweater with size {word}")
    public void buyingASweater(int quantity, String size) {
        clothesPage = new ClothesPage(this.driver);
        clothesPage.goToClotheDetails();
        sweaterDetailsPage = new SweaterDetailsPage(this.driver);
        assumeTrue(sweaterDetailsPage.isDiscountCorrect());
        sweaterDetailsPage.addingToCartSelectedProduct(quantity, size);
        sweaterDetailsPage.proceedToCheckout();
        cartPage = new CartPage(this.driver);
        cartPage.proceedToCheckout();
        orderDetailsPage = new OrderDetailsPage(this.driver);
        orderDetailsPage.placingTheOrder();
    }

    @Then("the order is placed")
    public void orderConfirmation(){
        orderConfirmationPage = new OrderConfirmationPage(this.driver);
        assertEquals("\uE876YOUR ORDER IS CONFIRMED", orderConfirmationPage.getOrderConfirmationAlert());
    }

    @And("the screenshot is taken")
    public void takingAScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("orderConfirmationImages/placed_order.png"));
    }

    @And("the order status is Awaiting check payment and the amount is the same as in the confirmation")
    public void confirmationOfOrderStatusAndAmount(){
        String orderAmount = orderConfirmationPage.getTotalOrderAmount();
        orderConfirmationPage.goToAccountPage();
        accountPage.goToOrderHistoryPage();
        orderHistoryPage = new OrderHistoryPage(this.driver);
        assertEquals(orderAmount, orderHistoryPage.getLastOrderTotalPrice());
        assertEquals("Awaiting check payment", orderHistoryPage.getLastOrderStatus());
    }
}
