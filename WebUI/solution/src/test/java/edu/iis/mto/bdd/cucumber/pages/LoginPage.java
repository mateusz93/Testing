package edu.iis.mto.bdd.cucumber.pages;

import edu.iis.mto.bdd.model.FrequentFlyerMember;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

/**
 * Created by student on 2016-06-09.
 */
@DefaultUrl("http://localhost:8080/#/welcome")
public class LoginPage extends PageObject {

    private WebElement email;
    private WebElement password;
    private WebElement signin;

    public void signinWithCredentials(FrequentFlyerMember user) {
        email.sendKeys(user.getEmail());
        password.sendKeys(user.getPassword());
        signin.click();
    }
}
