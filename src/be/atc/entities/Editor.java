package be.atc.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the editor database table.
 * 
 */
@Entity
@Table(name="editor")
@NamedQuery(name="Editor.findAll", query="SELECT e FROM Editor e")
public class Editor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idEditor;

	@Column(nullable=false, length=50)
	private String editorName;

	private boolean isActive;

	//bi-directional many-to-one association to Book
	@OneToMany(mappedBy="editor")
	private List<Book> books;

	public Editor() {
	}

	public int getIdEditor() {
		return this.idEditor;
	}

	public void setIdEditor(int idEditor) {
		this.idEditor = idEditor;
	}

	public String getEditorName() {
		return this.editorName;
	}

	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<Book> getBooks() {
		return this.books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Book addBook(Book book) {
		getBooks().add(book);
		book.setEditor(this);

		return book;
	}

	public Book removeBook(Book book) {
		getBooks().remove(book);
		book.setEditor(null);

		return book;
	}
}