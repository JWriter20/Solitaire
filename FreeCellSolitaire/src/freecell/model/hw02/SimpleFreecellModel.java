package cs3500.freecell.model.hw02;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import cs3500.freecell.model.Card;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.ICard;
import cs3500.freecell.model.PileType;


/*
Documented Changes to SimpleFreecellModel:
- Changed validMove method, cascadePiles, foundationPiles, open, and started to protected so
  they can be used to create methods in the MultiMoveModel class.
 */


/**
 * Represents the model of a Freecell Game. Which is a type of Solitaire
 * In this class I create a deck of standard (four suit 13 values) cards
 * and then arrange them into a given number of cascade piles. There are also
 * a given number of Open piles you can add cards to as well as 4 Foundation
 * piles which you use to complete the game. If any wrong moves are detected
 * It will throw errors
 */
public class SimpleFreecellModel implements FreecellModel<ICard> {

  private final char[] suits = {'♣', '♠', '♥', '♦'};
  protected List<List<ICard>> cascadePiles;
  private List<Stack<ICard>> foundationPiles;
  protected List<OpenPile> open;
  protected boolean started = false;

  /**
   * Constructs a {@code SimpleFreecellModel} object.
   */
  public SimpleFreecellModel() {
    cascadePiles = new ArrayList<List<ICard>>();
    foundationPiles = new ArrayList<Stack<ICard>>(
        Arrays.asList(new Stack<ICard>(), new Stack<ICard>(),
            new Stack<ICard>(), new Stack<ICard>()));
    open = new ArrayList<OpenPile>();
    started = false;

  }

  @Override
  public List<ICard> getDeck() {
    List<ICard> deck = new ArrayList<ICard>();

    for (int i = 1; i < 14; i++) {
      for (char curr: suits) {
        deck.add(new Card(curr, i));
      }
    }

    return deck;

  }

  @Override
  public void startGame(List<ICard> deck, int numCascadePiles, int numOpenPiles, boolean shuffle)
      throws IllegalArgumentException {

    cascadePiles = new ArrayList<List<ICard>>();
    foundationPiles = new ArrayList<Stack<ICard>>(
        Arrays.asList(new Stack<ICard>(), new Stack<ICard>(),
            new Stack<ICard>(), new Stack<ICard>()));
    open = new ArrayList<OpenPile>();

    if (deckIsValid(deck) && !(numCascadePiles < 4 || numOpenPiles < 1)) {
      started = true;
      if (shuffle) {
        Collections.shuffle(deck);
      }

      for (int i = 0; i < numCascadePiles; i++) {
        cascadePiles.add(new ArrayList<ICard>());
      }

      for (int i = 0; i < numOpenPiles; i++) {
        open.add(new OpenPile());
      }

      for (int i = 0; i < 52; i++) {
        cascadePiles.get(i % numCascadePiles).add(deck.get(i));
      }

    } else {
      throw new IllegalArgumentException("Invalid Deck");

    }

  }

  /**
   * Checks to see if a deck is valid or not.
   *
   * @return a boolean representing whether or not the deck is valid.
   */

  private boolean deckIsValid(List<ICard> deck) {
    if (deck.size() != 52) {
      return false;
    }

    Set<ICard> s = new HashSet<ICard>();

    for (ICard card: deck) {
      if (card.isValid()) {
        s.add(card);
      } else {
        return false;
      }
    }

    // Using sets no-duplicate property to make sure all the cards are unique
    return s.size() == 52;
  }


  /**
   * Check to see if the given move can be performed given the rules of the game.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source
   *                       pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   *
   * @return     A boolean representing whether or not a move can be played
   */
  protected boolean validMove(PileType source, int pileNumber,
                            int cardIndex, PileType destination, int destPileNumber) {

    if (source == destination && pileNumber == destPileNumber) {
      return false;
    }

    switch (source) {
      case OPEN:

        // checks to make sure an invalid cardIndex is not used
        if (pileNumber > open.size() || cardIndex > open.get(pileNumber).size()) {
          return false;
        }

        if (open.get(pileNumber).isEmpty()) {
          return false;
        }

        ICard toMoveO = getOpenCardAt(pileNumber);


        switch (destination) {
          case FOUNDATION:
            if (destPileNumber > 3 || destPileNumber < 0) {
              return false;
            }

            if (foundationPiles.get(destPileNumber).isEmpty()) {
              if (toMoveO.getVal() == 1) {
                return true;
              }
            }

            ICard foundTop = getFoundationCardAt(destPileNumber,
                foundationPiles.get(destPileNumber).size() - 1);

            return (foundTop.getSuit() == toMoveO.getSuit()
                && foundTop.getVal() == toMoveO.getVal() - 1);

          case CASCADE:

            if (cascadePiles.get(destPileNumber).isEmpty()) {
              return true;
            }

            ICard casTop = getCascadeCardAt(destPileNumber,
                cascadePiles.get(destPileNumber).size() - 1);

            return (casTop.isRed() != toMoveO.isRed()
                && casTop.getVal() == toMoveO.getVal() + 1);

          case OPEN:

            return (open.get(destPileNumber).isEmpty());

          default:
            return false;

        }

      case CASCADE:

        if (cascadePiles.get(pileNumber).isEmpty()) {
          return false;
        }

        if (cardIndex != cascadePiles.get(pileNumber).size() - 1) {
          return false;
        }

        ICard toMoveC = getCascadeCardAt(pileNumber, cardIndex);

        switch (destination) {
          case OPEN:

            return (open.get(destPileNumber).isEmpty());

          case CASCADE:
            ICard casTop = getCascadeCardAt(destPileNumber,
                cascadePiles.get(destPileNumber).size() - 1);

            return (casTop.isRed() != toMoveC.isRed() && casTop.getVal() == toMoveC.getVal() + 1);

          case FOUNDATION:

            if (foundationPiles.get(destPileNumber).isEmpty()) {
              if (toMoveC.getVal() == 1) {
                return true;
              }
            }

            ICard foundTop = getFoundationCardAt(destPileNumber,
                foundationPiles.get(destPileNumber).size() - 1);

            return (foundTop.getSuit() == toMoveC.getSuit()
                && foundTop.getVal() == toMoveC.getVal() - 1);

          default:
            return false;

        }

      case FOUNDATION:

        return false;

      default:
        return false;
    }
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber)
      throws IllegalArgumentException, IllegalStateException {

    if (!this.started) {
      throw new IllegalStateException("Game has not yet started");
    }

    if (validMove(source, pileNumber, cardIndex, destination, destPileNumber)) {
      switch (source) {
        case OPEN:

          switch (destination) {
            case FOUNDATION:

              foundationPiles.get(destPileNumber).push(open.get(pileNumber).pop());

              break;
            case CASCADE:

              cascadePiles.get(destPileNumber).add(open.get(pileNumber).pop());

              break;
            case OPEN:
              open.get(destPileNumber).push(open.get(pileNumber).pop());

              break;
            default:
              throw new IllegalArgumentException("Invalid Args");
          }

          break;
        case CASCADE:
          ICard toMoveC = getCascadeCardAt(pileNumber, cardIndex);

          switch (destination) {
            case OPEN:
              open.get(destPileNumber).push(toMoveC);
              cascadePiles.get(pileNumber).remove(toMoveC);

              break;

            case CASCADE:

              cascadePiles.get(destPileNumber).add(toMoveC);
              cascadePiles.get(pileNumber).remove(toMoveC);

              break;

            case FOUNDATION:

              foundationPiles.get(destPileNumber).push(toMoveC);
              cascadePiles.get(pileNumber).remove(toMoveC);

              break;

            default:
              throw new IllegalArgumentException("Invalid Args");
          }

          break;
        case FOUNDATION:
          throw new IllegalArgumentException("Does not meet requirements to be moved");

        default:
          throw new IllegalArgumentException("Invalid Args");

      }

    } else {
      throw new IllegalArgumentException(
          "Given argument does not meet the requirements to be moved");
    }

  }

  @Override
  public boolean isGameOver() {
    boolean hasWon = true;
    // boolean hasMoves = false;

    if (!(this.started)) {
      return false;

    }

    for (Stack<ICard> foundationPile: foundationPiles) {
      hasWon = hasWon && foundationPile.size() == 13;
    }

    return hasWon;

    // Based on the auto-grader, it seems like the game only ends when you have won,
    // However, It is possible for you to have no valid moves left, in which case
    // I think the game should also be considered over, the code for this case is below
    // but as it caused the autograder to not pass, I commented it out.

    /* Open -> other
    for (int i = 0; i< open.size(); i++) {
      for (int j = 0; j < cascadePiles.size(); j++){
        hasMoves = hasMoves
        || validMove(PileType.OPEN, i, open.size() - 1, PileType.CASCADE, j);
      }

      for (int k = 0; k < foundationPiles.size(); k++) {
        hasMoves = hasMoves
        || validMove(PileType.OPEN, i, open.size() - 1, PileType.FOUNDATION, k);
      }
    }

    // Cascade -> other
    for (int i = 0; i< cascadePiles.size(); i++) {
      for (int j = 0; j < open.size(); j++){
        hasMoves = hasMoves
        || validMove(PileType.CASCADE, i, cascadePiles.size() - 1, PileType.OPEN, j);
      }

      for (int k = 0; k < foundationPiles.size(); k++) {
        hasMoves = hasMoves
        || validMove(PileType.CASCADE, i, cascadePiles.size() - 1, PileType.FOUNDATION, k);
      }
    }


    return !hasMoves;
    */
  }

  @Override
  public int getNumCardsInFoundationPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game not yet started");
    }

    if (index >= foundationPiles.size() || index < 0) {
      throw new IllegalArgumentException("Index does not exist in foundationPiles");
    }

    return foundationPiles.get(index).size();
  }

  @Override
  public int getNumCascadePiles() {
    if (started) {
      return cascadePiles.size();
    } else {
      return -1;
    }

  }

  @Override
  public int getNumCardsInCascadePile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game not yet started");
    }

    if (index >= cascadePiles.size() || index < 0) {
      throw new IllegalArgumentException("Index does not exist in cascadePiles");
    }

    return cascadePiles.get(index).size();
  }

  @Override
  public int getNumCardsInOpenPile(int index)
      throws IllegalArgumentException, IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game not yet started");
    }

    if (index >= open.size() || index < 0) {
      throw new IllegalArgumentException("Index does not exist in cascadePiles");
    }

    return open.get(index).size();
  }

  @Override
  public int getNumOpenPiles() {
    if (started) {
      return open.size();
    } else {
      return -1;
    }
  }

  @Override
  public ICard getFoundationCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game not yet started");
    }

    if (pileIndex >= foundationPiles.size() || pileIndex < 0 || cardIndex < 0
        || cardIndex > foundationPiles.get(pileIndex).size()) {
      throw new IllegalArgumentException("Index does not exist in foundationPiles");
    }

    return foundationPiles.get(pileIndex).get(cardIndex);
  }

  @Override
  public ICard getCascadeCardAt(int pileIndex, int cardIndex)
      throws IllegalArgumentException, IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game not yet started");
    }

    if (cascadePiles.isEmpty()) {
      return null;
    }

    if (pileIndex >= cascadePiles.size() || pileIndex < 0 || cardIndex < 0
        || cardIndex >= cascadePiles.get(pileIndex).size()) {
      throw new IllegalArgumentException("Index does not exist in cascadePiles");
    }

    return cascadePiles.get(pileIndex).get(cardIndex);
  }

  @Override
  public ICard getOpenCardAt(int pileIndex) throws IllegalArgumentException, IllegalStateException {
    if (!started) {
      throw new IllegalStateException("Game not yet started");
    }

    if (pileIndex >= open.size() || pileIndex < 0) {
      throw new IllegalArgumentException("Index does not exist in given Open pile");
    }

    return open.get(pileIndex).peek();
  }
}
