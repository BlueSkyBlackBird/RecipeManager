/**
 *
 */
package de.nj.recipemanager.model;

import java.util.Locale;
import org.apache.log4j.Logger;
import de.nj.recipemanager.model.interfaces.GeneralDataModel;
import de.nj.recipemanager.model.interfaces.LocalisationProvider;
import de.nj.recipemanager.model.interfaces.PresenterModelCallback;
import de.nj.recipemanager.model.interfaces.RecipeBook;

/**
 * @author Nico
 * @date 21 Mar 2014
 *
 */
public class GeneralModelContainer implements GeneralDataModel
{
    protected static final Logger	logger	= Logger.getLogger(GeneralDataModel.class);

    protected Configuration			config;

    protected RecipeBook			data;

    protected LocalisationProvider	language;

    /**
     * This is the default constructor of this class.
     */
    public GeneralModelContainer(PresenterModelCallback callback)
    {
        config = new Configuration();
        data = new DefaultRecipeBook(callback);
        language = new ResourceBundleLocalisationProvider("language", Locale.getDefault(), callback);
    }

    /*
     * (non-Javadoc)
     * @see
     * de.nj.recipemanager.model.interfaces.GeneralDataModel#getConfiguration()
     */
    @Override
    public Configuration getConfiguration()
    {
        return config;
    }

    /* (non-Javadoc)
     * @see de.nj.recipemanager.model.GeneralModel#getLanguageModel()
     */
    @Override
    public LocalisationProvider getLocalisationProvider()
    {
        return language;
    }

    /* (non-Javadoc)
     * @see de.nj.recipemanager.model.GeneralModel#getDataModel()
     */
    @Override
    public RecipeBook getRecipeBook()
    {
        return data;
    }


}
