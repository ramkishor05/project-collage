package org.brijframework.college.models.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class NewsDTO {

	private Integer id;
	private String newsDescription;
	private String isPublish;
	private String newsTitle;
	private MultipartFile imageFile;
	private String postDate;
	private int totalComments;
	private List<CommentsDTO> commentsDTOs;
	private String imageName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNewsDescription() {
		return newsDescription;
	}

	public void setNewsDescription(String newsDescription) {
		this.newsDescription = newsDescription;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(String isPublish) {
		this.isPublish = isPublish;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public int getTotalComments() {
		return totalComments;
	}

	public void setTotalComments(int totalComments) {
		this.totalComments = totalComments;
	}

	public List<CommentsDTO> getCommentsDTOs() {
		return commentsDTOs;
	}

	public void setCommentsDTOs(List<CommentsDTO> commentsDTOs) {
		this.commentsDTOs = commentsDTOs;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}
