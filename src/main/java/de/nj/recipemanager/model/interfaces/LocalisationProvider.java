/**
 * 
 */
package de.nj.recipemanager.model.interfaces;

import java.util.Locale;

/**
 * @author Nico
 * @date 22 Mar 2014
 * 
 */
public interface LocalisationProvider
{
	public String valueFor(String key);

	public void setLocale(Locale locale);
}
