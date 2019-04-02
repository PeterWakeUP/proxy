package com.example;

public class Main {

    public static void main(String[] args) {
        try{
            Work work = (Work) new CglibTrans().getInstance(Work.class);
            //Work work = new Work();
            work.doWork();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
