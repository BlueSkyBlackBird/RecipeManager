/**
 *
 */
package de.nj.recipemanager.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import de.nj.recipemanager.model.interfaces.PresenterModelCallback;
import de.nj.recipemanager.model.interfaces.RecipeBook;
import de.nj.recipemanager.model.recipe.Recipe;

/**
 * @author Nico
 * @date 14.05.2013
 *
 */
public class DefaultRecipeBook implements RecipeBook
{
    private PresenterModelCallback presenterCallback;

    private List<Recipe>				recipeList;

    private Map<String, List<Recipe>> tagToRecipeMap;


    /**
     * This is the default constructor of this class.
     *
     * @param callback
     */
    public DefaultRecipeBook(PresenterModelCallback callback)
    {
        recipeList = new ArrayList<>();
        tagToRecipeMap = new HashMap<>();
        presenterCallback = callback;
    }

    /* (non-Javadoc)
     * @see de.nj.recipemanager.model.DataModel#addRecipe(java.lang.String)
     */
    @Override
    public void addRecipe(Recipe recipe)
    {
        recipeList.add(recipe);

        for (String tag : recipe.getTags())
        {
            tagToRecipeMap.putIfAbsent(tag, new LinkedList<>());
            tagToRecipeMap.get(tag).add(recipe);
        }
        presenterCallback.onRecipeAdded(recipe);
    }



    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DefaultRecipeBook other = (DefaultRecipeBook) obj;

        if (recipeList == null)
        {
            if (other.recipeList != null)
                return false;
        }
        else if (!recipeList.equals(other.recipeList))
            return false;
        if (tagToRecipeMap == null)
        {
            if (other.tagToRecipeMap != null)
                return false;
        }
        else if (!tagToRecipeMap.equals(other.tagToRecipeMap))
            return false;
        return true;
    }

    /* (non-Javadoc)
     * Return an unmodifiable view on the list containing all recipes known to this model.
     * @see de.nj.recipemanager.model.DataModel#getRecipes()
     *
     */
    @Override
    public List<Recipe> getRecipeListView()
    {
        return Collections.unmodifiableList(recipeList);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((recipeList == null) ? 0 : recipeList.hashCode());
        result = prime * result + ((tagToRecipeMap == null) ? 0 : tagToRecipeMap.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see de.nj.recipemanager.model.DataModel#removeRecipe(java.lang.String)
     */
    @Override
    public void removeRecipe(Recipe recipe)
    {
        recipeList.remove(recipe);
        recipe.getTags().forEach(tag -> tagToRecipeMap.get(tag).remove(recipe));
        presenterCallback.onRecipeDeleted(recipe);
    }

    @Override
    public String toString()
    {
        return "DataModelImpl [recipeList=" + recipeList + ", tagToRecipeMap=" + tagToRecipeMap + "]";
    }
}
