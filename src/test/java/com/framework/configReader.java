package com.framework;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class configReader {

    private static Logger logger = LogManager.getLogger();
    public static Map <String, String> testEnv= new HashMap<String, String>();
    Map<String, Map<String, Map<String, String>>> testEnvMap= new HashMap<>();
    String tier,platform;
    
    public void getTestEnv() throws FileNotFoundException
    {
    		logger.debug("Initializing to Read Test Environment");
    		readTestEnv();
    		testEnv.put("tier",getTier());
    		testEnv.put("platform", getPlatform());
    		testEnv.put("browser",getBrowser() );
    		testEnv.put("baseURL", getbaseURL());

    }
    
    
    private  String getbaseURL() throws FileNotFoundException {	             
    	return(testEnvMap.
    				get("URLs").
    				get(tier).
    				get(platform).
    				toString());
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
