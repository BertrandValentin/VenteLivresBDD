package be.atc.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the orderstatushistory database table.
 * 
 */
@Entity
@Table(name="orderstatushistory")
@NamedQuery(name="Orderstatushistory.findAll", query="SELECT o FROM Orderstatushistory o")
public class Orderstatushistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idOrderStatusHistory;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="Order_IdOrder", nullable=false)
	private Order order;

	//bi-directional many-to-one association to Orderstatus
	@ManyToOne
	@JoinColumn(name="OrderStatus_IdOrderStatus", nullable=false)
	private Orderstatus orderstatus;

	public Orderstatushistory() {
	}

	public int getIdOrderStatusHistory() {
		return this.idOrderStatusHistory;
	}

	public void setIdOrderStatusHistory(int idOrderStatusHistory) {
		this.idOrderStatusHistory = idOrderStatusHistory;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Orderstatus getOrderstatus() {
		return this.orderstatus;
	}

	public void setOrderstatus(Orderstatus orderstatus) {
		this.orderstatus = orderstatus;
	}

}