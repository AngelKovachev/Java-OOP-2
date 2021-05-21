package repositories;

import models.cards.interfaces.Card;
import repositories.interfaces.CardRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardRepositoryImpl implements CardRepository {
    private List<Card> cards;

    public CardRepositoryImpl(){
        this.cards = new ArrayList<>();
    }
    @Override
    public int getCount() {
        return this.cards.size();
    }

    @Override
    public List<Card> getCards() {
        return this.cards;
    }

    @Override
    public void add(Card card) {
        if (card == null){
            throw new IllegalArgumentException("Card cannot be null!");
        }
        for (int i = 0; i < this.cards.size(); i++) {
            if (this.cards.get(i).getName().equals(card.getName())){
                throw new IllegalArgumentException(String.format("Card %s already exists!", card.getName()));
            }
        }
        this.cards.add(card);
    }

    @Override
    public boolean remove(Card card) {
        if (card == null){
            throw new IllegalArgumentException("Card cannot be null!");
        }
        return this.cards.remove(card);
    }

    @Override
    public Card find(String name) {
        return this.cards.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }
}
