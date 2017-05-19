package com.joue.avectesamis.controlleurs;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joue.avectesamis.entites.AbcChallenge;
import com.joue.avectesamis.entites.AbcSolo;
import com.joue.avectesamis.entites.Friend;
import com.joue.avectesamis.entites.jeux.APresident;
import com.joue.avectesamis.entites.jeux.Agglo;
import com.joue.avectesamis.entites.jeux.Animaux;
import com.joue.avectesamis.entites.jeux.Artistes;
import com.joue.avectesamis.entites.jeux.Nobels;
import com.joue.avectesamis.entites.jeux.Pays_Capitale;
import com.joue.avectesamis.entites.jeux.President;
import com.joue.avectesamis.entites.jeux.VilleFrance;
import com.joue.avectesamis.metier.ChallengeMetier;
import com.joue.avectesamis.models.GameModel;



@Controller
public class ChallengeAbcController {

	@Autowired
	private ChallengeMetier metier;
	
	@RequestMapping(value="/indexjsp")
	public String index (){
		
		return "index";
	}
	
	@RequestMapping(value="abcChallengeHome")
	public String abcChallengeHome(Model model, GameModel gm, HttpServletRequest req){
		HttpSession session = req.getSession();
		Long id =  (Long) session.getAttribute("id");
		Friend moi = metier.getFriend(id);
		
		gm.setMesChallengesRecus(metier.mesChallengesRecus(id));
		gm.setMesChallengesEnvoyes(metier.mesChallengesEnvoyes(id));
		gm.setMesChallengesEnAttentes(metier.mesChallengesEnAttentes(id));
//		gm.setMesChallengesJoues(metier.mesChallengesJou�s(id));
		List<AbcChallenge> mesChallengesJoues = new ArrayList<AbcChallenge>();
		mesChallengesJoues.addAll(metier.mesChallengesJoues(id, id));
//		creation d'une map qui aura comme cl�f le code d'identificaion et comme valeur l'id de l'ami
		Map<String, Long> codeAmi = new HashMap<String, Long>();
//		pour les id des amis avec qui j'ai jou�
//		List<Long> idAmisJoues = new ArrayList<Long>();
//		pour les codes d'identificaions
//		List<String> codesInd = new ArrayList<String>();
		
//		les challenges de mes amis
		List<AbcChallenge> challengesAmis = new ArrayList<AbcChallenge>();
		AbcChallenge challenge = new AbcChallenge();
		Map<Friend, String> attentesAmi = new HashMap<Friend, String>();

		
		
//		AbcChallenge cha = new AbcChallenge();
		for(AbcChallenge c: mesChallengesJoues){
			System.out.println("les elements du challengeJoue: "+c.getCodeIndentification()+" "+c.getMonFriend().getId()+" "+c.getLettre()); 
//			recup�ration de l'id de l'ami associc� au jeu et du code d'identification pour les stocker dans une map
//			ensuite pour chaque id on recup�re l'ami correspondant 
//			
			codeAmi.put(c.getCodeIndentification(), c.getMonFriend().getId());
		}
//		recup�ration des ids dans la map pour cr�er les friends, puis comparer le code d'identification de la map avec les codes d'identifcation qui se trouvent 
//		dans les jeux d�j� jou�s de l'ami: s'il s'y trouvent alors l'ami a jou� donc r�cup�ration du jeu: si non alors il n'a pas encore jou�
		
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
			List<AbcChallenge> challengesAmi= ami.getMesChallengesJoues();
//			parcourt des challenges jou�s par l'ami
			for(AbcChallenge c:challengesAmi){
//				si on trouve un code d'identification dans ses challenges jou�s qui �quivaut au mien, recup�ration de tout le jeu de l'ami en rapport avec ce code
				System.out.println("le code indentification ami=="+c.getCodeIndentification()+" le getKey de chez moi== "+e.getKey());
				if(c.getCodeIndentification().equals(e.getKey())){
//					l'ami a d�j� jou� => recup�ration du jeu en question
//					challenge = new AbcChallenge(c.getEmail(), c.getScore(), c.getDate(), c.getLettre(), c.getTime(), c.getHelp(), c.getAgglo(), c.getaPresident(), c.getPresident(), c.getAnimal(), c.getArtiste(), c.getCapitale(), c.getPays(), c.getNobel(), c.getVille(), c.getTimeOut());					
//					challenge = new AbcChallenge(c.getId(), c.getEmail(), c.getScore(), c.getDate(), c.getLettre(), c.getTime(), c.getHelp(), c.getAgglo(), c.getaPresident(), c.getPresident(), c.getAnimal(), c.getArtiste(), c.getCapitale(), c.getPays(), c.getNobel(), c.getVille(), c.getTimeOut());					
//					challenge = new AbcChallenge(c.getId(),c.getCodeIndentification(), c.getEmail(), c.getScore(), c.getDate(), c.getLettre(), c.getTime(), c.getHelp(), c.getAgglo(), c.getaPresident(), c.getPresident(), c.getAnimal(), c.getArtiste(), c.getCapitale(), c.getPays(), c.getNobel(), c.getVille(), c.getTimeOut());
					challenge = new AbcChallenge(c.getId(),c.getCodeIndentification(), c.getEmail(), c.getScore(), c.getDate(), c.getLettre(), c.getTime(), c.getHelp(), c.getAgglo(), c.getaPresident(), c.getPresident(), c.getAnimal(), c.getArtiste(), c.getCapitale(), c.getPays(), c.getNobel(), c.getVille(), c.getTimeOut(), c.getMonFriend(), c.getFriend());
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
							challenge = new AbcChallenge(null, "xxx", null, 0, null, '?', 0, null, null, null, null, null, null, null, null, null, null, 0);
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
		List<AbcChallenge> challengeAmisOrdre = new ArrayList<AbcChallenge>();
		AbcChallenge challengea = null;
		List<String> listeCodeAmi = new ArrayList<String>();
		for(AbcChallenge abcMoi: mesChallengesJoues){
			for(AbcChallenge abcAmi: challengesAmis){
				listeCodeAmi.add(abcAmi.getCodeIndentification());
				if(abcMoi.getCodeIndentification().equals(abcAmi.getCodeIndentification())){
//					challengeAmiQueue.offerLast(abcAmi);
					challengeAmisOrdre.add(mesChallengesJoues.indexOf(abcMoi), abcAmi);
					
				}else{
					challengea = new AbcChallenge(null, "xxx", null, 0, null, 'd', 0, null, null, null, null, null, null, null, null, null, null, 0);
//					challengeAmiQueue.offerLast(challengea);
					challengeAmisOrdre.add(challengea);

				}
			}
		}
//		la taille de mes jeux
		int taille = mesChallengesJoues.size();
		List<AbcChallenge> challengeAmisOrdreCoupe=challengeAmisOrdre.subList(0, taille);
		
//		Mixage des deux listes pour faciliter l'affichage des duels dans le detail des challenges
		List<AbcChallenge> mixChallenge = new ArrayList<AbcChallenge>();
		mixChallenge = mixageList(mesChallengesJoues, challengeAmisOrdreCoupe);
//		mise du mixchallenge en session pour l'affichage des details des duels
		session.setAttribute("mixChallenge", mixChallenge);
		
		for(AbcChallenge c: mixChallenge){
			System.out.println("LES CODE D'IDENTIFICATIONS DANS LE MIXCHALLENGE SONT "+c.getCodeIndentification());
		}
		
		gm.setChallengeAmisOrdreCoupe(challengeAmisOrdreCoupe);
		gm.setMesChallengesJoues(mesChallengesJoues);
//	    recup�ration des derniers jeux
	   List<AbcChallenge> mesChallenges= metier.mesDerniersChallenges(id);
	    List<AbcSolo> mesSolos =  metier.getMesSolos(id);
	    
	    model.addAttribute("challengeAmisOrdreCoupe", challengeAmisOrdreCoupe);	    
	    
	    model.addAttribute("mesChallenges", mesChallenges);
	    model.addAttribute("mesSolos", mesSolos);
		
		System.out.println("mes challengesJou�s:la taille "+mesChallengesJoues.size());
		System.out.println("les challengesJou�sAmis: la taille "+challengesAmis.size());
		gm.setMesChallengesJoues(mesChallengesJoues);
		gm.setChallengesAmis(challengesAmis);
		session.setAttribute("mesChallengesJoues", mesChallengesJoues);
		session.setAttribute("challengesAmis", challengesAmis);
		model.addAttribute("gm", gm);
		model.addAttribute("mesChallengesJoues", mesChallengesJoues);
		model.addAttribute("challengesAmis", challengesAmis);
		model.addAttribute("moi", moi);
		
		return "abcChallengeHome";
		
	}
//	Methode qui permet de mixer deux ArrayList
	public List<AbcChallenge> mixageList(List<AbcChallenge> a, List<AbcChallenge>b ){
		List<AbcChallenge> resultat = new ArrayList<AbcChallenge>();
		int size = Math.max(a.size(), b.size());
		
		for (int i = 0; i < size; i++) {
	        if (i < a.size()) resultat.add(a.get(i));
	        if (i < b.size()) resultat.add(b.get(i));
	    }

		
		return resultat;
		
	}
	@RequestMapping(value="accepteChallenge")
	public String accepteChallenge(Model model, GameModel gm, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		Long id =  (Long) session.getAttribute("id");
		
//		recup�raiton de l'ami
		Long idAmi= (long) Integer.parseInt(request.getParameter("idAmi"));
		
		metier.accepterChallenge(id, idAmi);
		
		gm.setMesChallengesRecus(metier.mesChallengesRecus(id));
		gm.setMesChallengesEnvoyes(metier.mesChallengesEnvoyes(id));
		gm.setMesChallengesEnAttentes(metier.mesChallengesEnAttentes(id));
		
		
		
		model.addAttribute("gm", gm);
		
		return "abcChallengeHome";
		
	}
	
	
	@RequestMapping(value="AbcChallengeJeu")
	public String AbcChallengeJeu(Model model, GameModel gm, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		Long id =  (Long) session.getAttribute("id");
//		le temps en fonction du nombre de r�sultats possibles
		int temps =0;
		
//		recup�raiton de l'ami
		Long idAmi= (long) Integer.parseInt(request.getParameter("idAmi"));
		session.setAttribute("idAmi", idAmi);
		Friend ami= metier.getFriend(idAmi);
		
//		moi
		Friend moi = metier.getFriend(id);
		
		Random r = new Random();
		char lettre = 'x';
//		le code du jeu chez moi
		String codeAttenteMoi= metier.getCodeAttenteMoiAmi(id, idAmi);
//		le code chez mon ami s'il n'a pas encore jou�
		String codeAttenteAmi=metier.getCodeAttenteAmiMoi(id, idAmi);
//		le code chez mon ami s'il a d�j� jou�
		String codeJoueAmi = metier.getCodeJoueAmiMoi(id, idAmi);
		
//			- si mon code en attente correspond � celui de mon ami en attente (c'est qu'il na pas encore jou�, alors il faut g�n�rer une lettre random que lui aussi doit
//		utiliser apr�s quand il jouera car nous tous devons utiliser la m�me lettre pour jouer
//		sinon �a veut dire qu'il a d�j� jou�, alors il faut r�cup�rer la lettre avec laquelle il a jou�
		
		if(codeAttenteMoi.equals(codeAttenteAmi)){
			lettre = (char) (r.nextInt(26)+'a');
		}else{
			for(AbcChallenge c:ami.getMesChallengesJoues()){
				if(c.getCodeIndentification().equals(codeAttenteMoi))
					lettre = c.getLettre();
			}
		}
		
//		==================================================
		
		// liste des checksboxs coch�s pour les intitul�s
				List<String> checkList = new ArrayList<String>();
				
				// listes pour les diff�rents checkboxs pour la contruction des tableaux
				// on les remplit pour savoir combien de lignes il faut pr�voir
				List<Pays_Capitale>listPays = new ArrayList<Pays_Capitale>();
				List<Pays_Capitale>listeCapitales = new ArrayList<Pays_Capitale>();
				List<VilleFrance>listeVillesFrance = new ArrayList<VilleFrance>();
				List<President>listePresidentsA = new ArrayList<President>();
				List<APresident>listeAPresidents = new ArrayList<APresident>();
				List<Animaux>listeAnimaux = new ArrayList<Animaux>();
				List<Agglo>listeAglo = new ArrayList<Agglo>();
				List<Artistes>listeChanteurs = new ArrayList<Artistes>();
				List<Nobels>listeNobels = new ArrayList<Nobels>();
				
//				recup�ration dans la base de donn�es des informations concernant les intitul�s en fonction de la lettre avec laquelles on va jouer
					listPays.addAll(metier.getPays(lettre));
					checkList.add("pays");
					
					listePresidentsA.addAll(metier.getPresidents(lettre));
					checkList.add("presidents");
					
					listeAPresidents.addAll(metier.getAPresidents(lettre));
					checkList.add("ancienPresidents");
	
					listeNobels.addAll(metier.getNobels(lettre));
					checkList.add("nobels");
					
					listeAglo.addAll(metier.getAgglos(lettre));
					checkList.add("aglomerations");
				
					listeChanteurs.addAll(metier.getArtistes(lettre));
					checkList.add("chanteurs");
				
					listeAnimaux.addAll(metier.getAnimaux(lettre));
					checkList.add("animaux");
					
					
					// �������������������������������������������������������������������������������������
					int taillePays = listPays.size();
					int tailleCapitale = listeCapitales.size();
					int tailleVilles= listeVillesFrance.size();
					int tailleNobel = listeNobels.size();
					int tailleAnimaux = listeAnimaux.size();
					int taillePresidents = listePresidentsA.size();
					int tailleAPresidents = listeAPresidents.size();
					int tailleAgglo = listeAglo.size();
					int tailleChanteurs = listeChanteurs.size();
					temps = taillePays+tailleCapitale+tailleVilles+tailleNobel+tailleAnimaux+taillePresidents+tailleAPresidents+tailleAgglo+tailleChanteurs;
					
					
					session.setAttribute("listPays", listPays);
					session.setAttribute("listeCapitales", listeCapitales);
					session.setAttribute("listeVillesFrance", listeVillesFrance);
					session.setAttribute("listeNobels", listeNobels);
					session.setAttribute("listeAnimaux", listeAnimaux);
					session.setAttribute("listePresidentsA", listePresidentsA);
					session.setAttribute("listeAPresidents", listeAPresidents);
					session.setAttribute("listeAglo", listeAglo);
					session.setAttribute("listeChanteurs", listeChanteurs);
					request.setAttribute("temps", temps);
					session.setAttribute("lettre", lettre);
					session.setAttribute("checkList", checkList);
					
					model.addAttribute("listPays", listPays);
					model.addAttribute("listeCapitales", listeCapitales);
					model.addAttribute("listeVillesFrance", listeVillesFrance);
					model.addAttribute("listeNobels", listeNobels);
					model.addAttribute("listeAnimaux", listeAnimaux);
					model.addAttribute("listePresidentsA", listePresidentsA);
					model.addAttribute("listeAPresidents", listeAPresidents);
					model.addAttribute("listeAglo", listeAglo);
					model.addAttribute("listeChanteurs", listeChanteurs);
					model.addAttribute("taillePays", taillePays);
					model.addAttribute("gm", gm);
				
		
		return "AbcChallengeJeu";
		
	}
	
	@RequestMapping(value="abcChallengeCorrection")
	public String abcChallengeCorrection(Model model, GameModel gm, HttpServletRequest request){
		
		gm = new GameModel();
		HttpSession session = request.getSession();
		Long id =  (Long) session.getAttribute("id");
		Friend moi = metier.getFriend(id);
		
		
		
		
		// recup�ration des objets en session
		String email = (String) session.getAttribute("email");
		char lettre =  (Character) session.getAttribute("lettre");
		String date = null;
		int time =0; // le temps qu'a dur� le jeux
		String help = "no"; // si l'utlisateur a demand� de l'aide
		int score = 0;
//		 // pour savoir ce qu'on doit inserer dans les colonnes des intitul�s
//		 String pays = "no";
//		 String capitale = "no";
//		 String president = "no";
//		 String APresident = "no";
//		 String ville = "no";
//		 String nobel = "no";
//		 String chanteur = "no";
//		 String agglo = "no";
//		 String animaux ="no";
		
		/*
		 * POUR LE PAYS =====================================================================================================================================
		 */
		// liste de tous les pays concern�s 
		List<String> listePays = (List<String>) session.getAttribute("listPays");
		// liste des choix faits par l'utilisateur 
		List <String> choixPays = new ArrayList<String>();
		// liste des pays restants (s'il en reste)
		List<String> paysRestants = new ArrayList<String>();
		// recup�ration des contenus dans les input
		String [] reponses = null;
//		List<String> reponses = new ArrayList<String>();
//			reponses.addAll(request.getParameterValues("pays"));
		if(request.getParameterValues("pays")!=null){
			reponses = request.getParameterValues("pays");
			for(String s: reponses){
				if(!s.equals("pays")) choixPays.add(s);
			}
		}
		// si on retrouve tous les pays concern�s dans la liste des pays choisis alors l'utilisateur a trouv�
		// donc il gagne des points
		if(choixPays.containsAll(listePays)){
			score = score +1;
		}
		paysRestants.addAll(listePays);		
		paysRestants.removeAll(choixPays);
	
		 if(request.getParameter("pays")!=null){
//			 pays ="selectioned";
		 }
		 
		 /*
			 * POUR LES CAPITALES =====================================================================================================================================
			 */
		// liste de tous les pays concern�s 
			List<String> listeCapitales = (List<String>) session.getAttribute("listeCapitales");
			// liste des choix faits par l'utilisateur 
			List <String> choixCapitales = new ArrayList<String>();
			// liste des pays restants (s'il en reste)
			List<String> capitalesRestantes = new ArrayList<String>();
		 if(request.getParameter("capitale")!=null){
				
				
				String [] reponsesCapitales = request.getParameterValues("capitale");
				for(String s: reponsesCapitales){
					if(!s.equals("capitale")) choixCapitales.add(s);
				}
				// si on retrouve tous les pays concern�s dans la liste des pays choisis alors l'utilisateur a trouv�
				// donc il gagne des points
				if(choixCapitales.containsAll(listeCapitales)){
					score = score +1;
				}
				capitalesRestantes.addAll(listeCapitales);		
				capitalesRestantes.removeAll(choixCapitales);
			
				 
//				 capitale ="selectioned";
			 }
			 
			 /*
				 * POUR LES VILLES DE FRANCE =====================================================================================================================================
				 */
		// liste de tous les pays concern�s 
			List<String> listeVillesFrance = (List<String>) session.getAttribute("listeVillesFrance");
			// liste des choix faits par l'utilisateur 
			List <String> choixVilles = new ArrayList<String>();
			// liste des pays restants (s'il en reste)
			List<String> villesRestantes = new ArrayList<String>();
			 if(request.getParameter("ville")!=null){
				
				
				String [] reponsesVilles = request.getParameterValues("ville");
				for(String s: reponsesVilles){
					if(!s.equals("ville")) choixVilles.add(s);
				}
				// si on retrouve tous les pays concern�s dans la liste des pays choisis alors l'utilisateur a trouv�
				// donc il gagne des points
				if(choixVilles.containsAll(listeVillesFrance)){
					score = score +1;
				}
				villesRestantes.addAll(listeVillesFrance);		
				villesRestantes.removeAll(choixVilles);
			
				 
//					 ville ="selectioned";
				 }
				 
				 /*
					 * POUR LES PRIX NOBELS =====================================================================================================================================
					 */
			// liste de tous les pays concern�s 
				List<String> listeNobels = (List<String>) session.getAttribute("listeNobels");
				// liste des choix faits par l'utilisateur 
				List <String> choixNobels= new ArrayList<String>();
				// liste des pays restants (s'il en reste)
				List<String> nobelsRestants = new ArrayList<String>();
				 if(request.getParameter("nobel")!=null){
					
					
					String [] reponsesNobels = request.getParameterValues("nobel");
					for(String s: reponsesNobels){
						if(!s.equals("nobel")) choixNobels.add(s);
					}
					// si on retrouve tous les pays concern�s dans la liste des pays choisis alors l'utilisateur a trouv�
					// donc il gagne des points
					if(choixNobels.containsAll(listeNobels)){
						score = score +1;
					}
					nobelsRestants.addAll(listeNobels);		
					nobelsRestants.removeAll(choixNobels);
				
					 
//						 nobel ="selectioned";
					 }
				 
				 /*
					 * POUR LES ANIMAUX =====================================================================================================================================
					 */
				// liste de tous les pays concern�s 
					List<String> listeAnimaux = (List<String>) session.getAttribute("listeAnimaux");
					// liste des choix faits par l'utilisateur 
					List <String> choixAnimaux = new ArrayList<String>();
					// liste des pays restants (s'il en reste)
					List<String> animauxRestants = new ArrayList<String>();
				 if(request.getParameter("animaux")!=null){
						
						
						String [] reponsesAnimaux = request.getParameterValues("animaux");
						for(String s: reponsesAnimaux){
							if(!s.equals("animaux")) choixAnimaux.add(s);
						}
						// si on retrouve tous les pays concern�s dans la liste des pays choisis alors l'utilisateur a trouv�
						// donc il gagne des points
						if(choixAnimaux.containsAll(listeAnimaux)){
							score = score +1;
						}
						animauxRestants.addAll(listeAnimaux);		
						animauxRestants.removeAll(choixAnimaux);
					
						 
//						 animaux ="selectioned";
					 }
				 
				 /*
					 * POUR LES PRESIDENTS =====================================================================================================================================
					 */
				// liste de tous les pays concern�s 
					List<String> listePresidentsA = (List<String>) session.getAttribute("listePresidentsA");
					// liste des choix faits par l'utilisateur 
					List <String> choixPresidents = new ArrayList<String>();
					// liste des pays restants (s'il en reste)
					List<String> presidentsRestants = new ArrayList<String>();

				 if(request.getParameter("president")!=null){
												
						String [] reponsesPresidents = request.getParameterValues("president");
						for(String s: reponsesPresidents){
							if(!s.equals("president")) choixPresidents.add(s);
						}
						// si on retrouve tous les pays concern�s dans la liste des pays choisis alors l'utilisateur a trouv�
						// donc il gagne des points
						if(choixPresidents.containsAll(listePresidentsA)){
							score = score +1;
						}
						presidentsRestants.addAll(listePresidentsA);		
						presidentsRestants.removeAll(choixPresidents);
					
						 
//						 president ="selectioned";
					 }
				 
				 /*
					 * POUR LES ANCIENS PRESIDENTS =====================================================================================================================================
					 */
				// liste de tous les pays concern�s 
					List<String> listeAPresidents = (List<String>) session.getAttribute("listeAPresidents");
					// liste des choix faits par l'utilisateur 
					List <String> choixAPresidents = new ArrayList<String>();
					// liste des pays restants (s'il en reste)
					List<String> ApresidentsRestants = new ArrayList<String>();
				 if(request.getParameter("ancienP")!=null){
						
						
						String [] reponsesAPresidents = request.getParameterValues("ancienP");
						for(String s: reponsesAPresidents){
							if(!s.equals("ancienP")) choixAPresidents.add(s);
						}
						// si on retrouve tous les pays concern�s dans la liste des pays choisis alors l'utilisateur a trouv�
						// donc il gagne des points
						if(choixAPresidents.containsAll(listeAPresidents)){
							score = score +1;
						}
						ApresidentsRestants.addAll(listeAPresidents);		
						ApresidentsRestants.removeAll(choixAPresidents);
					
						 
//						 APresident ="selectioned";
					 }
				 /*
					 * POUR LES AGGOLOMERATIONS =====================================================================================================================================
					 */
				// liste de tous les pays concern�s 
					List<String> listeAglo = (List<String>) session.getAttribute("listeAglo");
					// liste des choix faits par l'utilisateur 
					List <String> choixAgglo = new ArrayList<String>();
					// liste des pays restants (s'il en reste)
					List<String> AggloRestantes = new ArrayList<String>();

				 if(request.getParameter("agglo")!=null){
												
						String [] reponsesAgglo = request.getParameterValues("agglo");
						for(String s: reponsesAgglo){
							if(!s.equals("agglo")) choixAgglo.add(s);
						}
						// si on retrouve tous les pays concern�s dans la liste des pays choisis alors l'utilisateur a trouv�
						// donc il gagne des points
						if(choixAgglo.containsAll(listeAglo)){
							score = score +1;
						}
						AggloRestantes.addAll(listeAglo);		
						AggloRestantes.removeAll(choixAgglo);
					
						 
//						 agglo ="selectioned";
					 }
				 /*
					 * POUR LES ARSTISTES CHANTEURS =====================================================================================================================================
					 */
				// liste de tous les pays concern�s 
					List<String> listeChanteurs = (List<String>) session.getAttribute("listeChanteurs");
					// liste des choix faits par l'utilisateur 
					List <String> choixArtistes = new ArrayList<String>();
					// liste des pays restants (s'il en reste)
					List<String> ArtistesRestants = new ArrayList<String>();
				 if(request.getParameter("artiste")!=null){
						
						
						String [] reponsesArtistes= request.getParameterValues("artiste");
						for(String s: reponsesArtistes){
							if(!s.equals("artiste")) choixArtistes.add(s);
						}
						// si on retrouve tous les pays concern�s dans la liste des pays choisis alors l'utilisateur a trouv�
						// donc il gagne des points
						if(choixArtistes.containsAll(listeChanteurs)){
							score = score +1;
						}
						ArtistesRestants.addAll(listeChanteurs);		
						ArtistesRestants.removeAll(choixArtistes);
					
						 
//						 chanteur ="selectioned";
					 }
				 
//				 pour savoir si l'utilisateur a demand� de l'aide
			if(request.getParameter("aidePaysNo")=="oui") help="oui";	 

//			insertion dans la base de donn�es =========================
//			AbcSoloJeux solojeu = new AbcSoloJeux(email, score, new Date(), null, time, help, choixAgglo, choixAPresidents, choixPresidents, choixAnimaux, choixArtistes,
//					choixCapitales, choixPays, choixNobels, choixVilles, time);
//			AbcSolo solo= new AbcSolo();
			
			AbcChallenge challenge = new AbcChallenge(null, score, new Date(), lettre, 0, help, choixAgglo, choixAPresidents, choixPresidents, choixAnimaux, choixArtistes, choixCapitales, choixPays, choixNobels, choixVilles, 0);
			
//			recup�ration de l'id de l'utilisateur
			Long idAmi=(Long) session.getAttribute("idAmi");
//			mon id
//			Long id =  (Long) session.getAttribute("id");
			metier.saveChallenge(id, idAmi, challenge);
			
			
//			Friend moi = new Friend();
//			moi=metier.getFriendByEmail(email);
//			Long id = moi.getId(); // c'est celui-ci qu'on doit mettre dans la m�thode qui suit quand les inscriptions seront bien faites
//			metier.saveAbcSolo(1l, solo, solojeu);
			
			
			
			model.addAttribute("moi", moi);
			
			model.addAttribute("listePays", listePays);
			model.addAttribute("paysRestants", paysRestants);
			model.addAttribute("paysTest", choixPays);
			 
			model.addAttribute("listeCapitales", listeCapitales);
			model.addAttribute("capitalesRestantes", capitalesRestantes);
			model.addAttribute("choixCapitales", choixCapitales);
			 
			model.addAttribute("listeVillesFrance", listeVillesFrance);
			model.addAttribute("villesRestantes", villesRestantes);
			model.addAttribute("choixVilles", choixVilles);
			 
			model.addAttribute("listeAglo", listeAglo); 
			model.addAttribute("AggloRestantes", AggloRestantes);
			model.addAttribute("choixAgglo", choixAgglo);
			 
			model.addAttribute("listeAnimaux", listeAnimaux);
			model.addAttribute("animauxRestants", animauxRestants);
			model.addAttribute("choixAnimaux", choixAnimaux);
			 
			model.addAttribute("listeAPresidents", listeAPresidents);
			model.addAttribute("ApresidentsRestants", ApresidentsRestants);
			model.addAttribute("choixAPresidents", choixAPresidents);
			 
			model.addAttribute("listeChanteurs", listeChanteurs);
			model.addAttribute("ArtistesRestants", ArtistesRestants);
			model.addAttribute("choixArtistes", choixArtistes);
			 
			model.addAttribute("listeNobels", listeNobels);
			model.addAttribute("nobelsRestants", nobelsRestants);
			model.addAttribute("choixNobels", choixNobels);
			 
			model.addAttribute("listePresidentsA", listePresidentsA);
			model.addAttribute("presidentsRestants", presidentsRestants);
			model.addAttribute("choixPresidents", choixPresidents);
		
			model.addAttribute("gm", gm);
			model.addAttribute("sm", gm);

		
		return "abcChallengeCorrection";
		
	}
	@RequestMapping(value="infoJeuChallenge")
	public String infoJeuChallenge(Model model, GameModel gm, HttpServletRequest request){
//		gm  = new GameModel();
		HttpSession session = request.getSession();
//		if(gm.getMesChallengesJoues().isEmpty()){
//			System.out.println("la liste de mes challlenges jou�s est vide");
//		}
		List<AbcChallenge> mixChallenge = (List<AbcChallenge>) session.getAttribute("mixChallenge");
//		List<AbcChallenge> challengesAmis = (List<AbcChallenge>) session.getAttribute("challengesAmis");
		
		model.addAttribute("mixChallenge", mixChallenge);
		model.addAttribute("gm", gm);
		return "infoJeuChallenge";
		
	}
	@RequestMapping(value="infoJeuChallengeDuel")
	public String infoJeuChallengeDuel(Model model, GameModel gm, HttpServletRequest request){
		
		
		model.addAttribute("gm", gm);
		return "infoJeuChallengeDuel";
		
	}
	
	
}
