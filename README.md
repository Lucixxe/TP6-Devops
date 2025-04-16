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

## 💡 Améliorations possibles (non demandées mais envisagées)

- Filtres conditionnels (`df.filter(col > 15)`)
- Export CSV
- Support d'autres formats (JSON, XML)
- Interface graphique (Swing ou web)

---

## 👨‍💻 Auteurs

- **Ilian BENAOUDIA**
- **Gabriel Zagury de Magalhaes**

Projet encadré dans le cadre du **TP6 de DevOps** – M1 Informatique – UGA