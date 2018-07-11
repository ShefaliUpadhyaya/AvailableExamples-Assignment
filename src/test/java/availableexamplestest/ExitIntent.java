package availableexamplestest;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.openqa.selenium.JavascriptExecutor;

public class ExitIntent {
	JavascriptExecutor js;
	Robot robot;
	
	public ExitIntent(JavascriptExecutor js) {
		this.js = js;
		try {
			robot = new Robot();
		} 
		catch (AWTException e) {}
	}
	
	private void revertToHomePage() {
		js.executeScript("document.querySelector('body > div:nth-child(2) > a:nth-child(1)').click();");
	}
	
	public void exitIntentButtonClick() {
		js.executeScript("document.querySelector('#content > ul > li:nth-child(13) > a').click();");
		String title = js.executeScript("return document.querySelector('#content > div.example > h3').innerHTML;").toString();
		assertEquals(title, "Exit Intent");
	}
	
	public void mouseOutsidePageDisplaysModal() {
		robot.mouseMove(0, 0);
		String titleOfModal = js.executeScript("return document.querySelector('#ouibounce-modal > div.modal > div.modal-title > h3').textContent;").toString().toUpperCase();
		assertEquals(titleOfModal, "THIS IS A MODAL WINDOW");
	}
	
	public void defocussingModalDisappearsPopup() {
		robot.mouseMove(450, 450);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		String display = js.executeScript("return document.getElementById('ouibounce-modal').style.display;").toString();
		assertEquals(display, "none");
		revertToHomePage();
	}
}
