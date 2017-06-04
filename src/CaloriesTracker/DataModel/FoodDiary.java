package CaloriesTracker.DataModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * Created by lamkeong on 6/2/2017.
 */
public class FoodDiary {
    private static FoodDiary instance = new FoodDiary();
    private static String fileName = "FoodDiary.txt";

    private ObservableList<Food> foodItems;

    public static FoodDiary getInstance(){
        return instance;
    }

    public ObservableList<Food> getFoodItems(){
        return foodItems;
    }

    private FoodDiary (){

    };

    public void storeFoodItems() throws IOException{
        Path path = Paths.get(fileName);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try{
            Iterator<Food> iter = foodItems.iterator();
            while (iter.hasNext()){
                Food food = iter.next();
                bw.write(String.format("%s\t%f\t%f\t%f\n",
                        food.getName(),
                        food.getQuantity(),
                        food.getCal(),
                        food.getTotalcal()));
            }
        } finally {
            if (bw != null){
                bw.close();
            }
        }
    }

    public void loadFoodItems() throws IOException{
        foodItems = FXCollections.observableArrayList();
        Path path = Paths.get(fileName);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try{
            while((input = br.readLine()) != null){
                String[] itemPieces = input.split("\t");
                String name = itemPieces[0];
                double quantity = Double.parseDouble(itemPieces[1]);
                double cal = Double.parseDouble(itemPieces[2]);
                double totalcal = Double.parseDouble(itemPieces[3]);

                Food loadFood = new Food(name,quantity,cal);
                foodItems.add(loadFood);
            }
        }finally {
            if(br!= null){
                br.close();
            }
        }
    }

    public void addFoodItems(Food food){
        foodItems.add(food);
    }
}
