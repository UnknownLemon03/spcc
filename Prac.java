import java.util.*;
import java.io.*;


public class Prac {
    
    static char[] stack = new char[30];
    static char[] input = new char[30];
    static int c;
      static String act = "SHIFT->";
    public static void main(String [] args){
    System.out.println("Rules E -> E + E \n E -> E * E \n E -> (id) \n E -> id");
      System.out.println("Enter the String");
      String line = (new Scanner(System.in)).nextLine();
     System.out.println("stack\tinput\taction");
      input = line.toCharArray();
      c = input.length;
      for(int i = 0,j = 0,l = 0 ;i < c;l ++ , i ++ , j ++){
          if( i+1 < c && input[i] == 'i' && input[i+1] == 'd'){
              stack[j] = input[i];
              stack[j+1] = input[i+1];
              stack[j+2] = '\0';
              input[i] = ' ';
              input[i+1] = ' ';
              i++;
              print("id");
              check();
          }else{
              stack[j] = input[i];
               stack[j+1] = '\0';
              input[i] = ' ';
              print(stack[j]+"");
              check();
          }
      }
    }
    
    static void check(){
        String ph = "Reduce to E";
        
        for(int i = 0 ; i < c ; i++){
            if(stack[i] == 'i' && stack[i+1] == 'd'){
              stack[i] = 'E';
              stack[i+1] = '\0';
              reduce(ph);
            }
        }
        
        for(int i = 0 ; i < c ; i++){
            if(stack[i] == '(' && stack[i+1] == 'i' && stack[i+2] == 'd' && stack[i+3] == ')'){
              stack[i] = 'E';
              stack[i+1] = '\0';
              stack[i+2] = '\0';
              stack[i+3] = '\0';
              print(ph);
            }
        }
        
       for(int i = 0 ; i < c ; i++){
            if(stack[i] == 'E' && stack[i+1] == '*' && stack[i+2] == 'E'){
              stack[i] = 'E';
              stack[i+1] = '\0';
              stack[i+2] = '\0';
              print(ph);
            }
        }
        
        for(int i = 0 ; i < c ; i++){
            if(stack[i] == 'E' && stack[i+1] == '+' && stack[i+2] == 'E'){
              stack[i] = 'E';
              stack[i+1] = '\0';
              stack[i+2] = '\0';
              print(ph);
            }
        }
    }
    static void print(String st){
      System.out.printf("$%s %s$ %s %s\n",
        new String(stack).replace("\0"," ").trim(),
        new String(input).replace(" "," ").trim(),
        act,
        st
      );
    }
    static void reduce(String st){
      System.out.printf("$%s %s$ %s %s\n",
        new String(stack).replace("\0"," ").trim(),
        new String(input).replace(" "," ").trim(),
        act,
        st
      );
    }
}
