/**
 * 
 */
package de.nj.recipemanager.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import de.nj.recipemanager.model.recipe.Recipe;
import de.nj.recipemanager.model.recipe.Tag;

/**
 * @author Nico
 * @date 14.05.2013
 * 
 */
public class DataModelImpl implements DataModel
{	
	private List<Recipe> recipeList;
	
	private Map<Tag, List<Recipe>> tagRecipeMap;
	
	private Properties config;
	
	
	/**
	 * This is the default constructor of this class.
	 */
	public DataModelImpl(Properties configuration)
	{
		recipeList = new ArrayList<>();
		tagRecipeMap = new HashMap<>();
		config = configuration;
	}
	
	/* (non-Javadoc)
	 * @see de.nj.recipemanager.model.DataModel#addRecipe(java.lang.String)
	 */
	@Override
	public void addRecipe(Recipe recipe)
	{
		recipeList.add(recipe);
		
		for (Tag t : recipe.getTags())
		{
			tagRecipeMap.putIfAbsent(t, new ArrayList<>());
			tagRecipeMap.get(t).add(recipe);
		}
	}
	
	/* (non-Javadoc)
	 * @see de.nj.recipemanager.model.DataModel#removeRecipe(java.lang.String)
	 */
	@Override
	public void removeRecipe(Recipe recipe)
	{
		recipeList.remove(recipe);
		recipe.getTags().forEach(tag -> tagRecipeMap.get(tag).remove(recipe));
	}

	/* (non-Javadoc)
	 * @see de.nj.recipemanager.model.DataModel#getProperty(java.lang.String)
	 */
	@Override
	public String getProperty(String key)
	{
		return config.getProperty(key);
	}

	/* (non-Javadoc)
	 * @see de.nj.recipemanager.model.DataModel#getRecipes()
	 */
	@Override
	public List<Recipe> getRecipes()
	{
		return recipeList;
	}
	
	
	
}
