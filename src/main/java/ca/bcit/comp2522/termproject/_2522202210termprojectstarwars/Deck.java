package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;

import java.util.ArrayList;

public class Deck extends Component {
    private ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        Card attackCard = new AttackCard(50);
        Card defenseCard = new DefenseCard(5);
        Card attackModifierCard = new AttackModifierCard(1);
        Card defenseModifierCard = new DefenseModifierCard(1);
        this.deck.add(attackCard);
        this.deck.add(defenseCard);
        this.deck.add(attackModifierCard);
        this.deck.add(defenseModifierCard);
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public Card getCard(CardType type) {
        switch (type) {
            case ATTACK -> {
                for (Card card : deck) {
                    if (card instanceof AttackCard) {
                        return card;
                    }
                }
            }

            case DEFENSE -> {
                for (Card card : deck) {
                    if (card instanceof DefenseCard) {
                        return card;
                    }
                }
            }
            case ATTACKMODIFIER -> {
                for (Card card : deck) {
                    if (card instanceof AttackModifierCard) {
                        return card;
                    }
                }
            }
            case DEFENSEMODIFER -> {
                for (Card card : deck) {
                    if (card instanceof DefenseModifierCard) {
                        return card;
                    }
                }
            }
            default -> { }
        }
        return null;
    }

    public boolean checkCard(final CardType type) {
        switch (type) {
            case ATTACK -> {
                for (Card card : deck) {
                    if (card instanceof AttackCard) {
                        return true;
                    }
                }
            }

            case DEFENSE -> {
                for (Card card : deck) {
                    if (card instanceof DefenseCard) {
                        return true;
                    }
                }
            }
            case ATTACKMODIFIER -> {
                for (Card card : deck) {
                    if (card instanceof AttackModifierCard) {
                        return true;
                    }
                }
            }
            case DEFENSEMODIFER -> {
                for (Card card : deck) {
                    if (card instanceof DefenseModifierCard) {
                        return true;
                    }
                }
            }
            default -> {
                return false;
            }
        }
        return false;
    }

    public void usedCard(final CardType type) {
        int counter = 0;
        switch (type) {
            case ATTACK -> {
                while (counter >= 0 && counter < deck.size()) {
                    if (deck.get(counter) instanceof AttackCard) {
                        deck.remove(counter);
                        counter = -2;
                    }
                    counter++;
                }
            }

            case DEFENSE -> {
                while (counter >= 0 && counter < deck.size()) {
                    if (deck.get(counter) instanceof DefenseCard) {
                        deck.remove(counter);
                        counter = -2;
                    }
                    counter++;
                }
            }
            case ATTACKMODIFIER -> {
                while (counter >= 0 && counter < deck.size()) {
                    if (deck.get(counter) instanceof AttackModifierCard) {
                        deck.remove(counter);
                        counter = -2;
                    }
                    counter++;
                }
            }
            case DEFENSEMODIFER -> {
                while (counter >= 0 && counter < deck.size()) {
                    if (deck.get(counter) instanceof DefenseModifierCard) {
                        deck.remove(counter);
                        counter = -2;
                    }
                    counter++;
                }
            }
        }
    }
    public void refreshDeck() {
        Card attackCard = new AttackCard(10);
        Card defenseCard = new DefenseCard(5);
        Card attackModifierCard = new AttackModifierCard(1);
        Card defenseModifierCard = new DefenseModifierCard(1);
        this.deck.add(attackCard);
        this.deck.add(defenseCard);
        this.deck.add(attackModifierCard);
        this.deck.add(defenseModifierCard);
    }

    public void addCard(Card card) {
        deck.add(card);
    }

}
