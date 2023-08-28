L'objectif consiste à développer une application en utilisant Spring Boot avec Java. Cette application aura pour but de peupler une base de données SQL avec des données d'assurance fournies. Ensuite, une application frontale sera développée en Angular afin de récupérer et d'afficher ces données.

Pour commencer, j'ai entrepris l'analyse du fichier JSON contenant les données, et j'ai identifié la classe principale que j'ai nommée "Insurance".

Pour réaliser le remplissage de la base de données, j'ai créé un bean exécutable conçu pour être exécuté une seule fois. Ce bean a pour fonction de lire le fichier JSON et d'enregistrer les informations d'assurance dans la base de données.

![bean](https://github.com/maarouf-ouassime/mapersive-backend/assets/79798140/34accfe6-bff1-44c7-a4bf-2e849d14668b)


J'ai élaboré la classe Java en respectant les noms utilisés dans le fichier JSON, bien que la convention CamelCase aurait normalement dû être appliquée.

Ensuite, j'ai mis en place les repositories JPA correspondants, puis développé les services nécessaires. Une fois ces étapes terminées, j'ai procédé à la création de la partie web, incluant la création du contrôleur, pour permettre l'interaction avec les données et leur affichage via l'application frontale Angular.
