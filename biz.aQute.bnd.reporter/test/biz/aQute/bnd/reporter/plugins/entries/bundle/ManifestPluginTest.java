package biz.aQute.bnd.reporter.plugins.entries.bundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import aQute.bnd.osgi.Jar;
import aQute.bnd.osgi.Processor;
import aQute.bnd.service.reporter.ReportEntryPlugin;
import java.util.Locale;
import java.util.Map;
import java.util.jar.Manifest;
import org.junit.Test;

public class ManifestPluginTest {

  @Test
  public void testManifestEntryPlugin() {
    final ManifestPlugin plugin = new ManifestPlugin();
    Jar jar = new Jar("jar");
    Manifest manifest = new Manifest();
    jar.setManifest(manifest);
    manifest.getMainAttributes().putValue("Bundle-Name", "test");
    Processor p = new Processor();
    plugin.setReporter(p);
    Object result;
    result = plugin.extract(jar, Locale.forLanguageTag("und"));

    assertTrue(p.isOk());
    assertTrue(((Map<?, ?>) result).size() > 0);

    jar = new Jar("jar");
    p = new Processor();
    plugin.setReporter(p);
    result = plugin.extract(jar, Locale.forLanguageTag("und"));

    assertTrue(p.isOk());
    assertEquals(null, result);

    jar = new Jar("jar");
    manifest = new Manifest();
    jar.setManifest(manifest);
    p = new Processor();
    plugin.setReporter(p);
    result = plugin.extract(jar, Locale.forLanguageTag("und"));

    assertTrue(p.isOk());
    assertEquals("manifest", plugin.getProperties().get(ReportEntryPlugin.ENTRY_NAME_PROPERTY));
  }
}
