import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random r = new Random();

        char [] letter =new char[52];

        for (int i = 0; i < letter.length; i++) {
            if (i<26){
                letter[i]= (char)('A'+i);
            }else {
                letter[i]=(char) ('a'+i-26);
            }
        }
        char []number={'0','1','2','3','4','5','6','7','8','9'};

        char[] cap = new char[5];
        for (int i = 0; i < 4; i++) {
            int num = r.nextInt(52);
            cap[i]=letter[num];
        }
        cap[4] = number[r.nextInt(10)];

        int index = r.nextInt(5);
        char temp = cap[4];
        cap[4]=cap[index];
        cap[index]= temp;

        StringBuilder strB = new StringBuilder("");
        for (int i = 0; i < cap.length; i++) {
            strB.append(cap[i]);
        }
        strB.toString();
        System.out.println("验证码是：" + strB);
    }
}
