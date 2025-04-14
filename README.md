# TP6-Devops-DataframeLib

**Une bibliothèque Java d'analyse de données inspirée de Pandas**

## 🚀 Objectif

`DataframeLib` est une bibliothèque d'analyse de données légère et orientée objet, écrite en Java, qui vise à reproduire certaines fonctionnalités essentielles de la librairie Pandas de Python.

## ✨ Fonctionnalités principales

- Création d'un `DataFrame` à partir de colonnes (`Series`)
- Affichage complet d'un `DataFrame`
- Affichage de `head(n)` et `tail(n)` pour aperçus rapides
- Fonctions statistiques (en cours de développement)
- Méthodes de sélection de lignes et colonnes (en cours)
- Import/export CSV (fonctionnel)
- Architecture orientée test avec JUnit 5

## ⚙️ Outils et technologies

- **Java 17** : langage principal
- **Maven** : gestionnaire de projet et dépendances
- **JUnit 5** : framework de tests unitaires
- **JaCoCo** : outil de couverture de code
- **GitHub Actions** : pipeline CI/CD

## 📚 Structure Git

- Branches principales : `main`
- Branches de fonctionnalité : `feature/<nom>`
- Branches de correctifs : `patch/<nom>`
- Branches DevOps : `devops/<outil>` (ex: ajout de GitHub Actions)
- Pull Requests : toutes les PR vers `main` sont soumises à des vérifications automatisées

## 🚜 CI/CD

Notre pipeline CI/CD est déployé via GitHub Actions.

### ✅ Intégration Continue (CI)

- Chaque **Pull Request** vers `main` déclenche :
  - Compilation et tests automatiques
  - Analyse de couverture de code avec JaCoCo
  - Refus de merge si la couverture < 60%

### 🌟 Déploiement Continu (CD)

- Chaque \*\*merge ou push sur \*\***`main`** déclenche :
  - Les mêmes tests que pour la CI
  - Si tout passe, **déploiement automatique** vers GitHub Maven Packages



## 📊 Fonctionnalités à venir

- Opérations statistiques : moyenne, variance, etc.

- Sélections : par index, conditions, labels

- Amélioration de la couverture de code

## ✍️ Contribution

- Forkez le repo

- Créez une branche `feature/` ou `patch/`&#x20;

- Assurez-vous que les **tests** **passent** et que la **couverture → ≥ 60%**

- Soumettez une PR avec une description claire

## 👨‍💻 Auteurs

- Ilian Benaoudia
- Gabriel Zagury de Magalhaes
