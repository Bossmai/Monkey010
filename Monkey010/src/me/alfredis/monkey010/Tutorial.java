package me.alfredis.monkey010;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import android.graphics.Color;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import de.robv.android.xposed.XposedBridge;

public class Tutorial implements IXposedHookLoadPackage {
	private HashMap<String, String> configMap = new HashMap<String, String>();
	
	public Tutorial() {
		try {
			FileReader fr = new FileReader("/data/data/me.alfredis.monkey010/monkey010.conf");
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				String line = br.readLine();
                if (line == null) {
                    break;
                }
                
                line = line.substring(1, line.length() - 2);
                String[] lineArray = line.split(",");
                for (String str : lineArray) {

                	String[] tempKVP = str.split(":"); 
                	String tempKey = tempKVP[0].substring(1, tempKVP[0].length() - 1);
                	String tempValue = "";
                	if (tempKVP[1].startsWith("\"")) {
                		tempValue = tempKVP[1].substring(1, tempKVP[1].length() - 1);
                	} else {
                		tempValue = tempKVP[1];
                	}


                    XposedBridge.log(tempKVP[0] + " " + tempKey);
                    configMap.put(tempKey, tempValue);
                }
			}
			
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			XposedBridge.log(e.getMessage());
		} catch (IOException e) {
			XposedBridge.log(e.getMessage());
		} 
	}
	
	public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
		//XposedBridge.log("Loaded app: " + lpparam.packageName);
		
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getDeviceId", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getDeviceId"));
            }
        });
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getSimOperatorName", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getSimOperator"));
            }
        });
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getSimSerialNumber", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getSimSerialNumber"));
            }
        });
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getSubscriberId", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getSubscriberId"));
            }
        });
        
	}
}
