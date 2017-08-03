/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.turek.liceum.rentit.session;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import pl.turek.liceum.rentit.model.Account;

/**
 *
 * @author miszcz
 */
@Named(value = "loginBean")
@RequestScoped
//@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Transactional
public class LoginBean extends HttpServlet{

    private String userName;
    private String password;
    private Account currentUser;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        ExternalContext externalContext
                = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            request.login(userName, password);
                   return "index";
            }
            catch (ServletException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.INFO,
                    "Failed to log in {0}", userName);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage(
                    "Failed to log in");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(null, facesMessage);
            return null;
        }
    }

    public String logout() {
        ExternalContext externalContext
                = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            request.logout();
            return "/login?faces-redirect=true";
        } catch (ServletException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE,
                    "Failed to logout", ex);
        }
        return null;
    }

//    public Account getCurrentUser() {
//        FacesContext fc = FacesContext.getCurrentInstance();
//        ExternalContext externalContext = fc.getExternalContext();
//        if (externalContext.getUserPrincipal() == null) {
//            Logger.getLogger("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!current principal is null");
//        } else {
////            Integer id = Integer.parseInt(externalContext.getUserPrincipal().getName());
////            String name = kontoFacade.znajdzLogin(externalContext.getUserPrincipal().getName());
//            Logger.getLogger("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!LOGGED USER " + externalContext.getUserPrincipal().getName());
////            Logger.getLogger("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!LOGGED USER " + id);
//            try {
////                currentUser = getLoginService().getLoginById(id);
////                currentUser = kontoFacade.znajdzLogin(externalContext.getUserPrincipal().getName());
//                listaKontPageBean.edytujKonto();
//                currentUser = edytujKontoPageBean.getKonto();
//            } catch (Exception ex) {
//                Logger.getLogger("!!!!!!!!!!!!!!!!!!!!!!currentUser not loaded ");
//            }
//        }
//        return currentUser;
//    }

//    public Account getCurrentUser() {
//        FacesContext fc = FacesContext.getCurrentInstance();
//        ExternalContext externalContext = fc.getExternalContext();
//        if (externalContext.getUserPrincipal() == null) {
//            Logger.getLogger("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!current principal is null");
//        } else {
//            Integer id = Integer.parseInt(externalContext.getUserPrincipal().getName());
//            Logger.getLogger("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!LOGGED USER " + id);
//            try {
//                currentUser = getLoginService().getLoginById(id);
//            } catch (Exception ex) {
//            }
//        }
//        return currentUser;
//    }
}
