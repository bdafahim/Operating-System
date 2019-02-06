/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RaceCondition;

/**
 *
 * @author bdafahim
 */
public class Race_Condition {
    boolean available;
    int x;

    public Race_Condition() {
        available = true;
        x = 5;
    }
    
    void acquire()
    {
        while(!available)
            available = true;
    }
    
    void release()
    {
        available = false;
    }
    
    class FirstClass implements Runnable{
        Thread t;

        public FirstClass() {
            t = new Thread();
            t.setName("First Thread");
            t.start();
        }
        

        @Override
        public void run() {
            acquire();
            x++;
            System.out.println(t.getName()+"Updated Value of x "+x);
            release();
        }
    }
    
    class SecondClass implements Runnable{
        Thread t;

        public SecondClass() {
            t = new Thread();
            t.setName("Second thread");
            t.start();
        }

        @Override
        public void run() {
            acquire();
            x--;
            System.out.println(t.getName()+"udated value2 of x "+x);
            release();
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        Race_Condition  rc = new Race_Condition();
        FirstClass f = rc.new FirstClass();
        SecondClass s = rc.new SecondClass();
        
        for(int i=0;i<10;i++)
        {
            s.run();
            f.run();
        }
        
        s.t.join();
        f.t.join();
    }
}
