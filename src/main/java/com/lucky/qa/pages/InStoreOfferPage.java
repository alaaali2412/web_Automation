package com.lucky.qa.pages;


import com.lucky.qa.common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class InStoreOfferPage extends BasePage {

    @FindBy(css = "div.col-12.col-md-6.col-lg-4 ")
    private List<WebElement> inStoreItems;

    @FindBy(css = "button.btn.btn-primary")
    private WebElement getOfferBtn;

    @FindBy(css = "div.inStoreFilteration.filteration > div:nth-child(2)>div")
    private List<WebElement> locations;

    @FindBy(css = ".collapse.show>div>form>div>div")
    private List<WebElement> subLocations;

    @FindBy(css = ".inStoreFilteration.filteration > div:nth-child(4)> div")
    private List<WebElement> mainCategories;

    @FindBy(css = ".collapse.show>div>form>.mb-3>.accordion")
    private List<WebElement> mainSubCategoriesList;

    @FindBy(css = ".collapse.show>div>form>div>div>div>label")
    private List<WebElement> subCategoriesList;

    @FindBy(css = ".inStoreFilteration.filteration .btn.btn-primary.col")
    private WebElement applyBtn;

    @FindBy(css = ".inStoreFilteration.filteration .btn.btn-outline-primary.col")
    private WebElement clearBtn;

    @FindBy(css = ".collapse.show")
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
        for (WebElement mainSubCategory : subCategoriesList) {
            if (getText(mainSubCategory).equals(Category)) {
                forceClickElement(mainSubCategory);
                break;
            }
        }
    }

    public void clickSubCategory(String Category) {
        for (WebElement subCategory : subCategoriesList) {
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
        scrollToViewElement(applyBtn);
        clickButton(applyBtn);
    }

    public void clickClearBtn() {
        clickButton(clearBtn);
        scrollToTopOfScreen();
    }

    public enum CategoryType {
        mainSubCategory, SubCategory
    }
}
