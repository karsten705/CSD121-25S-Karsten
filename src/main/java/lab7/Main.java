package lab7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Get all recipes that can be made in 15 minutes or less
     * @param dataService a service that provides access to recipe data
     * @return a list of quick recipes
     */
    public static List<Recipe> getQuickRecipes(DataService dataService) {
        try {
            var recipes = dataService.getRecipes();
            return recipes.stream().filter( r -> r.totalTime() <= 15 ).toList();
        } catch(Exception e ) {
            logger.error("Error while getting quick recipes: " + e.getMessage());
            logger.debug("Stack trace: " + Arrays.toString(e.getStackTrace()));
            return List.of();
        }
    }

    // TODO: implement the searchRecipes method
    public static List<Recipe> searchRecipes(String searchTerm, DataService dataService) {
        try {
            var recipes = dataService.getRecipes(); // fetch all recipes and store it in a variable
            String lowerSearch = searchTerm.toLowerCase(); // convert to lower-case
            return recipes.stream().filter(recipe -> recipe.name().toLowerCase().contains(lowerSearch) ||
                    recipe.description().toLowerCase().contains(lowerSearch)).toList();
        }catch(Exception e ) {
            logger.error("Error while searching recipes: " + e.getMessage());
            logger.debug("Stack trace: " + Arrays.toString(e.getStackTrace()));
            return List.of();
        }
    }

    public static void main(String[] args) {
        // Here, we INJECT a concrete implementation of the DataService interface
        // that allows us to get data from an SQLite database
        var quickRecipes = getQuickRecipes(new SqliteDataService());
        System.out.println("Quick Recipes:");
        quickRecipes.forEach(System.out::println);

        // TODO: use your searchRecipes method with a SqliteDataService object
        List<Recipe> chickenRecipes = searchRecipes("cHickEn", new SqliteDataService());
        System.out.println("Recipes including the keyword chicken:\n");
        chickenRecipes.forEach(System.out::println);
    }
}
