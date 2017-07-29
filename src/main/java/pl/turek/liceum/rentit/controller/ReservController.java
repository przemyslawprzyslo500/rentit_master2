package pl.turek.liceum.rentit.controller;

import javax.ejb.EJB;
import pl.turek.liceum.rentit.model.Reserv;
import pl.turek.liceum.rentit.facade.ReservFacade;
import pl.turek.liceum.rentit.controller.util.MobilePageController;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "reservController")
@ViewScoped
public class ReservController extends AbstractController<Reserv> {

//    @Inject
//    @EJB
    @Inject
    private AccountController accountIdController;
    @Inject
    private EquipmentController equipmentIdController;
    @Inject
    private ReservStatusController reservStatusIdController;
    @Inject
    private MobilePageController mobilePageController;

    public ReservController() {
        // Inform the Abstract parent controller of the concrete Reserv Entity
        super(Reserv.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        accountIdController.setSelected(null);
        equipmentIdController.setSelected(null);
        reservStatusIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Account controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAccountId(ActionEvent event) {
        Reserv selected = this.getSelected();
        if (selected != null && accountIdController.getSelected() == null) {
            accountIdController.setSelected(selected.getAccountId());
        }
    }

    /**
     * Sets the "selected" attribute of the Equipment controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareEquipmentId(ActionEvent event) {
        Reserv selected = this.getSelected();
        if (selected != null && equipmentIdController.getSelected() == null) {
            equipmentIdController.setSelected(selected.getEquipmentId());
        }
    }

    /**
     * Sets the "selected" attribute of the ReservStatus controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareReservStatusId(ActionEvent event) {
        Reserv selected = this.getSelected();
        if (selected != null && reservStatusIdController.getSelected() == null) {
            reservStatusIdController.setSelected(selected.getReservStatusId());
        }
    }

}
