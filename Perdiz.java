import java.util.List;
import java.util.Random;

/**
 * Un modelo simple de conejo
 * 
 * @author Apah (tradución de la versión del libro)
 * @version 12.02.2012
 */
public class Perdiz extends Animal
{
    // ------ Características compartidas por todos los conejos (campos estáticos).

    private static final int EDAD_REPRODUCCION = 4;                 // La edad a la que puede empezar a procrear
    private static final int EDAD_MAXIMA = 30;                      // La edad máxima que puede vivir
    private static final double PROBABILIDAD_REPRODUCCION = 0.1;   // La probabilidad con la que un conjeo se reproduce
    private static final int MAX_CAMADA = 5;                        // El máximo número de la camada
    private static final Random generador = Randomizer.getRandom(); // Un generador para controlar aleatoriamente la reproducción
    
    // ----- Características individuales (campos de instancia)
    

    /**
     * Crea una nueva perdiz. Una perdiz se puede crear
     * con 0 años (nacimiento) o con una edad al azar.
     * 
     * @param edadAzar Si es true, el conejo tendrá una edad al azar
     * @param campo El campo actual de la simulación
     * @param posicion La posición dentro del campo
     */
    public Perdiz (boolean edadAzar, Field campo, Location posicion)
    {
        super(edadAzar, campo, posicion);
    }
    
    /**
     * Esto es lo que la perdiz está siempre haciendo: corre alrededor. 
     * Algunas veces se reproduce y algunas veces muere de viejo
     * 
     * @param nuevasPerdices Una lista a la que se le van añadiendo los nacimientos
     */
    public void actuar(List<Actor> nuevasPerdices)
    {
        incrementarEdad();
        
        if (estaVivo()) {
            producirNacimientos (nuevasPerdices);            
            // Intenta moverlo a una posición libre
            Location nuevaPosicion = getCampo().posicionAdyacenteLibre(getPosicion());
            
            if(nuevaPosicion != null) {
                setPosicion(nuevaPosicion);
            }
            else {
                setMuerto (); // Muere por superpoblación
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
