import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * Un modelo simple de un zorro
 *  
 * @author Apah (Traducci�n de la misma clase del CD)
 * @version 13.02.2012
 */
public class Zorro extends Animal
{
    // ----- Caracter�sticas comunes para todos los zorros (campos est�ticos)

    private static final int EDAD_REPRODUCCION = 10;                // Edad a la que puede empezar a reproducirse
    private static final int EDAD_MAXIMA = 150;                      // Edad hasta la que puede vivier un zorro
    private static final double PROBABILIDAD_REPRODUCCION = 0.08;   // Probabilidad de reproducci�n de un zorro
    private static final int MAX_CAMADA = 2;                        // M�ximo n�mero de cachorros
    private static final int VALOR_COMIDA = 7;                      // N�mero de pasos que puede dar un zorro (hasta que vuelva a comer)
    private static final int MAX_COMIDA = 20;   
    private static final Random generador = Randomizer.getRandom(); // N�mero aleatorio para gestionar la reproducci�n

    // ----- Caracter�sticas individuales (campos de instancia)

    private int nivelComida;    // Es el nivel de comidad. Se incrementa comienzo conejos

    /**
     * Crea un zorro
     * Un zorro puede ser creado como un nuevo nacimiento (con edad 0 y no hambriento)
     * o con una edad y nivel de comida aleatorios.
     * 
     * @param edadAzar Si es true, el zorro tendr� una edad y un nivel de comida aleatorios
     * @param campo El campo actual donde se desarrolla la simulaci�n
     * @param posicion La posici�n en el campo
     */
    public Zorro (boolean edadAzar, Field campo, Location posicion)
    {
        super(edadAzar, campo, posicion);

        if(edadAzar) {
            nivelComida = generador.nextInt(VALOR_COMIDA);
        }
        else {
            nivelComida = VALOR_COMIDA;
        }
    }

    /**
     * Esto es lo que un zorro hace la mayor�a del tiempo: cazar conejos.
     * Adem�s puede reproducirse, morir de hambre o morir de viejo.
     * 
     * @param campo El campo donde se desarrolla la simulaci�n
     * @param nuevosZorros Una lista a la que se van a�adiendo los nacimientos
     */
    public void actuar(List<Actor> nuevosZorros)
    {
        incrementarEdad();
        incrementarHambre();

        if(estaVivo()) {
            producirNacimientos(nuevosZorros);            
            // Se mueve buscando comida
            Location nuevaPosicion = buscarComida(getPosicion());

            if(nuevaPosicion == null) { 
                nuevaPosicion = getCampo().posicionAdyacenteLibre(getPosicion()); // No hay comida. Se intenta mover a una posici�n libre
            }

            // Mira a ver si es posible moverse
            if(nuevaPosicion != null) {
                setPosicion(nuevaPosicion);
            }
            else {
                setMuerto();  // Superpoblaci�n
            }
        }
    }

    /**
     * Hace m�s hambriento al zorro
     * Esto puede ocasionar que muera
     */
    private void incrementarHambre()
    {
        nivelComida--;

        if(nivelComida <= 0) {
            setMuerto();
        }
    }

    /**
     * Busca alg�n conejo en los alrededores
     * Solo el primer conejo vivo es comido
     * @param posicion Situaci�n en el campo donde est� el zorro
     * @return La posici�n donde hay comida, o null si no la encuentra
     */
    private Location buscarComida(Location posicion)
    {
        List<Location> adyacentes = getCampo().adjacentLocations(posicion);
        Iterator<Location> it = adyacentes.iterator();
        Location localizacionActor = null;

        while(it.hasNext()) {
            Location donde = it.next();
            Actor actor = (Actor) getCampo().getObjectAt(donde);

            if(actor != null && actor.estaVivo() && actor.esPresa()) {
                actor.setMuerto();
                nivelComida += VALOR_COMIDA;

                if (nivelComida > MAX_COMIDA) {
                    nivelComida = MAX_COMIDA;
                }

                localizacionActor = donde;
            }
        }

        return localizacionActor;
    }

    public Animal crearActor(boolean edadAzar, Field campo, Location posicion)
    {
        return new Zorro(edadAzar, campo, posicion);
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
        return false;
    }
}
