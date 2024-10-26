package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class RadioButtonPage extends BasePage {
    public RadioButtonPage(WebDriver driver) {
        super(driver);
    }
    public static final String URL_TEXT_BOX_PAGE = "https://demoqa.com/radio-button";
    public static final String RADIO_BUTTON_XPATH = "//input[@type='radio'][following-sibling::label[contains(., '%s')]]";
    public static final String RADIO_BUTTON_CLICK_XPATH = "//input[@type='radio']/following-sibling::label[contains(., '%s')]";

    //метод для открытие страницы с радио кнопкой
    public void openRadioButtonPage(){
        openUrl(URL_TEXT_BOX_PAGE);
    }

    /*
    * 1.Получается XPath-локатор радиокнопки с именем radioButtonName.
      2.Затем переданный XPath используется в методе getRadioButtonState, чтобы узнать, выбрана ли радиокнопка.
      3.Результат (true или false) возвращается из метода getStateRadioButton.
      *
      *
      * String.format(RADIO_BUTTON_XPATH, radioButtonName) — подставляет значение radioButtonName
      * в строку RADIO_BUTTON_XPATH (например, если RADIO_BUTTON_XPATH равен чему-то вроде "//input[@name='%s']",
      * то в итоговом XPath вместо %s подставляется radioButtonName).*/
    public boolean getStateRadioButton(String radioButtonName){
        return getRadioButtonState(By.xpath(String.format(RADIO_BUTTON_XPATH,radioButtonName)));
    }

    //setRadioButton - базовый метод, что кликает по кнопке
    /*
    * то есть у нас есть локатор и после него, добавляем нам нужное имя кнопки.
    * метод для выбора кнопки*/
    public void clickRadioButton(String radioButtonName){
        setRadioButton(By.xpath(String.format(RADIO_BUTTON_CLICK_XPATH,radioButtonName)));
    }
    //метод, чтобы проверить, что кнопка не выбрана
    public boolean radioButtonIsEnabled(String radioButtonName){
        return isElementEnabled(By.xpath(String.format(RADIO_BUTTON_XPATH,radioButtonName)));
    }
}
