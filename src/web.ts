import { WebPlugin } from '@capacitor/core';

import type { BluetoothLeNordicPlugin } from './definitions';

export class BluetoothLeNordicWeb extends WebPlugin implements BluetoothLeNordicPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
