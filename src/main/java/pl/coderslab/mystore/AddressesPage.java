package pl.coderslab.mystore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddressesPage {

    private WebDriver driver;
    @FindBy(partialLinkText = "Create")
    WebElement createNewAddressButton;
    @FindBy(xpath = "/html/body/main/section/div/div/section/section/div[@class=\"col-lg-4 col-md-6 col-sm-6\"][last()]")
    WebElement wholeAddressText;
    @FindBy(xpath = "/html/body/main/section/div/div/section/section/div[@class=\"col-lg-4 col-md-6 col-sm-6\"][last()]/article/div[2]/a[2]/span")
    WebElement lastAddedAddressDeleteButton;
    @FindBy(xpath = "//*[@id=\"notifications\"]/div/article/ul/li")
    WebElement successfullyDeletingAlert;

    public AddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void goToNewAddressPage(){
        createNewAddressButton.click();
    }

    public String getWholeAddressText(){
        return wholeAddressText.getText();
    }

    public void deleteAddress(){
        lastAddedAddressDeleteButton.click();
    }

    public String getSuccessfullyDeletingAlert(){
        return successfullyDeletingAlert.getText();
    }
}
