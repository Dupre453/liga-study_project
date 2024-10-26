package checkBoxTest;

import browser.Browser;
import constant.Item;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.demoqa.main_pages.MainPage;
import pages.demoqa.secondPage.LeftPanel;
import pages.elements.CheckBoxPage;

public class CheckBoxTest1 {
    private WebDriver driver;
    private CheckBoxPage checkBoxPage;
    @BeforeTest
    public void beforeTest(){
        driver = Browser.createDriver();
        checkBoxPage = new CheckBoxPage(driver);
    }
    @AfterTest
    public void afterTest(){
        driver.quit();
    }

    /*
    * Открывает страницу чекбоксов.
      Проверяет, что имя текущей страницы соответствует ожидаемому имени чекбокса.*/
    @Test
    public void step_1(){
        checkBoxPage.openCheckBoxPage(); //проверяем открыт ли чекбокс
        Assert.assertEquals(checkBoxPage.getPageName(), Item.CHECK_BOX.getName());
    }
    @Test
    public void step_2(){
        checkBoxPage.openListCheckBox("Home");
        checkBoxPage.setCheckBox("Desktop",true);
        Assert.assertTrue(checkBoxPage.getCheckBoxState("Desktop"));
    }

}
