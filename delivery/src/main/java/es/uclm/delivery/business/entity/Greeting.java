package es.uclm.delivery.business.entity;

import org.hibernate.annotations.DialectOverride.OverridesAnnotation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The @Column annotation is used to specify the name of the column in the database.
    // @Column can be not specified, but it is recommended to use it to avoid any confusion.

    @Column
    private String person;
    @Column
    private String content;

    // The blank constructor is required by the JPA
    public Greeting() {
    }
    // The constructor with parameters is used to create new instances
    public Greeting(String person, String content) {
        super();
        this.person = person;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("Greeting [id=%d, person=%s, content=%s]", id, person, content);
    }
}