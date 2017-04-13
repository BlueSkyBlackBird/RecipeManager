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
	private Ingredient ingredient;
	private QuantityUnit unitOfQuantity;
	private double amount;

	/**
	 * This is the default constructor of this class.
	 *
	 * @param ingredient
	 * @param amount
	 * @throws Exception 
	 */
	public IngredientInformation(Ingredient ingredient, double amount, QuantityUnit unitOfQuantity) throws Exception
	{
		this.ingredient = ingredient;
		this.amount = amount;
		this.unitOfQuantity = unitOfQuantity;
	}	

	public Ingredient getIngredient()
	{
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient)
	{
		this.ingredient = ingredient;
	}

	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	public QuantityUnit getUnitOfQuantity()
	{
		return unitOfQuantity;
	}

	public void setUnitOfQuantity(QuantityUnit unitOfQuantity)
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
				+ ((ingredient == null) ? 0 : ingredient.hashCode());
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
		if (ingredient == null)
		{
			if (other.ingredient != null)
				return false;
		}
		else if (!ingredient.equals(other.ingredient))
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
