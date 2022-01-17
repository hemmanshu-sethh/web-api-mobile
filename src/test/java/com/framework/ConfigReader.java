package com.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigReader {
	
	private static Logger logger = LogManager.getLogger();
	public static Map<String, Map<String, Map<String, String>>> testEnvMap = new HashMap<>();

	String tier, platform;

	public ConfigReader() {
		
		
	}
	
	public Map<String, String> getTestEnv() throws FileNotFoundException {

		

		Map<String, String> testEnv = new HashMap<String, String>();
		logger.debug("Initializing to Read Test Environment");
		readTestEnv();
		testEnv.put("tier", getTier());
		testEnv.put("platform", getPlatform());
		testEnv.put("browser", getBrowser());
		testEnv.put("baseURL", getbaseURL());
		testEnv.put("seleniumserver", getseleniumserver());
		if (platform == "android") {
			testEnv.put("androideviceName", getAndroidDeviceName());
			testEnv.put("androidversion", getAndroidVersion());
			testEnv.put("androidautomation", getAndroidAutomation());
		}

		return testEnv;

	}

	private String getAndroidDeviceName() {
		String androideviceName = testEnvMap.get("Execution").get("android").get("device").toString();
		logger.debug("Test will be executed on Android Device " + androideviceName);
		return androideviceName;
	}

	private String getAndroidVersion() {
		String androidversion = testEnvMap.get("Execution").get("android").get("device").toString();
		logger.debug("Test will be executed on Android Device " + androidversion);
		return androidversion;
	}

	private String getAndroidAutomation() {
		String androidautomation = testEnvMap.get("Execution").get("android").get("automation").toString();
		logger.debug("Test will be executed on Android Device " + androidautomation);
		return androidautomation;
	}

	private String getseleniumserver() {
		String seleniumserver = testEnvMap.get("Execution").get("environment").get("seleniumserver").toString();
		logger.debug("Test will be executed on machine " + seleniumserver);
		return seleniumserver;
	}

	private String getbaseURL() throws FileNotFoundException {
		if (platform == "api")
			return (testEnvMap.get("URLs").get(tier).get(platform).toString());
		else
			return (testEnvMap.get("URLs").get(tier).get("web").toString());
	}

	private String getTier() throws FileNotFoundException {
		tier = testEnvMap.get("Execution").get("Config").get("tier").toString();
		logger.debug("Test will be executed on " + tier + " environment");
		return (tier);
	}

	private String getPlatform() throws FileNotFoundException {
		platform = testEnvMap.get("Execution").get("Config").get("platform").toString();
		logger.debug("Test will be executed on platform " + platform);
		return platform;

	}

	private String getBrowser() throws FileNotFoundException {
		String browser = testEnvMap.get("Execution").get("Config").get("browser").toString();
		logger.debug("Test will be executed on browser " + browser);
		return browser;
	}

	@SuppressWarnings("unchecked")
	private void readTestEnv() {

		testEnvMap = (Map<String, Map<String, Map<String, String>>>) ReadFileTestResources("TestEnv");
	}

	@SuppressWarnings("unchecked")
	public  String getPageTitle(String PageName) {
		Map<String, String> temp = new HashMap<String, String>();
		temp = (Map<String, String>) ReadFileTestResources(PageName);
		return temp.get("Title").toString();

	}

	private String getPageElementsMetaData(String PageName, String ElementName, String attribute) {
		Map<String, Map<String, Map<String, String>>> temp = new HashMap();
		temp = (Map<String, Map<String, Map<String, String>>>) ReadFileTestResources(PageName);
		return temp.get("elements").get(ElementName).get(attribute);
	}

	public Map<String, String> getElementsIdentifier(String PageName, String ElementName, String Platform) {
//		public Map<String, String> getElementsIdentifier(String PageName, String ElementName, String Platform) {

		Map<String, String> identifier = new HashMap();
		Map<String, Map<String, Map<String, Map<String, Map<String, Map<String, List<String>>>>>>> PageSpec = (Map<String, Map<String, Map<String, Map<String, Map<String, Map<String, List<String>>>>>>>) ReadFileTestResources(
				PageName);

		ArrayList<Map<String, Object>> test1 = (ArrayList<Map<String, Object>>) PageSpec.get("elements")
				.get(ElementName).get("identifier");
		

		int val = 0;
		logger.debug("*************** Searching Identifier for element " + ElementName+"***************");
		String type=null,value=null;
		while (test1.size() > val) {
			List<String> t = (List<String>) test1.get(val).get("platform");

			if(t.contains(Platform))
			{
				logger.debug("Platform found for element" + test1.get(val));
				 type = test1.get(val).get("type").toString();
				 value = test1.get(val).get("value").toString();
			
				identifier.put("type", type);
				identifier.put("value", value);
			}
			val++;
		}

			if(identifier.size()==0)
			{
			logger.debug("************************No Identifier found for element********************************************");
			}
			else
			{
				logger.debug("Element Identifer Type " + type);
				logger.debug("Element Identifer Value " + value);
			}
			logger.debug("********************************************************************");

	return identifier;
		
	}

	private Map<String, Map<String, Map<String, Map<String, Map<String, Map<String, ArrayList<String>>>>>>> readPageSpecs(
			String PageName) {

		Map<String, Map<String, Map<String, Map<String, Map<String, Map<String, ArrayList<String>>>>>>> PageSpec;
		PageSpec = (Map<String, Map<String, Map<String, Map<String, Map<String, Map<String, ArrayList<String>>>>>>>) ReadFileTestResources(
				PageName);
		return PageSpec;

	}

	private Object ReadFileTestResources(String filename) {
		System.out.println("The file I am processing"+filename);
		Object filecontents = null;
		InputStreamReader reader;
		try {
			reader = new InputStreamReader( getClass().getClassLoader().getResourceAsStream(filename + ".yml"));
			
			Yaml yaml = new Yaml();
			filecontents = yaml.load(reader);

		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return filecontents;
	}

}
