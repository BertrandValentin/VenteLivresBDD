package be.atc.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the author database table.
 * 
 */
@Entity
@Table(name="author")
@NamedQuery(name="Author.findAll", query="SELECT a FROM Author a")
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idAuthor;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(length=50)
	private String firstName;

	private boolean isActive;

	@Column(length=50)
	private String lastName;

	@Column(length=50)
	private String nationality;

	//bi-directional many-to-one association to Book
	@OneToMany(mappedBy="author")
	private List<Book> books;

	public Author() {
	}

	public int getIdAuthor() {
		return this.idAuthor;
	}

	public void setIdAuthor(int idAuthor) {
		this.idAuthor = idAuthor;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Book> getBooks() {
		return this.books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Book addBook(Book book) {
		getBooks().add(book);
		book.setAuthor(this);

		return book;
	}

	public Book removeBook(Book book) {
		getBooks().remove(book);
		book.setAuthor(null);

		return book;
	}

}