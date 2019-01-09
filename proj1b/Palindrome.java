public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0 ; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return true;
        }
        Deque d = wordToDeque(word);
        return remove(d);
    }

    private boolean remove(Deque<Character> d) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        } else {
            if (d.removeFirst().equals(d.removeLast())) {
                return remove(d);
            }
            else {
                return false;
            }
        }
    }

    /** Overload isPalindrome. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return true;
        }
        Deque d = wordToDeque(word);
        return helper(d, cc);
    }

    private boolean helper(Deque<Character> d, CharacterComparator cc) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        } else {
            if (cc.equalChars(d.removeFirst(), d.removeLast())) {
                return helper(d, cc);
            }
            else {
                return false;
            }
        }
    }
}
