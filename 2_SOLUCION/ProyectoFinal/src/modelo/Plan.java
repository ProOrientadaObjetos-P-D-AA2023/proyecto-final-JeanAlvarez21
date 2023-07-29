/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jeanca
 */
public abstract class Plan {
    protected int minutosN;
    protected double costoMinutosN;
    protected double megasAGigas;
    protected double costoPorGiga;

    public Plan(int minutosN, double costoMinutosN, double megasAGigas, double costoPorGiga) {
        this.minutosN = minutosN;
        this.costoMinutosN = costoMinutosN;
        this.megasAGigas = megasAGigas;
        this.costoPorGiga = costoPorGiga;
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

    public double getMegasAGigasl() {
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
    
    public abstract double calcularCostoPlan();
}
