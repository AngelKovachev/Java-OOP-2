package core;

import common.ConstantMessages;
import core.interfaces.ManagerController;
import models.battleFields.BattleFieldImpl;
import models.battleFields.interfaces.Battlefield;
import models.cards.MagicCard;
import models.cards.TrapCard;
import models.cards.interfaces.Card;
import models.players.Advanced;
import models.players.Beginner;
import models.players.interfaces.Player;
import repositories.CardRepositoryImpl;
import repositories.PlayerRepositoryImpl;
import repositories.interfaces.CardRepository;
import repositories.interfaces.PlayerRepository;

import java.util.ArrayList;
import java.util.List;


public class ManagerControllerImpl implements ManagerController {
    private Player attackPlayer;
    private Player enemyPlayer;
    private Player player;
    private PlayerRepository playerRepository;
    private CardRepository cardRepository;
    private Card card;
    private Battlefield battleField;
   // private BattleField battleField;


    public ManagerControllerImpl(PlayerRepository playerRepository, CardRepository cardRepository) {
       //TODO:IMPLEMENT ME
        this.playerRepository = playerRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public String addPlayer(String type, String username) {
        if (type.equals("Beginner")){
            this.player = new Beginner(new CardRepositoryImpl(), username);
        } else {
            this.player = new Advanced(new CardRepositoryImpl(), username);
        }
        this.playerRepository.add(this.player);
        String massage = String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER, type, username);
       return massage;
    }

    @Override
    public String addCard(String type, String name) {
        if (type.equals("Magic")){
            this.card = new MagicCard(name);
        } else {
            this.card = new TrapCard(name);
        }
        this.cardRepository.add(this.card);
        return  String.format(ConstantMessages.SUCCESSFULLY_ADDED_CARD, type, name);
    }

    @Override
    public String addPlayerCard(String username, String cardName) {
        /*for (int i = 0; i < this.playerRepository.getCount(); i++) {
            if (this.playerRepository.find(username).getUsername().equals(username)){
                for (int j = 0; j < this.cardRepository.getCount(); j++) {
                    if (this.cardRepository.find(cardName).getName().equals(cardName)){
                        this.playerRepository.find(username).getCardRepository().add(this.cardRepository.find(cardName));

                    }
                }
            }
        }*/

        this.playerRepository.find(username).getCardRepository().add(this.cardRepository.find(cardName));
        return  String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_WITH_CARDS, cardName, username);
    }

    @Override
    public String fight(String attackUser, String enemyUser) {
        Player attacer = this.playerRepository.find(attackUser);
        Player enemy = this.playerRepository.find(enemyUser);

        this.battleField = new BattleFieldImpl(attacer, enemy);
        this.battleField.fight(attacer, enemy);
        return  "Attack user health " + attacer.getHealth() + " - Enemy user health " + enemy.getHealth();
    }

    @Override
    public String report() {
        StringBuilder str = new StringBuilder();
        List<Player> players = this.playerRepository.getPlayers();
        for (int i = 0; i < players.size(); i++) {

            str.append(String.format("Username: %s - Health: %d - Cards %d%n",
                    players.get(i).getUsername(), players.get(i).getHealth(), players.get(i).getCardRepository().getCount()));
            List<Card> cards = players.get(i).getCardRepository().getCards();
            if (cards.size() > 0) {
                for (Card card : cards) {
                    str.append("Card: ");
                    str.append(card.getName());
                    str.append(" - Damage: ");
                    str.append(card.getDamagePoints());
                    str.append(System.lineSeparator());
                }
            }
            //str.append(System.lineSeparator());
            str.append(ConstantMessages.DEFAULT_REPORT_SEPARATOR);
            str.append(System.lineSeparator());

        }
        return  str.toString();
    }
}
