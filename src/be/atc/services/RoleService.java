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
		Role roleTarget = new Role();
		
		return modifyRole(roleTarget, role);
	}
	
	public void removeRole(Role role){
		//si en argument int idRole
		//Role roleSrv = new Role(); //Role.findRole(idRole);
		if (em.find(Role.class, role.getIdRole()) != null)
			em.remove(role);
	}
	
	public Role modifyRole(Role roleTarget, Role roleNew){
		roleTarget = em.find(Role.class, roleTarget.getIdRole());
		String roleNewName = roleNew.getRoleName();
		
		if(roleTarget != null & roleNewName.length() < ROLE_LENGTH_LIMIT){
			roleTarget.setIdRole(roleNew.getIdRole());
			roleTarget.setIsActive(roleNew.getIsActive());
			roleTarget.setRoleName(roleNewName);
			roleTarget.setUsers(roleNew.getUsers());
		}
		
		return roleTarget;
	}
	
	public Role findRole(Role role){
		return em.find(Role.class, role.getIdRole());
		
	}
	
	public List<Role> findAllRoles(){
		javax.persistence.TypedQuery<Role> query = (javax.persistence.TypedQuery<Role>)
										em.createQuery("SELECT r FROM Role r", Role.class);
		return query.getResultList();
	}
}