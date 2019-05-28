/*
Тесты
Тесты используют data provider, один пароль является верным, второй нет
Проверяем, что с правильным паролем можно зайти, а с неверным нельзя
Предусловия: создан аккаунт с данными: логин +79630830696 пароль bQp0Lem1Zv
 */

package dev.shop;

import dev.shop.models.SignInPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(value = Parameterized.class)
public class LoginTests {
    private WebDriver webDriver;
    private WebDriverWait wait;
    private String password;
    private String result;

    public LoginTests(String password, String result) {
        this.password = password;
        this.result = result;
    }

    @Before
    public void setUp(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        wait = new WebDriverWait(webDriver, 30);
    }


    @Parameters
    public static Collection<Object[]> data() {
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"bQp0Lem1Zv", "companies"});
        data.add(new Object[]{"bQp0Lem1xxxxZv", "sign-in"});
        return data;
    }

    @Test
    public void testLogin(){
        webDriver.get("https://dev-shop.jowi.club/auth/sign-in");
        SignInPage page = new SignInPage(webDriver);
        page.enterLogin("79630830696");
        page.enterPhone(password);
        page.pressSubmit();
        wait.until(ExpectedConditions.urlContains(result));
        assertTrue(webDriver.getCurrentUrl().contains(result));
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }
}
