package classExercise;


import intro.TelcelPadre;

public class TelcelSeleccionarArticulo extends TelcelPadre
{

    public static void main(String[] args) {
        //mandar llamar a los metodos siguientes

        navegarSitio("https://www.telcel.com");
        verificarLandingPage();
        listarTelefonos();
        seleccionarEstado("Jalisco");
        verificarPaginaResultados();
        Celular primerCelular = capturarDatosCelular(1);
        seleccionarCelular(1);
        validarDatosCelular(primerCelular);
        cerrarBrowser();
    }
}
