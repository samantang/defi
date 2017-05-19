package com.joue.avectesamis.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Post implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int nro;
	private String email;
	private Date postDate;
	private String message;
	private Date date;
	private boolean jeu;
	@JsonIgnore
	@OneToMany(mappedBy="post")
	private Collection<Likes> likes;
	@JsonIgnore
	@OneToMany(mappedBy="post")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Comment> comments ;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fp")
	private Friend friendpost;
	private int nbLikesPost;
//	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
//	private Collection<Post> posts;
	
	 
	/**
	 * 
	 */
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	/**
 * @param message
 * @param jeu
 */
public Post(String message, boolean jeu) {
	super();
	this.message = message;
	this.jeu = jeu;
}


	/**
	 * @param message
	 * @param date
	 * @param jeu
	 */
	public Post(String message, Date date, boolean jeu) {
		super();
		this.message = message;
		this.date = date;
		this.jeu = jeu;
	}


	/**
	 * @param message
	 * @param date
	 */
	public Post(String message, Date date) {
		super();
		this.message = message;
		this.date = date;
	}

	
	/**
	 * @return the nbLikesPost
	 */
	public int getNbLikesPost() {
		return nbLikesPost;
	}


	/**
	 * @param nbLikesPost the nbLikesPost to set
	 */
	public void setNbLikesPost(int nbLikesPost) {
		this.nbLikesPost = nbLikesPost;
	}


	/**
	 * @param message
	 */
	public Post(String message) {
		super();
		this.message = message;
	}
	

	/**
	 * @return the friendpost
	 */
	public Friend getFriendpost() {
		return friendpost;
	}


	/**
	 * @param friendpost the friendpost to set
	 */
	public void setFriendpost(Friend friendpost) {
		this.friendpost = friendpost;
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id
	 * @param message
	 */
	public Post(Long id, String message) {
		super();
		this.id = id;
		this.message = message;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the nro
	 */
	public int getNro() {
		return nro;
	}


	/**
	 * @param nro the nro to set
	 */
	public void setNro(int nro) {
		this.nro = nro;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	

	/**
	 * @return the postDate
	 */
	public Date getPostDate() {
		return postDate;
	}


	/**
	 * @param postDate the postDate to set
	 */
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}


	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * @return the jeu
	 */
	public boolean isJeu() {
		return jeu;
	}


	/**
	 * @param jeu the jeu to set
	 */
	public void setJeu(boolean jeu) {
		this.jeu = jeu;
	}


	/**
	 * @return the likes
	 */
	public Collection<Likes> getLikes() {
		return likes;
	}


	/**
	 * @param likes the likes to set
	 */
	public void setLikes(Collection<Likes> likes) {
		this.likes = likes;
	}


	/**
	 * @return the comments
	 */
	public Collection<Comment> getComments() {
		return comments;
	}


	/**
	 * @param comments the comments to set
	 */
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	
	
}
