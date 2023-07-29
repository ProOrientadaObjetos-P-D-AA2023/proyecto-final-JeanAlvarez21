/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.*;

public class Enlace {
    private Connection conn;

    public void establecerConexion() {
        try {
            String url = "jdbc:sqlite:db/clientes.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection obtenerConexion() {
        return conn;
    }

    public void insertarCliente(clientes cl) {
        try {
            establecerConexion();
            Statement statement = obtenerConexion().createStatement();

            // Insertar los detalles del cliente en la tabla "clientes"
            String insertClienteQuery = String.format("INSERT INTO clientes (nombre, id, ciudad, marcaC, modeloC, numeroC, edad, mail)"
                            + "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%d', '%s')",
                    cl.getNombre(),
                    cl.getId(),
                    cl.getCiudad(),
                    cl.getMarcaC(),
                    cl.getModeloC(),
                    cl.getNumeroC(),
                    cl.getEdad(),
                    cl.getMail());

            statement.executeUpdate(insertClienteQuery);
            String idClienteRecienInsertado = cl.getId();

            // Insertar los planes asociados al cliente en la tabla "planes"
            List<Plan> planes = cl.getPlanes();
            for (Plan plan : planes) {
                String insertPlanQuery = "";
                if (plan instanceof PlanPostPagoMegas) {
                    PlanPostPagoMegas planMegas = (PlanPostPagoMegas) plan;
                    insertPlanQuery = String.format("INSERT INTO planes (tipoPlan, minutosPlan, costoMinutosPlan, megasPlan, costoPorGigaPlan, tarifaBase, idCliente)"
                                    + "VALUES ('%s', '%d', '%f', '%f', '%f', '%f', '%s')",
                            "PlanPostPagoMegas",
                            planMegas.getMinutosN(),
                            planMegas.getCostoMinutosN(),
                            planMegas.getMegasAGigas(),
                            planMegas.getCostoPorGiga(),
                            planMegas.getTarifaBase(),
                            idClienteRecienInsertado);
                } else if (plan instanceof PlanPostPagoMinutos) {
                    PlanPostPagoMinutos planMinutos = (PlanPostPagoMinutos) plan;
                    insertPlanQuery = String.format("INSERT INTO planes (tipoPlan, minutosPlan, costoMinutosPlan, megasPlan, costoPorGigaPlan, minutosInternacionales, costoMinutosInternacionales, idCliente)"
                                    + "VALUES ('%s', '%d', '%f', '%f', '%f', '%d', '%f', '%s')",
                            "PlanPostPagoMinutos",
                            planMinutos.getMinutosN(),
                            planMinutos.getCostoMinutosN(),
                            planMinutos.getMegasAGigas(),
                            planMinutos.getCostoPorGiga(),
                            planMinutos.getMinutosInternacionales(),
                            planMinutos.getCostoMinutosInternacionales(),
                            idClienteRecienInsertado);
                } else if (plan instanceof PlanPostPagoMinutosMegas) {
                    PlanPostPagoMinutosMegas planMinutosMegas = (PlanPostPagoMinutosMegas) plan;
                    insertPlanQuery = String.format("INSERT INTO planes (tipoPlan, minutosPlan, costoMinutosPlan, megasPlan, costoPorGigaPlan, idCliente)"
                                    + "VALUES ('%s', '%d', '%f', '%f', '%f', '%s')",
                            "PlanPostPagoMinutosMegas",
                            planMinutosMegas.getMinutosN(),
                            planMinutosMegas.getCostoMinutosN(),
                            planMinutosMegas.getMegasAGigas(),
                            planMinutosMegas.getCostoPorGiga(),
                            idClienteRecienInsertado);
                } else if (plan instanceof PlanPostPagoMinutosMegasEconomico) {
                    PlanPostPagoMinutosMegasEconomico planMinutosMegasEconomico = (PlanPostPagoMinutosMegasEconomico) plan;
                    insertPlanQuery = String.format("INSERT INTO planes (tipoPlan, minutosPlan, costoMinutosPlan, megasPlan, costoPorGigaPlan, porcentajeDescuento, idCliente)"
                                    + "VALUES ('%s', '%d', '%f', '%f', '%f', '%f', '%s')",
                            "PlanPostPagoMinutosMegasEconomico",
                            planMinutosMegasEconomico.getMinutosN(),
                            planMinutosMegasEconomico.getCostoMinutosN(),
                            planMinutosMegasEconomico.getMegasAGigas(),
                            planMinutosMegasEconomico.getCostoPorGiga(),
                            planMinutosMegasEconomico.getPorcentajeDescuento(),
                            idClienteRecienInsertado);
                }

                statement.executeUpdate(insertPlanQuery);
            }

            // Calcular el valor mensual y actualizar la tabla "clientes" con el valor calculado
            double valorMensual = calcularValorMensual(planes);
            String updateClienteQuery = String.format("UPDATE clientes SET pagoMensual='%f' WHERE id='%s'",
                    valorMensual, idClienteRecienInsertado);
            statement.executeUpdate(updateClienteQuery);

            obtenerConexion().close();
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private double calcularValorMensual(List<Plan> planes) {
        double valorMensual = 0.0;
        for (Plan plan : planes) {
            valorMensual += plan.calcularCostoPlan();
        }
        return valorMensual;
    }

    public ArrayList<clientes> obtenerDataClientes() {
        ArrayList<clientes> lista = new ArrayList<>();
        try {
            establecerConexion();
            Statement statement = obtenerConexion().createStatement();
            String data = "Select * from clientes;";
            ResultSet rs = statement.executeQuery(data);
            while (rs.next()) {
                clientes cl = new clientes();
                cl.setNombre(rs.getString("nombre"));
                cl.setId(rs.getString("id"));
                cl.setCiudad(rs.getString("ciudad"));
                cl.setMarcaC(rs.getString("marcaC"));
                cl.setModeloC(rs.getString("modeloC"));
                cl.setNumeroC(rs.getString("numeroC"));
                cl.setPagoMensual(rs.getDouble("pagoMensual"));
                cl.setEdad(rs.getInt("edad"));
                cl.setMail(rs.getString("mail"));

                // Obtener los planes asociados al cliente y agregarlos a la lista de planes del cliente
                List<Plan> planesCliente = obtenerPlanesCliente(cl.getId());
                cl.setPlanes(planesCliente);

                lista.add(cl);
            }
            obtenerConexion().close();
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return lista;
    }

    public List<Plan> obtenerPlanesCliente(String idCliente) {
        List<Plan> planesCliente = new ArrayList<>();
        try {
            establecerConexion();
            Statement statement = obtenerConexion().createStatement();
            String data = String.format("SELECT * FROM planes WHERE idCliente='%s'", idCliente);
            ResultSet rs = statement.executeQuery(data);
            while (rs.next()) {
                Plan plan = null;
                String tipoPlan = rs.getString("tipoPlan");
                switch (tipoPlan) {
                    case "PlanPostPagoMegas":
                        plan = new PlanPostPagoMegas(rs.getDouble("tarifaBase"), rs.getInt("minutosPlan"), rs.getDouble("costoMinutosPlan"), rs.getDouble("megasGigas"), rs.getDouble("costoPorGigasPlan"));
                        break;
                    case "PlanPostPagoMinutos":
                        plan = new PlanPostPagoMinutos(rs.getInt("minutosInternacionales"), rs.getDouble("costoMinutosInternacionales"), rs.getInt("minutosN"), rs.getDouble("costoMinutosN"), rs.getDouble("megasGigas"), rs.getDouble("costoMegasGigas"));
                        break;
                    case "PlanPostPagoMinutosMegas":
                        plan = new PlanPostPagoMinutosMegas(rs.getInt("minutosPlan"), rs.getDouble("costoMinutosPlan"), rs.getDouble("megasPlan"), rs.getDouble("costoPorGigaPlan"));
                        break;
                    case "PlanPostPagoMinutosMegasEconomico":
                        plan = new PlanPostPagoMinutosMegasEconomico(rs.getDouble("porcentajeDescuento"), rs.getInt("minutosPlan"), rs.getDouble("costoMinutosPlan"), rs.getDouble("megasPlan"), rs.getDouble("costoPorGigaPlan"));
                        break;
                }
                if (plan != null) {
                    planesCliente.add(plan);
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return planesCliente;
    }

    public void actualizarCliente(clientes cl) {
        try {
            establecerConexion();
            Statement statement = obtenerConexion().createStatement();
            String data = String.format("UPDATE clientes SET nombre='%s', ciudad='%s', marcaC='%s', modeloC='%s', " +
                            "numeroC='%s', pagoMensual='%f', edad='%d', mail='%s' WHERE id='%s'",
                    cl.getNombre(), cl.getCiudad(), cl.getMarcaC(), cl.getModeloC(),
                    cl.getNumeroC(), cl.getPagoMensual(), cl.getEdad(), cl.getMail(), cl.getId());
            statement.executeUpdate(data);
            obtenerConexion().close();
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void eliminarClientePorID(String idCliente) {
        try {
            establecerConexion();
            Statement statement = obtenerConexion().createStatement();
            String data = String.format("DELETE FROM clientes WHERE id='%s'", idCliente);
            statement.executeUpdate(data);
            obtenerConexion().close();
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public clientes buscarClientePorID(String idCliente) {
        clientes clienteEncontrado = null;
        try {
            establecerConexion();
            Statement statement = obtenerConexion().createStatement();
            String data = String.format("SELECT * FROM clientes WHERE id='%s'", idCliente);
            ResultSet rs = statement.executeQuery(data);
            if (rs.next()) {
                clienteEncontrado = new clientes();
                clienteEncontrado.setNombre(rs.getString("nombre"));
                clienteEncontrado.setId(rs.getString("id"));
                clienteEncontrado.setCiudad(rs.getString("ciudad"));
                clienteEncontrado.setMarcaC(rs.getString("marcaC"));
                clienteEncontrado.setModeloC(rs.getString("modeloC"));
                clienteEncontrado.setNumeroC(rs.getString("numeroC"));
                clienteEncontrado.setPagoMensual(rs.getDouble("pagoMensual"));
                clienteEncontrado.setEdad(rs.getInt("edad"));
                clienteEncontrado.setMail(rs.getString("mail"));
            }
            obtenerConexion().close();
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return clienteEncontrado;
    }

    public Factura obtenerClienteInfoPorId(String idCliente) {
        Factura clienteInfo = null;

        try {
            establecerConexion();
            Statement statement = obtenerConexion().createStatement();

            // Consulta para obtener los datos del cliente con el id proporcionado
            String dataCliente = String.format("SELECT * FROM clientes WHERE id='%s'", idCliente);
            ResultSet rsCliente = statement.executeQuery(dataCliente);

            if (rsCliente.next()) {
                String nombre = rsCliente.getString("nombre");
                String ciudad = rsCliente.getString("ciudad");
                double pagoMensual = rsCliente.getDouble("pagoMensual");

                // Consulta para obtener el tipo de plan asociado al cliente
                String dataPlan = String.format("SELECT tipoPlan FROM planes WHERE idCliente='%s'", idCliente);
                ResultSet rsPlan = statement.executeQuery(dataPlan);
                String tipoPlan = "";
                while (rsPlan.next()) {
                    tipoPlan += rsPlan.getString("tipoPlan") + ", ";
                }
                if (!tipoPlan.isEmpty()) {
                    tipoPlan = tipoPlan.substring(0, tipoPlan.length() - 2);
                }

                clienteInfo = new Factura(nombre, ciudad, pagoMensual, tipoPlan);
            }

            obtenerConexion().close();
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        return clienteInfo;
    }
}