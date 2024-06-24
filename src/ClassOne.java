import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ClassOne {

	String url = "https://magento.softwaretestingboard.com/";
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();

	@BeforeTest
	public void mybeforetest() {
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	@Test(priority = 1)
	public void addrandomitem() throws InterruptedException {

		WebElement container = driver.findElement(By.cssSelector(".product-items.widget-product-grid"));
		List<WebElement> items = container.findElements(By.tagName("li"));
		int myindex = rand.nextInt(items.size());
		items.get(myindex).click();
		Thread.sleep(2000);
		if (driver.getCurrentUrl().contains("fusion") || driver.getCurrentUrl().contains("push")) {
			WebElement addtocart = driver.findElement(By.id("product-addtocart-button"));

			addtocart.click();

		} else {

			WebElement sizes = driver
					.findElement(By.xpath("//div[@class='swatch-attribute size']//div[@role='listbox']"));
			List<WebElement> size = sizes.findElements(By.tagName("div"));
			int mysize = rand.nextInt(size.size());
			size.get(mysize).click();
			WebElement colors = driver
					.findElement(By.xpath("//div[@class='swatch-attribute color']//div[@role='listbox']"));
			List<WebElement> color = colors.findElements(By.tagName("div"));
			int mycolor = rand.nextInt(color.size());
			color.get(mycolor).click();
			WebElement addtocart = driver.findElement(By.id("product-addtocart-button"));

			addtocart.click();
		}

		WebElement msg = driver
				.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
		boolean actual = msg.getText().contains("You added");
		boolean expected = true;
		Assert.assertEquals(actual, expected);

	}

	@Test(priority = 2)
	public void checkout() throws InterruptedException {

		Thread.sleep(2000);
		String checkout = "https://magento.softwaretestingboard.com/checkout/cart/";
		driver.get(checkout);

		WebElement proceed = driver.findElement(By.xpath("//span[normalize-space()='Proceed to Checkout']"));
		proceed.click();
		Thread.sleep(9000);

		WebElement email = driver.findElement(By.id("customer-email"));
		WebElement firstname = driver.findElement(By.name("firstname"));
		WebElement lastname =driver.findElement(By.name("lastname"));
		WebElement street = driver.findElement(By.name("street[0]"));
		WebElement city = driver.findElement(By.name("city"));
		WebElement state = driver.findElement(By.name("region_id"));
		WebElement zip = driver.findElement(By.name("postcode"));
		WebElement country = driver.findElement(By.name("country_id"));
		Select select = new Select(country);

		WebElement phone = driver.findElement(By.name("telephone"));
		
		
		
		email.sendKeys("dareee8778n@hotmail.com");
		firstname.sendKeys("dalya");
		lastname.sendKeys("kh");
		street.sendKeys("amman");
		city.sendKeys("amman");
		state.sendKeys("amman");
		zip.sendKeys("12345");
		select.selectByVisibleText("Jordan");
		phone.sendKeys("9634227843");
		
		
		
		Thread.sleep(5000);
		WebElement next = driver.findElement(By.cssSelector(".button.action.continue.primary"));
		next.click();
		Thread.sleep(3000);
		
		WebElement placeorder = driver.findElement(By.cssSelector(".action.primary.checkout"));
		placeorder.click();
	}
	
	@Test (priority = 3)
	public void signup () {
		
		WebElement createaccount = driver.findElement(By.xpath("//a[@class='action primary']"));
		createaccount.click();
		WebElement pass =driver.findElement(By.id("password"));
		pass.sendKeys("Dal0206902");
		WebElement confairmpass = driver.findElement(By.id("password-confirmation"));
		confairmpass.sendKeys("Dal0206902");
		WebElement submite = driver.findElement(By.cssSelector(".action.submit.primary"));
		submite.click();
		
		
		String expectedmsg = "Thank you for registering with Main Website Store.";
		WebElement actual = driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
		
	 String actuall =actual.getText();
		Assert.assertEquals(expectedmsg, actuall);
		
	}

	@AfterTest
	public void myaftertest() {

	}

}
