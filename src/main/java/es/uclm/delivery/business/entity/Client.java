package es.uclm.delivery.business.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;


@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @Column
    @Pattern(regexp = "\\d{8}[A-Za-z]", message = "El DNI debe tener 8 números seguidos de una letra")
    private String dni;

    @Column
    private String name;

    @Column
    private String surnames_M;

    @Column
    private String surnames_F;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuary_id")
    private Usuary usuary;

    //@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval=true)
    //private List<CustomerOrder> customerOrder = new ArrayList<>();

    public Client() {

    }

    public Client(String dni, String name, String surnames_F, String surnames_M, Usuary usuary) {
        this.dni = dni;
        this.name = name;
        this.surnames_M = surnames_M;
        this.surnames_F = surnames_F;
        this.usuary = usuary;
    }

    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Long getIdClient() {
        return idClient;
    }
    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getSurnames_M() {return surnames_M;}
    public void setSurnames_M(String surnames_M) {this.surnames_M = surnames_M;}

    public String getSurnames_F() {return surnames_F;}
    public void setSurnames_F(String surnames_F) {this.surnames_F = surnames_F;}

    public Usuary getUsuary() {
        return usuary;
    }
    public void setUsuary(Usuary usuary) {
        this.usuary = usuary;
    }

    //public List<CustomerOrder> getCustomerOrder() {return customerOrder;}
    //public void setCustomerOrder(List<CustomerOrder> customerOrder) {this.customerOrder = customerOrder;}


    @Override
    public String toString() {
        return String.format("Client [id_client=%s, dni=%s ,name=%s, surnames_M=%s, surname_F=%s]", idClient, dni, name, surnames_M, surnames_F,usuary);
    }


}
