package es.uclm.delivery.business.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class DeliveryMan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_DeliveryMan;

    @Column
    private String nif;

    @Column
    private String name;

    @Column
    private String surnames;

    @Column
    private String tipoAuto;

    @Column
    private int efficiency;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuary_id")
    private Usuary usuary;

    public DeliveryMan() {

    }

    public DeliveryMan(String nif, String name, String surnames, int efficiency, String tipoAuto ,Usuary usuary) {
        this.nif = nif;
        this.name = name;
        this.surnames = surnames;
        this.efficiency = efficiency;
        this.usuary = usuary;
        this.tipoAuto = tipoAuto;
    }

    public Long getId_DeliveryMan() {
        return id_DeliveryMan;
    }

    public void setId_DeliveryMan(Long id_DeliveryMan) {
        this.id_DeliveryMan = id_DeliveryMan;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getName() {
        return name;
    }

    public void setName_a(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public String getTransport() {
        return tipoAuto;
    }
    public void setTransport(String tipoAuto) {
        this.tipoAuto = tipoAuto;
    }

    public Usuary getUsuary() {
        return usuary;
    }

    public void setUsuary(Usuary usuary) {
        this.usuary = usuary;
    }

    @Override
    public String toString() {
        return "DeliveryMan [id_DeliveryMan=" + id_DeliveryMan + ", nif=" + nif + ", name=" + name + ", surnames="
                + surnames + ", efficiency=" + efficiency + ", tipoAuto=" + tipoAuto + ", usuary=" + usuary + "]";
    }

}
