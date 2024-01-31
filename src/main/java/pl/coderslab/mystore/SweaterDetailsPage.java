package pl.coderslab.mystore;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;
import java.util.Objects;

public class SweaterDetailsPage {
    private WebDriver driver;

    public SweaterDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "group_1")
    WebElement sizeSelectionBox;
    @FindBy(id = "quantity_wanted")
    WebElement quantityBox;
    @FindBy(xpath = "//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button")
    WebElement addToCartButton;
    @FindBy(xpath = "//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a")
    WebElement proceedToCheckoutButton;
    @FindBy(xpath = "//*[@id=\"main\"]/div[1]/div[2]/div[1]/div[2]/div/span[1]")
    WebElement currentPrice;
    @FindBy(xpath = "//*[@id=\"main\"]/div[1]/div[2]/div[1]/div[1]/span")
    WebElement regularPrice;

    public void addingToCartSelectedProduct(int quantity, String size){
        sizeSelectionBox.sendKeys(size);
        quantityBox.click();
        quantityBox.sendKeys(Keys.BACK_SPACE, Integer.toString(quantity));
        addToCartButton.click();
    }

    public void proceedToCheckout(){
        proceedToCheckoutButton.click();
    }

    public static double countTheRegularPrice(double currentPrice, double discountPercentage){
        return currentPrice / (1 - (discountPercentage/100));
    }

    public boolean correctDiscount(){
        double currentPriceNum = Double.parseDouble(currentPrice.getAttribute("content"));
        double regularPriceNum = countTheRegularPrice(currentPriceNum, 20);
        BigDecimal a = new BigDecimal(regularPriceNum);
        BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return Objects.equals(regularPrice.getText(), "€" + roundOff);
    }



}
