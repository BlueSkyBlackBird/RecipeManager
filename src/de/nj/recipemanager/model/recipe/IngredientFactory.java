/**
 * 
 */
package de.nj.recipemanager.model.recipe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nico
 * @date 13.11.2014
 * 
 */
public class IngredientFactory
{
	private static final IngredientFactory INSTANCE = new IngredientFactory();
	
	private Map<String, Ingredient> uidToIngredientMap = new HashMap<>();
	
	public static Ingredient get(String name, String uid)
	{
		Ingredient ing = INSTANCE.uidToIngredientMap.get(uid);
		
		// TODO: Check name mismatch: English to German ingredient names
		if (ing == null)
		{
			ing = new Ingredient(name, uid);
			INSTANCE.uidToIngredientMap.put(uid, ing);
		}
		
		return ing;	
	}	
}
