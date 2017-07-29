package pl.turek.liceum.rentit.converter;

import pl.turek.liceum.rentit.model.ReservStatus;
import pl.turek.liceum.rentit.facade.ReservStatusFacade;
import pl.turek.liceum.rentit.controller.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.convert.FacesConverter;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@FacesConverter(value = "reservStatusConverter")
public class ReservStatusConverter implements Converter {

    private ReservStatusFacade ejbFacade;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.getEjbFacade().find(getKey(value));
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof ReservStatus) {
            ReservStatus o = (ReservStatus) object;
            return getStringKey(o.getId());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ReservStatus.class.getName()});
            return null;
        }
    }

    private ReservStatusFacade getEjbFacade() {
        this.ejbFacade = CDI.current().select(ReservStatusFacade.class).get();
        return this.ejbFacade;
    }
}
