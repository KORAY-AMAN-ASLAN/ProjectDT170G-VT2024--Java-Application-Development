package se.miun.dt170g.projektdt170g.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.API.LunchAPI;
import se.miun.dt170g.projektdt170g.models.LunchMenuEntity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Named("menueBean")
@RequestScoped
public class MenueBean implements Serializable {

    @Inject
    private LunchAPI lunchAPI;
   private  LunchMenuEntity lunchMenuEntity = new LunchMenuEntity();
    private List<LunchMenuEntity> weeklyMenus = new ArrayList<>();
    public List<LunchMenuEntity> getWeeklyMenus() {
        return weeklyMenus;
    }


    @PostConstruct
    public void init() {
        try {
            fetchWeeklyMenus(); // Use this in production
           //this.weeklyMenus = createMockData(); // Use this for testing
        } catch (Exception e) {
            e.printStackTrace(); // Or use a logger to log the exception
        }
    }

    private void fetchWeeklyMenus() {
        this.weeklyMenus = lunchAPI.getLunch(true, false,false);

    }



// Inom MenueBean-klassen

    public LunchMenuEntity getTodaysLunch() {
        LocalDate today = LocalDate.now();
        for (LunchMenuEntity menu : weeklyMenus) {
            if (menu.getDate() != null && menu.getDate().isEqual(today)) {
                return menu;
            }
        }
        return null; // Inget hittades för dagens datum, hantera detta fall lämpligt
    }



    public String getName() {
        return lunchMenuEntity.getName() ;
    }

    public void setName(String name) {
        lunchMenuEntity.setName(name);
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
    public String getDayName() {
        return lunchMenuEntity.getDayName();
    }

    public void setDate(LocalDate date) {
        lunchMenuEntity.setDate(date);
    }

    public int getPrice() {
        return lunchMenuEntity.getPrice();
    }

    public void setPrice(int price) {
        lunchMenuEntity.setPrice(price);
    }











}
