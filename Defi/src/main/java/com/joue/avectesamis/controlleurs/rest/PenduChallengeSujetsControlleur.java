package com.joue.avectesamis.controlleurs.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joue.avectesamis.dao.PenduDao;
import com.joue.avectesamis.entites.AbcChallenge;
import com.joue.avectesamis.entites.AbcSolo;
import com.joue.avectesamis.entites.Friend;
import com.joue.avectesamis.entites.jeux.Artistes;
import com.joue.avectesamis.entites.jeux.Nobels;
import com.joue.avectesamis.entites.jeux.Pays_Capitale;
import com.joue.avectesamis.entites.jeux.President;
import com.joue.avectesamis.entites.jeux.pendu.PenduDicoChallenge;
import com.joue.avectesamis.entites.jeux.pendu.PenduSujetsChallenge;
import com.joue.avectesamis.entites.jeux.pendu.PenduSujetsSolo;
import com.joue.avectesamis.metier.ChallengeMetier;
import com.joue.avectesamis.models.PenduModel;
import com.joue.avectesamis.models.Word;

@Controller
public class PenduChallengeSujetsControlleur {

	@Autowired
	private PenduDao penduDao;
	@Autowired
	private ChallengeMetier metier;
	
	@RequestMapping(value="penduChallengeSujets", method=RequestMethod.GET)
	public String penduChallengeSujets(Model model, HttpServletRequest req, PenduModel penduModel, Word word ){
//		word = new Word(req);
		word.initWord(req);
		
//		la map qui va stocker tous les objets
		Map<String, Object> map = new HashMap<String, Object>();
		
		HttpSession session = req.getSession();
		Long id =  1L;
//				(Long) session.getAttribute("id");
		Friend moi = metier.getFriend(id);
		
//		penduModel.setMesChallengesDico(penduDao.mesDicoChallenges(id));
		penduModel.setMesChallengesSujetsJoues(penduDao.mesSujetsChallenges(id));
//		Collection<PenduDicoChallenge> mesChallengesDico=penduDao.mesDicoChallenges(id);
//		List<Friend> mesChallengesDicoEnvoyes = penduDao.mesChallengesDicoEnvoyes(id);
//		penduModel.setMesChallengesDicoEnvoyes(penduDao.mesChallengesDicoEnvoyes(id));
		penduModel.setMesChallengesSujetsEnvoyes(penduDao.mesChallengesSujetsEnvoyes(id));
//		gm.setMesChallengesEnvoyes(penduDao.mesChallengesDicoEnvoyes(id));
//		List<Friend> mesChallengesDicoEnAttentes = penduDao.mesChallengesDicoAttentes(id);
//		penduModel.setMesChallengesDicoEnAttentes(penduDao.mesChallengesDicoAttentes(id));
		penduModel.setMesChallengesSujetsEnAttentes(penduDao.mesChallengesSujetsAttentes(id));
//		gm.setMesChallengesEnAttentes(penduDao.mesChallengesDicoAttentes(id));
//		List<Friend> mesChallengesDicoAttentes = penduDao.mesChallengesDicoAttentes(id);
//		gm.setMesChallengesJoues(metier.mesChallengesJou�s(id));
//		List<PenduDicoChallenge> mesChallengesJoues = penduDao.mesDicoChallenges(id);
		List<PenduSujetsChallenge> mesChallengesJoues = penduDao.mesSujetsChallenges(id);
//		mesChallengesJoues.addAll(penduDao.mesDicoChallenges(id));
//		creation d'une map qui aura comme cl�f le code d'identificaion et comme valeur l'id de l'ami
		Map<String, Long> codeAmi = new HashMap<String, Long>();
//		pour les id des amis avec qui j'ai jou�
//		List<Long> idAmisJoues = new ArrayList<Long>();
//		pour les codes d'identificaions
//		List<String> codesInd = new ArrayList<String>();
		
//		les challenges de mes amis
		List<PenduSujetsChallenge> challengesAmis = new ArrayList<PenduSujetsChallenge>();
		PenduSujetsChallenge challenge = new PenduSujetsChallenge();
		Map<Friend, String> attentesAmi = new HashMap<Friend, String>();

		
		
		if (mesChallengesJoues!=null) {
			for (PenduSujetsChallenge c : mesChallengesJoues) {
				System.out.println("les elements du challengeJoue: "
						+ c.getCodeIndentification() + " "
						+ c.getMonFriend().getId());
				//			recup�ration de l'id de l'ami associc� au jeu et du code d'identification pour les stocker dans une map
				//			ensuite pour chaque id on recup�re l'ami correspondant 
				//			
				codeAmi.put(c.getCodeIndentification(), c.getMonFriend()
						.getId());
			}
			//		recup�ration des ids dans la map pour cr�er les friends, puis comparer le code d'identification de la map avec les codes d'identifcation qui se trouvent 
			//		dans les jeux d�j� jou�s de l'ami: s'il s'y trouvent alors l'ami a jou� donc r�cup�ration du jeu: si non alors il n'a pas encore jou�
		}
		Friend ami= null;
		Set<Entry<String, Long>> setAmi = codeAmi.entrySet();
		Iterator<Entry<String, Long>> it = setAmi.iterator();
		while(it.hasNext()){
			Entry<String, Long> e = it.next();
//			recup�ration de l'ami par rappport � l'id
			 ami = new Friend();
			ami=metier.getFriend(e.getValue());
			System.out.println("l'id de l'ami pour qui j'ai jou� le challenge ..."+ami.getId());
//			les challenges jou�s de l'ami
//			List<PenduSujetsChallenge> challengesAmi= ami.getMesChallengesJouesPenduDico();
			List<PenduSujetsChallenge> challengesAmi= ami.getMesChallengesJouesPenduSujets();
//			parcourt des challenges jou�s par l'ami
			for(PenduSujetsChallenge c:challengesAmi){
//				si on trouve un code d'identification dans ses challenges jou�s qui �quivaut au mien, recup�ration de tout le jeu de l'ami en rapport avec ce code
				System.out.println("le code indentification ami=="+c.getCodeIndentification()+" le getKey de chez moi== "+e.getKey());
				if(c.getCodeIndentification().equals(e.getKey())){
//					l'ami a d�j� jou� => recup�ration du jeu en question
					challenge = new PenduSujetsChallenge();
//							(c.getId(),c.getCodeIndentification(), c.getEmail(), c.getScore(), c.getDate(), c.getLettre(), c.getTime(), c.getHelp(), c.getAgglo(), c.getaPresident(), c.getPresident(), c.getAnimal(), c.getArtiste(), c.getCapitale(), c.getPays(), c.getNobel(), c.getVille(), c.getTimeOut(), c.getMonFriend(), c.getFriend());
					challengesAmis.add(challenge);
				}
				else{
//					parcourt des jeux en attente chez l'ami, si on retrouve le code d'indtification dans les jeux en attentes alors il n'a pas encore jou�
//					
					attentesAmi=ami.getChallengeEnAttentes();
					Set<Entry<Friend, String>> setAttenteAmi = attentesAmi.entrySet();
					Iterator<Entry<Friend, String>> itAttenteAmi = setAttenteAmi.iterator();
					while(itAttenteAmi.hasNext()){
						Entry<Friend, String> entryAmi = itAttenteAmi.next();
						System.out.println("e.getKey() dans attente == "+e.getKey());
						System.out.println("code d'identification attente ===="+entryAmi.getValue());
						if(e.getKey().equals(entryAmi.getValue())){
							challenge = new PenduSujetsChallenge();
							challengesAmis.add(challenge);
							System.out.println("l'ami n'a pas encore jou� ---------------");
						}
					}
				}
			}
		}
		
		
		System.out.println("\n");
//		comme l'affichage de des challenges jou�s par les amis ne se fait pas dans l'ordre souhait� (pour �a on PEUT utlitiser l'interface QUEUE), mais ici
//		on parcourt mes challenges jou�s et on recup�re le code d'identification qu'on compare � celui des jeux de mes amis
//		on remplit une autre liste de challengesAmisOrdre dans laquelle on insere � l'indice correspondant � celui de mes jeux (par rapport au code d'indentification)
//		comme �a dans les deux listes (mes jeux et les jeux de mes amis), les jeux qui ont le meme code d'indentification ont le le m�me index, ce qui facilite l'affichage
//		En fin on va couper la nouvelle liste qui aura la m�me longuer que la liste de mes jeux car tous mes jeux qui ont aussi �t� jou�s par un amis sont d�j� dans la nouvelle
//		liste en fonction de l'index
//		Deque<AbcChallenge> challengeAmiQueue = new ArrayDeque<AbcChallenge>();
		List<PenduSujetsChallenge> challengeAmisOrdre = new ArrayList<PenduSujetsChallenge>();
		PenduSujetsChallenge challengea = null;
		List<String> listeCodeAmi = new ArrayList<String>();
		if (mesChallengesJoues!=null) {
			for (PenduSujetsChallenge abcMoi : mesChallengesJoues) {
				for (PenduSujetsChallenge abcAmi : challengesAmis) {
					listeCodeAmi.add(abcAmi.getCodeIndentification());
					if (abcMoi.getCodeIndentification().equals(
							abcAmi.getCodeIndentification())) {
						//					challengeAmiQueue.offerLast(abcAmi);
						challengeAmisOrdre.add(
								mesChallengesJoues.indexOf(abcMoi), abcAmi);

					} else {
//						challengea = new PenduSujetsChallenge(null, null, 0,
//								true, null, 0, true, 0, null, 0, true, 0, null,
//								null, null);
						//					challengeAmiQueue.offerLast(challengea);
						challengeAmisOrdre.add(challengea);

					}
				}
			}
		}
		//		la taille de mes jeux
		int taille=0;
		if (mesChallengesJoues!=null) {
			taille = mesChallengesJoues.size();
		}
		List<PenduSujetsChallenge> challengeAmisOrdreCoupe=challengeAmisOrdre.subList(0, taille);
		
//		Mixage des deux listes pour faciliter l'affichage des duels dans le detail des challenges
		List<PenduSujetsChallenge> mixChallenge = new ArrayList<PenduSujetsChallenge>();
		if (mesChallengesJoues!=null) {
			mixChallenge = mixageList(mesChallengesJoues,challengeAmisOrdreCoupe);
		}
		//		mise du mixchallenge en session pour l'affichage des details des duels
		session.setAttribute("mixChallenge", mixChallenge);
		
		for(PenduSujetsChallenge c: mixChallenge){
			System.out.println("LES CODE D'IDENTIFICATIONS DANS LE MIXCHALLENGE SONT "+c.getCodeIndentification());
		}
		
		penduModel.setChallengeAmisOrdreSujetsCoupe(challengeAmisOrdreCoupe);
		penduModel.setMesChallengesSujetsJoues(mesChallengesJoues);
//	    recup�ration des derniers jeux
	   List<AbcChallenge> mesChallenges= metier.mesDerniersChallenges(id);
	    List<AbcSolo> mesSolos =  metier.getMesSolos(id);
	    
	    model.addAttribute("challengeAmisOrdreCoupe", challengeAmisOrdreCoupe);	  
	    map.put("challengeAmisOrdreCoupe", challengeAmisOrdreCoupe);
	    
	    
	    model.addAttribute("mesChallenges", mesChallenges);
	    map.put("mesChallenges", mesChallenges);
	    model.addAttribute("mesSolos", mesSolos);
	    map.put("mesSolos", mesSolos);
		
//		System.out.println("mes challengesJou�s:la taille "+mesChallengesJoues.size());
//		System.out.println("les challengesJou�sAmis: la taille "+challengesAmis.size());
//		penduModel.setMesChallengesDicoJoues(mesChallengesJoues);
		penduModel.setMesChallengesSujetsJoues(mesChallengesJoues);
	    model.addAttribute("mesSolos", mesSolos);
//		penduModel.setChallengesDicoAmis(challengesAmis);
		penduModel.setChallengesSujetsAmis(challengesAmis);
	    model.addAttribute("mesSolos", mesSolos);
		session.setAttribute("mesChallengesJoues", mesChallengesJoues);
		session.setAttribute("challengesAmis", challengesAmis);
		model.addAttribute("penduModel", penduModel);
	     map.put("penduModel", penduModel);
		model.addAttribute("mesChallengesJoues", mesChallengesJoues);
	     map.put("mesChallengesJoues", mesChallengesJoues);
		model.addAttribute("challengesAmis", challengesAmis);
	    map.put("challengesAmis", challengesAmis);
		model.addAttribute("moi", moi);
	    map.put("moi", moi);
	    map.put(" leMot", word);
		
		return "penduChallengeSujets";
		
	}
//	Methode qui permet de mixer deux ArrayList
	public List<PenduSujetsChallenge> mixageList(List<PenduSujetsChallenge> mesChallengesJoues, List<PenduSujetsChallenge> challengeAmisOrdreCoupe ){
		List<PenduSujetsChallenge> resultat = new ArrayList<PenduSujetsChallenge>();
		int size = Math.max(mesChallengesJoues.size(), challengeAmisOrdreCoupe.size());
		
		for (int i = 0; i < size; i++) {
	        if (i < mesChallengesJoues.size()) resultat.add(mesChallengesJoues.get(i));
	        if (i < challengeAmisOrdreCoupe.size()) resultat.add(challengeAmisOrdreCoupe.get(i));
	    }

		
		return resultat;
		
	}
	
	@RequestMapping(value="accepterPenduSujetChallenge", method=RequestMethod.GET)
	public String accepterPenduSujetChallenge(Model model, PenduModel penduModel, HttpServletRequest request){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		Long id =  (Long) session.getAttribute("id");
		
//		recup�raiton de l'ami
		Long idAmi= (long) Integer.parseInt(request.getParameter("idAmi"));
//		metier.accepterChallenge(id, idAmi);
		penduDao.acceptChallengeSujets(id, idAmi);
		
//		gm.setMesChallengesRecus(metier.mesChallengesRecus(id));
//		penduModel.setMesChallengesDico(penduDao.mesDicoChallenges(id));
		penduModel.setMesChallengesSujetsJoues(penduDao.mesSujetsChallenges(id));
//		gm.setMesChallengesEnvoyes(metier.mesChallengesEnvoyes(id));
//		penduModel.setMesChallengesDicoEnvoyes(penduDao.mesChallengesDicoEnvoyes(id));
		penduModel.setMesChallengesSujetsEnvoyes(penduDao.mesChallengesSujetsEnvoyes(id));
//		gm.setMesChallengesEnAttentes(metier.mesChallengesEnAttentes(id));
		penduModel.setMesChallengesDicoEnAttentes(penduDao.mesChallengesDicoAttentes(id));
		penduModel.setMesChallengesSujetsEnAttentes(penduDao.mesChallengesSujetsAttentes(id));
		
		
		
		model.addAttribute("penduModel", penduModel);
		map.put("penduModel", penduModel);
		
		return "accepterSujetChallenge";
		
	}
	@RequestMapping(value="penduChallengeSujetsJeu", method=RequestMethod.GET)
	public String penduChallengeSujetsJeu(Model model, PenduModel penduModel, HttpServletRequest request){
		System.out.println("dans la methode penduChallengeSujetJeu");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		Long id = 2L; 
//				(Long) session.getAttribute("id");
//		le temps en fonction du nombre de r�sultats possibles 
		int temps =0;
		
//		recup�raiton de l'ami
		Long idAmi= 3L;
//				(long) Integer.parseInt(request.getParameter("idAmi"));
		session.setAttribute("idAmi", idAmi);
		Friend ami= metier.getFriend(idAmi);
		
//		moi
		Friend moi = metier.getFriend(id);
		
//		le mot dans le DICO ou celui avec lequel il a joué (dans le cas ou il a joué avant moi)
		String mot = "";
		
		
//		Random r = new Random();
//		char lettre = 'x';
//		le code du jeu chez moi
		String codeAttenteMoiSujets= penduDao.getCodeAttenteMoiAmiSujets(id, idAmi);
		System.out.println("le code en attente chez moi "+codeAttenteMoiSujets);
//		le code chez mon ami s'il n'a pas encore jou�
		String codeAttenteAmiSujets=penduDao.getCodeAttenteAmiMoiSujets(id, idAmi);
		System.out.println("le code en attente chez mon ami "+codeAttenteAmiSujets);
//		le code chez mon ami s'il a d�j� jou�
		String codeJoueAmiSujets = penduDao.getCodeJoueAmiMoiSujets(id, idAmi);
		
//		char lettre ='x';
//		Random random = new Random();
		
//		les maps qui vont stocker pour chaque intitule, le mot et la lettre
		Map<Character, String> challengeSujetAgglo = new HashMap<Character, String>();
		
//		si mon code en attente est egale au code en attete de l'ami, ça veut dire qu'il n'ap pas encore joué, alors
//		il faut tirer un mot dans le dico, sinon recuperer le mot avec lequel il a joue
	
		if (codeAttenteMoiSujets!=null && codeAttenteAmiSujets!=null ) {
			System.out.println("les deux codes en attentes ne sont pas null ");
			if(codeAttenteMoiSujets.equals(codeAttenteAmiSujets)){
				System.out.println("il n'a pas encore joué .....");
				//			il n'a pas encore joue ALORS ...
							
				//			on tire une lettre au hasard
				//			choisir dans un intitule de la BDD les mots contenant cette lettre
				//			garder un seul mot
				//			enregistrer dans une map qui va porter le nom de l'intitule, le mot et la lettre
				//			FAIRE CECI POUR TOUS LES INTITULES
							
				//			agglomerations
//							lettre = (char) (random.nextInt(26)+'a');			
//							List<String> sujetAgglo= penduDao.challengeSujetAgglo(lettre);
//							String csa = sujetAgglo.get(random.nextInt(sujetAgglo.size()));
//							 challengeSujetAgglo = new HashMap<Character, String>();
//							challengeSujetAgglo.put(lettre, csa);		
//							map.put("challengeSujetAgglo", challengeSujetAgglo);
				
					chargementDesSujets(model, request);
							
						}else{
							for(PenduSujetsChallenge c:ami.getMesChallengesJouesPenduSujets()){
								if(c.getCodeIndentification().equals(codeAttenteMoiSujets)){
//									recupéretion dans le jeux de mon ami car moi aussi je dois jouer avec les memes lettres et mots
									
//									LES PAYS
									String motPays = c.getPaysS();
									char lettrePays = Character.toUpperCase(c.getLettrePays());
									
									String motCachePays="";
									String motCacheDepartPays="";
									for (int i = 0; i < motPays.length(); i++) {
										motCachePays = new String(motCachePays+"*");
										if (motPays.charAt(i)==lettrePays) {
											motCacheDepartPays = new String(motCacheDepartPays+lettrePays);
										} else {
											motCacheDepartPays = new String(motCacheDepartPays+"*");
										}
									}
									int longueurMotPays = motPays.length();
									
									System.out.println("le pays choisit est : "+motPays);
									String idBouton = String.valueOf(lettrePays);
									String lettreStringPays = String.valueOf(lettrePays);
									
									session.setAttribute("lettreStringPays", lettreStringPays);
									session.setAttribute("motPays", motPays);
									
									model.addAttribute("motCacheDepartPays", motCacheDepartPays);
									model.addAttribute("motCachePays", motCachePays);
									model.addAttribute("motPays", motPays);
									model.addAttribute("lettrePays", lettrePays);
									model.addAttribute("idBoutonPays", idBouton+"Pays");
									model.addAttribute("longueurMotPays", longueurMotPays);
									
									
//									LES CAPITALES 
									String motCapitales = c.getCapitaleS();
									char lettreCapitales = Character.toUpperCase(c.getLettreCapitale());
									
									String motCacheCapitales="";
									String motCacheDepartCapitales="";
									for (int i = 0; i < motCapitales.length(); i++) {
										motCacheCapitales = new String(motCacheCapitales+"*");
										if (motCapitales.charAt(i)==lettreCapitales) {
											motCacheDepartCapitales = new String(motCacheDepartCapitales+lettreCapitales);
										} else {
											motCacheDepartCapitales = new String(motCacheDepartCapitales+"*");
										}
									}
									int longueurMotCapitales = motCapitales.length();
									
									System.out.println("la capitale choisie est: "+motCapitales);
									String idBoutonCaptiale = String.valueOf(lettreCapitales);
									String lettreStringCapitale = String.valueOf(lettrePays);
									
									session.setAttribute("lettreStringCapitales", lettreStringCapitale);
									session.setAttribute("motCapitales", motCapitales);
									
									
									model.addAttribute("motCacheDepartCapitales", motCacheDepartCapitales);
									model.addAttribute("motCacheCapitales", motCacheCapitales);
									model.addAttribute("motCapitales", motCapitales);
									model.addAttribute("lettreCapitales", lettreCapitales);
									model.addAttribute("longueurMotCapitales", longueurMotCapitales);
									model.addAttribute("idBoutonCaptiales", idBoutonCaptiale+"Capitales");
									
									
//									LES NOBELS
									String motNobels = c.getNobelS();
									char lettreNobels = Character.toUpperCase(c.getLettreNobel());
									
									String motCacheNobels="";
									String motCacheDepartNobels ="";
									for (int i = 0; i < motNobels.length(); i++) {
										motCacheNobels = new String(motCacheNobels+"*");
										if (motNobels.charAt(i)==lettreNobels) {
											motCacheDepartNobels = new String(motCacheDepartNobels+lettreNobels);
										} else {
											motCacheDepartNobels = new String(motCacheDepartNobels+"*");
										}
									}
									int longueurMotNobels = motNobels.length();
									
									System.out.println("la Nobel choisie est: "+motNobels);
									String idBoutonNobels = String.valueOf(lettreNobels);
									String lettreStringNobels = String.valueOf(lettreNobels);
									
									session.setAttribute("lettreStringNobels", lettreStringNobels);
									session.setAttribute("motNobels", motNobels);
									
									model.addAttribute("motCacheDepartNobels", motCacheDepartNobels);
									model.addAttribute("motCacheNobels", motCacheNobels);
									model.addAttribute("motNobels", motNobels);
									model.addAttribute("lettreNobels", lettreNobels);
									model.addAttribute("longueurMotNobels", longueurMotNobels);
									model.addAttribute("idBoutonNobels", idBoutonNobels+"Nobels");
									
									
//									LES ARTISTES
									String motArtistes = c.getArtisteS();
									char lettreArtistes = Character.toUpperCase(c.getLettreArtiste());
									
									String motCacheArtistes="";
									String motCacheDepartArtistes ="";
									for (int i = 0; i < motArtistes.length(); i++) {
										motCacheArtistes = new String(motCacheArtistes+"*");
										if (motArtistes.charAt(i)==lettreArtistes) {
											motCacheDepartArtistes = new String(motCacheDepartArtistes+lettreArtistes);
										} else {
											motCacheDepartArtistes = new String(motCacheDepartArtistes+"*");
										}
									}
									int longueurMotArtistes = motArtistes.length();
									
									System.out.println("lArtiste choisi est: "+motArtistes);
									String idBoutonArtistes = String.valueOf(lettreArtistes);
									String lettreStringArtistes = String.valueOf(lettreArtistes);
									
									session.setAttribute("lettreStringArtistes", lettreStringArtistes);
									session.setAttribute("motArtistes", motArtistes);
									
									model.addAttribute("motCacheDepartArtistes", motCacheDepartArtistes);
									model.addAttribute("motCacheArtistes", motCacheArtistes);
									model.addAttribute("motArtistes", motArtistes);
									model.addAttribute("lettreArtistes", lettreArtistes);
									model.addAttribute("longueurMotArtistes", longueurMotArtistes);
									model.addAttribute("idBoutonArtistes", idBoutonArtistes+"Artistes");
									
									
//									LES PRESIDENTS
									String motPresident = c.getPresidentS();
									char lettrePresident = Character.toUpperCase(c.getLettrePresident());
									
									String motCachePresident="";
									String motCacheDepartPresident="";
									for (int i = 0; i < motPresident.length(); i++) {
										motCachePresident = new String(motCachePresident+"*");
										if (motPresident.charAt(i)==lettrePresident) {
											motCacheDepartPresident = new String(motCacheDepartPresident+lettrePresident);
										} else {
											motCacheDepartPresident = new String(motCacheDepartPresident+"*");
										}
									}
									int longueurMotPresident = motPresident.length();
									
									System.out.println("le Presi choisi est: "+motPresident);
									String idBoutonPresident = String.valueOf(lettrePresident);
									String lettreStringPresident = String.valueOf(lettrePresident);
									
									session.setAttribute("lettreStringPresident", lettreStringPresident);
									session.setAttribute("motPresident", motPresident);
									
									model.addAttribute("motCacheDepartPresident", motCacheDepartPresident);
									model.addAttribute("motCachePresident", motCachePresident);
									model.addAttribute("motPresident", motPresident);
									model.addAttribute("lettrePresident", lettrePresident);
									model.addAttribute("longueurMotPresident", longueurMotPresident);
									model.addAttribute("idBoutonPresident", idBoutonPresident+"President");
									
									
									
									
									
									int longueurMot = longueurMotPays+longueurMotCapitales+longueurMotNobels+longueurMotArtistes+longueurMotPresident;
									model.addAttribute("longueurMot", longueurMot);
														
								return "penduChallengeSujetJeu";
								}					
							}
						}
		}
			
		

					
//					map.put("mot", mot);
//					map.put("word", word);
				
		
		return "penduChallengeSujetsJeu";
		
	}
	private void chargementDesSujets(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println("nous sommes dans la methode de chargement des sujets");
//		Pays
		Random random = new  Random();
		char lettre = (char) (random.nextInt(26)+'a');
		char lettrePays = Character.toUpperCase(lettre);
		List<String> sujetPays = new ArrayList<String>();
		List<Pays_Capitale> sujetPaysP= penduDao.challengeSujetPays(lettre);
		
		for (Pays_Capitale pays_Capitale : sujetPaysP) {
			sujetPays.add(pays_Capitale.getPays());
		}
		String csPays= sujetPays.get(random.nextInt(sujetPays.size()));
		String motPaysEspace = csPays.toUpperCase();
		
//		pour la suppression des espaces
		String motPays = motPaysEspace.replaceAll("\\s", "");
								
		String motCachePays="";
		String motCacheDepartPays="";
		for (int i = 0; i < motPays.length(); i++) {
			motCachePays = new String(motCachePays+"*");
			if (motPays.charAt(i)==lettrePays) {
				motCacheDepartPays = new String(motCacheDepartPays+lettrePays);
			} else {
				motCacheDepartPays = new String(motCacheDepartPays+"*");
			}
		}
		int longueurMotPays = motPays.length();
		
		System.out.println("le pays choisit est : "+motPays);
		String idBouton = String.valueOf(lettrePays);
		String lettreStringPays = String.valueOf(lettrePays);
		
		session.setAttribute("lettreStringPays", lettreStringPays);
		session.setAttribute("motPays", motPays);
		
		model.addAttribute("motCacheDepartPays", motCacheDepartPays);
		model.addAttribute("motCachePays", motCachePays);
		model.addAttribute("motPays", motPays);
		model.addAttribute("lettrePays", lettrePays);
		model.addAttribute("idBoutonPays", idBouton+"Pays");
		model.addAttribute("longueurMotPays", longueurMotPays);
		
		
//		Capitales
		lettre = (char) (random.nextInt(26)+'a');
		char lettreCapitales = Character.toUpperCase(lettre);
		
		List<String> sujetCapitales = new ArrayList<String>();
		List<Pays_Capitale> sujetCapitalesP= penduDao.challengeSujetCapitale(lettre);
		for (Pays_Capitale pays_Capitale : sujetCapitalesP) {
			sujetCapitales.add(pays_Capitale.getCapitale());
		}		
		String csCapitales= sujetCapitales.get(random.nextInt(sujetCapitales.size()));
		String motCapitalesEspace = csCapitales.toUpperCase();
		
		String motCapitales = motCapitalesEspace.replaceAll("\\s", "");
								
		String motCacheCapitales="";
		String motCacheDepartCapitales="";
		for (int i = 0; i < motCapitales.length(); i++) {
			motCacheCapitales = new String(motCacheCapitales+"*");
			if (motCapitales.charAt(i)==lettreCapitales) {
				motCacheDepartCapitales = new String(motCacheDepartCapitales+lettreCapitales);
			} else {
				motCacheDepartCapitales = new String(motCacheDepartCapitales+"*");
			}
		}
		int longueurMotCapitales = motCapitales.length();
		
		System.out.println("la capitale choisie est: "+motCapitales);
		String idBoutonCaptiale = String.valueOf(lettreCapitales);
		String lettreStringCapitale = String.valueOf(lettrePays);
		
		session.setAttribute("lettreStringCapitales", lettreStringCapitale);
		session.setAttribute("motCapitales", motCapitales);
		
		
		model.addAttribute("motCacheDepartCapitales", motCacheDepartCapitales);
		model.addAttribute("motCacheCapitales", motCacheCapitales);
		model.addAttribute("motCapitales", motCapitales);
		model.addAttribute("lettreCapitales", lettreCapitales);
		model.addAttribute("longueurMotCapitales", longueurMotCapitales);
		model.addAttribute("idBoutonCaptiales", idBoutonCaptiale+"Capitales");
		
		
//		Nobels
		lettre = (char) (random.nextInt(26)+'a');
		char lettreNobels = Character.toUpperCase(lettre);
		
		List<String> sujetNobels= new ArrayList<String>();
		List<Nobels> sujetNobelsN = penduDao.challengeSujetNobel(lettre);
		for (Nobels nobels : sujetNobelsN) {
			sujetNobels.add(nobels.getNom());
		}			
		String csNobels= sujetNobels.get(random.nextInt(sujetNobels.size()));
		String motNobelsEspace = csNobels.toUpperCase();
		
		String motNobels = motNobelsEspace.replaceAll("\\s", "");
								
		String motCacheNobels="";
		String motCacheDepartNobels ="";
		for (int i = 0; i < motNobels.length(); i++) {
			motCacheNobels = new String(motCacheNobels+"*");
			if (motNobels.charAt(i)==lettreNobels) {
				motCacheDepartNobels = new String(motCacheDepartNobels+lettreNobels);
			} else {
				motCacheDepartNobels = new String(motCacheDepartNobels+"*");
			}
		}
		int longueurMotNobels = motNobels.length();
		
		System.out.println("la Nobel choisie est: "+motNobels);
		String idBoutonNobels = String.valueOf(lettreNobels);
		String lettreStringNobels = String.valueOf(lettreNobels);
		
		session.setAttribute("lettreStringNobels", lettreStringNobels);
		session.setAttribute("motNobels", motNobels);
		
		model.addAttribute("motCacheDepartNobels", motCacheDepartNobels);
		model.addAttribute("motCacheNobels", motCacheNobels);
		model.addAttribute("motNobels", motNobels);
		model.addAttribute("lettreNobels", lettreNobels);
		model.addAttribute("longueurMotNobels", longueurMotNobels);
		model.addAttribute("idBoutonNobels", idBoutonNobels+"Nobels");
		
//		Artistes
		lettre = (char) (random.nextInt(26)+'a');
		char lettreArtistes = Character.toUpperCase(lettre);
		
		List<String> sujetArtistes = new ArrayList<String>();
		List<Artistes> sujetsArtistesAr= penduDao.challengeSujetArtiste(lettre);
		for (Artistes artistes : sujetsArtistesAr) {
			sujetArtistes.add(artistes.getNom());
		}
		
		String csArtistes= sujetArtistes.get(random.nextInt(sujetArtistes.size()));
		String motArtistesEspace = csArtistes.toUpperCase();
								
		String motArtistes = motArtistesEspace.replaceAll("\\s", "");
		
		String motCacheArtistes="";
		String motCacheDepartArtistes ="";
		for (int i = 0; i < motArtistes.length(); i++) {
			motCacheArtistes = new String(motCacheArtistes+"*");
			if (motArtistes.charAt(i)==lettreArtistes) {
				motCacheDepartArtistes = new String(motCacheDepartArtistes+lettreArtistes);
			} else {
				motCacheDepartArtistes = new String(motCacheDepartArtistes+"*");
			}
		}
		int longueurMotArtistes = motArtistes.length();
		
		System.out.println("lArtiste choisi est: "+motArtistes);
		String idBoutonArtistes = String.valueOf(lettreArtistes);
		String lettreStringArtistes = String.valueOf(lettreArtistes);
		
		session.setAttribute("lettreStringArtistes", lettreStringArtistes);
		session.setAttribute("motArtistes", motArtistes);
		
		model.addAttribute("motCacheDepartArtistes", motCacheDepartArtistes);
		model.addAttribute("motCacheArtistes", motCacheArtistes);
		model.addAttribute("motArtistes", motArtistes);
		model.addAttribute("lettreArtistes", lettreArtistes);
		model.addAttribute("longueurMotArtistes", longueurMotArtistes);
		model.addAttribute("idBoutonArtistes", idBoutonArtistes+"Artistes");
		
//		President
		lettre = (char) (random.nextInt(26)+'a');
		char lettrePresident = Character.toUpperCase(lettre);
		
		List<String> sujetPresident = new ArrayList<String>();
		List<President> sujetPresidentsP = penduDao.challengeSujetPresi(lettre);
		for (President president : sujetPresidentsP) {
			sujetPresident.add(president.getNom());
		}			
		
		String csPresident= sujetPresident.get(random.nextInt(sujetPresident.size()));
		String motPresidentEspace = csPresident.toUpperCase();
		
//		pour la suppression des espaces
		 String motPresident = motPresidentEspace.replaceAll("\\s", "");
		
		
								
		String motCachePresident="";
		String motCacheDepartPresident="";
		for (int i = 0; i < motPresident.length(); i++) {
			motCachePresident = new String(motCachePresident+"*");
			if (motPresident.charAt(i)==lettrePresident) {
				motCacheDepartPresident = new String(motCacheDepartPresident+lettrePresident);
			} else {
				motCacheDepartPresident = new String(motCacheDepartPresident+"*");
			}
		}
		int longueurMotPresident = motPresident.length();
		
		System.out.println("le Presi choisi est: "+motPresident);
		String idBoutonPresident = String.valueOf(lettrePresident);
		String lettreStringPresident = String.valueOf(lettrePresident);
		
		session.setAttribute("lettreStringPresident", lettreStringPresident);
		session.setAttribute("motPresident", motPresident);
		
		model.addAttribute("motCacheDepartPresident", motCacheDepartPresident);
		model.addAttribute("motCachePresident", motCachePresident);
		model.addAttribute("motPresident", motPresident);
		model.addAttribute("lettrePresident", lettrePresident);
		model.addAttribute("longueurMotPresident", longueurMotPresident);
		model.addAttribute("idBoutonPresident", idBoutonPresident+"President");

		
		
		
		int longueurMot = longueurMotPays+longueurMotCapitales+longueurMotNobels+longueurMotArtistes+longueurMotPresident;
		model.addAttribute("longueurMot", longueurMot);
		
	}
	@RequestMapping(value="resultatPenduSujetsChallenge", method=RequestMethod.GET)
	public String resultatPenduSujetsChallenge ( Model model, PenduModel penduModel, HttpServletRequest req, Word word){
		
		
//		recupération pour chaque sujet: le mot, la lettre, les points (gagnés et totals)
//		pour tous les sujets: la longueur
//		le temps restant
		HttpSession session = req.getSession();
		Long moi = 2L;
//				(Long) session.getAttribute("id");
		
		PenduSujetsSolo penduSujetsSolo = new PenduSujetsSolo();
		
		String motCompletPays = (String) session.getAttribute("motPays");
		String lettreDevoileePays = (String) session.getAttribute("lettreStringPays");
		char lettreCharPays = lettreDevoileePays.charAt(0);
		int pointsMaxPays = (Integer) session.getAttribute("pointsMaxPays");
		int pointsPays = (Integer) session.getAttribute("pointsPays");
		String nbErreursPays = (String)session.getAttribute("nbErreursPays");
		System.out.println("le mot du pays: "+motCompletPays+", la lettre : "+lettreCharPays+", les pointsMax: "+pointsMaxPays);
//		savoir si on dejà soumis les pays pour correction, dans ce cas on met les données dans les attributs du pendu qui sera ensuite enregiste dans la BDD, sinon on les laisse par defaut
		String soumissionPays = (String) session.getAttribute("soumissionPays");
		if (soumissionPays =="oui") {
			penduSujetsSolo.setMotPays(motCompletPays);
			penduSujetsSolo.setLettreStringPays(lettreDevoileePays);
			penduSujetsSolo.setLettreCharPays(lettreCharPays);
			System.out.println("le nombre d'erreurs dans pays "+nbErreursPays);
			penduSujetsSolo.setNbErreursPays(Integer.parseInt(nbErreursPays));
			penduSujetsSolo.setScorePays(pointsPays);
			penduSujetsSolo.setScoreMaxPays(pointsMaxPays);
		}
		String motCompletCapitales = (String) session.getAttribute("motCapitales");
		String lettreDevoileeCapitales = (String) session.getAttribute("lettreStringCapitales");
		char lettreCharCapitales = lettreDevoileeCapitales.charAt(0);
		Integer pointsMaxCapitales = (Integer) session.getAttribute("pointsMaxCapitales");
		Integer pointsCapitales = (Integer) session.getAttribute("pointsCapitales");
		String nbErreursCapitales = (String)session.getAttribute("nbErreursCapitales");
		System.out.println("le mot de la  Capitale: "+motCompletCapitales+", la lettre : "+lettreCharCapitales+", les pointsMax: "+pointsMaxCapitales);
		String soumissionCapitale = (String) session.getAttribute("soumissionCapitale");
		if (soumissionCapitale=="oui") {
			penduSujetsSolo.setMotCapitale(motCompletCapitales);
			penduSujetsSolo.setLettreStringCapitale(lettreDevoileeCapitales);
			penduSujetsSolo.setLettreCharCapitale(lettreCharCapitales);
			penduSujetsSolo.setNbErreursCapitale(Integer
					.parseInt(nbErreursCapitales));
			penduSujetsSolo.setScoreCapitale(pointsCapitales);
			penduSujetsSolo.setScoreMaxCapitale(pointsMaxCapitales);
		}
		String motCompletNobels = (String) session.getAttribute("motNobels");
		String lettreDevoileeNobels = (String) session.getAttribute("lettreStringNobels");
		char lettreCharNobels = lettreDevoileeNobels.charAt(0);
		int pointsMaxNobels = (Integer) session.getAttribute("pointsMaxNobels");
		int pointsNobels = (Integer) session.getAttribute("pointsNobels");
		String nbErreursNobels = (String)session.getAttribute("nbErreursNobels");
		System.out.println("le mot du Nobel: "+motCompletNobels+", la lettre : "+lettreCharNobels+", les pointsMax: "+pointsMaxNobels);
		String soumissionNobel = (String) session.getAttribute("soumissionNobel");
		if (soumissionNobel=="oui") {
			penduSujetsSolo.setMotNobel(motCompletNobels);
			penduSujetsSolo.setLettreStringNobel(lettreDevoileeNobels);
			penduSujetsSolo.setLettreCharNobel(lettreCharNobels);
			penduSujetsSolo
					.setNbErreursNobel(Integer.parseInt(nbErreursNobels));
			penduSujetsSolo.setScoreNobel(pointsNobels);
			penduSujetsSolo.setScoreMaxNobel(pointsMaxNobels);
		}
		String motCompletArtistes = (String) session.getAttribute("motArtistes");
		String lettreDevoileeArtistes = (String) session.getAttribute("lettreStringArtistes");
		char lettreCharArtistes = lettreDevoileeArtistes.charAt(0);
		int pointsMaxArtistes = (Integer) session.getAttribute("pointsMaxArtistes");
		int pointsArtistes = (Integer) session.getAttribute("pointsArtistes");
		String nbErreursArtistes = (String)session.getAttribute("nbErreursArtistes");
		System.out.println("le mot de l'ARTISTE: "+motCompletArtistes+", la lettre : "+lettreCharArtistes+", les pointsMax: "+pointsMaxArtistes);
		String soumissionArtiste = (String) session.getAttribute("soumissionArtiste");
		if (soumissionArtiste=="oui") {
			penduSujetsSolo.setMotArtiste(motCompletArtistes);
			penduSujetsSolo.setLettreStringArtiste(lettreDevoileeArtistes);
			penduSujetsSolo.setLettreCharArtiste(lettreCharArtistes);
			penduSujetsSolo.setNbErreursArtiste(Integer
					.parseInt(nbErreursArtistes));
			penduSujetsSolo.setScoreArtiste(pointsArtistes);
			penduSujetsSolo.setScoreMaxArtiste(pointsMaxArtistes);
		}
		String motCompletPresidents = (String) session.getAttribute("motPresident");
		String lettreDevoileePresidents = (String) session.getAttribute("lettreStringPresident");
		char lettreCharPresidents = lettreDevoileePresidents.charAt(0);
		int pointsMaxPresidents = (Integer) session.getAttribute("pointsMaxPresident");
		int pointsPresidents = (Integer) session.getAttribute("pointsPresident");
		String nbErreursPresidents = (String)session.getAttribute("nbErreursPresident");
		System.out.println("le mot du President: "+motCompletPresidents+", le mot complet : "+motCompletPresidents+", les pointsMax: "+pointsMaxPresidents);
		String soumissionPresident = (String) session.getAttribute("soumissionPresident");
		if (soumissionPresident=="oui") {
			penduSujetsSolo.setMotPresident(motCompletPresidents);
			penduSujetsSolo.setLettreStringPresident(lettreDevoileePresidents);
			penduSujetsSolo.setLettreCharPresident(lettreCharPresidents);
			penduSujetsSolo.setNbErreursPresident(Integer
					.parseInt(nbErreursPresidents));
			penduSujetsSolo.setScorePresident(pointsPresidents);
			penduSujetsSolo.setScoreMaxPresident(pointsMaxPresidents);
		}
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date now = new Date();
		String dateString = df.format(now);
		penduSujetsSolo.setDate(now);
		penduSujetsSolo.setDateString(dateString); 
		
//		suppression des données en session ..............................................................................
//		session.removeAttribute("motCompletPays");
//		session.removeAttribute("lettreCharPays");
//		session.removeAttribute("pointsMaxPays");
//		session.removeAttribute("pointsPays");
//		session.removeAttribute("nbErreursPays");
//		session.removeAttribute("soumissionPays");
//		
//		session.removeAttribute("motCompletCapitales");
//		session.removeAttribute("lettreCharCapitales");
//		session.removeAttribute("pointsMaxCapitales");
//		session.removeAttribute("pointsCapitales");
//		session.removeAttribute("nbErreursCapitales");
//		session.removeAttribute("soumissionCapitales");
//		
//		session.removeAttribute("motCompletNobels");
//		session.removeAttribute("lettreCharNobels");
//		session.removeAttribute("pointsMaxNobels");
//		session.removeAttribute("pointsNobels");
//		session.removeAttribute("nbErreursNobels");
//		session.removeAttribute("soumissionNobels");
//		
//		session.removeAttribute("motCompletArtistes");
//		session.removeAttribute("lettreCharArtistes");
//		session.removeAttribute("pointsMaxArtistes");
//		session.removeAttribute("pointsArtistes");
//		session.removeAttribute("nbErreursArtistes");
//		session.removeAttribute("soumissionArtistes");
//		
//		session.removeAttribute("motCompletPresidents");
//		session.removeAttribute("lettreCharPresidents");
//		session.removeAttribute("pointsMaxPresidents");
//		session.removeAttribute("pointsPresidents");
//		session.removeAttribute("nbErreursPresidents");
//		session.removeAttribute("soumissionPresidents");
		
//		Enregistement des données dans la base
		penduDao.savePenduSujetSolo(penduSujetsSolo, moi);
		System.out.println("après l'insertion dans la BDD, la dateString "+penduSujetsSolo.getDateString());
		
//		Envoie des resultats à la vue pour affichage
		
		model.addAttribute("motCompletPays", motCompletPays);
		model.addAttribute("lettreCharPays", lettreCharPays);
		model.addAttribute("pointsMaxPays", pointsMaxPays);
		model.addAttribute("pointsPays", pointsPays);
		model.addAttribute("nbErreursPays", nbErreursPays);
		
		model.addAttribute("motCompletCapitales", motCompletCapitales);
		model.addAttribute("lettreCharCapitales", lettreCharCapitales);
		model.addAttribute("pointsMaxCapitales", pointsMaxCapitales);
		model.addAttribute("pointsCapitales", pointsCapitales);
		model.addAttribute("nbErreursCapitales", nbErreursCapitales);
		
		model.addAttribute("motCompletNobels", motCompletNobels);
		model.addAttribute("lettreCharNobels", lettreCharNobels);
		model.addAttribute("pointsMaxNobels", pointsMaxNobels);
		model.addAttribute("pointsNobels", pointsNobels);
		model.addAttribute("nbErreursNobels", nbErreursNobels);
		
		model.addAttribute("motCompletArtistes", motCompletArtistes);
		model.addAttribute("lettreCharArtistes", lettreCharArtistes);
		model.addAttribute("pointsMaxArtistes", pointsMaxArtistes);
		model.addAttribute("pointsArtistes", pointsArtistes);
		model.addAttribute("nbErreursArtistes", nbErreursArtistes);
		
		model.addAttribute("motCompletPresidents", motCompletPresidents);
		model.addAttribute("lettreCharPresidents", lettreCharPresidents);
		model.addAttribute("pointsMaxPresidents", pointsMaxPresidents);
		model.addAttribute("pointsPresidents", pointsPresidents);
		model.addAttribute("nbErreursPresidents", nbErreursPresidents);
		
		
		
		Integer longueurSujets = (Integer)session.getAttribute("longueurMot");
		String tempsRestant = req.getParameter("tempsRestant");
		
		int totalPoints = pointsPays+pointsCapitales+pointsNobels+pointsArtistes+pointsPresidents;
		int totalPointsMax = pointsMaxArtistes+pointsMaxCapitales+pointsMaxNobels+pointsMaxPays+pointsMaxPresidents;
		
		model.addAttribute("totalPoints", totalPoints);
		model.addAttribute("totalPointsMax", totalPointsMax);
		model.addAttribute("longueurSujets", longueurSujets);
		model.addAttribute("tempsRestant", tempsRestant);
		
		return "resultatPenduSujetsChallenge";
	}
}
