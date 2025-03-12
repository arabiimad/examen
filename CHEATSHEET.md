# Cheatsheet

Ce document répertorie les commandes dont vous aurez besoin durant ce TP (et qui ne sont pas relatives à Gitlab CI, bien entendu).

## Généralités sur Maven
### Lancement d'une commande Maven
Quelques astuces et commandes Maven utiles :

Résumé de l'utilisation de la CLI Maven :
```bash
mvn [options] phase[:task]
```

Options utiles pour les pipelines de CI :
- `--batch-mode` : Force le mode non-interactif (ne demande rien à l'utilisateur)

### Maven & Cycle de vie

Maven est un moteur de production dédié aux projets Java. Il est comparable à Ant ou make sous Linux.
Il permet de réaliser l'ensemble des étapes de production d'un projet à partir des fichiers sources.
La définition du projet Java est décrite dans le fichier pom.xml (Project Object Model).
Il permet de gérer le cycle de vie de génération de l'application :
- `validate` : On verifie la cohérence des informations de compilation
- `compile` : compilation du code source
- `test` : exécution des tests unitaires contenus dans le projet
- `package` : récupération du code compilé et création du package final (.jar) dans le répertoire target
- `install` : copie du package dans le répertoire maven local afin d'être disponible pour d'autre projets locaux
- `deploy` : copie du package sur le repertoire maven distant (Nexus, Artifactory, ..) pour le partager avec d'autres développeurs ou projets

**Important** (source Wikipédia): "l'idée est que, pour n'importe quel but, tous les buts en amont doivent être exécutés sauf s'ils ont déjà été exécutés avec succès et qu'aucun changement n'a été fait dans le projet depuis. Par exemple, quand on exécute mvn install, Maven va vérifier que mvn package s'est terminé avec succès (le jar existe dans target/), auquel cas cela ne sera pas ré-exécuté." **Chaque phase est donc importante dans le cadre d'un pipeline en succès**

## Partie 2
Les images OCI Maven sont disponible sur le Docker Hub en recherchant "maven" : https://hub.docker.com/_/maven.
Nous utiliserons ici Maven 3 avec Eclipse Temurin 21. À vous de trouver l'image correspondante pour configurer les jobs de votre pipeline.

Lancement de la phase de compilation du code Java : lancement des phases `clean` et `compile`.
```bash
mvn clean compile
```

Lancement des tests unitaires avec [JUnit][2] : lancement de la phase `test`
```bash
mvn test
```

Construction du package `.jar` : lancement de la phase `package`
```bash
mvn -DskipTests package
```

**Note :** L'option `-DskipTests` permet de ne pas ré-exécuter les tests

## Partie 3
Maven génère ses fichiers (classes compilées, packages, etc.) dans le répertoire `target`.

Avec Maven, JUnit stocke ses rapports de test sous forme de fichiers `.xml` dans le répertoire `target/surefire-reports/TEST-*.xml`.

Option Maven utile :
- `-Dmaven.repo.local=<path_to_dir>` : Définit le répertoire de cache local de Maven pour le téléchargement des dépendances, plugins, etc. 

> :warning: Maven attend un chemin absolu pour son répertoire local.
> Le chemin absolu vers le répertoire du projet Git est fourni par GitLab dans la variable $CI_PROJECT_DIR

**Note :** Il est possible de passer des options JVM à Maven (au format `-Doption.name=option.value`) sans les saisir dans toutes les commandes Maven, en utilisant la variable d'environnement `MAVEN_OPTS`, prise en charge par Maven. Exemple :
Sans `MAVEN_OPTS` :
```bash
mvn --batch-mode -Dmaven.repo.local=<mydir> compile
```
Avec `MAVEN_OPTS` :
```bash
MAVEN_OPTS="-Dmaven.repo.local=<mydir>"
mvn --batch-mode compile
```

## Partie 4

### Plugin Maven SonarQube
**Phases maven à exécuter :** `verify sonar:sonar`

Les propriétés sont à passer au format options JVM (`-Dproperty.name=value`).

| Propriétés                | Description                               | Valeur exemple                                                        |
| ------------------------- | ----------------------------------------- | --------------------------------------------------------------------- |
| `sonar.projectKey`        | La clé de votre projet SonarCloud. Utilisez `<votre trigramme>` pour le TP.                | `LGR`                 |
| `sonar.java.binaries`     | Emplacement des classes java compilées    | `./target/classes`                                                    |
| `sonar.qualitygate.wait`  | Attend le résultat de l'analyse. Impacte le code retour du scan | `true`                                          |


## Partie 5

Il y a plusieurs manières d'exécuter un job seulement sur une branche en particulier, référez vous à [la documentation GitLab CI](https://docs.gitlab.com/ee/ci/yaml/)

## Partie 6

### JIB
`Jib` : Il s'agit d'un outil pour les projets Java qui va vous créer des conteneurs sans utiliser de fichier Dockerfile ni nécessiter l'installation de Docker. Vous pouvez utiliser Jib dans les plug-ins Jib pour Maven ou Gradle, ou utiliser la bibliothèque Java Jib. Jib gère toutes les étapes de l'empaquetage de votre application dans une image de conteneur. Vous n'avez pas besoin de connaître les bonnes pratiques pour créer des fichiers Dockerfile ou installer Docker. 
Jib organise votre application en plusieurs couches (dépendances, ressources et classes) et utilise la mise en cache des couches d'images Docker pour accélérer les compilations en recréant uniquement les modifications. L'organisation des couches de Jib et l'image de base de petite taille réduisent la taille globale de l'image, ce qui améliore les performances et la portabilité.
Il va fonctionner comme un plugin à ajouter dans votre fichier pom.xml (voir documentation officielle) et pourra être lancé comme une commande maven.

Exemple :
```
build-image:
  stage: build-image
  image: maven:3-eclipse-temurin-21
  script:
    - mvn jib:build -Djib.to.image="$CI_REGISTRY_IMAGE:$BUILD_VERSION" -Djib.to.auth.username="$CI_REGISTRY_USER" -Djib.to.auth.password="$CI_REGISTRY_PASSWORD"
```

**NOTE :** Pour les autres technologies que Java, on pourra par exemple utiliser [Kaniko][3] en CI, pour construire une image à partir d'un Dockerfile


[1]: https://github.com/fusesource/jansi
[2]: https://junit.org/
[3]: https://docs.gitlab.com/ee/ci/docker/using_kaniko.html
