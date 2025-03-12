# Enoncé du TP (en binôme)

Pour réaliser ce TP, il est **conseillé** d'utiliser l'IDE intégré à GitLab et le Pipeline Editor.

## Ce TP utilise

- Git
- Eclipse Temurin (OpenJDK) 21
- Maven 3.x

Pour ce TP, nous utiliserons l'image Docker Maven en version `3-eclipse-temurin-21`

## Astuces

- Gitlab inject des variables disponibles dans la CI, vous trouverez la liste [ici](https://docs.gitlab.com/ee/ci/variables/predefined_variables.html)
- Vous pouvez aussi injecter vos propres variables dans **Settings > CI/CD**

## Préparation

1. Chaque personne doit demander l'accès au groupe `Forks/<Année>`
2. Forkez ce projet dans le groupe `Forks/<Année>`
3. Supprimer le lien entre le fork et le projet de base (dans le menu Settings > General > Advanced > Remove fork relationship)

## Partie 1 - Configuration du repository

1. Configurez le repository pour que personne ne puisse push directement sur la branche `main`.
2. Pour tester, essayez d'ajouter un commit modifiant un fichier directement sur la branche `main`.

## Partie 2 - Initialisation d'un pipeline dans GitLab CI

1. Créez une nouvelle branche + merge request
2. Ajouter un fichier `.gitlab-ci.yml` à votre projet
3. Faite en sorte que votre fichier gitlab-ci.yml lance un job de compilation
4. Quand la compilation passe, vous devez créer un autre job permettant de lancer les tests unitaires
5. Quand les tests unitaires passent, ajoutez un job permettant de créer le package "FatJar"

## Partie 3 - Cache et artifacts
1. Créez une nouvelle branche + merge request
2. Mettez les dépendances en cache pour éviter que Maven ne les télécharge à chaque job
3. Faites en sorte de passer le répertoire de compilation de Maven d'un job à l'autre
4. Vérifiez que chaque job n'exécute que la phase maven qui le concerne (voir la cheatsheet pour en savoir plus sur les phases maven)
5. Modifiez votre pipeline pour publier les rapports JUnit dans GitLab

## Partie 4 - Code Quality

1. Créez vous un compte sur [SonarCloud](https://sonarcloud.io/) et créez votre Organisation et un projet.
2. Créez une nouvelle branche + merge request
3. Modifiez votre pom.xml permettant l'utilisation du Sonar Scanner
4. Ajoutez un nouveau job à votre pipeline pour que GitLab CI exécute Sonar Scanner

## Partie 5 - Upload du package

1. Créez une nouvelle branche + merge request
2. Ajoutez un job permettant l'upload du package dans le Package Registry maven de GitLab
3. Faites en sorte que ce job ne s'exécute que sur la branche par défaut du projet

## Partie 6 - Création d'une image docker

1. Créez une nouvelle branche + merge request
2. Ajoutez un nouveau job pour construire et uploader une image Docker de l'application *(cf: Packages & Registries > Container Registry)*. Le tag de l'image doit être `latest`.
3. Modifiez ce job pour qu'il ne s'exécute que sur la branche `main`.
4. Modifiez ce job pour l'autoriser également à s'exécuter lors de la création d'un tag Git. Dans ce cas, le tag de l'image devra être identique au tag Git.
 
*Aide: [Plugin Maven Jib](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin)*
