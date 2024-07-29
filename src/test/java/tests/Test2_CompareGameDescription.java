package tests;

import org.testng.annotations.Test;

public class Test2_CompareGameDescription extends BaseTest{

    @Test
    public void test1() {
        //открытие страницы
        mainPage.openPage("https://store.steampowered.com");
        //открытие попап меню и нажатие лидеры продаж
        mainPage.leadersOfSells();
        //прокрутка страницы и нажатие “Просмотреть больше лидеров продаж”
        mainPage.seeMoreLeadersOfSells();
        //выбор чекбокса Steam
        mainPage.selectCheckboxSteam();
        //выбор чекбокса “Кооператив (LAN)”
        mainPage.selectCheckboxKoopLan();
        //проверка что количество результатов по запросу соответствует количеству игр в списке
        mainPage.compareResultAndGames();
        //проверка Названия, даты и цены
        mainPage.compareFirstResult();
    }

}
