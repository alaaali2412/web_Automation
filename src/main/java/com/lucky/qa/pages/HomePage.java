    package com.lucky.qa.pages;

    import com.lucky.qa.commons.BasePage;
    import org.openqa.selenium.NoSuchElementException;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;
    import org.openqa.selenium.support.PageFactory;
    import org.testng.Assert;

    import static org.openqa.selenium.By.className;


    public class HomePage extends BasePage {

        private String baseURL = "https://wcb.staging.thelucky.io/Home";

        @FindBy(className = "react-toast-notifications")
        private WebElement  toastSuccesMessage;

        @FindBy(xpath = "//*[@id=\"responsive-navbar-nav\"]/div/div[4]/a")


        private WebElement inStoreOffers;

        public HomePage(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }

        public HomePage goToThePage() {
                  driver.get(baseURL);
                  return this;
              }

        public void dropDownLanguage() {
            driver.findElement(className("dropdown-toggle")).click();
        }

        public void selectItemLanguage() {
            driver.findElement(className("dropdown-item")).click();
        }

        public void appearSuccess() {
            Assert.assertTrue(toastSuccesMessage.isDisplayed());
            System.out.println("Toast message success");
        }

       public void clickInStoreOffers(){
            click(inStoreOffers);
       }


    }