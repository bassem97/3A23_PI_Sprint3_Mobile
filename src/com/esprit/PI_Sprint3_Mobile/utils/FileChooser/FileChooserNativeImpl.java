package com.esprit.PI_Sprint3_Mobile.utils.FileChooser;

public class FileChooserNativeImpl implements FileChooserNative{
    @Override
    public boolean showNativeChooser(String accept, boolean multi) {
        return true;
    }

    @Override
    public boolean isSupported() {
        return true;
    }
}
