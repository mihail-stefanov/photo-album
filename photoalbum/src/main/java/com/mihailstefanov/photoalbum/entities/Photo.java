package com.mihailstefanov.photoalbum.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "photos")
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dateUploaded;
	
	@OneToOne
	@JoinColumn(name = "file_id")
	private File file;
	
	@Basic
	private long numberOfViews;
	
	@Basic
	private long numberOfLikes;
	
	@OneToMany(mappedBy = "photo", fetch = FetchType.EAGER)
	private Set<Comment> comments;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Photo(String name, String description, Date dateUploaded, File file, long numberOfViews,
			long numberOfLikes, String tags, boolean sharedPublicly, User user) {
		this.name = name;
		this.description = description;
		this.dateUploaded = dateUploaded;
		this.file = file;
		this.numberOfViews = numberOfViews;
		this.numberOfLikes = numberOfLikes;
		this.user = user;
	}
	
	public Photo(String name, String description, File file, User user) {
		this(name, description, new Date(), file, 0, 0, "", false, user);
	}
	
	public Photo() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateUploaded() {
		return dateUploaded;
	}

	public void setDateUploaded(Date dateUploaded) {
		this.dateUploaded = dateUploaded;
	}

	public long getNumberOfViews() {
		return numberOfViews;
	}

	public void setNumberOfViews(long numberOfViews) {
		this.numberOfViews = numberOfViews;
	}

	public long getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(long numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
