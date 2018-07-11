package availableexamplestest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import io.restassured.RestAssured;


public class StatusCodes {
	WebDriver driver;
	JavascriptExecutor js;
	String url;
	
	public StatusCodes(WebDriver driver, JavascriptExecutor js) {
		this.driver = driver;
		this.js = js;
	}
	
	private void revertToHomePage() {
		js.executeScript("document.querySelector('body > div:nth-child(2) > a:nth-child(1)').click();");
	}
	
	public void statusCodesButtonClick() {
		js.executeScript("document.querySelector('#content > ul > li:nth-child(37) > a').click();");
		String title = js.executeScript("return document.querySelector('#content > div > h3').innerHTML;").toString();
		assertEquals(title, "Status Codes");
	}
	
	public void statusCode404ButtonClick() {
		js.executeScript("document.querySelector('#content > div > ul > li:nth-child(3) > a').click();");
		String msg = js.executeScript("return document.querySelector('#content > div > p').textContent;").toString();
		url = js.executeScript("return document.URL").toString();
		assertTrue(msg.contains("This page returned a 404 status code."));
	}
	
	public void verify404StatusCode() {
		int statusCode = RestAssured.get(url).statusCode();
		assertEquals(statusCode, 404);
		revertToHomePage();
	}
}
