
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testcountUniqueIPs() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("counts of unique ip are: " + uniqueIPs);
    }
    
    public void testAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAllHigerThanNum(400);
    }
    
    public void testuniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> a = la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println(a);
        System.out.println(a.size());
        //ArrayList<String> b = la.uniqueIPVisitsOnDay("Sep 30");
        //System.out.println(b);
    }
    
    public void testcountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        //int counts1 = la.countUniqueIPsInRange(200,299);
        //System.out.println("counts of unique ips in range: " + counts1);
        int counts2 = la.countUniqueIPsInRange(400,499);
        System.out.println("counts of unique ips in range: " + counts2);
    }
    
    public void testcountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> map = la.countVisitsPerIP();
        System.out.println(map);
    }
    
    public void testmostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> map = la.countVisitsPerIP();
        int max = la.mostNumberVisitsByIP(map);
        System.out.println("most number visits by ip: " + max);
    }
    
    public void testiPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> map = la.countVisitsPerIP();
        ArrayList<String> ip = la.iPsMostVisits(map);
        System.out.println(ip);
    }
    
    public void testiPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        System.out.println(map);
    }
    
    public void testdayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        String date = la.dayWithMostIPVisits(map);
        System.out.println(date);
    }
    
    public void testiPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> map = la.iPsForDays();
        ArrayList<String> ip = la.iPsWithMostVisitsOnDay(map, "Sep 29");
        System.out.println(ip);
    }
}
