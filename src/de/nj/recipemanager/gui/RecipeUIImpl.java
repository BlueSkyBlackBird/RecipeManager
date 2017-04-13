/**
 * 
 */
package de.nj.recipemanager.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTree;
import de.nj.recipemanager.control.Controller;
import de.nj.recipemanager.model.DataModel;
import de.nj.recipemanager.model.LanguageModel;

/**
 * @author Nico
 * @date 14.05.2013
 * 
 */
public class RecipeUIImpl implements RecipeUI
{
	private JFrame frame = new JFrame();
	
	private JTree recipeOverviewTree;
	
	private JTextField nameField;
	
	private JTextField tagsField;
	
	private Controller controller;
	
	/**
	 * This is the default constructor of this class.
	 *
	 * @param controller
	 */
	public RecipeUIImpl(Controller controller, LanguageModel language)
	{
		this.controller = controller;
		initUI(language);
		layoutUI(language, controller);
	}
	
	private void initUI(LanguageModel language)
	{
		frame.setTitle(language.valueFor("program.name"));
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void layoutUI(LanguageModel language, Controller controller)
	{
		JLabel nameLabel = new JLabel(language.valueFor("general.name"));
		JLabel tagsLabel = new JLabel(language.valueFor("general.tags"));
		JLabel ingredientsLabel = new JLabel(language.valueFor("general.name"));
		JLabel preperationLabel = new JLabel(language.valueFor("general.preperation"));
		
		recipeOverviewTree = new JTree();
		tagsField = new JTextField();
		tagsField = new JTextField();
	}

	public void setLocation(int x, int y)
	{
		frame.setLocation(x, y);
	}
	
	public void setVisible(boolean value)
	{
		frame.setVisible(value);
	}
	
	public void dispose()
	{
		frame.dispose();
	}
	
	public void updateFromModel(DataModel model)
	{
		//TODO:
	}
}
