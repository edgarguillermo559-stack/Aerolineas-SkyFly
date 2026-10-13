public class ControlVuelo {
    Nodo head;

    public void aggVuelo(Vuelos vuelos) {
        Nodo nuevo = new Nodo(vuelos);
        if (head == null) {
            head = nuevo;
        } else {
            Nodo temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = nuevo;
        }
    }

    public void VuelosDemorados() {
        Nodo temp = head;
        while (temp != null) {
            if (temp.vuelos.getEstado().equalsIgnoreCase("Demorado")) {
                System.out.println(temp.vuelos.toString());
            }
            temp = temp.next;
        }
    }

    public void MostrarOrdenadosPorDuracion() {

        if (head == null) {
            System.out.println("No hay vuelos registrados.");
            return;
        }

        int contador = 0;
        Nodo temp = head;
        while (temp != null) {
            contador++;
            temp = temp.next;
        }

        for (int i = 0; i < contador - 1; i++) {
            Nodo actual = head;
            for (int j = 0; j < contador - i - 1; j++) {

                if (actual.vuelos.getDuracionVuelo() >
                        actual.next.vuelos.getDuracionVuelo()) {

                    Vuelos aux = actual.vuelos;
                    actual.vuelos = actual.next.vuelos;
                    actual.next.vuelos = aux;
                }

                actual = actual.next;
            }
        }

        mostrar();
    }

    public void CancelarVuelosADestino(String destino) {

        if (destino == null || destino.trim().isEmpty()) {
            System.out.println("Error: El destino no puede ser nulo ni estar vacío.");
            return;
        }

        String destinoBuscado = destino.trim();

        Nodo temp = head;
        boolean existe = false;

        while (temp != null) {
            if (temp.vuelos.getDestino().trim().equalsIgnoreCase(destinoBuscado)) {
                existe = true;
                break;
            }
            temp = temp.next;
        }

        if (!existe) {
            System.out.println("Error: El destino \"" + destinoBuscado + "\" no existe entre los vuelos registrados.");
            return;
        }

        int eliminados = 0;
        Nodo actual = head;
        Nodo anterior = null;

        while (actual != null) {
            if (actual.vuelos.getDestino().trim().equalsIgnoreCase(destinoBuscado)) {
                if (anterior == null) {
                    head = actual.next;
                } else {
                    anterior.next = actual.next;
                }
                eliminados++;
                actual = actual.next;
            } else {
                anterior = actual;
                actual = actual.next;
            }
        }

        System.out.println("Vuelos Eliminados: " + eliminados);
        ;
    }

    public boolean verificarVuelo(String numeroVuelo) {
        Nodo temp = head;
        while (temp != null) {
            if (temp.vuelos.getNumeroVuelo().equalsIgnoreCase(numeroVuelo)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void cambiarEstadoVuelo(String numeroVuelo, String estadoFinal) {
        Nodo temp = head;
        while (temp != null) {
            if (temp.vuelos.getNumeroVuelo().equalsIgnoreCase(numeroVuelo)) {
                temp.vuelos.setEstado(estadoFinal);
                System.out.println("El estado del Vuelo fue Cambiado correctamente");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Error: El numero de vuelo no fue encontrado en el registro de la AeroLinea");
    }

   public void buscarVueloMasLargo() {
    if (head == null) {
        System.out.println("No hay vuelos registrados.");
        return;
    }

    Nodo temp = head;
    Nodo mayor = head;
    while (temp != null) {
        if (temp.vuelos.getDuracionVuelo() > mayor.vuelos.getDuracionVuelo()) {
            mayor = temp;
        }
        temp = temp.next;
    }
    System.out.println(mayor.vuelos.toString());
}

    public void mostrar() {
        Nodo temp = head;
        while (temp != null) {
            System.out.println(temp.vuelos.toString());
            temp = temp.next;
        }
    }

}
