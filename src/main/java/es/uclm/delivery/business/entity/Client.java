package es.uclm.delivery.business.entity;

import jakarta.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    @Column
    private String name;

    @Column
    private String surnames_M;

    @Column
    private String surnames_F;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuary_id")
    private Usuary usuary;

    public Client() {

    }

    public Client(String name, String surnames_F,String surnames_M, Usuary usuary) {
        this.name = name;
        this.surnames_M = surnames_M;
        this.surnames_F = surnames_F;
        this.usuary = usuary;
    }

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


    @Override
    public String toString() {
        return String.format("Client [id_client=%s, name=%s, surnames_M=%s, surname_F=%s]", idClient, name, surnames_M, surnames_F,usuary);
    }


}
