package stepDefinitions;

import AdminPages.*;
import io.cucumber.java.en.*;

public class DocumentationsSteps {

    PageNavigation navigation = new PageNavigation();
    HamburgerMenuPage menu = new HamburgerMenuPage();

    @Given("^browse to ([^\"]*)$")
    public void browseToURL(String urlKey) {
        navigation.loadPage(urlKey);
    }

    @When("^log in Admin page with ([^\"]*) and ([^\"]*)$")
    public void loginAdmin(String name, String password) {
        LoginPage page = new LoginPage();
        page.login(name, password);
    }

    @Then("click hamburger menu")
    public void openHamburgerMenu() {
        menu.openMenu();
    }

    @And("click Documentation")
    public void openDocumentation() {
        menu.openDocumentation();
    }

    @Then("^open ([^\"]*)$")
    public void openDocumentationMenuItem(String item) {
        DocumentationPage page = new DocumentationPage();
        page.openDocumentationMenuItem(item);
    }

    @And("^verify Knowledge Base page was loaded in tab ([^\"]*)$")
    public void verifyKnowledgePageLoaded(int tab) {
        navigation.switchToTab(tab);
        KnowledgeBasePage page = new KnowledgeBasePage();
        page.verifyKnowledgePage();
    }

}
