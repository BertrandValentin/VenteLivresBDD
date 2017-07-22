package be.atc.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the book database table.
 * 
 */
@Entity
@Table(name="book")
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idBook;
	
	@Column(length=120, nullable=false)
	private String title;

	private boolean isActive;

	private double price;

	//bi-directional many-to-one association to Author
	@ManyToOne
	@JoinColumn(name="Author_IdAuthor", nullable=false)
	private Author author;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="Category_IdCategory", nullable=false)
	private Category category;

	//bi-directional many-to-one association to Editor
	@ManyToOne
	@JoinColumn(name="Editor_IdEditor", nullable=false)
	private Editor editor;

	//bi-directional many-to-one association to Orderline
	@OneToMany(mappedBy="book")
	private List<Orderline> orderlines;

	public Book() {
	}

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

	public Orderline addOrderline(Orderline orderline) {
		getOrderlines().add(orderline);
		orderline.setBook(this);

		return orderline;
	}

	public Orderline removeOrderline(Orderline orderline) {
		getOrderlines().remove(orderline);
		orderline.setBook(null);

		return orderline;
	}

}