package pl.coderslab.mystore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClothesPage {

    private WebDriver driver;

    public ClothesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"js-product-list\"]/div[1]/div[2]/article/div/div[2]/h2/a")
    WebElement clothingSought;

    public void goToClotheDetails(){
        clothingSought.click();
    }
}
