package be.atc.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the orderline database table.
 * 
 */
@Entity
@Table(name="orderline")
@NamedQuery(name="Orderline.findAll", query="SELECT o FROM Orderline o")
public class Orderline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idOrderLine;

	private int quantity;

	//bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name="Book_IdBook", nullable=false)
	private Book book;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="Order_IdOrder", nullable=false)
	private Order order;

	public Orderline() {
	}

	public int getIdOrderLine() {
		return this.idOrderLine;
	}

	public void setIdOrderLine(int idOrderLine) {
		this.idOrderLine = idOrderLine;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}