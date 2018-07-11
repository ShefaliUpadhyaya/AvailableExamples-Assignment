package availableexamplestest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class BasicAuth {
	WebDriver driver;
	JavascriptExecutor js;
	
	public BasicAuth(WebDriver driver, JavascriptExecutor js) {
		this.driver = driver;
		this.js = js;
	}
	
	private void revertToHomePage() {
		js.executeScript("window.location = 'http://10.0.31.161:9292/'");
	}
	
	public void basicAuthButtonClick() {
		js.executeScript("document.querySelector('#content > ul > li:nth-child(2) > a').click();");
	}
	
	public void authenticationPrompt() {
		js.executeScript("window.location = 'http://admin:admin@10.0.31.161:9292/basic_auth'");
		String title = js.executeScript("return document.querySelector('#content > div > h3').innerHTML;").toString();
		assertEquals(title, "Basic Auth");
		revertToHomePage();
	}
}
