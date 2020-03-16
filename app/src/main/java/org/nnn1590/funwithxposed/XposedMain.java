package org.nnn1590.funwithxposed;

import android.content.res.XModuleResources;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

public class XposedMain implements IXposedHookZygoteInit, IXposedHookInitPackageResources {
    private String modulePath = null;

    @Override
    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) throws Throwable {
        modulePath = startupParam.modulePath;
    }

    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {
        XModuleResources xModuleResources = XModuleResources.createInstance(modulePath, resparam.res);
        if (resparam.packageName.equals(BuildConfig.APPLICATION_ID)) {
            resparam.res.setReplacement(BuildConfig.APPLICATION_ID, "string", "current_status_disabled", xModuleResources.getString(R.string.current_status_enabled));
        }
        if (resparam.packageName.equals("com.android.settings")){
            resparam.res.setReplacement("com.android.settings","string","settings_label", xModuleResources.getString(R.string.current_status_enabled));
        }
    }
}
