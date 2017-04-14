/**
 *
 */
package de.nj.recipemanager.model.recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import de.nj.recipemanager.misc.RecipeHelper;

/**
 * @author Nico
 * @date 07.05.2013
 *
 */
public class Recipe
{
    private String                       cookingDescription;

    private int                          cookingTimeInMinutes;

    private UUID                         id;

    private List<IngredientInformation>  ingredientInformation;

    private String                       name;

    private Set<String>                  tags;

    public Recipe()
    {
        this("");
    }

    public Recipe(String recipeName)
    {
        id = UUID.randomUUID();
        name = recipeName;
        ingredientInformation = new ArrayList<>();
        cookingDescription = "";
        tags = new HashSet<>();
        cookingTimeInMinutes = 0;
    }

    /**
     * This is the default constructor of this class.
     *
     * @param selectedRecipe
     */
    public Recipe(Recipe recipe)
    {
        this.id = recipe.id;
        this.name = recipe.name;
        this.cookingDescription = recipe.cookingDescription;
        this.cookingTimeInMinutes = recipe.cookingTimeInMinutes;
        this.tags = new HashSet<>(recipe.getTags());
        this.ingredientInformation = RecipeHelper.copyIngredients(recipe.ingredientInformation);
    }

//    public void changeTo(RecipeChangeContainer newRecipe) {
//        this.name = newRecipe.getName();
//        this.cookingDescription = newRecipe.getCookingDescription();
//        this.cookingTimeInMinutes = newRecipe.getCookingTimeInMinutes();
//        this.tags = new HashSet<>(newRecipe.getTags());
//        this.ingredientInformation = RecipeHelper.copyIngredients(newRecipe.getIngredientInformation());
//    }
    
    public void addIngredient(IngredientInformation pair)
    {
        ingredientInformation.add(pair);
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
        Recipe other = (Recipe) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

    public String getCookingDescription()
    {
        return cookingDescription;
    }

    public int getCookingTimeInMinutes()
    {
        return cookingTimeInMinutes;
    }

    public UUID getId()
    {
        return id;
    }

    public List<IngredientInformation> getIngredientInformation()
    {
        return ingredientInformation;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    public Set<String> getTags()
    {
        return tags;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * @param string
     */
    public void setCookingDescription(String string)
    {
        cookingDescription = string;

    }

    public void setCookingTimeInMinutes(int minutes)
    {
        this.cookingTimeInMinutes = minutes;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public void setIngredientInformation(List<IngredientInformation> ingredientInformation)
    {
        this.ingredientInformation = ingredientInformation;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 
     * @param tags
     */
    public void setTags(Set<String> tags)
    {
        this.tags = tags;
    }

    @Override
    public String toString()
    {
        return "Recipe [id=" + id + ", name=" + name + ", ingredientInformation=" + ingredientInformation + ", cookingDescription=" + cookingDescription
                + ", tags=" + tags + ", minutes=" + cookingTimeInMinutes + "]";
    }
}
