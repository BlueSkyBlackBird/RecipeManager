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
import de.nj.recipemanager.model.recipe.Recipe;
import de.nj.recipemanager.model.recipe.RecipeChangeContainer;

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
    
    protected PresenterModelCallback callback;

    /**
     * This is the default constructor of this class.
     */
    public GeneralModelContainer(PresenterModelCallback modelCallback)
    {
        callback = modelCallback;
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

    /* (non-Javadoc)
     * @see de.nj.recipemanager.model.interfaces.GeneralDataModel#createAndAddRecipe()
     */
    @Override
    public Recipe createAndAddRecipe()
    {
        Recipe recipe = new Recipe();
        recipe.setName("<<???>>"); //TODO: Use lang to give a default name
        data.addRecipe(recipe);
        return recipe;
    }

    /* (non-Javadoc)
     * @see de.nj.recipemanager.model.interfaces.GeneralDataModel#changeLanguage(java.util.Locale, java.util.Locale)
     */
    @Override
    public void changeLanguage(Locale oldLocale, Locale newLocale)
    {
       language.setLocale(newLocale);
       callback.onLanguageChanged(oldLocale, newLocale);
        
    }

    /* (non-Javadoc)
     * @see de.nj.recipemanager.model.interfaces.GeneralDataModel#changeRecipe(de.nj.recipemanager.model.recipe.Recipe, de.nj.recipemanager.model.recipe.Recipe)
     */
    @Override
    public void changeRecipe(Recipe oldRecipe, RecipeChangeContainer newRecipe)
    {
       data.changeRecipe(oldRecipe,newRecipe);
        
    }

    /* (non-Javadoc)
     * @see de.nj.recipemanager.model.interfaces.GeneralDataModel#deleteRecipe(de.nj.recipemanager.model.recipe.Recipe)
     */
    @Override
    public void removeRecipe(Recipe selectedRecipe)
    {
      data.removeRecipe(selectedRecipe);
        
    }
}
