package selectMenuTest;

import browser.Browser;
import constant.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.elements.DynamicPropertiesPage;
import widgets.SelectMenu;

public class SelectMenuTest1 {
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
        dynamicPropertiesPage.openUrl("https://demoqa.com/select-menu");
        /*
        * Select — это класс из библиотеки Selenium, который позволяет взаимодействовать с выпадающими списками (select elements).
        dynamicPropertiesPage.findElement(By.id("oldSelectMenu")) —
        * находит элемент выпадающего списка по его id, который равен "oldSelectMenu".*/
        Select select = new Select(dynamicPropertiesPage.findElement(By.id("oldSelectMenu")));
        /*
         * Этот метод выбирает элемент из выпадающего списка с текстом "Green".
         * Если этот элемент доступен, он будет выделен.*/
        select.selectByVisibleText("Green");
        /*Этот вызов получает первый выбранный элемент из списка и его текст.*/
        select.getFirstSelectedOption().getText();
        //String defaultItem = option.getText();


        ////////////////////////////////////////////////////////////////////////////////////////
        /*Создается строковая переменная textItem, которая содержит текст "Group 1, option 2".
        Это значение будет использовано для поиска элемента.*/
        String textItem = "Group 1, option 2";
        /*
        * Этот код отправляет текст, хранящийся в textItem, в поле ввода, идентифицируемое по его id,
        * который равен "react-select-2-input".*/
        dynamicPropertiesPage.sendKeys(By.id("react-select-2-input"),textItem);
        /*
        * Здесь вызывается метод waitElementIsDisplay, который ожидает,
        * что элемент, идентифицируемый по указанному XPath, появится на странице в течение 5 секунд.*/
        dynamicPropertiesPage.waitElementIsDisplay(By.xpath("//div[normalize-space(@class)='css-26l3qy-menu']"),5);
        /*
        * Этот код выполняет клик по элементу, который имеет текст, соответствующий значению textItem,
        * внутри элемента с классом css-26l3qy-menu.
        * Метод click инициирует нажатие на найденный элемент.*/
        dynamicPropertiesPage.click(By.xpath("//div[normalize-space(@class)='css-26l3qy-menu']//div[text()='"+ textItem +"']"));


    }
}
