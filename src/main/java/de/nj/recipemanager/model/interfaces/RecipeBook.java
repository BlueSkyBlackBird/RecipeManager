/**
 * 
 */
package de.nj.recipemanager.model.interfaces;

import java.util.List;
import de.nj.recipemanager.model.recipe.Recipe;

/**
 * @author Nico
 * @date 14.05.2013
 * 
 */
public interface RecipeBook
{
	public void addRecipe(Recipe recipe);
	
	/**
	 * 
	 */
	public void removeRecipe(Recipe recipe);
	
	/**
	 * Returns a view on the list of recipes. The view is unmodifiable.
	 */
	public List<Recipe> getRecipeListView();

}
