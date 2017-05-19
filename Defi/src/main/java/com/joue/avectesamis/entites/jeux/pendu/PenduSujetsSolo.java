package com.joue.avectesamis.entites.jeux.pendu;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
public class PenduSujetsSolo implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date date;
	private String dateString;
	private int temps;
	private boolean timeOut;
	private String mot;
	private int nbEssais;
	private boolean reussi;
//	en fonction du nombre, on determine l'image correspondante qui sera stockée au sein de l'appli et nom pas dans la BDD
	private int imageIconJeu;
	@ElementCollection(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private Collection<Character> choix;
	private int score;
	private boolean aide;
	private int nbAide;
	@ManyToOne
	@JsonIgnore
	private Friend friend;
	@ElementCollection(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<String>agglo ;
	@ElementCollection(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<String>aPresident ;
	@ElementCollection(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<String>president ;
	@ElementCollection(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<String>animal ;
	@ElementCollection(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<String>artiste ;
	@ElementCollection(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<String>capitale ;
	@ElementCollection(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<String>pays ;
	@ElementCollection(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<String>nobel ;
	@ElementCollection(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<String>ville ;
	
	
	
	
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
	 * @param agglo
	 * @param aPresident
	 * @param president
	 * @param animal
	 * @param artiste
	 * @param capitale
	 * @param pays
	 * @param nobel
	 * @param ville
	 */
	public PenduSujetsSolo(Date date, String dateString, int temps,
			boolean timeOut, String mot, int nbEssais, boolean reussi,
			int imageIconJeu, Collection<Character> choix, int score,
			boolean aide, int nbAide, List<String> agglo,
			List<String> aPresident, List<String> president,
			List<String> animal, List<String> artiste, List<String> capitale,
			List<String> pays, List<String> nobel, List<String> ville) {
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
		this.agglo = agglo;
		this.aPresident = aPresident;
		this.president = president;
		this.animal = animal;
		this.artiste = artiste;
		this.capitale = capitale;
		this.pays = pays;
		this.nobel = nobel;
		this.ville = ville;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
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
	 * @param date the date to set
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
	 * @param dateString the dateString to set
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
	 * @param temps the temps to set
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
	 * @param timeOut the timeOut to set
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
	 * @param mot the mot to set
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
	 * @param nbEssais the nbEssais to set
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
	 * @param reussi the reussi to set
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
	 * @param imageIconJeu the imageIconJeu to set
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
	 * @param choix the choix to set
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
	 * @param score the score to set
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
	 * @param aide the aide to set
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
	 * @param nbAide the nbAide to set
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
	 * @param friend the friend to set
	 */
	public void setFriend(Friend friend) {
		this.friend = friend;
	}
	/**
	 * @return the agglo
	 */
	public List<String> getAgglo() {
		return agglo;
	}
	/**
	 * @param agglo the agglo to set
	 */
	public void setAgglo(List<String> agglo) {
		this.agglo = agglo;
	}
	/**
	 * @return the aPresident
	 */
	public List<String> getaPresident() {
		return aPresident;
	}
	/**
	 * @param aPresident the aPresident to set
	 */
	public void setaPresident(List<String> aPresident) {
		this.aPresident = aPresident;
	}
	/**
	 * @return the president
	 */
	public List<String> getPresident() {
		return president;
	}
	/**
	 * @param president the president to set
	 */
	public void setPresident(List<String> president) {
		this.president = president;
	}
	/**
	 * @return the animal
	 */
	public List<String> getAnimal() {
		return animal;
	}
	/**
	 * @param animal the animal to set
	 */
	public void setAnimal(List<String> animal) {
		this.animal = animal;
	}
	/**
	 * @return the artiste
	 */
	public List<String> getArtiste() {
		return artiste;
	}
	/**
	 * @param artiste the artiste to set
	 */
	public void setArtiste(List<String> artiste) {
		this.artiste = artiste;
	}
	/**
	 * @return the capitale
	 */
	public List<String> getCapitale() {
		return capitale;
	}
	/**
	 * @param capitale the capitale to set
	 */
	public void setCapitale(List<String> capitale) {
		this.capitale = capitale;
	}
	/**
	 * @return the pays
	 */
	public List<String> getPays() {
		return pays;
	}
	/**
	 * @param pays the pays to set
	 */
	public void setPays(List<String> pays) {
		this.pays = pays;
	}
	/**
	 * @return the nobel
	 */
	public List<String> getNobel() {
		return nobel;
	}
	/**
	 * @param nobel the nobel to set
	 */
	public void setNobel(List<String> nobel) {
		this.nobel = nobel;
	}
	/**
	 * @return the ville
	 */
	public List<String> getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(List<String> ville) {
		this.ville = ville;
	}
	/**
	 * 
	 */
	public PenduSujetsSolo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
