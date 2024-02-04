package pl.coderslab.mystore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage {
    private WebDriver driver;

    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"content\"]/table/tbody/tr[1]/td[2]")
    WebElement latestOrderTotalPrice;
    @FindBy(xpath = "//*[@id=\"content\"]/table/tbody/tr[1]/td[4]/span")
    WebElement latestOrderStatus;
    @FindBy(xpath = "//*[@id=\"content\"]/table/tbody/tr[1]/th")
    WebElement latestOrderReference;

    public String getLatestOrderTotalPrice(){
        return latestOrderTotalPrice.getText();
    }
    public String getLatestOrderStatus(){
        return latestOrderStatus.getText();
    }
    public String getLatestOrderReference(){
        return latestOrderReference.getText();
    }
}
