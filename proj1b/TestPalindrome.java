import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.*/
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } // Uncomment this class once you've created your Palindrome class. //

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("aA"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void testOverloadedMethod() {
        CharacterComparator offByOne = new OffByOne();
        assertFalse(palindrome.isPalindrome("aa", offByOne));
        assertFalse(palindrome.isPalindrome("bb", offByOne));
        assertFalse(palindrome.isPalindrome("aA", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("ab", offByOne));
        assertTrue(palindrome.isPalindrome("abab", offByOne));
    }

    @Test
    public void testOverloadedMethodN() {
        CharacterComparator offByN = new OffByN(4);
        assertFalse(palindrome.isPalindrome("aa", offByN));
        assertFalse(palindrome.isPalindrome("bb", offByN));
        assertFalse(palindrome.isPalindrome("aA", offByN));
        assertTrue(palindrome.isPalindrome("works", offByN));
        assertTrue(palindrome.isPalindrome("&", offByN));
        assertTrue(palindrome.isPalindrome("", offByN));
    }
}
