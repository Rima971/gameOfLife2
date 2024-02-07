package org.game;

import java.util.Scanner;

public class IO {
    public final Scanner scanner = new Scanner(System.in);
    public void printLine(String string){
        System.out.println(string);
    }

    public void clearScreenLinux(){
        System.out.print("\033\143");
    }
}
