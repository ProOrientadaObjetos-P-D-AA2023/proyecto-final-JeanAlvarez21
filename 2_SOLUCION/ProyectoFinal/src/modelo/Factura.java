package modelo;
public class Factura {
    private String nombre;
    private String ciudad;
    private double pagoMensual;
    private String tipoPlan;

    public Factura(String nombre, String ciudad, double pagoMensual, String tipoPlan) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pagoMensual = pagoMensual;
        this.tipoPlan = tipoPlan;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }

    public String getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }
}
