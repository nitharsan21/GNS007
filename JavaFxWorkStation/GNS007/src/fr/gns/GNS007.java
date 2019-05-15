/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gns;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
        private char genre;
        private boolean cgu;
        
        public Joueur(String nom,String mdp,char genre,boolean cgu){
            this.nom = nom;
            this.mdp = mdp;
            this.genre = genre;
            this.cgu = cgu;
        }
        
        @Override
        public String toString(){
            return "nom :" +this.nom +", mdp :"+this.mdp +", Genre :"+this.genre+", CGU :"+this.cgu ;
           
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
        //v1
        Label titre = new Label("Nouveau joueur :");
     
        
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
        
        
        //v4
        ToggleGroup genre = new ToggleGroup();
        RadioButton femme = new RadioButton("Femme");
        femme.setToggleGroup(genre);
        femme.setSelected(true);
        RadioButton homme = new RadioButton("Homme");
        homme.setToggleGroup(genre);
        
        HBox h3 = new HBox(1);
        h3.getChildren().addAll(femme,homme);
        
        //v5
        CheckBox cgu = new CheckBox("Acceptez-vous les C.G.U");
        
        HBox h4 = new HBox();
        h4.getChildren().add(cgu);
        
        //v6
        Button btn = new Button();
        btn.setText("Valider");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                boolean select;
                char gen;
                if(cgu.isSelected()){
                    select = true;
                }else{
                    select = false;
                }
                
                if(femme.isSelected()){
                    gen = 'f';
                }else{
                    gen = 'H';
                }
                Joueur joueur = new Joueur(nomfield.getText(),mdpfield.getText(),gen,select);
                System.out.println(joueur.toString());
                lesjoueurs.add(joueur);
                
                
                
    
            }
        });
        
        Button btnreset = new Button();
        btnreset.setText("Réinitialiser");
        btnreset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
               nomfield.clear();
               mdpfield.clear();
               cgu.setSelected(false);
               femme.setSelected(true);
               
            }
        });
        
        HBox h5 = new HBox();
        h5.getChildren().addAll(btnreset,btn);
        
        VBox root = new VBox(6);
        root.getChildren().addAll(titre,h1,h2,h3,h4,h5);
        
        Scene scene = new Scene(root, 300, 250);
        
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
