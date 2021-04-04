package com.lucky.qa.pages;

import com.lucky.qa.base.BasePage;
import com.lucky.qa.utilities.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class WalletPage extends BasePage {
    public WalletPage(WebDriver driver) {
        super(driver);
    }

    Helper helper = new Helper();

    @FindBy(className = "h2-text")
    private WebElement userbalance;

    @FindBy(xpath = "//section/div[1]/div/p[1]")
    private WebElement userCashbackBalance;

    @FindBy(xpath = "//*[@class = 'btn btn-primary']")
    private WebElement requestCashoutBtn;

    @FindBy(id = "cashoutMethod-tab-2")
    private WebElement amanBtn;

    @FindBy(id = "cashoutMethod-tab-1")
    private WebElement bankAccountBtn;

    @FindBy(xpath = "//*[@id='cashoutMethod-tabpane-2']//form//input")
    private WebElement amanAmountField;

    @FindBy(xpath = "//*[@id='cashoutMethod-tabpane-1']//form//input")
    private WebElement bankAccountAmountField;

    @FindBy(xpath = "//footer/div/button")
    private WebElement continueBtn;

    @FindBy(xpath = " //section/h2[1]")
    private WebElement successMessage;

    @FindBy(xpath = "//section/h2[2]")
    private WebElement cashoutConfirmationMessage;

    @FindBy(xpath = "//div[3]/a/button")
    private WebElement continueShoppingBtn;

    @FindBy(xpath = "//*[@id='cashoutMethod-tabpane-1']//div[2]/form/div/input")
    private List<WebElement> inputFields;

    @FindBy(className = "wallet-body")
    private WebElement transactionSection;

    @FindBy(className = "error error-message text-center")
    private WebElement errorMessage;

    @FindBy(xpath = "//div/nav/div/a/span")
    private WebElement luckyLogo;

    @FindBy(xpath = "//div//table//table/tbody/tr[5]")
    private WebElement arabicText;

    @FindBy(xpath = "//div//table//table/tbody/tr[4]/td/b/b/text()")
    private WebElement englishText;

    @FindBy(xpath = "//*[@class ='transaction-row'][1]/div[1]/div[2]/p[1]")
    private WebElement lastTransactionName;

    @FindBy(xpath = "//*[@class ='transaction-row'][1]/div[1]/div[2]/p[2]")
    private WebElement lastTransactionDate;

    @FindBy(xpath = "//*[@class ='transaction-row'][1]/div[1]/div[1]/p")
    private WebElement lastTransactionType;

    @FindBy(xpath = "//div[2]/div[3]/div[2]/p[2]")
    private WebElement lastTransactionStatus;

    public Double getUserTotalBalance() throws InterruptedException {
        Thread.sleep(3000);
        String[] balanceAsString = getText(userbalance).split("EGP ");
        return Double.parseDouble(balanceAsString[1]);
    }

    public Double getUserCashbackBalance() {
        waitVisibilityOfElement(transactionSection);
        String[] amountInCashback = getText(userCashbackBalance).split("Including EGP ");
        String[] balanceAsString = amountInCashback[1].split(" Cashback");
        DecimalFormat df = new DecimalFormat("0.00");
        Assert.assertTrue(Double.parseDouble(df.format(Double.parseDouble(balanceAsString[0]))) >= 100
                , "user does not have enough cashabck");
        return Double.parseDouble(df.format(Double.parseDouble(balanceAsString[0])));
    }

    public void clickRequestCashoutBtn() {
        clickButton(requestCashoutBtn);
    }

    public void clickAman() {
        clickButton(amanBtn);
    }

    public Double addAmountToCashoutByAman(String amount) {
        clearField(amanAmountField);
        addText(amanAmountField, amount);
        clickButton(amanAmountField);
        return Double.parseDouble(amount);
    }

    public Double addAmountToCashoutByBankAccount(String amount) {
        addText(bankAccountAmountField, amount);
        clickButton(bankAccountAmountField);
        return Double.parseDouble(amount);
    }

    public void clickBankAccountBtn() {
        clickButton(bankAccountBtn);
    }

    public void addBankAccountFields() {
        helper.setPropertiesFileName("BankAccountData.properties");
        inputFields.get(0).sendKeys(helper.getValuesFromPropertiesFile("FullName"));
        inputFields.get(1).sendKeys(helper.getValuesFromPropertiesFile("BankName"));
        inputFields.get(2).sendKeys(helper.getValuesFromPropertiesFile("HolderAddress"));
        inputFields.get(3).sendKeys(helper.getValuesFromPropertiesFile("BranchName"));
        inputFields.get(4).sendKeys(helper.getValuesFromPropertiesFile("BankAddress"));
        inputFields.get(5).sendKeys(helper.getValuesFromPropertiesFile("AccountNumber"));
        inputFields.get(6).sendKeys(helper.getValuesFromPropertiesFile("SWIFTCode"));
        inputFields.get(7).sendKeys(helper.getValuesFromPropertiesFile("IBAN"));
    }

    public void clickContinueBtn() {
        clickButton(continueBtn);
        waitVisibilityOfElement(successMessage);
    }

    public String checkCashoutSuccessMessage() {
        Assert.assertEquals("Cashout successful!", getText(successMessage));
        String[] cashoutMessage = getText(cashoutConfirmationMessage).split("You just cashout EGP ");
        String[] cashoutAmount = cashoutMessage[1].split(" succesfully");
        return cashoutAmount[0];
    }

    public ArrayList<Double> userBalanceBeforeCashout() throws InterruptedException {
        ArrayList<Double> values = new ArrayList<>();
        values.add(getUserTotalBalance());
        values.add(getUserCashbackBalance());
        return values;
    }

    public void cashoutMethod(String method, String cashoutAmount) {
        switch (method) {
            case "Aman":
                clickAman();
                addAmountToCashoutByAman(cashoutAmount);
                break;
            case "Bank Account":
                moveToTab(0);
                clickBankAccountBtn();
                addAmountToCashoutByBankAccount(cashoutAmount);
                addBankAccountFields();
                break;
        }
    }

    public String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public void checkIfTransactionReflected(String transactionMethod) {
        Assert.assertEquals(lastTransactionType.getText(), "Cashout");
        Assert.assertEquals(lastTransactionDate.getText(), getCurrentDate());
        Assert.assertEquals(lastTransactionStatus.getText(), "Pending");
        if (transactionMethod.equals("Aman")) {
            Assert.assertEquals(lastTransactionName.getText(), "Voucher");
        } else {
            Assert.assertEquals(lastTransactionName.getText(), "Banktransfer");
        }
    }
}
