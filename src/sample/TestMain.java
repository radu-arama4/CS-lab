package sample;

import com.sun.deploy.util.WinRegistry;
import sample.util.registry.Registry2;

import java.lang.reflect.InvocationTargetException;
import java.util.prefs.Preferences;

public class TestMain {

    private static Preferences prefs;

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        prefs = Preferences.systemRoot().node("IctSocialNode");

//        SerializationDeserializationUtil.deserialize("first");

//        String value = Registry.readString(
//                WinRegistry.HKEY_LOCAL_MACHINE,                             //HKEY
//                "SYSTEM\\CurrentControlSet\\Services\\LanmanWorkstation\\Parameters",           //Key
//                "EnableSecuritySignature");                                              //ValueName
//        System.out.println(value);


//        String value = Registry.readString (
//                WinRegistry.HKEY_LOCAL_MACHINE,                             //HKEY
//                "SOFTWARE\\Microsoft\\Windows NT\\CurrentVersion",           //Key
//                "InstallDate");                                              //ValueName
//        System.out.println("Windows Distribution = " + value);


        System.out.println(Registry2.readRegistry("HKEY_LOCAL_MACHINE\\SYSTEM\\CurrentControlSet\\Services\\LanmanWorkstation\\Parameters", "EnableSecuritySignature"));

    }
}
