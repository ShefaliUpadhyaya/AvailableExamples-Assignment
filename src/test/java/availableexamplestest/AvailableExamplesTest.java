package availableexamplestest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AvailableExamplesTest {
	WebDriver driver;
	JavascriptExecutor js;
	BasicAuth basicAuth;
	BrokenImages brokenImages;
	ExitIntent exitIntent;
	Tables tables;
	FormAuthentication formAuthentication;
	Hovers hovers;
	Editor editor;
	StatusCodes statusCodes;
	
	@BeforeClass
	public void launchBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
        js = (JavascriptExecutor)driver;	
		driver.get("http://10.0.31.161:9292/");
		basicAuth = new BasicAuth(driver, js);
		brokenImages = new BrokenImages(js);
		exitIntent = new ExitIntent(js);
		tables = new Tables(js);
		formAuthentication = new FormAuthentication(js);
		hovers = new Hovers(driver, js);
		editor = new Editor(driver, js); 
		statusCodes = new StatusCodes(driver, js);
	}
	
	@Test(priority = 1)
	public void basic_Auth_Button_Should_Open_Authentication_Prompt() {
		//basicAuth.basicAuthButtonClick();
	}
	
	@Test(priority = 2)
	public void enter_Credentials_In_Authentication_Prompt_Should_Open_Basic_Authentication_Page() {
		basicAuth.authenticationPrompt();
	}
	
	@Test(priority = 3)
	public void broken_Images_Button_Should_Open_Broken_Images_Page() {
		brokenImages.brokenImagesButtonClick();
	}
	
	@Test(priority = 4) 
	public void report_Source_URL_Of_Images_Not_Loaded_Properly() {
		brokenImages.verifyBrokenImages();
	}
	
	@Test(priority = 5)
	public void exit_Intent_Button_Should_Open_Exit_Intent_Page() {
		exitIntent.exitIntentButtonClick();
	}
	
	@Test(priority = 6)
	public void mouse_Outside_Page_Should_Display_Modal() {
		exitIntent.mouseOutsidePageDisplaysModal();
	}
	
	@Test(priority = 7)
	public void modal_Should_Disappear_On_Defocussing() {
		exitIntent.defocussingModalDisappearsPopup();
	}
	
	@Test(priority = 8)
	public void sortable_Data_Tables_Button_Should_Open_Data_Tables_Page() {
		tables.sortableDataTablesButtonClick();
	}
	
	@Test(priority = 9)
	public void due_Column_Click__Should_Arrange_Due_In_Descending_Order() {
		tables.sortInDescendingOrderOfDueDate();
	}
	
	@Test(priority = 10)
	public void form_Authentication_Button_Should_Open_Form_Authentication_Page() {
		formAuthentication.formAuthenticationButtonClick();
	}
	
	@Test(priority = 11)
	public void form_Authentication_With_Invalid_Credentials_Should_Display_Error() {
		formAuthentication.submitFormWithInvalidCredentials();
	}
	
	@Test(priority = 12)
	public void form_Authentication_With_Valid_Credentials_Should_Log_Into_Secure_Area() {
		formAuthentication.submitFormWithValidCredentials();
	}
	
	@Test(priority = 13)
	public void logout_Button_Should_Log_Out_Of_Secure_Area() {
		formAuthentication.logoutOpensFormAuthenticationPage();
	}
	
	@Test(priority = 14)
	public void hovers_Button_Should_Open_Hovers_Page() {
		hovers.hoversButtonClick();
	}
	
	@Test(priority = 15)
	public void verify_Username_And_Profile_On_Hovering_Over_First_Image() {
		hovers.verifyImage1();
	}
	
	@Test(priority = 16)
	public void hovering_Out_Of_First_Image_Makes_Username_And_Profile_Disappear() {
		hovers.hoverOutOfImage1();
	}
	
	@Test(priority = 17)
	public void editor_Button_Should_Open_Editor_Page() {
		editor.editorButtonClick();
	}
	
	@Test(priority = 18)
	public void clear_And_Enter_Text_In_Editor_Should_Is_Able_To_Edit() {
		editor.clearAndEnterText();
	}
	
	@Test(priority = 19)
	public void status_Codes_Button_Should_Open_Status_Codes_Page() {
		statusCodes.statusCodesButtonClick();
	}
	
	@Test(priority = 20)
	public void status_Codes_404_Button_Should_Display_404_Message() {
		statusCodes.statusCode404ButtonClick();
	}
	
	@Test(priority = 21)
	public void status_Code_Should_Be_404_For_The_Page() {
		statusCodes.verify404StatusCode();
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}
