package com.joue.avectesamis.entites;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class AbcChallenge {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
//	private Long idAmiChallenge;
	private String codeIndentification;
	private String email;
	private int score;
	private Date date ;
	private char lettre;
	private int time ;
	private String help ; 
	private Long idAmi;
	private int scoreMax;
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
	private int timeOut ;
	@OneToOne
	private Friend friend;
	@OneToOne
	private Friend monFriend;
	
	
	
	
	/**
	 * @return the monFriend
	 */
	public Friend getMonFriend() {
		return monFriend;
	}
	/**
	 * @param monFriend the monFriend to set
	 */
	public void setMonFriend(Friend monFriend) {
		this.monFriend = monFriend;
	}
	/**
	 * @return the idAmi
	 */
	public Long getIdAmi() {
		return idAmi;
	}
	/**
	 * @param idAmi the idAmi to set
	 */
	public void setIdAmi(Long idAmi) {
		this.idAmi = idAmi;
	}
	/**
	 * @return the scoreMax
	 */
	public int getScoreMax() {
		return scoreMax;
	}
	/**
	 * @param scoreMax the scoreMax to set
	 */
	public void setScoreMax(int scoreMax) {
		this.scoreMax = scoreMax;
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
//	/**
//	 * @return the idAmiChallenge
//	 */
//	public Long getIdAmiChallenge() {
//		return idAmiChallenge;
//	}
//	/**
//	 * @param idAmiChallenge the idAmiChallenge to set
//	 */
//	public void setIdAmiChallenge(Long idAmiChallenge) {
//		this.idAmiChallenge = idAmiChallenge;
//	}
//	
	/**
	 * @return the codeIndentification
	 */
	public String getCodeIndentification() {
		return codeIndentification;
	}
	/**
	 * @param codeIndentification the codeIndentification to set
	 */
	public void setCodeIndentification(String codeIndentification) {
		this.codeIndentification = codeIndentification;
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
	 * @return the lettre
	 */
	public char getLettre() {
		return lettre;
	}
	/**
	 * @param lettre the lettre to set
	 */
	public void setLettre(char lettre) {
		this.lettre = lettre;
	}
	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}
	/**
	 * @return the help
	 */
	public String getHelp() {
		return help;
	}
	/**
	 * @param help the help to set
	 */
	public void setHelp(String help) {
		this.help = help;
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
	 * @return the timeOut
	 */
	public int getTimeOut() {
		return timeOut;
	}
	/**
	 * @param timeOut the timeOut to set
	 */
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
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
	 * 
	 */
	public AbcChallenge() {
		super();
		// TODO Auto-generated constructor stub
	}
/**
 * @param email
 * @param score
 * @param date
 * @param lettre
 * @param time
 * @param help
 * @param agglo
 * @param aPresident
 * @param president
 * @param animal
 * @param artiste
 * @param capitale
 * @param pays
 * @param nobel
 * @param ville
 * @param timeOut
 */
public AbcChallenge(String email, int score, Date date, char lettre,
		int time, String help, List<String> agglo, List<String> aPresident,
		List<String> president, List<String> animal, List<String> artiste,
		List<String> capitale, List<String> pays, List<String> nobel,
		List<String> ville, int timeOut) {
	super();
	this.email = email;
	this.score = score;
	this.date = date;
	this.lettre = lettre;
	this.time = time;
	this.help = help;
	this.agglo = agglo;
	this.aPresident = aPresident;
	this.president = president;
	this.animal = animal;
	this.artiste = artiste;
	this.capitale = capitale;
	this.pays = pays;
	this.nobel = nobel;
	this.ville = ville;
	this.timeOut = timeOut;
}
/**
 * @param id
 * @param email
 * @param score
 * @param date
 * @param lettre
 * @param time
 * @param help
 * @param agglo
 * @param aPresident
 * @param president
 * @param animal
 * @param artiste
 * @param capitale
 * @param pays
 * @param nobel
 * @param ville
 * @param timeOut
 */
public AbcChallenge(Long id, String email, int score, Date date, char lettre,
		int time, String help, List<String> agglo, List<String> aPresident,
		List<String> president, List<String> animal, List<String> artiste,
		List<String> capitale, List<String> pays, List<String> nobel,
		List<String> ville, int timeOut) {
	super();
	this.id = id;
	this.email = email;
	this.score = score;
	this.date = date;
	this.lettre = lettre;
	this.time = time;
	this.help = help;
	this.agglo = agglo;
	this.aPresident = aPresident;
	this.president = president;
	this.animal = animal;
	this.artiste = artiste;
	this.capitale = capitale;
	this.pays = pays;
	this.nobel = nobel;
	this.ville = ville;
	this.timeOut = timeOut;
}
/**
 * @param id
 * @param codeIndentification
 * @param email
 * @param score
 * @param date
 * @param lettre
 * @param time
 * @param help
 * @param agglo
 * @param aPresident
 * @param president
 * @param animal
 * @param artiste
 * @param capitale
 * @param pays
 * @param nobel
 * @param ville
 * @param timeOut
 */
public AbcChallenge(Long id, String codeIndentification, String email, int score, Date date, char lettre,
		int time, String help, List<String> agglo, List<String> aPresident,
		List<String> president, List<String> animal, List<String> artiste,
		List<String> capitale, List<String> pays, List<String> nobel,
		List<String> ville, int timeOut) {
	super();
	this.id = id;
	this.codeIndentification = codeIndentification;
	this.email = email;
	this.score = score;
	this.date = date;
	this.lettre = lettre;
	this.time = time;
	this.help = help;
	this.agglo = agglo;
	this.aPresident = aPresident;
	this.president = president;
	this.animal = animal;
	this.artiste = artiste;
	this.capitale = capitale;
	this.pays = pays;
	this.nobel = nobel;
	this.ville = ville;
	this.timeOut = timeOut;
}
public AbcChallenge(Long id2, String codeIndentification2, String email2,
		int score2, Date date2, char lettre2, int time2, String help2,
		List<String> agglo2, List<String> getaPresident,
		List<String> president2, List<String> animal2, List<String> artiste2,
		List<String> capitale2, List<String> pays2, List<String> nobel2,
		List<String> ville2, int timeOut2, Friend monFriend2, Friend friend) {
	// TODO Auto-generated constructor stub
	super();
	this.id = id2;
	this.codeIndentification = codeIndentification2;
	this.email = email2;
	this.score = score2;
	this.date = date2;
	this.lettre = lettre2;
	this.time = time2;
	this.help = help2;
	this.agglo = agglo2;
	this.aPresident = getaPresident;
	this.president = president2;
	this.animal = animal2;
	this.artiste = artiste2;
	this.capitale = capitale2;
	this.pays = pays2;
	this.nobel = nobel2;
	this.ville = ville2;
	this.timeOut = timeOut2;
	this.monFriend= monFriend2;
	this.friend = friend;
}
	
	
}
