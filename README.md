# Enoncé du TP

Pour réaliser ce TP, il est **conseillé** d'utiliser l'IDE intégré à GitLab et le Pipeline Editor.

## Ce TP utilise

- Git
- Eclipse Temurin (OpenJDK) 21
- Maven 3.x

Pour ce TP, nous utiliserons l'image Docker Maven en version `3-eclipse-temurin-21`

## Préparation

1. Forkez ce projet dans votre repo gitlab personnel
2. Créer un ficher de réponse au question à la racine du projet
Chaque fois qu'il y a une question y répondre dans le fichier de réponse que vous aurez mis à la racine du projet.

## Partie 1 - Configuration du repository

1. Configurez le repository pour que personne ne puisse push directement sur la branche `main`.
2. Pour tester, essayez d'ajouter un commit modifiant un fichier directement sur la branche `main`.

Question: 

1) Pourquoi est ce important d'empécher de modifier directement la branche main ? 

## Partie 2 - Initialisation d'un pipeline dans GitLab CI

1. Créez une nouvelle branche + merge request
2. Ajouter un fichier `.gitlab-ci.yml` à votre projet

Question 

2) Que fait le fichier .gitlab-ci.yml par défaut ?  Quelles sont les jobs qui sont lancés et que font ils ?

3. Faite en sorte que votre fichier gitlab-ci.yml lance un job de compilation
4. Quand la compilation passe, vous devez créer un autre job permettant de lancer les tests unitaires
5. Quand les tests unitaires passent, ajoutez un job permettant de créer le package "FatJar"

Question

3) Explique avec tes propres mots ce qu’est le DevOps et pourquoi il est important d’intégrer le DevOps aux gros projets d’entreprise ?

4) Votre client est la CMA/CGM et vous avez développé une application de gestion des conteneurs portuaires à l’arrivée des bateaux sur le port de Marseille Fos.
Vous avez livré la version 4.2 la semaine dernière et le client se rend compte en catastrophe qu’il y a un bug sur la gestion du nombre de conteneur sur l’application. Il vous demande en urgence absolue de corriger ce bug et de livrer à nouveau une version patché nommé 4.21.
Décrire les étapes entre la demande du client et la livraison de la version 4.21 d’une part sans la mise en place de CI/CD puis dans le cas où la CI/CD est déjà en place. (On livre la version le plus vite possible mais on veille cette fois ci à bien tester l’application pour ne pas décevoir à nouveau le client avec un nouveau bug). A votre avis, combien de temps cela a permis de gagner ?

## Partie 3 - Code Quality (Bonus)

2. Créez une nouvelle branche + merge request
3. Modifiez votre pom.xml permettant l'utilisation du Sonar Scanner
4. Ajoutez un nouveau job à votre pipeline pour que GitLab CI exécute Sonar Scanner
 
