/**
 * 
 */
package de.nj.recipemanager.model;

import java.util.Locale;
import java.util.Properties;

/**
 * @author Nico
 * @date 21 Mar 2014
 * 
 */
public class GeneralModelImpl implements GeneralModel
{
	private DataModel data;
	
	private LanguageModel language;
	
	/**
	 * This is the default constructor of this class.
	 */
	public GeneralModelImpl(Properties configuration)
	{
		data = new DataModelImpl(configuration);
		language = new LanguageModelImpl("resources/language", Locale.getDefault());
	}
	
	/* (non-Javadoc)
	 * @see de.nj.recipemanager.model.GeneralModel#getDataModel()
	 */
	@Override
	public DataModel getDataModel()
	{
		return data;
	}

	/* (non-Javadoc)
	 * @see de.nj.recipemanager.model.GeneralModel#getLanguageModel()
	 */
	@Override
	public LanguageModel getLanguageModel()
	{
		return language;
	}
}
