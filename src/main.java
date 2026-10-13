import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        ControlVuelo control = new ControlVuelo();
        Scanner scan = new Scanner(System.in);
        boolean menu = true;

        control.aggVuelo(new Vuelos("AA1234", "Madrid", "A tiempo", 8.5));
        control.aggVuelo(new Vuelos("IB5678", "Paris", "Demorado", 10.0));
        control.aggVuelo(new Vuelos("LA9101", "Buenos Aires", "Cancelado", 6.5));
        control.aggVuelo(new Vuelos("AV2020", "Bogota", "Demorado", 5.0));
        control.aggVuelo(new Vuelos("QF0001", "Sidney", "A tiempo", 15.75));


        while (menu) {
            System.out.println("*****||BIENVENIDOS A LA AEROLINEA SKYFLY||*****");
            System.out.println();
            System.out.println("Seleccione la opcion que desea realizar");
            System.out.println("1. Ver Los Vuelos Demorados");
            System.out.println("2. Ver Los Vuelos Ordenados Por Duracion");
            System.out.println("3. Cancelar Vuelos");
            System.out.println("4. Cambiar Estado De Vuelo");
            System.out.println("5. Busqueda de Vuelo Mas Largo");
            System.out.println("6. Salir");
            String opc = scan.nextLine();

            switch (opc) {
                case "1":
                    System.out.println("Vuelos Demorados: ");
                    control.VuelosDemorados();
                    System.out.println("Presione Enter para volver al menu principal");
                    scan.nextLine();
                    break;
                case "2":
                    System.out.println("Vuelos Ordenados: ");
                    control.MostrarOrdenadosPorDuracion();
                    System.out.println("Presione Enter para volver al menu principal");
                    scan.nextLine();
                    break;

                case "3":
                    System.out.print("Escriba el Destino: ");
                    String destino = scan.nextLine();
                    control.CancelarVuelosADestino(destino);
                    System.out.println("Presione Enter para volver al menu principal");
                    scan.nextLine();
                    break;

                case "4":
                    System.out.print("Escriba el Numero del Vuelo: ");
                    String numeroVuelo = scan.nextLine();
                    if (control.verificarVuelo(numeroVuelo)) {
                        System.out.println("Seleccione su Nuevo Estado: ");
                        boolean menuEstado = true;
                        String estdoFinal = "";
                        while (menuEstado) {
                            System.out.println("1. A tiempo");
                            System.out.println("2. Demorado");
                            System.out.println("3. Cancelado");
                            String opcEstado = scan.nextLine();
                            switch (opcEstado) {
                                case "1":
                                    estdoFinal = "A tiempo";
                                    menuEstado = false;
                                    break;
                                case "2":
                                    estdoFinal = "Demorado";
                                    menuEstado = false;
                                    break;
                                case "3":
                                    estdoFinal = "Cancelado";
                                    menuEstado = false;
                                    break;
                                default:
                                    System.out.println("La opcion introducida es incorrecta...");
                                    System.out.println("Intentelo de nuevo.");
                                    System.out.println();
                                    break;
                            }

                        }
                        control.cambiarEstadoVuelo(numeroVuelo, estdoFinal);
                    } else {
                        System.out.println("Error: El Numero de Vuelo introducido no existe");
                    }
                    System.out.println("Presione Enter para volver al menu principal");
                    scan.nextLine();
                    break;

                case "5":
                    control.buscarVueloMasLargo();
                    System.out.println("Presione Enter para volver al menu principal");
                    scan.nextLine();
                    break;

                case "6":
                    System.out.println("*****||GRACIAS POR VISITARNOS||*****");
                    menu = false;
                    break;

            }

        }
    }


}
