/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bcServer.bc37server.securities;


import static bcServer.bc37server.securities.ApplicationUserPermission.admin_read;
import static bcServer.bc37server.securities.ApplicationUserPermission.admin_write;
import static bcServer.bc37server.securities.ApplicationUserPermission.karyawan_read;
import static bcServer.bc37server.securities.ApplicationUserPermission.karyawan_write;
import static bcServer.bc37server.securities.ApplicationUserPermission.trainer_read;
import static bcServer.bc37server.securities.ApplicationUserPermission.trainer_write;
import com.google.common.collect.Sets;
import java.util.Set;

/**
 *
 * @author Laila
 */
public enum ApplicationUserRole {
   admin(Sets.newHashSet(karyawan_read, karyawan_write, admin_write, admin_read)),
   karyawan(Sets.newHashSet(karyawan_read)),
   trainer(Sets.newHashSet(trainer_write, trainer_read));
   
   private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
    public Set<ApplicationUserPermission> getPermissions(){
        return permissions;
    }
   
}
