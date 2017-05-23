package eu.karols.domain;

import java.util.Date;

import javax.persistence.*;

//import org.hibernate.search.annotations.Field;
//import org.hibernate.search.annotations.Indexed;
//import org.hibernate.search.annotations.Store;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
//@Indexed
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Field
    @Column(nullable = false, length = 300)
    private String title;

//    @Field
    @Lob @Column(nullable = false)
    private String body;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User author;
   
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(nullable = false)
    private Date date;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Post() {
		
	}
	
	public Post(Long id, String title, String body, User author, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.author = author;
		this.date = date;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", body=" + body + ", author=" + author + ", date=" + date + "]";
	}

	
}