package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class MainPage {

    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //кнопка About
    @FindBy(xpath = "//div[@class='supernav_container']//*[contains(text(),'Информация')]")
    private WebElement aboutButton;
    //количество онлайн
    @FindBy(xpath = "//*[@class='online_stat_label gamers_online']//parent::div")
    private WebElement playersOnline;
    //количество в игре
    @FindBy(xpath = "//*[@class='online_stat_label gamers_in_game']//parent::div")
    private WebElement playersInGame;
    //меню Новое и интересное
    @FindBy(xpath = "//*[@id='noteworthy_tab']")
    private WebElement newAndInt;
    @FindBy(xpath = "//*[@id='noteworthy_flyout']")
    private WebElement popUpMenu;
    @FindBy(xpath = "//div[@class = 'popup_menu popup_menu_browse']//*[contains(text(), 'Лидеры продаж')]")
    private WebElement leadersOfSells;
    @FindBy(xpath = "//*[@class='DialogButton _DialogLayout Primary Focusable']")
    private WebElement seemoreLeadersOfSells;
    @FindBy(xpath = "//span[contains(text(), 'SteamOS + Linux')]/preceding-sibling::span")
    private WebElement steamOSLinuxCheckbox;
    @FindBy(xpath = "//div[text()='Количество игроков']/parent::div")
    private WebElement numberOfPlayers;
    @FindBy(xpath = "//span[@data-loc='Кооператив (локальная сеть)']")
    private WebElement koopLanCheckbox;
    @FindBy(xpath = "//span[@data-loc='Экшен']")
    private WebElement actionCheckbox;


    @FindBy(xpath = "//span[@data-loc='Экшен'] //span[@class='tab_filter_control_count']")
    private WebElement result;
    @FindBy(xpath = "//span[@data-loc='Экшен']")
    private WebElement numberOfGames;

    @FindBy(xpath = "//div[@id='search_resultsRows']//a")
    private WebElement firstResult;
    @FindBy(xpath = "//div[@id='search_resultsRows']//a//span")
    private WebElement firstResultName;
    @FindBy(xpath = "//div[@id='search_resultsRows']//a//div[@class='col search_released responsive_secondrow']")
    private WebElement firstResultDate;
    @FindBy(xpath = "//div[@id='search_resultsRows']//a//div[@class='discount_final_price']")
    private WebElement firstResultPrice;

    @FindBy(xpath = "//*[@id='appHubAppName']")
    private WebElement firstResultNameClick;
    @FindBy(xpath = "//*[@class='release_date']//*[@class='date']")
    private WebElement firstResultDateClick;
    @FindBy(xpath = "//*[@class='game_purchase_price price']")
    private WebElement firstResultPriceClick;




    //открыть страницу
    public void openPage(String url) {
        driver.get(url);
        String title = driver.getTitle();
        assertEquals(title, "Добро пожаловать в Steam");
    }

    //нажать на кнопку About
    public void clickAboutButton() {
        aboutButton.click();
    }

    //сравнить количество игроков
    public void comparePlayersAndGamers() {
        Integer d = Integer.parseInt(playersOnline.getText().replaceAll("\\D+", ""));
        Integer b = Integer.parseInt(playersInGame.getText().replaceAll("\\D+", ""));
        Assert.assertTrue(d > b, "Число игроков сейчас больше, чем онлайн");
    }

    //навести на Новое и интересное и выбрать "лидеры продаж"
    public void leadersOfSells() {
        Actions action = new Actions(driver);
        action.moveToElement(newAndInt);
        action.perform();
        wait.until(ExpectedConditions.visibilityOf(popUpMenu));
        leadersOfSells.click();
    }
    //нажать кнопку Посмотреть больше лидеров продаж
    public void seeMoreLeadersOfSells() {
        wait.until(ExpectedConditions.visibilityOf(seemoreLeadersOfSells));
        //прокрутка страницы
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        seemoreLeadersOfSells.click();
    }

    public void selectCheckboxSteam() {
        steamOSLinuxCheckbox.click();
    }

    public void selectCheckboxKoopLan() {
        numberOfPlayers.click();
        this.sleep(2);
        koopLanCheckbox.click();
        this.sleep(2);
    }

    //сравнить количество результатов по запросу соответствует количеству игр в списке todo сделать сравнение
    public void compareResultAndGames() {
        Integer f = Integer.parseInt(result.getText().replaceAll("\\D+", ""));
        actionCheckbox.click();
        this.sleep(2);
        List<WebElement> list = driver.findElements(By.xpath("//*[@id= 'search_resultsRows']//a"));
        Assert.assertEquals(f, list.size());
    }

    public void compareFirstResult() {
        String fN = firstResultName.getAttribute("innerText");
        String fD = firstResultDate.getAttribute("innerText");
//        String fP = firstResultPrice.getAttribute("innerText"); для проверки
        Integer fP = Integer.parseInt(firstResultPrice.getText().replaceAll("\\D+", ""));
        firstResult.click();
        this.sleep(2);
        String fNC = firstResultNameClick.getAttribute("innerText");
        String fDC = firstResultDateClick.getAttribute("innerText");
//        String fPC = firstResultPriceClick.getAttribute("innerText"); для проверки
        Integer fPC = Integer.parseInt(firstResultPriceClick.getText().replaceAll("\\D+", ""));


        Assert.assertEquals(fN,  fNC, "Название не совпадает");
        Assert.assertEquals(fD,  fDC, "Дата не совпадает");
        Assert.assertEquals(fP,  fPC, "Цена не совпадает");
    }



    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {

        }
    }
}
