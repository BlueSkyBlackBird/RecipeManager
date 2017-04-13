/**
 * 
 */
package de.nj.recipemanager.model.recipe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * @author Nico
 * @date 07.05.2013
 * 
 */
public class Recipe
{
	private String name;
	
	private final List<IngredientInformation> ingredientInformation;
	
	private final List<RecipeStep> steps;
	
	private final Set<Tag> tags;

	/**
	 * This is the default constructor of this class.
	 *
	 */
	public Recipe(String recipeName)
	{
		name 					= recipeName;
		ingredientInformation 	= new ArrayList<>();
		steps 					= new ArrayList<>();
		tags 					= new HashSet<>();
	}
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<IngredientInformation> getIngredientInformation()
	{
		return ingredientInformation;
	}
	
	public void addIngredient(IngredientInformation pair)
	{
		ingredientInformation.add(pair);
	}
	
	public List<RecipeStep> getSteps()
	{
		return steps;
	}

	public Set<Tag> getTags()
	{
		return tags;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((ingredientInformation == null) ? 0 : ingredientInformation
						.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((steps == null) ? 0 : steps.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
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
		if (ingredientInformation == null)
		{
			if (other.ingredientInformation != null)
				return false;
		}
		else if (!ingredientInformation.equals(other.ingredientInformation))
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (steps == null)
		{
			if (other.steps != null)
				return false;
		}
		else if (!steps.equals(other.steps))
			return false;
		if (tags == null)
		{
			if (other.tags != null)
				return false;
		}
		else if (!tags.equals(other.tags))
			return false;
		return true;
	}

}
