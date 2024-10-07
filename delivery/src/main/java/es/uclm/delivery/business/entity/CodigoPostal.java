package es.uclm.delivery.business.entity;

import java.util.List;

public enum CodigoPostal {
    CP_10001, CP_10002, CP_10003;  // Define varios códigos postales.

    private List<Repartidor> repartidores;

    public List<Repartidor> getRepartidores() {
        return repartidores;
    }

    public void setRepartidores(List<Repartidor> repartidores) {
        this.repartidores = repartidores;
    }
}
