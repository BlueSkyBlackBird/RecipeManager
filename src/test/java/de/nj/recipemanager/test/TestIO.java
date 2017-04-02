/**
 *
 */
package de.nj.recipemanager.test;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import de.nj.recipemanager.model.DefaultRecipeBook;
import de.nj.recipemanager.model.interfaces.RecipeBook;
import de.nj.recipemanager.model.recipe.IngredientInformation;
import de.nj.recipemanager.model.recipe.Recipe;
import de.nj.recipemanager.presenter.FileIOHandler;
import de.nj.recipemanager.test.dummy.DummyPresenterModelCallback;
import junit.framework.TestCase;

/**
 * @author Nico
 * @date 26.03.2017
 *
 */
public class TestIO extends TestCase
{
    @BeforeClass
    public static void setup()
    {}

    @AfterClass
    public static void teardown()
    {

        try
        {
            Preferences pref = FileIOHandler.loadAndCreateUserPreferenceIfNecesarry("de.nj.recipemanager.TestPref");
            pref.removeNode();
        }
        catch (BackingStoreException e)
        {
            fail("Cannot clean up preferences: " + e.getMessage());
        }

        File f = new File("MyJSONTestFile");
        f.delete();
    }

    public TestIO(String name)
    {
        super(name);
    }

    @Test
    public void testPreferenceReadAndWrite()
    {
        Properties defaultValues = new Properties();
        defaultValues.put("test.test1", "1");
        defaultValues.put("test.test2", "A");
        defaultValues.put("test.test3", "0x11");
        defaultValues.put("test.test4", "0.1");

        try
        {
            Preferences pref = FileIOHandler.loadAndCreateUserPreferenceIfNecesarry("de.nj.recipemanager.TestPref");

            defaultValues.forEach((k, v) -> pref.put(String.valueOf(k), String.valueOf(v)));

            try
            {
                pref.flush();
            }
            catch (Exception e)
            {
                fail("Failed to save preferences " + e.getMessage());
            }

            assertEquals("Mismatching number of keys after loading perference", pref.keys().length, defaultValues.keySet().size());

            defaultValues.forEach((k, v) -> {
                assertEquals("Detected missing key-value pair in preference!", v, pref.get(k.toString(), "NOT_FOUND"));
            });

        }
        catch (Exception e)
        {
            fail("Failed to load preference:\n" + e.getMessage());
        }
    }

    @Test
    public void testPropertiesRead()
    {
        Properties loadedProperties = null;

        try
        {
            loadedProperties = FileIOHandler.loadPropertiesInsideJar("config.properties");
        }
        catch (Exception e)
        {
            fail("Could not load file: " + e.getLocalizedMessage());
        }

        assertNotNull("Could not load the config properties!", loadedProperties);
        assertTrue("Loaded config is empty!", !loadedProperties.isEmpty());
    }

    @Test
    public void testRecipeSaveAndLoad()
    {
        RecipeBook originalRecipeBook = new DefaultRecipeBook(new DummyPresenterModelCallback());

        Recipe r = new Recipe("Test 1");
        r.addIngredient(new IngredientInformation("Basilikum", 5, "Gramm"));
        r.addIngredient(new IngredientInformation("Ei", 2, "Stück"));
        r.addIngredient(new IngredientInformation("Mehl", 4, "Esslöffel"));
        r.setCookingDescription("Du musst den Nippel durch die Lasche ziehen und dann mit der Kurbel ganz nach oben drehen!");
        r.getTags().add("Lecker!");

        Recipe r2 = new Recipe("Test 2");
        r2.addIngredient(new IngredientInformation("Zucker", 500, "Gramm"));
        r2.addIngredient(new IngredientInformation("Zimt", 3, "Gramm"));
        r2.addIngredient(new IngredientInformation("Mehl", 4, "Essl�ffel"));
        r2.setCookingDescription("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor "
                + "invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. "
                + "Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, "
                + "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. "
                + "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.");
        r2.getTags().add("Meh!");

        originalRecipeBook.addRecipe(r);
        originalRecipeBook.addRecipe(r2);

        RecipeBook loadedRecipeBook = new DefaultRecipeBook(new DummyPresenterModelCallback());

        try
        {
            FileIOHandler.saveRecipeBookAsJSON(originalRecipeBook, "MyJSONTestFile");
        }
        catch (IOException e)
        {
            fail("Failed to save datamodel and ingredients!" + e.getLocalizedMessage());
        }

        try
        {
            FileIOHandler.readRecipeBookFromJSON(loadedRecipeBook, "MyJSONTestFile");
        }
        catch (IOException e)
        {
            fail("Failed to load datamodel and ingredients!" + e.getLocalizedMessage());
        }

        assertEquals("Saved and loaded RecipeBooks are not equal", originalRecipeBook, loadedRecipeBook);
    }
}
