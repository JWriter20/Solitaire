import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.ICard;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;



/** Tests the methods in the {@link FreecellTextView} class.
 * To make sure that they function as intended
 */
public class FreecellTextViewTests {

  private FreecellView view;
  private FreecellModel<ICard> model;

  private void initData() {
    this.model = new SimpleFreecellModel();
    this.view = new FreecellTextView(model);
    List<ICard> deck = model.getDeck();
    model.startGame(deck, 7, 4, false);
  }


  @Test
  public void testViewInCascade() {
    initData();
    String output = view.toString();
    assertEquals(
        "F1:\n"
            + "F2:\n"
            + "F3:\n" + "F4:\n"
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

  }

  @Test
  public void testViewInCascade2() {
    initData();
    model.move(PileType.CASCADE, 3, 6, PileType.CASCADE, 2);
    String output = view.toString();
    assertEquals(
        "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠\n"
            + "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n"
            + "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦, Q♠\n"
            + "C4: A♦, 3♥, 5♠, 7♣, 8♦, 10♥\n"
            + "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n"
            + "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, Q♦\n"
            + "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:", output);

  }

  @Test(expected = IllegalStateException.class)
  public void testNotStartedModel() throws IllegalStateException {
    this.model = new SimpleFreecellModel();
    this.view = new FreecellTextView(model);
    String output = view.toString();

  }

  @Test
  public void testViewInAllThree() {
    initData();
    model.move(PileType.CASCADE, 3, 6, PileType.CASCADE, 2);
    model.move(PileType.CASCADE, 5, 6, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 6);
    model.move(PileType.CASCADE, 5, 6, PileType.CASCADE, 0);
    model.move(PileType.CASCADE, 3, 5, PileType.CASCADE, 5);
    model.move(PileType.CASCADE, 3, 4, PileType.OPEN, 0);
    model.move(PileType.CASCADE, 3, 3, PileType.OPEN, 1);
    model.move(PileType.CASCADE, 3, 2, PileType.OPEN, 2);
    model.move(PileType.CASCADE, 3, 1, PileType.OPEN, 3);
    model.move(PileType.CASCADE, 3, 0, PileType.FOUNDATION, 2);
    String output = view.toString();
    assertEquals(
        "F1:\n"
            + "F2:\n"
            + "F3: A♦\n"
            + "F4:\n"
            + "C1: A♣, 2♦, 4♥, 6♠, 8♣, 9♦, J♥, K♠, Q♦\n"
            + "C2: A♠, 3♣, 4♦, 6♥, 8♠, 10♣, J♦, K♥\n"
            + "C3: A♥, 3♠, 5♣, 6♦, 8♥, 10♠, Q♣, K♦, Q♠\n"
            + "C4:\n"
            + "C5: 2♣, 3♦, 5♥, 7♠, 9♣, 10♦, Q♥\n"
            + "C6: 2♠, 4♣, 5♦, 7♥, 9♠, J♣, 10♥\n"
            + "C7: 2♥, 4♠, 6♣, 7♦, 9♥, J♠, K♣\n"
            + "O1: 8♦\n"
            + "O2: 7♣\n"
            + "O3: 5♠\n"
            + "O4: 3♥", output);

  }

}
