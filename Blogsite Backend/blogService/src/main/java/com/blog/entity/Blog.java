package com.blog.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="Blogs")
public class Blog {
	
	@Id
	private Integer blogId;
	private String blogName;
	private String authorName;
	private String category;
	private String article;
	private Date createdTime;
	

}
