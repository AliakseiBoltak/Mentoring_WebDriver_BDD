package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static utils.RandomString.getRandomString;

public class GmailMainPage extends AbstractPage {

    @FindBy(xpath = "//span[@class='ait']/div[@class='G-asx T-I-J3 J-J5-Ji']")
    private WebElement expandMoreButton;

    @FindBy(xpath = "//a[@href='https://www.google.com/intl/en/policies/terms/']")
    private WebElement termsEndConditionsLink;

    @FindBy(css = ".aic .z0 div")
    private WebElement composeButton;

    @FindBy(xpath = "//textarea[@name='to']")
    private WebElement recipientInput;

    @FindBy(xpath = "//input[@name='subjectbox']")
    private WebElement subjectInput;

    @FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
    private WebElement bodyInput;

    @FindBy(xpath = "//td[@class='Hm']/img[@class='Ha']")
    private WebElement saveAndClose;

    @FindBy(xpath = "//a[contains(text(), 'Drafts')]")
    private WebElement draftsLink;

    @FindBy(xpath = "//div[text()='Send']")
    private WebElement sendButton;

    @FindBy(xpath = "//*[@title='Sent']")
    private WebElement sentLink;

    private By sentLinkLocator = By.xpath("//*[@title='Sent']");

    @FindBy(xpath = "//tr[@class='TD']/td[@class='TC']")
    private WebElement noDrafts;

    @FindBy(xpath = "//a[@href='https://accounts.google.com/SignOutOptions?hl=en-GB&continue=https://mail.google.com/mail&service=mail']")
    private WebElement imageButton;

    @FindBy(xpath = "//*[contains(text(), 'Sign out')]")
    private WebElement signOutButton;

    @FindBy(xpath = "//input[@placeholder = 'Search mail']")
    private WebElement searchField;

    private static final String EMAIL_SUBJECT = getRandomString(10);
    private static final String EMAIL_BODY = getRandomString(20);
    private static final String RECIPIENT_EMAIL = "aaboltak@gmail.com";

    public GmailMainPage pressComposeButton() {
        browser.waitForElementVisible(composeButton);
        composeButton.click();
        return new GmailMainPage();
    }

    public GmailMainPage fillRecipientInput() {
        browser.waitForElementVisible(recipientInput);
        recipientInput.sendKeys(RECIPIENT_EMAIL);
        return new GmailMainPage();
    }

    public GmailMainPage fillSubjectInput() {
        browser.waitForElementVisible(subjectInput);
        subjectInput.sendKeys(EMAIL_SUBJECT);
        return this;
    }

    public GmailMainPage fillBodyInput() {
        browser.waitForElementVisible(bodyInput);
        bodyInput.sendKeys(EMAIL_BODY);
        return this;
    }

    public GmailMainPage saveAndCloseEmail() {
        browser.waitForElementAndClick(saveAndClose);
        return this;
    }

    public GmailMainPage clickOnDraftsLink() {
        browser.waitForElementAndClick(draftsLink);
        return this;
    }

    public boolean isEmailAppearedInDrafts() {
        browser.waitForElementVisible(By.xpath(String.format(
                "//span[contains(text(), 'Draft')]/following::span[contains(text(), '%s')]/following::span[contains(text(), '%2s')]",
                EMAIL_SUBJECT, EMAIL_BODY)));
        return browser.findElement(By.xpath(String.format(
                "//span[contains(text(), 'Draft')]/following::span[contains(text(), '%s')]/following::span[contains(text(), '%2s')]",
                EMAIL_SUBJECT, EMAIL_BODY))).isDisplayed();
    }

    public GmailMainPage clickOnDraftEmail() {
        browser.waitForElementAndClick(By.xpath(String.format(
                "//span[contains(text(), 'Draft')]/following::span[contains(text(), '%1s')]/following::span[contains(text(), '%2s')]",
                EMAIL_SUBJECT, EMAIL_BODY)));
        return this;
    }

    public GmailMainPage waitForRecipientToLoadInEmail() {
        browser.waitForElementVisible(By.xpath("//span[contains(text(), 'Aliaksei Boltak')]"));
        return this;
    }

    public GmailMainPage sendEmail() {
        browser.waitForElementAndClick(sendButton);
        return this;
    }

    public GmailMainPage clickOnSentLink() {
        List<WebElement> elements = browser.findElements(sentLinkLocator);
        for (WebElement elem : elements) {
            if (elem.isDisplayed()) {
                elem.click();
                break;
            }
        }
        return this;
    }

    public boolean isEmailAppearedInSentFolder() {
        browser.waitForElementVisible(By.xpath(String.format(
                "//span[contains(text(), 'Aliaksei')]/following::span[contains(text(), '%1s')]/following::span[contains(text(), '%2s')]",
                EMAIL_SUBJECT, EMAIL_BODY)));
        return browser.findElement(By.xpath(String.format("//span[contains(text(), 'Aliaksei')]/following::span[contains(text(), '%1s')]/following::span[contains(text(), '%2s')]",
                EMAIL_SUBJECT, EMAIL_BODY))).isDisplayed();
    }

    public GmailMainPage clickOnImageButton() {
        browser.waitForElementAndClick(imageButton);
        return this;
    }

    public GmailMainPage acceptAlert() {
        browser.acceptAlert();
        return this;
    }

    public GmailMainPage clickOnSignOutButton() {
        browser.waitForElementAndClick(signOutButton);
         return this;
    }
}
