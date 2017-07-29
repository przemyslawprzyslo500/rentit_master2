package pl.turek.liceum.rentit.controller;

import pl.turek.liceum.rentit.model.Equipment;
import pl.turek.liceum.rentit.model.Reserv;
import java.util.Collection;
import pl.turek.liceum.rentit.facade.EquipmentFacade;
import pl.turek.liceum.rentit.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "equipmentController")
@ViewScoped
public class EquipmentController extends AbstractController<Equipment> {

    @Inject
    private LicenseTypeController licenseTypeIdController;
    @Inject
    private UsePlaceController usePlaceIdController;
    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isReservCollectionEmpty;

    public EquipmentController() {
        // Inform the Abstract parent controller of the concrete Equipment Entity
        super(Equipment.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        licenseTypeIdController.setSelected(null);
        usePlaceIdController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsReservCollectionEmpty();
    }

    /**
     * Sets the "selected" attribute of the LicenseType controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareLicenseTypeId(ActionEvent event) {
        Equipment selected = this.getSelected();
        if (selected != null && licenseTypeIdController.getSelected() == null) {
            licenseTypeIdController.setSelected(selected.getLicenseTypeId());
        }
    }

    /**
     * Sets the "selected" attribute of the UsePlace controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareUsePlaceId(ActionEvent event) {
        Equipment selected = this.getSelected();
        if (selected != null && usePlaceIdController.getSelected() == null) {
            usePlaceIdController.setSelected(selected.getUsePlaceId());
        }
    }

    public boolean getIsReservCollectionEmpty() {
        return this.isReservCollectionEmpty;
    }

    private void setIsReservCollectionEmpty() {
        Equipment selected = this.getSelected();
        if (selected != null) {
            EquipmentFacade ejbFacade = (EquipmentFacade) this.getFacade();
            this.isReservCollectionEmpty = ejbFacade.isReservCollectionEmpty(selected);
        } else {
            this.isReservCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Reserv entities that are
     * retrieved from Equipment and returns the navigation outcome.
     *
     * @return navigation outcome for Reserv page
     */
    public String navigateReservCollection() {
        Equipment selected = this.getSelected();
        if (selected != null) {
            EquipmentFacade ejbFacade = (EquipmentFacade) this.getFacade();
            Collection<Reserv> selectedReservCollection = ejbFacade.findReservCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Reserv_items", selectedReservCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/reserv/index";
    }

}
