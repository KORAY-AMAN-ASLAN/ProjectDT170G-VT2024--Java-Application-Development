package se.miun.dt170g.projektdt170g.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.API.ALaCarteAPI;
import se.miun.dt170g.projektdt170g.items.ALaCarteItem;
import se.miun.dt170g.projektdt170g.models.ALaCarteMenuEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("alaCarteBean")
@RequestScoped
public class AlaCartaBean implements Serializable {

    @Inject
    private ALaCarteAPI alaCarteAPI = new ALaCarteAPI(); // Antag att du kan injecta din API-klass direkt
    private ALaCarteMenuEntity aLaCarteMenuEntity;
    @PersistenceContext
    private EntityManager entityManager;

    private List<ALaCarteMenuEntity> alaCarteMenus;
    private List<ALaCarteItem> aLaCarteItems = new ArrayList<>();

    public ALaCarteAPI getAlaCarteAPI() {
        return alaCarteAPI;
    }

    public void setAlaCarteAPI(ALaCarteAPI alaCarteAPI) {
        this.alaCarteAPI = alaCarteAPI;
    }

    public List<ALaCarteItem> getaLaCarteMainDish() {
        return aLaCarteMainDish;
    }

    public void setaLaCarteMainDish(List<ALaCarteItem> aLaCarteMainDish) {
        this.aLaCarteMainDish = aLaCarteMainDish;
    }

    public List<ALaCarteItem> getaLaCarteStarter() {
        return aLaCarteStarter;
    }

    public void setaLaCarteStarter(List<ALaCarteItem> aLaCarteStarter) {
        this.aLaCarteStarter = aLaCarteStarter;
    }

    public List<ALaCarteItem> getaLaCarteDesert() {
        return aLaCarteDesert;
    }

    public void setaLaCarteDesert(List<ALaCarteItem> aLaCarteDesert) {
        this.aLaCarteDesert = aLaCarteDesert;
    }

    private List<ALaCarteItem> aLaCarteMainDish = new ArrayList<>();
    private List<ALaCarteItem> aLaCarteStarter= new ArrayList<>();
    private List<ALaCarteItem> aLaCarteDesert = new ArrayList<>();
    @PostConstruct
    public void init() {
        fetchAlaCarteMenus();
    }

    private void fetchAlaCarteMenus() {
        this.aLaCarteStarter =  alaCarteAPI.getItemsByType("Förrätt");
        this.aLaCarteMainDish =  alaCarteAPI.getItemsByType("Huvudrätt");
        this.aLaCarteDesert =  alaCarteAPI.getItemsByType("Efterrätt");
    }



    public List<ALaCarteMenuEntity> getAlaCarteMenus() {
        return alaCarteMenus;
    }






    public int getaLaCarteId() {
        return aLaCarteMenuEntity.getaLaCarteId();
    }

    public void setaLaCarteId(int aLaCarteId) {
        aLaCarteMenuEntity.setaLaCarteId(aLaCarteId);
    }

    public String getName() {
        return  aLaCarteMenuEntity.getName();
    }

    public void setName(String name) {
        aLaCarteMenuEntity.setName(name);
    }

    public String getDescription() {
        return aLaCarteMenuEntity.getDescription();
    }

    public void setDescription(String description) {
        aLaCarteMenuEntity.setDescription(description);
    }

    public String getType() {
        return aLaCarteMenuEntity.getType();
    }

    public void setType(String type) {
        aLaCarteMenuEntity.setType(type);
    }

    public int getPrice() {
        return aLaCarteMenuEntity.getPrice();
    }

    public void setPrice(int price) {aLaCarteMenuEntity.setPrice(price);
    }





}
