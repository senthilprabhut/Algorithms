package bits;

public class NextPowerOf2 {
    public static int nextPowerOf2(int target) {
      int bit = 1;
      //Left shift multiplies the number by 2
      //Once the pow of 2 is greater than or equal to target, return
      while(bit<target) bit = bit << 1;
      return bit;
    }
}
