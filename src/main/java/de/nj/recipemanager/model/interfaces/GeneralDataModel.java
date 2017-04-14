/**
 * 
 */
package de.nj.recipemanager.model.interfaces;

import java.util.Locale;
import de.nj.recipemanager.model.Configuration;
import de.nj.recipemanager.model.recipe.Recipe;
import de.nj.recipemanager.model.recipe.RecipeChangeContainer;

/**
 * @author Nico
 * @date 21 Mar 2014
 * 
 */
public interface GeneralDataModel
{
	public RecipeBook getRecipeBook();
	
	public LocalisationProvider getLocalisationProvider();

	public Configuration getConfiguration();

    public Recipe createAndAddRecipe();

    public void changeLanguage(Locale oldLocale, Locale newLocale);

    public void changeRecipe(Recipe oldRecipe, RecipeChangeContainer newRecipe);
}
