import { WebPlugin } from '@capacitor/core';

import type { BluetoothLeNordicPlugin } from './definitions';

export class BluetoothLeNordicWeb extends WebPlugin implements BluetoothLeNordicPlugin {
  start(option: { value: string; }): Promise<{ value: string; }> {
    throw new Error('Method not implemented.');
  }
  stop(): Promise<{ value: string; }> {
    throw new Error('Method not implemented.');
  }
}
