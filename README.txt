###################### ENSICAEN Awale et ENSICAEN AwaleServer ##################
Auteurs :       Jérémie LECLERC et Pierrick HUE
Date :          Juin 2016
Dépot GitHub :  https://github.com/Jeremielc/ENSICaen_Awale.git
                https://github.com/Jeremielc/ENSICaen_AwaleServer.git

################################ Mode d'emploi #################################
Lancer le programme ENSICaen_AwaleServer.jar.
Une fois lancé, celui ci se met en attente de connexion des deux joueurs.
Chaque joueur doit lancer le programme ENSICaen_Awale.jar.
Une fois lancé, rendez-vous dans le menu "File" et sélectionnez "connect".
Dans la fenêtre que s'ouvre, rentrez les information du serveur. Le port de
communication est configuré par défaut sur 15425.

Remarque : il n'est pas nécessaire de disposer de 3 machines pour joueur (deux
joueurs plus un serveur). En effet, l'un des joueurs peut jouer le rôle de la
machine hôte. Il devra alors utiliser l'adresse IP 127.0.0.1 comme adresse IP
du serveur.

############################ Difficulté rencontrées ############################


############################## Gestion de projet ###############################
Pour faciliter la communication et le transferts des codes sources, nous avons
utiliser un dépôt Git pour chaque projet (le serveur étant développé
séparément du jeu de base).

Le serveur ne sert que de routeur entre les deux joueurs.
L’intelligence derrière le jeu est gérée par le client de l’utilisateur.

############################### Règles du jeu ##################################
Règle 1 : Seulement deux joueurs peuvent s'affronter

Règle 2 : On répartit quarante-huit graines dans les douze trous à raison de
quatre graines par trou.

Règle 3 : Chaque joueur joue à tour de rôle, celui qui joue en premier est tiré
au hasard. Le joueur va prendre l'ensemble des graines dans l'un des trous de
son territoire et les distribuer, un par trou, dans le sens inverse des
aiguilles d'une montre.

Règle 4 : Si sa dernière graine tombe dans un trou du camp adverse et qu'il y a
maintenant deux ou trois graines dans ce trou, le joueur récupère ces deux ou
trois graines et les met de côté. Ensuite il regarde la case précédente : si
elle est dans le camp adverse et contient deux ou trois graines, il récupère ces
graines, et ainsi de suite jusqu'à ce qu'il arrive à son camp ou jusqu'à ce
qu'il y ait un nombre de graines différent de deux ou trois.

Règle 5 : On ne saute pas de case lorsqu'on égrène sauf lorsqu'on a douze
graines ou plus, c'est-à-dire qu'on fait un tour complet : on ne remplit pas la
case dans laquelle on vient de prendre les graines.

Règle 6 : Il faut « nourrir » l'adversaire, c'est-à-dire que, quand celui-ci n'a
plus de graines, il faut absolument jouer un coup qui lui permette de rejouer
ensuite. Si ce n'est pas possible, la partie s'arrête et le joueur qui allait
jouer capture les graines restantes.

Règle 7 : Si un coup devait prendre toutes les graines adverses, alors le coup
peut être joué, mais aucune capture n'est faite : il ne faut pas « affamer »
l'adversaire.

Règle 8 : La partie s'arrête quand un des joueurs a capturé au moins 25 graines,
et est désigné gagnant, ou qu'il ne reste qu'au plus 6 graines en jeu.

Règle 9 : Quand il ne reste qu'au plus 10 graines sur le plateau, le joueur qui
a la main peut proposer l'abandon de la partie. Si il est accepté les deux
joueurs se partagent les graines restantes. Si le total des graines du plateau
est inférieur à 6, sans qu'aucun des joueurs n'a un total de graines supérieur à
24. La partie est nulle.
