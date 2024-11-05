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
    private String id_DeliveryMan;

    @Column
    private String name_r;

    @Column
    private String surnames_r;

    @Column
    private int efficiency;

    public DeliveryMan() {

    }

    public DeliveryMan(String nif, String id_DeliveryMan, String name_r, String surnames_r, int efficiency) {
        this.nif = nif;
        this.id_DeliveryMan = id_DeliveryMan;
        this.name_r = name_r;
        this.surnames_r = surnames_r;
        this.efficiency = efficiency;
    }

    public String getId_DeliveryMan() {
        return id_DeliveryMan;
    }

    public void setId_DeliveryMan(String id_DeliveryMan) {
        this.id_DeliveryMan = id_DeliveryMan;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getName_r() {
        return name_r;
    }

    public void setName_a(String name_r) {
        this.name_r = name_r;
    }

    public String getSurnames_r() {
        return surnames_r;
    }

    public void setSurnames_a(String surnames_r) {
        this.surnames_r = surnames_r;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    public String toString() {
        return String.format("DeliveryMan [nif=%s, id_DeliveryMan=%s, name_r=%s, surnames_r=%s, efficiency=%s]", nif,
                id_DeliveryMan, name_r, surnames_r, efficiency);
    }

}
