package radioButtonTest;

import browser.Browser;
import constant.CategoryCards;
import constant.Item;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.demoqa.main_pages.MainPage;
import pages.demoqa.secondPage.LeftPanel;
import pages.elements.RadioButtonPage;

public class RadioButtonTest1 {
    private WebDriver driver;
    private LeftPanel leftPanel;
    private RadioButtonPage radioButtonPage;

    @BeforeClass
    public void beforeClass() {
        driver = Browser.createDriver();
        leftPanel = new LeftPanel(driver);
        radioButtonPage = new RadioButtonPage(driver);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void step_01() {
        radioButtonPage.openRadioButtonPage(); //открываем страницу с радио кнопкой
        radioButtonPage.clickRadioButton("Yes"); //кликаем по чекбоксу на ДА
        Assert.assertTrue(radioButtonPage.getStateRadioButton("Yes")); //проверяем, что чекбокс действительно отметился
    }

    @Test
    public void step_02() {
        radioButtonPage.openRadioButtonPage(); //открываем страницу с радио кнопкой
        radioButtonPage.clickRadioButton("Impressive"); //кликаем по чекбоксу на Впечатляющий
        Assert.assertFalse(radioButtonPage.getStateRadioButton("Yes")); //проверяем, что кнопка ДА не выбрана
        Assert.assertTrue(radioButtonPage.getStateRadioButton("Impressive")); //проверяем, что кнопка Впечатляющий выбрана
        Assert.assertFalse(radioButtonPage.radioButtonIsEnabled("No")); //проверяем, что кнопка НЕТ не выбрана(она вообще не активна)
    }
}
