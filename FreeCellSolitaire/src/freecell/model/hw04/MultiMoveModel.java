package cs3500.freecell.model.hw04;

import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.OpenPile;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.ICard;

import java.util.List;

/**
 * A model that allows for moving multiple cards at the same time, apart from that,
 * it has all of the same features as a {@code SimpleFreecellModel}.
 */
public class MultiMoveModel extends SimpleFreecellModel {

  /**
   * Initializes MultiMoveModel the same way as SimpleFreecellModel using super().
   */
  public MultiMoveModel() {
    super();
  }

  /**
   * Checks if a build is valid before moving it.
   *
   * @param cascadePile the cascade pile #.
   * @param cardIndex the card index to be moved.
   * @param destPile the destination pile.
   * @return a boolean representing whether or not the card can be moved.
   */
  private boolean isValidBuildMove(int cascadePile, int cardIndex, int destPile) {
    try {
      ICard prevCard = getCascadeCardAt(destPile, cascadePiles.get(destPile).size() - 1);
      ICard currCard;
      for (int i = cardIndex; i < super.cascadePiles.get(cascadePile).size(); i++) {
        currCard = getCascadeCardAt(cascadePile, i);
        if (!(prevCard.isRed() != currCard.isRed()
            && currCard.getVal() == prevCard.getVal() - 1)) {
          return false;
        }

        prevCard = currCard;
      }

      int numFreeCascade = 0;
      int numFreeOpen = 0;

      for (List<ICard> curr: super.cascadePiles) {
        if (curr.isEmpty()) {
          numFreeCascade++;
        }
      }

      for (OpenPile curr: super.open) {
        if (curr.isEmpty()) {
          numFreeOpen++;
        }
      }

      int numToMove = super.cascadePiles.get(cascadePile).size() - cardIndex;
      return numToMove <= (numFreeOpen + 1) * Math.pow(2, numFreeCascade);
    }

    catch (IllegalArgumentException e) {
      return false;
    }
    catch (IllegalStateException e) {
      return false;
    }
  }

  /**
   * Moves the build based on the given arguments.
   *
   * @param cascadePile the cascade pile #.
   * @param cardIndex the card index to be moved.
   * @param destPile the destination pile.
   * @throws IllegalArgumentException if the move is invalid
   * @throws IllegalStateException if the game has not yet started
   */
  private void moveBuild(int cascadePile, int cardIndex, int destPile)
      throws IllegalArgumentException, IllegalStateException {

    if (!super.started) {
      throw new IllegalStateException("Game has not yet started");
    }

    if (isValidBuildMove(cascadePile, cardIndex, destPile)) {
      int size = super.cascadePiles.get(cascadePile).size();
      for (int i = cardIndex; i < size; i++) {
        ICard toMove = getCascadeCardAt(cascadePile, cardIndex);
        super.cascadePiles.get(destPile).add(toMove);
        super.cascadePiles.get(cascadePile).remove(toMove);
      }
    } else {
      throw new IllegalArgumentException("Bad Input");
    }
  }

  /**
   * Checks if a move is valid, overridden to allow for indexes that are not at the
   * top of a cascade pile.
   *
   */
  @Override
  protected boolean validMove(PileType source, int pileNumber,
                              int cardIndex, PileType destination, int destPileNumber) {
    if (source == PileType.CASCADE && destination == PileType.CASCADE) {
      if (cascadePiles.get(pileNumber).isEmpty()) {
        return false;
      }

      ICard toMoveC = getCascadeCardAt(pileNumber, cardIndex);

      ICard casTop = getCascadeCardAt(destPileNumber,
          cascadePiles.get(destPileNumber).size() - 1);
      return (casTop.isRed() != toMoveC.isRed() && casTop.getVal() == toMoveC.getVal() + 1);

    }

    else {
      return super.validMove(source, pileNumber, cardIndex, destination, destPileNumber);

    }
  }

  /**
   * Overrides move to allow for multi-moves.
   *
   * @param source         the type of the source pile see @link{PileType}
   * @param pileNumber     the pile number of the given type, starting at 0
   * @param cardIndex      the index of the card to be moved from the source
   *                       pile, starting at 0
   * @param destination    the type of the destination pile (see
   * @param destPileNumber the pile number of the given type, starting at 0
   *
   */
  public void move(PileType source, int pileNumber, int cardIndex,
                   PileType destination, int destPileNumber)
      throws IllegalArgumentException, IllegalStateException {

    if (!started) {
      throw new IllegalStateException();
    }

    if (source == PileType.CASCADE && destination == PileType.CASCADE
        && cardIndex < cascadePiles.get(pileNumber).size() - 1) {
      moveBuild(pileNumber, cardIndex, destPileNumber);
    } else {
      super.move(source, pileNumber, cardIndex, destination, destPileNumber);
    }
  }
}
