    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
     */
    package vista;

    import controlador.Enlace;
    import modelo.*;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;

    /**
     *
     * @author Jeanca
     */
    public class ProyectoFinal {

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            Enlace enlace = new Enlace();
            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n--- Menú ---");
                System.out.println("1. Crear nuevo cliente");
                System.out.println("2. Mostrar todos los clientes");
                System.out.println("3. Actualizar datos de un cliente");
                System.out.println("4. Eliminar un cliente");
                System.out.println("5. Mostrar factura de un cliente");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        // Crear un nuevo cliente
                        clientes nuevoCliente = obtenerDatosPorTeclado(scanner);

                        // Preguntar al cliente qué tipo de plan desea
                        System.out.println("\nSeleccione el tipo de plan para el primer plan:");
                        System.out.println("1. Plan PostPago Minutos");
                        System.out.println("2. Plan PostPago Megas");
                        System.out.println("3. Plan PostPago Minutos y Megas");
                        System.out.println("4. Plan PostPago Minutos, Megas y Descuento");
                        int opcionPlan1 = scanner.nextInt();
                        scanner.nextLine();

// Crear el primer plan
                        Plan plan1 = crearPlan(opcionPlan1, scanner);
                        if (plan1 != null) {
                            nuevoCliente.agregarPlan(plan1);
                        }

// Preguntar si desea agregar un segundo plan
                        scanner.nextLine();
                        System.out.println("¿Desea agregar otro plan? (S/N)");
                        String respuesta = scanner.nextLine();
                        if (respuesta.equalsIgnoreCase("S")) {
                            System.out.println("\nSeleccione el tipo de plan para el segundo plan:");
                            System.out.println("1. Plan PostPago Minutos");
                            System.out.println("2. Plan PostPago Megas");
                            System.out.println("3. Plan PostPago Minutos y Megas");
                            System.out.println("4. Plan PostPago Minutos, Megas y Descuento");
                            int opcionPlan2 = scanner.nextInt();
                            scanner.nextLine();

                            Plan plan2 = crearPlan(opcionPlan2, scanner);
                            if (plan2 != null) {
                                nuevoCliente.agregarPlan(plan2);
                            }
                        }

                        enlace.insertarCliente(nuevoCliente);
                        System.out.println("¡Nuevo cliente creado exitosamente!");
                        break;
                    case 2:
                        // Mostrar todos los clientes
                        ArrayList<clientes> listaClientes = enlace.obtenerDataClientes();
                        mostrarClientes(listaClientes);
                        break;
                    case 3:
                        // Actualizar datos de un cliente
                        System.out.print("Ingrese el ID del cliente que desea actualizar: ");
                        String idCliente = scanner.nextLine();
                        clientes clienteExistente = enlace.buscarClientePorID(idCliente);
                        if (clienteExistente != null) {
                            actualizarClientePorTeclado(clienteExistente, scanner);
                            enlace.actualizarCliente(clienteExistente);
                            System.out.println("Datos del cliente actualizados exitosamente.");
                        } else {
                            System.out.println("Cliente no encontrado.");
                        }
                        break;
                    case 4:
                        // Eliminar un cliente
                        System.out.print("Ingrese el ID del cliente que desea eliminar: ");
                        String idClienteAEliminar = scanner.nextLine();
                        enlace.eliminarClientePorID(idClienteAEliminar);
                        System.out.println("Cliente eliminado exitosamente.");
                        break;
                    case 5:
                        // Mostrar factura de un cliente
                        System.out.print("Ingrese el ID del cliente para ver la factura: ");
                        String idClienteFactura = scanner.nextLine();
                        Factura clienteFactura = enlace.obtenerClienteInfoPorId(idClienteFactura);
                        if (clienteFactura != null) {
                            System.out.println("Factura del cliente con ID " + idClienteFactura + ":");
                            System.out.println("Nombre: " + clienteFactura.getNombre());
                            System.out.println("Ciudad: " + clienteFactura.getCiudad());
                            System.out.println("Pago Mensual: " + clienteFactura.getPagoMensual());
                            System.out.println("Tipo de Plan(es): " + clienteFactura.getTipoPlan());
                        } else {
                            System.out.println("No se encontró ningún cliente con el ID " + idClienteFactura);
                        }
                            break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }

            } while (opcion != 0);

            scanner.close();
        }

        // Método para obtener los datos de un nuevo cliente por teclado
        public static clientes obtenerDatosPorTeclado(Scanner scanner) {
            clientes nuevoCliente = new clientes();

            System.out.println("Ingrese los datos del nuevo cliente:");
            System.out.print("Nombre: ");
            nuevoCliente.setNombre(scanner.nextLine());

            System.out.print("ID: ");
            nuevoCliente.setId(scanner.nextLine());

            System.out.print("Ciudad: ");
            nuevoCliente.setCiudad(scanner.nextLine());

            System.out.print("Marca del celular: ");
            nuevoCliente.setMarcaC(scanner.nextLine());

            System.out.print("Modelo del celular: ");
            nuevoCliente.setModeloC(scanner.nextLine());

            System.out.print("Número de teléfono: ");
            nuevoCliente.setNumeroC(scanner.nextLine());

            System.out.print("Edad: ");
            nuevoCliente.setEdad(scanner.nextInt());
            scanner.nextLine(); // Consume la nueva línea pendiente después del nextInt()

            System.out.print("Correo electrónico: ");
            nuevoCliente.setMail(scanner.nextLine());

            return nuevoCliente;
        }

        // Método para mostrar los datos de todos los clientes
        public static void mostrarClientes(ArrayList<clientes> listaClientes) {
            System.out.println("\nClientes registrados:");
            for (clientes cliente : listaClientes) {
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("ID: " + cliente.getId());
                System.out.println("Ciudad: " + cliente.getCiudad());
                System.out.println("--------------------");
            }
        }

        // Método para actualizar los datos de un cliente por teclado
        public static void actualizarClientePorTeclado(clientes cliente, Scanner scanner) {

            System.out.println("Ingrese los nuevos datos del cliente:");
            System.out.print("Nombre: ");
            cliente.setNombre(scanner.nextLine());

            System.out.print("Ciudad: ");
            cliente.setCiudad(scanner.nextLine());

            System.out.print("Marca del celular: ");
            cliente.setMarcaC(scanner.nextLine());

            System.out.print("Modelo del celular: ");
            cliente.setModeloC(scanner.nextLine());

            System.out.print("Número de teléfono: ");
            cliente.setNumeroC(scanner.nextLine());

            System.out.print("Edad: ");
            cliente.setEdad(scanner.nextInt());
            scanner.nextLine();

            System.out.print("Correo electrónico: ");
            cliente.setMail(scanner.nextLine());
        }

        // Método para crear un plan según la opción seleccionada por el usuario
        public static Plan crearPlan(int opcionPlan, Scanner scanner) {
            Plan plan = null;
            switch (opcionPlan) {
                case 1:
                    System.out.println("Ingrese la cantidad de minutos internacionales:");
                    int minutosInternacionales = scanner.nextInt();
                    System.out.println("Ingrese el costo de minutos internacionales:");
                    double costoMinutosInternacionales = scanner.nextDouble();
                    int megasGigas = 0;
                    double costoMegasGigas = 0;
                    System.out.print("Ingrese la cantidad de minutos: ");
                    int minutosN = scanner.nextInt();
                    System.out.print("Ingrese el costo por minuto: ");
                    double costoMinutosN = scanner.nextDouble();
                    scanner.nextLine();
                    plan = new PlanPostPagoMinutos( minutosInternacionales, costoMinutosInternacionales, minutosN, costoMinutosN, megasGigas, costoMegasGigas);
                    break;
                case 2:
                    System.out.print("Ingrese la cantidad de megas: ");
                    double megasAGigas = scanner.nextDouble();
                    System.out.print("Ingrese el costo por giga: ");
                    double costoPorGiga = scanner.nextDouble();
                    System.out.print("Ingrese la tarifa base: ");
                    double tarifaBase = scanner.nextDouble();
                    int minutosN2 = 0;
                    double costoMinutosN2 = 0;
                    plan = new PlanPostPagoMegas(tarifaBase, minutosN2, costoMinutosN2, megasAGigas, costoPorGiga);
                    break;
                case 3:
                    System.out.print("Ingrese la cantidad de minutos: ");
                    int minutosNM = scanner.nextInt();
                    System.out.print("Ingrese el costo por minuto: ");
                    double costoMinutosNM = scanner.nextDouble();
                    System.out.print("Ingrese la cantidad de megas: ");
                    double megasAGigasNM = scanner.nextDouble();
                    System.out.print("Ingrese el costo por giga: ");
                    double costoPorGigaNM = scanner.nextDouble();
                    scanner.nextLine();
                    plan = new PlanPostPagoMinutosMegas(minutosNM, costoMinutosNM, megasAGigasNM, costoPorGigaNM);
                    break;
                case 4:
                    System.out.print("Ingrese la cantidad de minutos: ");
                    int minutosNMD = scanner.nextInt();
                    System.out.print("Ingrese el costo por minuto: ");
                    double costoMinutosNMD = scanner.nextDouble();
                    System.out.print("Ingrese la cantidad de megas: ");
                    double megasAGigasNMD = scanner.nextDouble();
                    System.out.print("Ingrese el costo por giga: ");
                    double costoPorGigaNMD = scanner.nextDouble();
                    System.out.print("Ingrese el porcentaje de descuento: ");
                    double porcentajeDescuento = scanner.nextDouble();
                    scanner.nextLine();
                    plan = new PlanPostPagoMinutosMegasEconomico(porcentajeDescuento, minutosNMD, costoMinutosNMD, megasAGigasNMD, costoPorGigaNMD);
                    break;
                default:
                    System.out.println("Opción inválida. No se agregará ningún plan al cliente.");
            }
            return plan;
        }
    }

