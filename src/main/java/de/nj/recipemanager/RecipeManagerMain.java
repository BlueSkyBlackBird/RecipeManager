/**
 *
 */
package de.nj.recipemanager;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import de.nj.recipemanager.presenter.FXUIPresenter;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * @author Nico
 * @date 21 Mar 2014
 *
 */
public class RecipeManagerMain extends Application
{
    public static void main(String[] args)
    {
        setupLogging();
        launch(args);
    }

    public static void setupLogging()
    {
        String sep = File.separator;
        String logDir = System.getProperty("user.home") + sep + "recipemanager" + sep + "logs" + sep + "recipe.log";

        try
        {
            Logger.getRootLogger().addAppender(new FileAppender(new SimpleLayout(), logDir));
        }
        catch (IOException e)
        {
            System.out.println("Could not establish logging to file: '" + logDir + "'");
        }
    }

    /*
     * (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        new FXUIPresenter(primaryStage).start();

    }

}
