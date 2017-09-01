package be.atc.javabeans;

import java.io.Serializable;
import java.util.List;
import be.atc.entities.Author;
import be.atc.entities.Category;
import be.atc.entities.Editor;
import be.atc.entities.Orderline;

public class BookBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int idBook;
	private String title;
	private boolean isActive;
	private double price;
	private Author author;
	private Category category;
	private Editor editor;
	private List<Orderline> orderlines;

	public int getIdBook() {
		return this.idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}
		
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Editor getEditor() {
		return this.editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}

	public List<Orderline> getOrderlines() {
		return this.orderlines;
	}

	public void setOrderlines(List<Orderline> orderlines) {
		this.orderlines = orderlines;
	}
}