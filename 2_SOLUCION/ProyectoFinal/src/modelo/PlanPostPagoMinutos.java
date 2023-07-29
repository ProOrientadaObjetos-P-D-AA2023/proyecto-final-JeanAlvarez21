/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jeanca
 */
public class PlanPostPagoMinutos extends Plan{
    private int minutosInternacionales;
    private double costoMinutosInternacionales;

    public PlanPostPagoMinutos(int minutosInternacionales, double costoMinutosInternacionales, int minutosN, double costoMinutosN, double megasAGigas, double costoPorGiga) {
        super(minutosN, costoMinutosN, megasAGigas, costoPorGiga);
        this.minutosInternacionales = minutosInternacionales;
        this.costoMinutosInternacionales = costoMinutosInternacionales;
    }

 
    public int getMinutosInternacionales() {
        return minutosInternacionales;
    }

    public void setMinutosInternacionales(int minutosInternacionales) {
        this.minutosInternacionales = minutosInternacionales;
    }

    public double getCostoMinutosInternacionales() {
        return costoMinutosInternacionales;
    }

    public void setCostoMinutosInternacionales(double costoMinutosInternacionales) {
        this.costoMinutosInternacionales = costoMinutosInternacionales;
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
        return minutosN * costoMinutosN + minutosInternacionales * costoMinutosInternacionales;
    }
    
    
    @Override
    public String toString() {
        return "\n------Costo de Plan Post Pago Mimutos------"
                + "\nMinutos nacionales consumidos: " + minutosN 
                + "\nCosto por minuto nacional: " + costoMinutosN
                + "\nMinutos internacionales consumidos: " + minutosInternacionales 
                + "\nCosto por minuto internacional: " + costoMinutosInternacionales
                + "\nPrecio a pagar: " + calcularCostoPlan();
    }
}
