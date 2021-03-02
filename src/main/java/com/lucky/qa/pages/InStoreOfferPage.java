package com.lucky.qa.pages;


import com.lucky.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class InStoreOfferPage extends BasePage {

    @FindBy(xpath = "//*/div/div[2]/div/div[3]/div[1]/div")
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
    private List<WebElement> mainSubCategoriesList;

    @FindBy(xpath = "//div[1]/div[1]/div[2]/div/div/div[2]/div/form/div/div/div/label")
    private List<WebElement> mainSubCategories;

    @FindBy(xpath = "//*[@class = 'collapse show']//form/div/div/div/label")
    private List<WebElement> categoriesCheckbox;

    @FindBy(xpath = "//*[@class = 'collapse show']//label")
    private List<WebElement> subCategories;

    @FindBy(xpath = "//div[1]/div[3]/button[2]")
    private WebElement applyBtn;

    @FindBy(xpath = "//div[1]/div[3]/button[1]")
    private WebElement clearBtn;

    @FindBy(xpath = "//*[@class = 'collapse show']")
    private WebElement expandedMenu;

    public InStoreOfferPage(WebDriver driver) {
        super(driver);
    }

    public void selectInStoreItem() {
        waitVisibilityOfAllElements(inStoreItems);
        for (WebElement item : inStoreItems) {
            clickButton(item);
            break;
        }
    }

    public void filterByLocation(String filterLocation) {
        //waitVisibilityOfElement(expandedMenu);
        for (WebElement location : locations) {
            if (getText(location).equals(filterLocation)) {
                clickButton(location);
                break;
            }

        }

    }

    public void selectSubLocation(String FilterSubLocation) {
        waitVisibilityOfElement(expandedMenu);
        for (WebElement subLocation : subLocations) {
            if (getText(subLocation).equals(FilterSubLocation)) {
                WebElement element = subLocation.findElement(By.tagName("input"));
                forceClickElement(element);
                break;
            }


        }

    }

    public void clickCategory(String Category) {
        waitVisibilityOfAllElements(inStoreItems);
        waitVisibilityOfAllElements(mainCategories);
        for (WebElement mainCategory : mainCategories) {
            if (getText(mainCategory).equals(Category)) {
                clickButton(mainCategory);
                break;
            }
        }
    }

    public void clickMainSubCategoryList(String Category) {
        for (WebElement mainSubCategory : mainSubCategoriesList) {
            if (getText(mainSubCategory).equals(Category)) {
                clickButton(mainSubCategory);
                break;
            }
        }
    }

    public void clickMainSubCategory(String Category) {
        for (WebElement mainSubCategory : categoriesCheckbox) {
            if (getText(mainSubCategory).equals(Category)) {
                forceClickElement(mainSubCategory);
                break;
            }
        }
    }

    public void clickSubCategory(String Category) {
        for (WebElement subCategory : categoriesCheckbox) {
            if (getText(subCategory).equals(Category)) {
                forceClickElement(subCategory);
                break;
            }
        }
    }

    public void designPattern(CategoryType categoryType, String mainCategory,
                              String mainSubCategory, String SubCategory) {

        switch (categoryType) {

            case mainSubCategory:
                clickCategory(mainCategory);
                clickMainSubCategory(mainSubCategory);
                break;
            case SubCategory:
                clickCategory(mainCategory);
                clickMainSubCategoryList(mainSubCategory);
                clickSubCategory(SubCategory);
                break;
            default:
                break;
        }


    }

    public void clickApplyBtn() {
        clickButton(applyBtn);
    }

    public void clickClearBtn() {
        clickButton(clearBtn);
    }

    public enum CategoryType {
        mainSubCategory, SubCategory
    }

}
