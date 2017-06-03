package CaloriesTracker;

import CaloriesTracker.DataModel.Food;
import CaloriesTracker.DataModel.FoodDiary;
import CaloriesTracker.DataModel.Workout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    private ObservableList<Food> foods;

    private ObservableList <Workout> workouts;
    private static Controller instance = new Controller();
    private ContextMenu foodContextMenu;

    public static Controller getInstance(){
        return instance;
    }

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private ListView<Food> foodConsumed;

    @FXML
    private TextArea foodDetailsTextArea;

    @FXML
    private Label totalCal;

    @FXML
    public void showNewItemDialog(){
        Dialog<ButtonType> newFoodDialog = new Dialog<>();
        newFoodDialog.initOwner(mainBorderPane.getScene().getWindow());
        newFoodDialog.setTitle("Add New Item");
        newFoodDialog.setHeaderText("Input name of food, quantity and calories per serving ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("NewFoodDialog.fxml"));
        try {
            newFoodDialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }
        newFoodDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        newFoodDialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);


        Optional<ButtonType> result = newFoodDialog.showAndWait();
        if (result.isPresent() && result.get()==ButtonType.OK){
            NewFoodDialogController controller = fxmlLoader.getController();
            controller.processResults();
            foodConsumed.getSelectionModel().selectLast();
            calculateCalSum();
        }

    }

//    public ListView<Food> getFoodConsumed() {
//        return foodConsumed;
//    }

    public ObservableList<Food> getFoods() {
        return foods;
    }


    public void initialize(){
//        Food food1 = new Food("Apple" , 1,100);
//        Food food2= new Food ("Chicken Breast", 2,100);
//        Food food3 = new Food("Apple" , 2,100);
//        foods = new HashSet<>();
//
//        foods.add(food1);
//        foods.add(food2);
//        foods.add(food3);
//
//        FoodDiary.getInstance().setFoodItems(foods);
//        FoodRecipe.getInstance().setFoodRecipeItems(foods);
        foodConsumed.setItems(FoodDiary.getInstance().getFoodItems());

        foodContextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Food food = foodConsumed.getSelectionModel().getSelectedItem();
                deleteItem(food);
            }
        });
        foodContextMenu.getItems().addAll(deleteMenuItem);
        foodConsumed.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        foodConsumed.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Food>() {
            @Override
            public void changed(ObservableValue<? extends Food> observable, Food oldValue, Food newValue) {
                if (newValue!= null){
                    Food item = foodConsumed.getSelectionModel().getSelectedItem();
                    foodDetailsTextArea.setText(item.getDescription());
                }
            }

        });
        foodConsumed.getSelectionModel().selectFirst();
        calculateCalSum();

        foodConsumed.setCellFactory(new Callback<ListView<Food>, ListCell<Food>>() {
            @Override
            public ListCell<Food> call(ListView<Food> param) {
                ListCell<Food> cell = new ListCell<Food>(){
                    @Override
                    protected void updateItem(Food item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty){
                            setText(null);
                        }else{
                            setText(item.getName());
                        }
                    }
                };
                cell.emptyProperty().addListener(
                        (obs,wasEmpty,isNowEmpty) ->{
                            if(isNowEmpty){
                                cell.setContextMenu(null);
                            } else{
                                cell.setContextMenu(foodContextMenu);
                            }
                        }
                );
                return cell;
            }
        });

    }

    public void deleteItem(Food food){
        FoodDiary.getInstance().getFoodItems().remove(food);
        calculateCalSum();
    }

    public void calculateCalSum(){
        double sum = 0;
        if(FoodDiary.getInstance().getFoodItems()!= null){
            for (int i = 0;i < FoodDiary.getInstance().getFoodItems().size();i++) {
                double CalConsumed;
                CalConsumed = FoodDiary.getInstance().getFoodItems().get(i).getTotalcal();
                sum += CalConsumed;
                totalCal.setText(Double.toString(sum) + " Calories consumed today");
            }
        }
    }
}
