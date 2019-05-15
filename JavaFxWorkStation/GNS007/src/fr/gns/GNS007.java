/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gns;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author etudiant
 */
public class GNS007 extends Application {
    
    List<Joueur> lesjoueurs = new ArrayList<Joueur>();
    
    public class Joueur{
        private String nom;
        private String mdp;
        private String genre;
        private boolean cgu;
        private String pays;
        
        public Joueur(String nom,String mdp,String genre,boolean cgu, String pays){
            this.nom = nom;
            this.mdp = mdp;
            this.genre = genre;
            this.cgu = cgu;
            this.pays= pays;
        }
        
        public Joueur(String nom,String genre,String pays,boolean cgu){
            this.nom = nom;
            this.pays = pays;
            this.genre = genre;
            this.cgu= cgu;
        }
        
        public String getNom(){
            return this.nom;
        }
        
        public String getGenre(){
            return this.genre;
        }
        public String getPays(){
            return this.pays;
        }
        
        public boolean getCgu(){
            return this.cgu;
        }
        
        
        @Override
        public String toString(){
            return "nom :" +this.nom +", mdp :"+this.mdp +", Genre :"+this.genre+",Pays :"+this.pays+", CGU :"+this.cgu ;
           
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
        //v1
        Label titre = new Label("Nouveau joueur :");
        TableView<Joueur> table = new TableView<Joueur>();
        
        //v2
        Label nom = new Label("Nom : ");
        
        TextField nomfield = new TextField("");
        nomfield.setMinWidth(50);
        
        HBox h1 = new HBox(2);
        h1.getChildren().addAll(nom,nomfield);
        
        //v3
        Label mdp = new Label("Mdp : ");
        
        PasswordField mdpfield = new PasswordField();
        mdpfield.setMinWidth(50);
      
        HBox h2 = new HBox(2);
        h2.getChildren().addAll(mdp,mdpfield);
        
        
        Label Cmdp = new Label("Confirmer Mdp : ");
        
        PasswordField Cmdpfield = new PasswordField();
        Cmdpfield.setMinWidth(50);
        
        HBox h21 = new HBox(2);
        h21.getChildren().addAll(Cmdp,Cmdpfield);
        
        //v4
        ToggleGroup genre = new ToggleGroup();
        RadioButton femme = new RadioButton("Femme");
        femme.setUserData('F');
        femme.setToggleGroup(genre);
        femme.setSelected(true);
        RadioButton homme = new RadioButton("Homme");
        homme.setUserData('H');
        homme.setToggleGroup(genre);
        
        
        HBox h3 = new HBox(1);
        h3.getChildren().addAll(femme,homme);
        
        //v5 pays
        Label paytite = new Label("Pays :");
        
        ChoiceBox pays = new ChoiceBox(FXCollections.observableArrayList(
        "France", "Sri Lanka", "Itale" ,"Espagne" ,"Etat Unis","Inde" ));
        pays.setTooltip(new Tooltip(" selectionner une pays"));
        
        HBox hpays = new HBox(2);
        hpays.getChildren().addAll(paytite,pays);
        
        
        
        //v6
        CheckBox cgu = new CheckBox("Acceptez-vous les C.G.U");
        
        HBox h4 = new HBox();
        h4.getChildren().add(cgu);
        
        //v6
        Button btn = new Button();
        btn.setText("Valider");
        
        btn.disableProperty().bind(nomfield.textProperty().isEmpty().or(mdpfield.textProperty().isEmpty()).or(pays.selectionModelProperty().isNull()));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Alert ok = new Alert(AlertType.INFORMATION);
                ok.setTitle(" Joueur Créé");
                ok.setHeaderText(" le Joueur créé");
                ok.setContentText("<<< le Joueur "+nomfield.getText()+" est bien créé!!! >>>");
                
                Alert erreurmdp = new Alert(AlertType.ERROR);
                erreurmdp.setTitle("Erreur de mdp");
                erreurmdp.setHeaderText("Joueur "+nomfield.getText()+" non créé");
                erreurmdp.setContentText("verifier votre mot de passe et la confirmation !!! ");
                
               
                

             
                    
                if(mdpfield.getText().length() > 1){

                    if(mdpfield.getText().equals(Cmdpfield.getText())){

                        boolean select = cgu.isSelected();
                        String gen = genre.getSelectedToggle().getUserData().toString();
                        String pay = (String) pays.getSelectionModel().getSelectedItem();

                        Joueur joueur = new Joueur(nomfield.getText(),mdpfield.getText(),gen,select,pay);
                        System.out.println(joueur.toString());
                        lesjoueurs.add(joueur);
                        Optional<ButtonType> resultat = ok.showAndWait();
                        
                        ObservableList<Joueur> list = FXCollections.observableArrayList(lesjoueurs);
                        table.setItems(list);
                        
                        System.out.println(list);
                        System.out.println(list.size());
                        
                        if(resultat.get() == ButtonType.OK){
                            nomfield.clear();
                            mdpfield.clear();
                            Cmdpfield.clear();
                            cgu.setSelected(false);
                            femme.setSelected(true);
                            pays.getSelectionModel().clearSelection();
                        }

                    }
                    else{
                        erreurmdp.showAndWait();

                    }
                }
                else{
                    Alert warning = new Alert(AlertType.WARNING);
                    warning.setTitle("Attention de mdp");
                    warning.setHeaderText("Probléme de mdp");
                    warning.setContentText("Vérifier!!! Votre mot de passe doit \ncontenir au moins 6 caractère ");
                    warning.showAndWait();

                }
               
                

                
                
            }
        });
        
        Button btnreset = new Button();
        btnreset.setText("Réinitialiser");
        btnreset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
               nomfield.clear();
               mdpfield.clear();
               Cmdpfield.clear();
               cgu.setSelected(false);
               femme.setSelected(true);
               pays.getSelectionModel().clearSelection();
               
            }
        });
        
        HBox h5 = new HBox();
        h5.getChildren().addAll(btnreset,btn);
        
        //formaulaire
        VBox up = new VBox(6);
        up.getChildren().addAll(titre,h1,h2,h21,h3,hpays,h4,h5);
        
        //cree table
        
        
        TableColumn<Joueur, String> nomJ = new TableColumn<Joueur, String>("Nom");
        nomJ.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        TableColumn<Joueur, String> gerneJ = new TableColumn<Joueur, String>("Genre");
        gerneJ.setCellValueFactory(new PropertyValueFactory<>("genre"));
        
        TableColumn<Joueur, String> paysJ = new TableColumn<Joueur, String>("Pays");
         paysJ.setCellValueFactory(new PropertyValueFactory<>("pays"));
        
        TableColumn<Joueur, Boolean> cguJ= new TableColumn<Joueur, Boolean>("CGU");
        cguJ.setCellValueFactory(new PropertyValueFactory<>("cgu"));
        

        nomJ.setSortType(TableColumn.SortType.DESCENDING);
        
        
        
        table.getColumns().addAll(nomJ,gerneJ, paysJ, cguJ);
 
  
        
        //table
        VBox down = new VBox();  
        down.getChildren().add(table);
        
        
        VBox main = new VBox(2);
        main.getChildren().addAll(up,down);
        
        Scene scene = new Scene(main, 325, 350);
        
        primaryStage.setTitle("Creation des Joueurs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
   
 
    /**
     * @param args the command line arguments
     */
    
    
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
