package org.zhokha.fastfood.persistence.model.session;

/**
 * author: maks
 * date: 25.07.15
 */
public interface UserDetails {

    int getId();
    void setId(int id);
    String getUserName();
    void setUserName(String userName);
}
