package availableexamplestest;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;

import org.openqa.selenium.JavascriptExecutor;

public class Tables {
	JavascriptExecutor js;
	
	public Tables(JavascriptExecutor js) {
		this.js = js;
	}
	
	private void clickDueColumn() {
		js.executeScript("document.querySelector('#table1 > thead > tr > th:nth-child(4) > span').click();");
	}
	
	private Double fetchDueValue(int i) {
		return Double.parseDouble(js.executeScript("return document.querySelector('#table1 > tbody > tr:nth-child(" + i +") > td:nth-child(4)').textContent;").toString().replaceAll("[^0-9\\.]+", ""));
	}
	
	private void revertToHomePage() {
		js.executeScript("document.querySelector('body > div:nth-child(2) > a:nth-child(1)').click();");
	}
	
	public void sortableDataTablesButtonClick() {
		js.executeScript("return document.querySelector('#content > ul > li:nth-child(36) > a').click();");
		String title = js.executeScript("return document.querySelector('#content > div > h3').innerHTML;").toString();
		assertEquals(title, "Data Tables");
	}
	
	public void sortInDescendingOrderOfDueDate() {
		clickDueColumn();
		clickDueColumn();
		Double due[] = new Double[4];
		for(int i = 0; i < 4; i++) {
			due[i] = fetchDueValue(i+1);
		}
		assertEquals(Arrays.toString(due), "[100.0, 51.0, 50.0, 50.0]");
		revertToHomePage();
	}
}
