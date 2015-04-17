import java.util.List;
import java.util.Random;

/**
 * Write a description of class Animal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Animal implements Actor
{
    //Campos
    private int edad;
    private boolean vive;
    private Location posicion;
    private Field campo;
    
    protected static final Random generador = Randomizer.getRandom();

    /**
     * 
     */
    public Animal(boolean edadAzar, Field campo, Location posicion)
    {
        edad = 0;
        
        if(edadAzar) {
            setEdad(generador.nextInt(getEdadMaxima()));
        }
        
        vive = true;
        this.campo = campo;
        setPosicion(posicion);
    }

    public boolean estaVivo()
    {
        return vive;
    }
    
    public int getEdad()
    {
        return edad;
    }
    
    public void setEdad(int edad)
    {
        this.edad = edad;
    }

    public Location getPosicion()
    {
        return posicion;
    }

    public Field getCampo()
    {
        return campo;
    }

    public void setPosicion(Location nuevaPosicion)
    {
        if (posicion != null) {
            campo.clear(posicion);
        }
        
        posicion = nuevaPosicion;
        campo.place(this, nuevaPosicion);
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
    
    public int reproducir()
    {
        int nacimientos = 0;
        
        if(puedeReproducirse() && generador.nextDouble() <= getProbabilidadReproduccion()) {
            nacimientos = generador.nextInt(getMaxCamada()) + 1;
        }
        
        return nacimientos;
    }
    
    public void incrementarEdad()
    {
        edad++;
        
        if(edad > getEdadMaxima()) {
            setMuerto();
        }
    }
    
    public boolean puedeReproducirse()
    {
        return edad >= getEdadDeReproduccion();
    }
    
    protected void producirNacimientos(List<Actor> nuevosActores)
    {
        // Los nuevos conejos se colocan en posiciones adyacentes
        // Para ello, primero obtiene una lista de posiciones adaycentes libres
        List<Location> libres = getCampo().getPosicionesLibresAdyacentes(getPosicion());
        int nacimientos = reproducir();
        
        for(int n = 0; n < nacimientos && libres.size() > 0; n++) {
            Location pos = libres.remove(0);
            Actor nuevoActor = crearActor(false, campo, pos);
            nuevosActores.add(nuevoActor);
        }
    }
    
    abstract public void actuar(List<Actor> nuevosActores);
    
    abstract public int getEdadDeReproduccion();
    
    abstract public int getEdadMaxima();
    
    abstract public double getProbabilidadReproduccion();
    
    abstract public int getMaxCamada();
    
    abstract public Actor crearActor(boolean edadAzar, Field campo, Location posicion);
    
    abstract public boolean esPresa();
}
