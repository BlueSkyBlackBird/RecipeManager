/**
 * 
 */
package de.nj.recipemanager.model.recipe;



/**
 * @author Nico
 * @date 26 Mar 2014
 * 
 */
public class IngredientInformation
{
	private String ingredientName;
	private String unitOfQuantity;
	private double amount;

	public IngredientInformation()
	{
		this.ingredientName = "";
		this.amount = 0.0;
		this.unitOfQuantity = "";
	}
	
	/**
	 * This is the default constructor of this class.
	 *
	 * @param ingredient
	 * @param amount
	 * @throws Exception 
	 */
	public IngredientInformation(String ingredient, double amount, String unitOfQuantity)
	{
		this.ingredientName = ingredient;
		this.amount = amount;
		this.unitOfQuantity = unitOfQuantity;
	}	

	public String getIngredientName()
	{
		return ingredientName;
	}

	public void setIngredientName(String ingredient)
	{
		this.ingredientName = ingredient;
	}

	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	public String getUnitOfQuantity()
	{
		return unitOfQuantity;
	}

	public void setUnitOfQuantity(String unitOfQuantity)
	{
		this.unitOfQuantity = unitOfQuantity;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((ingredientName == null) ? 0 : ingredientName.hashCode());
		result = prime * result
				+ ((unitOfQuantity == null) ? 0 : unitOfQuantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IngredientInformation other = (IngredientInformation) obj;
		if (Double.doubleToLongBits(amount) != Double
				.doubleToLongBits(other.amount))
			return false;
		if (ingredientName == null)
		{
			if (other.ingredientName != null)
				return false;
		}
		else if (!ingredientName.equals(other.ingredientName))
			return false;
		if (unitOfQuantity == null)
		{
			if (other.unitOfQuantity != null)
				return false;
		}
		else if (!unitOfQuantity.equals(other.unitOfQuantity))
			return false;
		return true;
	}

}
