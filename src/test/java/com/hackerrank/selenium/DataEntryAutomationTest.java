package com.hackerrank.selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DataEntryAutomationTest {
//    private static JettyServer server=null;
//    private static WebDriver driver=null;
//    private static String pagUrl=null;
//
//    @BeforeClass
//    public static void setup(){
//        driver = new HtmlUnitDriver(BrowserVersion.CHROME,true){
//
//            @Override
//            protected WebClient newWebClient(BrowserVersion version){
//                WebClient webClient=super.newWebClient(version);
//                webClient.getOptions().setThrowExceptionOnScriptError();
//            }
//        }
//    }

    private static WebDriver driver = null;
    private static String pagUrl = null;
    private static Map<String, String> data;

    @BeforeClass
    public static void setup() throws IOException {
        File file = new File("src/test/resources/webdriver/chromedriver.exe");
        String absolutePath = file.getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", absolutePath);
        //System.setProperty("webdriver.chrome.driver","C:\\DataEntryAutomationSelenium\\src\\test\\webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);


        data = new HashMap() {
            {
                put("11", "day");
                put("July", "month");
                put("1990", "year");
                put("favorite_language", "Perl,Ruby");
                put("favorite_os", "send_newsletter,agree");
                put("favorite_idea", "Red");
            }
//            {
//                put("11", "day");
//                put("July", "month");
//                put("1990", "year");
//                put("favorite_language", "Java,Python");
//                put("favorite_os", "Linux,Mac OSX");
//                put("favorite_idea", "IntelliJ IDEA");
//            }
        };

        DataEntryAutomation.data = data;

        pagUrl = "https://html.form.guide/checkbox/html-form-with-checkbox/";
        //pagUrl = "https://www.w3.org/WAI/tutorials/forms/grouping/?course=8.01.1#";
        //pagUrl = "https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/checkbox";
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }

    @Test
    public void testFillDateOfBirth() throws InterruptedException {
        DataEntryAutomation.fillDateOfBirth(driver, pagUrl);
        List<WebElement> selects = driver.findElements(By.tagName("select"));

        assertEquals(4, selects.size());

        for (WebElement element : selects) {
            Select select = new Select(element);
            System.out.println(select.getFirstSelectedOption().getText());
            //assertEquals(data.get(select.getFirstSelectedOption().getText()), "month");
        }
    }

    @Test
    public void testAnswerQuestions() throws InterruptedException {
        DataEntryAutomation.answerQuestions(driver, pagUrl);

        List<WebElement> checkboxes = driver.findElements(By.tagName("input"));
        assertEquals(4, checkboxes.size());

        Set<WebElement> checked = new HashSet<>();
        for (WebElement select : checkboxes) {
            if (select.isSelected()) {
                checked.add(select);
            }
        }

        assertEquals(1, checked.size());
        for (WebElement element : checked) {
            WebElement e2 = driver.findElement(By.xpath("/html/body/div/div[2]/article/p[3]"));
            String testNew2 = e2.getText().trim();
            System.out.println(e2.getText().trim());
            assertTrue(data.get(e2.getText().trim()).contains("Red"));
        }
    }
}
