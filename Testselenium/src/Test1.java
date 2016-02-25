import java.util.regex.*;
import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.*;

public class Test1 {
	  private WebDriver driver;
	  private WebDriver secondDriver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  boolean secondd=true;
	  
	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	   

	    baseUrl = "https://www.google.fr/";
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	  }	
	  
	  public String generate(int length)
	  {
	  	    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Tu supprimes les lettres dont tu ne veux pas
	  	    String pass = "";
	  	    for(int x=0;x<length;x++)
	  	    {
	  	       int i = (int)Math.floor(Math.random() * chars.length()); // Si tu supprimes des lettres tu diminues ce nb
	  	       pass += chars.charAt(i);
	  	    }
	  	    System.out.println(pass);
	  	    return pass;
	  }
	  
	  @Test
	  public void test1() throws Exception {
		String randomSearch = generate(15);  
		assertEquals("sdsd", randomSearch.length(), 15);
		driver.get(baseUrl);
		
	    driver.findElement(By.id("lst-ib")).clear();
	    driver.findElement(By.id("lst-ib")).sendKeys(randomSearch); // recherche sur randomsearch
	    driver.findElement(By.id("lst-ib")).sendKeys(Keys.RETURN);	//press la touche entre du clavier
	    
	    
		    driver.findElement(By.linkText("Images")).click(); //aller dans l'onglet images
		    //driver.findElement(By.xpath("(//a[contains(text(),'Images')])[2]")).click(); //aller dans l'onglet images
		    System.out.println("ok0");
		    //driver.findElement(By.cssSelector("a.q.qs")).click();
		   // if (driver.findElement(By.xpath("//li[contains(text(), 'Essayer d'autres mots.')]")).getText().length() > 0 )
		    
		    //if (driver.findElement(By.xpath("img.rg_i"))==null)
		    	//if(driver.getPageSource().contains("Essayer d'autres mots."))
		    if (isElementPresent(By.cssSelector("img.rg_i")) == false) //on verifie la presence du de l'image
		    {
		    	//System.out.println(driver.findElement(By.xpath("//ul//li[1]")).getText());
		    	System.out.println("il n'y a pas d'image");
		    	secondd = false;
		    }
		    
		    else {
		    		
			    System.out.println("il une image");
			    driver.findElement(By.cssSelector("img.rg_i")).click(); //clique sur la premiere image
			    //driver.findElement(By.cssSelector("a.q.qs")).click();
			    //driver.findElement(By.name("hiVlYzeJQNaVGM:")).click();// clique sur l'image hi..
			    WebElement test = driver.findElement(By.id("il_fi"));//on recupere les element de la balise il_f et on mets ca dans test
			    
			    //WebElement iFrame = driver.findElement(By.tagName("iframe")); //prendre la iframe de la vrai url
			    //System.out.println(iFrame.toString());
			    String ok = test.getAttribute("src"); //on recupere le site src
			    //System.out.println("ok ?");
			    System.out.println(ok); 
			    //File file = new File("D:\\selnium webdriver\\driver\\IEDriverServer.exe");
			    //System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			     //  WebDriver driver2 = new InternetExplorerDriver()
			   
			    //driver.findElement(By.cssSelector("(//a[=http]")).click(); 
			    System.setProperty("webdriver.chrome.driver","C:/Users/alfr/Downloads/driver/chromedriver.exe");
			    secondDriver = new ChromeDriver();
			    secondDriver.get(ok);
		    }
	        
	    
	  }
	  
	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    if(secondd)
	    {
	    secondDriver.quit();
	    }
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	  
	  private boolean isElementPresent(By by) {
		    try {
		      driver.findElement(by);
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }

		  private boolean isAlertPresent() {
		    try {
		      driver.switchTo().alert();
		      return true;
		    } catch (NoAlertPresentException e) {
		      return false;
		    }
		  }

		  private String closeAlertAndGetItsText() {
		    try {
		      Alert alert = driver.switchTo().alert();
		      String alertText = alert.getText();
		      if (acceptNextAlert) {
		        alert.accept();
		      } else {
		        alert.dismiss();
		      }
		      return alertText;
		    } finally {
		      acceptNextAlert = true;
		    }
		  }
	  
	  
}
