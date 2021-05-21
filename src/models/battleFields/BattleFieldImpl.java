package models.battleFields;

import models.battleFields.interfaces.Battlefield;
import models.players.Beginner;
import models.players.interfaces.Player;

public class BattleFieldImpl implements Battlefield {
    private Player attackPlayer;
    private Player enemyPlayer;

    public BattleFieldImpl(Player attackPlayer, Player enemyPlayer){
        this.attackPlayer = attackPlayer;
        this.enemyPlayer = enemyPlayer;
    }

    @Override
    public void fight(Player attackPlayer, Player enemyPlayer) {
        //TODO
        if (attackPlayer.isDead() || enemyPlayer.isDead()){
            throw new IllegalArgumentException("Player is dead!");
        }
        if (attackPlayer instanceof Beginner){
            attackPlayer.setHealth(attackPlayer.getHealth() + 40);
            attackPlayer.getCardRepository().getCards().stream()
                    .forEach(c -> c.setDamagePoints(c.getDamagePoints() + 30));
        }
        if (enemyPlayer instanceof Beginner){
            enemyPlayer.setHealth(enemyPlayer.getHealth() + 40);
            enemyPlayer.getCardRepository().getCards().stream()
                    .forEach(c -> c.setDamagePoints(c.getDamagePoints() + 30));
        }
        int sumAttacer = attackPlayer.getCardRepository().getCards().stream().mapToInt(c -> c.getHealthPoints()).sum();
        attackPlayer.setHealth(attackPlayer.getHealth() + sumAttacer);

        int sumEnemy = enemyPlayer.getCardRepository().getCards().stream().mapToInt(c -> c.getHealthPoints()).sum();
        enemyPlayer.setHealth(enemyPlayer.getHealth() + sumEnemy);

        int num = 0;
        while (!attackPlayer.isDead() && !enemyPlayer.isDead()){
            int sumAttacerDamage = attackPlayer.getCardRepository()
                    .getCards().stream().mapToInt(c -> c.getDamagePoints()).sum();
            int sumEnemyDamage = enemyPlayer.getCardRepository()
                    .getCards().stream().mapToInt(c -> c.getDamagePoints()).sum();
            if (num % 2 == 0){

                enemyPlayer.takeDamage(sumAttacerDamage);

            } else {

                attackPlayer.takeDamage(sumEnemyDamage);

            }

            num++;
        }

        /*if (attackPlayer instanceof Beginner){
            attackPlayer.setHealth(attackPlayer.getHealth() + 40);
            attackPlayer.getCardRepository().getCards().stream()
                    .forEach(c -> c.setDamagePoints(c.getDamagePoints() + 30));
        }
        if (enemyPlayer instanceof Beginner){
            enemyPlayer.setHealth(enemyPlayer.getHealth() + 40);
            enemyPlayer.getCardRepository().getCards().stream()
                    .forEach(c -> c.setDamagePoints(c.getDamagePoints() + 30));
        }
        int sumAttacer = attackPlayer.getCardRepository().getCards().stream().mapToInt(c -> c.getHealthPoints()).sum();
        attackPlayer.setHealth(attackPlayer.getHealth() + sumAttacer);

        int sumEnemy = enemyPlayer.getCardRepository().getCards().stream().mapToInt(c -> c.getHealthPoints()).sum();
        enemyPlayer.setHealth(enemyPlayer.getHealth() + sumEnemy);

        int num = 0;



        while (!attackPlayer.isDead() && !enemyPlayer.isDead()){

            if (enemyPlayer.isDead() || attackPlayer.isDead()){
                throw new IllegalArgumentException("Player is dead!");
            }

            int sumAttacerDamage = attackPlayer.getCardRepository()
                    .getCards().stream().mapToInt(c -> c.getDamagePoints()).sum();
            int sumEnemyDamage = enemyPlayer.getCardRepository()
                    .getCards().stream().mapToInt(c -> c.getDamagePoints()).sum();
            if (num % 2 == 0){

                enemyPlayer.takeDamage(sumAttacerDamage);

            } else {

                attackPlayer.takeDamage(sumEnemyDamage);

            }
            num++;

        }*/

    }
}
