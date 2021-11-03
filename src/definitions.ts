export interface BluetoothLeNordicPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
