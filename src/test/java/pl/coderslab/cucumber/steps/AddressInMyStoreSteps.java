package pl.coderslab.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.coderslab.mystore.*;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class AddressInMyStoreSteps {

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
}
