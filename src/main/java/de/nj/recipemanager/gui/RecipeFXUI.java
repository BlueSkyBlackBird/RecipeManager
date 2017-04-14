/**
 *
 */
package de.nj.recipemanager.gui;

import java.io.IOException;
import org.apache.log4j.Logger;
import de.nj.recipemanager.model.Configuration;
import de.nj.recipemanager.model.interfaces.LocalisationProvider;
import de.nj.recipemanager.model.interfaces.PresenterUICallback;
import de.nj.recipemanager.model.interfaces.RecipeBook;
import de.nj.recipemanager.model.recipe.Recipe;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author BlueSkyBlackBird
 * @date 31.08.2015
 *
 */
public class RecipeFXUI implements RecipeUI
{
   

    protected static final Logger logger = Logger.getLogger(RecipeFXUI.class);

    protected FXController        fxcontroller;

    protected PresenterUICallback presenterCallback;

    protected Stage               primaryStage;

    /**
     * This is the default constructor of this class.
     */
    public RecipeFXUI(LocalisationProvider lang, RecipeBook data, PresenterUICallback callback, Stage stage, Configuration config)
    {
        presenterCallback = callback;
        primaryStage = stage;
        fxcontroller = new FXController(callback);

        int width = Integer.parseInt(config.getUserConfig("ui.width"));
        int height = Integer.parseInt(config.getUserConfig("ui.height"));
        int minWidth = Integer.parseInt(config.getProgramConfig("ui.minwidth"));
        int minHeigth = Integer.parseInt(config.getProgramConfig("ui.minheight"));

        loadFXML();
        init(width, height, minWidth, minHeigth, lang, data, config);

        fxcontroller.layout();
        fxcontroller.localiseUI(lang);
    }

    /*
     * (non-Javadoc)
     * @see de.nj.recipemanager.gui.RecipeUI#dispose()
     */
    @Override
    public void dispose()
    {
        setVisibility(false);
        // TODO:
        Platform.exit();
    }

    protected void init(int width, int height, int minWidth, int minHeight, LocalisationProvider lang, RecipeBook data, Configuration config)
    {
        primaryStage.setTitle(config.getProgramConfig("general.name") + " " + config.getProgramConfig("general.version"));
        primaryStage.onCloseRequestProperty().addListener(e -> presenterCallback.onUIisClosing());
        setMinSize(minWidth, minHeight);
        setSize(width, height);
        fxcontroller.init();
    }

    protected void loadFXML()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/RecipeFXUIMainPane.fxml"));
        fxmlLoader.setController(fxcontroller);

        try
        {
            Pane root = (BorderPane) fxmlLoader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        }
        catch (IOException exception)
        {
            logger.error("Exception encountered while loading FXML: " + exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void localise(LocalisationProvider lang)
    {
        localiseLabels(lang);
    }

    protected void localiseLabels(LocalisationProvider lang)
    {
        fxcontroller.localiseUI(lang);
    }

    /*
     * (non-Javadoc)
     * @see de.nj.recipemanager.gui.RecipeUI#setLocation(int, int)
     */
    @Override
    public void setLocationOnScreen(int x, int y)
    {
        primaryStage.setX(x);
        primaryStage.setY(x);
    }

    protected void setMinSize(int width, int height)
    {
        primaryStage.setMinWidth(width);
        primaryStage.setMinHeight(height);
    }

    protected void setSize(int width, int height)
    {
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);

    }

    /*
     * (non-Javadoc)
     * @see de.nj.recipemanager.gui.RecipeUI#setVisible(boolean)
     */
    @Override
    public void setVisibility(boolean show)
    {
        if (show)
            primaryStage.show();
        else
            primaryStage.hide();
    }

    /*
     * (non-Javadoc)
     * @see
     * de.nj.recipemanager.gui.RecipeUI#addRecipeToUI(de.nj.recipemanager.model.
     * recipe.Recipe)
     */
    @Override
    public void addRecipeToUI(Recipe recipe, boolean highlight)
    {
        fxcontroller.addRecipe(recipe, highlight, highlight);
    }

    /*
     * (non-Javadoc)
     * @see
     * de.nj.recipemanager.gui.RecipeUI#removeRecipeFromUI(de.nj.recipemanager.
     * model.recipe.Recipe)
     */
    @Override
    public void removeRecipeFromUI(Recipe recipe)
    {
        fxcontroller.removeReicpe(recipe);

    }

    /*
     * (non-Javadoc)
     * @see de.nj.recipemanager.gui.RecipeUI#updateRecipeDueToChange(de.nj.
     * recipemanager.model.recipe.RecipeChangeContainer)
     */
    @Override
    public void updateRecipeDueToChange(Recipe changedRecipe, boolean highlight)
    {
        fxcontroller.displayRecipeIfSelectedElseClear(changedRecipe);

    }
}
