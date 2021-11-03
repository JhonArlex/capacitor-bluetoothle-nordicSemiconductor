import { registerPlugin } from '@capacitor/core';

import type { BluetoothLeNordicPlugin } from './definitions';

const BluetoothLeNordic = registerPlugin<BluetoothLeNordicPlugin>('BluetoothLeNordic', {
  web: () => import('./web').then(m => new m.BluetoothLeNordicWeb()),
});

export * from './definitions';
export { BluetoothLeNordic };
