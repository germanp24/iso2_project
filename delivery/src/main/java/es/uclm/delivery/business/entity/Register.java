package es.uclm.delivery.business.entity;

public class Register {

    private String name;
    private String surname;
    private String email;
    private String DNI;
    private String password;
    
    public Register(String name, String surname, String email, String dNI, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        DNI = dNI;
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDNI() {
        return DNI;
    }
    public void setDNI(String dNI) {
        DNI = dNI;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}