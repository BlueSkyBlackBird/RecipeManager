/**
 * 
 */
package de.nj.recipemanager.control;

/**
 * @author Nico
 * @date 14.05.2013
 * 
 */
public interface Controller
{
	public void start();
	
	public void end();
	
	public void saveData(String fileName); //Daten mitgeben
	
	public void loadData(String fileName);
	
}
