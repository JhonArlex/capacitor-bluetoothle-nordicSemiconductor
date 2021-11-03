package com.jhonocampo.capacitorbluetoothlenordic;

import android.Manifest;

import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

@CapacitorPlugin(
    name = "BluetoothLeNordic",
    permissions = {
        @Permission(
            alias = "location",
            strings = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            }
        ),
        @Permission(
            alias = "bluetooth",
            strings = {
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN
            }
        )
    }
)
public class BluetoothLeNordicPlugin extends Plugin {

    private BluetoothLeNordic implementation = new BluetoothLeNordic();

    @PluginMethod
    public void scan(PluginCall call) {
        if (getPermissionState("location") != PermissionState.GRANTED) {
            requestPermissionForAlias("location", call, "locationPermsCallback");
        } else {
            implementation.scan();
        }
    }

    @PluginMethod
    public void stop(PluginCall call) {
        implementation.stop();
    }


    /**
     * Callbacks de todos los permisos
     * @param call
     */

    @PermissionCallback
    private void locationPermsCallback(PluginCall call) {
        if (getPermissionState("camera") == PermissionState.GRANTED) {
            scan(call);
        } else {
            call.reject("Permission is required to take a picture");
        }
    }
}
