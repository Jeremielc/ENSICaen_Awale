/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensicaen.awale.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 *
 * @author Pierrick Hue & Jérémie Leclerc
 */
public class RulesLayoutController implements Initializable {

    public RulesLayoutController() {

    }

    @FXML
    private TextArea txtRules;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtRules.wrapTextProperty().set(true);
        txtRules.setStyle("-fx-font-alignment: justify");
    }

    public void handleRules(ActionEvent event) {
        txtRules.setText("Règle 1 : Seulement deux joueurs peuvent s'affronter. \n"
                + "\nRègle 2 : On répartit quarante-huit graines dans les douze trous à raison de quatre graines par trou.\n"
                + "\nRègle 3 : Chaque joueur joue à tour de rôle, celui qui joue en premier est tiré au hasard. Le joueur va prendre l'ensemble des graines dans l'un des trous de son territoire et les distribuer, un par trou, dans le sens inverse des aiguilles d'une montre.\n"
                + "\nRègle 4 : Si sa dernière graine tombe dans un trou du camp adverse et qu'il y a maintenant deux ou trois graines dans ce trou, le joueur récupère ces deux ou trois graines et les met de côté. Ensuite il regarde la case précédente : si elle est dans le camp adverse et contient deux ou trois graines, il récupère ces graines, et ainsi de suite jusqu'à ce qu'il arrive à son camp ou jusqu'à ce qu'il y ait un nombre de graines différent de deux ou trois.\n"
                + "\nRègle 5 : On ne saute pas de case lorsqu'on égrène sauf lorsqu'on a douze graines ou plus, c'est-à-dire qu'on fait un tour complet : on ne remplit pas la case dans laquelle on vient de prendre les graines.\n"
                + "\nRègle 6 : Il faut «nourrir » l'adversaire, c'est-à-dire que, quand celui-ci n'a plus de graines, il faut absolument jouer un coup qui lui permette de rejouer ensuite. Si ce n'est pas possible, la partie s'arrête et le joueur qui allait jouer capture les graines restantes.\n"
                + "\nRègle 7 : Si un coup devait prendre toutes les graines adverses, alors le coup peut être joué, mais aucune capture n'est faite : il ne faut pas « affamer » l'adversaire.\n"
                + "\nRègle 8 : La partie s'arrête quand un des joueurs a capturé au moins 25 graines, et est désigné gagnant, ou qu'il ne reste qu'au plus 6 graines en jeu.\n"
                + "\nRègle 9 Quand il ne reste qu'au plus 10 graines sur le plateau, le joueur qui a la main peut proposer l'abandon de la partie. Si il est accepté les deux joueurs se partagent les graines restantes. Si le total des graines du plateau est inférieur à 6, sans qu'aucun des joueurs n'a un total de graines supérieur à 24. La partie est nulle.\n");
    }
}
