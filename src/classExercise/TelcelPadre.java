package classExercise;
import classExercise.Celular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class TelcelPadre {
    static WebDriver driver;
    static WebDriverWait wait;
    public static void navegarSitio(String url) {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 30);
        driver.navigate().to(url);
    }
    public static void verificarLandingPage() {
        /*
        WebElement logoTelcel = driver.findElement(By.cssSelector("[src='/content/dam/htmls/img/icons/logo-telcel.svg']"));
        WebElement linkTiendaEnLinea = driver.findElement(By.cssSelector("[data-nombreboton='Tienda en linea superior']"));
        WebElement campoBusqueda = driver.findElement(By.cssSelector("#buscador-menu-input"));
        if( logoTelcel.isDisplayed() && linkTiendaEnLinea.isDisplayed()
                && campoBusqueda.isDisplayed()) {
            System.out.println("Si cargó la página principal de Telcel");
        } else {
            System.out.println("No cargó la pagina");
            System.exit(-1);
        }
        */
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[src='/content/dam/htmls/img/icons/logo-telcel.svg']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-nombreboton='Tienda en linea superior']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#buscador-menu-input")));
    }
    public static void listarTelefonos() {
        WebElement linkTiendaEnLinea = driver.findElement(By.cssSelector("[data-nombreboton='Tienda en linea superior']"));
        linkTiendaEnLinea.click();
        WebElement linkTelefonosCelulares = driver.findElement(By.cssSelector(".shortcut-container [data-nombreboton='Telefonos y smartphones']"));
        linkTelefonosCelulares.click();
    }
    public static void seleccionarEstado(String nombreEstado) {
        System.out.println("breakpoint instruction.");
        /*
        //WebElement seleccionaEstadoDropdown = driver.findElement(By.id("marca_nocliente_chosen"));
        WebElement seleccionaEstadoDropdown = driver.findElement(By.cssSelector(".modal .chosen-single"));
        if(seleccionaEstadoDropdown.isDisplayed()) {
            seleccionaEstadoDropdown.click();
        } else {
            System.out.println("Fallo el modal");
            System.exit(-1);
        }
        */
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal .chosen-single")));
        WebElement seleccionaEstadoDropdown = driver.findElement(By.cssSelector(".modal .chosen-single"));
        seleccionaEstadoDropdown.click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".chosen-search input")));
        WebElement inputEstado = driver.findElement(By.cssSelector(".chosen-search input"));
        inputEstado.sendKeys(nombreEstado);
        WebElement opcionEstado = driver.findElement(By.cssSelector(".chosen-results li"));
        opcionEstado.click();
        WebElement botonEntrar = driver.findElement(By.id("entrarPerfilador"));
        botonEntrar.click();
        //.chosen-results li[data-option-array-index='15']
    }
    public static void verificarPaginaResultados() {
        /*
        List<WebElement> celulares = driver.findElements(By.cssSelector(".comp-telcel-mosaico-equipos li"));
        if(celulares.size() > 1) {
            System.out.println("Lista desplegada correctamente");
        } else {
            System.out.println("No se desplegó la lista de celulares");
            System.exit(-1);
        }
        */
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".comp-telcel-mosaico-equipos li")));
    }
    public static Celular capturarDatosCelular(int i) {
        WebElement textoMarcaModelo = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-marca"));
        String mm = textoMarcaModelo.getText();
        WebElement nombreEquipo = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-nombre-equipo"));
        String nombre = nombreEquipo.getText();
        WebElement precioEquipo = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-precio"));
        String precioString = precioEquipo.getText();
        precioString = precioString.replace("$", "");
        precioString = precioString.replace(",", "");
        double precio = Double.parseDouble(precioString);
        WebElement textoCapacidadEquipo = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-capacidad-numero"));
        int capacidad = Integer.parseInt((textoCapacidadEquipo.getText()).split(" ")[0]);
        System.out.println("Datos extraidos");
        return new Celular(mm, nombre, precio, capacidad);
    }
    public static void seleccionarCelular(int numCelular) {
        List<WebElement> celulares = driver.findElements(By.cssSelector(".comp-telcel-mosaico-equipos li"));
        WebElement celular = celulares.get(numCelular -1);
        celular.click();
    }
    public static void validarDatosCelular(Celular primerCelular) {
        /*
        WebElement textoMarcaModelo = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-marca"));
        if(textoMarcaModelo.getText().equals(primerCelular.getMarcaModelo())) {
            System.out.println("Marca y model son correctos: " + primerCelular.getMarcaModelo());
        } else{
            System.out.println("Marca y modelo incorrectos");
            System.exit(-1);
        }
        WebElement nombreEquipo = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-nombre-equipo"));
        if(nombreEquipo.getText().equals(primerCelular.getNombre())) {
            System.out.println("Nombre correcto: " + primerCelular.getNombre());
        } else{
            System.out.println("Nombre incorrecto");
            System.exit(-1);
        }
        WebElement precioEquipo = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-precio"));
        String precioString = precioEquipo.getText();
        precioString = precioString.replace("$", "");
        precioString = precioString.replace(",", "");
        if(Double.parseDouble(precioString) == primerCelular.getPrecio()) {
            System.out.println("Precio correcto: " + primerCelular.getPrecio());
        } else{
            System.out.println("Precio incorrecto");
            System.exit(-1);
        }
        WebElement textoCapacidadEquipo = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-capacidad-numero"));
        int capacidad = Integer.parseInt((textoCapacidadEquipo.getText()).split(" ")[0]);
        if(capacidad == primerCelular.getCapacidadGigas()) {
            System.out.println("Capacidad correcta: " + primerCelular.getCapacidadGigas());
        } else{
            System.out.println("Capacidad incorrecta");
            System.exit(-1);
        }
        System.out.println("Test completado exitosamente");
        */
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra #ecommerce-ficha-tecnica-modelo")));
        WebElement textoMarcaModelo = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra #ecommerce-ficha-tecnica-modelo"));
        String mm = textoMarcaModelo.getText();
        if(primerCelular.getMarcaModelo().equals(mm))
            System.out.println("La marca y modelo coinciden");
        else
            System.out.println("La marca y modelo no coinciden");
        WebElement textoNombre = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra #ecommerce-ficha-tecnica-nombre"));
        String nombreEquipo = textoNombre.getText();
        if(primerCelular.getNombre().equals(nombreEquipo))
            System.out.println("El nombre coincide");
        else
            System.out.println("El nombre no  coincide");

        WebElement textoPrecio = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra #ecommerce-ficha-tecnica-precio-obj"));
        String precioEquipo = textoPrecio.getText();
        precioEquipo = precioEquipo.replace(",", "");
        precioEquipo = precioEquipo.replace("$", "");
        double pe = Double.parseDouble(precioEquipo);
        if(primerCelular.getPrecio() == pe)
            System.out.println("El precio coincide");
        else
            System.out.println("El precio no coincide");

        WebElement textoCapacidad = driver.findElement(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra .ecommerce-ficha-tecnica-opciones-compra-caracteristicas-etiqueta"));
        String capacidadEquipo = textoCapacidad.getText();
        String[] datos = capacidadEquipo.split(" ");
        String capacidadString = datos[0];
        int numGigas = Integer.parseInt(capacidadString);
        if(primerCelular.getCapacidadGb() == numGigas)
            System.out.println("La capacidad coincide");
        else
            System.out.println("La capacidad no coincide");
    }
    public static void cerrarBrowser() {
        driver.quit();
    }
}
