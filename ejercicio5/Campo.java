package ejercicio5;
import java.util.ArrayList;
import java.util.List;

/**
 * Descripcion de Campo
 * 
 * @author Ramón Alegre Ascorbe 
 * @version 23/2/2015
 */
public class Campo
{
    // instance variables - replace the example below with your own
    private int alto, ancho;
    //private Object [] [] casillas;

    /**
     * Constructor de Campo
     */
    public Campo(int alto, int ancho)
    {
        this.alto = alto;
        this.ancho = ancho;
    }
    
    public void listaPosicionesAdyacentes()
    {
        
    }
    
    private List<Posicion> posicionesAdyacentes(Posicion posicion)
    {
        List<Posicion> posiciones = new ArrayList<Posicion>();
        if(posicion != null) {
            int fila = posicion.getFila();
            int columna = posicion.getColumna();
            for(int dplzFila = -1; dplzFila <= 1; dplzFila++) {
                int siguienteFila = fila + dplzFila;
                if(siguienteFila >= 0 && siguienteFila < alto) {
                    for(int dplzColumna = -1; dplzColumna <= 1; dplzColumna++) {
                        int siguienteColumna = columna + dplzColumna;
                        if(siguienteColumna >= 0 && siguienteColumna < ancho && (dplzColumna != 0 || dplzFila != 0)) {
                            posiciones.add(new Posicion(siguienteFila, siguienteColumna));
                        }
                    }
                }
            }
        }
    }
}
