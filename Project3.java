/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
//package project3;


import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;
import java.util.Queue;
import java.util.Iterator;

/**
 *
 * @author Timot
 */

//SCHEDULING ALGORITHM ARE: ROUND-ROBIN (RR), SHORTEST REMAINING TIME (SRT), FEEDBACK (FB)
//COLUMN 1: JOB NAMES ----- COLUMN 2: ARRIVAL TIME --------- COLUMN 3: DURATION/SERVICE TIME

public class Project3{

    public static void main(String[] args){
       List<Job> q = new LinkedList<>();
       
        
        Scanner obj1 = new Scanner(System.in);
        System.out.println("Please choose a scheduler: ");
        System.out.println("1.) Round-Robin: ");
        System.out.println("2.) Shortest-Remaining Time: ");
        System.out.println("3.) Feedback: ");
        System.out.println("4.) All of the Above: ");        
        
         
        System.out.print("Input: ");
        
   
        
        int num = Integer.parseInt(obj1.nextLine());
        
            System.out.println("\nYou chose: " + num);
       
             try{    
       File file = new File("jobs.txt");
         Scanner obj = new Scanner(file);
         StringBuffer sb = new StringBuffer();
         
         
         
         while(obj.hasNext()) {
                  
         String str = obj.nextLine();
         sb.append("\n" + str);
         String [] arr = str.split("\\t+");
         
         Job job = new Job(arr[0].charAt(0), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
         
         q.add(job);
         
      }
         System.out.println(sb);
                    
}
        catch(Exception e){
            System.out.println("Error");
        }
             
        if(num == 1){
        RR(q);
        }
 
        else if(num == 2){
        SRT(q);
        }
        
        else if(num == 3){
        FB(q);
        }
        
        else if(num == 4){
        RR(q);
        SRT(q);
        FB(q);
        } 
        else
            System.out.println("Number not valid");
    }
    
    
    

    public static void RR(List<Job> q){
        //Where you store A-E inside of the q1
        Queue<Job> q1 = new LinkedList<>();
    
       int time = 0;
        System.out.println();
        //for loop to setup the job names
        for(int name = 0; name < q.size(); name++){
        System.out.print(q.get(name).job_name);
        }
        System.out.println();
       //copying q and storing the q values into q_copy
       List<Job> q_copy = new LinkedList<>();
       q_copy.addAll(q);
     
       while(!(q1.isEmpty() && q_copy.isEmpty())){
        Iterator<Job> it = q_copy.iterator();
        while(it.hasNext())
        {
            Job x = it.next();
            if(time >= x.arr_time)
            {
                q1.add(x);
		
                it.remove();
            }
        }
           
         //if q1 is not empty  
         if(!q1.isEmpty()){
              
             //dequeue the first process from the queue
             Job holder = q1.poll(); //->q1.poll is pulling the first jobname out of q1 into holder
             //loop used to place the X on the correct jobname
             for(int i = 0; i < holder.job_name - 65; i++){
                 System.out.print(" ");
                 
             }
             System.out.println("X");
             //decrementing the duration time from the storage once x is placed
             holder.dur_time--;
             
        time++;
             
        it = q_copy.iterator();
        while(it.hasNext())
        {
            Job x = it.next();
            if(time >= x.arr_time)
            {
                q1.add(x);
		
                it.remove();
            }
        }
             //if the duration time is greater than 0 
             if(holder.dur_time > 0){
                 q1.add(holder); //add the holders values of q1
		
             }
            
         }      

    }     
      
}
        
        
    public static void SRT(List<Job> q){
        Queue<Job> q2 = new LinkedList<>(); //shortest remaining time queue
        
    }
    
    public static void FB(List<Job> q){
        
    }
} 
//Job is a process
class Job{
    char job_name;
    int arr_time;
    int dur_time;
    
    Job(){
        
    }
    Job(char job_name, int arr_time, int dur_time){
        this.job_name = job_name;
        this.arr_time = arr_time;
        this.dur_time = dur_time;
    }
    
    @Override
    public Job clone(){        
        return new Job(job_name, arr_time, dur_time);
    }
}