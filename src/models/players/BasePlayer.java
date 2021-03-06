package models.players;

import models.players.interfaces.Player;
import repositories.interfaces.CardRepository;

public abstract class BasePlayer implements Player {
    private String username;
    private int health;
    private CardRepository cardRepository;
    private boolean isDead;

    protected BasePlayer (CardRepository cardRepository, String username, int health){
        this.cardRepository = cardRepository;
        setUserName(username);
        setHealth(health);
        this.isDead = false;
    }

    private void setUserName(String username){
        if (username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("Player's username cannot be null or an empty string.");
        }
        this.username = username;
    }

    @Override
    public CardRepository getCardRepository() {
        return this.cardRepository;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int healthPoints) {
        if (healthPoints < 0){
            throw new IllegalArgumentException("Player's health bonus cannot be less than zero.");
        }
        this.health = healthPoints;
    }

    @Override
    public boolean isDead() {
        if (this.health <= 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void takeDamage(int damagePoints) {
        if (damagePoints < 0){
            throw new IllegalArgumentException("Damage points cannot be less than zero.");
        }
        if ((this.health -= damagePoints ) < 0){
            this.health = 0;
        } else {
            this.health -= damagePoints;
        }
    }
}
