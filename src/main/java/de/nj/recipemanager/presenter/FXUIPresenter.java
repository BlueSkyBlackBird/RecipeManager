/**
 *
 */
package de.nj.recipemanager.presenter;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import org.apache.log4j.Logger;
import de.nj.recipemanager.gui.RecipeFXUI;
import de.nj.recipemanager.gui.RecipeUI;
import de.nj.recipemanager.model.Configuration;
import de.nj.recipemanager.model.GeneralModelContainer;
import de.nj.recipemanager.model.interfaces.GeneralDataModel;
import de.nj.recipemanager.model.interfaces.Presenter;
import de.nj.recipemanager.model.interfaces.PresenterModelCallback;
import de.nj.recipemanager.model.interfaces.PresenterUICallback;
import de.nj.recipemanager.model.recipe.Recipe;
import de.nj.recipemanager.model.recipe.RecipeChangeContainer;
import javafx.stage.Stage;

/**
 * @author Nico
 * @date 14.05.2013
 *
 */
public class FXUIPresenter implements Presenter, PresenterUICallback, PresenterModelCallback
{
    protected static final Logger LOGGER = Logger.getLogger(FXUIPresenter.class);

    protected GeneralDataModel    model;

    protected RecipeUI            ui;

    /**
     * This is the default constructor of this class.
     *
     */
    public FXUIPresenter(GeneralDataModel dataModel, RecipeUI uiDisplay)
    {
        model = dataModel;
        ui = uiDisplay;
    }

    public FXUIPresenter(Stage stage)
    {
        model = new GeneralModelContainer(this);
        ui = new RecipeFXUI(model.getLocalisationProvider(), model.getRecipeBook(), this, stage, model.getConfiguration());
    }

    /*
     * (non-Javadoc)
     * @see de.nj.recipemanager.control.Controller#end()
     */
    @Override
    public void end()
    {
        ui.setVisibility(false);
        ui.dispose();
    }

    protected void loadData(String fileName)
    {
        try
        {
            FileIOHandler.readRecipeBookFromJSON(model.getRecipeBook(), fileName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /*
     * (non-Javadoc)
     * @see
     * de.nj.recipemanager.model.interfaces.PresenterUICallback#onDeleteRecipe(
     * de.nj.recipemanager.model.recipe.Recipe)
     */
    @Override
    public void onDeleteRecipe(Recipe selectedRecipe)
    {
        model.removeRecipe(selectedRecipe);

    }

    /*
     * (non-Javadoc)
     * @see de.nj.recipemanager.model.interfaces.PresenterModelCallback#
     * languageChanged(java.util.Locale, java.util.Locale)
     */
    @Override
    public void onLanguageChanged(Locale oldLocale, Locale newLocale)
    {
        ui.localise(model.getLocalisationProvider());
    }

    /*
     * (non-Javadoc)
     * @see
     * de.nj.recipemanager.model.interfaces.PresenterUICallback#onNewRecipe()
     */
    @Override
    public Recipe onNewRecipe()
    {
        return model.createAndAddRecipe();
    }

    /*
     * (non-Javadoc)
     * @see
     * de.nj.recipemanager.model.interfaces.PresenterModelCallback#recipeAdded(
     * de.nj.recipemanager.model.recipe.Recipe)
     */
    @Override
    public void onRecipeAdded(Recipe recipe)
    {
        ui.addRecipeToUI(recipe, true);
    }

    /*
     * (non-Javadoc)
     * @see
     * de.nj.recipemanager.model.interfaces.PresenterModelCallback#recipeDeleted
     * (de.nj.recipemanager.model.recipe.Recipe)
     */
    @Override
    public void onRecipeDeleted(Recipe recipe)
    {
        ui.removeRecipeFromUI(recipe);
    }

    /*
     * (non-Javadoc)
     * @see de.nj.recipemanager.model.interfaces.PresenterUICallback#onSaveAll()
     */
    @Override
    public void onSaveAll()
    {
        saveData(getSaveFilename());
    }

    /*
     * (non-Javadoc)
     * @see
     * de.nj.recipemanager.model.interfaces.PresenterUICallback#onUIWasClosed()
     */
    @Override
    public void onUIisClosing()
    {
        
        saveData(getSaveFilename());

    }
    
    protected String getSaveFilename() {
        Configuration conf = model.getConfiguration();
        return System.getProperty("user.home") + File.separator + "recipemanager" + File.separator + conf.getUserConfig("recipes.filename") ;
    }

    protected void saveData(String fileName)
    {
        try
        {
            FileIOHandler.saveRecipeBookAsJSON(model.getRecipeBook(), fileName);
            System.out.println("Saved File t: " + getSaveFilename());
        }
        catch (IOException e)
        {
            LOGGER.error("Unable to save files to '" + fileName + "' :" + e.getMessage());
        }

    }

    /*
     * (non-Javadoc)
     * @see de.nj.recipemanager.control.Controller#start()
     */
    @Override
    public void start()
    {
        loadData(getSaveFilename());
        ui.setVisibility(true);
    }

    /*
     * (non-Javadoc)
     * @see
     * de.nj.recipemanager.model.interfaces.PresenterUICallback#onChangeRecipe(
     * de.nj.recipemanager.model.recipe.Recipe)
     */
    @Override
    public void onChangeRecipe(Recipe oldRecipe, RecipeChangeContainer newRecipe)
    {
        model.changeRecipe(oldRecipe, newRecipe);

    }

    /*
     * (non-Javadoc)
     * @see de.nj.recipemanager.model.interfaces.PresenterModelCallback#
     * onRecipeChanged(de.nj.recipemanager.model.recipe.RecipeChangeContainer)
     */
    @Override
    public void onRecipeChanged(Recipe changedRecipe)
    {
        ui.updateRecipeDueToChange(changedRecipe, true);

    }
}
