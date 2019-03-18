package com.freemi.entity.general;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="blog_content")
public class BlogContent {

	@Id
	private int id;
	private String blog_topic;
	private String blog_description;
	private String blog_linked_images;
	private String blog_comments_id;
	private String blog_author_id;
	private int avg_rate;
	private int likes;
	private int dislikes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBlog_topic() {
		return blog_topic;
	}
	public void setBlog_topic(String blog_topic) {
		this.blog_topic = blog_topic;
	}
	public String getBlog_description() {
		return blog_description;
	}
	public void setBlog_description(String blog_description) {
		this.blog_description = blog_description;
	}
	public String getBlog_linked_images() {
		return blog_linked_images;
	}
	public void setBlog_linked_images(String blog_linked_images) {
		this.blog_linked_images = blog_linked_images;
	}
	
	
	public String getBlog_author_id() {
		return blog_author_id;
	}
	public void setBlog_author_id(String blog_author_id) {
		this.blog_author_id = blog_author_id;
	}
	public String getBlog_comments_id() {
		return blog_comments_id;
	}
	public void setBlog_comments_id(String blog_comments_id) {
		this.blog_comments_id = blog_comments_id;
	}
	public int getAvg_rate() {
		return avg_rate;
	}
	public void setAvg_rate(int avg_rate) {
		this.avg_rate = avg_rate;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	
	
	
}
