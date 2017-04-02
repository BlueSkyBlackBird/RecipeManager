/**
 * 
 */
package de.nj.recipemanager.test.dummy;

import java.util.Locale;
import de.nj.recipemanager.model.interfaces.PresenterModelCallback;
import de.nj.recipemanager.model.recipe.Recipe;

/**
 * @author Nico
 * @date 29.03.2017
 * 
 */
public class DummyPresenterModelCallback implements PresenterModelCallback
{

	/* (non-Javadoc)
	 * @see de.nj.recipemanager.model.interfaces.PresenterModelCallback#onLanguageChanged(java.util.Locale, java.util.Locale)
	 */
	@Override
	public void onLanguageChanged(Locale oldLocale, Locale newLocale)
	{}

	/* (non-Javadoc)
	 * @see de.nj.recipemanager.model.interfaces.PresenterModelCallback#onRecipeAdded(de.nj.recipemanager.model.recipe.Recipe)
	 */
	@Override
	public void onRecipeAdded(Recipe recipe)
	{}

	/* (non-Javadoc)
	 * @see de.nj.recipemanager.model.interfaces.PresenterModelCallback#onRecipeDeleted(de.nj.recipemanager.model.recipe.Recipe)
	 */
	@Override
	public void onRecipeDeleted(Recipe recipe)
	{}

}
