package com.joue.avectesamis.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String message;
	@ManyToOne
	@JoinColumn(name="nro_friend")
	private Friend friendpost;
	/**
	 * 
	 */
	public Message() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @param message
	 */
	public Message(String message) {
		super();
		this.message = message;
	}
	
	
	
	

}
