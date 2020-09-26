    package com.lucky.qa.pages;

    import com.lucky.qa.commons.BasePage;
    import org.openqa.selenium.NoSuchElementException;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.FindBy;
    import org.openqa.selenium.support.PageFactory;

    import static org.openqa.selenium.By.className;


    public class HomePage extends BasePage {


        @FindBy(className = "react-toast-notifications")
        private WebElement  toastSuccesMessage;

        public HomePage(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver, this);
        }

        public HomePage goToThePage() {
                  try {
                      driver.get("https://wcb.staging.thelucky.io/Home");
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
                  return this;
              }

        public void dropDownLanguage() {
            driver.findElement(className("dropdown-toggle")).click();
        }

        public void selectItemLanguage() {
            driver.findElement(className("dropdown-item")).click();
        }

        public boolean appearSucces() {
            try {
                toastSuccesMessage.isDisplayed();
                return true;
            } catch(NoSuchElementException e) {
                return false;
            }

        }

    }