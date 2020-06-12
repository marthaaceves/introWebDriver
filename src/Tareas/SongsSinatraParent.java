package Tareas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SongsSinatraParent {

    public static WebDriver driver;

    public static void navegar(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ksvv295\\repo\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.navigate().to(url);
    }

    public static void realizarLoginCorrecto(String usuario, String password) {
//    HomePage:
//    linkLogin: txt="log in "
        WebElement linkLogin = driver.findElement(By .partialLinkText("log"));
        linkLogin.click();

        WebElement username = driver.findElement(By.id("username"));

//    LoginPage:
//    usernameTxt: id="username"
//    passwordTxt: id="password"
//    loginBtn: value="Log In"
    }

    public static void validarHomePage() {
//    txtBienvenida:
//    imgSinatra: src="/images/sinatra.jpg"
//    linkLogin: txt="log in "
    }

    public static void validarMensajeBienvenida(String mensajeBienvenida) {
//    HomePage:
//    mensajeBienvenida: id="flash"
//    linkLogout: href="/logout"
    }

    public static void cerrarBrowser() {
    }
}
