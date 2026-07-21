package Base;

import Pages.DashboardPage;
import Pages.LoginPage;
import Utilities.DriverFactory;
import io.appium.java_client.AppiumDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    protected AppiumDriver driver;
    protected Properties config;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;

    public void setUpAndLogin() throws IOException {
    config = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/config.properties");
        config.load(fis);

        DriverFactory.initDriver(config);
        driver = DriverFactory.getDriver();

        loginPage = new LoginPage(driver, config);
        loginToNdosiAutomation();

        dashboardPage = new DashboardPage(driver, config);
    }

    public void loginToNdosiAutomation() {
        loginPage.clickBurgerMenuButton();
        loginPage.clickSignInButton();
        loginPage.enterEmail(config.getProperty("email"));
        loginPage.enterPassword(config.getProperty("password"));
        loginPage.clickLoginButton();
    }
}
