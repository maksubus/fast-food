package org.zhokha.fastfood.persistence.model.session;

/**
 * author: maks
 * date: 25.07.15
 */
public class RestoDetailsImpl implements RestoDetails {

    private int id;
    private String name;

    public RestoDetailsImpl() {
    }

    public RestoDetailsImpl(String name) {
        this.name = name;
    }

    public RestoDetailsImpl(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
