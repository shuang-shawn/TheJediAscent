package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import com.almasb.fxgl.entity.component.Component;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class Deck extends Component {
    private ArrayList<Card> deck = new ArrayList<>();
    private ArrayList<Card> graveyard = new ArrayList<>();
    private ArrayList<Card> hand = new ArrayList<>();


    public Deck() {
        Card attackCard = new AttackCard(50);
        Card defenseCard = new DefenseCard(5);
        Card attackModifierCard = new AttackModifierCard(1);
        Card defenseModifierCard = new DefenseModifierCard(1);
        int randomNum;
        for (int i = 0; i < 10; i++) {
            randomNum = ThreadLocalRandom.current().nextInt(1, 11);
            if (randomNum < 7) {
                this.deck.add(attackCard);
            } else if (randomNum < 9) {
                this.deck.add(defenseCard);
            } else if (randomNum < 10) {
                this.deck.add(attackModifierCard);
            } else if (randomNum < 11) {
                this.deck.add(defenseModifierCard);
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
    public ArrayList<Card> getHand() { return hand; }
    public boolean checkEmptyHand() {
        if (hand.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
    public Card getCard(CardType type) {
        switch (type) {
            case ATTACK -> {
                for (Card card : hand) {
                    if (card instanceof AttackCard) {
                        return card;
                    }
                }
            }

            case DEFENSE -> {
                for (Card card : hand) {
                    if (card instanceof DefenseCard) {
                        return card;
                    }
                }
            }
            case ATTACKMODIFIER -> {
                for (Card card : hand) {
                    if (card instanceof AttackModifierCard) {
                        return card;
                    }
                }
            }
            case DEFENSEMODIFER -> {
                for (Card card : hand) {
                    if (card instanceof DefenseModifierCard) {
                        return card;
                    }
                }
            }
            default -> { }
        }
        return null;
    }
    public CardType checkType(Card card) {
        if (card instanceof AttackCard) {
            return CardType.ATTACK;
        } else if (card instanceof DefenseCard) {
            return CardType.DEFENSE;
        } else if (card instanceof AttackModifierCard) {
            return CardType.ATTACKMODIFIER;
        } else if (card instanceof DefenseModifierCard) {
            return CardType.DEFENSEMODIFER;
        }
        return null;
    }

    public boolean checkCard(final CardType type) {
        switch (type) {
            case ATTACK -> {
                for (Card card : hand) {
                    if (card instanceof AttackCard) {
                        return true;
                    }
                }
            }

            case DEFENSE -> {
                for (Card card : hand) {
                    if (card instanceof DefenseCard) {
                        return true;
                    }
                }
            }
            case ATTACKMODIFIER -> {
                for (Card card : hand) {
                    if (card instanceof AttackModifierCard) {
                        return true;
                    }
                }
            }
            case DEFENSEMODIFER -> {
                for (Card card : hand) {
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
                while (counter >= 0 && counter < hand.size()) {
                    if (hand.get(counter) instanceof AttackCard) {
                        graveyard.add(hand.get(counter));
                        hand.remove(counter);
                        counter = -2;
                    }
                    counter++;
                }
            }

            case DEFENSE -> {
                while (counter >= 0 && counter < hand.size()) {
                    if (hand.get(counter) instanceof DefenseCard) {
                        graveyard.add(hand.get(counter));
                        hand.remove(counter);
                        counter = -2;
                    }
                    counter++;
                }
            }
            case ATTACKMODIFIER -> {
                while (counter >= 0 && counter < hand.size()) {
                    if (hand.get(counter) instanceof AttackModifierCard) {
                        graveyard.add(hand.get(counter));
                        hand.remove(counter);
                        counter = -2;
                    }
                    counter++;
                }
            }
            case DEFENSEMODIFER -> {
                while (counter >= 0 && counter < hand.size()) {
                    if (hand.get(counter) instanceof DefenseModifierCard) {
                        graveyard.add(hand.get(counter));
                        hand.remove(counter);
                        counter = -2;
                    }
                    counter++;
                }
            }
        }
    }

    public void drawCard() {
        int deckSize = this.deck.size();
        int remainingCounter = 4;
        if (deckSize < 4) {
            this.hand.addAll(this.deck);
            remainingCounter -= deckSize;
            this.deck.clear();
            refreshDeck();
            Iterator<Card> cardIterator = this.deck.iterator();
            while (cardIterator.hasNext() && remainingCounter > 0) {
                Card card = cardIterator.next();
                this.hand.add(card);
                cardIterator.remove();
                remainingCounter--;
            }
        } else {
            Iterator<Card> i = this.deck.iterator();
            int removeCounter = 0;
            while (i.hasNext() && removeCounter < 4) {
                Card card = i.next();
                this.hand.add(card);
                i.remove();
                removeCounter++;
            }
        }

    }

    public void refreshDeck() {
        this.deck.addAll(this.graveyard);
        this.graveyard.clear();
    }

    public void addCard(Card card) {
        deck.add(card);
    }

}
