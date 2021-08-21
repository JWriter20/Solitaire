import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.ICard;
import cs3500.freecell.model.Card;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultiMoveModel;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import org.junit.Test;
import cs3500.freecell.model.FreecellModelCreator.GameType;
import cs3500.freecell.model.FreecellModelCreator;

import java.io.StringReader;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Adds tests for the methods of {@link MultiMoveModel} class.
 */

public class TestMultiMoveModel {

  private FreecellView view;
  private FreecellModel<ICard> model;
  private List<ICard> deck;

  private void initData() {
    this.model = FreecellModelCreator.create(GameType.MULTIMOVE);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 16, 4, false);
  }

  private void initData(int numCascade, boolean isShuffle, GameType type) {
    this.model = FreecellModelCreator.create(type);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, numCascade, 4, isShuffle);
  }

  @Test
  public void TestMultiMoveAndSingleMove() {
    initData();
    model.move(PileType.CASCADE, 11, 2, PileType.CASCADE, 12);
    model.move(PileType.CASCADE, 12, 2, PileType.CASCADE, 3);

    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: A♣, 5♣, 9♣, K♣\n"
        + "C2: A♠, 5♠, 9♠, K♠\n"
        + "C3: A♥, 5♥, 9♥, K♥\n"
        + "C4: A♦, 5♦, 9♦, K♦, Q♣, J♦\n"
        + "C5: 2♣, 6♣, 10♣\n"
        + "C6: 2♠, 6♠, 10♠\n"
        + "C7: 2♥, 6♥, 10♥\n"
        + "C8: 2♦, 6♦, 10♦\n"
        + "C9: 3♣, 7♣, J♣\n"
        + "C10: 3♠, 7♠, J♠\n"
        + "C11: 3♥, 7♥, J♥\n"
        + "C12: 3♦, 7♦\n"
        + "C13: 4♣, 8♣\n"
        + "C14: 4♠, 8♠, Q♠\n"
        + "C15: 4♥, 8♥, Q♥\n"
        + "C16: 4♦, 8♦, Q♦", view.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void MultiMoveToOpen() {
    initData();
    model.move(PileType.CASCADE, 11, 2, PileType.CASCADE, 12);
    model.move(PileType.CASCADE, 12, 2, PileType.OPEN, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void MultiMoveToFoundation() {
    initData(52, false, GameType.MULTIMOVE);
    model.move(PileType.CASCADE, 2, 0, PileType.CASCADE, 4);
    model.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadDeck() {
    this.model = FreecellModelCreator.create(GameType.MULTIMOVE);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    deck.remove(0);
    deck.add(new Card('♣', 3));
    model.startGame(deck, 16, 4, false);
    model.move(PileType.CASCADE, 2, 0, PileType.CASCADE, 4);
    model.move(PileType.CASCADE, 4, 0, PileType.FOUNDATION, 0);
  }

  @Test
  public void MoveStackOfEverySize() {
    initData(26, false, GameType.MULTIMOVE);
    model.move(PileType.CASCADE, 1, 1, PileType.CASCADE, 2);
    model.move(PileType.CASCADE, 2, 1, PileType.CASCADE, 9);
    model.move(PileType.CASCADE, 9, 1, PileType.CASCADE, 11);
    model.move(PileType.CASCADE, 11, 1, PileType.CASCADE, 17);
    model.move(PileType.CASCADE, 17, 1, PileType.CASCADE, 18);

    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "C1: A♣, 7♥\n" +
        "C2: A♠\n" +
        "C3: A♥\n" +
        "C4: A♦, 8♠\n" +
        "C5: 2♣, 8♥\n" +
        "C6: 2♠, 8♦\n" +
        "C7: 2♥, 9♣\n" +
        "C8: 2♦, 9♠\n" +
        "C9: 3♣, 9♥\n" +
        "C10: 3♠\n" +
        "C11: 3♥, 10♣\n" +
        "C12: 3♦\n" +
        "C13: 4♣, 10♥\n" +
        "C14: 4♠, 10♦\n" +
        "C15: 4♥, J♣\n" +
        "C16: 4♦, J♠\n" +
        "C17: 5♣, J♥\n" +
        "C18: 5♠\n" +
        "C19: 5♥, Q♣, J♦, 10♠, 9♦, 8♣, 7♦\n" +
        "C20: 5♦, Q♠\n" +
        "C21: 6♣, Q♥\n" +
        "C22: 6♠, Q♦\n" +
        "C23: 6♥, K♣\n" +
        "C24: 6♦, K♠\n" +
        "C25: 7♣, K♥\n" +
        "C26: 7♠, K♦", view.toString());

  }

  @Test(expected = IllegalArgumentException.class)
  public void CheckMoveWhenNoOpen() {
    initData(26, false, GameType.MULTIMOVE);
    model.move(PileType.CASCADE, 10, 1, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 20, 1, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 15, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 7, 1, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 1, 1, PileType.CASCADE, 2);
    model.move(PileType.CASCADE, 2, 1, PileType.CASCADE, 9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPile() {
    initData(26, false, GameType.MULTIMOVE);
    model.move(PileType.CASCADE, 2, 1, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 4, 0, PileType.CASCADE, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void CheckTooBigPile() {
    initData(26, false, GameType.MULTIMOVE);
    model.move(PileType.CASCADE, 1, 1, PileType.CASCADE, 2);
    model.move(PileType.CASCADE, 2, 1, PileType.CASCADE, 9);
    model.move(PileType.CASCADE, 9, 1, PileType.CASCADE, 11);
    model.move(PileType.CASCADE, 11, 1, PileType.CASCADE, 17);
    model.move(PileType.CASCADE, 17, 1, PileType.CASCADE, 18);
    model.move(PileType.CASCADE, 18, 1, PileType.CASCADE, 25);
  }

  @Test(expected = IllegalArgumentException.class)
  public void CheckNullCreate() {
    this.model = FreecellModelCreator.create(null);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 26, 4, false);
    model.move(PileType.CASCADE, 1, 1, PileType.CASCADE, 2);
    model.move(PileType.CASCADE, 2, 1, PileType.CASCADE, 9);
  }

  @Test
  public void CheckSinglecellCreate() {
    this.model = FreecellModelCreator.create(GameType.SINGLEMOVE);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 26, 4, false);
    model.move(PileType.CASCADE, 1, 1, PileType.CASCADE, 2);
    assertTrue(model instanceof SimpleFreecellModel);
    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: A♣, 7♥\n"
        + "C2: A♠\n"
        + "C3: A♥, 8♣, 7♦\n"
        + "C4: A♦, 8♠\n"
        + "C5: 2♣, 8♥\n"
        + "C6: 2♠, 8♦\n"
        + "C7: 2♥, 9♣\n"
        + "C8: 2♦, 9♠\n"
        + "C9: 3♣, 9♥\n"
        + "C10: 3♠, 9♦\n"
        + "C11: 3♥, 10♣\n"
        + "C12: 3♦, 10♠\n"
        + "C13: 4♣, 10♥\n"
        + "C14: 4♠, 10♦\n"
        + "C15: 4♥, J♣\n"
        + "C16: 4♦, J♠\n"
        + "C17: 5♣, J♥\n"
        + "C18: 5♠, J♦\n"
        + "C19: 5♥, Q♣\n"
        + "C20: 5♦, Q♠\n"
        + "C21: 6♣, Q♥\n"
        + "C22: 6♠, Q♦\n"
        + "C23: 6♥, K♣\n"
        + "C24: 6♦, K♠\n"
        + "C25: 7♣, K♥\n"
        + "C26: 7♠, K♦", view.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void TryMultiWithSingle() {
    this.model = FreecellModelCreator.create(GameType.SINGLEMOVE);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 26, 4, false);
    model.move(PileType.CASCADE, 1, 1, PileType.CASCADE, 2);
    model.move(PileType.CASCADE, 2, 1, PileType.CASCADE, 9);
  }

  @Test
  public void TestConrollerWithNewModel() {
    model = FreecellModelCreator.create(GameType.MULTIMOVE);
    Appendable ap = new StringBuffer();
    Readable rd = new StringReader("C2 2 C3 C3 2 C10 q");
    FreecellController<ICard> controller = new SimpleFreecellController(model, rd, ap);
    controller.playGame(model.getDeck(), 26, 4, false);
    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: A♣, 7♥\n"
        + "C2: A♠, 7♦\n"
        + "C3: A♥, 8♣\n"
        + "C4: A♦, 8♠\n"
        + "C5: 2♣, 8♥\n"
        + "C6: 2♠, 8♦\n"
        + "C7: 2♥, 9♣\n"
        + "C8: 2♦, 9♠\n"
        + "C9: 3♣, 9♥\n"
        + "C10: 3♠, 9♦\n"
        + "C11: 3♥, 10♣\n"
        + "C12: 3♦, 10♠\n"
        + "C13: 4♣, 10♥\n"
        + "C14: 4♠, 10♦\n"
        + "C15: 4♥, J♣\n"
        + "C16: 4♦, J♠\n"
        + "C17: 5♣, J♥\n"
        + "C18: 5♠, J♦\n"
        + "C19: 5♥, Q♣\n"
        + "C20: 5♦, Q♠\n"
        + "C21: 6♣, Q♥\n"
        + "C22: 6♠, Q♦\n"
        + "C23: 6♥, K♣\n"
        + "C24: 6♦, K♠\n"
        + "C25: 7♣, K♥\n"
        + "C26: 7♠, K♦\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: A♣, 7♥\n"
        + "C2: A♠\n"
        + "C3: A♥, 8♣, 7♦\n"
        + "C4: A♦, 8♠\n"
        + "C5: 2♣, 8♥\n"
        + "C6: 2♠, 8♦\n"
        + "C7: 2♥, 9♣\n"
        + "C8: 2♦, 9♠\n"
        + "C9: 3♣, 9♥\n"
        + "C10: 3♠, 9♦\n"
        + "C11: 3♥, 10♣\n"
        + "C12: 3♦, 10♠\n"
        + "C13: 4♣, 10♥\n"
        + "C14: 4♠, 10♦\n"
        + "C15: 4♥, J♣\n"
        + "C16: 4♦, J♠\n"
        + "C17: 5♣, J♥\n"
        + "C18: 5♠, J♦\n"
        + "C19: 5♥, Q♣\n"
        + "C20: 5♦, Q♠\n"
        + "C21: 6♣, Q♥\n"
        + "C22: 6♠, Q♦\n"
        + "C23: 6♥, K♣\n"
        + "C24: 6♦, K♠\n"
        + "C25: 7♣, K♥\n"
        + "C26: 7♠, K♦\n"
        + "F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: A♣, 7♥\n"
        + "C2: A♠\n"
        + "C3: A♥\n"
        + "C4: A♦, 8♠\n"
        + "C5: 2♣, 8♥\n"
        + "C6: 2♠, 8♦\n"
        + "C7: 2♥, 9♣\n"
        + "C8: 2♦, 9♠\n"
        + "C9: 3♣, 9♥\n"
        + "C10: 3♠, 9♦, 8♣, 7♦\n"
        + "C11: 3♥, 10♣\n"
        + "C12: 3♦, 10♠\n"
        + "C13: 4♣, 10♥\n"
        + "C14: 4♠, 10♦\n"
        + "C15: 4♥, J♣\n"
        + "C16: 4♦, J♠\n"
        + "C17: 5♣, J♥\n"
        + "C18: 5♠, J♦\n"
        + "C19: 5♥, Q♣\n"
        + "C20: 5♦, Q♠\n"
        + "C21: 6♣, Q♥\n"
        + "C22: 6♠, Q♦\n"
        + "C23: 6♥, K♣\n"
        + "C24: 6♦, K♠\n"
        + "C25: 7♣, K♥\n"
        + "C26: 7♠, K♦\n"
        + "Game quit prematurely.", ap.toString());
  }

  @Test
  public void CheckMultiMoveCreate() {
    this.model = FreecellModelCreator.create(GameType.MULTIMOVE);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 26, 4, false);
    model.move(PileType.CASCADE, 1, 1, PileType.CASCADE, 2);
    model.move(PileType.CASCADE, 2, 1, PileType.CASCADE, 9);
    assertTrue(model instanceof MultiMoveModel);
    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: A♣, 7♥\n"
        + "C2: A♠\n"
        + "C3: A♥\n"
        + "C4: A♦, 8♠\n"
        + "C5: 2♣, 8♥\n"
        + "C6: 2♠, 8♦\n"
        + "C7: 2♥, 9♣\n"
        + "C8: 2♦, 9♠\n"
        + "C9: 3♣, 9♥\n"
        + "C10: 3♠, 9♦, 8♣, 7♦\n"
        + "C11: 3♥, 10♣\n"
        + "C12: 3♦, 10♠\n"
        + "C13: 4♣, 10♥\n"
        + "C14: 4♠, 10♦\n"
        + "C15: 4♥, J♣\n"
        + "C16: 4♦, J♠\n"
        + "C17: 5♣, J♥\n"
        + "C18: 5♠, J♦\n"
        + "C19: 5♥, Q♣\n"
        + "C20: 5♦, Q♠\n"
        + "C21: 6♣, Q♥\n"
        + "C22: 6♠, Q♦\n"
        + "C23: 6♥, K♣\n"
        + "C24: 6♦, K♠\n"
        + "C25: 7♣, K♥\n"
        + "C26: 7♠, K♦", view.toString());
  }

  @Test
  public void SolveWithMultiMove() {
    initData(52, false, GameType.MULTIMOVE);
    model.move(PileType.CASCADE, 43, 0, PileType.CASCADE, 45);
    model.move(PileType.CASCADE, 45, 0, PileType.CASCADE, 51);
    for (int i = 0; i < 52; i++) {
      if (i == 43) {
        model.move(PileType.CASCADE, 51, 2, PileType.FOUNDATION,i % 4);
      } else if (i == 45) {
        model.move(PileType.CASCADE, 51, 1, PileType.FOUNDATION,i % 4);
      } else {
        model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION,i % 4);
      }
    }
    assertEquals("F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n"
        + "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n"
        + "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n"
        + "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1:\n"
        + "C2:\n"
        + "C3:\n"
        + "C4:\n"
        + "C5:\n"
        + "C6:\n"
        + "C7:\n"
        + "C8:\n"
        + "C9:\n"
        + "C10:\n"
        + "C11:\n"
        + "C12:\n"
        + "C13:\n"
        + "C14:\n"
        + "C15:\n"
        + "C16:\n"
        + "C17:\n"
        + "C18:\n"
        + "C19:\n"
        + "C20:\n"
        + "C21:\n"
        + "C22:\n"
        + "C23:\n"
        + "C24:\n"
        + "C25:\n"
        + "C26:\n"
        + "C27:\n"
        + "C28:\n"
        + "C29:\n"
        + "C30:\n"
        + "C31:\n"
        + "C32:\n"
        + "C33:\n"
        + "C34:\n"
        + "C35:\n"
        + "C36:\n"
        + "C37:\n"
        + "C38:\n"
        + "C39:\n"
        + "C40:\n"
        + "C41:\n"
        + "C42:\n"
        + "C43:\n"
        + "C44:\n"
        + "C45:\n"
        + "C46:\n"
        + "C47:\n"
        + "C48:\n"
        + "C49:\n"
        + "C50:\n"
        + "C51:\n"
        + "C52:", view.toString());
  }

  /** Standard Initialization with a 7 cascade pile, 4 open pile game.
   */
  private void simpleInitData() {
    this.model = FreecellModelCreator.create(GameType.SINGLEMOVE);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 7, 4, false);
  }

  /** Moved around Initialization with a 7 cascade pile, 4 open pile game.
   */
  private void simpleInitDataTwo() {
    this.model = FreecellModelCreator.create(GameType.SINGLEMOVE);
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
    this.model = FreecellModelCreator.create(GameType.SINGLEMOVE);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 52, 4, false);
    for (int i = 0; i < 52; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION,i % 4);
    }
  }

  @Test
  public void testGetDeck() {
    this.model = FreecellModelCreator.create(GameType.SINGLEMOVE);
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
    this.model = FreecellModelCreator.create(GameType.SINGLEMOVE);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 7, 4, false);
    String output = view.toString();
    assertEquals("F1:\n"
        + "F2:\n"
        + "F3:\n"
        + "F4:\n"
        + "O1:\n"
        + "O2:\n"
        + "O3:\n"
        + "O4:\n"
        + "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n"
        + "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n"
        + "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n"
        + "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n"
        + "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n"
        + "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n"
        + "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣", output);

    this.model = FreecellModelCreator.create(GameType.SINGLEMOVE);
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
    this.model = FreecellModelCreator.create(GameType.SINGLEMOVE);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    this.deck.remove(0);
    model.startGame(deck, 8, 4, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNumCascadePiles() {
    this.model = FreecellModelCreator.create(GameType.SINGLEMOVE);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, -3, 4, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNumCascadePiles2() {
    this.model = FreecellModelCreator.create(GameType.SINGLEMOVE);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 3, 4, true);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNumOpenPiles() {
    this.model = FreecellModelCreator.create(GameType.SINGLEMOVE);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 8, -1, true);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNumOpenPiles2() {
    this.model = FreecellModelCreator.create(GameType.SINGLEMOVE);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.startGame(deck, 8, 0, true);
  }

  @Test
  public void testValidMoves() {

    simpleInitData();
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
        + "O1: 5♠\n"
        + "O2: 7♣\n"
        + "O3: 3♥\n"
        + "O4:\n"
        + "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n"
        + "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n"
        + "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦, Q♠\n"
        + "C4:\n"
        + "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n"
        + "C6: 2♠, 4♣, 5♦, 7♥\n"
        + "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣, Q♦, J♣, 10♥, 9♠, 8♦", output);


    simpleInitData();
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
        + "O1: 7♣\n"
        + "O2:\n"
        + "O3: 3♥\n"
        + "O4:\n"
        + "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n"
        + "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n"
        + "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦, Q♠\n"
        + "C4: 5♠\n"
        + "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n"
        + "C6: 2♠, 4♣, 5♦, 7♥\n"
        + "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣, Q♦, J♣, 10♥, 9♠, 8♦", output);

  }

  // Moving a card from index out of bounds √
  @Test(expected = IllegalArgumentException.class)
  public void FromCascadeOOB() {
    simpleInitData();
    System.out.println(view.toString());
    model.move(PileType.CASCADE, 3, 8, PileType.CASCADE, 2);
  }

  // Moving a card from not the end index √
  @Test(expected = IllegalArgumentException.class)
  public void FromCascadeNotAtEnd() {
    simpleInitData();
    System.out.println(view.toString());
    model.move(PileType.CASCADE, 5, 5, PileType.CASCADE, 4);
  }

  // Moving a card to its same place √
  @Test(expected = IllegalArgumentException.class)
  public void toItself() {
    simpleInitData();
    System.out.println(view.toString());
    model.move(PileType.CASCADE, 5, 6, PileType.CASCADE, 5);
  }

  // Moving a card from index out of bounds √
  @Test(expected = IllegalArgumentException.class)
  public void OpenOOB() {
    simpleInitData();
    System.out.println(view.toString());
    model.move(PileType.OPEN, 3, 8, PileType.CASCADE, 2);
  }

  // moving from an empty Open √
  @Test(expected = IllegalArgumentException.class)
  public void MovingFromEmpty() {
    simpleInitData();
    System.out.println(view.toString());
    model.move(PileType.OPEN, 2, 0, PileType.OPEN, 1);
  }

  // Moving a card that's suit doesn't fit with the pattern √
  @Test(expected = IllegalArgumentException.class)
  public void InvalidSuit() {
    simpleInitData();
    System.out.println(view.toString());
    model.move(PileType.CASCADE, 3, 6, PileType.CASCADE, 0);
  }

  // Moving a card that's value doesn't fit with the pattern √
  @Test(expected = IllegalArgumentException.class)
  public void InvalidValue() {
    simpleInitData();
    model.move(PileType.CASCADE, 3, 6, PileType.CASCADE, 4);
  }

  // Moving a card that is not an ace when empty, √
  @Test(expected = IllegalArgumentException.class)
  public void InvalidMoveToFoundation() {
    simpleInitData();
    model.move(PileType.CASCADE, 4, 6, PileType.FOUNDATION, 3);
  }

  // Moving a card into Foundation that does not fit the pattern
  @Test(expected = IllegalArgumentException.class)
  public void BadFoundationPattern() {
    simpleInitDataTwo();
    model.move(PileType.CASCADE, 4, 6, PileType.FOUNDATION, 2);
  }

  // Moving card when a Card is already in open
  @Test(expected = IllegalArgumentException.class)
  public void CardAlreadyInOpen() {
    simpleInitDataTwo();
    model.move(PileType.CASCADE, 4, 6, PileType.OPEN, 2);
  }


  // Bad move from Foundation, any move from foundation is bad
  @Test(expected = IllegalArgumentException.class)
  public void InvalidMoveFromFoundation() {
    simpleInitDataTwo();
    model.move(PileType.FOUNDATION, 2, 0, PileType.OPEN, 1);
  }


  @Test(expected = IllegalStateException.class)
  public void testInvalidMoveStart() {
    this.model = FreecellModelCreator.create(GameType.SINGLEMOVE);
    this.view = new FreecellTextView(model);
    this.deck = model.getDeck();
    model.move(PileType.CASCADE, 1, 6, PileType.OPEN, 1);

  }


  @Test
  public void testIsGameOver() {
    simpleInitData();
    assertFalse(model.isGameOver());
    initSolved();
    assertTrue(model.isGameOver());
  }


  @Test
  public void testGetNumCardsInFoundationPile() {
    simpleInitDataTwo();
    assertEquals(1, model.getNumCardsInFoundationPile(2));
  }

  @Test
  public void testGetNumCascadePiles() {
    simpleInitDataTwo();
    assertEquals(7, model.getNumCascadePiles());
  }

  @Test
  public void testGetNumCardsInCascadePile() {
    simpleInitDataTwo();
    assertEquals(8, model.getNumCardsInCascadePile(0));

  }

  @Test
  public void testGetNumCardsInOpenPile() {
    simpleInitDataTwo();
    assertEquals(1, model.getNumCardsInOpenPile(2));
  }

  @Test
  public void testGetNumOpenPiles() {
    simpleInitDataTwo();
    assertEquals(4, model.getNumOpenPiles());
  }

  @Test
  public void testGetFoundationCardAt() {
    simpleInitDataTwo();
    assertEquals(new Card('♦', 1), model.getFoundationCardAt(2, 0));
  }

  @Test
  public void testGetCascadeCardAt() {
    simpleInitDataTwo();
    System.out.println(view.toString());
    assertEquals(new Card('♠', 13), model.getCascadeCardAt(0, 7));
  }

  @Test
  public void testGetOpenCardAt() {
    simpleInitDataTwo();
    System.out.println(view.toString());
    assertEquals(new Card('♣', 7), model.getOpenCardAt(0));
  }


}
