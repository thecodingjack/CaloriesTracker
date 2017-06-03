package CaloriesTracker;

import CaloriesTracker.DataModel.FoodDiary;
import CaloriesTracker.DataModel.FoodRecipe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
        primaryStage.setTitle("Calories Tracker");
        int w = 1080;
        primaryStage.setScene(new Scene(root, w, w*9/14));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        try{
            FoodDiary.getInstance().loadFoodItems();
            FoodRecipe.getInstance().loadFoodRecipeItems();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (NullPointerException e){
            System.out.println("No item found");
        }
    }

    @Override
    public void stop() throws Exception {
        try{
            FoodDiary.getInstance().storeFoodItems();
            FoodRecipe.getInstance().storeFoodRecipe();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (NullPointerException e){
            System.out.println("Tracker is empty");
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
