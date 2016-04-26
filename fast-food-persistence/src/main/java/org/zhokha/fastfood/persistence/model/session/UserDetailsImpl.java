package org.zhokha.fastfood.persistence.model.session;

/**
 * author: maks
 * date: 25.07.15
 */
public class UserDetailsImpl implements UserDetails {

    private int id;
    private String userName;

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
