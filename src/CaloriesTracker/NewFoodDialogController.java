package CaloriesTracker;

import CaloriesTracker.DataModel.Food;
import CaloriesTracker.DataModel.FoodDiary;
import CaloriesTracker.DataModel.FoodRecipe;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


/**
 * Created by lamkeong on 6/2/2017.
 */
public class NewFoodDialogController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField calField;



    public void processResults(){
        String name = nameField.getText();
        double quantity = Double.parseDouble( quantityField.getText());
        double cal = Double.parseDouble(calField.getText());

        FoodDiary.getInstance().addFoodItems(new Food(name,quantity,cal));
        FoodRecipe.getInstance().addNewFoodRecipe(new Food(name,cal));

    }
}
