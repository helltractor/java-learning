package com.helltractor.demo.accessModifier.two;

import com.helltractor.demo.accessModifier.one.FatherPublic;

public class Son extends FatherPublic {
    
    void print() {
//        super.print();
        System.out.println("a son!");
    }
    
    public static void main(String[] args) {
        Son son = new Son();
        son.print();
        System.out.println(son.asset);
//        System.out.println(son.wifeNum);
    }
}
