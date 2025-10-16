package Objects;

public class Inmueble {
    String tipo;
    String ciudad;
    int habitaciones;
    int pisos;
    double precio;

    public Inmueble() {

    }
    public Inmueble(String tipo, String ciudad, int habitaciones, int pisos, double precio) {
        this.tipo = tipo;
        this.ciudad = ciudad;
        this.habitaciones = habitaciones;
        this.pisos = pisos;
        this.precio = precio;

    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public int getHabitaciones() {
        return habitaciones;

    }
    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }
    public int getPisos() {
        return pisos;
    }
    public void setPisos(int pisos) {
        this.pisos = pisos;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    @Override
    public String toString() {
        return "Inmueble{"+"Tipo='"+ tipo+'\''+", Ciudad='"+ ciudad +'\''+", Habitaciones='"+ habitaciones+'\''+", Pisos='"+pisos+'\''+", Precio='"+precio+'\''+'}';

    }
}
