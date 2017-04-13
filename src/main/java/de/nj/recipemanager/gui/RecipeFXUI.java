/**
 *
 */
package de.nj.recipemanager.gui;

import java.io.IOException;
import org.apache.log4j.Logger;
import de.nj.recipemanager.misc.RecipeHelper;
import de.nj.recipemanager.model.Configuration;
import de.nj.recipemanager.model.interfaces.LocalisationProvider;
import de.nj.recipemanager.model.interfaces.PresenterUICallback;
import de.nj.recipemanager.model.interfaces.RecipeBook;
import de.nj.recipemanager.model.recipe.IngredientInformation;
import de.nj.recipemanager.model.recipe.Recipe;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
    protected class FXController
    {
        @FXML private TextArea                         areaPrepDesc;
        @FXML private TextArea                         areaTags;
        @FXML private Button                           buttonDelete;
        @FXML private Button                           buttonNew;
        @FXML private Button                           buttonSaveAll;
        @FXML private Button                           buttonApplyChanges;
        @FXML private Button                           buttonDiscardChanges;
        @FXML private Label                            labelIngredients;
        @FXML private Label                            labelName;

        @FXML private Label                            labelPrepDesc;
        @FXML private Label                            labelTags;
        @FXML private Label                            labelTime;
        @FXML private MenuBar                          menuBar;

        @FXML private TableView<IngredientInformation> tableIngredients;
        @FXML private TextField                        textName;
        @FXML private TextField                        textTime;
        @FXML private TreeView<Recipe>                 treeRecipes;

        public void clearSelection()
        {
            textName.setText("");
            textTime.setText("");
            areaTags.setText("");
            areaPrepDesc.setText("");
            
            //TODO tableIngredients
        }

        public void displayRecipeInformation(Recipe recipe)
        {
            textName.setText(recipe.getName());
            textTime.setText(String.valueOf(recipe.getCookingTimeInMinutes()));
            areaTags.setText(RecipeHelper.tagsToString(recipe.getTags()));
            areaPrepDesc.setText(recipe.getCookingDescription());
            
            // TODO: tableIngredients
        }

        public void layout()
        {
            layoutIngredientsTable();
            layoutMenuBar();
            layoutTreeView();
        }

        private void layoutIngredientsTable()
        {
            ObservableList<TableColumn<IngredientInformation, ?>> columns = tableIngredients.getColumns();
            columns.clear();
            columns.add(new TableColumn<>("1"));
            columns.add(new TableColumn<>("2"));
            columns.add(new TableColumn<>("3"));
        }

        public void addRecipeToTree(Recipe recipe)
        {

            treeRecipes.getRoot().getChildren().add(new TreeItem<>(recipe));
        }

        private void layoutMenuBar()
        {
            ObservableList<Menu> menus = menuBar.getMenus();
            menus.clear();
            menus.add(new Menu("TO BE DONE"));
            // TODO: Implement menu bar
        }

        private void layoutTreeView()
        {
            TreeItem<Recipe> root = new TreeItem<>();
            root.setExpanded(true);
            treeRecipes.setRoot(root);
            treeRecipes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }

        public void localiseUI(LocalisationProvider provider)
        {
            labelName.setText(provider.valueFor("ui.name"));
            labelTime.setText(provider.valueFor("ui.time"));
            labelTags.setText(provider.valueFor("ui.tags"));
            labelIngredients.setText(provider.valueFor("ui.ingredients"));
            labelPrepDesc.setText(provider.valueFor("ui.preperation"));

            buttonNew.setText(provider.valueFor("ui.btn.newrecipe"));
            buttonDelete.setText(provider.valueFor("ui.btn.delrecipe"));
            buttonSaveAll.setText(provider.valueFor("ui.btn.saveall"));
            buttonApplyChanges.setText(provider.valueFor("ui.btn.applychanges"));
            buttonDiscardChanges.setText(provider.valueFor("ui.btn.discardchanges"));

            tableIngredients.getColumns().get(0).setText(provider.valueFor("ui.table.ingredient"));
            tableIngredients.getColumns().get(1).setText(provider.valueFor("ui.table.amount"));
            tableIngredients.getColumns().get(2).setText(provider.valueFor("ui.table.unitofquanity"));
        }

        @FXML
        public void onDeleteRecipeButtonClicked(ActionEvent event)
        {
            // TODO:

            TreeItem<Recipe> recipeTreeItem = treeRecipes.getSelectionModel().getSelectedItem();
            Recipe selectedRecipe = recipeTreeItem == null ? null : recipeTreeItem.getValue();

            if (selectedRecipe != null)
                presenterCallback.onDeleteRecipe(selectedRecipe);
        }

        @FXML
        public void onNewRecipeButtonClicked(ActionEvent event)
        {
            Recipe newRecipe = presenterCallback.onNewRecipe();
            displayRecipeInformation(newRecipe);

        }

        @FXML
        public void onSaveAllRecipeButtonClicked(ActionEvent event)
        {
            presenterCallback.onSaveAll();
        }

        @FXML
        public void onApplyChangesButtonClicked(ActionEvent event)
        {
            Recipe selectedRecipe = getSelectedRecipe();
            Recipe newRecipe = new Recipe(selectedRecipe);
            loadUIValuesIntoRecipe(newRecipe);
            if (selectedRecipe != null)
                presenterCallback.onChangeRecipe(selectedRecipe, newRecipe);
        }

        @FXML
        public void onDiscardChangesButtonClicked(ActionEvent event)
        {
            
        }

        protected boolean doesUIholdAllValuesForRecipe()
        {
            // TODO: check values better
            return      textName.getText().length() > 0
                    &&  textTime.getText().length() > 0
                    &&  areaPrepDesc.getText().length() > 0
                    && tableIngredients.getItems().size() > 0;
        }

        public void loadUIValuesIntoRecipe(Recipe recipe)
        {
            recipe.setName(textName.getText());
            recipe.setCookingTimeInMinutes(RecipeHelper.cookingTimeStringToInt(textTime.getText()));
            recipe.setCookingDescription(areaPrepDesc.getText());
            recipe.setTags(RecipeHelper.tagsToAdjustedSet(areaTags.getText(), ","));
            recipe.setIngredientInformation(RecipeHelper.ingredientUICollectionToListForRecipe(tableIngredients.getItems()));
        }
        
        public Recipe getSelectedRecipe()
        {
            TreeItem<Recipe> item = treeRecipes.getSelectionModel().getSelectedItem();
            return item == null ? null : item.getValue();
        }

        public void displaySelectedRecipe()
        {
            Recipe selectedRecipe = getSelectedRecipe();
            
            if (selectedRecipe != null)
                displayRecipeInformation(selectedRecipe);
        }
        
        public void displayRecipeIfSelected(Recipe recipe)
        {
            Recipe selectedRecipe = getSelectedRecipe();
            
            if (selectedRecipe != null && selectedRecipe.equals(recipe))
                displayRecipeInformation(recipe);
        }

    }

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
        fxcontroller = new FXController();

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

    protected void init(int width, int height, int minWidth, int minHeight, LocalisationProvider lang, RecipeBook data,
            Configuration config)
    {
        primaryStage.setTitle(config.getProgramConfig("general.name") + " " + config.getProgramConfig("general.version"));
        primaryStage.onCloseRequestProperty().addListener(e -> presenterCallback.onUIWasClosed());
        setMinSize(minWidth, minHeight);
        setSize(width, height);
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
    public void addRecipeToUI(Recipe recipe)
    {
        fxcontroller.addRecipeToTree(recipe);

    }
}
