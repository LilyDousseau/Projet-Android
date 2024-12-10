# Application de visualisation d'événements sur Paris

Cette application Android permet aux utilisateurs de visualiser et explorer des événements sur Paris. Elle offre plusieurs fonctionnalités, notamment la visualisation des événements sur une carte, une liste détaillée, et des informations sur chaque événement. Les utilisateurs peuvent également marquer leurs événements favoris.

## 📝 Fonctionnalités

- Liste des événements : affiche une liste triée des événements, avce leur photo, leur titre et leur lieu
- Carte des événements : affiche les emplacements des événements sur une carte interactive avec des marqueurs personnalisés
- Favoris : permet aux utilisateurs de marquer des événements comme favoris
- Affichage des détails : fournit des informations détaillées sur chaque événement, telles que le prix, les dates ou encore une description
- Mise à jour des données : un bouton Refresh permet d'actualiser les données des événements

## 💻 Technologies

- Langage : Kotlin 
- Architecture : architecture basée sur des fragments et des activités
- API : communication avec une API pour récupérer les événements
- UI :
  - RecyclerView pour afficher la liste des événements
  - Google Maps pour afficher les événements sur une carte
  - Toolbar personnalisée avec action de réinitialisation des données (Refresh)
- Images : chargement des images à partir d'URLs avec Glide
- Gestion des favoris : gestion locale des événements favoris

## ⚙️ Configuration requise

- Android 5.0 (API niveau 21) ou supérieur
- Connexion Internet pour charger les données des événements

## 💻 Installation

- Clonez le dépôt : git clone https://github.com/username/event-management-app.git
- Ouvrez le projet dans Android Studio
- Synchronisez les dépendances dans le fichier build.gradle 
- Ajoutez votre clé API dans la partie meta-data du fichier AndroidManifest.xml
- Lancez l'application sur un émulateur ou un appareil physique

## 🚀 Utilisation

- Navigation dans l'application
    - Accédez à la liste des événements en appuyant sur le bouton "Liste"
    - Passez à la vue "Carte" pour voir les emplacements des événements et leur nom en cliquant sur le marqueur
    - Affichez les détails d'un événement en sélectionnant un élément de la liste
    - Réinitialisez les données des événements en appuyant sur l'icône refresh dans la toolbar

- Gestion des favoris
    - Marquez un événement comme favori en appuyant sur l'étoile dans le détail de l'événement
    - Les favoris sont indiqués par une étoile pleine et mis en haut de la liste

## 📝 Crédits

- Nom : Lily DOUSSEAU, Clément GORON
- URL des données : https://opendata.paris.fr/explore/embed/dataset/que-faire-a-paris-/table/?disjunctive.tags&amp;disjunctive.address_name&amp;disjunctive.address_zipcode&amp;disjunctive.address_city&amp;disjunctive.pmr&amp;disjunctive.blind&amp;disjunctive.deaf&amp;disjunctive.price_type&amp;disjunctive.access_type&amp;disjunctive.programs


