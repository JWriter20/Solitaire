import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.ICard;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import org.junit.Test;
import cs3500.freecell.model.Card;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/** Adds tests for the methods of {@link SimpleFreecellModel} class.
 */

public class SimpleFreecellModelTests {

  private FreecellView view;
  private FreecellModel<ICard> model;
  private List<ICard> deck;

  /** Standard Initialization with a 7 cascade pile, 4 open pile game.
   */
  private void initData() {
    this.model = new SimpleFreecellModel();
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 7, 4, false);
  }

  /** Moved around Initialization with a 7 cascade pile, 4 open pile game.
   */
  private void initDataTwo() {
    this.model = new SimpleFreecellModel();
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 7, 4, false);
    model.move(PileType.CASCADE, 3, 6, PileType.CASCADE, 2);
    model.move(PileType.CASCADE, 5, 6, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 5, 5, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 3, 4, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 3, 3, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 3, 2, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 3, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 2);
    model.move(PileType.OPEN, 1, 1, PileType.CASCADE, 3);
  }

  private void initSolved() {
    this.model = new SimpleFreecellModel();
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 52, 4, false);
    for (int i = 0; i < 52; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION,i % 4);
    }
  }

  @Test
  public void testGetDeck() {
    this.model = new SimpleFreecellModel();
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();

    // Make sure there is the correct number of cards
    assertEquals(this.deck.size(), 52);

    HashSet<ICard> s = new HashSet<ICard>();

    for (ICard card: deck) {
      if (card.isValid()) {
        s.add(card);
      }
    }
    // check to make sure there are no repeated cards
    assertEquals(this.deck.size(), s.size());

  }

  @Test
  public void testValidStartGames() {
    this.model = new SimpleFreecellModel();
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 7, 4, false);
    String output = view.toString();
    assertEquals(
        "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n"
            + "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n"
            + "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n"
            + "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n"
            + "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n"
            + "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n"
            + "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:", output);

    this.model = new SimpleFreecellModel();
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 4, 4, true);

    assertTrue(model.getNumCardsInCascadePile(0) == 13
            && model.getNumCardsInCascadePile(1) == 13
            && model.getNumCardsInCascadePile(2) == 13
            && model.getNumCardsInCascadePile(3) == 13);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDeck() {
    this.model = new SimpleFreecellModel();
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    this.deck.remove(0);
    model.startGame(deck, 8, 4, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNumCascadePiles() {
    this.model = new SimpleFreecellModel();
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, -3, 4, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNumCascadePiles2() {
    this.model = new SimpleFreecellModel();
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 3, 4, true);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNumOpenPiles() {
    this.model = new SimpleFreecellModel();
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 8, -1, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNumOpenPiles2() {
    this.model = new SimpleFreecellModel();
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 8, 0, true);
  }

  /* all valid move cases
    Cascade -> Cascade √
    Cascade -> Open √
    Cascade -> Foundation √
    Open -> Cascade √
    Open -> Open √
    Open -> Foundation √

     */
  @Test
  public void testValidMoves() {

    initData();
    model.move(PileType.CASCADE, 3, 6, PileType.CASCADE, 2);
    model.move(PileType.CASCADE, 5, 6, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 5, 5, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 3, 4, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 3, 3, PileType.OPEN, 0);
    model.move(PileType.OPEN, 0, 1, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 3, 2, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 3, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 3, 0, PileType.OPEN, 3);
    model.move(PileType.OPEN, 3, 0, PileType.FOUNDATION, 2);
    String output = view.toString();
    assertEquals(
        "F1:\n"
            + "F2:\n"
            + "F3: A♦\n"
            + "F4:\n"
            + "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n"
            + "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n"
            + "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦, Q♠\n"
            + "C4:\n"
            + "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n"
            + "C6: 2♠, 4♣, 5♦, 7♥\n"
            + "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣, Q♦, J♣, 10♥, 9♠, 8♦\n"
            + "O1: 5♠\n"
            + "O2: 7♣\n"
            + "O3: 3♥\n"
            + "O4:", output);


    initData();
    model.move(PileType.CASCADE, 3, 6, PileType.CASCADE, 2);
    model.move(PileType.CASCADE, 5, 6, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 5, 5, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 5, 4, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 3, 4, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 3, 3, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 3, 2, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 3, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 2);
    model.move(PileType.OPEN, 1, 1, PileType.CASCADE, 3);
    output = view.toString();

    assertEquals(
        "F1:\n"
            + "F2:\n"
            + "F3: A♦\n"
            + "F4:\n"
            + "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n"
            + "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n"
            + "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦, Q♠\n"
            + "C4: 5♠\n"
            + "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n"
            + "C6: 2♠, 4♣, 5♦, 7♥\n"
            + "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣, Q♦, J♣, 10♥, 9♠, 8♦\n"
            + "O1: 7♣\n"
            + "O2:\n"
            + "O3: 3♥\n"
            + "O4:", output);

  }

  /*
    Types of invalid Moves:
    Bad move to foundation:
      - Moving a card that is not an ace when empty, √
      - Moving one that does not fit the pattern
    Bad move to cascade:
      - Moving a card that doesn't fit with the pattern √
    Bad move to Open
      - Card already in open √
    - Bad move from Foundation, any move from foundation is bad √
    Bad move from Cascade:
    - Moving a card from not the end index √
    - Moving a card from index out of range √
    Bad move from Open:
    - moving from an empty √
    - Moving a card from index out of range √

    Moving anyType onto itself √

   */

  // Moving a card from index out of bounds √
  @Test(expected = IllegalArgumentException.class)
  public void FromCascadeOOB() {
    initData();
    System.out.println(view.toString());
    model.move(PileType.CASCADE, 3, 8, PileType.CASCADE, 2);
  }

  // Moving a card from not the end index √
  @Test(expected = IllegalArgumentException.class)
  public void FromCascadeNotAtEnd() {
    initData();
    System.out.println(view.toString());
    model.move(PileType.CASCADE, 5, 5, PileType.CASCADE, 4);
  }

  // Moving a card to its same place √
  @Test(expected = IllegalArgumentException.class)
  public void toItself() {
    initData();
    System.out.println(view.toString());
    model.move(PileType.CASCADE, 5, 6, PileType.CASCADE, 5);
  }

  // Moving a card from index out of bounds √
  @Test(expected = IllegalArgumentException.class)
  public void OpenOOB() {
    initData();
    System.out.println(view.toString());
    model.move(PileType.OPEN, 3, 8, PileType.CASCADE, 2);
  }

  // moving from an empty Open √
  @Test(expected = IllegalArgumentException.class)
  public void MovingFromEmpty() {
    initData();
    System.out.println(view.toString());
    model.move(PileType.OPEN, 2, 0, PileType.OPEN, 1);
  }

  // Moving a card that's suit doesn't fit with the pattern √
  @Test(expected = IllegalArgumentException.class)
  public void InvalidSuit() {
    initData();
    System.out.println(view.toString());
    model.move(PileType.CASCADE, 3, 6, PileType.CASCADE, 0);
  }

  // Moving a card that's value doesn't fit with the pattern √
  @Test(expected = IllegalArgumentException.class)
  public void InvalidValue() {
    initData();
    model.move(PileType.CASCADE, 3, 6, PileType.CASCADE, 4);
  }

  // Moving a card that is not an ace when empty, √
  @Test(expected = IllegalArgumentException.class)
  public void InvalidMoveToFoundation() {
    initData();
    model.move(PileType.CASCADE, 4, 6, PileType.FOUNDATION, 3);
  }

  // Moving a card into Foundation that does not fit the pattern
  @Test(expected = IllegalArgumentException.class)
  public void BadFoundationPattern() {
    initDataTwo();
    model.move(PileType.CASCADE, 4, 6, PileType.FOUNDATION, 2);
  }

  // Moving card when a Card is already in open
  @Test(expected = IllegalArgumentException.class)
  public void CardAlreadyInOpen() {
    initDataTwo();
    model.move(PileType.CASCADE, 4, 6, PileType.OPEN, 2);
  }


  // Bad move from Foundation, any move from foundation is bad
  @Test(expected = IllegalArgumentException.class)
  public void InvalidMoveFromFoundation() {
    initDataTwo();
    model.move(PileType.FOUNDATION, 2, 0, PileType.OPEN, 1);
  }


  @Test(expected = IllegalStateException.class)
  public void testInvalidMoveStart() {
    this.model = new SimpleFreecellModel();
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.move(PileType.CASCADE, 1, 6, PileType.OPEN, 1);

  }


  @Test
  public void testIsGameOver() {
    initData();
    assertFalse(model.isGameOver());
    initSolved();
    assertTrue(model.isGameOver());
  }


  @Test
  public void testGetNumCardsInFoundationPile() {
    initDataTwo();
    assertEquals(1, model.getNumCardsInFoundationPile(2));
  }

  @Test
  public void testGetNumCascadePiles() {
    initDataTwo();
    assertEquals(7, model.getNumCascadePiles());
  }

  @Test
  public void testGetNumCardsInCascadePile() {
    initDataTwo();
    assertEquals(8, model.getNumCardsInCascadePile(0));

  }

  @Test
  public void testGetNumCardsInOpenPile() {
    initDataTwo();
    assertEquals(1, model.getNumCardsInOpenPile(2));
  }

  @Test
  public void testGetNumOpenPiles() {
    initDataTwo();
    assertEquals(4, model.getNumOpenPiles());
  }

  @Test
  public void testGetFoundationCardAt() {
    initDataTwo();
    assertEquals(new Card('♦', 1), model.getFoundationCardAt(2, 0));
  }

  @Test
  public void testGetCascadeCardAt() {
    initDataTwo();
    System.out.println(view.toString());
    assertEquals(new Card('♠', 13), model.getCascadeCardAt(0, 7));
  }

  @Test
  public void testGetOpenCardAt() {
    initDataTwo();
    System.out.println(view.toString());
    assertEquals(new Card('♣', 7), model.getOpenCardAt(0));
  }

}
