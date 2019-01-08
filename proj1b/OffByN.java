public class OffByN implements CharacterComparator {
    private int charDif;

    public OffByN(int N) {
        charDif = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int dif = Math.abs(x - y);
        if (dif == charDif) {
            return true;
        }
        return false;
    }
}
