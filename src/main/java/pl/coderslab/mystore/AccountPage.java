package pl.coderslab.mystore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

    private WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@id=\"footer_account_list\"]/li[4]/a")
    WebElement footerAddressLabel;
    @FindBy(xpath = "//*[@id=\"category-3\"]/a")
    WebElement clothesCategoryButton;
    @FindBy(id = "history-link")
    WebElement orderHistoryLabel;

    public void goToAddressPage(){
        footerAddressLabel.click();
    }

    public void goToPageWithClothes(){
        clothesCategoryButton.click();
    }

    public void goToOrderHistoryPage(){
        orderHistoryLabel.click();
    }
}
