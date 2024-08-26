package com.pw;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


 /**
 * Feature: User Registration and Account Deletion

 * Scenario: Register a new user and delete the account
     *  Given I navigate to the home page
     *  When I click on the "Login"
     *  And I fill in the sign-up form with valid details
     *  And I click the "Sign Up" button
     *  Then I should be on the sign-up page
     *  And I complete the registration form
     *  And I click the "Create Account" button
     *  Then I should see "Logged in as" text
     *  When I click on the "Delete Account" link
     *  Then I should see "Account Deleted!" text
     *  And I return to the home page
 **/


public class Exercise {

    // Test data
    String name = "Janko";
    String password = "569MJw*";
    String email = "yr65pk3@mail.com";

    @Test
    public void RegisterUser() {
        //Given("I navigate to the home page")
        try (Playwright playwright = Playwright.create()) {
            Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
            page.navigate("http://automationexercise.com");

            //When I click on the "Login"
            page.click("a[href='/login']");


            //And I fill in the sign-up form with valid details
            //ako je vhodne vyhladavat elementy?
            page.fill("[data-qa='signup-name']", name);
            page.fill("[data-qa='signup-email']", email);

            page.waitForTimeout(100);

            //And I click the "Sign Up" button
            page.click("button[data-qa='signup-button']");

            Assertions.assertEquals("https://automationexercise.com/signup", page.url(), "okj 1");

            //Then I should be on the sign-up page
            //overit ci zobrazena stranka zodpoveda ocakavanej url
            assertThat(page).hasURL("https://automationexercise.com/signup");
            page.click("#id_gender2");

            //And I complete the registration form
            page.fill("[data-qa='password']", password);

            page.selectOption("#days", "10");
            page.selectOption("#months", "January");
            page.selectOption("#years", "2000");

            page.click("#newsletter");


            page.fill("[data-qa='first_name']", name);
            page.fill("[data-qa='last_name']", "569MJw*");
            page.fill("[data-qa='company']", "Mrkvickovo s.r.o.");
            page.fill("[data-qa='address']", "Oranzova 14");
            page.selectOption("#country", "Canada");
            page.fill("[data-qa='state']", "Nova Scotia");
            page.fill("[data-qa='city']", "Halifax");
            page.fill("[data-qa='zipcode']", "045 98");
            page.fill("[data-qa= 'mobile_number']", "+56 475 368 522");

            //And I click the "Create Account" button
            page.click("button[data-qa='create-account']");
            page.click("a[href='/']");


            //Then I should see "Logged in as" text
            //moze sa toto pozovat za istu formu assetu?
            Locator loginText = page.locator("a:has-text('Logged in as') b");
            boolean isVisible = loginText.isVisible();
            if (isVisible) {
                System.out.println("The login is visible.");
            } else {
                System.out.println("The login text is not visible.");
            }

            //When I click on the "Delete Account" link
            page.click("a[href='/delete_account']");

            //Then I should see "Account Deleted!" text
            Locator deleteText = page.locator("b:has-text('Account Deleted!')");
            boolean visible = deleteText.isVisible();
            if (visible) {
                System.out.println("The delete account text is visible.");
            } else {
                System.out.println("The delete account text is not visible.");
            }

            //And I return to the home page
            //je to to iste ako kliknut na continue button?
            page.click("a[href='/']");

            page.waitForTimeout(10000);

        }
    }

    /**
     * Feature: User Login and Account Deletion

     * Scenario: Log in with correct credentials and delete account
         * Given I navigate to the home page
         * When I click on the "Login"
         * And I fill in the email field with "janko@gmail.com"
         * And I fill in the password field with "123"
         * And I click the "Login" button
         * Then I should see "Logged in as" text
         * When I click on the "Delete Account" link
         * Then I should see "Account Deleted!" text
         * And I return to the home page
     **/

    @Test
    public void LogInCorrectCredential() {

        //Given I navigate to the home page
        try (Playwright playwright = Playwright.create()) {
            Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
            page.navigate("http://automationexercise.com");

            //When I click on the Login
            page.click("a[href='/login']");
            Assertions.assertEquals("http://automationexercise.com/login", page.url(), "okj 1");

            //And I fill in the email field with "janko@gmail.com"
            page.fill("[data-qa='login-email']", "janko@gmail.com");

            //And I fill in the password field with "123"
            page.fill("[data-qa='login-password']", "123");

            //And I click the Login button
            page.click("button[data-qa='login-button']");

            //Then I should see "Logged in as" text
            Locator loginText = page.locator("a:has-text('Logged in as') b");
            boolean isVisible = loginText.isVisible();
            if (isVisible) {
                System.out.println("The login text is visible.");
            } else {
                System.out.println("The login text is not visible.");
            }

            //When I click on the "Delete Account"
            page.click("a[href='/delete_account']");

            //Then I should see "Account Deleted!" text
            Locator deleteText = page.locator("b:has-text('Account Deleted!')");
            boolean visible = deleteText.isVisible();
            if (visible) {
                System.out.println("The delete account text is visible.");
            } else {
                System.out.println("The delete account text is not visible.");
            }

            //And I return to the home page
            page.click("a[href='/']");
            Assertions.assertEquals("http://automationexercise.com", page.url(), "okj 2");

            page.waitForTimeout(10000);
        }

    }

     /**
      * Feature: Contact Us Form Submission

      * Scenario: Submit the contact us form with valid details
         * Given I navigate to the home page
         * When I click on the "Contact Us" link
         * Then I should see "Get In Touch" text
         * And I fill in the contact form with valid details
         * And I upload a file
         * And I click the "Submit" button
         * Then I should see a success message
      **/

    @Test
    public void contactUsForm() {
        //Given I navigate to the home page
        try (Playwright playwright = Playwright.create()) {
            Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
            page.navigate("http://automationexercise.com");

            //When I click on the "Contact Us" link
            page.click("a[href='/contact_us']");

            //Then I should see "Get In Touch" text
            Locator getInTouchText = page.locator("h2.title.text-center:has-text('Get In Touch')");
            boolean isVisible = getInTouchText.isVisible();
            if (isVisible) {
                System.out.println("The text is visible.");
            } else {
                System.out.println("The text is not visible.");
            }

            //And I fill in the contact form with valid details
            page.fill("[data-qa='name']", name);
            page.fill("[data-qa='email']", email);
            page.fill("[data-qa='subject']", " A Greeting");
            page.fill("[data-qa='message']", "Hello");

            //And I upload a file
            Locator fileInput = page.locator("input[type='file']");
            fileInput.setInputFiles(Paths.get("C:\\Users\\kiara\\OneDrive\\Obrázky\\Snímky obrazovky\\Screenshot 2023-12-09 192239.png"));

            //And I click the "Submit" button
            // page.click("input[data-qa='submit-button']");

            //Then I should see a success message


            //And("I return to the home page")
            page.click("a[href='/']");
            Assertions.assertEquals("http://automationexercise.com", page.url(), "okj 1");


            page.waitForTimeout(10000);

        }
    }

}
