import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        ArrayList<User> userList = new ArrayList<>();
        User user1 = new User("diy1iren", "123aaa", "370282199106053214", "13612586327");
        userList.add(user1);
        Scanner sc = new Scanner(System.in);

        while (true) {
            logInMenu();
            int choose = sc.nextInt();
            switch (choose){
                case 1 -> login();
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



        public static void login(){}

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

        public static void forgetPassword(){}

        public static void captcha(){}




    }

