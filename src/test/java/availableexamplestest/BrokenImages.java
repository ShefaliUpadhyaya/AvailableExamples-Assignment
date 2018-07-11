package availableexamplestest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;

public class BrokenImages {
	JavascriptExecutor js;
	
	public BrokenImages(JavascriptExecutor js) {
		this.js = js;
	}
	
	private void revertToHomePage() {
		js.executeScript("document.querySelector('body > div:nth-child(2) > a:nth-child(1)').click();");
	}
	
	public void brokenImagesButtonClick() {
		js.executeScript("document.querySelector('#content > ul > li:nth-child(3) > a').click();");
		String title = js.executeScript("return document.querySelector('#content > div > h3').innerHTML;").toString();
		assertEquals(title, "Broken Images");
	}
	
	public void verifyBrokenImages() {
		Boolean imagePresent;
		String brokenImageURL;
		for(int i = 1; i < 4; i++) {
			imagePresent = (Boolean) js.executeScript("return document.images[" + i + "].complete && typeof document.images[" + i + "].naturalWidth != 'undefined' && document.images[" + i + "].naturalWidth > 0;");
			if(!imagePresent && i==1) {
				brokenImageURL = js.executeScript("return document.images[1].src").toString();
				assertEquals(brokenImageURL, "http://10.0.31.161:9292/asdf.jpg");
			}
			else if(!imagePresent && i==2) {
				brokenImageURL = js.executeScript("return document.images[2].src").toString();
				assertEquals(brokenImageURL, "http://10.0.31.161:9292/hjkl.jpg");
			}
		}
		revertToHomePage();
	}
}
