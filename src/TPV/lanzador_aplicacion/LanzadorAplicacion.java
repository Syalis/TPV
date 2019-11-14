package montes.gutierrez.borja.TPV.lanzador_aplicacion;

import montes.gutierrez.borja.TPV.vistas.principalFrame.PrincipalFrame;

/**
 * Clase para lanzar la aplicación.
 *
 * @author Borja Montes Gutiérrez
 */
public class LanzadorAplicacion {

    /**
     * Método main que inicia la aplicación.
     *
     * @param args String[] argumentos/parámetros que no se usan.
     */
    public static void main(String[] args) {
        PrincipalFrame frame = new PrincipalFrame();
        frame.setVisible(true);
    }

}
