package eu.karols.form;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import eu.karols.domain.User;

public class PostCreateForm {
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String body;
	
	@NotEmpty
	private User author;
	
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@NotEmpty
	private Date date;

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

	@Override
	public String toString() {
		return "PostCreateForm [title=" + title + ", body=" + body + ", author=" + author + ", date=" + date + "]";
	}
	
	
}
