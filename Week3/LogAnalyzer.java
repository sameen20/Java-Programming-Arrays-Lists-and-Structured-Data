
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     private ArrayList<Integer> myFreqs;
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
         myFreqs = new ArrayList<Integer>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String record: fr.lines()) {
             records.add(WebLogParser.parseEntry(record));
             
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le: records) {
             String ipAddr = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddr)) {
                 uniqueIPs.add(ipAddr);
             }
         }
         
         return uniqueIPs.size();
     }
     
     public void printAllHigerThanNum(int num) {
         for (LogEntry le: records) {
             int statusCode = le.getStatusCode();
             if (statusCode > num) {
                 System.out.println(le);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le: records) {
             Date time = le.getAccessTime();
             String stringTime = time.toString();
             String ipAddr = le.getIpAddress();
             if (stringTime.substring(4, 10).equals(someday) && !uniqueIPs.contains(ipAddr)) {
                 
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le: records) {
             String ipAddr = le.getIpAddress();
             int statusCode = le.getStatusCode();
             if (statusCode >= low && statusCode <= high && !uniqueIPs.contains(ipAddr)) {
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> countsOfPerIP = new HashMap<String, Integer>();
         for (LogEntry le: records) {
             String ipAddr = le.getIpAddress();
             if (!countsOfPerIP.containsKey(ipAddr)) {
                 countsOfPerIP.put(ipAddr,1);
             }
             else {
                 countsOfPerIP.put(ipAddr,countsOfPerIP.get(ipAddr)+1);
             }
         }
         return countsOfPerIP;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> countVisitsPerIP) {
         int max = 0;
         for (int num: countVisitsPerIP.values()) {
             
             if (num > max) {
                 max = num;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> countVisitsPerIP) {
         ArrayList<String> ip = new ArrayList<String>();
         int max = mostNumberVisitsByIP(countVisitsPerIP);
         for (String s: countVisitsPerIP.keySet()) {
             int value = countVisitsPerIP.get(s);
             if (value == max) {
                 ip.add(s);
             }
         }
         return ip;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
         ArrayList<String> ip = new ArrayList<String>();
         String date = "";
         for (LogEntry le: records) {
             Date time = le.getAccessTime();
             String stringTime = time.toString();
             date = stringTime.substring(4, 10);
             String ipAddr = le.getIpAddress();
             ip = uniqueIPVisitsOnDay(date);
             
             //ip.add(ipAddr);
             //map.put(date, ip);
             if (!map.containsKey(date)) {
                 //ip.add(ipAddr);
                 map.put(date, ip);
             }
             //else {
             //    ip = map.get(date);
             //    if (!ip.contains(ipAddr)) {
             //        ip.add(ipAddr);
             //    }
             //}
         }
         return map;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDays) {
         int max = 0;
         for (ArrayList s: iPsForDays.values()) {
             if (max < s.size()) {
                 max = s.size();
             }
         }
         for (String s: iPsForDays.keySet()) {
             ArrayList ips = iPsForDays.get(s);
             if (max == ips.size()) {
                 return s;
             }
         }
         return null;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDays, String date) {
         //System.out.println("The " + date + ", this IPs visited our website: ");
         myFreqs.clear();
         ArrayList<String> mostIPs = new ArrayList<String>();
         mostIPs = uniqueIPVisitsOnDay(date);
         HashMap<String,Integer> counts = new HashMap<String,Integer>();      
        
         for (int k=0;k<mostIPs.size();k++) {
            String s = mostIPs.get(k) ; 
            if (!counts.containsKey(s)) {
                 counts.put(s,1);
             }
             else {
                 int freq = counts.get(s);
                 counts.put(s,freq+1);
             }
         }
         return iPsMostVisits(counts);
     
     }
}
