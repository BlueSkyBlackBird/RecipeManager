/**
 * 
 */
package de.nj.recipemanager.control;


import java.util.Properties;
import de.nj.recipemanager.gui.RecipeUI;
import de.nj.recipemanager.gui.RecipeUIImpl;
import de.nj.recipemanager.model.GeneralModel;
import de.nj.recipemanager.model.GeneralModelImpl;

/**
 * @author Nico
 * @date 14.05.2013
 * 
 */
public class ControllerImpl implements Controller
{
	private GeneralModel model;
	
	private RecipeUI ui;
	
	private FileIOHandler fileIO;
	
	/**
	 * This is the default constructor of this class.
	 */
	public ControllerImpl()
	{
		fileIO = new FileIOHandler();
		model = new GeneralModelImpl(loadProperties("config.properties"));
		ui = new RecipeUIImpl(this, model.getLanguageModel());
	}
	
	private Properties loadProperties(String name)
	{
		return fileIO.loadProperties(name);
	}

	/* (non-Javadoc)
	 * @see de.nj.recipemanager.control.Controller#start()
	 */
	@Override
	public void start()
	{
		ui.setVisible(true);
		
	}

	/* (non-Javadoc)
	 * @see de.nj.recipemanager.control.Controller#end()
	 */
	@Override
	public void end()
	{
		ui.setVisible(false);
		ui.dispose();
	}

	/* (non-Javadoc)
	 * @see de.nj.recipemanager.control.Controller#saveData()
	 */
	@Override
	public void saveData(String fileName)
	{
		try
		{
			fileIO.saveData(model, fileName);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see de.nj.recipemanager.control.Controller#loadData()
	 */
	@Override
	public void loadData(String fileName)
	{
		fileIO.loadData(model.getDataModel(), fileName);
	}
}
