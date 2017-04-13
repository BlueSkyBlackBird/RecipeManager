/**
 *
 */
package de.nj.recipemanager.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import de.nj.recipemanager.model.recipe.IngredientInformation;
import de.nj.recipemanager.model.recipe.Recipe;

/**
 * @author Nico
 * @date 02.04.2017
 *
 */
public class RecipeHelper
{
    /**
     * 
     * @param string
     * @return
     */
    public static int cookingTimeStringToInt(String string)
    {
        // TODO: Try more ways to handle the number
        if (string.matches("\\d+"))
            return Integer.parseInt(string);
        else
            return 0;
    }

    /**
     * 
     * @param tags
     * @param separatorRegex
     * @return
     */
    public static Set<String> tagsToAdjustedSet(String tags, String separatorRegex)
    {
        String[] splitTags = tags.split(separatorRegex);

        return Arrays.stream(splitTags)
                .map(RecipeHelper::adjustTagForRecipe)
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * 
     * @param recipe
     */
    public static void adjustTagsOfRecipe(Recipe recipe)
    {
        Set<String> tags = recipe.getTags();
        tags = tags.stream()
                .map(tag -> adjustTagForRecipe(tag))
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * 
     * @param tag
     * @return
     */
    public static String adjustTagForRecipe(String tag)
    {
        return capitalizeWordsPrefixedBySpearator(tag.trim().toLowerCase(), ' ', true);
    }
    
    /**
     * 
     * @param string
     * @param prefix
     * @param captialiseFirstCharacter
     * @return
     */
    public static String capitalizeWordsPrefixedBySpearator(String string, char prefix, boolean captialiseFirstCharacter)
    {
        char[] tagAsArray = string.toCharArray();

        if (captialiseFirstCharacter)
            tagAsArray[1] = Character.toUpperCase(tagAsArray[1]);
        
       for (int i = 1; i < tagAsArray.length; i++)
            if (tagAsArray[i - 1] == prefix)
                tagAsArray[i] = Character.toUpperCase(tagAsArray[i]);

        return new String(tagAsArray);
    }
    
    /**
     * 
     * @param ingredients
     */
    public static void adjustIngredientsForRecipe(Collection<IngredientInformation> ingredients)
    {
        ingredients.forEach(RecipeHelper::adjustIngredientForRecipe);
    }
    
    /**
     * 
     * @param ingredient
     */
    public static void adjustIngredientForRecipe(IngredientInformation ingredient)
    {
        String name = capitalizeWordsPrefixedBySpearator(ingredient.getIngredientName().trim().toLowerCase(), ' ', true);
        String unit = capitalizeWordsPrefixedBySpearator(ingredient.getUnitOfQuantity().trim().toLowerCase(), ' ', true);
        
        ingredient.setUnitOfQuantity(unit);
        ingredient.setIngredientName(name);
    }

    /**
     * @param items
     * @return
     */
    public static List<IngredientInformation> ingredientUICollectionToListForRecipe(Collection<IngredientInformation> ingredients)
    {
        List<IngredientInformation> newList = new ArrayList<>(ingredients);
        adjustIngredientsForRecipe(newList);
        return newList;
    }

    /**
     * @param ingredientInformation
     * @return
     */
    public static List<IngredientInformation> copyIngredients(Collection<IngredientInformation> ingredientInformation)
    {
        return ingredientInformation.stream()
                .map(IngredientInformation::copy)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @param tags
     * @return
     */
    public static String tagsToString(Set<String> tags)
    {
        return tags.stream().sorted().reduce((s1,s2) ->s1 + " " + s2).orElse("");
    }

}
