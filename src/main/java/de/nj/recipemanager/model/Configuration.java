/**
 * 
 */
package de.nj.recipemanager.model;

import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.Properties;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.apache.log4j.Logger;
import de.nj.recipemanager.presenter.FileIOHandler;

/**
 * @author Nico
 * @date 29.03.2017
 * 
 */
public class Configuration
{
	private static final Logger	logger	= Logger.getLogger(Configuration.class);

	protected Properties		programProperties;

	protected Properties		userDefaultProperties;

	protected Preferences		userPreference;

	public Configuration()
	{
		userDefaultProperties = loadProperties("user.properties");
		programProperties = loadProperties("config.properties");
		userPreference = loadPreferences(programProperties.getProperty("config.userprefpath"), userDefaultProperties);
	}

	protected Preferences loadPreferences(String path, Properties propWithdefaultValues)
	{
		Preferences pref = FileIOHandler.loadAndCreateUserPreferenceIfNecesarry(path);
		propWithdefaultValues.forEach((k, v) -> propWithdefaultValues.put(k, pref.get(String.valueOf(k), String.valueOf(v))));
		try
		{
			pref.flush();
		}
		catch (BackingStoreException e)
		{
			logger.error("Exception encountered while loading preferences from path '" + path + "'", e);
			throw new FileSystemNotFoundException(e.getMessage());
		}
		return pref;
	}

	protected Properties loadProperties(String path)
	{
		try
		{
			return FileIOHandler.loadPropertiesInsideJar(path);
		}
		catch (IOException e)
		{
			logger.error("Exception encountered while loading properties from path '" + path + "'", e);
			throw new FileSystemNotFoundException(e.getMessage());
		}
	}

	public String getUserConfig(String key)
	{
		String defaultValue = userDefaultProperties.getProperty(key);
		return userPreference.get(key, defaultValue);
	}

	public String getProgramConfig(String key)
	{
		return programProperties.getProperty(key);
	}

	public void setUserConfig(String key, String value) throws BackingStoreException
	{
		userPreference.put(key, value);
		save();
	}


	protected void save() throws BackingStoreException
	{
		try
		{
			userPreference.flush();
		}
		catch (BackingStoreException e)
		{
			logger.error("Unable to save Configuration", e);
			throw e;

		}
	}
}
