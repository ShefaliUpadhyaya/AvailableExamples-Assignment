package availableexamplestest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;

public class FormAuthentication {
	JavascriptExecutor js;
	
	public FormAuthentication(JavascriptExecutor js) {
		this.js = js;
	}
	
	private void revertToHomePage() {
		js.executeScript("document.querySelector('body > div:nth-child(2) > a:nth-child(1)').click();");
	}
	
	private void login() {
		js.executeScript("document.querySelector('#login > button').click();");
	}
	
	private void logout() {
		js.executeScript("document.querySelector('#content > div > a').click();");
	}
	
	public void formAuthenticationButtonClick() {
		js.executeScript("document.querySelector('#content > ul > li:nth-child(18) > a').click();");
		String title = js.executeScript("return document.querySelector('#content > div > h2').innerHTML;").toString();
		assertEquals(title, "Login Page");
	}
	
	public void submitFormWithInvalidCredentials() {
		js.executeScript("document.querySelector('#username').value = 'Invalid';");
		js.executeScript("document.querySelector('#password').value = 'Invalid';");
		login();
		String error_msg = js.executeScript("return document.querySelector('#flash').textContent;").toString();
		assertTrue(error_msg.contains("Your username is invalid!"));
	}
	
	public void submitFormWithValidCredentials() {
		js.executeScript("document.querySelector('#username').value = 'tomsmith';");
		js.executeScript("document.querySelector('#password').value = 'SuperSecretPassword!';");
		login();
		String msg = js.executeScript("return document.querySelector('#flash').textContent;").toString();
		assertTrue(msg.contains("You logged into a secure area!"));
	}
	
	public void logoutOpensFormAuthenticationPage() {
		logout();
		String msg = js.executeScript("return document.querySelector('#flash').textContent;").toString();
		assertTrue(msg.contains("You logged out of the secure area!"));
		revertToHomePage();
	}
}
