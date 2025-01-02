package es.uclm.delivery.business.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Random;

@Entity
public class RestaurantAdministrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdmin;

    @Column (unique = true)
    private String nif_admin;

    @Column
    @Pattern(regexp = "\\d{8}[A-Za-z]", message = "El DNI debe tener 8 números seguidos de una letra")
    private String dni;

    @Column
    private String name;

    @Column
    private String surnames_M;

    @Column
    private String surnames_F;

    @Column
    @Size(min = 9, max = 9, message = "El número de teléfono debe tener 9 dígitos")
    private String phone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuary_id")
    private Usuary usuary;

    public RestaurantAdministrator() {
    }

    public RestaurantAdministrator(String dni, String name, String surnamesM, String surnamesF, String phone, Usuary usuary) {
        this.dni = dni;
        this.name = name;
        this.surnames_M = surnamesM;
        this.surnames_F = surnamesF;
        this.phone = phone;
        this.usuary = usuary;
    }

    @PrePersist
    private void generateNifAdmin() {
        if (this.nif_admin == null || this.nif_admin.isEmpty()) {
            this.nif_admin = generateEmployeeId();
        }
    }
    private String generateEmployeeId() {
        Random random = new Random();
        String numbers = String.format("%06d", random.nextInt(1000000));
        char letter = (char) ('A' + random.nextInt(26));
        return numbers + letter;
    }

    public String getNif_admin() {return nif_admin;}
    public void setNif_admin(String nif_admin) {this.nif_admin = nif_admin;}

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public Long getId_admin() {
        return idAdmin;
    }
    public void setId_admin(Long id_admin) {
        this.idAdmin = id_admin;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames_M() {return surnames_M;}
    public void setSurnames_M(String surnames_M) {this.surnames_M = surnames_M;}

    public String getSurnames_F() {return surnames_F;}
    public void setSurnames_F(String surnames_F) {this.surnames_F = surnames_F;}

    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public Usuary getUsuary() {
        return usuary;
    }
    public void setUsuary(Usuary usuary) {
        this.usuary = usuary;
    }

    @Override
    public String toString() {
        return String.format( "RestaurantAdministrator [dni=%s, id_admin=%s, name=%s, surnames_F%s, surnames_M=%s, phone=%s, nif_admin=%s]", dni, idAdmin, name, surnames_F,surnames_M,phone,nif_admin);
    }


}