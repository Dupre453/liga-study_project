package dynamicPropertiesTest;

import browser.Browser;
import constant.Item;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.elements.CheckBoxPage;
import pages.elements.DynamicPropertiesPage;

import static pages.elements.DynamicPropertiesPage.*;

public class DynamicPropertiesTest1 {
    private WebDriver driver;
    private DynamicPropertiesPage dynamicPropertiesPage;
    @BeforeTest
    public void beforeTest(){
        driver = Browser.createDriver();
        dynamicPropertiesPage = new DynamicPropertiesPage(driver);
    }
    @AfterTest
    public void afterTest(){
        driver.quit();
    }

    @Test
    public void step_1(){
        dynamicPropertiesPage.openDynamicPage();
        Assert.assertEquals(dynamicPropertiesPage.getPageName(), Item.DYNAMIC_PROPERTIES.getName());
    }
    @Test
    public void step_2(){
        String color = dynamicPropertiesPage.getColorBtn(BTN_COLOR_CHANGE); //getColorBtn - метод получает текущий цвет кнопки
        System.out.println(color);
        dynamicPropertiesPage.waitForColorChange(); //подожди пока кнопка окрасится
        color = dynamicPropertiesPage.getColorBtn(BTN_COLOR_CHANGE); //изменение цвета
        System.out.println(color);
        Assert.assertTrue(true); //проверяем, что кнопка окрасилась
    }
    @Test
    public void step_3(){
        driver.navigate().refresh(); //обновляет страницу
        //dynamicPropertiesPage.click(BTN_VISIBLE_AFTER); //появление кнопки после 5 секунд
        dynamicPropertiesPage.waitElementIsDisplay(BTN_VISIBLE_AFTER,10); //поменяется через 10сек
    }
    @Test
    public void step_4(){
        driver.navigate().refresh();
        //dynamicPropertiesPage.click(BTN_VISIBLE_AFTER);
        //Assert.assertTrue(dynamicPropertiesPage.isElementEnabled(BTN_ENABLE_AFTER));
        dynamicPropertiesPage.waitForElementsEnabled(BTN_ENABLE_AFTER,10);
        Assert.assertTrue(dynamicPropertiesPage.isElementEnabled(BTN_ENABLE_AFTER));//проверяет, когда элемент станет активным
    }
}
