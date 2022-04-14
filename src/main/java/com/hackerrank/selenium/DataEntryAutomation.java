package com.hackerrank.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DataEntryAutomation {
    public static Map<String, String> data;


    public static void fillDateOfBirth(WebDriver driver, String pageUrl) throws InterruptedException {
        String dayKey = "", monthKey = "", yearKey = "";

        driver.get("https://www.plus2net.com/php_tutorial/date-selection.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);


        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (entry.getValue().equals("day")) {
                dayKey = entry.getKey();

            }
            if (entry.getValue().equals("month")) {
                monthKey = entry.getKey();

            }
            if (entry.getValue().equals("year")) {
                yearKey = entry.getKey();

            }
        }


        WebElement date = driver.findElement(By.xpath("//*[@id=\"body_content\"]/table/tbody/tr[1]/td/span[1]/plus2net_index/form/table/tbody/tr/td[2]/select"));
        Select select = new Select(date);
        select.selectByVisibleText(dayKey);

        Thread.sleep(5000);

        WebElement month = driver.findElement(By.xpath("//*[@id=\"body_content\"]/table/tbody/tr[1]/td/span[1]/plus2net_index/form/table/tbody/tr/td[1]/select"));

        Select selectMonth = new Select(month);
        selectMonth.selectByVisibleText(monthKey);


        WebElement year = driver.findElement(By.xpath("//*[@id=\"body_content\"]/table/tbody/tr[1]/td/span[1]/plus2net_index/form/table/tbody/tr/td[3]/input[1]"));
        year.clear();
        year.sendKeys(yearKey);
        Thread.sleep(10000);
    }

    public static void answerQuestions(WebDriver driver, String pageUrl) throws InterruptedException {

        driver.get(pageUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(1000, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);

//        data = new HashMap() {
//            {
//                put("11", "day");
//                put("July", "month");
//                put("1990", "year");
//                put("favorite_language", "Perl,Ruby");
//                put("favorite_os", "send_newsletter,agree");
//                put("favorite_idea", "Red");
//            }
//        };
//

        String testNew = data.get("favorite_idea");
        WebElement e1 = driver.findElement(By.xpath("/html/body/div/div[2]/article/p[3]/input"));
        WebElement e2 = driver.findElement(By.xpath("/html/body/div/div[2]/article/p[3]"));
        String testNew2 = e2.getText().trim();

        if (testNew.equalsIgnoreCase(testNew2)) {
            System.out.println("test");
            e1.click();
            System.out.println("test1");
        }

        List<String> language = new ArrayList<String>();
        language.addAll(List.of(data.get("favorite_language").split(",")));
        System.out.println(language);

        Thread.sleep(10000);
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div/div[2]/article/iframe[2]")));
        driver.switchTo().frame("CodePen Preview for HTML form with checkbox group");

        Thread.sleep(10000);
        WebElement checkbox1 = driver.findElement(By.xpath("//*[@id=\"langs_javascript\"]"));
        WebElement checkbox2 = driver.findElement(By.xpath("//*[@id=\"langs_perl\"]"));
        WebElement checkbox3 = driver.findElement(By.xpath("//*[@id=\"langs_php\"]"));
        WebElement checkbox4 = driver.findElement(By.xpath("//*[@id=\"langs_ruby\"]"));
//        WebElement checkbox5 = driver.findElement(By.xpath("/html/body/form[2]/input[5]"));


        List<WebElement> checkboxes = new ArrayList<>();
        checkboxes.add(checkbox1);
        checkboxes.add(checkbox2);
        checkboxes.add(checkbox3);
        checkboxes.add(checkbox4);
//        checkboxes.add(checkbox5);

        Thread.sleep(10000);
        for (int i = 0; i < language.size(); i++) {
            for (int j = 0; j < checkboxes.size(); j++) {
                String ch = checkboxes.get(j).getAttribute("value");
                //System.out.println("ch: "+ch+"lang: "+language.get(i));
                if (ch.equals(language.get(i))) {
                    checkboxes.get(j).click();
                }
            }
        }

        Thread.sleep(10000);
        driver.switchTo().defaultContent();

        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/div/div[2]/article/iframe[1]")));
        driver.switchTo().frame("CodePen Preview for Simple checkbox");

        List<String> osList = new ArrayList<String>();
        osList.addAll(List.of(data.get("favorite_os").split(",")));
        System.out.println(osList);

        Thread.sleep(5000);

        //WebElement os1 = driver.findElement(By.xpath("//*[@id=\"send_newsletter\"]"));
        WebElement os2 = driver.findElement(By.xpath("//*[@id=\"agree\"]"));
        // WebElement os3 = driver.findElement(By.xpath("/html/body/form/fieldset/div/ul/li[3]/input"));


        List<WebElement> elementsOs = new ArrayList<>();
        //elementsOs.add(os1);
        elementsOs.add(os2);
        // elementsOs.add(os3);


        for (int i = 0; i < osList.size(); i++) {
            for (int j = 0; j < elementsOs.size(); j++) {
                String ch = elementsOs.get(j).getAttribute("name");

                if (ch.equals(osList.get(i))) {
                    Thread.sleep(1000);
                    elementsOs.get(j).click();
                }
            }
        }

    }
}
/**
 * John Ray Zee
 * 567 986 789
 * 5
 * Hey
 * How are you
 * Get Lost
 * Why so
 * Hey you
 * Ray
 * 986
 * 7 4 3
 */