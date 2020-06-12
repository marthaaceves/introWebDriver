package Tareas;

import com.google.common.io.LittleEndianDataOutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LikeSongFrankSinatra extends Likessongparent {


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ksvv295\\repo\\chromedriver.exe");
        navegarSitio("https://evening-bastion-49392.herokuapp.com/");
        validaHomePage();
        navegarListadoCanciones();
        navegarPrimeraCancion();
        likeCancion();
    }


    }
