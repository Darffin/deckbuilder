package com.darffin.controller;

import com.darffin.model.Card;
import com.darffin.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerController {

    @Autowired
    private CardController cardController;

    public PlayerController(){
        //defaultDeck();
    }

}
