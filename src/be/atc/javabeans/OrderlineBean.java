package be.atc.javabeans;

import java.io.Serializable;

import be.atc.entities.Book;
import be.atc.entities.Order;

public class OrderlineBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idOrderLine;
	private int quantity;
	private Book book;
	private Order order;

	public OrderlineBean() {
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