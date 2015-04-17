import java.util.List;

/**
 * Write a description of interface Actor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Actor
{
    /**
     * Ejecuta el comportamiento normal de un actor
     * @param La lista de actores que son creados con su comportamiento
     */
    void actuar(List<Actor> listaActores);
    
    /**
     * Devuelve si el actor esta vivo (activo)
     * @return boolean si el actor esta vivo o no
     */
    boolean estaVivo();
    
    boolean esPresa();
    
    void setMuerto();
}
