/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timot
 */

//SCHEDULING ALGORITHM ARE: ROUND-ROBIN (RR), SHORTEST REMAINING TIME (SRT), FEEDBACK (FB)
//COLUMN 1: JOB NAMES ----- COLUMN 2: ARRIVAL TIME --------- COLUMN 3: DURATION/SERVICE TIME

       public class Project3 {

    public static void main(String[] args){
        List<Character> job_name = new ArrayList<>();
        List<Integer> arr_time = new ArrayList<>();
        List<Integer> dur_time = new ArrayList<>();
        try{    
        File file = new File("C:\\Users\\Timot\\Downloads\\2022 Fall\\CS 4348 - Operating Systems Concepts\\Projects\\Project3\\jobs.txt");
                
            BufferedReader  b_reader = new BufferedReader(new FileReader(file));
            
            String st;
            
            //Adding the job names to the List
            job_name.add('A');
            job_name.add('B');
            job_name.add('C');
            job_name.add('D');
            job_name.add('E');
            
            
            
            while((st = b_reader.readLine()) != null)
 
            // Print the string
            System.out.println(st);
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }
    
    public void RR(String str){
        
    }
    
    public void SRT(String str){
        
    }
    
    public void FB(String str){
        
    }
    
    
   
}
    
