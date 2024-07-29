package tests;

import org.testng.annotations.Test;

public class Test1_ComparePlayersPageObject extends BaseTest {

    @Test
    public void test1() {
        mainPage.openPage("https://store.steampowered.com");
        mainPage.clickAboutButton();
        mainPage.comparePlayersAndGamers();
        mainPage.openPage("https://store.steampowered.com");

    }
}
