package bits;

public class LogBase2 {
    public static int getLogBase2(int n) {
        int i = 1, cntr = 0;
        while(i <= n) {
            i = i << 1;
            cntr++;
        }
        if (i > n) cntr--;
        return cntr;
    }
}
