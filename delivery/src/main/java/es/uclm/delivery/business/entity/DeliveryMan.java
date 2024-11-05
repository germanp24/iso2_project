package es.uclm.delivery.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DeliveryMan {
    @Id
    @Column
    private String nif;

    @Column
    private String id_usuary;

    @Column
    private String name;

    @Column
    private String surnames;

    @Column
    private int efficiency;

    public DeliveryMan() {

    }

    public DeliveryMan(String name, String surnames, String nif, int efficiency, String id_usuary) {
        this.name = name;
        this.surnames = surnames;
        this.nif = nif;
        this.efficiency = efficiency;
        this.id_usuary = id_usuary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public String getId_usuary() {
        return id_usuary;
    }

    public void setId_usuary(String id_usuary) {
        this.id_usuary = id_usuary;
    }

    @Override
    public String toString() {
        return String.format("DeliveryMan [nif=%s, id_usuary=%s, name=%s, surnames=%s, efficiency=%s]", nif, id_usuary, name, surnames, efficiency);
    }

}
