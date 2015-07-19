package me.alfredis.monkey010;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;


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
                
                line = line.substring(1, line.length() - 1);
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


                    //XposedBridge.log(tempKey + " " + tempValue);
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
		XposedBridge.log("Loaded app: " + lpparam.packageName);
		
		
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
		
		de.robv.android.xposed.XposedHelpers.setStaticObjectField(Build.class, "BRAND", configMap.get("BRAND"));
		de.robv.android.xposed.XposedHelpers.setStaticObjectField(Build.class, "DEVICE", configMap.get("DEVICE"));
		de.robv.android.xposed.XposedHelpers.setStaticObjectField(Build.class, "FINGERPRINT", configMap.get("FINGERPRINT"));
		de.robv.android.xposed.XposedHelpers.setStaticObjectField(Build.class, "HARDWARE", configMap.get("HARDWARE"));
		de.robv.android.xposed.XposedHelpers.setStaticObjectField(Build.class, "MANUFACTURER", configMap.get("MANUFACTURER"));
		de.robv.android.xposed.XposedHelpers.setStaticObjectField(Build.class, "MODEL", configMap.get("MODEL"));
		de.robv.android.xposed.XposedHelpers.setStaticObjectField(Build.class, "PRODUCT", configMap.get("PRODUCT"));
		de.robv.android.xposed.XposedHelpers.setStaticObjectField(Build.VERSION.class, "RELEASE", configMap.get("RELEASE"));
		de.robv.android.xposed.XposedHelpers.setStaticObjectField(Build.VERSION.class, "SDK", configMap.get("SDK"));

		String archTempString = configMap.get("ARCH");
		de.robv.android.xposed.XposedHelpers.setStaticObjectField(Build.class, "CPU_ABI", archTempString.substring(0, archTempString.indexOf("_")));
		de.robv.android.xposed.XposedHelpers.setStaticObjectField(Build.class, "CPU_ABI2", archTempString.substring(archTempString.indexOf("_") + 1, archTempString.length()));

		//XposedHelpers.setFloatField(XposedHelpers.findClass("DisplayMetrics", lpparam.classLoader), "density", Float.valueOf(configMap.get("density")));
		
		
		findAndHookMethod(Display.class.getName(), lpparam.classLoader, "getMetrics", DisplayMetrics.class.getName(), new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		DisplayMetrics metrics = (DisplayMetrics) param.args[0];
        		metrics.density = Float.valueOf(configMap.get("density"));
        		metrics.densityDpi = Integer.valueOf(configMap.get("densityDpi"));
        		metrics.scaledDensity = Float.valueOf(configMap.get("scaledDensity"));
        		metrics.heightPixels = Integer.valueOf(configMap.get("getMetrics").substring(0, configMap.get("getMetrics").indexOf("x")));
        		metrics.widthPixels = Integer.valueOf(configMap.get("getMetrics").substring(configMap.get("getMetrics").indexOf("x") + 1));
        		metrics.xdpi = Float.valueOf(configMap.get("xdpi"));
        		metrics.ydpi = Float.valueOf(configMap.get("ydpi"));
        		param.setResult(metrics);
        		}
        });
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getSimState", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(Integer.valueOf(configMap.get("getSimState")));
            }
        });
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getNetworkType", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(Integer.valueOf(configMap.get("getNetworkType")));
            }
        });
		
		findAndHookMethod(TelephonyManager.class.getName(), lpparam.classLoader, "getPhoneType", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(Integer.valueOf(configMap.get("getPhoneType")));
            }
        });
		
		findAndHookMethod(Build.class.getName(), lpparam.classLoader, "getRadioVersion", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(configMap.get("getRadioVersion"));
            }
        });
		
		findAndHookMethod(GsmCellLocation.class.getName(), lpparam.classLoader, "getLac", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(Integer.parseInt(configMap.get("getJiZhan").substring(0, configMap.get("getJiZhan").indexOf("_"))));
            }
        });
		
		findAndHookMethod(GsmCellLocation.class.getName(), lpparam.classLoader, "getCid", new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		param.setResult(Integer.parseInt(configMap.get("getJiZhan").substring(configMap.get("getJiZhan").indexOf("_") + 1)));
            }
        });
		
		findAndHookMethod(Settings.Secure.class.getName(), lpparam.classLoader, "getString", ContentResolver.class.getName(), String.class.getName(), new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		if (((String) param.args[1]).endsWith("android_id")) {
            		param.setResult(configMap.get("getString"));
        		}
            }
        });
		
		Class systemPropertiesClass = null;
        Object systemProperties = null;
        Method systemPropertiesGetMethod = null;
        try {
            systemPropertiesClass = Class.forName("android.os.SystemProperties");
            systemProperties = systemPropertiesClass.newInstance();
            Class[] argClasses = new Class[2];
            argClasses[0] = String.class;
            argClasses[1] = String.class;
            systemPropertiesGetMethod = systemPropertiesClass.getMethod("get", argClasses);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        
		findAndHookMethod(systemPropertiesClass.getName(), lpparam.classLoader, systemPropertiesGetMethod.getName(), String.class.getName(), String.class.getName(), new XC_MethodHook() {
        	@Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        		super.afterHookedMethod(param);
        		if (((String) param.args[0]).endsWith("gsm.version.baseband") && ((String) param.args[1]).endsWith("no message")) {
            		param.setResult(configMap.get("get"));
        		}
            }
        });
		
	}
}
