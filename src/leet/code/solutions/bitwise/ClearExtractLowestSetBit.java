package leet.code.solutions.bitwise;

public class ClearExtractLowestSetBit {

    public static void main(String[] args) {
        int num = 3;
        int numWithClearedLSB = clearLSB(num);//3 = [11], cleared LSB = [10] = 2
        System.out.println(numWithClearedLSB);
        int exctectedLSB = extractLowestSetBit(num);//3 = [11], extracted LSB = 1
        System.out.println(exctectedLSB);
        int extractCertainBit = extractCertainSetBit(num,1);
        System.out.println(extractCertainBit);
    }

    private static int clearLSB(int num) {
        return num & (num - 1);
    }

    private static int extractLowestSetBit(int num) {
        return num & ~(num - 1);
    }

    private static int extractCertainSetBit(int num, int bitToExtract) {
        return num >>> bitToExtract;
    }
}
