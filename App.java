import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        ArrayList<User> userList = new ArrayList<>();
        User user1 = new User("zhang", "123aaa", "370282199106053214", "13612586327");
        userList.add(user1);
        Scanner sc = new Scanner(System.in);

        while (true) {
            logInMenu();
            int choose = sc.nextInt();
            switch (choose){
                case 1 -> login(userList);
                case 2 -> register(userList);
                case 3 -> forgetPassword();
                case 4 -> {
                    System.out.println("您已退出");
                    System.exit(0);
                }
                default -> System.out.println("没有这个选项，请重新输入");
            }
        }


    }



        //升级登陆系统

        public static void logInMenu(){
            System.out.println("欢迎来到学生管理系统");
            System.out.println("请选择操作：1.登录 2.注册 3.忘记密码 4.退出");
        }


        //登录
        public static void login(ArrayList<User> list){
            Scanner sc = new Scanner(System.in);

                while (true) {
                    System.out.println("请输入用户名：");
                    String str = sc.next();
                    boolean flag = judgeStr(list,str);

                    if (!flag){
                        System.out.println("用户名不存在，请先注册");
                        break;
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("请输入密码：");
                        String loginPassword = sc.next();

                        captcha();
                        boolean flag2 = judgeNamePassword(list,str,loginPassword);

                        if (flag2){
                            System.out.println("登录成功");
                            MangeSys ms = new MangeSys();
                            ms.startStudentSys();
                            break;
                        }else {
                            if (i==2){
                                System.out.println("账号被锁定");
                                return;
                            }else {
                                System.out.println("密码输入错误，还剩下"+(2-i)+"次机会");
                            }
                        }
                    }





                }




        }

        public static void inputPassword(){


        }

        public static boolean judgeStr(ArrayList<User> list,String str){

            if (list.size()==0){
                return false;
            }
            for (int i = 0; i < list.size(); i++) {
                    User user = list.get(i);
                    if (user.getUserName().equals(str) ){
                        return true;
                    }

            }
            return false;


        }

        public static boolean judgeNamePassword(ArrayList<User> list,String str,String password){
            int num = index(list,str);
            User user = list.get(num);
            if (user.getPassword().equals(password)){
                return true;
            }else {
                return false;
            }
        }

        public static int index(ArrayList<User> list,String str){
            int num =0;
            for (int i = 0; i < list.size(); i++) {
                User user = list.get(i);
                if (user.getUserName().equals(str) ){
                    num=i;
                    break;
                }
            }
            return num;

        }

        //注册
        public static void register(ArrayList<User> list){
            Scanner sc = new Scanner(System.in);
            User user = new User();
            String str1;
            String password;
            String idNumber;
            String phoneNumber;
            while (true) {
                System.out.println("请输入用户名，用户名长度为3~15位，是数字和字母的混合，不能是纯数字");
                str1 = sc.next();
                boolean flag = judgmentUserName(str1,list);

                if (!flag){
                    continue;
                }
                password = judgePassword();

                idNumber =judgeIdNumber();

                phoneNumber = judgePhoneNumber();

                user.setUserName(str1);
                user.setPassword(password);
                user.setIdNumber(idNumber);
                user.setPhoneNumber(phoneNumber);
                list.add(user);
                System.out.println("注册成功请使用其他功能");
                break;


            }




        }

        public static boolean judgmentUserName(String str,ArrayList<User> list){
            if (str.length()<3||str.length()>15){
                System.out.println("用户名长度不符合要求请重新输入：");
                return false;
            }
            int letterCount = 0;
            int numCount = 0;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if ((c>='a'&&c<='z')||(c>='A'&&c<='Z')){
                    letterCount++;
                } else if (c>='0'&&c<='9') {
                    numCount++;
                }
            }
            if (letterCount ==0){
                System.out.println("用户名中缺少字母，请重新输入");
                return false;
            }else if ((letterCount+numCount)<str.length()){
                System.out.println("用户名中含有不是字母或数字的字符，请重新输入");
                return false;
            }
            if (list.size()==0){
                return true;
            }
            for (int i = 0; i < list.size(); i++) {
                    if (str.equals(list.get(i).getUserName())){
                        System.out.println("用户名已经存在，请重新输入");
                        return false;
                    }
                }
            return true;

        }

        public static String judgePassword(){
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("用户名输入正确，请输入密码；");
                String password = sc.next();
                System.out.println("请再次输入密码，两次密码要相同");
                String password2 = sc.next();
                if (!(password.equals(password2))){
                    System.out.println("两次密码输入不一致，请重新输入密码");
                    continue;
                }
                return password;
            }


        }

        public static String judgeIdNumber(){
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("请输入身份证号");
                String personId = sc.next();

                boolean flag = judgePersonId(personId);

                if (!flag){
                    System.out.println("输入身份证号错误，请重新输入：");
                    continue;
                }

                return personId;
            }
        }

        public static boolean judgePersonId(String id){


            if (id.length()!=18){
                System.out.println("身份证格式错误，请重新输入");
                return false;
            }
            if (id.charAt(0)=='0'){
                System.out.println("身份证格式错误，请重新输入");
                return false;
            }
            for (int i = 0; i < id.length()-1; i++) {
                if (!(id.charAt(i)>='0'&&id.charAt(i)<='9')){
                    System.out.println("身份证格式错误，请重新输入");
                    return false;
                }
            }
                int index = id.length()-1;
            if ((id.charAt(index)>='0'&&id.charAt(index)<='9')||id.charAt(index)=='X'||id.charAt(index)=='x'){
                return true;
            }else {
                return false;
            }


        }

        public  static  String judgePhoneNumber(){
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("请输入手机号：");
                String num = sc.next();
                boolean flag = judgePhone(num);

                if (!flag){
                    System.out.println("手机号码输入有误，请重新输入：");
                    continue;
                }



                return num;
            }


        }

        public static boolean judgePhone(String num){
            if (num.length()!=11){
                return false;
            }
            if (num.charAt(0)=='0'){
                return false;
            }
            for (int i = 0; i < num.length(); i++) {
                if (!(num.charAt(i)>='0'&&num.charAt(i)<='9')){
                    return false;
                }
            }
            return true;
        }

        //忘记密码
        public static void forgetPassword(){
            Scanner sc = new Scanner(System.in);
        }

        //验证码
        public static void captcha(){
            Scanner sc = new Scanner(System.in);
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

            while (true) {
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
                String str1= strB.toString();
                System.out.println("验证码是：" + str1);

                System.out.println("请输入验证码：");
                String str2= sc.next();
                if (!str2.equalsIgnoreCase(str1)){
                    System.out.println("验证码输入错误，请重新输入");
                    continue;
                }else {
                    System.out.println("验证码输入正确");
                    break;
                }
            }

        }







    }

