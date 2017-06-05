/*

import be.atc.connection.EMF;
//...

//select u from User u where u.login=:login
public class Prof implements serializable{
	private static final long serialVersionUID = 1L;
	
	@notnull
	@pattern(regexp="[0-9](11)")
	@column(name="matricule_FWB")
	private string matriculeFWB;

	@temporal(TemporalType.DATE)
	private Date date_Naiss;
	@OptionalPattern(regexp=".+0.+[.][a-zA-Z]+")
	private String email;

	private String gsm1;

	private String gsm2;
	@NotNull
	@NotEmpty
	@Size(max=250)
	@Column(nullable = false, length=250)//250 is the maximum value possible here
	private String nom_P;
	@NotNull
	@NotEmpty
	@Size(max=250)
	@Column(nullable=false, length=250)//250 is the maximum value possible here
	private String prenom_P;

	private String rue;

	private String status;

	@Column(name="supp_log")
	private boolean suppLog;

	private String tel;
	
	//...
}

*/

/*******************/
/* nouveau fichier */
/*******************/

/*

public String createMaterial(){
	//get entity manager
	em=EMF.getEM();
	
	//call model
	em.getTransaction().begin();
	try{
		em.persist(materialNew);
		em.getTransaction().commit();
		log.debug("Creation " + materielNew + " to database: OK");
		
		//generate new list
		list.findAllMateriel();
		
		//add message
		//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Create mat...
	}
	finally {
		if(em.getTransaction().isActive()){
			em.getTransaction().rollback();
			log.error("Creation " + materielNew + " to database: Rollback...")
			//...
		}
	}
}

*/