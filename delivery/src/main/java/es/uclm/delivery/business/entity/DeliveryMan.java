package es.uclm.delivery.business.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DeliveryMan {
    @Id
    
    @Column
    private String name;

    @Column
    private String surnames;

    @Column
    private String nif;

    @Column
    private int efficiency;

    public DeliveryMan(String name, String surnames, String nif, int efficiency) {
        this.name = name;
        this.surnames = surnames;
        this.nif = nif;
        this.efficiency = efficiency;
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

}
