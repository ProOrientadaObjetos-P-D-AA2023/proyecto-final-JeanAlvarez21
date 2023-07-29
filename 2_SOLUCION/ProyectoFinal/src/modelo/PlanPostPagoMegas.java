/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jeanca
 */
public class PlanPostPagoMegas extends Plan{
    private double tarifaBase;

    public PlanPostPagoMegas(double tarifaBase, int minutosN, double costoMinutosN, double megasAGigas, double costoPorGiga) {
        super(minutosN, costoMinutosN, megasAGigas, costoPorGiga);
        this.tarifaBase = tarifaBase;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
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
        return (megasAGigas/1000) * costoPorGiga + tarifaBase;
    }

    @Override
    public String toString() {
        return "\n------Costo de Plan Post Pago Megas------"
                + "\nMegas: " + megasAGigas + "Mb a gigas ----> " + megasAGigas / 1000
                + "Gb\nCosto por Giga: " + costoPorGiga + "\nTarifa base: " + tarifaBase
                + "\nPrecio a pagar: " + calcularCostoPlan();
    }
    
}
