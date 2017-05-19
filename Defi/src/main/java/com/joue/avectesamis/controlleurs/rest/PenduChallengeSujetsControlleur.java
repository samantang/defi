package com.joue.avectesamis.controlleurs.rest;

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
import com.joue.avectesamis.entites.jeux.pendu.PenduDicoChallenge;
import com.joue.avectesamis.entites.jeux.pendu.PenduSujetsChallenge;
import com.joue.avectesamis.metier.ChallengeMetier;
import com.joue.avectesamis.models.PenduModel;
import com.joue.avectesamis.models.Word;

@Controller
public class PenduChallengeSujetsControlleur {

	@Autowired
	private PenduDao penduDao;
	@Autowired
	private ChallengeMetier metier;
	
	@RequestMapping(value="challengeSujets", method=RequestMethod.GET)
	public Map<String, Object> challengDico(Model model, HttpServletRequest req, PenduModel penduModel, Word word ){
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
		
		return map;
		
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
	
	@RequestMapping(value="accepterSujetChallenge", method=RequestMethod.GET)
	public String accepterDicoChallenge(Model model, PenduModel penduModel, HttpServletRequest request){
		
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
	public String AbcChallengeJeu(Model model, PenduModel penduModel, HttpServletRequest request){
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		Long id = 2L; 
//				(Long) session.getAttribute("id");
//		le temps en fonction du nombre de r�sultats possibles
		int temps =0;
		
//		recup�raiton de l'ami
		Long idAmi= 1L;
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
//		le code chez mon ami s'il n'a pas encore jou�
		String codeAttenteAmiSujets=penduDao.getCodeAttenteAmiMoiSujets(id, idAmi);
//		le code chez mon ami s'il a d�j� jou�
		String codeJoueAmiSujets = penduDao.getCodeJoueAmiMoiSujets(id, idAmi);
		
		char lettre ='x';
		Random random = new Random();
		
//		les maps qui vont stocker pour chaque intitule, le mot et la lettre
		Map<Character, String> challengeSujetAgglo = new HashMap<Character, String>();
		
//		si mon code en attente est egale au code en attete de l'ami, ça veut dire qu'il n'ap pas encore joué, alors
//		il faut tirer un mot dans le dico, sinon recuperer le mot avec lequel il a joue
	
		if (codeAttenteMoiSujets!=null && codeAttenteAmiSujets!=null ) {
			
			if(codeAttenteMoiSujets.equals(codeAttenteAmiSujets)){
				//			il n'a pas encore joue ALORS ...
							
				//			on tire une lettre au hasard
				//			choisir dans un intitule de la BDD les mots contenant cette lettre
				//			garder un seul mot
				//			enregistrer dans une map qui va porter le nom de l'intitule, le mot et la lettre
				//			FAIRE CECI POUR TOUS LES INTITULES
							
				//			agglomerations
							lettre = (char) (random.nextInt(26)+'a');			
							List<String> sujetAgglo= penduDao.challengeSujetAgglo(lettre);
							String csa = sujetAgglo.get(random.nextInt(sujetAgglo.size()));
							 challengeSujetAgglo = new HashMap<Character, String>();
							challengeSujetAgglo.put(lettre, csa);		
							map.put("challengeSujetAgglo", challengeSujetAgglo);
							
						}else{
							for(PenduSujetsChallenge c:ami.getMesChallengesJouesPenduSujets()){
								if(c.getCodeIndentification().equals(codeAttenteMoiSujets)){
//									recupéretion dans le jeux de mon ami car moi aussi je dois jouer avec les memes lettres et mots
									map.put("challengeAmi", c);
				//					PenduSujetsChallenge ch = new PenduSujetsChallenge();
				//					List<String> choix = (List<String>) ch.getIntitules();
								}					
							}
						}
		}
			
		

					
//					map.put("mot", mot);
//					map.put("word", word);
				
		
		return "penduChallengeSujetsJeu";
		
	}
	@RequestMapping(value="penduChallengeCorrection", method=RequestMethod.GET)
	public String penduChallengeCorrection ( Model model, PenduModel penduModel, HttpServletRequest request, Word word){
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
//		recuperation du mot, nombre de coups, nombre d'erreurs, temps restant, points gagnés, pointsMax
		String mot ="";
		int nbCoups =0;
		int nbErreurs =0;
		int score =0;
		int scoreMax = 0;
		int temps =0;
		int tempsRestants = 10;
		int nbAide = 0;
		boolean aide = false;
		Collection<Character> lesChoix =null;
		
		HttpSession session = request.getSession();
		Long id =  1L;
//				(Long) session.getAttribute("id");
//		le temps en fonction du nombre de r�sultats possibles
		
//		recup�raiton de l'ami
		Long idAmi= 2L;
//				(long) Integer.parseInt(request.getParameter("idAmi"));
		session.setAttribute("idAmi", idAmi);
		Friend ami= metier.getFriend(idAmi);
		
//		moi
		Friend moi = metier.getFriend(id);
		
		PenduDicoChallenge challenge = new PenduDicoChallenge(new Date(), "laDate", temps, nbErreurs, tempsRestants, true, mot, nbCoups, true, 0, null, score, scoreMax, aide, nbAide);
//		penduDao.savePenduDicoChallenge(challenge, id, idAmi);
		
		map.put("challenge", challenge);
		model.addAttribute("challenge", challenge);
		
		return "penduChallengeCorrection";
	}
}
