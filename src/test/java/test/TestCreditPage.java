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

public class TestCreditPage {

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
    void testBuyCreditUsingApprovedCardWithValidInformationAndReturnSuccessNotification() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithValidInformation();
        payPage.initializeCard(cardInfo);
        payPage.notificationSuccess();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithInvalidMonth() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidMonth();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithEmptyMonth() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyMonth();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithMonth00() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithMonth00();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithInvalidYear() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidYear();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidExpirationDate();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithPastYear() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithPastYear();
        payPage.initializeCard(cardInfo);
        payPage.notificationPastYear();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithEmptyYear() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyYear();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithEmptyCardHolder() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCardHolder();
        payPage.initializeCard(cardInfo);
        payPage.notificationEmptyCardHolder();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithCardHolderNameOnly() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithCardHolderNameOnly();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithRusCardHolder() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithRusCardHolder();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithCardHolderWithNumbers() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithCardHolderWithNumbers();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyUsingApprovedCardWithLongCardHolderName() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithLongCardHolderName();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithInvalidCVV() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithInvalidCVV();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingApprovedCardWithEmptyCVV() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithEmptyCVV();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingCardWithIncompleteNumber() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateCardWithIncompleteCardNumber();
        payPage.initializeCard(cardInfo);
        payPage.notificationInvalidString();
    }

    @Test
    void testBuyCreditUsingDeclinedCardWithValidInformationAndReturnErrorNotification() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.initializeCard(cardInfo);
        payPage.notificationError();
    }

    @Test
    void testBuyCreditUsingCardWithRandomNumberAndReturnErrorNotification() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateCardWithRandomNumber();
        payPage.initializeCard(cardInfo);
        var expected = DataHelper.getDeclinedStatus();
        var actual = DBHelper.getPayCreditStatus();
        assertEquals(expected, actual);
    }

    @Test
    void testBuyCreditUsingApprovedCardWithValidInformationAndReturnApprovedStatus() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateApprovedCardWithValidInformation();
        payPage.initializeCard(cardInfo);
        payPage.notificationSuccess();
        var actual = DBHelper.getPayCreditStatus();
        assertEquals("APPROVED", actual);
    }

    @Test
    void testBuyCreditUsingDeclinedCardWithValidInformationAndReturnDeclinedStatus() {
        var payPage = new MainPage().buyCredit();
        var cardInfo = DataHelper.generateDeclinedCardWithValidInformation();
        payPage.initializeCard(cardInfo);
        payPage.notificationError();
        var actual = DBHelper.getPayCreditStatus();
        assertEquals("DECLINED", actual);
    }

}
