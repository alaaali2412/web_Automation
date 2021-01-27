package com.lucky.qa.pages;


import com.lucky.qa.commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class InStoreOfferPage extends BasePage {

    @FindBy(className = "InstoreOffer")
    private WebElement card;

    @FindBy(className = "btn-primary")
    private WebElement getOfferButton;

    @FindBy(className = "modal-content")
    private WebElement modalOffers;

    @FindBy(xpath = "//*/div/div[2]/div/div[3]/div[1]")
    private List<WebElement> inStoreItems;

    @FindBy(xpath = "//div[2]/button")
    private WebElement getOfferBtn;

    @FindBy(xpath = "//section/div/div[1]/div[1]/div[1]/div")
    private List<WebElement> locations;

    @FindBy(xpath = "//*[@class ='collapse show']//form/div/div")
    private List<WebElement> subLocations;

    @FindBy(xpath = "//section/div/div[1]/div[1]/div[2]/div")
    private List<WebElement> mainCategories;

    @FindBy(xpath = "//div[1]/div[2]/div/div/div/div/form/div/div")
    private List<WebElement> mainSubCategories;

    @FindBy(xpath = "//div[1]/div[1]/div[2]/div/div/div[2]/div/form/div/div/div/label")
    private List<WebElement> mainSubCategories2;

    @FindBy(xpath = "//form/div/div/div/div/div/form/div/div/div/label")
    private List<WebElement> subCategories;

    @FindBy(xpath = "//div[1]/div[3]/button[2]")
    private WebElement applyBtn;

    public InStoreOfferPage(WebDriver driver) {
        super(driver);
    }

    public void selectCard() {
        clickButton(card);
    }

    public void getOffer() {
        clickButton(getOfferButton);
    }

    public void checkModalOffer() {
        Assert.assertTrue(modalOffers.isDisplayed());
        System.out.println("Modal appear");
    }

    public void selectInStoreItem() throws InterruptedException {
        for (WebElement item : inStoreItems) {
            Thread.sleep(3000);
            clickButton(item);
            break;
        }
    }

    public void filterByLocation(String filterLocation)  {
        for (WebElement location : locations) {
            if (location.getText().equals(filterLocation)) {
                clickButton(location);
                break;
            }

        }

    }

    public void selectSubLocation(String FiltersubLocation)  {
        actions = new Actions(driver);
        for (WebElement subLocation : subLocations) {
            System.out.println(subLocation.getText());
            if (subLocation.getText().equals(FiltersubLocation)) {
                WebElement element = subLocation.findElement(By.tagName("input"));
                actions.moveToElement(element).click(element).build().perform();
                clickButton(applyBtn);
                break;
            }


        }

    }


    public void filterByCategory(String filterCategory, String filterMainSubCategory, String filterSubCategory) throws InterruptedException {
        actions = new Actions(driver);
        for (WebElement mainCategory : mainCategories) {
            if (mainCategory.getAttribute("class").equals("accordion") && mainCategory.getText().equals(filterCategory)) {
                clickButton(mainCategory);
                Thread.sleep(3000);
                for (WebElement mainSubCategory : mainSubCategories) {
                    //  System.out.println("categry =" + mainSubCategory.getText());
                    if (mainSubCategory.getAttribute("class").equals("accordion")
                            && mainSubCategory.getText().equals(filterMainSubCategory)) {
                        clickButton(mainSubCategory);
                        Thread.sleep(3000);
                        for (WebElement subCategory : subCategories) {
                            if (subCategory.getText().equals(filterSubCategory)) {
                                actions.moveToElement(subCategory).click(subCategory).build().perform();
                                clickButton(applyBtn);
                                break;
                            }
                        }
                    } else {
                        for (WebElement mainSubCategory2 : mainSubCategories2) {
                            if (mainSubCategory2.getText().equals(filterMainSubCategory)) {
                                actions.moveToElement(mainSubCategory2).click(mainSubCategory2).build().perform();
                                clickButton(applyBtn);
                                break;
                            }
                        }

                    }
                }

                break;
            } else if (mainCategory.getText().equals(filterCategory)) {
                WebElement cat = mainCategory.findElement(By.tagName("div"));
                actions.moveToElement(cat).doubleClick(cat).build().perform();
                Thread.sleep(5000);
                clickButton(applyBtn);
                break;
            }

        }

    }
}
