/**
 *
 */
package de.nj.recipemanager.test;

import java.util.Locale;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import de.nj.recipemanager.model.ResourceBundleLocalisationProvider;
import de.nj.recipemanager.model.interfaces.LocalisationProvider;
import de.nj.recipemanager.test.dummy.DummyPresenterModelCallback;
import junit.framework.TestCase;

/**
 * @author Nico
 * @date 29.03.2017
 *
 */
public class TestLanguageProvider extends TestCase
{

    @BeforeClass
    public static void setup()
    {}

    @AfterClass
    public static void teardown()
    {}

    @Test
    public static void testLoadingLanguageFiles()
    {
        LocalisationProvider provider = new ResourceBundleLocalisationProvider("language", Locale.GERMAN, new DummyPresenterModelCallback());

        assertEquals("LocalisationProvider does not provide correct localisation", "Einheit", provider.valueFor("ui.table.unitofquanity"));
        assertEquals("LocalisationProvider does not provide correct localisation", "Menge", provider.valueFor("ui.table.amount"));

        provider.setLocale(Locale.ENGLISH);

        assertEquals("LocalisationProvider does not provide correct localisation", "Unit", provider.valueFor("ui.table.unitofquanity"));
        assertEquals("LocalisationProvider does not provide correct localisation", "Amount", provider.valueFor("ui.table.amount"));
    }

}
