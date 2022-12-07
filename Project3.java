/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
//package project3_;

import java.io.File;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;
import java.util.Queue;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author Timot
 */
//SCHEDULING ALGORITHM ARE: ROUND-ROBIN (RR), SHORTEST REMAINING TIME (SRT), FEEDBACK (FB)
//COLUMN 1: JOB NAMES ----- COLUMN 2: ARRIVAL TIME --------- COLUMN 3: DURATION/SERVICE TIME
public class Project3{

    public static void main(String[] args) {
        List<Job> q = new LinkedList<>();


        String alg = args[0];
        

        System.out.println("\nYou chose: " + alg);

        try {
            File file = new File("jobs.txt");
            Scanner obj = new Scanner(file);
            StringBuffer sb = new StringBuffer();

            while (obj.hasNext()) {

                String str = obj.nextLine();
                sb.append("\n" + str);
                String[] arr = str.split("\\t+");

                Job job = new Job(arr[0].charAt(0), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));

                q.add(job);

            }
            System.out.println(sb);

        } catch (Exception e) {
            System.out.println("Error");
        }

        if (alg.equals("RR")) {
            RR(q);
        } else if (alg.equals("SRT")) {
            SRT(q);
        } else if (alg.equals("FB")) {
            FB(q);
        } else if (alg.equals("ALL")) {
            RR(q);
            SRT(q);
            FB(q);
        } else {
            System.out.println("NOT A VALID JOB TYPE");
        }
    }

    public static void RR(List<Job> q) {
        System.out.println("Round Robin");
        //Where you store A-E inside of the q1
        Queue<Job> q1 = new LinkedList<>();

        int time = 0;
        System.out.println();
        //for loop to setup the job names
        for (int name = 0; name < q.size(); name++) {
            System.out.print(q.get(name).job_name + " ");
        }
        System.out.println();

        for (Job j : q) {
            j.reset();
        }

        //copying q and storing the q values into q_copy
        List<Job> q_copy = new LinkedList<>();
        q_copy.addAll(q);

        while (!(q1.isEmpty() && q_copy.isEmpty())) {
            Iterator<Job> it = q_copy.iterator();
            while (it.hasNext()) {
                Job x = it.next();
                if (time >= x.arr_time) {
                    q1.add(x);

                    it.remove();
                }
            }

            //if q1 is not empty  
            if (!q1.isEmpty()) {

                //dequeue the first process from the queue
                Job holder = q1.poll(); //->q1.poll is pulling the first jobname out of q1 into holder
                //loop used to place the X on the correct jobname
                for (int i = 0; i < holder.job_name - 65; i++) {
                    System.out.print("  ");

                }
                System.out.println("X");
                //decrementing the duration time from the storage once x is placed
                holder.dur_time--;
                time++;
                

                it = q_copy.iterator();
                while (it.hasNext()) {
                    Job x = it.next();
                    if (time >= x.arr_time) {
                        q1.add(x);

                        it.remove();
                    }
                }
                //if the duration time is greater than 0 
                if (holder.dur_time > 0) {
                    q1.add(holder); //add the holders values of q1

                }
                

            }
            else{
                System.out.println();
                time++;
            }
           
        }

    }

    public static void SRT(List<Job> q) {
        System.out.println("\n------------------------------");
        System.out.println("Shortest Remaining Time");
        int time = 0;

        Queue<Job> q2 = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Job a, Job b) {
                //Comparing all of the job names to see their duration times
                if (a.dur_time > b.dur_time) {
                    return 1;
                } else if (a.dur_time == b.dur_time) {
                    return a.job_name - b.job_name;
                } else {
                    return -1;
                }
            }
        });
        System.out.println();

        //for loop to setup the job names
        for (int name = 0; name < q.size(); name++) {
            System.out.print(q.get(name).job_name + " ");
        }

        System.out.println();
        for (Job j : q) {
            j.reset();
        }
        List<Job> q_copy = new LinkedList<>();
        q_copy.addAll(q);

        while (!(q2.isEmpty() && q_copy.isEmpty())) {
            Iterator<Job> it = q_copy.iterator();
            while (it.hasNext()) {
                Job x = it.next();
                if (time >= x.arr_time) {
                    q2.add(x);
                    it.remove();
                }
            }

            if (!q2.isEmpty()) {
                Job holder = q2.poll();
                //loop used to place the X on the correct jobname
                for (int i = 0; i < holder.job_name - 65; i++) {
                    System.out.print("  ");

                }
                System.out.println("X");

                holder.dur_time--;
                

                //if the duration time is greater than 0 
                if (holder.dur_time > 0) {
                    q2.add(holder); //add the holders values of q1

                }

            }
                else{
                System.out.println();
            }
            time++;

        }

    }

    public static void FB(List<Job> q) {
        System.out.println("\n------------------------------");
        System.out.println("FeedBack");
        //Where you store A-E inside of the q1
        List<Job> q3 = new LinkedList<>();

        int time = 0;
        System.out.println();
        //for loop to setup the job names
        for (int name = 0; name < q.size(); name++) {
            System.out.print(q.get(name).job_name + " ");
        }
        System.out.println();

        for (Job j : q) {
            j.reset();
        }

        //copying q and storing the q values into q_copy
        List<Job> q_copy = new LinkedList<>();
        q_copy.addAll(q);

        while (!(q3.isEmpty() && q_copy.isEmpty())) {
            Iterator<Job> it = q_copy.iterator();
            while (it.hasNext()) {
                Job x = it.next();
                if (time >= x.arr_time) {
                    q3.add(x);

                    it.remove();
                }
            }

            //if q1 is not empty  
            if (!q3.isEmpty()) {

                Job low = q3.get(0);
                for (int i = 1; i <= q3.size() - 1; i++) {

                    if (low.depth > q3.get(i).depth) {
                        low = q3.get(i);
                    }

                }
                q3.remove(low);

                //loop used to place the X on the correct jobname
                for (int i = 0; i < low.job_name - 65; i++) {
                    System.out.print("  ");

                }
                System.out.println("X");
                //decrementing the duration time from the storage once x is placed
                low.dur_time--;
                //if the duration time is greater than 0 
                if (low.dur_time > 0) {
                    boolean arriving = false;
                    Iterator<Job> iti = q_copy.iterator();
                    while (iti.hasNext()) {
                        Job x = iti.next();
                        if (time + 1 >= x.arr_time) {
                            arriving = true;
                        }
                    }

                    if (q3.size() >= 1 || arriving) {
                        low.depth++;
                    }
                    q3.add(low); //add the holders values of q1

                }

            }
             else{
                System.out.println();
            }
    time++; 

        }
    }
}

//Job is a process
class Job {

    char job_name;
    int arr_time;
    int dur_time;
    int original_time;
    int depth = 0;

    Job() {

    }

    Job(char job_name, int arr_time, int dur_time) {
        this.job_name = job_name;
        this.arr_time = arr_time;
        this.dur_time = this.original_time = dur_time;

    }

    public void reset() {
        dur_time = original_time;
    }

}

