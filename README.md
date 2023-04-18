
Réalisation d'une application exposant une API REST pour gérer le classement de joueurs lors d'un tournoi.

Les joueurs sont triés en fonction du nombre de points de chacun, du joueur ayant le plus de points à celui qui en a le moins.

L'API devra permettre :
- [X]  d'ajouter un joueur (son pseudo)
- [X]  de mettre à jour le nombre de points du joueur
- [X]  de récupérer les données d'un joueur (pseudo, nombre de points et classement dans le tournoi)
- [X]  de retourner les joueurs triés en fonction de leur nombre de points
- [X]  de supprimer tous les joueurs à la fin du tournoi

L'application devra être réalisée en Kotlin, pourra utiliser le framework d'injection Koin (Optionnel), et basée sur Ktor.  
L'application pourra utiliser la technologie de base de données de votre choix, de préférence MongoDB.

Le service devra s'initialiser et se lancer par un script bash.

Bonus :  
Fournir une interface d'administration (une UI) permettant de visualiser les joueurs présents dans l'application; en Angular.
URL front: https://github.com/Karlitooooo/tournament-angular
