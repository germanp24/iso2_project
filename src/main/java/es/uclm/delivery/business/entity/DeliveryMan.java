package es.uclm.delivery.business.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.*;

@Entity
public class DeliveryMan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeliveryMan;

    @Column
    @Pattern(regexp = "\\d{8}[A-Za-z]", message = "El DNI debe tener 8 números seguidos de una letra")
    private String dni;

    @Column (unique = true)
    private String nif_delivery;

    @Column
    private String names;

    @Column
    private String surnames_M;

    @Column
    private String surnames_F;

    @Column
    @Size(min = 9, max = 9, message = "El número de teléfono debe tener 9 dígitos")
    private String phone;

    @Column
    private String tipoAuto;

    @Column
    private int efficiency;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuary_id")
    private Usuary usuary;

    @OneToMany(mappedBy = "deliveryMan", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<DeliveryService> deliveryServices = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "delivRestaurant",
            joinColumns = @JoinColumn(name = "nif_delivery"),
            inverseJoinColumns = @JoinColumn (name = "cif")
    )
    private Set<Restaurant> restaurant = new HashSet<>();

    public DeliveryMan() {

    }

    public DeliveryMan(String dni, String names, String surnamesM, String surnamesF, int efficiency, String tipoAuto, String phone, Usuary usuary) {
        this.dni = dni;
        this.names = names;
        this.surnames_M = surnamesM;
        this.surnames_F = surnamesF;
        this.phone = phone;
        this.efficiency = efficiency;
        this.tipoAuto = tipoAuto;
        this.usuary = usuary;
    }

    @PrePersist
    private void generateNifAdmin() {
        if (this.nif_delivery == null || this.nif_delivery.isEmpty()) {
            this.nif_delivery = generateEmployeeId();
        }
    }
    private String generateEmployeeId() {
        Random random = new Random();
        String numbers = String.format("%06d", random.nextInt(1000000));
        char letter = (char) ('A' + random.nextInt(26));
        return numbers + letter;
    }

    public String getNif_delivery() {return nif_delivery;}
    public void setNif_delivery(String nif_delivery) {this.nif_delivery = nif_delivery;}

    public Long getIdDeliveryMan() {
        return idDeliveryMan;
    }
    public void setIdDeliveryMan(Long idDeliveryMan) {
        this.idDeliveryMan = idDeliveryMan;
    }

    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}

    public String getNames() {
        return names;
    }
    public void setNames(String names) {
        this.names = names;
    }

    public String getSurnames_M() {return surnames_M;}
    public void setSurnames_M(String surnames_M) {this.surnames_M = surnames_M;}

    public String getSurnames_F() {return surnames_F;}
    public void setSurnames_F(String surnames_F) {this.surnames_F = surnames_F;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public int getEfficiency() {
        return efficiency;
    }
    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public String getTipoAuto() {
        return tipoAuto;
    }
    public void setTipoAuto(String tipoAuto) {
        this.tipoAuto = tipoAuto;
    }

    public Usuary getUsuary() {
        return usuary;
    }
    public void setUsuary(Usuary usuary) {
        this.usuary = usuary;
    }

    public List<DeliveryService> getDeliveryServices() {return deliveryServices;}
    public void setDeliveryServices(List<DeliveryService> deliveryServices) {this.deliveryServices = deliveryServices;}

    public Set<Restaurant> getRestaurant() {return restaurant;}
    public void setRestaurant(Set<Restaurant> restaurant) {this.restaurant = restaurant;}

    @Override
    public String toString() {
        return String.format("DeliveryMan [dni=%s, idDeliveryMan=%s, name=%s, surnames_M%s, surnames_F=%s, phone=%s, efficiency=%s, tipoAuto=%s, nif_delivery=%s]",
                dni,idDeliveryMan,names,surnames_M,surnames_F,phone,efficiency,tipoAuto,nif_delivery);
    }

}
