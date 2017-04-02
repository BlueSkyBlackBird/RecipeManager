/**
 * 
 */
package de.nj.recipemanager.presenter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import java.util.prefs.Preferences;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.nj.recipemanager.model.interfaces.RecipeBook;
import de.nj.recipemanager.model.recipe.Recipe;


/**
 * @author Nico
 * @date 31 Jan 2014
 * 
 */
public class FileIOHandler
{
	public static final Logger LOGGER = Logger.getLogger(FileIOHandler.class);
	
	public static Properties loadPropertiesInsideJar(String relativePath) throws IOException
	{
		try (BufferedReader rd = new BufferedReader(new InputStreamReader(FileIOHandler.class.getClassLoader().getResourceAsStream(relativePath))))
		{
			Properties properties = new Properties();
			properties.load(rd);
			return properties;
		}
		catch (IOException e)
		{
			// TODO:
			throw e;
		}
	}

	public static Preferences loadAndCreateUserPreferenceIfNecesarry(String relativePath)
	{
		Preferences pref = Preferences.systemRoot().node(relativePath);
		
		return pref;
	}

	public static void saveRecipeBookAsJSON(RecipeBook model, String file) throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File(file), model.getRecipeListView());
	}
	
	public static void readRecipeBookFromJSON(RecipeBook model, String file) throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		List<Recipe> recipes = mapper.readValue(new File(file), new TypeReference<List<Recipe>>() {});
		
		recipes.forEach(model::addRecipe);
	}
}
