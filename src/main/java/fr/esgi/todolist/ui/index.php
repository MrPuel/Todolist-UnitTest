<!-- CSS -->
<style>
    .header{
        width : 600px;
        margin : 0 auto;
        background-color : green;
        height : 60px;
    }
    
    .header_titre {
        color : white;
        text-align : center;
        margin-bottom : -20px;
        font-size : 28px;
    }
    
    body {
        margin : 0;
    }
    
    .taches_input {
        width : 600px;
        margin : 0 auto;
        margin-top : 40px;
        display : grid;
        grid-template-columns:  8fr 2fr;
    }
    
    #inserer {
        height : 45px;
    }
    
    #envoyer {
        background-color : green;
        height : 45px;
    }
    
    
    .taches {
        width : 600px;
        border: 1px solid green;
        margin : 0 auto;
        margin-top : 30px;
        border-collapse : collapse
    }
    
    th {
        border: 1px solid green;
        padding : 10px;
    }
    
    td {
        border : 1px solid green;
        text-align : center;
        padding : 10px;
    }
    
    .suppr {
        color : red;
        text-decoration : none;
    
    }
    
    .suppr:hover {
        color : red;
        text-decoration : none;
    }
</style>

<?php

 session_start(); 
 //test de la connexion a la bdd
    if ($db = mysqli_connect('localhost','root','','todolist')){
        echo "";
    }	
    else {
        echo "Erreur";
    }
 
    if (isset($_POST['creer_tache'])) { // On vérifie que la variable POST existe
        if (empty($_POST['creer_tache'])) {  // On vérifie qu'elle a une valeur
            $erreurs = 'Vous devez indiquer la valeur de la tâche';
        } else if (strlen($_POST['creer_tache']) > 100) {
            $erreurs = 'Une tâche peut contenir 100 caractères maximum';
        } else {
            $tache = $_POST['creer_tache'];
            // On insère la tâche dans la base de donnée
    
            $requete="INSERT INTO tache VALUES (null,'".$tache."',0);";				
            $resultat = mysqli_query($db, $requete) OR DIE ("Erreur" . mysqli_error() . "dans la requete $requete");
        }
    }
    
    if(isset($_GET['supprimer_tache'])) {
        $id = $_GET['supprimer_tache'];
        $requete="DELETE FROM tache WHERE id='$id';";				
        $resultat = mysqli_query($db, $requete) OR DIE ("Erreur" . mysqli_error() . "dans la requete $requete");
    }
    
    if(isset($_GET['valider_tache'])) {
        $id = $_GET['valider_tache'];
        $requete="UPDATE tache SET valide=1 WHERE id='$id';";				
        $resultat = mysqli_query($db, $requete) OR DIE ("Erreur" . mysqli_error() . "dans la requete $requete");
    }
 
?>

<link rel="stylesheet" href="tolist.css"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="icon" type="image/ico" href="https://image.flaticon.com/icons/svg/1721/1721929.svg" />
<title>Todo List ! </title>
<meta charset="utf-8">

<div class="header">
    <p class="header_titre">Todo List ! </p>
</div>

<form class="taches_input" method="post" action="index.php">
 
 <input id="inserer" type="text"  class="form-control" placeholder="Ecrire la tâche ici" name="creer_tache"/>
 <button id="envoyer" class="btn btn-primary">Créer</button>
 <?php
     if (isset($erreurs)) {
 ?>
         <p><?php echo $erreurs ?></p>
 <?php
     }
 ?>
 </form>

<table class="taches">

    <tr align="center">
        <th>
            N°
        </th>
        <th>
            Tâche
        </th>
        <th>
            Etat
        </th>
        <th>
            Actions
        </th>
    </tr>

    <?php
    // On exécute une requête visant à récupérer les tâches
    $requete="SELECT * FROM tache ORDER BY valide,id DESC;";	
    $resultat= mysqli_query($db,$requete) OR DIE('\n ERREUR SQL :' .$requete.mysql_error());				
   
    while ($taches = mysqli_fetch_array($resultat)) { // On initialise une boucle
        ?>
        <tr>
            <td><?php echo $taches['id'] ?></td>
            <td><?php echo $taches['tache'] ?></td>
            <?php
                if($taches['valide']==0){
                    echo"<td bgcolor=\"#E50E45\">";
                }
                else{
                    echo"<td bgcolor=\"#0EE56B\">";
                }
            ?>
            
            </td>
            <td>
                <?php if ($taches['valide'] == 0) {?>
                <a class="suppr" href="index.php?valider_tache=<?php echo $taches['id'] ?>" title="Valider la tâche"> ✔️</a>
                <?php }?>
                <a class="suppr" href="index.php?supprimer_tache=<?php echo $taches['id'] ?>" title="Supprimer la tâche"> ❌</a>
            </td>
        </tr>
        <?php
    }
    ?>

</table>
 
