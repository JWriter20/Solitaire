import cs3500.freecell.controller.SimpleFreecellController;
import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.view.FreecellTextView;
import org.junit.Test;

import java.io.IOException;
import java.io.PipedWriter;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;


/**
 * Tests the {@code FreecellTextView} class and its methods.
 */
public class UpdatedFreecellTextViewTests {
  FreecellModel<ICard> model;
  FreecellTextView view;
  Appendable ap;

  private void initData() {
    model = new SimpleFreecellModel();
    ap = new StringBuffer();
    view = new FreecellTextView(model, ap);
  }


  @Test
  public void testRenderBoard() {
    initData();
    model.startGame(model.getDeck(), 13, 4, false);
    try {
      view.renderBoard();
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 4♠, 7♥, 10♦\n" +
        "C2: A♠, 4♥, 7♦, J♣\n" +
        "C3: A♥, 4♦, 8♣, J♠\n" +
        "C4: A♦, 5♣, 8♠, J♥\n" +
        "C5: 2♣, 5♠, 8♥, J♦\n" +
        "C6: 2♠, 5♥, 8♦, Q♣\n" +
        "C7: 2♥, 5♦, 9♣, Q♠\n" +
        "C8: 2♦, 6♣, 9♠, Q♥\n" +
        "C9: 3♣, 6♠, 9♥, Q♦\n" +
        "C10: 3♠, 6♥, 9♦, K♣\n" +
        "C11: 3♥, 6♦, 10♣, K♠\n" +
        "C12: 3♦, 7♣, 10♠, K♥\n" +
        "C13: 4♣, 7♠, 10♥, K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:\n", ap.toString());
  }

  @Test
  public void testRenderMessage() {
    initData();
    try {
      view.renderMessage("TestingMessage");
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }

    assertEquals("TestingMessage", ap.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void badIO() {
    model = new SimpleFreecellModel();
    ap = new PipedWriter();
    Readable rd = new StringReader("");
    view = new FreecellTextView(model, ap);
    try {
      view.renderMessage("asdf");
    } catch (IOException e) {
      throw new IllegalArgumentException("IO Exception Found");
    }
    SimpleFreecellController controller = new SimpleFreecellController(model, rd, ap);

  }

  @Test
  public void testToString() {
    initData();
    model.startGame(model.getDeck(), 13, 4, false);
    assertEquals("F1:\n" +
        "F2:\n" +
        "F3:\n" +
        "F4:\n" +
        "C1: A♣, 4♠, 7♥, 10♦\n" +
        "C2: A♠, 4♥, 7♦, J♣\n" +
        "C3: A♥, 4♦, 8♣, J♠\n" +
        "C4: A♦, 5♣, 8♠, J♥\n" +
        "C5: 2♣, 5♠, 8♥, J♦\n" +
        "C6: 2♠, 5♥, 8♦, Q♣\n" +
        "C7: 2♥, 5♦, 9♣, Q♠\n" +
        "C8: 2♦, 6♣, 9♠, Q♥\n" +
        "C9: 3♣, 6♠, 9♥, Q♦\n" +
        "C10: 3♠, 6♥, 9♦, K♣\n" +
        "C11: 3♥, 6♦, 10♣, K♠\n" +
        "C12: 3♦, 7♣, 10♠, K♥\n" +
        "C13: 4♣, 7♠, 10♥, K♦\n" +
        "O1:\n" +
        "O2:\n" +
        "O3:\n" +
        "O4:", view.toString());
  }
}
