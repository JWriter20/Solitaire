import cs3500.freecell.controller.FreecellController;
import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.ICard;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.PipedWriter;
import java.io.PipedReader;
import java.util.List;
import java.util.Objects;


import static org.junit.Assert.assertEquals;


/**
 * Tests the {@code SimpleFreecellController} class' methods.
 */
public class SimpleFreecellControllerTests {

  FreecellModel<ICard> model;
  FreecellController<ICard> controller;
  Appendable ap;
  Readable rd;


  private void initData(String moves) {
    model = new SimpleFreecellModel();
    ap = new StringBuffer();
    rd = new StringReader(moves);
    controller = new SimpleFreecellController(model, rd, ap);
    controller.playGame(model.getDeck(), 7, 4, false);
  }

  private void initData(String moves, int numCascade, boolean shuffled) {
    model = new SimpleFreecellModel();
    ap = new StringBuffer();
    rd = new StringReader(moves);
    controller = new SimpleFreecellController(model, rd, ap);
    controller.playGame(model.getDeck(), numCascade, 4, shuffled);
  }

  @Test
  public void testSpaceSeparatedValid() {
    initData("C2 8 O1 C2 q");
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1: K♥\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "Game quit prematurely.", ap.toString());
  }

  @Test
  public void testNLSeparatedValue() {
    initData("C2\n8\nO1\nC2\nq");
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1: K♥\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "Game quit prematurely.", ap.toString());
  }

  /*
  @Test
  public void testShuffledMoveWithoutError() {
    initData("C2 8 O1 C2 q", 8, true);
  }
  */

  @Test
  public void badPileTypeIn() {
    initData("B2 8 C2 8 O1 q");
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "Invalid move. Try again. B2 Discarded. Finding next valid Pile.\n" +
        "Invalid move. Try again. 8 Discarded. Finding next valid Pile.\n" +
        "F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1: K♥\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "Game quit prematurely.", ap.toString());
  }

  @Test
  public void testInvalidPileFilled() {
    initData("C13 C4 7 C3 q");
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "Invalid move. Try again. C13 Discarded. Finding next valid Pile.\n" +
        "F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦, Q♠\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "Game quit prematurely.", ap.toString());
  }

  // ~
  @Test
  public void testInvalidCardFilled() {
    initData("C1 16 O1 q");
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "Invalid move. Try again. Move not Executed.\n" +
        "F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "Game quit prematurely.", ap.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testEmptyMoves() {
    initData("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAp() {
    model = new SimpleFreecellModel();
    ap = null;
    rd = new StringReader("C1 8 O1 q");
    controller = new SimpleFreecellController(model, rd, ap);
    controller.playGame(model.getDeck(), 7, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullRd() {
    model = new SimpleFreecellModel();
    ap = new StringBuffer();
    rd = null;
    controller = new SimpleFreecellController(model, rd, ap);
    controller.playGame(model.getDeck(), 7, 4, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    model = null;
    ap = new StringBuffer();
    rd = new StringReader("C1 8 O1 q");
    controller = new SimpleFreecellController(model, rd, ap);
    controller.playGame(model.getDeck(), 7, 4, false);
  }

  @Test(expected = IllegalStateException.class)
  public void testNoInputsLeft() {
    initData("C1 8 O1");
    assertEquals("", ap.toString());
  }

  @Test
  public void testNegInput() {
    initData("C-1 8 O1 q");
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "Invalid move. Try again. C-1 Discarded. Finding next valid Pile.\n" +
        "Invalid move. Try again. 8 Discarded. Finding next valid Pile.\n" +
        "Game quit prematurely.", ap.toString());
  }

  @Test
  public void testOOBInput() {
    initData("C1033 8 O1 q");
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "Invalid move. Try again. C1033 Discarded. Finding next valid Pile.\n" +
        "Invalid move. Try again. 8 Discarded. Finding next valid Pile.\n" +
        "Game quit prematurely.", ap.toString());
  }

  @Test
  public void testBadMove() {
    initData("C1 8 C2 q");
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "Invalid move. Try again. Move not Executed.\n" +
        "F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "Game quit prematurely.", ap.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testGameNotStarted() {
    model = new SimpleFreecellModel();
    ap = new StringBuffer();
    rd = new StringReader("C1 3 O1 q");
    controller = new SimpleFreecellController(model, rd, ap);
    model.move(PileType.CASCADE, 1, 3, PileType.OPEN, 1);
    controller.playGame(model.getDeck(), 7, 4, false);
  }

  @Test
  public void testCapitalQ() {
    initData("C1 8 O1 Q");
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥\n" +
        "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n" +
        "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦\n" +
        "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥, Q♠\n" +
        "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n" +
        "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n" +
        "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n" +
        "O1: K♠\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "Game quit prematurely.", ap.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void badAP() {
    model = new SimpleFreecellModel();
    ap = new PipedWriter();
    rd = new StringReader("C1 3 O1 q");
    controller = new SimpleFreecellController(model, rd, ap);
    controller.playGame(model.getDeck(), 7, 4, false);
  }

  @Test(expected = IllegalStateException.class)
  public void badRD() {
    model = new SimpleFreecellModel();
    ap = new StringBuffer();
    rd = new PipedReader();
    controller = new SimpleFreecellController(model, rd, ap);
    controller.playGame(model.getDeck(), 7, 4, false);
  }


  /**
   * Mock class used in the two test cases below to make sure
   * the correct inputs are being received.
   */
  public class MockSMModel implements FreecellModel {
    final StringBuilder log;

    /**
     * Constructs a {@code MockSMModel} object.
     */
    public MockSMModel(StringBuilder log) {
      this.log = Objects.requireNonNull(log);
    }

    @Override
    public List getDeck() {
      return null;
    }

    @Override
    public void startGame(List deck, int numCascadePiles,
                          int numOpenPiles, boolean shuffle)
        throws IllegalArgumentException {
      log.append(String.format("deck = " + deck.toString() + ", numCascade = %d, numOpen = %d, " +
              "shuffle = " + String.valueOf(shuffle) + "\n",
          numCascadePiles, numOpenPiles, shuffle));
    }

    @Override
    public void move(PileType source, int pileNumber, int cardIndex,
                     PileType destination, int destPileNumber)
        throws IllegalArgumentException, IllegalStateException {
      log.append(String.format("Source = " + source + ", pileNum = %d,cardIndex = %d, " +
          "destination = " + destination + ", destPileNum = %d\n", pileNumber,
          cardIndex, destPileNumber));
    }

    @Override
    public boolean isGameOver() {
      return false;
    }

    @Override
    public Object getOpenCardAt(int pileIndex)
        throws IllegalArgumentException, IllegalStateException {
      return null;
    }

    @Override
    public Object getCascadeCardAt(int pileIndex, int cardIndex)
        throws IllegalArgumentException, IllegalStateException {
      return null;
    }

    @Override
    public Object getFoundationCardAt(int pileIndex, int cardIndex)
        throws IllegalArgumentException, IllegalStateException {
      return null;
    }

    @Override
    public int getNumOpenPiles() {
      return 0;
    }

    @Override
    public int getNumCardsInOpenPile(int index)
        throws IllegalArgumentException, IllegalStateException {
      return 0;
    }

    @Override
    public int getNumCardsInCascadePile(int index)
        throws IllegalArgumentException, IllegalStateException {
      return 0;
    }

    @Override
    public int getNumCascadePiles() {
      return 0;
    }

    @Override
    public int getNumCardsInFoundationPile(int index)
        throws IllegalArgumentException, IllegalStateException {
      return 0;
    }

  }

  @Test
  public void testStartGameMock() throws IOException {

    StringBuilder log = new StringBuilder();
    FreecellModel m = new MockSMModel(log);

    model = new SimpleFreecellModel();
    rd = new StringReader("C1 13 O1 q");
    StringBuilder dontCareOutput = new StringBuilder();
    SimpleFreecellController c = new SimpleFreecellController(m, rd, dontCareOutput);


    c.playGame(model.getDeck(), 13, 4, false);
    assertEquals("deck = [A♣, A♠, A♥, A♦, 2♣, 2♠, 2♥, 2♦, 3♣, 3♠, 3♥, 3♦," +
        " 4♣, 4♠, 4♥, 4♦, 5♣, 5♠, 5♥, 5♦, 6♣, 6♠, 6♥, 6♦, 7♣, 7♠, 7♥, 7♦, 8♣, 8♠, 8♥," +
        " 8♦, 9♣, 9♠, 9♥, 9♦, 10♣, 10♠, 10♥, 10♦, J♣, J♠, J♥, J♦, Q♣, Q♠, Q♥, Q♦, K♣," +
        " K♠, K♥, K♦], numCascade = 13, numOpen = 4, shuffle = false\n", log.toString());
  }

  @Test
  public void testMoveMock() throws IOException {
    StringBuilder log = new StringBuilder();
    FreecellModel m = new MockSMModel(log);

    model = new SimpleFreecellModel();
    rd = new StringReader("C1 13 O1 q");
    StringBuilder dontCareOutput = new StringBuilder();
    SimpleFreecellController c = new SimpleFreecellController(m, rd, dontCareOutput);


    c.playGame(model.getDeck(), 13, 4, false);
    m.move(PileType.CASCADE, 1, 13, PileType.OPEN, 1);
    assertEquals("deck = [A♣, A♠, A♥, A♦, 2♣, 2♠, 2♥, 2♦, 3♣, 3♠, 3♥, 3♦, 4♣, 4♠, " +
        "4♥, 4♦, 5♣, 5♠, 5♥, 5♦, 6♣, 6♠, 6♥, 6♦, 7♣, 7♠, 7♥, 7♦, 8♣, 8♠, 8♥, 8♦, 9♣, 9♠, 9♥," +
        " 9♦, 10♣, 10♠, 10♥, 10♦, J♣, J♠, J♥, J♦, Q♣, Q♠, Q♥, Q♦, K♣, K♠, K♥, K♦], " +
        "numCascade = 13, numOpen = 4, shuffle = false\n" +
        "Source = CASCADE, pileNum = 1,cardIndex = 13, destination = OPEN, destPileNum = 1\n",
        log.toString());
  }

  @Test
  public void testGameOver() {
    StringBuilder moves = new StringBuilder();
    for (int i = 0; i < 52; i++) {
      moves.append("C" + (i + 1) + " 1 F" + ((i % 4) + 1) + " ");
    }

    initData(moves.toString(), 52, false);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣\n" +
        "C2: A♠\n" +
        "C3: A♥\n" +
        "C4: A♦\n" +
        "C5: 2♣\n" +
        "C6: 2♠\n" +
        "C7: 2♥\n" +
        "C8: 2♦\n" +
        "C9: 3♣\n" +
        "C10: 3♠\n" +
        "C11: 3♥\n" +
        "C12: 3♦\n" +
        "C13: 4♣\n" +
        "C14: 4♠\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1:\n" +
        "C2: A♠\n" +
        "C3: A♥\n" +
        "C4: A♦\n" +
        "C5: 2♣\n" +
        "C6: 2♠\n" +
        "C7: 2♥\n" +
        "C8: 2♦\n" +
        "C9: 3♣\n" +
        "C10: 3♠\n" +
        "C11: 3♥\n" +
        "C12: 3♦\n" +
        "C13: 4♣\n" +
        "C14: 4♠\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣\n" +
        "F2: A♠\n" +
        "F3:\n" +
        "F4:\n" +
        "C1:\n" +
        "C2:\n" +
        "C3: A♥\n" +
        "C4: A♦\n" +
        "C5: 2♣\n" +
        "C6: 2♠\n" +
        "C7: 2♥\n" +
        "C8: 2♦\n" +
        "C9: 3♣\n" +
        "C10: 3♠\n" +
        "C11: 3♥\n" +
        "C12: 3♦\n" +
        "C13: 4♣\n" +
        "C14: 4♠\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣\n" +
        "F2: A♠\n" +
        "F3: A♥\n" +
        "F4:\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4: A♦\n" +
        "C5: 2♣\n" +
        "C6: 2♠\n" +
        "C7: 2♥\n" +
        "C8: 2♦\n" +
        "C9: 3♣\n" +
        "C10: 3♠\n" +
        "C11: 3♥\n" +
        "C12: 3♦\n" +
        "C13: 4♣\n" +
        "C14: 4♠\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣\n" +
        "F2: A♠\n" +
        "F3: A♥\n" +
        "F4: A♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5: 2♣\n" +
        "C6: 2♠\n" +
        "C7: 2♥\n" +
        "C8: 2♦\n" +
        "C9: 3♣\n" +
        "C10: 3♠\n" +
        "C11: 3♥\n" +
        "C12: 3♦\n" +
        "C13: 4♣\n" +
        "C14: 4♠\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣\n" +
        "F2: A♠\n" +
        "F3: A♥\n" +
        "F4: A♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6: 2♠\n" +
        "C7: 2♥\n" +
        "C8: 2♦\n" +
        "C9: 3♣\n" +
        "C10: 3♠\n" +
        "C11: 3♥\n" +
        "C12: 3♦\n" +
        "C13: 4♣\n" +
        "C14: 4♠\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣\n" +
        "F2: A♠, 2♠\n" +
        "F3: A♥\n" +
        "F4: A♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7: 2♥\n" +
        "C8: 2♦\n" +
        "C9: 3♣\n" +
        "C10: 3♠\n" +
        "C11: 3♥\n" +
        "C12: 3♦\n" +
        "C13: 4♣\n" +
        "C14: 4♠\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣\n" +
        "F2: A♠, 2♠\n" +
        "F3: A♥, 2♥\n" +
        "F4: A♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8: 2♦\n" +
        "C9: 3♣\n" +
        "C10: 3♠\n" +
        "C11: 3♥\n" +
        "C12: 3♦\n" +
        "C13: 4♣\n" +
        "C14: 4♠\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣\n" +
        "F2: A♠, 2♠\n" +
        "F3: A♥, 2♥\n" +
        "F4: A♦, 2♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9: 3♣\n" +
        "C10: 3♠\n" +
        "C11: 3♥\n" +
        "C12: 3♦\n" +
        "C13: 4♣\n" +
        "C14: 4♠\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣\n" +
        "F2: A♠, 2♠\n" +
        "F3: A♥, 2♥\n" +
        "F4: A♦, 2♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10: 3♠\n" +
        "C11: 3♥\n" +
        "C12: 3♦\n" +
        "C13: 4♣\n" +
        "C14: 4♠\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣\n" +
        "F2: A♠, 2♠, 3♠\n" +
        "F3: A♥, 2♥\n" +
        "F4: A♦, 2♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11: 3♥\n" +
        "C12: 3♦\n" +
        "C13: 4♣\n" +
        "C14: 4♠\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣\n" +
        "F2: A♠, 2♠, 3♠\n" +
        "F3: A♥, 2♥, 3♥\n" +
        "F4: A♦, 2♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12: 3♦\n" +
        "C13: 4♣\n" +
        "C14: 4♠\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣\n" +
        "F2: A♠, 2♠, 3♠\n" +
        "F3: A♥, 2♥, 3♥\n" +
        "F4: A♦, 2♦, 3♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13: 4♣\n" +
        "C14: 4♠\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣\n" +
        "F2: A♠, 2♠, 3♠\n" +
        "F3: A♥, 2♥, 3♥\n" +
        "F4: A♦, 2♦, 3♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14: 4♠\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠\n" +
        "F3: A♥, 2♥, 3♥\n" +
        "F4: A♦, 2♦, 3♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15: 4♥\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥\n" +
        "F4: A♦, 2♦, 3♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16: 4♦\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17: 5♣\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18: 5♠\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19: 5♥\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20: 5♦\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21: 6♣\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22: 6♠\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23: 6♥\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24: 6♦\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25: 7♣\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26: 7♠\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27: 7♥\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28: 7♦\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29: 8♣\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30: 8♠\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31: 8♥\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32: 8♦\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33: 9♣\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34: 9♠\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35: 9♥\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36: 9♦\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37: 10♣\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38: 10♠\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39: 10♥\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39:\n" +
        "C40: 10♦\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39:\n" +
        "C40:\n" +
        "C41: J♣\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39:\n" +
        "C40:\n" +
        "C41:\n" +
        "C42: J♠\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39:\n" +
        "C40:\n" +
        "C41:\n" +
        "C42:\n" +
        "C43: J♥\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39:\n" +
        "C40:\n" +
        "C41:\n" +
        "C42:\n" +
        "C43:\n" +
        "C44: J♦\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39:\n" +
        "C40:\n" +
        "C41:\n" +
        "C42:\n" +
        "C43:\n" +
        "C44:\n" +
        "C45: Q♣\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39:\n" +
        "C40:\n" +
        "C41:\n" +
        "C42:\n" +
        "C43:\n" +
        "C44:\n" +
        "C45:\n" +
        "C46: Q♠\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39:\n" +
        "C40:\n" +
        "C41:\n" +
        "C42:\n" +
        "C43:\n" +
        "C44:\n" +
        "C45:\n" +
        "C46:\n" +
        "C47: Q♥\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39:\n" +
        "C40:\n" +
        "C41:\n" +
        "C42:\n" +
        "C43:\n" +
        "C44:\n" +
        "C45:\n" +
        "C46:\n" +
        "C47:\n" +
        "C48: Q♦\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39:\n" +
        "C40:\n" +
        "C41:\n" +
        "C42:\n" +
        "C43:\n" +
        "C44:\n" +
        "C45:\n" +
        "C46:\n" +
        "C47:\n" +
        "C48:\n" +
        "C49: K♣\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39:\n" +
        "C40:\n" +
        "C41:\n" +
        "C42:\n" +
        "C43:\n" +
        "C44:\n" +
        "C45:\n" +
        "C46:\n" +
        "C47:\n" +
        "C48:\n" +
        "C49:\n" +
        "C50: K♠\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39:\n" +
        "C40:\n" +
        "C41:\n" +
        "C42:\n" +
        "C43:\n" +
        "C44:\n" +
        "C45:\n" +
        "C46:\n" +
        "C47:\n" +
        "C48:\n" +
        "C49:\n" +
        "C50:\n" +
        "C51: K♥\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39:\n" +
        "C40:\n" +
        "C41:\n" +
        "C42:\n" +
        "C43:\n" +
        "C44:\n" +
        "C45:\n" +
        "C46:\n" +
        "C47:\n" +
        "C48:\n" +
        "C49:\n" +
        "C50:\n" +
        "C51:\n" +
        "C52: K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "F1: A♣, 2♣, 3♣, 4♣, 5♣, 6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣\n" +
        "F2: A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠\n" +
        "F3: A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥, 10♥, J♥, Q♥, K♥\n" +
        "F4: A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦\n" +
        "C1:\n" +
        "C2:\n" +
        "C3:\n" +
        "C4:\n" +
        "C5:\n" +
        "C6:\n" +
        "C7:\n" +
        "C8:\n" +
        "C9:\n" +
        "C10:\n" +
        "C11:\n" +
        "C12:\n" +
        "C13:\n" +
        "C14:\n" +
        "C15:\n" +
        "C16:\n" +
        "C17:\n" +
        "C18:\n" +
        "C19:\n" +
        "C20:\n" +
        "C21:\n" +
        "C22:\n" +
        "C23:\n" +
        "C24:\n" +
        "C25:\n" +
        "C26:\n" +
        "C27:\n" +
        "C28:\n" +
        "C29:\n" +
        "C30:\n" +
        "C31:\n" +
        "C32:\n" +
        "C33:\n" +
        "C34:\n" +
        "C35:\n" +
        "C36:\n" +
        "C37:\n" +
        "C38:\n" +
        "C39:\n" +
        "C40:\n" +
        "C41:\n" +
        "C42:\n" +
        "C43:\n" +
        "C44:\n" +
        "C45:\n" +
        "C46:\n" +
        "C47:\n" +
        "C48:\n" +
        "C49:\n" +
        "C50:\n" +
        "C51:\n" +
        "C52:\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n" +
        "\n" +
        "Game over.", ap.toString());
  }

}
