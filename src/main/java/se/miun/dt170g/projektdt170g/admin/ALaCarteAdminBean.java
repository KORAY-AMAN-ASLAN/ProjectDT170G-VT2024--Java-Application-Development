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
import se.miun.dt170g.projektdt170g.API.ALaCarteAPI;
import se.miun.dt170g.projektdt170g.items.ALaCarteItem;

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
import se.miun.dt170g.projektdt170g.models.LunchMenuEntity;


@Named
@ViewScoped
public class ALaCarteAdminBean implements Serializable {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    @Inject
    private ALaCarteAPI alacarteAPI;

    private ALaCarteItem aLaCarteItem = new ALaCarteItem();
    private String action; // Define the action property

    private String message = "";
    private int aLaCarteIdToDelete;
    // Id of the selected a la carte for update
    private int selectedALaCarteId;

    private String selectedALaCarteType;




    // Temporary here for update a la carte




    public String getAction() {
        return action;
    }

    public void setAction(String action) {

        if("add".equals(action) || "update".equals(action) || "delete".equals(action)) {
            aLaCarteItem = new ALaCarteItem();
            setMessage("");
        }


        this.action = action;
    }



    public void addALaCarte(){
        // call api post a la carte
        setMessage("Tillagd");
        setAction("none");
        alacarteAPI.createItem(aLaCarteItem);
    }

    public void deleteALaCarte() {
        setMessage("Borttagen");
        setAction("none");
        Response response = alacarteAPI.deleteItem(this.aLaCarteIdToDelete);
    }

    public void updateAlaCarte() {
        setMessage("Uppdaterad");
        setAction("none");
        Response response = alacarteAPI.updateItem(selectedALaCarteId,aLaCarteItem);
    }

    public String getALaCarteName() {
        return aLaCarteItem.getName();
    }

    public void setALaCarteName(String alacarteName) {
        aLaCarteItem.setName(alacarteName);
    }
    public String getALaCarteType() {
        return aLaCarteItem.getType();
    }

    public void setALaCarteType(String alacarteType) {
        aLaCarteItem.setType(alacarteType);
    }

    public String getALaCarteDescription() {
        return aLaCarteItem.getDescription();
    }

    public void setALaCarteDescription(String alacarteDescription) {
        aLaCarteItem.setDescription(alacarteDescription);
    }

    public int getALaCartePrice() {
        return aLaCarteItem.getPrice();
    }

    public void setALaCartePrice(int alacartePrice) {
        aLaCarteItem.setPrice(alacartePrice);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getaLaCarteIdToDelete() {
        return aLaCarteIdToDelete;
    }

    public void setaLaCarteIdToDelete(int aLaCarteIdToDelete) {
        this.aLaCarteIdToDelete = aLaCarteIdToDelete;
    }

    public ALaCarteItem getALaCarteItem() {
        return aLaCarteItem;
    }

    public void setALaCarteItem(ALaCarteItem aLaCarteItem) {
        this.aLaCarteItem = aLaCarteItem;
    }








    // Temporary here
    public int getSelectedALaCarteId() {
        return selectedALaCarteId;
    }

    public void setSelectedALaCarteId(int selectedALaCarteId) {
        this.selectedALaCarteId = selectedALaCarteId;
    }

    public String getName() {
        return aLaCarteItem.getName();
    }

    public void setName(String name) {
        this.aLaCarteItem.setName(name);
    }
    public String getDescription() {
        return aLaCarteItem.getDescription();
    }

    public void setDescription(String description) {
        aLaCarteItem.setDescription(description);
    }

    public String getType() {
        return aLaCarteItem.getType();
    }

    public void setType(String type) {
        aLaCarteItem.setType(type);
    }

    public int getPrice() {
        return aLaCarteItem.getPrice();
    }

    public void setPrice(int price) {
        this.aLaCarteItem.setPrice(price);
    }


    // Method to get all alacartes from the database for the dropdown
    public List<ALaCarteItem> getAllALaCartes() {
        return alacarteAPI.getItemsByType(null);
    }


    // Loads the details of the selected a la carte so the can be shown in the update form
    public void loadSelectedALaCarte() {
        this.aLaCarteItem = alacarteAPI.getItemById(selectedALaCarteId);
        System.out.println("hej");

    }

    public String getSelectedALaCarteType() {
        return selectedALaCarteType;
    }

    public void setSelectedALaCarteType(String selectedALaCarteType) {
        this.selectedALaCarteType = selectedALaCarteType;
    }




}