import java.util.List;
import java.util.Iterator;

/**
 * Write a description of class Cazador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cazador implements Actor
{
    //Constantes
    private static final int NUM_TIROS = 3;

    //campos
    private Location posicion;
    private Field campo;

    /**
     * Constructor de Cazador
     */
    public Cazador(Location posicion, Field campo)
    {
        this.campo = campo;
        setPosicion(posicion);
    }

    public void setPosicion(Location nuevaPosicion)
    {
        if (posicion != null) {
            campo.clear(posicion);
        }

        posicion = nuevaPosicion;
        campo.place(this, nuevaPosicion);
    }

    public Location getPosicion()
    {
        return posicion;
    }

    public Field getCampo()
    {
        return campo;
    }

    public boolean estaVivo()
    {
        return true;
    }
    
    public boolean esPresa()
    {
        return false;
    }
    
    public void actuar(List<Actor> listaCazadores)
    {           
        int numTiros = 0;
        List<Location> adyacentes = campo.adjacentLocations(posicion);
        Iterator<Location> it = adyacentes.iterator();

        while(it.hasNext() && numTiros < NUM_TIROS) {
            Location donde = it.next();
            Object actor = campo.getObjectAt(donde);

            if(actor instanceof Animal) {
                Animal animal = (Animal) actor;
                animal.setMuerto();
                numTiros++;
            }
        }

        Location nuevaPosicion = campo.posicionAdyacenteLibre(posicion);
        if(nuevaPosicion != null) {
            setPosicion(nuevaPosicion);
        }
    }
    
    public void setMuerto()
    {
        vive = false;
        
        if (posicion != null) {
            campo.clear(posicion);
            posicion = null;
            campo = null;
        }
    }
}
