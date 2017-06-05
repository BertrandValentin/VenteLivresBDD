package be.atc.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the orderstatus database table.
 * 
 */
@Entity
@Table(name="orderstatus")
@NamedQuery(name="Orderstatus.findAll", query="SELECT o FROM Orderstatus o")
public class Orderstatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idOrderStatus;

	@Column(nullable=false, length=50)
	private String orderStatusName;

	//bi-directional many-to-one association to Orderstatushistory
	@OneToMany(mappedBy="orderstatus")
	private List<Orderstatushistory> orderstatushistories;

	public Orderstatus() {
	}

	public int getIdOrderStatus() {
		return this.idOrderStatus;
	}

	public void setIdOrderStatus(int idOrderStatus) {
		this.idOrderStatus = idOrderStatus;
	}

	public String getOrderStatusName() {
		return this.orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public List<Orderstatushistory> getOrderstatushistories() {
		return this.orderstatushistories;
	}

	public void setOrderstatushistories(List<Orderstatushistory> orderstatushistories) {
		this.orderstatushistories = orderstatushistories;
	}

	public Orderstatushistory addOrderstatushistory(Orderstatushistory orderstatushistory) {
		getOrderstatushistories().add(orderstatushistory);
		orderstatushistory.setOrderstatus(this);

		return orderstatushistory;
	}

	public Orderstatushistory removeOrderstatushistory(Orderstatushistory orderstatushistory) {
		getOrderstatushistories().remove(orderstatushistory);
		orderstatushistory.setOrderstatus(null);

		return orderstatushistory;
	}

}