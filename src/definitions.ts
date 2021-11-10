export interface BluetoothLeNordicPlugin {
  scan(option: {value: string}): Promise<{value: string}>;
  stop(): Promise<{value: string}>;
}
