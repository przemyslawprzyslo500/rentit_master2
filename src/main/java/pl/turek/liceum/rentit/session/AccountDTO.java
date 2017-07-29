/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.turek.liceum.rentit.session;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import pl.turek.liceum.rentit.model.Account;

/**
 *
 * @author miszcz
 */
@Named(value = "accountDTO")
@SessionScoped
public class AccountDTO {
//    private Integer id;
    private String accountFunction;
//    private boolean active;
    private String email;
    private String login;
    private String name;
    private String password;
    private String phone;
    private String surname;
    private Account currentAccount;
    
    public AccountDTO(String accountFunction, boolean active, String email, String login, String name, String password, String phone, String surname) {
        this.accountFunction = accountFunction;
//        this.active = active;
        this.email = email;
        this.login = login;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.surname = surname;
    }

    public void init(){
        accountFunction=currentAccount.getAccountFunction();
//        active=currentAccount.getActive();
        email=currentAccount.getEmail();
        login=currentAccount.getLogin();
        name=currentAccount.getName();
        password=currentAccount.getPassword();
        phone=currentAccount.getPhone();
        surname=currentAccount.getSurname();
         
    }
    
    public String getAccountFunction() {
        return accountFunction;
    }

    public void setAccountFunction(String accountFunction) {
        this.accountFunction = accountFunction;
    }

//    public boolean getActive() {
//        return active;
//    }

//    public void setActive(boolean active) {
//        this.active = active;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
