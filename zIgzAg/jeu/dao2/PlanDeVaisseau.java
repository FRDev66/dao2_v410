// v2.00 01/02/01
// Copyright 2001 Julien Buret All Rights Reserved.
// Use is subject to license terms.

package zIgzAg.jeu.oceane;



import java.io.Serializable;
import zIgzAg.utile.Mdt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Locale;

public class PlanDeVaisseau extends Produit implements Serializable{

 static final long serialVersionUID=1683235038033747213L;

 private String[] composants;
 private int royalties;

 private int concepteurNum;
 private String concepteurNom;
 private String nom;
 private String marque;
 private String description;
 private int acces;
 //0 si public 1 si privé 2 si d'alliance 3 si réservé à une race.
 private int precisionAcces;
 //-1 si public ou privé, sinon le numéro de l'alliance ou de la race.
 private int tourDeCreation;

 private int nbCases;

 private transient ComposantDeVaisseau[] composantsDeVaisseau;

 //les méthodes d'accès

// --> méthode provisoire :
 public String[] getC(){return composants;}
 public void setAcces(int a){acces=a;}
 public void setPrecisionAcces(int a){precisionAcces=a;}

// fin méthodes provisoires

 public boolean estPublic(){if(acces==0) return true; else return false;}
 public boolean estPrive(){if(acces==1) return true; else return false;}
 public boolean estDAlliance(){if(acces==2) return true; else return false;}
 public boolean estRacial(){if(acces==3) return true; else return false;}

 public String getNom(){return nom;}
 public String getMarque(Locale l){
  if(marque==null) return Utile.maj(Univers.getMessage("PLAN_DE_VAISSEAU_MARQUE_INCONNUE",l));
   else return marque;
  }

 public void concepteurDisparu(){concepteurNum=-2;}
 public boolean concepteurExistant(){return concepteurNum>0;}

 public int getNumeroConcepteur(){return concepteurNum;}
 public boolean estConcepteur(int numC){return numC==concepteurNum;}
 public String getNomConcepteur(Locale l){
  if(concepteurNum==-1) return Utile.maj(Univers.getMessage("PLAN_DE_VAISSEAU_CONCEPTEUR_INCONNU",l));
  else return concepteurNom;
  }
 public int getPrecisionEtat(){return precisionAcces;}
 public int getRoyalties(){return royalties;}
 public String getDescriptionDomaine(Locale l){
  if((estPublic())||(estPrive())) return Utile.maj(Univers.getMessage("DOMAINES_PLAN_DE_VAISSEAU",acces,l));
  if(estRacial()) return Utile.maj(Univers.getMessage("RACES",precisionAcces,l));
  return Univers.getAlliance(precisionAcces).getNom();
  }

 public int getNombreDeComposants(){return composants.length;}
 private void determinerComposantsDeVaisseau(){
  if(composantsDeVaisseau==null){
   composantsDeVaisseau=new ComposantDeVaisseau[composants.length];
   for(int i=0;i<composants.length;i++)
    composantsDeVaisseau[i]=(ComposantDeVaisseau)Univers.getTechnologie(composants[i]);
   }
  }
 public ComposantDeVaisseau[] getComposants(){
  determinerComposantsDeVaisseau();
  return composantsDeVaisseau;
  }

 public ComposantDeVaisseau getComposant(int index){
  determinerComposantsDeVaisseau();
  return composantsDeVaisseau[index];
  }

 public float getPrixConstructionEspace(){
  float retour=getPrix()+2*getMineraiNecessaire();
  int[][] m=getMarchandises();
  if(m!=null)
   for(int i=0;i<m.length;i++)
    if((m[i][0]==Const.PRODUIT_TIXIUM)||(m[i][0]==Const.PRODUIT_LIXIAM)||(m[i][0]==Const.PRODUIT_OXOLE))
    //transition o2 -> 100 au lieu de 20 ->
     retour=retour+m[i][1]*100F;
    //transition o2 -> 20 au lieu de 10 ->
    else retour=retour+m[i][1]*20F;
  return retour;
  }

 public int getNombreDeCases(){return nbCases;}
 public int getPointsDeConstructions(){return Math.max(1,nbCases/2);}

 public int getTaille(){
  for(int i=0;i<10;i++)
   if ((Const.TAILLE_VAISSEAUX[i][0]<=nbCases)&&(Const.TAILLE_VAISSEAUX[i][1]>=nbCases)) return i;
  return 0;
  }

 public int getTailleCombat(){ //##############
  for(int i=0;i<10;i++)
   if ((Const.TAILLE_VAISSEAUX_COMBAT[i][0]<=nbCases)&&(Const.TAILLE_VAISSEAUX_COMBAT[i][1]>=nbCases)) return i;
  return 0;
  }
  
 public boolean concepteurInconnu(){if(concepteurNum==-1) return true; else return false;}
 public boolean marqueInconnue(){if(marque==null) return true; else return false;}

 //les méthodes statiques "basiques"

 public static int[] traductionDomaine(String d){
  int[] retour=new int[2];
  if(Messages.DOMAINES_PLAN_DE_VAISSEAU[0].equals(d)) {retour[0]=0;retour[1]=-1;}
  else if(Messages.DOMAINES_PLAN_DE_VAISSEAU[1].equals(d)) {retour[0]=1;retour[1]=-1;}
  else if(Mdt.estPresent(Messages.RACES,d)) {retour[0]=3;retour[1]=Mdt.position(Messages.RACES,d);}
  else {retour[0]=2;retour[1]=Integer.parseInt(d);}
  return retour;
  }

 public static boolean verificationConformite(Commandant com,String[] comp,String cod){
  if(Univers.existenceCode(cod)) return com.ajouterErreur("ER_PLAN_DE_VAISSEAU_0000",cod);
  int nbBouc=0;
  int nbModCons=0;
  for(int i=0;i<comp.length;i++){
   Technologie t=null;
   if((Univers.estTechnologiePublique(comp[i]))||(com.estTechnologieConnue(comp[i])))
     t=Univers.getTechnologie(comp[i]);
    else return Univers.ajouterErreur(com.getNomNumero(),"ER_PLAN_DE_VAISSEAU_0001",cod,comp[i]);
   if(!(t instanceof ComposantDeVaisseau))
    return Univers.ajouterErreur(com.getNomNumero(),"ER_PLAN_DE_VAISSEAU_0001",cod,comp[i]);
   if(((ComposantDeVaisseau)t).estBouclier()) nbBouc=nbBouc+1;
   //if(((ComposantDeVaisseau)t).estUsineVaisseaux()) nbModCons++;
   if(nbBouc>Const.NB_BOUCLIERS_MAX) return com.ajouterErreur("ER_PLAN_DE_VAISSEAU_0002",cod);
   //if(nbModCons>Const.NB_NAVIRE_USINE_MAX) return com.ajouterErreur("ER_PLAN_DE_VAISSEAU_0003",cod);
   }

  return true;
  }

 public static int determinerMineraiNecessaire(Commandant com,String[] comp){
  int retour=0;
  for(int i=0;i<comp.length;i++)
   retour=retour+((ComposantDeVaisseau)Univers.getTechnologie(comp[i])).getMineraiNecessaire();
  return retour;
  }

 public static float determinerPrix(Commandant com,String[] comp,int royalties){
  float retour=0F;
  for(int i=0;i<comp.length;i++)
   retour=retour+((ComposantDeVaisseau)Univers.getTechnologie(comp[i])).getPrix();
  retour=retour+retour*royalties/100;
  return retour;
  }

 public static int[][] determinerMarchandisesNecessaires(Commandant com,String[] comp){
  int[][] retour=null;
  for(int i=0;i<comp.length;i++)
   retour=Mdt.ajoutIndex(retour,((ComposantDeVaisseau)Univers.getTechnologie(comp[i])).getMarchandises());
  return retour;
  }

 public static int[][] determinerCaracteristiquesSpeciales(Commandant com,String[] comp){
  int[][] retour=null;
  for(int i=0;i<comp.length;i++)
   retour=Mdt.ajoutIndex(retour,Univers.getTechnologie(comp[i]).getCaracteristiquesSpeciales());
  return retour;
  }

 private static String[] classementComposants(String[] comp){
  ArrayList a=new ArrayList(comp.length);
  for(int i=0;i<comp.length;i++)
   if(((ComposantDeVaisseau)Univers.getTechnologie(comp[i])).estMoteur()){
    a.add(comp[i]);
    comp[i]=null;
    }
  for(int j=10;j>=0;j--)
   for(int i=0;i<comp.length;i++)
    if((comp[i]!=null)&&(((ComposantDeVaisseau)Univers.getTechnologie(comp[i])).estArme())&&
       (((Arme)Univers.getTechnologie(comp[i])).getVitesse()==j)){
     a.add(comp[i]);
     comp[i]=null;
     }
  for(int i=0;i<comp.length;i++)
   if((comp[i]!=null)&&((ComposantDeVaisseau)Univers.getTechnologie(comp[i])).estBouclier()){
    a.add(comp[i]);
    comp[i]=null;
    }
  for(int i=0;i<comp.length;i++)
   if(comp[i]!=null) a.add(comp[i]);
  return (String[])a.toArray(new String[0]);
  }

 public static int determinerNombreDeCases(String[] comp){
  int retour=0;
  for(int i=0;i<comp.length;i++)
   retour=retour+((ComposantDeVaisseau)Univers.getTechnologie(comp[i])).getNombreDeCasesPrises();
  return retour;
  }

 //constructeur
 private PlanDeVaisseau(){
  super(null,0,null,0,null,0,0F,null);
  }

 public PlanDeVaisseau(int conceptNum,String conceptNom,String[] comp,String n,String m,String d,int a,int typeA,
                       int roy,int[][] capSpe,int min,float p,int[][] marchand,int nbC,int tour){

  super(null,0,null,0,capSpe,min,p,marchand);
  nom=n;
  marque=m;
  description=d;
  acces=a;
  precisionAcces=typeA;
  royalties=roy;
  composants=classementComposants(comp);
  nbCases=nbC;
  concepteurNum=conceptNum;
  concepteurNom=conceptNom;
  tourDeCreation=tour;
  }

 //les autres méthodes

 public boolean possedeCaracteristiqueSpeciale(int carac,int[] invalides){
  determinerComposantsDeVaisseau();
  for(int i=0;i<composantsDeVaisseau.length;i++)
   if((composantsDeVaisseau[i].possedeCaracteristiqueSpeciale(carac))&&(!Mdt.estPresent(invalides,i))) return true;
  return false;
  }

 public int niveauMaximalComposantPossedantLaCapaciteSpeciale(int carac,int[] invalides){
  int retour=0;
  determinerComposantsDeVaisseau();
  for(int i=0;i<composantsDeVaisseau.length;i++)
   if(!Mdt.estPresent(invalides,i))
    retour=Math.max(composantsDeVaisseau[i].getNiveau(),retour);
  return retour;
  }

 public int capaciteMaximaleCaracteristiqueSpeciale(int carac,int[] invalides){
  int retour=0;
  determinerComposantsDeVaisseau();
  for(int i=0;i<composantsDeVaisseau.length;i++)
   if(!Mdt.estPresent(invalides,i))
    retour=Math.max(composantsDeVaisseau[i].getValeurCaracteristiqueSpeciale(carac),retour);
  return retour;
  }

 public int sommeCaracteristiqueSpeciale(int carac,int[] invalides){
  int retour=0;
  determinerComposantsDeVaisseau();
  for(int i=0;i<composantsDeVaisseau.length;i++)
   if(!Mdt.estPresent(invalides,i))
    retour=retour+composantsDeVaisseau[i].getValeurCaracteristiqueSpeciale(carac);
  return retour;
  }

 public int getCapaciteMouvement(boolean intraGalactique){
  return getCapaciteMouvement(new int[0],intraGalactique);
  }

 public int getCapaciteMouvement(int[] invalides,boolean intraGalactique){
  int retour=capaciteMaximaleCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_PROPULSION,invalides);
  if(intraGalactique)
   if(possedeCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_PROPULSION_INTRAGALACTIQUE,invalides))
    return Const.BORNE_MAX;
  if(retour==0) return 0;
   else return retour+Const.TAILLE_VAISSEAUX[getTaille()][2];
  }

 public int getCapaciteMouvementCombat(boolean intraGalactique){
  return getCapaciteMouvementCombat(new int[0],intraGalactique);
  }

 public int getCapaciteMouvementCombat(int[] invalides,boolean intraGalactique){
  int retour=capaciteMaximaleCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_PROPULSION,invalides);
  //if(intraGalactique)
   //if(possedeCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_PROPULSION_INTRAGALACTIQUE,invalides))
    //return Const.BORNE_MAX;
  if(retour==0) return 0;
   //else return retour+Const.TAILLE_VAISSEAUX[getTaille()][2];
   else return retour+Const.TAILLE_VAISSEAUX_COMBAT[getTailleCombat()][2];
  }
  
 public int getPorteeScannerSysteme(){
  return getPorteeScannerSysteme(new int[0]);
  }

 public int getPorteeScannerSysteme(int[] invalides){
  return capaciteMaximaleCaracteristiqueSpeciale(Const.COMPOSANT_PORTEE_SCANNER_SYSTEME,invalides);
  }

 public int getPorteeScannerFlotte(){
  return getPorteeScannerSysteme(new int[0]);
  }

 public int getPorteeScannerFlotte(int[] invalides){
  return capaciteMaximaleCaracteristiqueSpeciale(Const.COMPOSANT_PORTEE_SCANNER_FLOTTE,invalides);
  }

 public int getBrouillageRadar(){
  return getBrouillageRadar(new int[0]);
  }

 public int getBrouillageRadar(int[] invalides){
  return capaciteMaximaleCaracteristiqueSpeciale(Const.COMPOSANT_BROUILLAGE_RADAR,invalides);
  }

/*
 public boolean estInterGalactique(){
  return estInterGalactique(new int[0]);
  }

 public boolean estInterGalactique(int[] invalides){
  return possedeCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_PROPULSION_INTERGALACTIQUE,invalides);
  }
*/

 public boolean estIntraGalactique(){
  return estIntraGalactique(new int[0]);
  }

 public boolean estIntraGalactique(int[] invalides){
  return possedeCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_PROPULSION_INTRAGALACTIQUE,invalides);
  }

 public boolean possedeRayonTracteur(){
  return possedeCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_RAYON_TRACTEUR,new int[0]);
  }

 public boolean capaciteRayonTracteur(int[] invalides){
  return possedeCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_RAYON_TRACTEUR,invalides);
  }

 public boolean estLanceurDeMines(){
  return estLanceurDeMines(new int[0]);
  }

 public boolean estLanceurDeMines(int[] invalides){
  return possedeCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_LANCEUR_MINES_CLASSIQUES,invalides);
  }

 public int nombreLanceurMinesClassiques(){
  return nombreLanceurMinesClassiques(new int[0]);
  }

 public int nombreLanceurMinesClassiques(int[] invalides){
  return sommeCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_LANCEUR_MINES_CLASSIQUES,invalides);
  }

 public int capaciteDetectionMines(){
  return capaciteDetectionMines(new int[0]);
  }

 public int capaciteDetectionMines(int[] invalides){
  return sommeCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_DETECTION_MINES,invalides);
  }

 public int capaciteDraguageMines(){
   return capaciteDraguageMines(new int[0]);
  }

 public int capaciteDraguageMines(int[] invalides){
   return sommeCaracteristiqueSpeciale(Const.COMPOSANT_DRAGUEUR_MINES,invalides);
  }

 public boolean protectionDraguageMines(){
  ComposantDeVaisseau[] c=getComposants();
  int compteur=0;
  for(int i=0;i<c.length;i++)
   if(c[i].possedeCaracteristiqueSpeciale(Const.COMPOSANT_DRAGUEUR_MINES))
    compteur++;
  return compteur>=c.length/2;
  }


 public int capaciteCargo(){
  return capaciteCargo(new int[0]);
  }

 public int capaciteCargo(int[] invalides){
  return sommeCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_CONTENANCE_CARGO,invalides);
  }

 public int capaciteNavireUsine(){
  return capaciteNavireUsine(new int[0]);
  }

 public int capaciteNavireUsine(int[] invalides){
  return sommeCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_NAVIRE_USINE,invalides);
  }

 public int niveauNavireUsine(){
  return niveauNavireUsine(new int[0]);
  }

 public int niveauNavireUsine(int[] invalides){
  return niveauMaximalComposantPossedantLaCapaciteSpeciale(Const.COMPOSANT_CAPACITE_NAVIRE_USINE,invalides);
  }

 public boolean estTransporteur(){
  return estTransporteur(new int[0]);
  }

 public boolean estTransporteur(int[] invalides){
  if(capaciteCargo(invalides)>0) return true;
   else return false;
  }

 public boolean estColonisateur(){
  return estColonisateur(new int[0]);
  }

 public boolean estColonisateur(int[] invalides){
  return possedeCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_COLONISATEUR,invalides);
  }

 public int getCapaciteVilleSpatiale(){
  return getCapaciteVilleSpatiale(new int[0]);
  }

 public int getCapaciteVilleSpatiale(int[] invalides){
  return sommeCaracteristiqueSpeciale(Const.COMPOSANT_CAPACITE_VILLE_SPATIALE,invalides);
  }

 public int getForceSpatiale(){
  return getForceSpatiale(new int[0]);
  }

 public int getForceSpatiale(int[] invalides){
  int retour=0;
  determinerComposantsDeVaisseau();
  for(int i=0;i<composantsDeVaisseau.length;i++)
   if(!Mdt.estPresent(invalides,i))
    if(composantsDeVaisseau[i].estArme())
     retour=retour+((Arme)composantsDeVaisseau[i]).getForceSpatiale();
  return retour;
  }

 public int getForcePlanetaire(){
  return getForcePlanetaire(new int[0]);
  }

 public int getForcePlanetaire(int[] invalides){
  int retour=0;
  determinerComposantsDeVaisseau();
  for(int i=0;i<composantsDeVaisseau.length;i++)
   if(!Mdt.estPresent(invalides,i))
    if(composantsDeVaisseau[i].estArme())
     retour=retour+((Arme)composantsDeVaisseau[i]).getForcePlanetaire();
  return retour;
  }

 public int getPuissance(){
  return getPuissance(new int[0]);
  }

 public int getPuissance(int[] invalides){
  return getForceSpatiale(invalides)+getForcePlanetaire(invalides)/2;
  }

 public int getVitesseArmes(int[] invalides){
  int retour=0;
  determinerComposantsDeVaisseau();
  for(int i=0;i<composantsDeVaisseau.length;i++)
   if(!Mdt.estPresent(invalides,i))
    if(composantsDeVaisseau[i].estArme())
     retour=retour+((Arme)composantsDeVaisseau[i]).getVitesse();
  return retour;
  }

 public int getNombreBoucliers(int[] invalides){
  int retour=0;
  determinerComposantsDeVaisseau();
  for(int i=0;i<composantsDeVaisseau.length;i++)
   if(!Mdt.estPresent(invalides,i))
    if(composantsDeVaisseau[i].estBouclier())
     retour++;
  return retour;
  }

 public boolean estBombardier(int[] invalides){
  determinerComposantsDeVaisseau();
  for(int i=0;i<composantsDeVaisseau.length;i++)
   if(!Mdt.estPresent(invalides,i))
    if(composantsDeVaisseau[i].estArme())
     if(((Arme)composantsDeVaisseau[i]).estCombatPlanetaire()) return true;
  return false;
  }

 public boolean estChasseur(int[] invalides){
  determinerComposantsDeVaisseau();
  for(int i=0;i<composantsDeVaisseau.length;i++)
   if(!Mdt.estPresent(invalides,i))
    if(composantsDeVaisseau[i].estArme())
     if(((Arme)composantsDeVaisseau[i]).estCombatSpatial()) return true;
  return false;
  }

 public String descriptionComposants(Locale l){
  determinerComposantsDeVaisseau();
  return descriptionComposants(composantsDeVaisseau,l);
  }

 public static String descriptionComposants(ComposantDeVaisseau[] c,Locale l){
  HashMap hm=new HashMap(c.length);
  for(int i=0;i<c.length;i++){
   Object o=hm.get(c[i]);
   if(o==null) hm.put(c[i],new Integer(1));
    else hm.put(c[i],new Integer(((Integer)o).intValue()+1));
   }
  Map.Entry[] m=(Map.Entry[])hm.entrySet().toArray(new Map.Entry[0]);
  String retour="";
  for(int i=0;i<m.length;i++){
   int nb=((Integer)m[i].getValue()).intValue();
   ComposantDeVaisseau co=(ComposantDeVaisseau)m[i].getKey();
   retour=retour+Integer.toString(nb)+" "+(nb>1 ? co.getNomPlurielComplet(l) : co.getNomComplet(l));
   if(i!=m.length-1) retour=retour+"<BR>";
   }
  if(retour.length()!=0) return retour;
   else return "&nbsp;";
  }

 public String descriptionComposantsDetruits(int[] invalides,Locale l){
  determinerComposantsDeVaisseau();
  ArrayList a=new ArrayList(composantsDeVaisseau.length);
  for(int i=0;i<composantsDeVaisseau.length;i++)
   if(Mdt.estPresent(invalides,i))
    a.add(composantsDeVaisseau[i]);
  return descriptionComposants((ComposantDeVaisseau[])a.toArray(new ComposantDeVaisseau[0]),l);
  }
 
 //nouveauPlan("Jumbocot","Compagnie Blue",3,4,0,77,"Wulock",new String[]{"moteurI","cargoI"},15,10,new int[]{1,1},2,new int[][]{{0,1}}{10,10}},8);
 
 public static void nouveauPlan(String nom, String mark, int domaine, int domaine2, int royal, int numero, String concepteur, String[] compo, int minerai, float prix, int[][] marchan, int nbcases, int[][] capaspe, int numtour)
 {
  PlanDeVaisseau p = new PlanDeVaisseau(
        numero,concepteur,
        compo,
        nom,mark,"",
        domaine,domaine2,royal,
        //domaine : 0 public, 1 privé, 2 alliance, 3 race
        //domaine2 : -1 public privé, numero alliance ou race
        capaspe,
        minerai,prix,
        marchan,
        nbcases,numtour);
  
  Univers.ajouterPlanDeVaisseau(p);
 } 
  
 }
