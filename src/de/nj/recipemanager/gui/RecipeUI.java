/**
 * 
 */
package de.nj.recipemanager.gui;

import de.nj.recipemanager.model.DataModel;

/**
 * @author Nico
 * @date 14.05.2013
 * 
 */
public interface RecipeUI
{
	public void setLocation(int x, int y);
	
	public void setVisible(boolean value);
	
	public void dispose();
	
	public void updateFromModel(DataModel model);
}
