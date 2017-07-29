package pl.turek.liceum.rentit.controller;

import pl.turek.liceum.rentit.model.UsePlace;
import pl.turek.liceum.rentit.model.Equipment;
import java.util.Collection;
import pl.turek.liceum.rentit.facade.UsePlaceFacade;
import pl.turek.liceum.rentit.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "usePlaceController")
@ViewScoped
public class UsePlaceController extends AbstractController<UsePlace> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isEquipmentCollectionEmpty;

    public UsePlaceController() {
        // Inform the Abstract parent controller of the concrete UsePlace Entity
        super(UsePlace.class);
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
        UsePlace selected = this.getSelected();
        if (selected != null) {
            UsePlaceFacade ejbFacade = (UsePlaceFacade) this.getFacade();
            this.isEquipmentCollectionEmpty = ejbFacade.isEquipmentCollectionEmpty(selected);
        } else {
            this.isEquipmentCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Equipment entities that
     * are retrieved from UsePlace and returns the navigation outcome.
     *
     * @return navigation outcome for Equipment page
     */
    public String navigateEquipmentCollection() {
        UsePlace selected = this.getSelected();
        if (selected != null) {
            UsePlaceFacade ejbFacade = (UsePlaceFacade) this.getFacade();
            Collection<Equipment> selectedEquipmentCollection = ejbFacade.findEquipmentCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Equipment_items", selectedEquipmentCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/equipment/index";
    }

}
