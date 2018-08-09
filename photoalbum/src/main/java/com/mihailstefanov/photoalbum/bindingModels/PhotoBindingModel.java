package com.mihailstefanov.photoalbum.bindingModels;

import java.util.Date;

import javax.persistence.Basic;

public class PhotoBindingModel {
	
	private Long id;
	private String name;
	private String description;
	private Date dateUploaded;
//	private Date dateTaken;
	private byte[] largePhoto;
//	private byte[] smallPhoto;
	private long numberOfUniqueUserViews;
	private long numberOfLikes;
	private String tags;
	
	@Basic
	private boolean sharedPublicly;
	
	// TODO: Add the following:
	//	- shared with [list]
	//	- comments
	
	public PhotoBindingModel(String name, String description, Date dateUploaded, byte[] largePhoto, long numberOfUniqueUserViews,
			long numberOfLikes, String tags, boolean sharedPublicly) {
		this.name = name;
		this.description = description;
		this.dateUploaded = dateUploaded;
		this.largePhoto = largePhoto;
		this.numberOfUniqueUserViews = numberOfUniqueUserViews;
		this.numberOfLikes = numberOfLikes;
		this.tags = tags;
		this.sharedPublicly = sharedPublicly;
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

	public byte[] getLargePhoto() {
		return largePhoto;
	}

	public void setLargePhoto(byte[] largePhoto) {
		this.largePhoto = largePhoto;
	}

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

	public boolean isSharedPublicly() {
		return sharedPublicly;
	}

	public void setSharedPublicly(boolean sharedPublicly) {
		this.sharedPublicly = sharedPublicly;
	}
}
