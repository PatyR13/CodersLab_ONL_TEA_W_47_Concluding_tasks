package pl.coderslab.mystore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewAddressPage {

    private WebDriver driver;

    @FindBy(id = "field-alias")
    WebElement aliasField;
    @FindBy(id = "field-address1")
    WebElement addressField;
    @FindBy(id = "field-city")
    WebElement cityField;
    @FindBy(id = "field-postcode")
    WebElement zipCodeField;
    @FindBy(id = "field-phone")
    WebElement phoneField;
    @FindBy(xpath = "//*[@id=\"content\"]/div/div/form/footer/button")
    WebElement saveButton;

    public NewAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void completeAndSubmitNewAddressForm(String alias, String address, String city, String zipCode, String phone){
        aliasField.sendKeys(alias);
        addressField.sendKeys(address);
        cityField.sendKeys(city);
        zipCodeField.sendKeys(zipCode);
        phoneField.sendKeys(phone);
        saveButton.click();
    }
}
