# Projet - Vente de livres

Consulter issues pour tâches à faire.

## Git workflow

Il y a 2 branches principales :

* `master` ne reçoit que les commits testés
* `dev` reçoit les nouvelles features

TOUT commit doit passer par `dev` avant d'être sur `master`.

Pushez d'abord sur la branche `dev`, ainsi tous les contributeurs peuvent pull les dernières modifications et vérifier que ça fonctionne.

Evidemment, si vous ne faites que rajouter de la doc, des commentaires ou formater, vous pouvez pusher directement sans créer de branche.

### Commandes

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

# quand vous avez fini de travailler sur une feature,
# soyez bien sur votre branche,
# puis faites un rebase de dev vers votre branche pour avoir les dernières modifs
git rebase -i dev

# si vous avez des conflits, réglez-les, puis :
git rebase --continue

# quand un rebase est terminé, vous pouvez taper :
git rebase --skip

# maintenant, mergez votre branche dans dev,
# il ne devrait y avoir aucun conflit !
# et l'historique git reste clean
git checkout -dev
git merge maBranche --no-ff
```
