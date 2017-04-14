/**
 *
 */
package de.nj.recipemanager.gui;

import de.nj.recipemanager.model.interfaces.LocalisationProvider;
import de.nj.recipemanager.model.recipe.Recipe;

/**
 * @author Nico
 * @date 14.05.2013
 *
 */
public interface RecipeUI
{
    public void addRecipeToUI(Recipe recipe, boolean highlight);
    
    public void removeRecipeFromUI(Recipe recipe);

    public void dispose();

    public void setLocationOnScreen(int x, int y);

    public void setVisibility(boolean show);
    
    public void localise(LocalisationProvider lang);

    public void updateRecipeDueToChange(Recipe changedRecipe, boolean highlight);

}
