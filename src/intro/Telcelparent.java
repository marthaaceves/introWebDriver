    package intro;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.chrome.ChromeDriver;

    import java.util.List;
    import java.util.concurrent.TimeUnit;

    public class Telcelparent {

        static WebDriver driver;

    public static void navegarSitio(String url) {
        //el chrome driver está en la carpeta c: ya que no se tienen derechos de admin, esta funcion es un ejecutable que hace la función de servidor (intermediario).
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ksvv295\\repo\\chromedriver.exe");
        //inicializar el drver
        driver = new ChromeDriver();
        // Setearle un time out
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //Redirige hacia el URL que uno indique.
        driver.navigate().to(url);
    }

    public static void verificarLandingPage() {
        //verificar que existen estos elementos
//        logoTelcel:  css="[src='/content/dam/htmls/img/icons/logo-telcel.svg']". Se utilizo el source de la imagen para identificar el elemento (atributo:src)
        WebElement logoTelcel = driver.findElement(By.cssSelector("[src='/content/dam/htmls/img/icons/logo-telcel.svg']"));
        // Se utilizo atributo data-nombreboton, se puede identificar por '[
        WebElement tiendaEnLinea = driver.findElement(By.cssSelector("[data-nombreboton='Tienda en linea superior']"));
        //Se identifico el campo de busqueda por un id, se identifica por tener un # antes del nombre.
        WebElement campoBusqueda = driver.findElement(By.cssSelector("#buscador-menu-input"));
        // Evalua los 3 elementos previos se muestran y el campo busqueda está habilitado
        if (logoTelcel.isDisplayed() &&
                tiendaEnLinea.isDisplayed() &&
                campoBusqueda.isDisplayed() && campoBusqueda.isEnabled()) {
            System.out.println("Si cargo la pagina principal de telcel");
        } else {
            System.out.println("No cargo la pagina");
            // con esta instrucción termina el programa
            System.exit(-1);
        }
//        linkTiendaEnLinea:css="[data-nombreboton='Tienda en linea superior']"

//        campoBusqueda: css= "#buscador-menu-input" 


    }

    public static void listarTelefonos() {
        WebElement tiendaEnLinea = driver.findElement(By.cssSelector("[data-nombreboton='Tienda en linea superior']"));
        tiendaEnLinea.click();
        // este elemento se encontró por clase, esta se puede identificar por el punto antes del elemento. A continuación también contiene un atributo
        WebElement linkTelefonosCelulares = driver.findElement(By.cssSelector(".shortcut-container [data-nombreboton='Telefonos y smartphones']"));
        linkTelefonosCelulares.click();
    }

    public static void seleccionarEstado(String nombreEstado) {
        System.out.println("breakpoint instruction.");
        //WebElement seleccionaEstadoDropdown = driver.findElement(By.id("marca_nocliente_chosen"));
        WebElement seleccionaEstadoDropdown = driver.findElement(By.cssSelector(".modal .chosen-single"));
        if(seleccionaEstadoDropdown.isDisplayed()) {
            seleccionaEstadoDropdown.click();
        } else {
            System.out.println("Fallo el modal");
            System.exit(-1);
        }

        WebElement inputEstado = driver.findElement(By.cssSelector(".chosen-search input"));
        inputEstado.sendKeys(nombreEstado);
        WebElement opcionEstado = driver.findElement(By.cssSelector(".chosen-results li"));
        opcionEstado.click();
        WebElement botonEntrar = driver.findElement(By.id("entrarPerfilador"));
        botonEntrar.click();

        //.chosen-results li[data-option-array-index='15']
    }


    public static void verificarPaginaResultados() {
        List<WebElement> celulares = driver.findElements(By.cssSelector(".comp-telcel-mosaico-equipos li"));
        System.out.println(celulares.size());
        if(celulares.size() > 1) {
            System.out.println("La lista se desplego correctamente.");
        }
    }


    public static Celular capturarDatosCelular(int i) {
        WebElement textoMarcaModelo = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-marca"));
        String mm = textoMarcaModelo.getText();

        WebElement textoNombre = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-nombre-equipo"));
        String nombreEquipo = textoNombre.getText();


        WebElement textoPrecio = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-precio"));
        String precioEquipo = textoPrecio.getText();
        precioEquipo = precioEquipo.replace(",", "");
        precioEquipo = precioEquipo.replace("$", "");
        double pe = Double.parseDouble(precioEquipo);


        WebElement textoCapacidad = driver.findElement(By.cssSelector(".telcel-mosaico-equipos-capacidad-numero"));
        String capacidadEquipo = textoCapacidad.getText();
        String[] datos = capacidadEquipo.split(" ");
        String capacidadString = datos[0];
        int numGigas = Integer.parseInt(capacidadString);
        return new Celular(mm, nombreEquipo, pe, numGigas);
    }
    public static void seleccionarCelular(int numCelular) {
        List<WebElement> celulares = driver.findElements(By.cssSelector(".comp-telcel-mosaico-equipos li"));
        System.out.println(celulares.size());
        WebElement celular = celulares.get(numCelular - 1);
        celular.click();
    }

    public static void validarDatosCelular(Celular primerCelular) {
    }
    
}
