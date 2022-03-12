export interface BluetoothLeNordicPlugin {
  scan(option: {manufacturerId: number}): Promise<{value: string}>;
  stop(): Promise<{value: string}>;
}
