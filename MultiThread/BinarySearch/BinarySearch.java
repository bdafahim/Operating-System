/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinarySearch;

import java.util.Scanner;

/**
 *
 * @author bdafahim
 */
public class BinarySearch {
    boolean flagInput = true;
    int arr[] = new int[10];
     int n;
      Scanner sc = new Scanner(System.in);

    public BinarySearch() {
    }
      
     void acquire() {
        while (!flagInput);
        flagInput = false;
    }

    void release() {
        flagInput = true;
    }
    class Input implements Runnable{

        Thread t;

        public Input() {
            t = new Thread(this);
            t.start();
        }
        
        @Override
        public void run() {
           acquire();
          
            System.out.println("Input n: ");
           
            n = sc.nextInt();
            System.out.println("Input numbers in sorted order");
            for(int i=0;i<n;i++)
                arr[i] = sc.nextInt();
            System.out.println("Input Done");
            release();
        }
        
    }
    class Search implements Runnable{
        Thread t;
        public Search(){
            t = new Thread(this);     
        }
        @Override
        public void run(){
            acquire();
            System.out.println("Input Number");
            int s=5;
            int b = 0,e = n;
            while(b<e){
                int mid = (b+e)/2;
                if(arr[mid]==s){
                    System.out.println("Found");
                    break;
                }
                if(arr[mid]<s){
                    b = mid;
                }
                else e = mid;
            }
            release();
        }
    }
    
    public static void main(String[] args) {
        BinarySearch bin = new BinarySearch();

        Input i = bin.new Input();
        try {
            i.t.join();
        } catch (Exception e) {
        }
        Search s = bin.new Search();
        s.t.start();
    }
    
}

