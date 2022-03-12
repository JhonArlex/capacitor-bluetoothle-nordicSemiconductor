package com.jhonocampo.capacitorbluetoothlenordic;

import android.Manifest;

import androidx.annotation.NonNull;

import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.support.v18.scanner.ScanCallback;
import no.nordicsemi.android.support.v18.scanner.ScanResult;

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
    BluetoothLeScannerCompat scanner = BluetoothLeScannerCompat.getScanner();
    int manufacturerId = 0;
    public ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, @NonNull ScanResult result) {
            super.onScanResult(callbackType, result);
            byte[] manufacturerData = result.getScanRecord().getManufacturerSpecificData(manufacturerId);
            String data = byteArrayToHexString(manufacturerData);
            if (data != null) {
                JSObject json = new JSObject();
                json.put("addres", result.getDevice().getAddress());
                json.put("manufacturerData", data);
                notifyListeners("readBeacon", json);
            }

        }
    };
    @PluginMethod
    public void scan(PluginCall call) {
        manufacturerId = call.getInt("manufacturerId");
        if (getPermissionState("location") != PermissionState.GRANTED) {
            requestPermissionForAlias("location", call, "locationPermsCallback");
        } else {
            scan();
        }
    }

    @PluginMethod
    public void stop(PluginCall call) {
        stop();
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

    public static String byteArrayToHexString(final byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes){
            sb.append(String.format("%02x", b&0xff));
        }
        return sb.toString();
    }


    public void scan() {
        scanner.startScan(null, null, scanCallback);
    }

    public void stop() {
        scanner.stopScan(scanCallback);
    }
}
