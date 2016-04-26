package org.zhokha.fastfood.persistence.model;

import javax.persistence.*;

/**
 * author: maks
 * date: 16.08.15
 */

@Entity
@Table(name = "resto")
public class Resto implements Model {

    private int id;
    private String name;

    public Resto() {
    }

    public Resto(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
