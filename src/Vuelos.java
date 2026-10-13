public class Vuelos {

    private String numeroVuelo;
    private String destino;
    private String estado;
    private double duracionVuelo;

    public Vuelos(String numeroVuelo, String destino, String estado, double duracionVuelo) {
        this.numeroVuelo = numeroVuelo;
        this.destino = destino;
        this.estado = estado;
        this.duracionVuelo = duracionVuelo;
    }

    public String getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getDuracionVuelo() {
        return duracionVuelo;
    }

    public void setDuracionVuelo(double duracionVuelo) {
        this.duracionVuelo = duracionVuelo;
    }

    @Override
    public String toString() {
        return "Vuelos{" +
                "numeroVuelo='" + numeroVuelo + '\'' +
                ", destino='" + destino + '\'' +
                ", estado='" + estado + '\'' +
                ", duracionVuelo=" + duracionVuelo + " horas" +
                '}';
    }
}