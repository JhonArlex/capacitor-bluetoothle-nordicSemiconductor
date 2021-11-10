import { WebPlugin } from '@capacitor/core';

import type { BluetoothLeNordicPlugin } from './definitions';

export class BluetoothLeNordicWeb extends WebPlugin implements BluetoothLeNordicPlugin {
  scan(_option: { value: string; }): Promise<{ value: string; }> {
    throw new Error('Method not implemented.');
  }
  stop(): Promise<{ value: string; }> {
    throw new Error('Method not implemented.');
  }
}
