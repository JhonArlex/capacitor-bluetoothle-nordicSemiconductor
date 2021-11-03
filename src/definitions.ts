export interface BluetoothLeNordicPlugin {
  start(option: {value: string}): Promise<{value: string}>;
  stop(): Promise<{value: string}>;
}
