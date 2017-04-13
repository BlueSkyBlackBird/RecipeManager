/**
 * 
 */
package de.nj.recipemanager.model.recipe;

/**
 * @author Nico
 * @date 04.11.2014
 * 
 */
public class QuantityUnit 
{
	private final String unitUID;
	
	private final String name;
	
	private final QuantityType type;
	
	
	/**
	 * This is the default constructor of this class.
	 *
	 * @param unitUID
	 * @param name
	 * @param type
	 */
	public QuantityUnit(String unitUID, String name, QuantityType type)
	{
		this.unitUID = unitUID;
		this.name = name;
		this.type = type;
	}


	public String getName()
	{
		// TODO Auto-generated method stub
		return name;
	}


	public QuantityType getType()
	{
		// TODO Auto-generated method stub
		return type;
	}


	public String getUid()
	{
		// TODO Auto-generated method stub
		return unitUID;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((unitUID == null) ? 0 : unitUID.hashCode());
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
		QuantityUnit other = (QuantityUnit) obj;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		if (unitUID == null)
		{
			if (other.unitUID != null)
				return false;
		}
		else if (!unitUID.equals(other.unitUID))
			return false;
		return true;
	}

}
