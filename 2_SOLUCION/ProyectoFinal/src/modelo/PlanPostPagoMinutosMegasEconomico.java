/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jeanca
 */
public class PlanPostPagoMinutosMegasEconomico extends Plan {
    private double porcentajeDescuento;

    public PlanPostPagoMinutosMegasEconomico(double porcentajeDescuento, int minutosN, double costoMinutosN, double megasAGigas, double costoPorGiga) {
        super(minutosN, costoMinutosN, megasAGigas, costoPorGiga);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    
    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public int getMinutosN() {
        return minutosN;
    }

    public void setMinutosN(int minutosN) {
        this.minutosN = minutosN;
    }

    public double getCostoMinutosN() {
        return costoMinutosN;
    }

    public void setCostoMinutosN(double costoMinutosN) {
        this.costoMinutosN = costoMinutosN;
    }

    public double getMegasAGigas() {
        return megasAGigas;
    }

    public void setMegasAGigas(double megasAGigas) {
        this.megasAGigas = megasAGigas;
    }

    public double getCostoPorGiga() {
        return costoPorGiga;
    }

    public void setCostoPorGiga(double costoPorGiga) {
        this.costoPorGiga = costoPorGiga;
    }

    @Override
    public double calcularCostoPlan() {
        double a = minutosN * costoMinutosN + megasAGigas/1000 * costoPorGiga;
        double b = a * (porcentajeDescuento/100);
        return a - b;
    }

    @Override
    public String toString() {
        return "\n------Costo de Plan Post Pago Mimutos Megas Economico------"
                + "\nMinutos consumidos: " + minutosN
                + "\nCosto por minuto consumido: " + costoMinutosN
                + "\nMegas: " + megasAGigas + "Mb a gigas ----> " + megasAGigas / 1000
                + "Gb\nCosto por Giga: " + costoPorGiga
                +"\nDescuento aplicado " + porcentajeDescuento  + "%"
                + "\nPrecio a pagar: " + calcularCostoPlan();
    }
    
    
}
