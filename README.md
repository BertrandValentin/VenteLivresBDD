# Projet - Vente de livres

Consulter [to-do list](TODO.md) pour tâches à faire.

## Guidelines

SQL :

* [Coding guidelines](https://dev.mysql.com/doc/dev/mysql-server/latest/PAGE_CODING_GUIDELINES.html)
* [Style guide](http://www.sqlstyle.guide/)
* [Security](https://dev.mysql.com/doc/refman/5.7/en/security-guidelines.html)

Code styles :

* [Java](https://google.github.io/styleguide/javaguide.html)
* [HTML/CSS](https://google.github.io/styleguide/htmlcssguide.html)
* [JavaScript](https://google.github.io/styleguide/jsguide.html)
* [XML/XHTML](https://google.github.io/styleguide/xmlstyle.html)

Linting : 

* TODO: intégrer [ce fichier](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml) (Google's Eclipse style)

## Git workflow

Il y a 2 branches principales :

* `master` ne reçoit que les commits testés
* `dev` reçoit les nouvelles features

TOUT commit doit passer par `dev` avant d'être sur `master`.

Pushez d'abord sur la branche `dev`, ainsi tous les contributeurs peuvent pull les dernières modifications et vérifier que ça fonctionne.

Evidemment, si vous ne faites que rajouter de la doc, des commentaires ou formater, vous pouvez pusher directement sans créer de branche.

```shell
# assurez vous que 
# https://github.com/BertrandValentin/VenteLivresBDD.git
# apparaisse comme 'origin' lorsque vous tapez cette commande
git remote -v

# ne commitez pas directement sur master,
# créez une branch qui porte le nom de la feature sur laquelle vous travaillez
git checkout -b maBranche

# commitez sur cette branche,
# ensuite pushez les commits sur le repo
git push -u origin maBranche

# pour télécharger une branche :
git pull -u origin uneAutreBranche

# pensez à mettre à jour dev CHAQUE FOIS qu'il y a de nouveaux commits
git pull -u origin dev
```

### Eviter merge conflicts

```shell
# quand vous avez fini de travailler sur une feature,
# soyez bien sur votre branche
# puis faites un rebase de dev vers votre branche pour avoir les dernières modifs
git rebase -i dev

# si vous avez des conflits, réglez-les, puis :
git rebase --continue

# quand un rebase est terminé (no more conflicts), vous pouvez taper :
git rebase --skip

# maintenant, mergez votre branche dans dev,
# il ne devrait y avoir aucun conflit !
# et l'historique git reste clean
git checkout -dev
git merge maBranche --no-ff # cette option évite certains conflits
```

### Commandes utiles

**Editer le dernier commit** :

```shell
# Plutôt que de faire un nouveau commit chaque fois que vous changez un caractère :
git commit --amend
# ça ouvre un éditeur de texte pour le message du dernier commit
# éditez le message ou faites ctrl + x pour quitter
# NE JAMAIS éditer un commit déjà push !
```

**Voir les différences entre deux commits** :

```shell
# récupérez les hashs des 2 commits via 'git log'
git diff commit1 commit2

# voir les changements actuels sur les fichiers
git diff
git diff monFichier

# voir les changements ajoutés via 'git add' (staged changes)
git diff --cached
git diff --cached monFichier
```

**Supprimer les changements pas encore commités** :

Vous avez modifié un fichier, c'était une erreur. Faites :

```shell
# consultez les fichiers changés
git status
# éliminez les changements sur le fichier modifié par erreur :
git checkout monFichier
# note : utilisez tab pour la complétion automatique

# si vous voulez rapidement éliminer TOUS les changements
git stash && git stash --drop
```

**Eliminer le dernier commit** :

Vous avez fait un mauvais commit, vous voulez l'éliminer. Si vous ne l'avez pas encore push, tapez :

```shell
git reset HEAD~
# vérifiez que la modif est bien ce que vous vouliez :
git log
```



