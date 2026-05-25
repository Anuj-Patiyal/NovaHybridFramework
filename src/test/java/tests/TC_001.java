package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.*;

public class TC_001 {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeClass
    public void setup() {

        // Initialize Playwright
        playwright = Playwright.create();

        // Launch browser in headless mode
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(true)
        );

        // Create browser context
        context = browser.newContext();

        // Open new page
        page = context.newPage();

        // Navigate to application
        page.navigate("https://demoqa.com");

        System.out.println("Application launched successfully.");
    }

    @Test
    public void verifyTextBoxForm() {

        // Open Elements section
        page.getByText("Elements").click();

        // Open Text Box page
        page.getByText("Text Box").click();

        // Fill form
        page.fill("#userName", "John Doe");

        page.fill("#userEmail", "john.doe@test.com");

        page.fill("#currentAddress", "New Delhi, India");

        page.fill("#permanentAddress", "Bangalore, India");

        // Submit form
        page.locator("#submit").scrollIntoViewIfNeeded();

        page.locator("#submit").click();

        // Validation
        Assert.assertTrue(
                page.locator("#output").isVisible(),
                "Form submission failed."
        );

        System.out.println("Text Box Test Passed.");
    }

    @AfterClass
    public void tearDown() {

        page.close();

        context.close();

        browser.close();

        playwright.close();

        System.out.println("Browser closed successfully.");
    }
}