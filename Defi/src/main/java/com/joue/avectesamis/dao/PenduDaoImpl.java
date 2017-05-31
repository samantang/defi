package com.joue.avectesamis.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.joue.avectesamis.entites.Friend;
import com.joue.avectesamis.entites.jeux.Agglo;
import com.joue.avectesamis.entites.jeux.Artistes;
import com.joue.avectesamis.entites.jeux.Nobels;
import com.joue.avectesamis.entites.jeux.Pays_Capitale;
import com.joue.avectesamis.entites.jeux.President;
import com.joue.avectesamis.entites.jeux.pendu.PenduDicoChallenge;
import com.joue.avectesamis.entites.jeux.pendu.PenduDicoSolo;
import com.joue.avectesamis.entites.jeux.pendu.PenduSujetsChallenge;
import com.joue.avectesamis.entites.jeux.pendu.PenduSujetsSolo;

@Transactional
@Repository
public class PenduDaoImpl implements PenduDao {

	@PersistenceContext
	private EntityManager em;
	
	
	
	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Collection<PenduDicoSolo> mesDicoSolos(Long id) {
		Friend moi = em.find(Friend.class, id);
		return moi.getPenduDicoSolos();
	}

	@Override
	public Collection<PenduSujetsSolo> mesSujetsSolos(Long id) {
		Friend moi = em.find(Friend.class, id);
		return moi.getPenduSujetsSolos();
	}

	@Override
	public List<PenduDicoChallenge> mesDicoChallenges(Long id) {
		Friend moi = em.find(Friend.class, id);
		
		return moi.getMesChallengesJouesPenduDico();
	}

	@Override
	public List<PenduSujetsChallenge> mesSujetsChallenges(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePenduDicoSolo(PenduDicoSolo solo, Long id) {
		Friend moi = em.find(Friend.class, id);
		solo.setFriend(moi);
		moi.getPenduDicoSolos().add(solo);
		em.persist(solo);
		em.merge(moi);
		
	}

	@Override
	public void savePenduSujetSolo(PenduSujetsSolo solo, Long m) {
		System.out.println("juste avant le Friend");
		System.out.println("le code "+m);
		Agglo a = em.find(Agglo.class, m);
		System.out.println("après agglo");
		Nobels n = em.find(Nobels.class, m);
		System.out.println("après le Nobel");
		Friend f = em.find(Friend.class, m);
		System.out.println("après le Friend");
//		Friend moi = em.find(Friend.class, m);
//		System.out.println("juste après");
//		solo.setFriend(moi);
//		moi.getPenduSujetsSolos().add(solo);
//		em.persist(solo);
//		em.merge(moi);
		
	}

	@Override
	public void savePenduDicoChallenge(PenduDicoChallenge challenge,
			Long m, Long a) {
		Friend moi = em.find(Friend.class, m);
		Friend ami = em.find(Friend.class, a);
		String code = moi.getChallengeEnAttentesPenduDico().get(ami);		
		
		
		challenge.setCodeIndentification(code);
		challenge.setFriend(moi);
		challenge.setMonFriend(ami);
		em.persist(challenge);
		
		moi.getMesChallengesJouesPenduDico().add(challenge);
		moi.getChallengeEnAttentesPenduDico().remove(ami);
		
		moi.getChallengeEnJouesPenduDico().put(ami, code);
		
		
		em.merge(moi);
		
	}

	@Override
	public void savePenduSujetChallenge(PenduSujetsChallenge challenge,
			Long m, Long a) {
		Friend moi = em.find(Friend.class, m);
		Friend ami = em.find(Friend.class, a);
		String code = moi.getChallengeEnAttentesPenduSujets().get(ami);		
		
		
		challenge.setCodeIndentification(code);
		challenge.setFriend(moi);
		challenge.setMonFriend(ami);
		em.persist(challenge);
		
		moi.getMesChallengesJouesPenduSujets().add(challenge);
		moi.getChallengeEnAttentesPenduSujets().remove(ami);
		
		moi.getChallengeEnJouesPenduSujets().put(ami, code);
		
		
		em.merge(moi);
		
	}

	@Override
	public void sendChallengeDico(Long m, Long a) {
		Friend moi = em.find(Friend.class, m);
		Friend ami = em.find(Friend.class, a);
		
//		pour le code qui indentifiera cette demande
		Integer random =  (int) (Math.random()*(1-100000)+1);
		String nom = moi.getNom();
		String nomAmi =ami.getNom();
		String randomString = random.toString();
		String code = nom+randomString+nomAmi;
		
		moi.getChallengesEnvoyesPenduDico().put(ami, code);
		ami.getChallengesRecusPenduDico().put(moi, code);
		
//		moi.getChallengeenvoiyes().add(ami);
//		ami.getChallengerecus().add(moi);
		em.merge(moi);
		em.merge(ami);
		
	}

	@Override
	public void sedChallengeSujets(Long m, Long a) {
		Friend moi = em.find(Friend.class, m);
		Friend ami = em.find(Friend.class, a);
		
//		pour le code qui indentifiera cette demande
		Integer random =  (int) (Math.random()*(1-100000)+1);
		String nom = moi.getNom();
		String nomAmi =ami.getNom();
		String randomString = random.toString();
		String code = nom+randomString+nomAmi;
		
		moi.getChallengesEnvoyesPenduSujets().put(ami, code);
		ami.getChallengesRecusPenduSujets().put(moi, code);
		
//		moi.getChallengeenvoiyes().add(ami);
//		ami.getChallengerecus().add(moi);
		em.merge(moi);
		em.merge(ami);

		
	}

	@Override
	public void acceptChallengeDico(Long m, Long a) {
		Friend moi = em.find(Friend.class, m);
		Friend ami = em.find(Friend.class, a);
		
		String code = moi.getChallengesRecusPenduDico().get(ami);
		moi.getChallengeEnAttentesPenduDico().put(ami, code);
		ami.getChallengeEnAttentesPenduDico().put(moi, code);
		
		moi.getChallengesRecusPenduDico().remove(ami);
		ami.getChallengesEnvoyesPenduDico().remove(moi);
		
		em.merge(moi);
		em.merge(ami);
		
	}

	@Override
	public void acceptChallengeSujets(Long m, Long a) {
		Friend moi = em.find(Friend.class, m);
		Friend ami = em.find(Friend.class, a);
		
		String code = moi.getChallengesRecusPenduSujets().get(ami);
		moi.getChallengeEnAttentesPenduSujets().put(ami, code);
		ami.getChallengeEnAttentesPenduSujets().put(moi, code);
		
		moi.getChallengesRecusPenduSujets().remove(ami);
		ami.getChallengesEnvoyesPenduSujets().remove(moi);
		
		em.merge(moi);
		em.merge(ami);
		
	}

	@Override
	public List<Friend> mesChallengesDicoEnvoyes(Long m) {
		Friend moi = em.find(Friend.class, m);
//		recup�ration des amis dans la map pour les mettre dans une list
		List<Friend> mesChallenges = new ArrayList<Friend>();
		Set<Entry<Friend, String>> setF = moi.getChallengesEnvoyesPenduDico().entrySet();
		Iterator<Entry<Friend, String>> it = setF.iterator();
		while(it.hasNext()){
			Entry<Friend, String> e = it.next();
			mesChallenges.add(e.getKey());
		}
		
		
		return mesChallenges;
	}

	@Override
	public List<Friend> mesChallengesDicoAttentes(Long m) {
		Friend moi = em.find(Friend.class, m);
//		recup�ration des amis dans la map pour les mettre dans une list
		List<Friend> mesChallenges = new ArrayList<Friend>();
		Set<Entry<Friend, String>> setF = moi.getChallengeEnAttentesPenduDico().entrySet();
		Iterator<Entry<Friend, String>> it = setF.iterator();
		while(it.hasNext()){
			Entry<Friend, String> e = it.next();
			mesChallenges.add(e.getKey());
		}
		
		
		return mesChallenges;
	}

	@Override
	public List<Friend> mesChallengesSujetsEnvoyes(Long m) {
		Friend moi = em.find(Friend.class, m);
//		recup�ration des amis dans la map pour les mettre dans une list
		List<Friend> mesChallenges = new ArrayList<Friend>();
		Set<Entry<Friend, String>> setF = moi.getChallengesEnvoyesPenduSujets().entrySet();
		Iterator<Entry<Friend, String>> it = setF.iterator();
		while(it.hasNext()){
			Entry<Friend, String> e = it.next();
			mesChallenges.add(e.getKey());
		}
		
		
		return mesChallenges;
	}

	@Override
	public List<Friend> mesChallengesSujetsAttentes(Long m) {
		Friend moi = em.find(Friend.class, m);
//		recup�ration des amis dans la map pour les mettre dans une list
		List<Friend> mesChallenges = new ArrayList<Friend>();
		Set<Entry<Friend, String>> setF = moi.getChallengeEnAttentesPenduSujets().entrySet();
		Iterator<Entry<Friend, String>> it = setF.iterator();
		while(it.hasNext()){
			Entry<Friend, String> e = it.next();
			mesChallenges.add(e.getKey());
		}
		
		
		return mesChallenges;
	}

	@Override
	public String getCodeAttenteMoiAmiDico(Long m, Long a) {
		// TODO Auto-generated method stub
		Friend moi = em.find(Friend.class, m);
		Friend ami = em.find(Friend.class, a);
		
		
		return  moi.getChallengeEnAttentesPenduDico().get(ami);
	}

	@Override
	public String getCodeAttenteAmiMoiDico(Long m, Long a) {
		// TODO Auto-generated method stub
		Friend moi = em.find(Friend.class, m);
		Friend ami = em.find(Friend.class, a);
		return ami.getChallengeEnAttentesPenduDico().get(moi);
	}

	@Override
	public String getCodeJoueAmiMoiDico(Long m, Long a) {
		// TODO Auto-generated method stub
		Friend moi = em.find(Friend.class, m);
		Friend ami = em.find(Friend.class, a);
		
		return ami.getChallengeEnJouesPenduDico().get(moi);
	}

	@Override
	public String getCodeAttenteMoiAmiSujets(Long m, Long a) {
		Friend moi = em.find(Friend.class, m);
		Friend ami = em.find(Friend.class, a);
		return moi.getChallengeEnAttentesPenduSujets().get(ami);
	}

	@Override
	public String getCodeAttenteAmiMoiSujets(Long m, Long a) {
		Friend moi = em.find(Friend.class, m);
		Friend ami = em.find(Friend.class, a);
		return ami.getChallengeEnAttentesPenduSujets().get(moi);
	}

	@Override
	public String getCodeJoueAmiMoiSujets(Long m, Long a) {
		Friend moi = em.find(Friend.class, m);
		Friend ami = em.find(Friend.class, a);
		return ami.getChallengeEnJouesPenduSujets().get(moi);
	}

	@Override
	public List<String> challengeSujetAgglo(char c) {
		Query req = em.createQuery("select c from Agglo c where c.nom like'%"+c+"%'");
		return req.getResultList();
	}

	@Override
	public List<String> challengeSujetAnimaux(char c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> challengeSujetApresi(char c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<President> challengeSujetPresi(char c) {
		Query req = em.createQuery("select c from President c where c.nom like'%"+c+"%'");
		return req.getResultList();
	}

	@Override
	public List<Artistes> challengeSujetArtiste(char c) {
		Query req = em.createQuery("select c from Artistes c where c.nom like'%"+c+"%'");
		return req.getResultList();
	} 

	@Override
	public List<Nobels> challengeSujetNobel(char c) {
		Query req = em.createQuery("select c from Nobels c where c.nom like'%"+c+"%'");
		return req.getResultList();
	}

	@Override
	public List<Pays_Capitale> challengeSujetPays(char c) {
		Query req = em.createQuery("select c from Pays_Capitale c where c.pays like'%"+c+"%'");
		return req.getResultList();
	}

	@Override
	public List<Pays_Capitale> challengeSujetCapitale(char c) {
		Query req = em.createQuery("select c from Pays_Capitale c where c.capitale like'%"+c+"%'");
		return req.getResultList();
	}

	@Override
	public List<String> challengeSujetVilleF(char c) {
		// TODO Auto-generated method stub
		return null;
	}

}
