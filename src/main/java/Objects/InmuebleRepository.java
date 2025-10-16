package Objects;
import java.util.ArrayList;

public class InmuebleRepository {
    private static InmuebleRepository instance;
    private ArrayList<Inmueble> inmuebles;
    private InmuebleRepository() {
        inmuebles = new ArrayList<>();
        showExamples();
    }
    public static InmuebleRepository getInstance() {
        if(instance == null){
            instance = new InmuebleRepository();
        }
        return instance;
    }
    private void showExamples(){
        inmuebles.add(new Inmueble("Casa", "Quimbaya", 3, 2, 1300000));
    }
    public ArrayList<Inmueble> getInmuebles(){
        return inmuebles;
    }
    public void agregarInmueble(Inmueble inmueble){
        inmuebles.add(inmueble);
    }
    public boolean eliminarInmueble(Inmueble inmueble){
        return inmuebles.remove(inmueble);
    }
    public int getTotalInmuebles(){
        return inmuebles.size();
    }
}

