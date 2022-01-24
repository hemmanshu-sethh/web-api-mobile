package com.automation.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigReader {
	
	private String PageName;
	private static Logger logger = LogManager.getLogger();
	Map<String, Map<String, Map<String, String>>> testEnvMap = new HashMap<>();
	public static  Map<String, String> testEnv = new HashMap<String, String>();

	String tier, platform;



	
	public Map<String, String> getTestEnv() throws FileNotFoundException {

		

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
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+testEnv.toString());

		}

		return testEnv;

	}

	private String getseleniumserverhost() {
		String seleniumserverhost = testEnvMap.get("Execution").get("environment").get("seleniumserverhost").toString();
		logger.debug("Test will be executed on machine " + seleniumserverhost);
		return seleniumserverhost;		
	}

	private String gettimeout() {
//		String timeout = testEnvMap.get("Execution").get("environment").get("timeout").toString();
//		logger.debug("Test will be executed on machine " + timeout);
		return "10";		
	}

	private String getAndroidDeviceName() {
		String androideviceName = testEnvMap.get("Execution").get("android").get("device").toString();
		logger.debug("Test will be executed on Android Device " + androideviceName);
		return androideviceName;
	}

	private String getAndroidVersion() {
		
		String androidversion = 		String.valueOf(testEnvMap.get("Execution").get("android").get("version"));
		logger.debug("Test will be executed on Android Device " + androidversion);
		return (androidversion);
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

	public String getPageElementsMetaData(String PageName, String ElementName, String attribute) {
		Map<String, Map<String, Map<String, String>>> temp = new HashMap();
		temp = (Map<String, Map<String, Map<String, String>>>) ReadFileTestResources(PageName);
		return temp.get("elements").get(ElementName).get(attribute);
	}
	
	public String getElementsLabel(String PageName, String ElementName) {
		Map<String, Map<String, Map<String, String>>> temp = new HashMap();
		temp = (Map<String, Map<String, Map<String, String>>>) ReadFileTestResources(PageName);
		return temp.get("elements").get(ElementName).get("label");
	}
	
	public static String getElementsTestData(String PageName, String ElementName) {
		Map<String, Map<String, Map<String, String>>> temp = new HashMap();
		temp = (Map<String, Map<String, Map<String, String>>>) ReadFileTestResources(PageName);
		return temp.get("elements").get(ElementName).get("testdata");
	}

	public Map<String, String> getElementsIdentifier(String PageName, String ElementName) {
		 Map<String, String> identifier=new HashMap();
		String Platform= ConfigReader.testEnv.get("platform");
		Map<String, Map<String, Map<String, Map<String, Map<String, Map<String, List<String>>>>>>> PageSpec = (Map<String, Map<String, Map<String, Map<String, Map<String, Map<String, List<String>>>>>>>) ReadFileTestResources(
				PageName);

		ArrayList<Map<String, Object>> test1 = (ArrayList<Map<String, Object>>) PageSpec.get("elements")
				.get(ElementName).get("identifier");
		

		int val = 0;
		logger.debug("*************** Searching Identifier for element " + ElementName+"***************");
		String type=null,value=null,platform;
		while (test1.size() > val) {
			List<String> t = (List<String>) test1.get(val).get("platform");
			
			if(containsCaseInsensitive(Platform,t))
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
	public boolean containsCaseInsensitive(String s, List<String> l){
	     for (String string : l){
	        if (string.equalsIgnoreCase(s)){
	            return true;
	         }
	     }
	    return false;
	  }

	private Map<String, Map<String, Map<String, Map<String, Map<String, Map<String, ArrayList<String>>>>>>> readPageSpecs(
			String PageName) {

		Map<String, Map<String, Map<String, Map<String, Map<String, Map<String, ArrayList<String>>>>>>> PageSpec;
		PageSpec = (Map<String, Map<String, Map<String, Map<String, Map<String, Map<String, ArrayList<String>>>>>>>) ReadFileTestResources(
				PageName);
		return PageSpec;

	}

	private static Object ReadFileTestResources(String filename) {
		System.out.println("The file I am processing"+filename);
		Object filecontents = null;
		
		try {
			
			InputStream inputStream       = new FileInputStream("src/test/resources//"+filename + ".yml");

			InputStreamReader reader   = new InputStreamReader(inputStream);

			Yaml yaml = new Yaml();
			filecontents = yaml.load(reader);

		} catch (Exception e) {
			e.printStackTrace();
		}
		

		return filecontents;
	}

}
