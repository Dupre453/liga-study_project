package mainPage;

import browser.Browser;
import constant.CategoryCards;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.demoqa.main_pages.MainPage;
import pages.demoqa.secondPage.LeftPanel;
import pages.demoqa.secondPage.SecondPage;
import pages.elements.CheckBoxPage;

public class MainPageTest1 {
    private WebDriver driver;
    private MainPage mainPage;
    private SecondPage secondPage;
    private LeftPanel leftPanel;
    private CheckBoxPage checkBoxPage;

    @BeforeTest
    public void beforeTest(){
        driver = Browser.createDriver();
        mainPage = new MainPage(driver);
        leftPanel = new LeftPanel(driver);
    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }

    @Test
    public void step_01(){
        mainPage.openMainPage();
        Assert.assertTrue(mainPage.isElementDisplay(MainPage.HOME_BANNER)); //проверка, что открыта главная страница
        Assert.assertEquals(mainPage.getCategoryCount(),6); //проверка, что все 6 карточек выводятся
    }
    @Test
    public void step_02(){
        secondPage = mainPage.openCategoryCards(CategoryCards.ELEMENTS); //выбирает какую-то конкретную карточку на главной странице
        Assert.assertTrue(secondPage.isPageOpen()); //проверяем, что наша страница открыта
        Assert.assertTrue(leftPanel.isLeftPanelDisplayed()); // проверяем, что левая панель отоьражается
        Assert.assertTrue(leftPanel.isBlockOpen(CategoryCards.ELEMENTS)); // проверяем, что наш навбар открыт
    }
    @Test
    public void step_03(){
         checkBoxPage = leftPanel.openCheckBoxPage(); //открыта страница с чекбоксом
         checkBoxPage.getCheckBoxState("Home"); //
         checkBoxPage.setCheckBox("Home",true);

    }
}
