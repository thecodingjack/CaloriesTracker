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
public class FoodRecipe {
    private static FoodRecipe instance = new FoodRecipe();
    private static String filename = "FoodRecipe.txt";

    private ObservableList<Food> foodRecipeItems;

    public ObservableList<Food> getFoodRecipeItems(){
        return foodRecipeItems;
    }

    public static FoodRecipe getInstance(){
        return  instance;
    }

    private FoodRecipe(){

    }

//    public void setFoodRecipeItems(ObservableSet<Food> foods){
//        this.foodRecipeItems = foods;
//    }


    public void storeFoodRecipe() throws IOException{
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try {
            Iterator <Food> iter = foodRecipeItems.iterator();
            while(iter.hasNext()){
                    Food food = iter.next();
//                        if(foodRecipeItems.contains(food)){
//                            System.out.println("Already contain " + food.getName());
//                        }else {
                            bw.write(String.format("%s\t%f\n",
                                    food.getName(),
                                    food.getCal()));

//                        }
            }
        }finally {
            if(bw != null){
                bw.close();
            }
        }
    }

    public void loadFoodRecipeItems() throws IOException{
        foodRecipeItems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = Files.newBufferedReader(path);

        String input;

        try{
            while((input = br.readLine()) != null){
                String[] itemPieces = input.split("\t");
                String name = itemPieces[0];
                double cal = Double.parseDouble(itemPieces[1]);

                Food loadFood = new Food(name,cal);
                foodRecipeItems.add(loadFood);
            }
        }finally {
            if(br!= null){
                br.close();
            }
        }

    }

    public void addNewFoodRecipe (Food food){
            foodRecipeItems.add(food);
        System.out.println("Added " +food.getName());
    }


}
