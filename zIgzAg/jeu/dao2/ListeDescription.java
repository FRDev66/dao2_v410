// v2.00 01/02/01
// Copyright 2001 Julien Buret All Rights Reserved.
// Use is subject to license terms.

package zIgzAg.jeu.oceane;



public class ListeDescription extends MessagesAbstraits{

 public static final Description laser=new Description("laser","lasers","Cette arme vous permettra de concevoir des plans de vaisseaux plus efficaces.");
 public static final Description plasma=new Description("canon à plasma","canons à plasma","Cette arme vous permettra de concevoir des plans de vaisseaux plus efficaces.");
 public static final Description torp=new Description("lance-torpille","lance-torpilles","Cette arme vous permettra de concevoir des plans de vaisseaux plus efficaces.");
 public static final Description miss=new Description("missile","missiles","Cette arme vous permettra de concevoir des plans de vaisseaux plus efficaces.");

 public static final Description bombe=new Description("bombe","bombes","Cette arme vous permettra de concevoir des plans de vaisseaux plus efficaces.");

 public static final Description moteur=new Description("réacteur","réacteurs","Un réacteur permet à un vaisseau de se déplacer à la vitesse classique de base égale à 6, auquel on ajoute le bonus de la caractéristique &quot;Propulsion&quot; du réacteur.");
 public static final Description intra=new Description("réacteur intragalactique","réacteurs intragalactiques","Un réacteur intragalactique est un composant de vaisseau.<br>Il permet au vaisseau qui en possède un de se déplacer instantanément d'un point à l'autre de la galaxie.");
 public static final Description inter=new Description("réacteur intergalactique","réacteurs intergalactiques","Un réacteur intergalactique est un composant de vaisseau.<br>Il permet au vaisseau qui en possède un de se déplacer instantanément d'une galaxie à une autre, sans passer par une porte galactique.");

 public static final Description scan=new Description("scanner","scanners","Un scanner est un composant de vaisseau.<br> Le scanner permet d'apercevoir des systèmes à une distance égale à sa caractéristique &quot;Portée scanner&quot;.");
 public static final Description bouclier=new Description("bouclier","boucliers","Un bouclier est un composant de vaisseau.<br> Un bouclier permet de parer un nombre de dommages égal à sa capacité &quot;Capacité bouclier magnétique&quot; et se régénère après le combat. <br> Il ne peut pas y avoir plus de 10 boucliers par vaisseau.");
 public static final Description lmine=new Description("lanceur de mines AM","lanceurs de mines AM","Un lanceur de mines est un composant de vaisseau.<br>Un lanceur de mine permet de lancer un nombre de mines anti-matière classiques par tour égal à sa caractéristique &quot;Lanceur de mines&quot;.");
 public static final Description dmine=new Description("détecteur de mines","détecteurs de mines","Un détecteur de mines est un composant de vaisseau.<br>Un détecteur de mines permet au vaisseau qui en possède un d'éviter tout dégât dû à des mines lors d'un tour avec un pourcentage de chance égal à sa caractéristique &quot;Détection de mines&quot;. Plusieurs détecteurs de mines sur un même vaisseau n'ont pas d'effets cumulatifs.");
 //public static final Description mconstru=new Description("module de construction","modules de construction","Un module de construction est un composant de vaisseau.<br>Un module de construction permet de disposer d'un nombre de points de construction par tour dans l'espace pour construire des vaisseaux égal à la caractéristique &quot;Potentiel navire usine&quot;. Il ne peut pas y avoir plus d'un module de construction par vaisseau.");
 public static final Description bscan=new Description("brouilleur radar","brouilleurs radars","Un brouilleur radar est un composant de vaisseau.<br>un brouilleur radar a un pourcentage de chance égal à sa caractéristique &quot;Brouillage radar&quot; de rendre la flotte du vaisseau invisible aux radars ce tour-ci.<br> Plusieurs brouilleurs radars n'ont pas d'effets cumulatifs : c'est le brouillage le plus puissant qui est pris en compte pour la flotte .");
 public static final Description tract=new Description("rayon tracteur","rayons tracteur","Un rayon tracteur est un composant de vaisseau<br>Il permet de récupérer des containers de marchandises perdus par des vaisseaux ennemis détruits au cours du combat. Pour qu'un rayon tracteur ait une utilité, il est nécessaire que des vaisseaux dans la même flotte possèdent des soutes cargos pour pouvoir stocker les marchandises récupérées.");
 public static final Description cargo=new Description("cargo","cargos","Un cargo est un composant de vaisseau.<br>Un cargo permet de stocker un nombre d'unités de marchandises égal à sa caractéristique &quot;Capacité cargo&quot;.");
 public static final Description ville=new Description("ville spatiale","villes spatiales","Une ville spatiale est un composant de vaisseau.<br>une ville spatiale est un espace de vie pour un nombre d'unités de population égal à sa caractéristique &quot;Capacité ville spatiale&quot;.");
 public static final Description roboco=new Description("robot colonisateur","robots colonisateurs","Un robot colonisateur est un composant de vaisseau.<br>Un robot colonisateur permet d'explorer une planète pour voir si elle peut être habitée par des membres de la race de l'équipage.");
 public static final Description hscan=new Description("hyper-scanner","hyper-scanners","Un hyper-scanner est un composant de vaisseau.<br> Ce scanner permet d'apercevoir des flottes à une distance égale à sa caractéristique &quot;Portée scanner&quot;.");
 public static final Description dramin=new Description("dragueur de mines","dragueurs de mines","Un dragueur de mines est un composant de vaisseau.<br>Il permet de désactiver un nombre de mines ou de débris diverses par tour égal à sa caractéristique &quot;Capacité dragueur mines&quot;.<br>Un vaisseau dont la moitié ou plus de ses composants sont des dragueurs de mines ne peut pas subir de dommages dûs aux mines.");

  //les batiments -->

 public static final Description chantier=new Description("chantier naval","chantiers navals","Le chantier naval permet de construire des vaisseaux spatiaux à partir du système qui en possède.<br>Il permet également de réparer 20 points de dégats par tour pour une flotte.");
 public static final Description mine=new Description("mine","mines","Une mine procure un revenu en minerai en fonction de la planète sur laquelle elle est construite.");
 public static final Description retraite=new Description("usine de retraitement","usines de retraitement","Une usine de retraitement permet de récupérer du minerai en détruisant des constructions planétaires sur la planète sur laquelle elle est présente.");
 public static final Description optp=new Description("usine d'optimisation planétaire","usines d'optimisation planétaires","Une usine d'optimisation planétaire permet d'augmenter de 1 pt par usine construite le nombre de points de construction du système.");
 public static final Description opts=new Description("usine d'optimisation spatiale","usines d'optimisation spatiales","Une usine d'optimisation spatiale permet d'augmenter de 2 pt par usine construite le nombre de points de construction du système.");
 public static final Description repare=new Description("centre de réparation spatial","centres de réparation spatiaux","Un centre de réparation spatial permet de renflouer les flottes endommagées qui orbitent autour du système, à raison de 100 points par tour.");
 public static final Description boucpla=new Description("bouclier planétaire","boucliers planétaires","Un bouclier planétaire procure une protection efficace en cas d'attaque de la planète.<br>Les attaquants doivent d'abord le détruire avant de pilonner la population et les éventuelles constructions de défense.");
 public static final Description agro=new Description("complexe agro-alimentaire","complexes agro-alimentaires","Un complexe agro-alimentaire procure 10 unités de produits alimentaires par tour.");
 public static final Description agricole=new Description("usine agricole","usines agricoles","Une usine agricole procure 10 unités de materiel agricole par tour.");
 public static final Description mode=new Description("centre de mode","centres de mode","Un centre de mode procure 10 unités de produits de luxe par tour.");
 public static final Description culturel=new Description("centre culturel","centres culturels","Un centre culturel procure 10 unités de holofilms et hololivres par tour.");
 public static final Description energie=new Description("centrale énergétique","centrales énergétiques","Une centrale énergétique procure 10 unités d'énergie par tour.");
 public static final Description pharma=new Description("laboratoire pharmaceutique","laboratoires pharmaceutiques","Un laboratoire pharmaceutique procure 10 unités de médicaments par tour.");
 public static final Description info=new Description("société informatique","sociétés informatiques","Une société informatique procure 10 unités de logiciels par tour.");
 public static final Description robot=new Description("usine de robotique","usines de robotique","Une usine de robotique procure 10 unités de robots par tour.");
 public static final Description techno=new Description("technocentre","technocentres","Un technocentre procure 10 unités de composants électroniques par tour.");
 public static final Description arme=new Description("usines d'armement","usines d'armement","Une usine d'armement procure 10 unités d'armement et d'explosif par tour.");
 public static final Description raffine=new Description("raffinerie","raffineries","Une raffinerie procure 10 unités de carburant par tour.");
 public static final Description lourde=new Description("industrie lourde","industries lourdes","Une centre industriel procure 10 unités de pièces industrielles par tour.");
 public static final Description metaux=new Description("usine d'enrichissement des métaux","usines d'enrichissement des métaux","Une usine d'enrichissement des métaux procure 10 unités de métaux précieux par tour.");
 public static final Description radar=new Description("radar","radars","Un radar permet de détecter les flottes et les systèmes qui se trouvent au plus à une distance égale à sa caractéristique &quot;Portée radar&quot;.");
 public static final Description extra=new Description("usine d'extraction avancée","usines d'extraction avancée","Une usine d'extraction avancée permet de rajouter une capacité égale à sa caractéristique &quot;Capacité extraction avancée&quot; au potentiel de production de minerai de la planète. Il ne peut pas y avoir plus d'une usine d'extraction utile par planète.");

 public static final Description battla=new Description("batterie de lasers","batteries de lasers","Une batterie laser permet de mieux défendre la planète sur laquelle elle est construite.");
 public static final Description battpla=new Description("batterie de plasmas","batteries de plasmas","Une batterie plasma permet de mieux défendre la planète sur laquelle elle est construite.");
 public static final Description rampemis=new Description("rampe de missiles","rampes de missiles","Une rampe de missile permet de mieux défendre la planète sur laquelle elle est construite.");
 public static final Description lancetor=new Description("lance-torpilles","lances-torpilles","Un lance-torpilles permet de mieux défendre la planète sur laquelle il est construit.");

 public static final Description gravit=new Description("Module de gravité","modules de gravité","Le module de gravité permet de modifier la gravité d'une planète lors d'une terraformation.");
 
    //technologies simples -->

 public static final Description stratco=new Description("maîtrise du combat","","La connaissance de cette technologie permet de créer des stratégies de combat pour ses flottes.");
 public static final Description diplo=new Description("maîtrise de la diplomatie","","La connaissance de cette technologie permet de créer des alliances.");
 public static final Description creplan=new Description("maîtrise de l'ingénierie","","La connaissance de cette technologie permet de créer des plans de vaisseaux.");
 public static final Description progco=new Description("maîtrise de la planification","","La connaissance de cette technologie permet d'ordonner à un système de construire un type de bâtiment.<BR>Le système gérera ensuite lui-même le lancement des constructions.");
 public static final Description gestpla=new Description("maîtrise de la gestion","","La connaissance de cette technologie permet de détruire des bâtiments inutiles sur ses systèmes et de modifier la taxation et la terraformation de planètes précises, au lieu d'être obligé de modifier toutes les planètes du système en même temps.");

 }



