package be.atc.services;
import java.util.List;

import javax.persistence.EntityManager;

import be.atc.entities.Role;


/**
 * projpa2 voir page 29
 * @author bertrand
 *
 */
public class RoleService {
	protected EntityManager em;
	private static final int ROLE_LENGTH_LIMIT = 50;

	public RoleService(EntityManager em){
		this.em = em;
	}
	
	public Role createRole(Role role){
		Role roleSrv = new Role();
		roleSrv.setIdRole(0);
		roleSrv.setIsActive(true);
		roleSrv.setRoleName("bertrand");
		roleSrv.setUsers(null);
		return roleSrv;
		
	}
	
	public void removeRole(int idRole){
		Role roleSrv = new Role(); //Role.findRole(idRole);
		if (roleSrv != null)
			em.remove(roleSrv);
	}
	
	public Role modifyRole(Role role, String roleName){
		role = em.find(Role.class, role.getIdRole());
		
		if(role != null & roleName.length() < ROLE_LENGTH_LIMIT){
			role.setRoleName(roleName);
		}
		
		return role;
	}
	
	public Role findRole(int idRole){
		return em.find(Role.class, idRole);
		
	}
	
	public List<Role> findAllRoles(){
		javax.persistence.TypedQuery<Role> query = (javax.persistence.TypedQuery<Role>) em.createQuery("SELECT r FROM Role r", Role.class);
		return query.getResultList();
	}
}