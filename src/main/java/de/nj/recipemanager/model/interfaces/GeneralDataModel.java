/**
 * 
 */
package de.nj.recipemanager.model.interfaces;

import de.nj.recipemanager.model.Configuration;

/**
 * @author Nico
 * @date 21 Mar 2014
 * 
 */
public interface GeneralDataModel
{
	public RecipeBook getRecipeBook();
	
	public LocalisationProvider getLocalisationProvider();

	public Configuration getConfiguration();
}
