package pl.coderslab.mystore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderDetailsPage {
    private WebDriver driver;

    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"checkout-addresses-step\"]/div/div/form/div[2]/button")
    WebElement addressesContinueButton;
    @FindBy(id = "delivery_option_8")
    WebElement selfPickUpLabel;
    @FindBy(xpath = "//*[@id=\"js-delivery\"]/button")
    WebElement shippingMethodContinueButton;
    @FindBy(id = "payment-option-1")
    WebElement payByCheckRadio;
    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    WebElement agreeTermsCheckbox;
    @FindBy(xpath = "//*[@id=\"payment-confirmation\"]/div[1]/button")
    WebElement placeOrderButton;

    public void placingTheOrder(){
        addressesContinueButton.click();
        if(!selfPickUpLabel.isSelected()){
            selfPickUpLabel.click();
        }
        shippingMethodContinueButton.click();
        payByCheckRadio.click();
        agreeTermsCheckbox.click();
        placeOrderButton.click();
    }

}
