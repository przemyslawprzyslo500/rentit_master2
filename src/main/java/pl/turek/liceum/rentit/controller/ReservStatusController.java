package pl.turek.liceum.rentit.controller;

import pl.turek.liceum.rentit.model.ReservStatus;
import pl.turek.liceum.rentit.model.Reserv;
import java.util.Collection;
import pl.turek.liceum.rentit.facade.ReservStatusFacade;
import pl.turek.liceum.rentit.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "reservStatusController")
@ViewScoped
public class ReservStatusController extends AbstractController<ReservStatus> {

    @Inject
    private MobilePageController mobilePageController;

    // Flags to indicate if child collections are empty
    private boolean isReservCollectionEmpty;

    public ReservStatusController() {
        // Inform the Abstract parent controller of the concrete ReservStatus Entity
        super(ReservStatus.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsReservCollectionEmpty();
    }

    public boolean getIsReservCollectionEmpty() {
        return this.isReservCollectionEmpty;
    }

    private void setIsReservCollectionEmpty() {
        ReservStatus selected = this.getSelected();
        if (selected != null) {
            ReservStatusFacade ejbFacade = (ReservStatusFacade) this.getFacade();
            this.isReservCollectionEmpty = ejbFacade.isReservCollectionEmpty(selected);
        } else {
            this.isReservCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Reserv entities that are
     * retrieved from ReservStatus and returns the navigation outcome.
     *
     * @return navigation outcome for Reserv page
     */
    public String navigateReservCollection() {
        ReservStatus selected = this.getSelected();
        if (selected != null) {
            ReservStatusFacade ejbFacade = (ReservStatusFacade) this.getFacade();
            Collection<Reserv> selectedReservCollection = ejbFacade.findReservCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Reserv_items", selectedReservCollection);
        }
        return this.mobilePageController.getMobilePagesPrefix() + "/app/reserv/index";
    }

}
