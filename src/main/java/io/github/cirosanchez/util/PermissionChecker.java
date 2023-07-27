package io.github.cirosanchez.util;

import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.Permissions;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

public class PermissionChecker {
    public static boolean hasPermissionInServer(User user, Server server, PermissionType permissionType) {
    Permissions userPermissions = server.getPermissions(user);
    return userPermissions.getAllowedPermission().contains(permissionType);
}
}
