# TP6 DevOps – Mini-Librairie `DataFrame` en Java

Bienvenue dans ce projet DevOps réalisé dans le cadre du Master 1 Informatique à l'Université Grenoble Alpes.  
Ce projet met en œuvre une mini-librairie de manipulation de données inspirée de `pandas`, avec une approche DevOps complète.

## 📚 Objectifs pédagogiques

- Développement collaboratif avec Git (branches, commits, merge)
- Mise en place de tests unitaires et couverture de code
- Génération de documentation avec JavaDoc
- Dockerisation et déploiement sur Docker Hub
- Suivi de qualité logicielle avec Maven et JaCoCo

---

## 🛠️ Fonctionnalités principales

| Classe       | Description |
|--------------|-------------|
| `Series<T>`  | Colonne typée contenant des données homogènes |
| `DataFrame`  | Tableau de données composé de plusieurs `Series` |
| `CsvLoader`  | Lecture d’un fichier CSV et conversion en `DataFrame` |
| `App`        | Application interactive en ligne de commande avec menu |

Fonctionnalités clés :
- Chargement depuis un fichier CSV
- Affichage : `head`, `tail`, `full`
- Sélection de colonnes et lignes
- Statistiques (`min`, `max`, `mean`, `count`) sur colonnes numériques

---

## 📂 Structure du projet

```
TP6-Devops/
├── src/
│   ├── main/
│   │   └── java/com/ilian/dataframe/
│   │       ├── App.java
│   │       ├── CsvLoader.java
│   │       ├── DataFrame.java
│   │       └── Series.java
│   └── test/
│       └── java/com/ilian/dataframe/
│           ├── AppTest.java
│           └── DataFrameTest.java
├── data/
│   └── etudiants.csv
├── Dockerfile
├── pom.xml
└── README.md
```

---

## ✅ Exécution des tests

Lancer tous les tests avec Maven :
```bash
mvn clean test
```

La couverture de code est générée via JaCoCo dans : `target/site/jacoco/index.html`  
**Couverture obtenue : > 79%**

---
[![Unit Tests & Deploy](https://github.com/Lucixxe/TP6-Devops/actions/workflows/deploy.yml/badge.svg?branch=zodecky%2Ffeature%2Fadd-cd)](https://github.com/Lucixxe/TP6-Devops/actions/workflows/deploy.yml)

## 📄 Générer la documentation JavaDoc

```bash
mvn javadoc:javadoc
```

JavaDoc générée dans le dossier : `target/site/apidocs/index.html`

---

## 🐳 Dockerisation

### 1. Construire l’image Docker
```bash
docker build -t tp6-devops .
```

### 2. Lancer l'application
```bash
docker run -it --rm tp6-devops
```

> Menu interactif pour explorer les données et afficher les statistiques.

### 3. Image disponible sur Docker Hub

📦 [https://hub.docker.com/r/lucixxe/tp6-devops](https://hub.docker.com/r/lucixxe/tp6-devops)

```bash
docker pull lucixxe/tp6-devops:latest
docker run -it --rm lucixxe/tp6-devops:latest
```

---

## 🧪 Exemple de fichier `etudiants.csv`

```
Nom,Age,Note
Alice,22,15
Bob,23,13
Charlie,21,18
David,24,12
Eva,22,17
Farid,23,11
Gina,22,19
Hugo,25,14
Imane,21,13
Jules,24,16
Katia,23,16
Léo,25,18
Mina,21,15
Nora,22,12
Oscar,23,17
```

---

## 🧰 Choix des outils

- Maven pour la gestion de projet, des dépendances et l’exécution des tests.
- JaCoCo pour la génération de rapports de couverture de code.
- JavaDoc pour générer la documentation technique.
- Docker pour garantir la portabilité et la simplicité du déploiement.
- JUnit pour les tests unitaires.

## 🔄 Structure Git et Workflow

### 📚 Branches Git
- main : branche stable contenant les versions validées
- feature/<nom> : développement de nouvelles fonctionnalités (revues de code obligatoires)
- patch/<nom> : petites corrections ou ajustements rapides
- devops/<outil> : changements liés aux outils DevOps (Docker, GitHub Actions, etc.)

### 📦 Pull Requests
- Toutes les PR vers main passent par des vérifications automatiques
- Les branches feature/* sont systématiquement soumises à revue par un membre de l’équipe

⸻

### 🚀 CI/CD avec GitHub Actions

#### 🔧 Intégration Continue (CI)
**Chaque pull request vers main déclenche automatiquement :**

- Compilation du projet
- Exécution de tous les tests unitaires
- Génération du rapport de couverture avec JaCoCo
- Le merge est refusé si la couverture est < 60%

#### 🚀 Déploiement Continu (CD)
**Chaque push ou merge sur main déclenche :**

- Les mêmes tests et vérifications que la CI
- Si tout passe, le projet est automatiquement déployé sur GitHub Maven Packages 

## 💡 Améliorations possibles (non demandées mais envisagées)

- Filtres conditionnels (`df.filter(col > 15)`)
- Export CSV
- Support d'autres formats (JSON, XML)
- Interface graphique (Swing ou web)

---

## 💬 Feedback

- Le début du projet a été un peu difficile à organiser, en partie à cause du nombre d’outils à configurer. Certains, comme Docker, demandent pas mal d’ajustements pour être vraiment utiles dans un projet simple.

- En revanche, GitHub Actions s’est révélé très efficace, notamment pour automatiser les tests lors des Pull Requests. Une fois le rythme de travail établi, la collaboration est devenue plus fluide.

- Nous avons aussi tenté d’utiliser Google Cloud, mais l’expérience a été compliquée à cause d’une prise en main peu intuitive.

- Au final, ce projet nous a permis de mieux comprendre comment structurer un projet DevOps. Le TP était intéressant.


---

## 👨‍💻 Auteurs

- **Ilian BENAOUDIA**
- **Gabriel ZAGURY DE MAGALHÃES**

---
Projet encadré dans le cadre du **TP6 de DevOps** – M1 Informatique – UGA
