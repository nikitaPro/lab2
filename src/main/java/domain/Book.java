package domain;

import javax.persistence.*;

@Entity
@Table
public class Book {
	@Id
	@SequenceGenerator(name="book_id_seq",
			sequenceName="book_id_seq",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator="book_id_seq")
	private int id;

	private String name;

	private int pages;

    private String author;

	public Book(int id, String name, int pages, String author) {
        this.id = id;
        this.name = name;
		this.pages = pages;
		this.author = author;
	}

	public Book() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int numbersOfPages) {
		this.pages = numbersOfPages;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return id + " " + name + " by " + author + " pages: " + pages;
	}

}
