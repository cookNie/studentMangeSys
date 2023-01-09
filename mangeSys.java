import java.util.ArrayList;
import java.util.Scanner;

public class mangeSys {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);


       loop: while (true){
            menu();
            int num = sc.nextInt();

                switch (num){
                    case 1 ->add(input(),list);
                    case 2 ->remove(list);
                    case 3->change(list);
                    case 4 ->query(list);
                    case 5 -> {
                        System.out.println("系统已退出");
                        break loop;
                    }
                    default -> System.out.println("输入错误，请重新输入");

            }


        }
    }

    public static void menu(){
        System.out.println("-----------欢迎来到学生管理系统-----------");
        System.out.println("1.添加学生信息");
        System.out.println("2.删除学生");
        System.out.println("3.修改学生");
        System.out.println("4.查询学生");
        System.out.println("5.退出");
        System.out.println("请输入您的选择：");
    }

    public static Student input(){
        Student stu = new Student();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生id：");
        stu.setId(sc.next());
        System.out.println("请输入学生姓名：");
        stu.setName(sc.next());
        System.out.println("请输入学生年龄：");
        stu.setAge(sc.nextInt());
        System.out.println("请输入学生家庭地址：");
        stu.setAddress(sc.next());
        return stu;
    }

    public static void add(Student stu,ArrayList<Student> list){
        if (list.size()==0){
            list.add(stu);
        }else {
            for (int i = 0; i < list.size(); i++) {
                if (stu.getId().equals(list.get(i).getId())){
                    System.out.println("id重复，请重新输入");
                    break;
                }
            }
            list.add(stu);
        }


    }

    public static void remove(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的id：");
        String id = sc.next();
        boolean flag = true;
        if (list.size()==0){
            System.out.println("不存在学生信息，请先添加");
        }else {
            for (int i = 0; i < list.size(); i++) {
                if (id.equals(list.get(i).getId())){
                    list.remove(i);
                    System.out.println("删除成功");
                    flag = true;
                    break;
                }
                flag = false;
            }

            if (!flag){System.out.println("id不存在，请重新输入");}

        }
    }

    public static void change(ArrayList<Student> list){
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        System.out.println("请输入学生id：");
        String id = sc.next();
        if (list.size() ==0){
            System.out.println("学生信息不存在，请先录入学生信息");
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (id.equals(list.get(i).getId())){
                    System.out.println("请输入学生姓名：");
                    list.get(i).setName(sc.next());
                    System.out.println("请输入学生年龄：");
                    list.get(i).setAge(sc.nextInt());
                    System.out.println("请输入学生家庭地址：");
                    list.get(i).setAddress(sc.next());
                    break;
                }
                flag = false;
            }
            if (!flag){
                System.out.println("id不存在，请重新输入");
            }

        }


    }

    public static void query(ArrayList<Student> list){
        if (list.size()==0){
            System.out.println("当前无学生信息，请添加后再查询");
        }else {
            System.out.println("id"+"\t姓名"+"\t年龄"+"\t家庭住址");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getId()+"\t"+list.get(i).getName()+"\t"+list.get(i).getAge()+"\t"+list.get(i).getAddress());
            }
        }

    }




}
