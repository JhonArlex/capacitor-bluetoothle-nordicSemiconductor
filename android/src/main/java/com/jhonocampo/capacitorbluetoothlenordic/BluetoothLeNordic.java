package com.jhonocampo.capacitorbluetoothlenordic;

import android.util.Log;

import androidx.annotation.NonNull;

import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.support.v18.scanner.ScanCallback;
import no.nordicsemi.android.support.v18.scanner.ScanResult;

public class BluetoothLeNordic {
    BluetoothLeScannerCompat scanner = BluetoothLeScannerCompat.getScanner();

    public void scan() {
        scanner.startScan(null, null, scanCallback);
    }

    public void stop() {
        scanner.stopScan(scanCallback);
    }

    public ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, @NonNull ScanResult result) {
            super.onScanResult(callbackType, result);
            System.out.println(result.getDevice().getName());
            System.out.println(byteArrayToHexString(result.getScanRecord().getManufacturerSpecificData().valueAt(0)));
        }
    };

    public static String byteArrayToHexString(final byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes){
            sb.append(String.format("%02x", b&0xff));
        }
        return sb.toString();
    }
}
