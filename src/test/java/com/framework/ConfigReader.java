package com.framework;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ConfigReader {

    private static Logger logger = LogManager.getLogger();
    Map<String, Map<String, Map<String, String>>> testEnvMap= new HashMap<>();
    String tier,platform;
    
    public Map<String,String> getTestEnv() throws FileNotFoundException
    {
        	Map <String, String> testEnv= new HashMap<String, String>();
    		logger.debug("Initializing to Read Test Environment");
    		readTestEnv();
    		testEnv.put("tier",getTier());
    		testEnv.put("platform", getPlatform());
    		testEnv.put("browser",getBrowser() );
    		testEnv.put("baseURL", getbaseURL());
    		testEnv.put("seleniumserver", getseleniumserver());
    		if(platform=="android")
    		{
        		testEnv.put("androideviceName", getAndroidDeviceName());
        		testEnv.put("androidversion", getAndroidVersion());
        		testEnv.put("androidautomation", getAndroidAutomation());
    		}
    		
    		return testEnv;

    }
    
    private String getAndroidDeviceName() {
     	String androideviceName= testEnvMap.get("Execution").get("android").get("device").toString();
    		logger.debug("Test will be executed on Android Device "+androideviceName);
    		return androideviceName;
	}    
   
    private String getAndroidVersion() {
     	String androidversion= testEnvMap.get("Execution").get("android").get("device").toString();
    		logger.debug("Test will be executed on Android Device "+androidversion);
    		return androidversion;
	}    
    

    private String getAndroidAutomation() {
     	String androidautomation= testEnvMap.get("Execution").get("android").get("automation").toString();
    		logger.debug("Test will be executed on Android Device "+androidautomation);
    		return androidautomation;
	}    
    private String getseleniumserver() {
     	String seleniumserver= testEnvMap.get("Execution").get("environment").get("seleniumserver").toString();
    		logger.debug("Test will be executed on machine "+seleniumserver);
    		return seleniumserver;
	}


	private  String getbaseURL() throws FileNotFoundException {	  
		if(platform=="api")
			return(testEnvMap.get("URLs").get(tier).get(platform).toString());		
		else	
			return(testEnvMap.get("URLs").get(tier).get("web").toString());
    }
    
    private  String getTier() throws FileNotFoundException {	 
    	tier=testEnvMap.get("Execution").get("Config").get("tier").toString();
		logger.debug("Test will be executed on "+tier +" environment");
    	return(tier);
    }
    
    private  String getPlatform() throws FileNotFoundException {	               	
        platform= testEnvMap.get("Execution").get("Config").get("platform").toString();
		logger.debug("Test will be executed on platform "+platform);
		return platform;

    }    
    
    private  String getBrowser() throws FileNotFoundException {	               	
    	String browser= testEnvMap.get("Execution").get("Config").get("browser").toString();
		logger.debug("Test will be executed on browser "+browser);
		return browser;
    }
    
    private void readTestEnv()
    {
    	InputStreamReader reader;

        try {
            reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream("TestEnv.yml"));
            Yaml yaml = new Yaml();
            testEnvMap = (Map<String, Map<String, Map<String, String>>>) yaml.load(reader);

        } catch (Exception e) {
            e.printStackTrace();
        }    	
    	
    }


	

    
    
}
