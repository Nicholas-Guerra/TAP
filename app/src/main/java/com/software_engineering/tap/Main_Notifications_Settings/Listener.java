package com.software_engineering.tap.Main_Notifications_Settings;

public interface Listener {
    void onDialogDisplayed(boolean writer);
    void onDialogDismissed();
    void showToast(String message);
}
