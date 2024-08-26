package com.pw;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.MouseButton;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlaywrightTest {

    @Test
    public void TC001() {
        //initializes a Pw instance
        //resource management, automatic closing
        //hierarchy of instances?
        try (Playwright playwright = Playwright.create()) {
            Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
            page.navigate("https://best.aliexpress.com/?aff_fcid=01e416c57ec1422bac82972dd4954911-1721025471174-06888-_97Pc0X&tt=CPS_NORMAL&aff_fsk=_97Pc0X&aff_platform=portals-tool&sk=_97Pc0X&aff_trace_key=01e416c57ec1422bac82972dd4954911-1721025471174-06888-_97Pc0X&terminal_id=f561587a58a54716842fd880e92c09a9");
            /*this method directly uses a css selector
             method can be use when you have a straightforward selector like an ID, class or other css and you want to directly fill a input field
             input field has a unique css selector: <input id="search-words" type="text">
             */
            page.fill("#search-words", "volleyball");
            /*page.getByLabel("Search words").fill("ball");
              when you want to select the input field based on the text of its associated label
              <label for="search-words">Search words</label>
              */
            page.waitForTimeout(5000);
            /*dot before a name indicates that the selector is for a class
             # ID
             p type(tag name)
             * universal selector
             ...
             */
            page.click(".search--submit--2VTbd-T");
            page.waitForTimeout(5000);

        }
    }


    //bolo by vhodnejsie napisat methods pre opakovane kroky, skopirovat kod alebo je este nejake ina moznost?
    //ak by predosli test fungoval mohla by som pokracovat od isteho kroku?
    // ako vlastne preconditions zapracovat do testu?

    @Test
    public void TC002() {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();

            Page page = context.newPage();
            page.navigate("https://www.aliexpress.com/w/wholesale-volleyballs.html?spm=a2g0o.best.discover_more.3.1db92c2599xkpd", new Page.NavigateOptions().setTimeout(60000));

            //z toho velkeho mnozstva veci na ktore mozem kliknut, co si mam vybrat?
            page.waitForTimeout(5000);

            Locator parentDiv = page.locator(".list--galleryWrapper--29HRJT4");

            Locator childDivs = parentDiv.locator("div[data-tticheck='true']");

            List<ElementHandle> divList = childDivs.elementHandles();

            divList.get(0).hover();

            Page newPage = context.waitForPage(() -> {
                divList.get(0).click();
            });

            newPage.waitForLoadState();
            newPage.waitForTimeout(5000);


            Locator button = page.locator(".comet-v2-btn.comet-v2-btn-large.add-to-cart--addtocart--Qhoji3M.comet-v2-btn-important");
            button.scrollIntoViewIfNeeded();
            button.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));

            button.click();

            newPage.waitForTimeout(500);





        }

    }

    @Test
    public void TC003() {
        try (Playwright playwright = Playwright.create()) {
            Page page = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
            page.navigate("https://best.aliexpress.com/?aff_fcid=01e416c57ec1422bac82972dd4954911-1721025471174-06888-_97Pc0X&tt=CPS_NORMAL&aff_fsk=_97Pc0X&aff_platform=portals-tool&sk=_97Pc0X&aff_trace_key=01e416c57ec1422bac82972dd4954911-1721025471174-06888-_97Pc0X&terminal_id=f561587a58a54716842fd880e92c09a9");

        }
    }

}
