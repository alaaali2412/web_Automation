package com.lucky.qa.pages;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.utilities.DatabaseHelper;
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
    private WebElement userBalance;

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

    @FindBy(xpath = "//footer//*[@type='submit']")
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

    @FindBy(xpath = "//*[@class = 'error error-message text-center'][1]")
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

    @FindBy(xpath = "//*[@class ='transaction-row'][1]/div[2]/p[2]")
    private WebElement lastTransactionStatus;

    @FindBy(id = "formGridZip")
    private WebElement mobileNumberField;

    @FindBy(className = "pincode-input-text")
    private List<WebElement> otpFields;

    @FindBy(xpath = "//*[@class= 'modal-footer']/button[1]")
    private WebElement confirmBtn;

    @FindBy(id = "root")
    private WebElement pageContent;

    @FindBy(xpath = "//*[@class = 'react-toast-notifications__toast__content css-1ad3zal']")
    private WebElement toastMessage;

    @FindBy(xpath = "  //*[@class='link-text']")
    private WebElement otpCounter;

    @FindBy(xpath = "//*[@class= 'invalid-feedback']")
    private List<WebElement> BankFieldsErrorMessages;

    public Double getUserTotalBalance() throws InterruptedException {
        Thread.sleep(4000);
        String[] balanceAsString = getText(userBalance).split(" ");
        return Double.parseDouble(balanceAsString[1]);
    }

    public Double getUserCashbackBalance() {
        waitVisibilityOfElement(transactionSection);
        DecimalFormat df = new DecimalFormat("0.00");
        String[] amountInCashback = getText(userCashbackBalance).split(" ");
        Assert.assertTrue(Double.parseDouble(df.format(Double.parseDouble(amountInCashback[2]))) >= 100
                , "user does not have enough cashback");
        return Double.parseDouble(df.format(Double.parseDouble(amountInCashback[2])));
    }

    public void clickRequestCashoutBtn() {
        clickButton(requestCashoutBtn);
        if (isAlertPresent()) {
            driver.switchTo().alert().accept();
        }
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
    }

    public void checkCashoutSuccessMessage(String successMsg) {
        waitVisibilityOfElement(successMessage);
        Assert.assertEquals(successMsg, getText(successMessage));
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

    public void checkIfTransactionReflected(String transactionMethod, String transactionNameLanguage, String statusLanguage) {
        Assert.assertEquals(lastTransactionType.getText(), transactionNameLanguage);
        Assert.assertEquals(lastTransactionDate.getText(), getCurrentDate());
        Assert.assertEquals(lastTransactionStatus.getText(), statusLanguage);
        if (transactionMethod.equals("Aman")) {
            Assert.assertEquals(lastTransactionName.getText(), "Voucher");
        } else {
            Assert.assertEquals(lastTransactionName.getText(), "Banktransfer");
        }
    }

    public void addMobileNumber() {
        String mobileNumber = "013" + helper.generateRandomNumber(8);
        addText(mobileNumberField, mobileNumber);
        clickButton(continueBtn);
        waitVisibilityOfElement(otpCounter);
        String value = pageContent.getAttribute("aria-hidden");
        if (value != null) {
            forceClickElement(confirmBtn);
        }
    }

    public void addOTPCode(String email, String language) {
        helper.setPropertiesFileName("RegistrationData.properties");
        DatabaseHelper.setUpDBConnection(language);
        String otp = DatabaseHelper.getValueFromDatabase("SELECT CashOutMobileVerificationOtp from LuckyUser WHERE email = '" +
                helper.getValuesFromPropertiesFile(email) + "'");
        System.out.println("sdfgfdsfg" + otp);
        String[] otpDigits = otp.split("");
        for (int i = 0; i < otpDigits.length; i++) {
            addText(otpFields.get(i), otpDigits[i]);
        }
        clickButton(continueBtn);
        waitVisibilityOfElement(toastMessage);
        DatabaseHelper.closeDBConnection();
    }

    public void addWrongOTP() {
        String otp = helper.generateRandomNumber(6);
        String[] otpDigits = otp.split("");
        for (int i = 0; i < otpDigits.length; i++) {
            addText(otpFields.get(i), otpDigits[i]);
        }
        clickButton(continueBtn);
    }

    public void assertOtpErrorMessage(String errorMsg) {
        waitVisibilityOfElement(errorMessage);
        Assert.assertTrue(errorMessage.isDisplayed());
        Assert.assertEquals(errorMessage.getText(), errorMsg);
    }

    public void checkThatSuccessDisplayed() {
        Assert.assertTrue(toastMessage.isDisplayed());
    }

    public void resetMobileNumberInDataBase(String email, String language) {
        helper.setPropertiesFileName("RegistrationData.properties");
        DatabaseHelper.setUpDBConnection(language);
        DatabaseHelper.updateDatabaseValues("UPDATE LuckyUser SET IsMerged = '0', PhoneNumber = '" +
                helper.generateRandomText(4) + "' WHERE email = '" + helper.getValuesFromPropertiesFile(email) + "'");
        DatabaseHelper.closeDBConnection();
    }

    public void checkWelcomeBonus(String transactionNameLanguage, String statusLanguage) {
        Assert.assertEquals(lastTransactionType.getText(), transactionNameLanguage);
        Assert.assertEquals(lastTransactionStatus.getText(), statusLanguage);
    }

    public void approveTheCashoutTransaction(String language, String email) {
        helper.setPropertiesFileName("LoginData.properties");
        DatabaseHelper.setUpDBConnection(language);
        DatabaseHelper.updateDatabaseValues("UPDATE AffiliateCashout SET StatusId= '2' WHERE id =(SELECT top 1 id from AffiliateCashout" +
                " WHERE CreatedBy In (SELECT id from LuckyUser WHERE email = '" + helper.getValuesFromPropertiesFile(email) + "') order by id desc ) ");
        DatabaseHelper.closeDBConnection();
    }

    public void checkTheCashoutTransactionApproved(String status) {
        refreshCurrentPage();
        Assert.assertEquals(lastTransactionStatus.getText(), status);
    }

    public void clearCashoutTransactionsInDB(String language, String email) {
        helper.setPropertiesFileName("LoginData.properties");
        DatabaseHelper.setUpDBConnection(language);
        DatabaseHelper.executeQuery("DELETE from  AffiliateCashout WHERE CreatedBy In (SELECT id from LuckyUser WHERE email = '"
                + helper.getValuesFromPropertiesFile(email) + "')");
    }

    public void checkBankAccountFieldsValidationMessages(String name, String bankName, String address, String branchName,
                                                         String bankAddress, String accountNumber, String swiftCode, String iban) {
        Assert.assertEquals(BankFieldsErrorMessages.get(0).getText(), name);
        Assert.assertEquals(BankFieldsErrorMessages.get(1).getText(), bankName);
        Assert.assertEquals(BankFieldsErrorMessages.get(2).getText(), address);
        Assert.assertEquals(BankFieldsErrorMessages.get(3).getText(), branchName);
        Assert.assertEquals(BankFieldsErrorMessages.get(4).getText(), bankAddress);
        Assert.assertEquals(BankFieldsErrorMessages.get(5).getText(), accountNumber);
        Assert.assertEquals(BankFieldsErrorMessages.get(6).getText(), swiftCode);
        Assert.assertEquals(BankFieldsErrorMessages.get(7).getText(), iban);
    }

    public void addArabicValuesOnBankAccountFields() {
        for (WebElement inputField : inputFields) {
            addText(inputField, helper.generateRandomArabicText(5));
        }
    }

    public void addInvalidIBANFormat() {
        deleteTextInField(inputFields.get(7));
        addText(inputFields.get(7), helper.generateRandomNumber(10));
    }

    public void checkIBANInvalidFormatErrorMessage(String errorMessage) {
        Assert.assertEquals(BankFieldsErrorMessages.get(7).getText(), errorMessage);
    }
}
