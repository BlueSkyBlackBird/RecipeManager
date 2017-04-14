/**
 *
 */
package de.nj.recipemanager.model.interfaces;

import de.nj.recipemanager.model.recipe.Recipe;
import de.nj.recipemanager.model.recipe.RecipeChangeContainer;

/**
 * @author Nico
 * @date 27.03.2017
 *
 */
public interface PresenterUICallback
{
    public void onUIWasClosed();

    public void onChangeRecipe(Recipe oldRecipe, RecipeChangeContainer newRecipe);

    public Recipe onNewRecipe();

    public void onDeleteRecipe(Recipe selectedRecipe);

    public void onSaveAll();
}
