package edu.iis.mto.bdd.cucumber.pages;

import edu.iis.mto.bdd.model.FrequentFlyerMember;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by student on 2016-06-09.
 */
@DefaultUrl("http://localhost:8080/#/home")
public class HomePage extends PageObject {

    @FindBy(id = "welcome-message")
    private WebElement message;

    public void checkWelcomeMessage(FrequentFlyerMember user) {
        assertThat(message.getText(), equalTo("Witaj " + user.getFirstName()));
    }
}
