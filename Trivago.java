package assign;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Trivago {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","./drivers/Chromedriver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--disable-notifications");
		options.addArguments("--incognito");
		ChromeDriver driver= new ChromeDriver(options);		
		WebDriverWait wait = new WebDriverWait(driver,20);
		driver.get("https://www.trivago.com/");
		driver.manage().window().maximize();		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.findElementById("querytext").sendKeys("Agra");
		driver.findElementByXPath("//span[text()='City - Uttar Pradesh, India']").click();
		Thread.sleep(4000);
		WebElement check=driver.findElementByXPath("(//span[@class='dealform-button__text-wrapper'])[1]");
		check.click();
		Thread.sleep(1000);
		check.click();
		//driver.findElementByXPath("//time[@datetime='2020-07-15']").click();
		driver.findElementByXPath("//table//tr//td[@class='cal-day-wrap']//time[@datetime='2020-07-31']").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//table//tr//td[@class='cal-day-wrap']//time[@datetime='2020-08-15']").click();
		//driver.findElementByXPath("//time[@datetime='2020-07-30']").click();
		Thread.sleep(1000);
		driver.findElementByXPath("(//button[@class='circle-btn circle-btn--plus'])[2]").click();
		WebElement age = driver.findElementByXPath("//select[@class='select icon-bg-icn_arrow-sml-down dropdown-arrow ages-input__select']");
		Select sel=new Select(age);
		sel.selectByValue("4");

		Thread.sleep(2000);

		driver.findElementByXPath("//button[text()='Apply']").click();
		
		WebElement allTypes = driver.findElementByXPath("//span[text()='All types']");
		Actions builder=new Actions(driver);
		builder.moveToElement(allTypes).perform();
		builder.click(driver.findElementByXPath("//input[@value='Hotel']")).perform();
		builder.click(driver.findElementByXPath("//span[text()='4-star hotels']")).perform();
		builder.click(driver.findElementById("filter-popover-done-button")).perform();
		
		builder.moveToElement(driver.findElementByXPath("//span[text()='All']")).perform();
		builder.click(driver.findElementByXPath("//span[text()='Very good']")).perform();
		Thread.sleep(2000);
				
		driver.findElementByXPath("//li[@class='toolbar-list__item toolbar-list__item--location js-toolbar__item--location js-toolbar-location']").click();

		Thread.sleep(2000);

		WebElement city = driver.findElementByXPath("//select[@name='pois']");

		Select sel1=new Select(city);

		sel1.selectByValue("159477/500");

		Thread.sleep(2000);

		driver.findElementByXPath("//button[@id='filter-popover-done-button']").click();

		Thread.sleep(3000);

		WebElement more = driver.findElementByXPath("(//strong[@class='filter-item__label'])[5]");
		builder.moveToElement(more).perform();
		Thread.sleep(1000);
		driver.findElementByXPath("//label[text()='Air conditioning']").click();
		
		Thread.sleep(1000);
		driver.findElementByXPath("//label[text()='WiFi']").click();
		
		Thread.sleep(2000);

		driver.findElementById("more-filters-search").sendKeys("Restaurant");
		Thread.sleep(1000);
		driver.findElementByXPath("//mark[text()='Restaurant']").click();
		Thread.sleep(1000);
		
		driver.findElementByXPath("//button[@id='filter-popover-done-button']").click();
		WebElement sort = driver.findElementByXPath("//select[@id='mf-select-sortby']");
		Select sel2=new Select(sort);
		sel2.selectByValue("7");
		Thread.sleep(2000);
				
		List<WebElement> hotelnames = driver.findElementsByXPath("//span[@data-qa='item-name']");
		
		int size = hotelnames.size();
		
		System.out.println("Total number of hotels fetched : " +size);
		
		String hotel = driver.findElementByXPath("(//span[@class='item-link name__copytext'])[1]").getText();
		System.out.println("Hotel name is "+""+hotel);
		
		driver.findElementByXPath("(//span[@class='accommodation-list__text--f5334'])[1]").click();
		
		String currentUrl = driver.getCurrentUrl();
		System.out.println("URL:"+""+currentUrl);
		
		driver.get(currentUrl);
		Thread.sleep(4000);
		Set<String> allWindows=driver.getWindowHandles();
		List<String> allHandles=new ArrayList<String>(allWindows);
		driver.switchTo().window(allHandles.get(1));
		
		driver.manage().deleteAllCookies();
		
		driver.navigate().refresh();
		
		String price = driver.findElementByXPath("//div[@class='bui-price-display__value prco-text-nowrap-helper prco-inline-block-maker-helper']").getText();
		System.out.println("Price of the hotel is"+""+price);
		
		driver.findElementByXPath("(//span[@class='bui-button__text'])[1]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//span[@class='bui-button__text'])[1]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//span[@class='bui-button__text'])[10]").click();
		
		driver.quit();
		

	}

}
