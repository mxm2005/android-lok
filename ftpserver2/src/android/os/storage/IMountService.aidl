package android.os.storage;
interface IMountService
{
boolean isUsbMassStorageConnected();
void setUsbMassStorageEnabled(boolean enable);
boolean isUsbMassStorageEnabled();
int mountVolume(java.lang.String mountPoint);
void unmountVolume(java.lang.String mountPoint, boolean force);
int formatVolume(java.lang.String mountPoint);
int[] getStorageUsers(java.lang.String path);
java.lang.String getVolumeState(java.lang.String mountPoint);
int createSecureContainer(java.lang.String id, int sizeMb, java.lang.String fstype, java.lang.String key, int ownerUid);
int finalizeSecureContainer(java.lang.String id);
int destroySecureContainer(java.lang.String id, boolean force);
int mountSecureContainer(java.lang.String id, java.lang.String key, int ownerUid);
int unmountSecureContainer(java.lang.String id, boolean force);
boolean isSecureContainerMounted(java.lang.String id);
int renameSecureContainer(java.lang.String oldId, java.lang.String newId);
java.lang.String getSecureContainerPath(java.lang.String id);
java.lang.String[] getSecureContainerList();
void finishMediaUpdate();
}
