package me.alfredis.monkey010;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Binder;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodHook.MethodHookParam;

public class IoBridgeHook extends MethodHook {
    private Methods mMethod;

    private static final String mClassName = "libcore.io.IoBridge";

    private IoBridgeHook(Methods method) {
        super( mClassName, method.name());
        mMethod = method;
    }

    private enum Methods {
        open
    };

    @SuppressLint("InlinedApi")
    public static List<MethodHook> getMethodHookList() {
        List<MethodHook> methodHookList = new ArrayList<MethodHook>();
        methodHookList.add(new IoBridgeHook(Methods.open));

        return methodHookList;
    }

    @Override
    public void before(MethodHookParam param) throws Throwable {
        int uid = Binder.getCallingUid();
        if(uid > 10000 && uid < 99999){
            if(mMethod == Methods.open)
                if(param.args.length >= 1 && param.args[0] != null){
                    String path = (String) param.args[0];
                    if(path.equals("/system/build.prop")) {
                        param.args[0] = "/data/data/me.alfredis.monkey010/build.prop";
                    } else if(path.equals("/proc/version")) {
                        param.args[0] = "/data/data/me.alfredis.monkey010/version";
                    } else if(path.equals("/proc/cpuinfo")) {
                        param.args[0] = "/data/data/me.alfredis.monkey010/cpuinfo";
                    } else if(path.equals("/proc/cpuinfo_max_freq")) {
                        param.args[0] = "/data/data/me.alfredis.monkey010/cpuinfo_max_freq";
                    } else if(path.equals("/proc/cpuinfo_min_freq")) {
                        param.args[0] = "/proc/me.alfredis.monkey010/cpuinfo_min_freq";
                    } else if(path.equals("/data/data/me.alfredis.monkey010/scaling_cur_freq")) {
                        param.args[0] = "/data/data/me.alfredis.monkey010/scaling_cur_freq";
                    } else if(path.equals("/dev/socket/qemud")) {
                        param.args[0] = "/data/data/me.alfredis.monkey010/null";
                    } else if(path.equals("/dev/qemu_pipe")) {
                        param.args[0] = "/data/data/me.alfredis.monkey010/null";
                    } else if(path.equals("/system/lib/libc_malloc_debug_qemu.so")) {
                        param.args[0] = "/data/data/me.alfredis.monkey010/null";
                    } else if(path.equals("/sys/qemu_trace")) {
                        param.args[0] = "/data/data/me.alfredis.monkey010/null";
                    } else if(path.equals("/system/bin/qemu-props")) {
                        param.args[0] = "/data/data/me.alfredis.monkey010/null";
                    } else if(path.equals("/dev/socket/baseband_genyd")) {
                        param.args[0] = "/data/data/me.alfredis.monkey010/null";
                    } else if(path.equals("/sys/class/net/wlan0/address")) {
                        param.args[0] = "/data/data/me.alfredis.monkey010/address";
                    } else if(path.equals("/dev/socket/genyd")) {
                        param.args[0] = "/data/data/me.alfredis.monkey010/null";
                    }
                }
        }
    }
}
