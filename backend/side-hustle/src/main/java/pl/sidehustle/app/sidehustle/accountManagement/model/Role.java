package pl.sidehustle.app.sidehustle.accountManagement.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "roles")
@Access(AccessType.FIELD)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    // default false
    @Column(name = "admin_access")
    private boolean adminAccess;

    @Column(name = "is_provider")
    private boolean isProvider;

    @Column(name = "is_customer")
    private boolean isCustomer;

    @OneToMany(mappedBy = "roleId", fetch = FetchType.LAZY)
    private Set<User> users;

    public Role() {
    }

    public String getRoleLevel(){
        if (this.adminAccess){
            return RoleLevel.ADMIN.toString();
        } else if (this.isProvider){
            return RoleLevel.PROVIDER.toString();
        } else {
            return RoleLevel.CUSTOMER.toString();
        }
    }
}
