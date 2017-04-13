/**
 * 
 */
package de.nj.recipemanager.model;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Nico
 * @date 22 Mar 2014
 * 
 */
public class LanguageModelImpl implements LanguageModel
{
	private final ResourceBundle resources;
	
	/**
	 * This is the default constructor of this class.
	 *
	 */
	public LanguageModelImpl(String sourceData, Locale locale)
	{
		resources = ResourceBundle.getBundle(
				sourceData, 
				locale,
				LanguageModelImpl.class.getClassLoader());
	}
	
	/* (non-Javadoc)
	 * @see de.nj.recipemanager.model.LanguageModel#valueFor(java.lang.String)
	 */
	@Override
	public String valueFor(String key)
	{
		return resources.getString(key);
	}

}
