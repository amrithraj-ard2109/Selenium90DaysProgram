package assign;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zalando {
	public static JavascriptExecutor js;

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","./drivers/Chromedriver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--disable-notifications");
		ChromeDriver driver= new ChromeDriver(options);		
		WebDriverWait wait = new WebDriverWait(driver,20);
		driver.get("https://www.zalando.com/");
		Thread.sleep(1000);
		Alert al=driver.switchTo().alert();
		String text = al.getText();
		System.out.println(text);
		al.accept();
		driver.manage().window().maximize();		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.findElementByXPath("//li/a[@class='nav_link nav_link-gb']").click();
		Thread.sleep(1000);
		driver.findElementByXPath("(//span[text()='Women'])[2]").click();
		
		driver.findElementByXPath("//span[text()='Clothing']").click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'That’s OK')]")));
			driver.findElementByXPath("//button[contains(text(),'That’s OK')]").click();  //click that's ok button at the bottom
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		driver.findElementByXPath("//li/a[text()='Coats']").click();
		Thread.sleep(3000);
		Actions builder=new Actions(driver);
		builder.click(driver.findElementByXPath("//span[text()='Material']")).perform();
		builder.click(driver.findElementByXPath("//span[text()='cotton (100%)']")).perform();
		driver.findElementByXPath("//button[text()='Save']").click();
		Thread.sleep(2000);
		builder.click(driver.findElementByXPath("//span[text()='Length']")).perform();
		builder.click(driver.findElementByXPath("//span[text()='thigh-length']")).perform();
		driver.findElementByXPath("//button[text()='Save']").click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='cat_brandName-2XZRz cat_ellipsis-MujnT'])[1]")));
		
		List<WebElement> brandNamesList = driver.findElementsByXPath("//div[@class='cat_brandName-2XZRz cat_ellipsis-MujnT']");
		
		int size = brandNamesList.size();
						
		for (int i = 0; i < size; i++) 
		{
			
			String brandName = brandNamesList.get(i).getText();
			
				if (brandName.equals("JUNAROSE - by VERO MODA")) {
					
					driver.findElementByXPath("//div[text()='JUNAROSE - by VERO MODA']").click();
					break;
				}
				
				if((i==(size-1)) && driver.findElementByXPath("//a[@title='next page']").isEnabled()) 
				{
					
					driver.findElementByXPath("//a[@title='next page']").click();
					Thread.sleep(4000);
					
					List<WebElement> secondPageBrandNamesList = driver.findElementsByXPath("//div[@class='cat_brandName-2XZRz cat_ellipsis-MujnT']");
					
						for (int j = 0; j < secondPageBrandNamesList.size(); j++) 
						{
							String brandNamesInSecondPage = secondPageBrandNamesList.get(j).getText();
							
							if (brandNamesInSecondPage.equals("JUNAROSE - by VERO MODA")) 
							{											
								driver.findElementByXPath("//div[text()='JUNAROSE - by VERO MODA']").click();
								break;
							}
							
						}
				   }
			}
		
		driver.findElementByXPath("//button[@id='picker-trigger']").click();
		driver.findElementByXPath("//span[text()='Manufacturer sizes']").click();
		driver.findElementByXPath("//span[text()='M']").click();
		
		String delivery = driver.findElementByXPath("//div[@class='c2gWar']").getText();
		if(delivery.contains("Free"))
		{
			driver.findElementByXPath("//span[text()='Add to bag']").click();
		}
		
		WebElement gotobag = driver.findElementByXPath("//a[contains(@class,'z-1-button z-coast-base-primary-accessible')]");
		wait.until(ExpectedConditions.visibilityOf(gotobag)).click();
		Thread.sleep(2000);

		WebElement bag = driver.findElementByXPath("//span[text()='Your bag']"); 
		builder = new Actions(driver);
		builder.moveToElement(bag).perform();
		
		String estimatedDelivery = driver.findElementByXPath("(//span[@class='z-2-text z-2-text-body z-2-text-black'])[1]").getText();
		System.out.println("Estimated delivery is"+" "+estimatedDelivery);
		
		driver.findElementByXPath("(//div[text()='Go to checkout'])[1]").click();
		 Thread.sleep(2000);
		driver.findElementById("login.email").sendKeys("amrithraj@gmail.com");
		
		driver.findElementByXPath("//span[text()='Login']").click();
		
		String errorMsg = driver.findElementByXPath("//span[text()='This field is required']").getText();
		System.out.println("Error messsage is"+" "+errorMsg);
		
		
		
		
		
		
		
		/*driver.findElementByXPath("(//a[@class='cat_imageLink-OPGGa'])[1]").click();
		  Thread.sleep(2000);
		  /*
		WebElement vermoda = driver.findElementByXPath("//div[text()='JUNAROSE - by VERO MODA']");
		   js.executeScript("arguments[0].scrollIntoView(false);", vermoda);
		   vermoda.click();
		   Thread.sleep(3000);*/

	}

}
