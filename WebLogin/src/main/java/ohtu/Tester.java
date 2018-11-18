package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
// import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
//        WebDriver driver = new ChromeDriver();
        WebDriver driver = new HtmlUnitDriver();
        
        driver.get("http://localhost:4567");
// Tulostetaan sivu konsoliin:

        System.out.println(driver.getPageSource());
        sleep(2);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        
        System.out.println(driver.getPageSource());
        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        System.out.println(driver.getPageSource());
        sleep(1);
        element.submit();

        System.out.println("");
        System.out.println("WHAT DO YOU GET FROM 1?");
        System.out.println("");
        
        System.out.println(driver.getPageSource());
        sleep(5);
//        driver.quit();

        System.out.println("");
        System.out.println("End of test 1");
        System.out.println("");
        System.out.println("Start of test 2");
        System.out.println(""); 

// 2. epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana      
               
        driver.get("http://localhost:4567");
// Tulostetaan sivu konsoliin:

        System.out.println(driver.getPageSource());
        sleep(1);
        
        element = driver.findElement(By.linkText("login"));
        element.click();
        
        System.out.println(driver.getPageSource());
        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("noakkep");
        element = driver.findElement(By.name("login"));

        System.out.println(driver.getPageSource());
        sleep(1);
        element.submit();

        System.out.println("");
        System.out.println("WHAT DO YOU GET FROM 2?");
        System.out.println("");

        System.out.println(driver.getPageSource());
        sleep(5);

        System.out.println("");
        System.out.println("End of test 2");
        System.out.println("");
        System.out.println("Start of test 3");
        System.out.println("");

// 3. epäonnistunut kirjautuminen: ei-olemassaoleva käyttäjätunnus      

        driver.get("http://localhost:4567");

        System.out.println(driver.getPageSource());
        sleep(2);

        element = driver.findElement(By.linkText("login"));
        element.click();

        System.out.println(driver.getPageSource());
        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("nopekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        System.out.println(driver.getPageSource());
        sleep(1);
        element.submit();

        System.out.println("");
        System.out.println("WHAT DO YOU GET FROM 3?");
        System.out.println("");

        System.out.println(driver.getPageSource());
        sleep(5);

        System.out.println("");
        System.out.println("End of test 3");
        System.out.println("");
        System.out.println("Start of test 4");
        System.out.println("");

// 4. epäonnistunut kirjautuminen: ei-olemassaoleva käyttäjätunnus      

        driver.get("http://localhost:4567");

        System.out.println(driver.getPageSource());
        sleep(1);

        element = driver.findElement(By.linkText("register new user"));
        element.click();

        System.out.println(driver.getPageSource());
        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka5");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep5");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("akkep5");
        element = driver.findElement(By.name("signup"));

        System.out.println(driver.getPageSource());
        sleep(1);
        element.submit();

        System.out.println("");
        System.out.println("WHAT DO YOU GET FROM 4?");
        System.out.println("");

        System.out.println(driver.getPageSource());
        sleep(5);

        System.out.println("");
        System.out.println("End of test 4");
        System.out.println("");
        System.out.println("Start of test 5");
        System.out.println("");

// 5. uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(1);
        element = driver.findElement(By.linkText("logout"));
        element.click();

        System.out.println("");
        System.out.println("WHAT DO YOU GET FROM 5?");
        System.out.println("");
        System.out.println(driver.getPageSource());
        sleep(5);

        driver.quit();

    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }

}
