package pl.coderslab.mystore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage {
    private WebDriver driver;

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"content-hook_order_confirmation\"]/div/div/div/h3")
    WebElement orderConfirmationAlert;
    @FindBy(xpath = "//*[@id=\"order-items\"]/div[2]/table/tbody/tr[4]/td[2]")
    WebElement totalOrderAmount;
    @FindBy(xpath = "//*[@id=\"_desktop_user_info\"]/div/a[2]")
    WebElement accountDetailsButton;
    @FindBy(xpath = "//*[@id=\"order-items\"]/div[2]/div/div[3]/div/div[2]")
    WebElement orderedQuantity;
    @FindBy(xpath = "//*[@id=\"order-items\"]/div[2]/div/div[2]/span")
    WebElement orderedProductWithSize;
    @FindBy(xpath = "//*[@id=\"order-details\"]/ul/li[3]/em")
    WebElement shippingMethod;
    @FindBy(xpath = "//*[@id=\"order-details\"]/ul/li[2]")
    WebElement paymentMethod;
    @FindBy(id = "order-reference-value")
    WebElement orderReference;

    public String getOrderConfirmationAlert(){
        return orderConfirmationAlert.getText();
    }

    public String getTotalOrderAmount(){
        return totalOrderAmount.getText();
    }

    public void goToAccountPage(){
        accountDetailsButton.click();
    }

    public String getOrderedQuantity(){
        return orderedQuantity.getText();
    }

    public String getOrderedProductWithSize(){
        return orderedProductWithSize.getText();
    }

    public String getShippingMethod(){
        return shippingMethod.getText();
    }

    public String getPaymentMethod(){
        return paymentMethod.getText();
    }

    public String getOrderReference(){
        return orderReference.getText();
    }
}
