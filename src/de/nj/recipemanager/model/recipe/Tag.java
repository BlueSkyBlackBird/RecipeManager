/**
 * 
 */
package de.nj.recipemanager.model.recipe;

/**
 * @author Nico
 * @date 20 Mar 2014
 * 
 */
public class Tag
{
	private String tagName;

	/**
	 * This is the default constructor of this class.
	 *
	 * @param tagName
	 */
	public Tag(String tagName)
	{
		this.tagName = tagName;
	}

	public String getTagName()
	{
		return tagName;
	}

	public void setTagName(String tagName)
	{
		this.tagName = tagName;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
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
		Tag other = (Tag) obj;
		if (tagName == null)
		{
			if (other.tagName != null)
				return false;
		}
		else if (!tagName.equals(other.tagName))
			return false;
		return true;
	}
}
