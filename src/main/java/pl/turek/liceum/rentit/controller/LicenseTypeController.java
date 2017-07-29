package pl.turek.liceum.rentit.controller;

import pl.turek.liceum.rentit.model.LicenseType;
import pl.turek.liceum.rentit.model.Equipment;
import java.util.Collection;
import pl.turek.liceum.rentit.facade.LicenseTypeFacade;
import pl.turek.liceum.rentit.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "licenseTypeController")
@ViewScoped
public class LicenseTypeController extends AbstractController<LicenseType> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEquipmentCollectionEmpty;

    public LicenseTypeController() {
        // Inform the Abstract parent controller of the concrete LicenseType Entity
        super(LicenseType.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEquipmentCollectionEmpty();
    }

    public boolean getIsEquipmentCollectionEmpty() {
        return this.isEquipmentCollectionEmpty;
    }

    private void setIsEquipmentCollectionEmpty() {
        LicenseType selected = this.getSelected();
        if (selected != null) {
            LicenseTypeFacade ejbFacade = (LicenseTypeFacade) this.getFacade();
            this.isEquipmentCollectionEmpty = ejbFacade.isEquipmentCollectionEmpty(selected);
        } else {
            this.isEquipmentCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Equipment entities that
     * are retrieved from LicenseType and returns the navigation outcome.
     *
     * @return navigation outcome for Equipment page
     */
    public String navigateEquipmentCollection() {
        LicenseType selected = this.getSelected();
        if (selected != null) {
            LicenseTypeFacade ejbFacade = (LicenseTypeFacade) this.getFacade();
            Collection<Equipment> selectedEquipmentCollection = ejbFacade.findEquipmentCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Equipment_items", selectedEquipmentCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/equipment/index";
    }

}
