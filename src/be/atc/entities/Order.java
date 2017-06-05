package be.atc.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@Table(name="order")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idOrder;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_IdUser", nullable=false)
	private User user;

	//bi-directional many-to-one association to Orderline
	@OneToMany(mappedBy="order")
	private List<Orderline> orderlines;

	//bi-directional many-to-one association to Orderstatushistory
	@OneToMany(mappedBy="order")
	private List<Orderstatushistory> orderstatushistories;

	public Order() {
	}

	public int getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Orderline> getOrderlines() {
		return this.orderlines;
	}

	public void setOrderlines(List<Orderline> orderlines) {
		this.orderlines = orderlines;
	}

	public Orderline addOrderline(Orderline orderline) {
		getOrderlines().add(orderline);
		orderline.setOrder(this);

		return orderline;
	}

	public Orderline removeOrderline(Orderline orderline) {
		getOrderlines().remove(orderline);
		orderline.setOrder(null);

		return orderline;
	}

	public List<Orderstatushistory> getOrderstatushistories() {
		return this.orderstatushistories;
	}

	public void setOrderstatushistories(List<Orderstatushistory> orderstatushistories) {
		this.orderstatushistories = orderstatushistories;
	}

	public Orderstatushistory addOrderstatushistory(Orderstatushistory orderstatushistory) {
		getOrderstatushistories().add(orderstatushistory);
		orderstatushistory.setOrder(this);

		return orderstatushistory;
	}

	public Orderstatushistory removeOrderstatushistory(Orderstatushistory orderstatushistory) {
		getOrderstatushistories().remove(orderstatushistory);
		orderstatushistory.setOrder(null);

		return orderstatushistory;
	}

}