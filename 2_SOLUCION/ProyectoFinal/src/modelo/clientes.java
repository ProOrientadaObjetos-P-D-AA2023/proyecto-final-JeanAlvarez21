/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import controlador.Enlace;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jeanca
 */
public class clientes {
    private String nombre;
    private String id;
    private String ciudad;
    private String marcaC;
    private String modeloC;
    private String numeroC;
    private double pagoMensual;
    private int edad;
    private String mail;
    private List<Plan> planes;


    public clientes() {
        planes = new ArrayList<>();
    }

    public List<Plan> getPlanes() {
        return planes;
    }

    public void agregarPlan(Plan plan) {
        if (planes.size() < 2 && !planes.contains(plan)) {
            planes.add(plan);
        } else {
            System.out.println("El cliente ya tiene asignados dos planes o el plan ya está asignado. No se puede agregar más.");
        }
    }
    public void setPlanes(List<Plan> planes) {
        this.planes = planes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getMarcaC() {
        return marcaC;
    }

    public void setMarcaC(String marcaC) {
        this.marcaC = marcaC;
    }

    public String getModeloC() {
        return modeloC;
    }

    public void setModeloC(String modeloC) {
        this.modeloC = modeloC;
    }

    public String getNumeroC() {
        return numeroC;
    }

    public void setNumeroC(String numeroC) {
        this.numeroC = numeroC;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    
}
