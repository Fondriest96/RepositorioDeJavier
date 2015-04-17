import java.util.List;
import java.util.Random;

/**
 * Un modelo simple de conejo
 * 
 * @author Apah (traduci�n de la versi�n del libro)
 * @version 12.02.2012
 */
public class Perdiz extends Animal
{
    // ------ Caracter�sticas compartidas por todos los conejos (campos est�ticos).

    private static final int EDAD_REPRODUCCION = 4;                 // La edad a la que puede empezar a procrear
    private static final int EDAD_MAXIMA = 30;                      // La edad m�xima que puede vivir
    private static final double PROBABILIDAD_REPRODUCCION = 0.1;   // La probabilidad con la que un conjeo se reproduce
    private static final int MAX_CAMADA = 5;                        // El m�ximo n�mero de la camada
    private static final Random generador = Randomizer.getRandom(); // Un generador para controlar aleatoriamente la reproducci�n
    
    // ----- Caracter�sticas individuales (campos de instancia)
    

    /**
     * Crea una nueva perdiz. Una perdiz se puede crear
     * con 0 a�os (nacimiento) o con una edad al azar.
     * 
     * @param edadAzar Si es true, el conejo tendr� una edad al azar
     * @param campo El campo actual de la simulaci�n
     * @param posicion La posici�n dentro del campo
     */
    public Perdiz (boolean edadAzar, Field campo, Location posicion)
    {
        super(edadAzar, campo, posicion);
    }
    
    /**
     * Esto es lo que la perdiz est� siempre haciendo: corre alrededor. 
     * Algunas veces se reproduce y algunas veces muere de viejo
     * 
     * @param nuevasPerdices Una lista a la que se le van a�adiendo los nacimientos
     */
    public void actuar(List<Actor> nuevasPerdices)
    {
        incrementarEdad();
        
        if (estaVivo()) {
            producirNacimientos (nuevasPerdices);            
            // Intenta moverlo a una posici�n libre
            Location nuevaPosicion = getCampo().posicionAdyacenteLibre(getPosicion());
            
            if(nuevaPosicion != null) {
                setPosicion(nuevaPosicion);
            }
            else {
                setMuerto (); // Muere por superpoblaci�n
            }
        }
    }
    
    public Actor crearActor(boolean edadAzar, Field campo, Location posicion)
    {
        return new Perdiz(edadAzar, campo, posicion);
    }

    public int getEdadDeReproduccion()
    {
        return EDAD_REPRODUCCION;
    }
    
    public int getEdadMaxima()
    {
        return EDAD_MAXIMA;
    }
    
    public int getMaxCamada()
    {
        return MAX_CAMADA;
    }
    
    public double getProbabilidadReproduccion()
    {
        return PROBABILIDAD_REPRODUCCION;
    }
    
    public boolean esPresa()
    {
        return true;
    }
}
