package se.miun.dt170g.projektdt170g.admin;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.API.DrinkAPI;

import java.io.Serializable;
import java.util.List;

import se.miun.dt170g.projektdt170g.items.Drink;


@Named
    @ViewScoped
    public class DrinkAdminBean implements Serializable {



        @Inject
        private DrinkAPI drinkApi;

        private Drink drink = new Drink();
        private String action; // Define the action property

        private String message = "";
        private int drinkIdToDelete;
        private int selectedDrinkId;





        // Temporary here for update a la carte




        public String getAction() {
            return action;
        }

        public void setAction(String action) {

            if("add".equals(action) || "update".equals(action) || "delete".equals(action)) {
                drink = new Drink();
                setMessage("");
            }
            this.action = action;
        }



        public void addDrink(){
            // call api post a la carte
            setMessage("Tillagd");
            setAction("none");
            drinkApi.createDrink(drink);

        }

        public void deleteDrink() {
            setMessage("Borttagen");
            setAction("none");
            Response response = drinkApi.deleteDrink(this.drinkIdToDelete);
        }

        public void updateDrink() {
            setMessage("Uppdaterad");
            setAction("none");
            Response response = drinkApi.updateDrink(selectedDrinkId, drink);
        }

        public String getDrinkName() {
            return drink.getName();
        }

        public void setDrinkName(String drinkName) {
            drink.setName(drinkName);
        }


        public String getDrinkDescription() {
            return drink.getDescription();
        }

        public void setDrinkDescription(String drinkDescription) {
            drink.setDescription(drinkDescription);
        }

        public int getDrinkPrice() {
            return drink.getPrice();
        }

        public void setDrinkPrice(int drinkPrice) {
            drink.setPrice(drinkPrice);
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getDrinkIdToDelete() {
            return drinkIdToDelete;
        }

        public void setDrinkIdToDelete(int drinkIdToDelete) {
            this.drinkIdToDelete = drinkIdToDelete;
        }

        public Drink getDrink() {
            return drink;
        }

        public void setDrink(Drink drink) {
            this.drink = drink;
        }








        // Temporary here
        public int getSelectedDrinkId() {
            return selectedDrinkId;
        }

        public void setSelectedDrinkId(int selectedDrinkId) {
            this.selectedDrinkId = selectedDrinkId;
        }

        public String getName() {
            return drink.getName();
        }

        public void setName(String name) {
            this.drink.setName(name);
        }
        public String getDescription() {
            return drink.getDescription();
        }

        public void setDescription(String description) {
            drink.setDescription(description);
        }



        public int getPrice() {
            return drink.getPrice();
        }

        public void setPrice(int price) {
            this.drink.setPrice(price);
        }


        // Method to get all alacartes from the database for the dropdown
        public List<Drink> getAllDrinks() {
            return drinkApi.getAllDrinks();
        }


        // Loads the details of the selected a la carte so the can be shown in the update form
        public void loadSelectedDrink() {
            this.drink = drinkApi.getDrinkByID(selectedDrinkId);
            System.out.println("hej");

        }








}
