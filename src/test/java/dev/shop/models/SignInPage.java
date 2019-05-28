/*
* Page object для страницы sign in
* Реализованы методы заполнения логина, пароля и нажатия на кнопку с ожиданием
* В конструктор передается webdriver
*
* */

package dev.shop.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    private WebDriver webDriver;

    public SignInPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterLogin(String login){
        webDriver.findElement(By.cssSelector ("[name=login]")).sendKeys(login);
    }

    public void enterPhone(String password){
        webDriver.findElement(By.cssSelector ("[name=password]")).sendKeys(password);
    }

    public void pressSubmit(){
          WebElement we = new WebDriverWait(webDriver, 60).
                  until(ExpectedConditions.elementToBeClickable(By.cssSelector ("[type=submit]")));
          we.click();
    }
}
