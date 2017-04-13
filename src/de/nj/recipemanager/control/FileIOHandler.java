/**
 * 
 */
package de.nj.recipemanager.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;
import de.nj.recipemanager.model.DataModel;
import de.nj.recipemanager.model.GeneralModel;
import de.nj.recipemanager.model.recipe.Ingredient;
import de.nj.recipemanager.model.recipe.IngredientInformation;
import de.nj.recipemanager.model.recipe.Recipe;

/**
 * @author Nico
 * @date 31 Jan 2014
 * 
 */
public class FileIOHandler
{
	
	public Properties loadProperties(String path)
	{
		try (BufferedReader rd = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("resources/" + path))))
		{
			Properties properties = new Properties();
			properties.load(rd);
			return properties;
		}
		catch (IOException e)
		{
			throw new NullPointerException("Invalid path! File \"" + path +"\" does not exist!");
		}
	}
	
	public void saveData(GeneralModel model, String file) throws Exception 
	{
		DataModel dModel = model.getDataModel();
		File destination = new File(file);
		
		String encoding = 			dModel.getProperty("general.encoding");
		String header = 			dModel.getProperty("general.version")+ ":" + encoding;
		String ingredientSection = 	ingredientsToString(dModel);
		String recipeSection = 		recipesToString(dModel);
		
		String complete = 
				"%HEADER::" + 				System.lineSeparator()
				+ header + 					System.lineSeparator()
				+ "%INGREDIENTS::" + 		System.lineSeparator()
				+ ingredientSection + 		System.lineSeparator()
				+ "%RECIPES::" + 			System.lineSeparator()
				+ recipeSection + 			System.lineSeparator();
		
		saveStringToFile(complete, destination, encoding);
	}
	
	
	private String ingredientsToString(DataModel model)
	{
		StringBuffer result = new StringBuffer();
		
		for (Recipe recipe : model.getRecipes())
		{
			for (IngredientInformation ingredientInfo : recipe.getIngredientInformation())
			{
				result.append(ingredientToString(ingredientInfo.getIngredient()));
			}
		}
		
		return result.toString();
	}
	
	private String ingredientToString(Ingredient ingredient)
	{
		return ">ING=" + ingredient.getUid() + ":" + ingredient.getIngredientName() + System.lineSeparator();
	}

	private String recipesToString(DataModel model)
	{
		StringBuffer result = new StringBuffer();
		
		for(Recipe recipe : model.getRecipes())
		{
			result.append(recipeToString(recipe) + System.lineSeparator() + "#" + System.lineSeparator());
		}
		
		return result.toString();
	}
	
	private String recipeToString(Recipe recipe)
	{		
		String name 			= recipe.getName();
		String tags 			= recipeToTags(recipe);
		String ingredientInfo 	= recipeToIngredientInfo(recipe);
		String steps 			= recipeToSteps(recipe);
		String unitOfQuantity 	= recipeToQuantity(recipe);
				
		String result = 	
					">NAME=" + name + System.lineSeparator() +
					">TAGS=" + tags + System.lineSeparator() +
					">QUANTITY=" + unitOfQuantity + System.lineSeparator() + 
					">INGINF=" + ingredientInfo + System.lineSeparator() +
					">STEPS=" + steps + System.lineSeparator();
		
		return result;
	}
	
	private String recipeToQuantity(Recipe recipe)
	{
		StringBuffer result = new StringBuffer();
		
		for (IngredientInformation info : recipe.getIngredientInformation())
		{
			result.append(
					info.getUnitOfQuantity().getUid() + ":" +
					info.getUnitOfQuantity().getName() + ":" +
					info.getUnitOfQuantity().getType() + "|");
			
		}
		
		return result.toString();
	}
	
	private String recipeToSteps(Recipe recipe)
	{
		return recipe.getSteps().stream()
				.map(s -> s.getDescription() + ":" + s.getDurationInMinutes())
				.reduce((t1,t2) -> t1 + "|" + t2).get();
	}
	
	private String recipeToTags(Recipe recipe)
	{
		return recipe.getTags().stream()
				.map(t -> t.getTagName())
				.reduce((t1,t2) -> t1 + "|" + t2).get();
	}
	
	private String recipeToIngredientInfo(Recipe recipe)
	{
		StringBuffer result = new StringBuffer();
		
		for (IngredientInformation info : recipe.getIngredientInformation())
		{
			double amount = info.getAmount();
			String ingredientUID = info.getIngredient().getUid();
			String unitUID = info.getUnitOfQuantity().getUid();
			
			result.append(ingredientUID + ":" + unitUID + ":" + amount + "|");
		}
		
		return result.toString();
	}
	
	private void saveStringToFile(String text, File file, String encoding) throws Exception
	{
		try (BufferedWriter wr = new BufferedWriter(
				new OutputStreamWriter(
						new FileOutputStream(file),encoding)))
		{
			wr.write(text + System.lineSeparator());
		}
		catch (Exception e)
		{
			System.out.println("PersistenceHandler#saveStringToFile: File could not be written!");
			throw e;
		}
	}

	public void loadData(DataModel model, String file) 
	{
		//TODO: 
		
	}
}
