package org.pentaho.platform.plugin.services.importer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Test;
import org.pentaho.platform.plugin.services.importexport.IRepositoryImportLogger;
import org.pentaho.platform.plugin.services.importexport.Log4JRepositoryImportLogger;

/**
 * User: nbaker
 * Date: 6/13/12
 */
public class PlatformImporterTest {



  @Test
  public void testNoMatchingMime() throws Exception {
    Map<String, IPlatformImportHandler> handlers = new HashMap<String, IPlatformImportHandler>();

    Map<String, String> mimes = new HashMap<String, String>();
    PentahoPlatformImporter importer = new PentahoPlatformImporter(handlers, new NameBaseMimeResolver(mimes));

    FileInputStream in = new FileInputStream(new File("test-res/ImportTest/steel-wheels.xmi"));
    
    
    Log4JRepositoryImportLogger importLogger = new Log4JRepositoryImportLogger();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    importLogger.startJob(outputStream, "", Level.DEBUG);

    // With custom domain id
    final IPlatformImportBundle bundle1 = (new RepositoryFileImportBundle.Builder().input(in).charSet("UTF-8").hidden(false).name("steel-wheels.xmi").comment("Test Metadata Import").withParam("domain-id", "parameterized-domain-id")).build();

    try{
      importer.setRepositoryImportLogger(importLogger);
      importer.importFile(bundle1);
      String result = new String(outputStream.toByteArray());
      Assert.assertTrue(result.contains("PentahoPlatformImporter.ERROR_0001_INVALID_MIME_TYPE"));
    } catch(PlatformImportException e){
      e.printStackTrace();
      return;
    }
    importLogger.endJob();
   }


  @Test
  public void testMatchingMimeAndHandler() throws Exception {
    Map<String, IPlatformImportHandler> handlers = new HashMap<String, IPlatformImportHandler>();

    Mockery context = new Mockery();

    final IPlatformImportHandler Handler = context.mock(IPlatformImportHandler.class);
    handlers.put("text/xmi+xml", Handler);

    // mock logger to prevent npe
    IRepositoryImportLogger importLogger = new Log4JRepositoryImportLogger();

    Map<String, String> mimes = new HashMap<String, String>();
    mimes.put("xmi", "text/xmi+xml");
    PentahoPlatformImporter importer = new PentahoPlatformImporter(handlers, new NameBaseMimeResolver(mimes));
    importer.setRepositoryImportLogger(importLogger);

    FileInputStream in = new FileInputStream(new File("test-res/ImportTest/steel-wheels.xmi"));

    // With custom domain id
    final IPlatformImportBundle bundle1 = (new RepositoryFileImportBundle.Builder().input(in).charSet("UTF-8").hidden(false).mime("text/xmi+xml").name("steel-wheels.xmi").comment("Test Metadata Import").withParam("domain-id", "parameterized-domain-id")).build();

    context.checking(new Expectations() {{
      oneOf(Handler).importFile(bundle1);
    }});

    importer.importFile(bundle1);
    context.assertIsSatisfied();
  }

  @Test
  public void testNoMatchingHandler() throws Exception {
    Map<String, IPlatformImportHandler> handlers = new HashMap<String, IPlatformImportHandler>();

    Map<String, String> mimes = new HashMap<String, String>();
    mimes.put("xmi", "text/xmi+xml");
    PentahoPlatformImporter importer = new PentahoPlatformImporter(handlers, new NameBaseMimeResolver(mimes));

    FileInputStream in = new FileInputStream(new File("test-res/ImportTest/steel-wheels.xmi"));

    // With custom domain id
    final IPlatformImportBundle bundle1 = (new RepositoryFileImportBundle.Builder().input(in).charSet("UTF-8").hidden(false).name("steel-wheels.xmi").comment("Test Metadata Import").withParam("domain-id", "parameterized-domain-id")).build();

    try{
      importer.importFile(bundle1);
    } catch(PlatformImportException e){
      e.printStackTrace();
      return;
    }
    Assert.fail("should have failed resolving a handler");
  }


}
