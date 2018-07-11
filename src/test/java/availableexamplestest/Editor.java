package availableexamplestest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Editor {
	WebDriver driver;
	JavascriptExecutor js;
	
	public Editor(WebDriver driver, JavascriptExecutor js) {
		this.driver = driver;
		this.js = js;
	}
	
	private void revertToHomePage() {
		js.executeScript("document.querySelector('body > div:nth-child(2) > a:nth-child(1)').click();");
	}
	
	public void editorButtonClick() {
		js.executeScript("document.querySelector('#content > ul > li:nth-child(39) > a').click();");
		String title = js.executeScript("return document.querySelector('#content > div > h3').innerHTML;").toString();
		assertEquals(title, "An iFrame containing the TinyMCE WYSIWYG Editor");
	}
	
	public void clearAndEnterText() {
		js.executeScript("document.querySelector('#mce_0_ifr').contentWindow.document.getElementsByTagName('p')[0].textContent = '';");
		js.executeScript("document.querySelector('#mce_0_ifr').contentWindow.document.getElementsByTagName('p')[0].textContent = 'QA InfoTech';");
		
		driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));
		WebElement textarea = driver.findElement(By.xpath("//*[@id=\"tinymce\"]/p"));
		Actions actions = new Actions(driver);
		actions.moveToElement(textarea);
		actions.click();
		actions.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		actions.build().perform();
		driver.switchTo().defaultContent();
		
		js.executeScript("document.querySelector('#mceu_3 > button').click();");
		String strongText = js.executeScript("return document.querySelector('#mce_0_ifr').contentWindow.document.getElementsByTagName('strong')[0].textContent; ").toString();
		assertEquals(strongText, "QA InfoTech");
		revertToHomePage();
	}
}
