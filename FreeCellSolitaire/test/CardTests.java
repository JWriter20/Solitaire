import cs3500.freecell.model.Card;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;


/** Adds tests for the methods of {@link Card} class.
 */
public class CardTests {

  @Test
  public void testEquals() {
    Card c1 = new Card('♦', 1);
    Card c2 = new Card('♦', 1);
    Card c3 = new Card('♦', 10);
    Card c4 = new Card('♠', 1);

    assertTrue(c1.equals(c2));
    assertFalse(c2.equals(c3));
    assertFalse(c2.equals(c4));
  }

  @Test
  public void testHashCode() {
    Card c1 = new Card('♦', 1);
    Card c2 = new Card('♦', 1);
    Card c3 = new Card('♦', 10);

    assertEquals(c1.hashCode(), c2.hashCode());
    assertNotEquals(c2.hashCode(), c3.hashCode());

  }

  @Test
  public void testToString() {
    Card c1 = new Card('♦', 1);
    Card c2 = new Card('♦', 11);

    assertEquals("A♦", c1.toString());
    assertEquals("J♦", c2.toString());
  }

  @Test
  public void testIsValid() {
    Card c1 = new Card('♦', 5);
    Card c2 = new Card('♦', 14);
    Card c3 = new Card('♦', -2);
    Card c4 = new Card('a', 7);

    assertTrue(c1.isValid());
    assertFalse(c2.isValid());
    assertFalse(c3.isValid());
    assertFalse(c4.isValid());


  }

  @Test
  public void getVal() {
    Card c1 = new Card('♦', 1);
    Card c2 = new Card('♦', 11);

    assertEquals(1, c1.getVal());
    assertEquals(11, c2.getVal());
  }

  @Test
  public void testGetSuit() {
    Card c1 = new Card('♦', 3);
    Card c2 = new Card('♠', 5);

    assertEquals('♦', c1.getSuit());
    assertEquals('♠', c2.getSuit());

  }

  @Test
  public void testIsRed() {
    Card c1 = new Card('♦', 3);
    Card c2 = new Card('♠', 5);

    assertTrue(c1.isRed());
    assertFalse(c2.isRed());
  }
}
