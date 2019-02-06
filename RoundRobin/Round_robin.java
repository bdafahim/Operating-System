/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RoundRobin;

/**
 *
 * @author bdafahim
 */
public class Round_robin {
    private int limit;
    private int burstTime[];
    private int CurrentTime = 0;
    private int TurnAroundTime[];

    public Round_robin(int limit,int[] burstTime,int quantum) {
        this.limit = limit;
        this.burstTime = burstTime;
        TurnAroundTime = new int[limit];
        Update(quantum);
        print();
        
        
    }
    
    private void Update(int quantum){
        
        int processes = limit;
        int[] remainingTime = burstTime.clone();
        
        for(int id=0; processes>0; id++)
        {
            id %= limit;
            if(remainingTime[id]<=0) continue;
            else if(remainingTime[id]>quantum)
            {
                CurrentTime += quantum;
                TurnAroundTime[id] = CurrentTime;
                remainingTime[id] -= quantum;
            }
            
            else
            {
                CurrentTime += remainingTime[id];
                TurnAroundTime[id] = CurrentTime;
                remainingTime[id] = 0;
                processes--;
            }
        }
    }
    
    private void print(){
        
        System.out.println("BT\tTAT\tWT");
        
        for(int i=0;i<limit;i++)
        {
            System.out.println(burstTime[i]+"\t"+TurnAroundTime[i]+"\t"+(TurnAroundTime[i]-burstTime[i]));
        }
    }
    
    public static void main(String[] args) {
        int limit = 3;
        int BurstTime[] = {10,5,3};
        int quantum = 2;
        
         new Round_robin(limit, BurstTime, quantum);
    }
            
}
