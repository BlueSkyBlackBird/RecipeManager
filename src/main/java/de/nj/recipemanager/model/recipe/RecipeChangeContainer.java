/**
 * 
 */
package de.nj.recipemanager.model.recipe;

import java.util.List;

/**
 * @author Nico
 * @date 13.04.2017
 * 
 */
public class RecipeChangeContainer
{
    private String                       cookingDescription;

    private String                       cookingTimeInMinutes;

    private List<IngredientInformation>  ingredientInformation;

    private String                       name;

    private String                       tags;

    
    public String getCookingDescription()
    {
        return cookingDescription;
    }

    public void setCookingDescription(String cookingDescription)
    {
        this.cookingDescription = cookingDescription;
    }

    public String getCookingTimeInMinutes()
    {
        return cookingTimeInMinutes;
    }

    public void setCookingTimeInMinutes(String cookingTimeInMinutes)
    {
        this.cookingTimeInMinutes = cookingTimeInMinutes;
    }

    public List<IngredientInformation> getIngredientInformation()
    {
        return ingredientInformation;
    }

    public void setIngredientInformation(List<IngredientInformation> ingredientInformation)
    {
        this.ingredientInformation = ingredientInformation;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTags()
    {
        return tags;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }
    
    
}
