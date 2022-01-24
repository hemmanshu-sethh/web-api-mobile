package com.automation.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigReader {

    private static final Logger logger = LogManager.getLogger();
    Map<String, Map<String, Map<String, String>>> testEnvMap = new HashMap<>();
    public static Map<String, String> testEnv = new HashMap<>();
    CommonUtils utils = new CommonUtils();
    String tier, platform;


    public Map<String, String> getTestEnv() {


        logger.debug("Initializing to Read Test Environment");
        readTestEnv();
        testEnv.put("tier", getTier());
        testEnv.put("platform", getPlatform());
        testEnv.put("browser", getBrowser());
        testEnv.put("baseURL", getbaseURL());
        testEnv.put("seleniumserver", getseleniumserver());
        testEnv.put("seleniumserverhost", getseleniumserverhost());
        testEnv.put("timeout", gettimeout());

        if (platform.contains("android")) {
            testEnv.put("androideviceName", getAndroidDeviceName());
            testEnv.put("androidversion", getAndroidVersion());
            testEnv.put("androidautomation", getAndroidAutomation());

        }

        return testEnv;

    }

    private String getseleniumserverhost() {
        String seleniumserverhost = testEnvMap.get("Execution").get("environment").get("seleniumserverhost");
        logger.debug("Test will be executed on machine " + seleniumserverhost);
        return seleniumserverhost;
    }

    private String gettimeout() {
//		String timeout = testEnvMap.get("Execution").get("environment").get("timeout").toString();
//		logger.debug("Test will be executed on machine " + timeout);
        return "10";
    }

    private String getAndroidDeviceName() {
        String androideviceName = testEnvMap.get("Execution").get("android").get("device");
        logger.debug("Test will be executed on Android Device " + androideviceName);
        return androideviceName;
    }

    private String getAndroidVersion() {

        String androidversion = String.valueOf(testEnvMap.get("Execution").get("android").get("version"));
        logger.debug("Test will be executed on Android Device " + androidversion);
        return (androidversion);
    }

    private String getAndroidAutomation() {
        String androidautomation = testEnvMap.get("Execution").get("android").get("automation");
        logger.debug("Test will be executed on Android Device " + androidautomation);
        return androidautomation;
    }

    private String getseleniumserver() {
        String seleniumserver = testEnvMap.get("Execution").get("environment").get("seleniumserver");
        logger.debug("Test will be executed on machine " + seleniumserver);
        return seleniumserver;
    }

    private String getbaseURL()  {
        if (platform.equals("api"))
            return (testEnvMap.get("URLs").get(tier).get(platform));
        else
            return (testEnvMap.get("URLs").get(tier).get("web"));
    }

    private String getTier()  {
        tier = testEnvMap.get("Execution").get("Config").get("tier");
        logger.debug("Test will be executed on " + tier + " environment");
        return (tier);
    }

    private String getPlatform()  {
        platform = testEnvMap.get("Execution").get("Config").get("platform");
        logger.debug("Test will be executed on platform " + platform);
        return platform;

    }

    private String getBrowser()  {
        String browser = testEnvMap.get("Execution").get("Config").get("browser");
        logger.debug("Test will be executed on browser " + browser);
        return browser;
    }

    @SuppressWarnings("unchecked")
    private void readTestEnv() {

        testEnvMap = (Map<String, Map<String, Map<String, String>>>) ReadFileTestResources("TestEnv");
    }



    @SuppressWarnings("unchecked")
    public String getPageTitle(String PageName) {
        Map<String, String> temp;
        temp = (Map<String, String>) ReadFileTestResources("PageSpecs/"+PageName);
        return temp.get("Title");

    }

	public String getPageElementsMetaData(String PageName, String ElementName, String attribute) {
		Map<String, Map<String, Map<String, String>>> temp;
		temp = (Map<String, Map<String, Map<String, String>>>) ReadFileTestResources("PageSpecs/"+PageName);
		return temp.get("elements").get(ElementName).get(attribute);
	}
	
	public String getElementsLabel(String PageName, String ElementName) {
        Map<String, Map<String, Map<String, String>>> temp = (Map<String, Map<String, Map<String, String>>>) ReadFileTestResources("PageSpecs/"+PageName);
		return temp.get("elements").get(ElementName).get("label");
	}
	
	public static String getElementsTestData(String PageName, String ElementName) {
		Map<String, Map<String, Map<String, String>>> temp = new HashMap();
		temp = (Map<String, Map<String, Map<String, String>>>) ReadFileTestResources("PageSpecs/"+PageName);
		return temp.get("elements").get(ElementName).get("testdata");
	}

    public Map<String, String> getElementsIdentifier(String PageName, String ElementName) {
        Map<String, String> identifier = new HashMap();
        String Platform = ConfigReader.testEnv.get("platform");
        Map<String, Map<String, Map<String, Map<String, Map<String, Map<String, List<String>>>>>>> PageSpec = (Map<String, Map<String, Map<String, Map<String, Map<String, Map<String, List<String>>>>>>>) ReadFileTestResources(
                "PageSpecs/"+PageName);

        ArrayList<Map<String, Object>> test1 = (ArrayList<Map<String, Object>>) PageSpec.get("elements")
                .get(ElementName).get("identifier");


        int val = 0;
        logger.debug("*************** Searching Identifier for element " + ElementName + "***************");
        String type = null, value = null, platform;
        while (test1.size() > val) {
            List<String> t = (List<String>) test1.get(val).get("platform");

            if (utils.containsCaseInsensitive(Platform, t)) {
                logger.debug("Platform found for element" + test1.get(val));
                type = test1.get(val).get("type").toString();
                value = test1.get(val).get("value").toString();

                identifier.put("type", type);
                identifier.put("value", value);
            }
            val++;
        }

        if (identifier.size() == 0) {
            logger.debug("************************No Identifier found for element********************************************");
        } else {
            logger.debug("Element Identifer Type " + type);
            logger.debug("Element Identifer Value " + value);
        }
        logger.debug("********************************************************************");

        return identifier;

    }




    private static Object ReadFileTestResources(String filename) {
        Object filecontents = null;

        try {

            InputStream inputStream = new FileInputStream("src/test/resources//" + filename + ".yml");
            InputStreamReader reader = new InputStreamReader(inputStream);

            Yaml yaml = new Yaml();
            filecontents = yaml.load(reader);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return filecontents;
    }

}
