//v1.0 03/12/01

Voilà donc l'ensemble des sources d'océane 2.

Comme j'ai quasiment appris à programmer en même temps que
j'ai créé ce programme, l'ensemble n'est ni commenté, ni très facile
à faire évoluer. Cependant il a l'avantage de fonctionner et d'avoir été testé 
en "fonctionnement réel" ;o) pendant plus d'un an.

Une version 3 est en cours d'élaboration ici :
http://www.e-oceane.net/

Toute question sur ces sources est à poser sur le forum suivant :
http://jeu.oceane.sources.free.fr/w-agora/index.php3?bn=forumso_progra

Pour installer et faire fonctionner ce programme, les prérequis sont un processeur 500Mhz
et 256 Mo de RAM. Ces limites sont indicatives : le programme peut tourner avec beaucoup moins que ca!

Au niveau soft, le jdk 1.2 ou supérieur est nécessaire ainsi qu'apache et mysql.
Pour le jdk, voyez le site de sun :
http://java.sun.com/products/
pour apache et mysql, sous windows easyphp fait ca très bien :
http://www.manucorp.com/


Attention dans certains .bat sous linux, n'oubliez pas de remplacer les ";" par des ":"

**************************
Procédure d'installation :
**************************

1)Créer les galaxies.

Le programme est paramétré par défaut pour comporter deux galaxies.
Si vous voulez changer cela, vous devrez modifier les sources et recompiler grace à
la commande co.bat. Pour la première installation, je vous conseille de procéder
à une installation standard.

	a) lancer i1.bat -> initialise l'univers.
	b) lancer g1 et g2 -> crée les galaxies 1 et 2
	c) lancer image_g1 et image_g2 -> crée les images des galaxies
	d) lancer image_secteur1 et image_secteur2 -> crée les images des secteurs des galaxies.
			notez que des problèmes "apparaîtront". c'est lié aux chargements des images,
			ce n'est pas grave normallement.
	e) lancer html_secteur1 et html_secteur2 -> crée les fichiers html de présentation de l'atlas.
		normallement pas nécessaire à) lancer car ca exsite déjà dans l'atlas.
		
Et voilà vous avez maintenant des données dans le répertoire Univers (là où seront stockées 
	toutes vos données du jeu)!
Vous pouvez transvaser les données de Univers/atlas vers site/atlas -> votre atlas est créé
	
2)Installer la base en locale

Vous avez installé sur votre ordinateur une base mysql et un sevreur apache.
Transférer le contenu du répertoire "localhost" dans le répertoire d'exécution de script
Si il y a un mot de passe à votre base, modifiez le fichier secure/connect.txt
Ensuite créer à la main une base "jeu"
puis lancer le script mJ/creer_tables.php3
Si tout se passe bien un certain nombre de tables sont créées et c'est tout ce que vous avez besoin
de faire pour l'instant ici.

3)Installer le site sur le web.

Vous pouvez transvaser l'ensemble du contenu du répertoire "site" sur le web.
Attention, le site actuel présuppose plusieurs choses :

	1) il utilise un script perl propre à free pour s'identifier dans .htaccess.
		A vous de modifier cela si vous n'utilisez pas free.
	2) modifier les fichiers site/secure/connect.txt & site/secure/mj.txt & site/secure/mj2.txt
	3)la base mysql sur le site doit s'appeler "jeu.oceane. Si ce n'est pas le cas,
		il vous faut modifier la variable $base dans certains scripts de
		site/mj site/mj2 site/ordres
		
	4) vous devez créer cette base jeu.oceane puis lancer le script mj/creer_tables.php3 pour
		créer les tables.
		
		
L'installation est maintenant terminée, on peut passer à la phase :

**************************
Procédure du tour de jeu :
**************************
	

1)vous avez du récupérer un certain nombre d'inscription dans la base du site, puis plus tard des ordres.

lancer les scripts suivant :

- site/mj/transferer_fichier.php3
- site/mj/transferer_fichier2.php3
- site/mj/transferer_fichier3.php3

Ces trois scripts vont sauver les données importantes de la base dans 3 fichier, ok.txt, ok2.txt et ok3.txt

Vous pouvez ensuite lancer les scripts

-site/mj/supprimer_tables.php3 -> efface le contenu des tables
-site/mj/actu.php3 -> efface certains fichiers temporaires

2)télécharger en ftp les fichier "ok" et mettez les dans le même repertoire où se trouve vos fichiers
localhost/mj.

vous pouvez nettoyer la base avec 
localhost/mj/supprimer_tables.php3

puis charegz le contenu de la base avec 
localhost/mj/chargement.php3
localhost/mj/chargement2.php3
localhost/mj/chargement3.php3

3)lancer la résolution avec o2.bat

4)zipper les rapports avec z.bat

5)envoyez les mails avec m.bat

6)télécharger en ftp le contenu de Univers/tourX/secure2 dans le répertoire correspondant sur le site
puis
télécharger en ftp le contenu de Univers/tourX/zip dans le répertoire rapports/
puis télécharger le contenu de Univers/tourX/ordres dans mj/ sur le site

7)lancer les scripts suivants à partir du site pour charger les ordres :
mj/chargement.php3
mj/chargement2.php3
mj/chargement3.php3
mj/chargement4.php3

La procédure du tour est terminée!

Bonne chance!



