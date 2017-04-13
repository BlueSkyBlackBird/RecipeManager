/**
 *
 */
package de.nj.recipemanager.model.interfaces;

import de.nj.recipemanager.model.recipe.Recipe;

/**
 * @author Nico
 * @date 27.03.2017
 *
 */
public interface PresenterUICallback
{
    public void onUIWasClosed();

    public void onChangeRecipe(Recipe oldRecipe, Recipe newRecipe);

    /**
     *
     */
    public Recipe onNewRecipe();

    /**
     * @param selectedRecipe
     */
    public void onDeleteRecipe(Recipe selectedRecipe);

    /**
     *
     */
    public void onSaveAll();
}
