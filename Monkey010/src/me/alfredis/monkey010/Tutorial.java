package me.alfredis.monkey010;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import android.graphics.Color;
import android.net.wifi.WifiInfo;
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
                	
                	int count = 0;
                	for (int i = 0; i < str.length(); i++) {
                		if (str.charAt(i) == ':') {
                			count++;
                		}                		
                	}
                	
                	if (tempKVP[1].startsWith("\"")) {
                		if (count > 1) { 
                    		tempValue = str.substring(str.indexOf(':') + 2, str.length() - 1);
                		} else {
                    		tempValue = tempKVP[1].substring(1, tempKVP[1].length() - 1);
                		}
                	} else {
                		tempValue = tempKVP[1];
                	}


                    XposedBridge.log(tempKey + " " + tempValue);
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
		
		findAndHookMethod(WifiInfo.class.getName(), lpparam.classLoader, "getBSSID", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getBSSID"));
            }
        });
		
		findAndHookMethod(WifiInfo.class.getName(), lpparam.classLoader, "getMacAddress", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getMacAddress"));
            }
        });
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getNetworkCountryIso", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getNetworkCountryIso"));
            }
        });
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getNetworkOperator", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getSimOperator"));
            }
        });
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getNetworkOperatorName", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getNetworkOperatorName"));
            }
        });
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getLine1Number", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getLine1Number"));
            }
        });
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getSubscriberId", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getSubscriberId"));
            }
        });
		
		findAndHookMethod(WifiInfo.class.getName(), lpparam.classLoader, "getSSID", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getSSID"));
            }
        });
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getSimCountryIso", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getSimCountryIso"));
            }
        });
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getSimOperator", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getSimOperator"));
            }
        });
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getSimOperatorName", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getSimOperatorName"));
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
