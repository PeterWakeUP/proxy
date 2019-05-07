package com.example;

public class Main {

    public static void main(String[] args) {
        try{
            Work work = (Work) new CglibTrans().getInstance(Work.class);
            work.doWork();
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
            work.say();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
