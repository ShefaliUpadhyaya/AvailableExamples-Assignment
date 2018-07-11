package availableexamplestest;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Hovers {
	WebDriver driver;
	JavascriptExecutor js;
	Robot robot;
	WebElement usernameDisplay, profileDisplay;
	
	public Hovers(WebDriver driver, JavascriptExecutor js) {
		this.driver = driver;
		this.js = js;
		try {
			robot = new Robot();
		} 
		catch (AWTException e) {}
	}
	
	private void revertToHomePage() {
		js.executeScript("document.querySelector('body > div:nth-child(2) > a:nth-child(1)').click();");
	}
	
	private Long getNoOfImages() {
		return (Long) js.executeScript("return document.getElementsByClassName('figure').length;");
	} 
	
	public void hoversButtonClick() {
		js.executeScript("document.querySelector('#content > ul > li:nth-child(22) > a').click();");
		assertTrue(getNoOfImages()==3);
	}
	
	public void verifyImage1() {
		robot.mouseMove(250, 300);
		usernameDisplay = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/h5"));
		profileDisplay = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a"));
		assertTrue(usernameDisplay.isDisplayed());
		assertTrue(profileDisplay.isDisplayed());
	}
	
	public void hoverOutOfImage1() {
		robot.mouseMove(200, 200);
		assertFalse(usernameDisplay.isDisplayed());
		assertFalse(profileDisplay.isDisplayed());
		revertToHomePage();
	}
}
