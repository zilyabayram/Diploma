package test;


import com.codeborne.selenide.logevents.SelenideLogger;
import data.DBHelper;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DBHelper.cleanDataBase;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPayPage {

    @BeforeEach
    public void openPage() {
        open("http://localhost:8080/");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    public void tearDown() {
        cleanDataBase();
    }
    @Test
    void testBuyUsingApprovedCardWithValidInformationAndReturnSuccessNotification() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithValidInformation();
        payPage.initializeCard(cardInfo);
        payPage.notificationSuccess();
    }

    @Test
    void testBuyUsingApprovedCardWithInvalidMonth() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidMonth();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }

    @Test
    void testBuyUsingApprovedCardWithEmptyMonth() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyMonth();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithMonth00() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithMonth00();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithInvalidYear() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidYear();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }

    @Test
    void testBuyUsingApprovedCardWithPastYear() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithPastYear();
        payPage.initializeCard(cardInfo);
        payPage.notificationPastYear();
    }

    @Test
    void testBuyUsingApprovedCardWithEmptyYear() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyYear();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithEmptyCardHolder() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCardHolder();
        payPage.initializeCard(cardInfo);
        payPage.notificationEmptyCardHolder();
    }

    @Test
    void testBuyUsingApprovedCardWithCardHolderNameOnly() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithCardHolderNameOnly();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

  @Test
    void testBuyUsingApprovedCardWithRusCardHolder() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithRusCardHolder();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithCardHolderWithNumbers() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithCardHolderWithNumbers();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithLongCardHolderName() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithLongCardHolderName();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithInvalidCVV() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidCVV();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithEmptyCVV() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCVV();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingCardWithIncompleteNumber() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateCardWithIncompleteCardNumber();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingDeclinedCardWithValidInformationAndReturnErrorNotification() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.initializeCard(cardInfo);
        payPage.notificationError();

    }

    @Test
    void testBuyUsingCardWithRandomNumber() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateCardWithRandomNumber();
        payPage.initializeCard(cardInfo);
        payPage.notificationError();
        var expected = DataHelper.getDeclinedStatus();
        var actual = DBHelper.getPayStatus();
        assertEquals(expected, actual);
    }

    @Test
    void testBuyUsingApprovedCardWithValidInformationAndReturnApprovedStatus() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateApprovedCardWithValidInformation();
        payPage.initializeCard(cardInfo);
        payPage.notificationSuccess();
        var expected = DataHelper.getApprovedStatus();
        var actual = DBHelper.getPayStatus();
        assertEquals(expected, actual);
    }

    @Test
    void testBuyUsingDeclinedCardWithValidInformationAndReturnDeclinedStatus() {
        var payPage = new MainPage().buyDebit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.initializeCard(cardInfo);
        var expected = DataHelper.getDeclinedStatus();
        var actual = DBHelper.getPayStatus();
        assertEquals(expected, actual);
    }


}
