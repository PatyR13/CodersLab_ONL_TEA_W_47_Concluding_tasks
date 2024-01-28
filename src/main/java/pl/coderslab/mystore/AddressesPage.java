package pl.coderslab.mystore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddressesPage {

    private WebDriver driver;
    @FindBy(xpath = "//*[@id=\"content\"]/div[4]/a/span")
    WebElement createNewAddressButton;
    @FindBy(xpath = "//*[@id=\"address-8926\"]/div[1]/h4")
    WebElement aliasText;
    @FindBy(xpath = "//*[@id=\"address-8926\"]/div[1]/address")
    WebElement wholeAddressText;
    @FindBy(xpath = "//*[@id=\"address-8938\"]/div[2]/a[2]/span")
    WebElement additionalAddressDeleteButton;
    @FindBy(xpath = "//*[@id=\"notifications\"]/div/article/ul/li")
    WebElement successfullyDeletingAlert;

    public AddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToNewAddressPage(){
        createNewAddressButton.click();
    }

    public String getAliasText(){
        return aliasText.getText();
    }
    public String getWholeAddressText(){
        return wholeAddressText.getText();
    }

    public void deleteAddress(){
        additionalAddressDeleteButton.click();
    }

    public String getSuccessfullyDeletingAlert(){
        return successfullyDeletingAlert.getText();
    }
}
