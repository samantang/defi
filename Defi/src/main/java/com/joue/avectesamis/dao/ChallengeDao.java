package com.joue.avectesamis.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.joue.avectesamis.entites.AbcChallenge;
import com.joue.avectesamis.entites.AbcSolo;
import com.joue.avectesamis.entites.AbcSoloJeux;
import com.joue.avectesamis.entites.Album;
import com.joue.avectesamis.entites.Comment;
import com.joue.avectesamis.entites.Friend;
import com.joue.avectesamis.entites.Message;
import com.joue.avectesamis.entites.Post;
import com.joue.avectesamis.entites.jeux.APresident;
import com.joue.avectesamis.entites.jeux.Agglo;
import com.joue.avectesamis.entites.jeux.Animaux;
import com.joue.avectesamis.entites.jeux.Artistes;
import com.joue.avectesamis.entites.jeux.Nobels;
import com.joue.avectesamis.entites.jeux.Pays_Capitale;
import com.joue.avectesamis.entites.jeux.President;
import com.joue.avectesamis.entites.jeux.VilleFrance;



public interface ChallengeDao {

	public Friend monProfil(Long moi);
	public Friend getFriend(Long id);
	public void enregistreFriend(Friend f);
	public List<Friend> listeToutLeMonde();
	public Friend metAJour(Friend moi);
	public Friend getFriendByEmail(String email);
	public void ajouterPhot(Long id, byte [] photo, String nomPhoto);

	public void envoyerDemandeAmi(Long moi, Long ami);
	public void accepterAmi(Long moi, Long ami);
	public void supprimerAmi(Long moi, Long ami);
	public void rejeterAmi(Long moi, Long ami);
	public int nombreDemandeAmiRecues(Long moi);
	public List<Friend> demandesRecues (Long moi);
	public List<Friend> mesAmis (Long moi);
//	savoir si deux personnes sont amis
	public boolean estAmiAvec(Long moi, Long ami);
	
	public void posterPost(Long moi, String messagePost);
	public void posterPost(Post p);
	public void commenterPost(Long moi, Long idPost, String commentaire);
	public void aimerPost(Long moi, Long idPost);
	public void neplusAimerPost(Long moi, Long idPost);
	public void supprimerPost(Long moi, Long idPost);
	public int compterNBlikePost(Long p);
	public List<Post> getPosts();
	
	public void envoyerMessage(Long moi, Long m, String mes);
	public List<Message> messagesEnvoyes(Long m);
	public List<Message> messagesRecus(Long m);
	
	
	public List<Friend> demandesChallengesRecus(Long m);
//	public void enregistrerChallenge(Long moi, Long ami, Abc_Challenge c);
	public int nbChallengeRecus(Long m);
	
	public void creerAlbum(Long m);
	public List<Album> mesAlbums(Long m);
	
	public List<Comment> getComments ();
	
	
	// POUR ABC_SOLO
	public void saveAbcSolo(Long m, AbcSolo abc, AbcSoloJeux abcjeu);
	public List<AbcSolo> getMesSolos(Long m);
	public List<President> getPresidents(char lettre);
	public List<Pays_Capitale> getPays(char lettre);
	public List<Pays_Capitale> getCapitales(char lettre);
	public List<Agglo> getAgglos(char lettre);
	public List<Animaux> getAnimaux(char lettre);
	public List<APresident> getAPresidents(char lettre);
	public List<Nobels> getNobels(char lettre);
	public List<VilleFrance> getVillesFrance(char lettre);
	public List<Artistes> getArtistes(char lettre);
	public void supprimerSolo(Long id);
	public void pubierSolo(Long idJeu, Long moi);
	public List<AbcSoloJeux> mesDerniersSolo(Long moi);
	public AbcSolo detailsSolo(Long id);
	
	// POUR ABC_CHALLENGE
//	public List<AbcChallenge>  mesChallenges(Long id);
	public List<AbcChallenge> mesDerniersChallenges(Long moi);
	public void envoyerChallenge(Long moi, Long ami);
	public void accepterChallenge(Long moi, Long ami);
	public void saveChallenge(Long m, Long a, AbcChallenge challenge);
	public List<Friend>	mesChallengesRecus(Long moi);
	public List<Friend>	mesChallengesEnvoyes(Long moi);
	public List<Friend>	mesChallengesEnAttentes(Long moi);
	public List<Friend> mesChallengesJoues(Long m);
	public List<AbcChallenge> mesChallengesJoues(Long moi, Long ami);
//	les amis pour lesquels Annuler la demande
	public List<Friend> amisAnnulerDemande(Long moi);
//	les amis pour lesquels Accepter la demande
	public List<Friend> amisAccepterDemande(Long moi);
//	les amis pour lesquels Envoyer une demande
	public List<Friend> amisEnvoyerDemande(Long moi);
//	les amis avec lesquels Jouer
	public List<Friend> amisJouerAvec(Long moi);
	public List<Friend> tousLesInscrits();
	public void annulerEnvoiChallenge (Long moi, Long ami);
	public void refuserChallenge (Long moi, Long ami);
	public void jouerChallenge (Long moi, Long ami);
	public void challengerChallenge (Long moi, Long ami);
	
	
	
	
	public String getCodeAttenteMoiAmi(Long moi, Long ami);
	public String getCodeAttenteAmiMoi(Long moi, Long ami);
	public String getCodeJoueAmiMoi(Long moi, Long ami);

}
