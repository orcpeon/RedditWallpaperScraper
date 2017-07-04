import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import utils.EnumIterator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Emulator {

    String searchUrl;
    WebClient client;
    HtmlPage page;


    public Emulator(String searchUrl) {
        this.searchUrl = searchUrl;
        client = new WebClient();
        createPage();
    }

    public String getSearchUrl() {
        return searchUrl;
    }

    public void setSearchUrl(String searchUrl) {
        this.searchUrl = searchUrl;
    }

    public WebClient getClient() {
        return client;
    }

    public void setClient(WebClient client) {
        this.client = client;
    }

    private void disableJSCSS() {
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
    }

    private void createPage() {
        try {
            disableJSCSS();
            page = client.getPage(searchUrl);
        } catch (IOException e) {
            System.out.println("IO Exception");
            e.printStackTrace();
        }
    }

    //takes xpath expression
    private List<HtmlElement> getElementList(String exp) {
        return page.getByXPath(exp);
    }

    //test method
    public void printTitles() {
        List<String> printableList = new ArrayList<String>();
        List<HtmlElement> titleList = page.getByXPath(
                "//*[@id='siteTable']//*[@class='entry unvoted']//*[@class='title']");
        List<HtmlElement> domainList = page.getByXPath(
                "//*[@id='siteTable']//*[@class='entry unvoted']//*[@class='title']/*[@class='domain']");
        if (!titleList.isEmpty()) {
            for (HtmlElement node : titleList) {
                printableList.add(node.asText());
            }
        } else {
            printableList.add("None found");
        }
        System.out.println(printableList);
        System.out.println("_____");
        System.out.println(chooseFirstLegitLink(titleList).asText());
    }

    private HtmlElement chooseFirstLegitLink(List<HtmlElement> list) {
        HtmlElement returnable = list.get(0); //TODO NICE WAY OF HANDLING NO SUITABLE DOMAINS

        for (HtmlElement node : list) {
            if (EnumIterator.isDomainSuitable(node.getChildNodes().get(2).asText())) { //TODO MAKE INDEPENDENT ON THE LIST
                returnable = node;
            }
        }
        return returnable;
    }



}
