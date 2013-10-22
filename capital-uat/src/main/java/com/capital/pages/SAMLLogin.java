package com.capital.pages;

import com.capital.helpers.Constants;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class SAMLLogin extends BasePage {

    public void go() {
        get(Constants.SAML_URL);
    }

    public void doLogin() {
        input(id("username")).sendKeys(Constants.SAML_USER);
        input(id("password")).sendKeys(Constants.SAML_PW);
        input(cssSelector("#content>form>table>tbody>tr:nth-child(1)>td:nth-child(4)>input")).click();
    }
}
