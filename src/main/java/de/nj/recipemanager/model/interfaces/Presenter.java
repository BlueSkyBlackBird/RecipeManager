/**
 * 
 */
package de.nj.recipemanager.model.interfaces;

import java.util.UUID;
import de.nj.recipemanager.model.recipe.Recipe;

/**
 * @author Nico
 * @date 14.05.2013
 * 
 */
public interface Presenter
{
	public void start();
	
	public void end();
	
	public void addNewRecipe(Recipe newRecipe);
	
	public void changeRecipe(UUID id, Recipe change);
	
	public void deleteRecipe(UUID id);
}
