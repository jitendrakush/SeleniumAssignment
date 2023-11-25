import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Tmobile {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.t-mobile.com/tablets");
        Thread.sleep(5000);

        selectFilter("Brands", "Apple", "Samsung", "TCL");
        selectFilter("Deals", "New", "Special offer");
        selectFilter("Operating System", "iPadOS");
//        selectFilter("Deals", "all");
    }

    public static void selectFilter(String... filterOptions) {
        boolean flag = false;
        String filter = "//legend[contains(text(),'v1')]";
        String selectAllFilterOptions = "//div[@aria-label='v1']/mat-checkbox/label/span[1]";
        String singleFilterOption = "//div[@aria-label='v1'] //span[normalize-space()='v2']";
        driver.findElement(By.xpath((filter.replace("v1", filterOptions[0])))).click();
        for (String searchFilters : filterOptions) {
            flag = searchFilters.equals("all");
        }
        if (flag) {
            List<WebElement> getAllFilters = driver.findElements(By.xpath(selectAllFilterOptions.replace("v1", filterOptions[0])));
            for (WebElement selectFilter : getAllFilters) selectFilter.click();
        } else {
            for (int i = 1; i < filterOptions.length; i++) {
                driver.findElement(By.xpath(singleFilterOption.replace("v1", filterOptions[0]).replace("v2", filterOptions[i]))).click();
            }
        }
    }
}

