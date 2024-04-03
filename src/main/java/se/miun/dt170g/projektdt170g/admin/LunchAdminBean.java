package se.miun.dt170g.projektdt170g.admin;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.ViewHandler;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.API.LunchAPI;
import se.miun.dt170g.projektdt170g.models.LunchMenuEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import java.net.URI;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TemporalType;


@Named
@ViewScoped
public class LunchAdminBean implements Serializable {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    @Inject
    private LunchAPI lunchAPI;

    private LunchMenuEntity lunchMenuEntity = new LunchMenuEntity();
    private String action; // Define the action property

    private String message = "";
    private int lunchIdToDelete;
    // Id of the selected lunch for update
    private int selectedLunchId;




    // Temporary here for update lunch




    public String getAction() {
        return action;
    }

    public void setAction(String action) {

        if("add".equals(action) || "update".equals(action) || "delete".equals(action)) {
            lunchMenuEntity = new LunchMenuEntity();
            setMessage("");
            if ("add".equals(action)){
                setPrice(99);
            }
        }
         this.action = action;
    }

    public List<LunchMenuEntity> getLast14Lunches(){
         return lunchAPI.getPreviousLunches();
    }



    public void addLunch(){
        // call api post lunch
        setMessage("Tillagd");
        setAction("none");
        Response response = lunchAPI.addLunch(lunchMenuEntity);
    }

    public void deleteLunch() {
        setMessage("Borttagen");
        setAction("none");
        Response response = lunchAPI.deleteLunch(lunchMenuEntity.getLunchId());
    }

    public void updateLunch() {
        setMessage("Uppdaterad");
        setAction("none");
        Response response = lunchAPI.updateLunch(lunchMenuEntity.getLunchId(), this.lunchMenuEntity);
    }

    public String getLunchName() {
        return lunchMenuEntity.getName();
    }

    public void setLunchName(String lunchName) {
        lunchMenuEntity.setName(lunchName);
    }
    public LocalDate getLunchDate() {
        return lunchMenuEntity.getDate();
    }

    public void setLunchDate(LocalDate lunchDate) {
        lunchMenuEntity.setDate(lunchDate);
    }

    public String getLunchDescription() {
        return lunchMenuEntity.getDescription();
    }

    public void setLunchDescription(String lunchDescription) {
        lunchMenuEntity.setDescription(lunchDescription);
    }

    public int getLunchPrice() {
        return lunchMenuEntity.getPrice();
    }

    public void setLunchPrice(int lunchPrice) {
        lunchMenuEntity.setPrice(lunchPrice);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLunchIdToDelete() {
        return lunchIdToDelete;
    }

    public void setLunchIdToDelete(int lunchIdToDelete) {
        this.lunchIdToDelete = lunchIdToDelete;
    }

    public LunchMenuEntity getLunchMenuEntity() {
        return lunchMenuEntity;
    }

    public void setLunchMenuEntity(LunchMenuEntity lunchMenuEntity) {
        this.lunchMenuEntity = lunchMenuEntity;
    }








    // Temporary here
    public int getSelectedLunchId() {
        return selectedLunchId;
    }

    public void setSelectedLunchId(int selectedLunchId) {
        this.selectedLunchId = selectedLunchId;
    }

    public String getName() {
        return lunchMenuEntity.getName();
    }

    public void setName(String name) {
        this.lunchMenuEntity.setName(name);
    }
    public String getDescription() {
        return lunchMenuEntity.getDescription();
    }

    public void setDescription(String description) {
        lunchMenuEntity.setDescription(description);
    }

    public LocalDate getDate() {
        return lunchMenuEntity.getDate();
    }

    public void setDate(LocalDate date) {
        lunchMenuEntity.setDate(date);
    }

    public int getPrice() {
        return lunchMenuEntity.getPrice();
    }

    public void setPrice(int price) {
        this.lunchMenuEntity.setPrice(price);
    }


    // Method to get all lunches from the database for the dropdown
    public List<LunchMenuEntity> getAllLunches() {
        return lunchAPI.getLunch(false,false,true);
    }


    // Loads the details of the selected lunch so the can be shown in the update form
    public void loadSelectedLunch() {
        this.lunchMenuEntity = lunchAPI.getLunch(selectedLunchId);
        System.out.println("hej");

    }





}