package bits;

public class GetBit {
    //Get bit i for a give number n. (i count from 0 and starts from right)
    public static boolean getBit(int num, int i){
        int result = num & (1<<i);

        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }
}
