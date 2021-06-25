package com.example.demo.auth;

public enum ApplicationUserPermission {
    DEVICE_CAMERA_READ("device:camera:read"),
    DEVICE_CAMERA_WRITE("device:camera:write"),	
    USER_READ("user:read"),
    USER_WRITE("user:write");


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
