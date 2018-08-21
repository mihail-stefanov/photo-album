package com.mihailstefanov.photoalbum.entities;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(nullable = false)
//	private Date dateTaken;
	
	@OneToOne
	@JoinColumn(name = 	"file_id")
	private File file;
	
//	@Lob
//	@Column(nullable = false, columnDefinition = "BLOB")
//	private byte[] smallFile;
	
	@Basic
	private long numberOfUniqueUserViews;
	
	@Basic
	private long numberOfLikes;
	
	@Basic
	private String tags;
	
	@Basic
	private boolean sharedPublicly;
	
	// TODO: Add the following:
	//	- shared with [list]
	//	- comments
	
	public Photo(String name, String description, Date dateUploaded, File file, long numberOfUniqueUserViews,
			long numberOfLikes, String tags, boolean sharedPublicly) {
		this.name = name;
		this.description = description;
		this.dateUploaded = dateUploaded;
		this.file = file;
		this.numberOfUniqueUserViews = numberOfUniqueUserViews;
		this.numberOfLikes = numberOfLikes;
		this.tags = tags;
		this.sharedPublicly = sharedPublicly;
	}
	
	public Photo(String name, String description, File file) {
		this(name, description, new Date(), file, 0, 0, "", false);
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

//	public Date getDateTaken() {
//		return dateTaken;
//	}
//
//	public void setDateTaken(Date dateTaken) {
//		this.dateTaken = dateTaken;
//	}


//	public byte[] getSmallPhoto() {
//		return smallPhoto;
//	}
//
//	public void setSmallPhoto(byte[] smallPhoto) {
//		this.smallPhoto = smallPhoto;
//	}

	public long getNumberOfUniqueUserViews() {
		return numberOfUniqueUserViews;
	}

	public void setNumberOfUniqueUserViews(long numberOfUniqueUserViews) {
		this.numberOfUniqueUserViews = numberOfUniqueUserViews;
	}

	public long getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(long numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public boolean isSharedPublicly() {
		return sharedPublicly;
	}

	public void setSharedPublicly(boolean sharedPublicly) {
		this.sharedPublicly = sharedPublicly;
	}
	
}
