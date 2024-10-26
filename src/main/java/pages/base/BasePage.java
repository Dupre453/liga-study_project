package pages.base;

import constant.LocatorsType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static browser.Config.EXPLICIT_WAIT;

/*
* BASE PAGE - для работы с основными методами*/

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public static final By HEADER_XPATH = By.xpath("//header/a[@href='https://demoqa.com'][img[@src='/images/Toolsqa.jpg']]");
    public static final String CHECK_BOX_INPUT_XPATH = "//input[@type='checkbox'][following-sibling::span[text()='%s']]";
    public static final String CHECK_BOX_XPATH = CHECK_BOX_INPUT_XPATH + "/following-sibling::span[@class='rct-checkbox']";
    public static final By NAME_PAGE = By.xpath("//h1[@class='text-center']");
    public static final String BUTTON_XPATH = "//*[@type='button' and text()='%s']";

    public void openUrl(String url) {
        driver.get(url);
    }


    public WebElement findElement(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        return element;
    }

    //поиск карточек на странице
    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public void waitElementIsVisible(By locator, int second) {
        new WebDriverWait(driver, Duration.ofSeconds(second)).until(ExpectedConditions.visibilityOf(findElement(locator)));
    }

    //ожидаем через какое время отобразится элемент на странице
    public void waitElementIsDisplay(By locator, int second) {
        new WebDriverWait(driver, Duration.ofSeconds(second)).until(d -> isElementDisplay(locator));
    }
    public void waitForElementsEnabled(By locator, int second){
        new WebDriverWait(driver, Duration.ofSeconds(second)).until(d -> isElementEnabled(locator));
    }


    public void waitElementIsVisible(By locator) {
        waitElementIsVisible(locator, EXPLICIT_WAIT);
    }

    //просто метод клика
    public void click(By locator) {
        findElement(locator).click();
    }

    //
    public void doubleClick(By locator){
        Actions actions = new Actions(driver);//Actions — это класс Selenium, для работы с расширенными действиями, такими как двойной клик, перетаскивание, наведение курсора и т.д.
        actions.doubleClick(findElement(locator)).perform();//.perform() метод, который запускает цепочку действий, накопленных в actions. В данном случае он выполняет двойной клик.
    }
    public void contextClick(By locator){
        Actions actions = new Actions(driver);
        actions.contextClick(findElement(locator)).perform();
    }
    public void clear(By locator) {
        findElement(locator).clear();
    }

    public void sendKeys(By locator, String text) {
        findElement(locator).sendKeys(text);
    }

    public void sendKeysWithClear(By locator, String text){
        clear(locator);
        sendKeys(locator,text);
    }

    public String getText(By locator) {
        return findElement(locator).getText();
    }

    //метод, что ищет по xpath HOME_BANNER
    /*1. найди элемент по локатору и если открыто, то true, иначе слови ошибку и false*/
    public boolean isElementDisplay(By locator) {
        try {
            return findElement(locator).isDisplayed();
        }catch (Exception ex){
            return false;
        }
    }
    public boolean isElementEnabled(By locator){
        return findElement(locator).isEnabled();
    }

    public boolean isHeaderDisplayed() {
        return isElementDisplay(HEADER_XPATH);
    }


    public int getElementsCount(By locator) {
        return findElements(locator).size();
    }

    //метод, что кликает на чекбокс
    public boolean getCheckBoxState(String checkBoxName){
        return findElement(By.xpath(String.format(CHECK_BOX_INPUT_XPATH,checkBoxName))).isSelected(); //метод isSelected отвечает за состояние чекбокса
    }
    /**
     * Переводит чек-бокс в нужное состочние
     * @param checkBoxName - Название чек-бокса
     * @param state - нужное состочние, true если чек-бокс должен быть активным, false - если нет
     */
    public void setCheckBox(String checkBoxName, boolean state){
        //String locator = String.format(CHECK_BOX_XPATH, checkBoxName) + "/following-sibling::span[@class='rct-checkbox']";
        if (!getCheckBoxState(checkBoxName)==state){
            click(By.xpath(String.format(CHECK_BOX_XPATH,checkBoxName)));
        }
    }

    //используем локатор. верни нам элемент, если он выбран(элемент либо, выбран, либо нет true false)
    public boolean getRadioButtonState(By locator){
        return findElement(locator).isSelected();
    }

    //базовый метод, что просто кликает по кнопке
    public void setRadioButton (By locator){
        click(locator);
    }

    public By locatorBuild(String textLocator, LocatorsType locatorsType) {
        By locator = null;
        switch (locatorsType) {
            case ID:
                locator = By.id(textLocator);
                break;
            case XPATH:
                locator = By.xpath(textLocator);
                break;
            case CSS:
                locator = By.cssSelector(textLocator);
                break;
            case NAME:
                locator = By.name(textLocator);
                break;
            case TAG_NAME:
                locator = By.tagName(textLocator);
                break;
            case CLASS_NAME:
                locator = By.className(textLocator);
            default:
                System.out.println("Некорректный вид селектора: " + locatorsType);
        }
        return locator;
    }
    public void failure() {
        throw new AssertionError();
    }
    public String getPageName(){
        return getText(NAME_PAGE);
    }

    //базовый клик по кнопке
    public void buttonClick(String buttonName){
        click(By.xpath(String.format(BUTTON_XPATH,buttonName)));
    }

    //метод чтобы вытянуть любой стиль css
    public String getAttributeValue(By locator, String attribute){
        return findElement(locator).getCssValue(attribute);
    }

}
