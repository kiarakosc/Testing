package com.pw;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Exercise02 {

    /**
     * Feature: Verify Products Page
     * <p>
     * Scenario: Navigate to the products page and verify the URL
     * Given I navigate to the home page
     * When I click on the "Products" button
     * Then I should be redirected to the products page
     **/
    @Test
    public void verifyProducts() {

        //Given I navigate to the home page
        try (Playwright playwright = Playwright.create()) {
            Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
            page.navigate("http://automationexercise.com");

            //step 3.

            //When I click on the "Products" button
            page.click("button[class='btn btn-success']");
            //page.click("a[href='/test_cases']");

            //Then I should be redirected to the products page
            Assertions.assertEquals("https://automationexercise.com/test_cases", page.url(), "okj 1");

            page.waitForTimeout(2000);

        }

    }

    @Test
    public void verifyAllProducts() {

        //Given I navigate to the home page
        try (Playwright playwright = Playwright.create()) {
            Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
            page.navigate("http://automationexercise.com");

            //step 3.

            //When I click on the "Products" button
            //page.click("button[class='material-icons card_travel']");
            page.click("a[href='/products']");

          //  Assertions.assertEquals("https://automationexercise.com/products", page.url(), "okj 1");


            List<ElementHandle> items = page.locator(".features_items").elementHandles();

            for (ElementHandle item : items) {
                boolean isVisible = page.isVisible(String.valueOf(item));
                if (isVisible) {
                    System.out.println("The products list is not visible.");
                }
            }

            page.click("a[href='/product_deails/1']");
            Assertions.assertEquals("https://www.automationexercise.com/product_details/1", page.url(), "okj 2");

            Locator productName = page.locator("h2:has-text('Blue Top')");
            Locator category = page.locator("p:has-text('Category')");
            Locator price = page.locator("span:has-text('Rs. 500')");
            Locator availability = page.locator("b:has-text('Availability')");
            Locator condition = page.locator("b:has-text('Condition')");

            boolean isProductNameVisible = productName.isVisible();
            boolean isCategoryVisible = category.isVisible();
            boolean isPriceVisible = price.isVisible();
            boolean isAvailabilityVisible = availability.isVisible();
            boolean isConditionVisible = condition.isVisible();

            if (isProductNameVisible && isCategoryVisible && isPriceVisible &&
                    isAvailabilityVisible && isConditionVisible) {
                System.out.println("All product details are visible.");
            } else {
                System.out.println("Some product details are not visible:");
            }


        }
    }
}
