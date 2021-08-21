import cs3500.freecell.model.Card;
import cs3500.freecell.model.hw02.OpenPile;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/** Adds tests for the (modified) methods of {@link OpenPile} class.
 */
public class TestOpenPile {

  private OpenPile p = new OpenPile();


  // Test for pop not needed as it just runs the pop method from the super class


  @Test
  public void testPush() {
    p.push(new Card('♠', 10));

    assertEquals(p.peek(), new Card('♠', 10));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadPush() {
    p.push(new Card('♠', 10));
    p.push(new Card('♠', 1));
  }

  @Test
  public void testPeek() {
    p.push(new Card('♥', 12));

    assertEquals(p.peek(), new Card('♥', 12));

  }
}
