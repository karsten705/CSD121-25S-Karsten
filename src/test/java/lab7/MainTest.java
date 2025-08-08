package lab7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    // creating some recipes....
    private final Recipe chickenSoup = new Recipe(1, "Chicken Soup", "Warm and tasty", "",
            2, 10, 10, 20);
    private final Recipe tomatoSoup = new Recipe(2, "Tomato Soup", "Simple and fresh", "",
            1, 5, 5, 10);
    private final Recipe beefStew = new Recipe(3, "Beef Stew", "Includes tender chicken inside",
            "", 4, 15, 30, 45);


    @Test
    void testGetQuickRecipesReturnsEmptyListIfNoData() {
        var recipes = Main.getQuickRecipes(List::of);
        assertEquals(0, recipes.size());
    }

    @Test
    void testGetQuickRecipesReturnsEmptyListIfNoQuickRecipes() {
        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 16),
                new Recipe(1, "", "", "", 4, 10, 10, 20),
                new Recipe(2, "", "", "", 4, 10, 10, 200)
        ));
        assertEquals(0, recipes.size());
    }

    @Test
    void testGetQuickRecipesReturnsAllRecipesIfAllQuick() {

        var recipes = Main.getQuickRecipes(() -> List.of(
                new Recipe(0, "", "", "", 4, 10, 10, 15),
                new Recipe(1, "", "", "", 4, 10, 10, 1),
                new Recipe(2, "", "", "", 4, 10, 10, 10)
        ));

        assertEquals(3, recipes.size());
    }

    @Test
    void testGetQuickRecipesWorksOnTypicalData() {

        var recipes = Main.getQuickRecipes(() -> List.of(
                        new Recipe(0, "", "", "", 4, 10, 10, 10),
                        new Recipe(1, "", "", "", 4, 10, 10, 15),
                        new Recipe(2, "", "", "", 4, 10, 10, 16),
                        new Recipe(3, "", "", "", 4, 10, 10, 20),
                        new Recipe(4, "", "", "", 4, 10, 10, 2343)
        ));

        assertEquals(2, recipes.size());

        // Verify that the two recipes we expected are in fact in the list
        assertEquals(0, recipes.get(0).id());
        assertEquals(1, recipes.get(1).id());
    }

    // TODO: test the searchRecipes method
    @Test
    public void testSearchByName() {
        DataService mockService = () -> List.of(chickenSoup, tomatoSoup);
        var results = Main.searchRecipes("chicken", mockService);
        assertEquals(1, results.size());
        assertEquals("Chicken Soup", results.getFirst().name());
    }

    @Test
    public void testSearchByDescription() {
        DataService mockService = () -> List.of(beefStew, tomatoSoup);
        var results = Main.searchRecipes("chicken", mockService);
        assertEquals(1, results.size());
        assertEquals("Beef Stew", results.getFirst().name());
    }

    @Test
    public void testSearchByCaseInsensitivity() {
        DataService mockService = () -> List.of(chickenSoup);
        var results = Main.searchRecipes("cHIcKEn", mockService);
        assertEquals(1, results.size());
    }

    @Test
    public void testSearchWithNoResults() {
        DataService mockService = () -> List.of(tomatoSoup);
        var results = Main.searchRecipes("banana", mockService);
        assertTrue(results.isEmpty());
    }

    @Test
    public void testSearchExceptionHandling() {
        DataService mockService = () -> { throw new RuntimeException("Database error (Test successful)"); };
        var results = Main.searchRecipes("chicken", mockService);
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }
}