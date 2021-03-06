import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(null));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("aba"));
        assertFalse(palindrome.isPalindrome("persiflage"));
        assertFalse(palindrome.isPalindrome("Aba"));
    }

    @Test
    public void testNewIsPalindrome() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("a", cc));
        assertTrue(palindrome.isPalindrome(null, cc));
        assertFalse(palindrome.isPalindrome("noon", cc));
        assertTrue(palindrome.isPalindrome("acb", cc));
        assertFalse(palindrome.isPalindrome("persiflage", cc));
        assertFalse(palindrome.isPalindrome("Aba", cc));
    }
}
