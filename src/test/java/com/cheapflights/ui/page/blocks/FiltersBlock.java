package com.cheapflights.ui.page.blocks;

import com.cheapflights.ui.page.abstractpages.AbstractSearchPage;
import com.cheapflights.ui.utils.webdrivertools.WebDriverTools;
import com.cheapflights.ui.utils.webdrivertools.WebDriverToolsDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.CheckBox;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.logging.Logger;


@Name("Filters container")
@FindBy(xpath = "//div[@class='filterListContainer']")
public class FiltersBlock extends HtmlElement {

    @Name("One stop checkbox")
    @FindBy(xpath = "//label[@data-name='2']")
    private CheckBox twoStops;

    @Name("Two stops checkbox")
    @FindBy(xpath = "//label[@data-name='1']")
    private CheckBox oneStop;

    @Name("Max flight duration sider")
    @FindBy(xpath = "//div[@aria-label='Maximum flight duration']")
    private WebElement slider;

    @Name("Page load progress bar")
    @FindBy(xpath = "//div[contains(@id, 'legdur-content')]//div[contains(@class,'activeRange')]")
    private WebElement progress;

    @Name("Sort section dropdown")
    @FindBy(xpath = "//div[@data-name='sort-section']")
    private WebElement sortSection;

    @Name("Sort section dropdown values")
    @FindBy(xpath = "//div[@data-name='sort-section']//div//div/span")
    private WebElement sortSectionValue;

    private SortDropDownBlock sortDropDownBlock;

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    private WebDriver driver = AbstractSearchPage.getDriver();

    public void chooseNonStopFlights() {
        logger.info("Waiting for the search results page to load");
        new WebDriverToolsDecorator(new WebDriverTools(AbstractSearchPage.getDriver())).waitForJSandJQueryToLoad(driver);
        logger.info("Unchecking one stop checkbox");
        oneStop.click();
        new WebDriverToolsDecorator(new WebDriverTools(AbstractSearchPage.getDriver())).waitForJSandJQueryToLoad(driver);
        logger.info("Unchecking two stops checkbox");
        twoStops.click();
        logger.info("Waiting for the page to update according to the chosen filters");
        new WebDriverToolsDecorator(new WebDriverTools(AbstractSearchPage.getDriver())).waitForJSandJQueryToLoad(driver);

    }

    public void modifyDuration(int divider, int multiplier) {
        //Here Javascript is used as a workaround as moveToElement doesn't scroll the element into view in ForeFox and MoveTargetOutOfBoundsException is thrown
        //https://github.com/mozilla/geckodriver/issues/776
        ((JavascriptExecutor) AbstractSearchPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", slider);
        Dimension size = progress.getSize();
        int sliderWidth = size.getWidth();
        logger.info("Modifying flight duration");
        Actions builder = new Actions(AbstractSearchPage.getDriver());
        builder.moveToElement(slider).click()
                .dragAndDropBy
                        (slider, -((sliderWidth / divider) * multiplier), 0)
                .build()
                .perform();
        logger.info("Waiting for the page to update according to the chosen duration");
        new WebDriverToolsDecorator(new WebDriverTools(AbstractSearchPage.getDriver())).waitForJSandJQueryToLoad(driver);

    }

    public void sortByCheapest() {
        if (!(sortSectionValue.getText().equals("Cheapest"))) {
            logger.info("Opening sorting drop-down");
            sortSection.click();
            sortDropDownBlock.sortByCheapest();
        }

    }


}
