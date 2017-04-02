/**
 * 
 */
package de.nj.recipemanager.model;

import java.util.Locale;
import java.util.ResourceBundle;
import de.nj.recipemanager.model.interfaces.LocalisationProvider;
import de.nj.recipemanager.model.interfaces.PresenterModelCallback;

/**
 * @author Nico
 * @date 22 Mar 2014
 * 
 */
public class ResourceBundleLocalisationProvider implements LocalisationProvider
{
	private ResourceBundle resources;
	
	private PresenterModelCallback	callback;

	public ResourceBundleLocalisationProvider(String file, Locale locale, PresenterModelCallback callback)
	{
		this.callback = callback;
		resources = ResourceBundle.getBundle(
				"localisation/" + file, 
				locale,
				ResourceBundleLocalisationProvider.class.getClassLoader());
	}
	
	/* (non-Javadoc)
	 * @see de.nj.recipemanager.model.LanguageModel#valueFor(java.lang.String)
	 */
	@Override
	public String valueFor(String key)
	{
		return resources.getString(key);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * de.nj.recipemanager.model.interfaces.LocalisationProvider#setLocale(java.
	 * util.Locale)
	 */
	@Override
	public void setLocale(Locale locale)
	{
		Locale oldLocale = resources.getLocale();
		resources = ResourceBundle.getBundle(resources.getBaseBundleName(), locale);
		callback.onLanguageChanged(resources.getLocale(), oldLocale);
	}

}
