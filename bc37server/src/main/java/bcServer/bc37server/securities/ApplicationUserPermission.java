/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bcServer.bc37server.securities;

/**
 *
 * @author Laila
 */
public enum ApplicationUserPermission {
    karyawan_read("karyawan:read"),
    karyawan_write("karyawan:write"),
    admin_read("admin:read"),
    admin_write("admin:write"),
    trainer_read("trainer:read"),
    trainer_write("trainer:write");
    ;
    private final String permission;

    private ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
    
    
}
