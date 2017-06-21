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
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.joue.avectesamis.entites.Friend;

@Entity
public class PenduSujetsChallenge implements Serializable{
	
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
	@ElementCollection(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private Collection<String> intitules;
	private int score;
	private boolean aide;
	private int nbAide;
	private String codeIndentification;
	@OneToOne
	private Friend friend;
	@OneToOne
	private Friend monFriend;
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
	
//	dans ce cas les sujets doivent être des String et non des listes de String
	private String paysS;
	private String capitaleS;
	private String nobelS;
	private String artisteS;
	private String presidentS;
	
	private char lettrePays;
	private char lettreCapitale;
	private char lettreArtiste;
	private char lettreNobel;
	private char lettrePresident;
	
	private String dateStringAmi;
	private String tempsRestantAmi;
	private boolean timeOutAmi;
	private int imageIconJeuAmi;
	private int scoreAmi;
	private String paysSAmi;
	private String capitaleSAmi;
	private String nobelSAmi;
	private String artisteSAmi;
	private String presidentSAmi;
	
	private char lettrePaysAmi;
	private char lettreCapitaleAmi;
	private char lettreArtisteAmi;
	private char lettreNobelAmi;
	private char lettrePresidentAmi;
	private int nbErreursAmi;
	
	private int nbErreurs;
	private String tempsRestant;
	
	private boolean aideAmi;
	
	private int scorePays;
	private int scoreCapitale;
	private int scoreNobel;
	private int scoreArtiste;
	private int scorePresident;
	
	private int scoreMaxPays;
	private int scoreMaxCapitale;
	private int scoreMaxNobel;
	private int scoreMaxArtiste;
	private int scoreMaxPresident;
	
	private int scorePaysAmi;
	private int scoreCapitaleAmi;
	private int scoreNobelAmi;
	private int scoreArtisteAmi;
	private int scorePresidentAmi;
	
	
	
	
	
	
	
	/**
	 * @return the scorePays
	 */
	public int getScorePays() {
		return scorePays;
	}
	/**
	 * @param scorePays the scorePays to set
	 */
	public void setScorePays(int scorePays) {
		this.scorePays = scorePays;
	}
	/**
	 * @return the scoreCapitale
	 */
	public int getScoreCapitale() {
		return scoreCapitale;
	}
	/**
	 * @param scoreCapitale the scoreCapitale to set
	 */
	public void setScoreCapitale(int scoreCapitale) {
		this.scoreCapitale = scoreCapitale;
	}
	/**
	 * @return the scoreNobel
	 */
	public int getScoreNobel() {
		return scoreNobel;
	}
	/**
	 * @param scoreNobel the scoreNobel to set
	 */
	public void setScoreNobel(int scoreNobel) {
		this.scoreNobel = scoreNobel;
	}
	/**
	 * @return the scoreArtiste
	 */
	public int getScoreArtiste() {
		return scoreArtiste;
	}
	/**
	 * @param scoreArtiste the scoreArtiste to set
	 */
	public void setScoreArtiste(int scoreArtiste) {
		this.scoreArtiste = scoreArtiste;
	}
	/**
	 * @return the scorePresident
	 */
	public int getScorePresident() {
		return scorePresident;
	}
	/**
	 * @param scorePresident the scorePresident to set
	 */
	public void setScorePresident(int scorePresident) {
		this.scorePresident = scorePresident;
	}
	/**
	 * @return the scoreMaxPays
	 */
	public int getScoreMaxPays() {
		return scoreMaxPays;
	}
	/**
	 * @param scoreMaxPays the scoreMaxPays to set
	 */
	public void setScoreMaxPays(int scoreMaxPays) {
		this.scoreMaxPays = scoreMaxPays;
	}
	/**
	 * @return the scoreMaxCapitale
	 */
	public int getScoreMaxCapitale() {
		return scoreMaxCapitale;
	}
	/**
	 * @param scoreMaxCapitale the scoreMaxCapitale to set
	 */
	public void setScoreMaxCapitale(int scoreMaxCapitale) {
		this.scoreMaxCapitale = scoreMaxCapitale;
	}
	/**
	 * @return the scoreMaxNobel
	 */
	public int getScoreMaxNobel() {
		return scoreMaxNobel;
	}
	/**
	 * @param scoreMaxNobel the scoreMaxNobel to set
	 */
	public void setScoreMaxNobel(int scoreMaxNobel) {
		this.scoreMaxNobel = scoreMaxNobel;
	}
	/**
	 * @return the scoreMaxArtiste
	 */
	public int getScoreMaxArtiste() {
		return scoreMaxArtiste;
	}
	/**
	 * @param scoreMaxArtiste the scoreMaxArtiste to set
	 */
	public void setScoreMaxArtiste(int scoreMaxArtiste) {
		this.scoreMaxArtiste = scoreMaxArtiste;
	}
	/**
	 * @return the scoreMaxPresident
	 */
	public int getScoreMaxPresident() {
		return scoreMaxPresident;
	}
	/**
	 * @param scoreMaxPresident the scoreMaxPresident to set
	 */
	public void setScoreMaxPresident(int scoreMaxPresident) {
		this.scoreMaxPresident = scoreMaxPresident;
	}
	/**
	 * @return the scorePaysAmi
	 */
	public int getScorePaysAmi() {
		return scorePaysAmi;
	}
	/**
	 * @param scorePaysAmi the scorePaysAmi to set
	 */
	public void setScorePaysAmi(int scorePaysAmi) {
		this.scorePaysAmi = scorePaysAmi;
	}
	/**
	 * @return the scoreCapitaleAmi
	 */
	public int getScoreCapitaleAmi() {
		return scoreCapitaleAmi;
	}
	/**
	 * @param scoreCapitaleAmi the scoreCapitaleAmi to set
	 */
	public void setScoreCapitaleAmi(int scoreCapitaleAmi) {
		this.scoreCapitaleAmi = scoreCapitaleAmi;
	}
	/**
	 * @return the scoreNobelAmi
	 */
	public int getScoreNobelAmi() {
		return scoreNobelAmi;
	}
	/**
	 * @param scoreNobelAmi the scoreNobelAmi to set
	 */
	public void setScoreNobelAmi(int scoreNobelAmi) {
		this.scoreNobelAmi = scoreNobelAmi;
	}
	/**
	 * @return the scoreArtisteAmi
	 */
	public int getScoreArtisteAmi() {
		return scoreArtisteAmi;
	}
	/**
	 * @param scoreArtisteAmi the scoreArtisteAmi to set
	 */
	public void setScoreArtisteAmi(int scoreArtisteAmi) {
		this.scoreArtisteAmi = scoreArtisteAmi;
	}
	/**
	 * @return the scorePresidentAmi
	 */
	public int getScorePresidentAmi() {
		return scorePresidentAmi;
	}
	/**
	 * @param scorePresidentAmi the scorePresidentAmi to set
	 */
	public void setScorePresidentAmi(int scorePresidentAmi) {
		this.scorePresidentAmi = scorePresidentAmi;
	}
	/**
	 * @return the aideAmi
	 */
	public boolean isAideAmi() {
		return aideAmi;
	}
	/**
	 * @param aideAmi the aideAmi to set
	 */
	public void setAideAmi(boolean aideAmi) {
		this.aideAmi = aideAmi;
	}
	/**
	 * @return the tempsRestant
	 */
	public String getTempsRestant() {
		return tempsRestant;
	}
	/**
	 * @param tempsRestant the tempsRestant to set
	 */
	public void setTempsRestant(String tempsRestant) {
		this.tempsRestant = tempsRestant;
	}
	/**
	 * @return the dateStringAmi
	 */
	public String getDateStringAmi() {
		return dateStringAmi;
	}
	/**
	 * @param dateStringAmi the dateStringAmi to set
	 */
	public void setDateStringAmi(String dateStringAmi) {
		this.dateStringAmi = dateStringAmi;
	}
	/**
	 * @return the tempsRestantAmi
	 */
	public String getTempsRestantAmi() {
		return tempsRestantAmi;
	}
	/**
	 * @param tempsRestantAmi the tempsRestantAmi to set
	 */
	public void setTempsRestantAmi(String tempsRestantAmi) {
		this.tempsRestantAmi = tempsRestantAmi;
	}
	/**
	 * @return the timeOutAmi
	 */
	public boolean isTimeOutAmi() {
		return timeOutAmi;
	}
	/**
	 * @param timeOutAmi the timeOutAmi to set
	 */
	public void setTimeOutAmi(boolean timeOutAmi) {
		this.timeOutAmi = timeOutAmi;
	}
	/**
	 * @return the imageIconJeuAmi
	 */
	public int getImageIconJeuAmi() {
		return imageIconJeuAmi;
	}
	/**
	 * @param imageIconJeuAmi the imageIconJeuAmi to set
	 */
	public void setImageIconJeuAmi(int imageIconJeuAmi) {
		this.imageIconJeuAmi = imageIconJeuAmi;
	}
	/**
	 * @return the scoreAmi
	 */
	public int getScoreAmi() {
		return scoreAmi;
	}
	/**
	 * @param scoreAmi the scoreAmi to set
	 */
	public void setScoreAmi(int scoreAmi) {
		this.scoreAmi = scoreAmi;
	}
	/**
	 * @return the paysSAmi
	 */
	public String getPaysSAmi() {
		return paysSAmi;
	}
	/**
	 * @param paysSAmi the paysSAmi to set
	 */
	public void setPaysSAmi(String paysSAmi) {
		this.paysSAmi = paysSAmi;
	}
	/**
	 * @return the capitaleSAmi
	 */
	public String getCapitaleSAmi() {
		return capitaleSAmi;
	}
	/**
	 * @param capitaleSAmi the capitaleSAmi to set
	 */
	public void setCapitaleSAmi(String capitaleSAmi) {
		this.capitaleSAmi = capitaleSAmi;
	}
	/**
	 * @return the nobelSAmi
	 */
	public String getNobelSAmi() {
		return nobelSAmi;
	}
	/**
	 * @param nobelSAmi the nobelSAmi to set
	 */
	public void setNobelSAmi(String nobelSAmi) {
		this.nobelSAmi = nobelSAmi;
	}
	/**
	 * @return the artisteSAmi
	 */
	public String getArtisteSAmi() {
		return artisteSAmi;
	}
	/**
	 * @param artisteSAmi the artisteSAmi to set
	 */
	public void setArtisteSAmi(String artisteSAmi) {
		this.artisteSAmi = artisteSAmi;
	}
	/**
	 * @return the presidentSAmi
	 */
	public String getPresidentSAmi() {
		return presidentSAmi;
	}
	/**
	 * @param presidentSAmi the presidentSAmi to set
	 */
	public void setPresidentSAmi(String presidentSAmi) {
		this.presidentSAmi = presidentSAmi;
	}
	/**
	 * @return the lettrePaysAmi
	 */
	public char getLettrePaysAmi() {
		return lettrePaysAmi;
	}
	/**
	 * @param lettrePaysAmi the lettrePaysAmi to set
	 */
	public void setLettrePaysAmi(char lettrePaysAmi) {
		this.lettrePaysAmi = lettrePaysAmi;
	}
	/**
	 * @return the lettreCapitaleAmi
	 */
	public char getLettreCapitaleAmi() {
		return lettreCapitaleAmi;
	}
	/**
	 * @param lettreCapitaleAmi the lettreCapitaleAmi to set
	 */
	public void setLettreCapitaleAmi(char lettreCapitaleAmi) {
		this.lettreCapitaleAmi = lettreCapitaleAmi;
	}
	/**
	 * @return the lettreArtisteAmi
	 */
	public char getLettreArtisteAmi() {
		return lettreArtisteAmi;
	}
	/**
	 * @param lettreArtisteAmi the lettreArtisteAmi to set
	 */
	public void setLettreArtisteAmi(char lettreArtisteAmi) {
		this.lettreArtisteAmi = lettreArtisteAmi;
	}
	/**
	 * @return the lettreNobelAmi
	 */
	public char getLettreNobelAmi() {
		return lettreNobelAmi;
	}
	/**
	 * @param lettreNobelAmi the lettreNobelAmi to set
	 */
	public void setLettreNobelAmi(char lettreNobelAmi) {
		this.lettreNobelAmi = lettreNobelAmi;
	}
	/**
	 * @return the lettrePresidentAmi
	 */
	public char getLettrePresidentAmi() {
		return lettrePresidentAmi;
	}
	/**
	 * @param lettrePresidentAmi the lettrePresidentAmi to set
	 */
	public void setLettrePresidentAmi(char lettrePresidentAmi) {
		this.lettrePresidentAmi = lettrePresidentAmi;
	}
	/**
	 * @return the nbErreursAmi
	 */
	public int getNbErreursAmi() {
		return nbErreursAmi;
	}
	/**
	 * @param nbErreursAmi the nbErreursAmi to set
	 */
	public void setNbErreursAmi(int nbErreursAmi) {
		this.nbErreursAmi = nbErreursAmi;
	}
	/**
	 * @return the nbErreurs
	 */
	public int getNbErreurs() {
		return nbErreurs;
	}
	/**
	 * @param nbErreurs the nbErreurs to set
	 */
	public void setNbErreurs(int nbErreurs) {
		this.nbErreurs = nbErreurs;
	}
	/**
	 * @param date
	 * @param dateString
	 * @param imageIconJeu
	 * @param paysS
	 * @param capitaleS
	 * @param nobelS
	 * @param artisteS
	 * @param presidentS
	 * @param lettrePays
	 * @param lettreCapitale
	 * @param lettreArtiste
	 * @param lettreNobel
	 * @param lettrePresident
	 */
	public PenduSujetsChallenge(Date date, String dateString, int imageIconJeu,
			String paysS, String capitaleS, String nobelS, String artisteS,
			String presidentS, char lettrePays, char lettreCapitale,
			char lettreArtiste, char lettreNobel, char lettrePresident) {
		super();
		this.date = date;
		this.dateString = dateString;
		this.imageIconJeu = imageIconJeu;
		this.paysS = paysS;
		this.capitaleS = capitaleS;
		this.nobelS = nobelS;
		this.artisteS = artisteS;
		this.presidentS = presidentS;
		this.lettrePays = lettrePays;
		this.lettreCapitale = lettreCapitale;
		this.lettreArtiste = lettreArtiste;
		this.lettreNobel = lettreNobel;
		this.lettrePresident = lettrePresident;
	}
	/**
	 * @return the lettreCapitale
	 */
	public char getLettreCapitale() {
		return lettreCapitale;
	}
	/**
	 * @param lettreCapitale the lettreCapitale to set
	 */
	public void setLettreCapitale(char lettreCapitale) {
		this.lettreCapitale = lettreCapitale;
	}
	/**
	 * @return the lettreArtiste
	 */
	public char getLettreArtiste() {
		return lettreArtiste;
	}
	/**
	 * @param lettreArtiste the lettreArtiste to set
	 */
	public void setLettreArtiste(char lettreArtiste) {
		this.lettreArtiste = lettreArtiste;
	}
	/**
	 * @return the lettreNobel
	 */
	public char getLettreNobel() {
		return lettreNobel;
	}
	/**
	 * @param lettreNobel the lettreNobel to set
	 */
	public void setLettreNobel(char lettreNobel) {
		this.lettreNobel = lettreNobel;
	}
	/**
	 * @return the lettrePresident
	 */
	public char getLettrePresident() {
		return lettrePresident;
	}
	/**
	 * @param lettrePresident the lettrePresident to set
	 */
	public void setLettrePresident(char lettrePresident) {
		this.lettrePresident = lettrePresident;
	}
	/**
	 * @return the lettrePays
	 */
	public char getLettrePays() {
		return lettrePays;
	}
	/**
	 * @param lettrePays the lettrePays to set
	 */
	public void setLettrePays(char lettrePays) {
		this.lettrePays = lettrePays;
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
	 * @return the paysS
	 */
	public String getPaysS() {
		return paysS;
	}
	/**
	 * @param paysS the paysS to set
	 */
	public void setPaysS(String paysS) {
		this.paysS = paysS;
	}
	/**
	 * @return the capitaleS
	 */
	public String getCapitaleS() {
		return capitaleS;
	}
	/**
	 * @param capitaleS the capitaleS to set
	 */
	public void setCapitaleS(String capitaleS) {
		this.capitaleS = capitaleS;
	}
	/**
	 * @return the nobelS
	 */
	public String getNobelS() {
		return nobelS;
	}
	/**
	 * @param nobelS the nobelS to set
	 */
	public void setNobelS(String nobelS) {
		this.nobelS = nobelS;
	}
	/**
	 * @return the artisteS
	 */
	public String getArtisteS() {
		return artisteS;
	}
	/**
	 * @param artisteS the artisteS to set
	 */
	public void setArtisteS(String artisteS) {
		this.artisteS = artisteS;
	}
	/**
	 * @return the presidentS
	 */
	public String getPresidentS() {
		return presidentS;
	}
	/**
	 * @param presidentS the presidentS to set
	 */
	public void setPresidentS(String presidentS) {
		this.presidentS = presidentS;
	}
	/**
	 * @return the intitules
	 */
	public Collection<String> getIntitules() {
		return intitules;
	}
	/**
	 * @param intitules the intitules to set
	 */
	public void setIntitules(Collection<String> intitules) {
		this.intitules = intitules;
	}
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
//	/**
//	 * @return the agglo
//	 */
//	public List<String> getAgglo() {
//		return agglo;
//	}
//	/**
//	 * @param agglo the agglo to set
//	 */
//	public void setAgglo(List<String> agglo) {
//		this.agglo = agglo;
//	}
//	/**
//	 * @return the aPresident
//	 */
//	public List<String> getaPresident() {
//		return aPresident;
//	}
//	/**
//	 * @param aPresident the aPresident to set
//	 */
//	public void setaPresident(List<String> aPresident) {
//		this.aPresident = aPresident;
//	}
//	/**
//	 * @return the president
//	 */
//	public List<String> getPresident() {
//		return president;
//	}
//	/**
//	 * @param president the president to set
//	 */
//	public void setPresident(List<String> president) {
//		this.president = president;
//	}
//	/**
//	 * @return the animal
//	 */
//	public List<String> getAnimal() {
//		return animal;
//	}
//	/**
//	 * @param animal the animal to set
//	 */
//	public void setAnimal(List<String> animal) {
//		this.animal = animal;
//	}
//	/**
//	 * @return the artiste
//	 */
//	public List<String> getArtiste() {
//		return artiste;
//	}
//	/**
//	 * @param artiste the artiste to set
//	 */
//	public void setArtiste(List<String> artiste) {
//		this.artiste = artiste;
//	}
//	/**
//	 * @return the capitale
//	 */
//	public List<String> getCapitale() {
//		return capitale;
//	}
//	/**
//	 * @param capitale the capitale to set
//	 */
//	public void setCapitale(List<String> capitale) {
//		this.capitale = capitale;
//	}
//	/**
//	 * @return the pays
//	 */
//	public List<String> getPays() {
//		return pays;
//	}
//	/**
//	 * @param pays the pays to set
//	 */
//	public void setPays(List<String> pays) {
//		this.pays = pays;
//	}
//	/**
//	 * @return the nobel
//	 */
//	public List<String> getNobel() {
//		return nobel;
//	}
//	/**
//	 * @param nobel the nobel to set
//	 */
//	public void setNobel(List<String> nobel) {
//		this.nobel = nobel;
//	}
//	/**
//	 * @return the ville
//	 */
//	public List<String> getVille() {
//		return ville;
//	}
//	/**
//	 * @param ville the ville to set
//	 */
//	public void setVille(List<String> ville) {
//		this.ville = ville;
//	}
	/**
	 * 
	 */
	public PenduSujetsChallenge() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
