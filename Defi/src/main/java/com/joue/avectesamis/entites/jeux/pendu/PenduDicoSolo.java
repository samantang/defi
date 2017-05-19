package com.joue.avectesamis.entites.jeux.pendu;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joue.avectesamis.entites.Friend;

@Entity
public class PenduDicoSolo implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private String dateString;
	private int temps;
	private boolean timeOut;
	private String mot;
	private int nbEssais;
	private boolean reussi;
	// en fonction du nombre, on determine l'image correspondante qui sera
	// stockée au sein de l'appli et nom pas dans la BDD
	private int imageIconJeu;
	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private Collection<Character> choix;
	private int score;
	private boolean aide;
	private int nbAide;
	@ManyToOne
	@JsonIgnore
	private Friend friend;
	private String motUser;
	
	
	

	/**
	 * @return the motUser
	 */
	public String getMotUser() {
		return motUser;
	}

	/**
	 * @param motUser the motUser to set
	 */
	public void setMotUser(String motUser) {
		this.motUser = motUser;
	}

	/**
	 * 
	 */
	public PenduDicoSolo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param date
	 * @param dateString
	 * @param temps
	 * @param timeOut
	 * @param mot
	 * @param nbEssais
	 * @param reussi
	 * @param imageIconJeu
	 * @param choix
	 * @param score
	 * @param aide
	 * @param nbAide
	 */
	public PenduDicoSolo(Date date, String dateString, int temps,
			boolean timeOut, String mot, int nbEssais, boolean reussi,
			int imageIconJeu, Collection<Character> choix, int score,
			boolean aide, int nbAide) {
		super();
		this.date = date;
		this.dateString = dateString;
		this.temps = temps;
		this.timeOut = timeOut;
		this.mot = mot;
		this.nbEssais = nbEssais;
		this.reussi = reussi;
		this.imageIconJeu = imageIconJeu;
		this.choix = choix;
		this.score = score;
		this.aide = aide;
		this.nbAide = nbAide;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the dateString
	 */
	public String getDateString() {
		return dateString;
	}

	/**
	 * @param dateString
	 *            the dateString to set
	 */
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	/**
	 * @return the temps
	 */
	public int getTemps() {
		return temps;
	}

	/**
	 * @param temps
	 *            the temps to set
	 */
	public void setTemps(int temps) {
		this.temps = temps;
	}

	/**
	 * @return the timeOut
	 */
	public boolean isTimeOut() {
		return timeOut;
	}

	/**
	 * @param timeOut
	 *            the timeOut to set
	 */
	public void setTimeOut(boolean timeOut) {
		this.timeOut = timeOut;
	}

	/**
	 * @return the mot
	 */
	public String getMot() {
		return mot;
	}

	/**
	 * @param mot
	 *            the mot to set
	 */
	public void setMot(String mot) {
		this.mot = mot;
	}

	/**
	 * @return the nbEssais
	 */
	public int getNbEssais() {
		return nbEssais;
	}

	/**
	 * @param nbEssais
	 *            the nbEssais to set
	 */
	public void setNbEssais(int nbEssais) {
		this.nbEssais = nbEssais;
	}

	/**
	 * @return the reussi
	 */
	public boolean isReussi() {
		return reussi;
	}

	/**
	 * @param reussi
	 *            the reussi to set
	 */
	public void setReussi(boolean reussi) {
		this.reussi = reussi;
	}

	/**
	 * @return the imageIconJeu
	 */
	public int getImageIconJeu() {
		return imageIconJeu;
	}

	/**
	 * @param imageIconJeu
	 *            the imageIconJeu to set
	 */
	public void setImageIconJeu(int imageIconJeu) {
		this.imageIconJeu = imageIconJeu;
	}

	/**
	 * @return the choix
	 */
	public Collection<Character> getChoix() {
		return choix;
	}

	/**
	 * @param choix
	 *            the choix to set
	 */
	public void setChoix(Collection<Character> choix) {
		this.choix = choix;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the aide
	 */
	public boolean isAide() {
		return aide;
	}

	/**
	 * @param aide
	 *            the aide to set
	 */
	public void setAide(boolean aide) {
		this.aide = aide;
	}

	/**
	 * @return the nbAide
	 */
	public int getNbAide() {
		return nbAide;
	}

	/**
	 * @param nbAide
	 *            the nbAide to set
	 */
	public void setNbAide(int nbAide) {
		this.nbAide = nbAide;
	}

	/**
	 * @return the friend
	 */
	public Friend getFriend() {
		return friend;
	}

	/**
	 * @param friend
	 *            the friend to set
	 */
	public void setFriend(Friend friend) {
		this.friend = friend;
	}

}
