/**
 * 
 */
package de.nj.recipemanager.gui;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import de.nj.recipemanager.misc.RecipeHelper;
import de.nj.recipemanager.model.interfaces.LocalisationProvider;
import de.nj.recipemanager.model.interfaces.PresenterUICallback;
import de.nj.recipemanager.model.recipe.IngredientInformation;
import de.nj.recipemanager.model.recipe.Recipe;
import de.nj.recipemanager.model.recipe.RecipeChangeContainer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * @author Nico
 * @date 14.04.2017
 * 
 */
public class FXController
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
    @FXML private ListView<Recipe>                 listRecipes;

    private final static Logger LOGGER = Logger.getLogger(FXController.class);
    
    private ObservableList<Recipe>                 displayedRecipes;
    private ObservableList<IngredientInformation>  displayedIngredients;

    private PresenterUICallback presenterCallback;
    
    /**
     * This is the default constructor of this class.
     *
     */
    public FXController(PresenterUICallback presenterCallback)
    {
        this.presenterCallback = presenterCallback;
    }
    
    public void clearSelection()
    {
        textName.setText("");
        textTime.setText("");
        areaTags.setText("");
        areaPrepDesc.setText("");

        // TODO tableIngredients
    }

    public void displayRecipeInformation(Recipe recipe)
    {
        textName.setText(recipe.getName());
        textTime.setText(String.valueOf(recipe.getCookingTimeInMinutes()));
        areaTags.setText(RecipeHelper.tagsToString(recipe.getTags()));
        areaPrepDesc.setText(recipe.getCookingDescription());

        // TODO: tableIngredients
    }

    public void init()
    {
        listRecipes.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            displaySelectedRecipeElseClear();

        });

        displayedRecipes = listRecipes.getItems();
        displayedIngredients = tableIngredients.getItems();
    }

    public void layout()
    {
        layoutIngredientsTable();
        layoutMenuBar();
    }

    private void layoutIngredientsTable()
    {
        ObservableList<TableColumn<IngredientInformation, ?>> columns = tableIngredients.getColumns();
        columns.clear();
        columns.add(new TableColumn<>("1"));
        columns.add(new TableColumn<>("2"));
        columns.add(new TableColumn<>("3"));
    }

    public void addRecipe(Recipe recipe, boolean selectRecipe, boolean displayRecipe)
    {
        displayedRecipes.add(recipe);

        if (selectRecipe)
        {
            listRecipes.getSelectionModel().select(recipe);
            if (displayRecipe)
                displaySelectedRecipeElseClear();
        }
    }

    private void layoutMenuBar()
    {
        ObservableList<Menu> menus = menuBar.getMenus();
        menus.clear();
        menus.add(new Menu("TO BE DONE"));
        // TODO: Implement menu bar
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

        Recipe selectedRecipe = getSelectedRecipe();

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
        RecipeChangeContainer newRecipe = new RecipeChangeContainer();
        loadUIValuesIntoRecipe(newRecipe);

        if (selectedRecipe != null)
            presenterCallback.onChangeRecipe(selectedRecipe, newRecipe);
    }

    @FXML
    public void onDiscardChangesButtonClicked(ActionEvent event)
    {
        displayRecipeIfSelectedElseClear(getSelectedRecipe());
    }

    protected boolean doesUIholdAllValuesForRecipe()
    {
        // TODO: check values better
        return textName.getText().length() > 0 && textTime.getText().length() > 0 && areaPrepDesc.getText().length() > 0
                && tableIngredients.getItems().size() > 0;
    }

    public void loadUIValuesIntoRecipe(RecipeChangeContainer recipe)
    {
        recipe.setName(textName.getText());
        recipe.setCookingTimeInMinutes(textTime.getText());
        recipe.setCookingDescription(areaPrepDesc.getText());
        recipe.setTags(areaTags.getText());
        recipe.setIngredientInformation(new ArrayList<>(tableIngredients.getItems()));
    }

    public void clearInputFields()
    {
        textName.clear();
        textTime.clear();
        areaPrepDesc.clear();
        areaTags.clear();
        displayedIngredients.clear();

    }

    public Recipe getSelectedRecipe()
    {
        return listRecipes.getSelectionModel().getSelectedItem();
    }

    public void displaySelectedRecipeElseClear()
    {
        Recipe selectedRecipe = getSelectedRecipe();

        if (selectedRecipe != null)
            displayRecipeInformation(selectedRecipe);
        else
            clearInputFields();
    }

    public void displayRecipeIfSelectedElseClear(Recipe recipe)
    {
        Recipe selectedRecipe = getSelectedRecipe();

        if (selectedRecipe != null && selectedRecipe.equals(recipe))
            displayRecipeInformation(recipe);
        else
            clearInputFields();
    }

    public void removeReicpe(Recipe recipe)
    {
        // TODO: can be optimized
        Recipe wantedRecipe = displayedRecipes.stream().filter(r -> r.equals(recipe)).findFirst().get();

        if (wantedRecipe != null)
            displayedRecipes.remove(wantedRecipe);
        else
            LOGGER.error("Recipe " + recipe.getName() + " could not be deleted because it was not part of the displayed recipe tree!");
    }
}
