/**
 * 
 */
package de.nj.recipemanager.model.interfaces;

import java.util.Locale;
import de.nj.recipemanager.model.recipe.Recipe;

/**
 * @author Nico
 * @date 28.03.2017
 * 
 */
public interface PresenterModelCallback
{
	public void onLanguageChanged(Locale oldLocale, Locale newLocale);

	public void onRecipeAdded(Recipe recipe);

	public void onRecipeDeleted(Recipe recipe);

}
