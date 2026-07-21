package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class LoginPage {

    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public LoginPage(AppiumDriver driver, Properties config) {
        this.driver = driver;
        this.config = config;
        //implicit wait
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private By burgerMenuButtonNative = By.xpath("//android.widget.Button");
    private By burgerMenuButtonWeb = By.xpath("//button[@class='nav-burger']");

    private By emailFieldNative = By.xpath("");
    private By emailFieldWeb = By.xpath("//input[@id='login-email']");

    private By signInButtonNative = By.xpath("//android.widget.Button[@content-desc='Login / Sign Up']");
    private By signInButtonWeb = By.xpath("//button[@class='mobile-menu-item']");

    private By passwordFieldNative = By.xpath("");
    private By passwordFieldWeb = By.xpath("//input[@id='email']");

    private By loginButtonNative = By.xpath("");
    private By loginButtonWeb = By.xpath("//button[@type='submit']");

    private WebElement getElement(By nativeLocator, By webLocator) {
        String execType = config.getProperty("executionType");

        if (execType.equalsIgnoreCase("nativeApp")) {
            return wait.until(ExpectedConditions.elementToBeClickable(nativeLocator));
        } else if (execType.equalsIgnoreCase("mobileWeb")) {
            return wait.until(ExpectedConditions.elementToBeClickable(webLocator));
        } else {
            throw new RuntimeException("Unsupported executionType: " + execType);
        }
    }

    public void clickBurgerMenuButton() {
        getElement(burgerMenuButtonNative, burgerMenuButtonWeb).click();
    }

    public void clickSignInButton() {
        getElement(signInButtonNative, signInButtonWeb).click();
    }

    public void enterEmail(String email) {
        WebElement emailElement = getElement(emailFieldNative, emailFieldWeb);
        emailElement.click();
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = getElement(passwordFieldNative, passwordFieldWeb);
        passwordElement.click();
        passwordElement.sendKeys(password);
    }
    public void clickLoginButton() {
        getElement(loginButtonNative, loginButtonWeb).click();
    }
}
