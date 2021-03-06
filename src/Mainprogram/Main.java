package Mainprogram;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import indi.individual;
import sprint1.*;
import sprint2.*;
import sprint3.*;
import sprint4.*;
import fam.Family;
import dnl.utils.text.table.*;
import Mainprogram.output_format;

public class Main {
	
	public static ArrayList<individual> allPeople = new ArrayList<individual>();
	public static ArrayList<Family> allFamilies = new ArrayList<Family>();
	
    public static void main(String[] args) throws IOException {
        Testing("MyFamily.ged");
        updatemerrage(allPeople , allFamilies);
        //checkMarBefDeath(allPeople, allFamilies);
        //Testing("proj02test.ged");
        
        String[] indiTitle = { 
        		"ID", 
        		"Name", 
        		"Gender", 
        		"Birthday", 
        		"Age",
        		"Alive",
        		"Death",
        		"Child",
        		"Spouse"};
        
        Object[][] indiData = new String[allPeople.size()][9];
        	for (int i = 0; i < allPeople.size(); i++) {
            indiData[i][0] = allPeople.get(i).id;
            indiData[i][1] = allPeople.get(i).name;
            indiData[i][2] = allPeople.get(i).sex;
            indiData[i][3] = allPeople.get(i).Birthday;
        	indiData[i][4] = Integer.toString(allPeople.get(i).age);
        	indiData[i][5] = Boolean.toString(!allPeople.get(i).isdead);
        	indiData[i][6] = allPeople.get(i).Deathday;
        	indiData[i][7] = allPeople.get(i).children.toString();
        	indiData[i][8] = allPeople.get(i).spouse.toString();
        	
        	for (int j = 0; j < 9; j++) {
        		if (indiData[i][j] == "") {
        			indiData[i][j] = "NA";
        		}
        	}
        }
        System.out.println("Individuals");
        TextTable indiTable = new TextTable(indiTitle, indiData);
        PrintStream writeTable = new PrintStream(new File("Table.txt"));
        writeTable.append("Individuals").append("\n");
        indiTable.setSort(0); 
        indiTable.printTable(writeTable, 0);
        indiTable.printTable();

        String[] famTitle = { 
        		"ID", 
        		"Married", 
        		"Divorced", 
        		"Husband ID", 
        		"Husband Name",
        		"Wife ID",
        		"Wife Name",
        		"Children"};
        
        Object[][] famData = new String[allFamilies.size()][8];
        	for (int i = 0; i < allFamilies.size(); i++) {
            famData[i][0] = allFamilies.get(i).familyID;
            famData[i][1] = allFamilies.get(i).marrDate;
        	famData[i][2] = allFamilies.get(i).divoDate;
        	famData[i][3] = allFamilies.get(i).husband;
        	famData[i][4] = allPeople.get(searchbyId(allPeople , allFamilies.get(i).husband)).name;
        	famData[i][5] = allFamilies.get(i).wife;
        	famData[i][6] = allPeople.get(searchbyId(allPeople , allFamilies.get(i).wife)).name;
        	famData[i][7] = allFamilies.get(i).children.toString();
        	
        	for (int j = 0; j < 8; j++) {
        		if (famData[i][j] == "") {
        			famData[i][j] = "NA";
        		}
        	}
        }

        System.out.println("\n");
        System.out.println("Families");
        TextTable famTable = new TextTable(famTitle, famData); 
        writeTable.append("\n").append("Families").append("\n");
        famTable.setSort(0); 
        famTable.printTable(writeTable, 0);
        famTable.printTable();
        
        //Debug messages print out
        System.out.println();
        System.out.println("--------------------------------------------------------------");
        System.out.println("Sprint 1");
        System.out.println("--------------------------------------------------------------");
        
        US02.birth_before_marriage(allPeople,allFamilies);
        System.out.println("-----------------------------"); 
        
        US03.birth_before_death(allPeople);
        System.out.println("-----------------------------");  
        
        US04 us04 = new US04();
        us04.Marriage_Before_Divorce(allFamilies);
        System.out.println("--------------------------------------------------------------");
        US05 us05 = new US05();
        us05.Marriage_Before_Death(allPeople, allFamilies);
        System.out.println("-----------------------------");  
        
        US06 us06 = new US06();
        us06.Divorce_Before_Death(allPeople, allFamilies);
        System.out.println("-----------------------------");  
        
        US08 us08 = new US08();
        us08.Birth_Before_Marriage_Of_Parents(allPeople, allFamilies);
        System.out.println();
        System.out.println("--------------------------------------------------------------");
        
        System.out.println("Sprint 2");
        System.out.println("--------------------------------------------------------------");  
                
        US09 us09 = new US09();
        us09.Birth_before_death_of_parents(allPeople, allFamilies);
        System.out.println("-----------------------------"); 
        
        US10 us10 = new US10();
        us10.Marriage_After_14(allPeople, allFamilies);
        System.out.println("-----------------------------"); 
        
        US11.NoBigamy(allPeople,allFamilies);
        System.out.println("-----------------------------");  
        
        US12 us12 = new US12();
        us12.checkParentsAgeAll(allPeople, allFamilies);
        System.out.println("-----------------------------"); 
                
        US13 us13 = new US13();
        us13.sibiling_space(allPeople, allFamilies);
        System.out.println("-----------------------------"); 
        
        US14 us14 = new US14();
        us14.Multiple_Births_Smaller_5(allPeople, allFamilies);
        System.out.println("-----------------------------"); 
                
        US15.sibilings(allFamilies);
        System.out.println("-----------------------------"); 
        
        US16 us16 = new US16();
        us16.checkMaleNameAll(allPeople, allFamilies);
        System.out.println("-----------------------------"); 

        System.out.println();
        System.out.println("Sprint 3");
        System.out.println("--------------------------------------------------------------"); 
        
        US17 us17 = new US17();
        us17.us17Func(allFamilies);
        System.out.println("--------------------------------------------------------------"); 
        
        US18 us18 = new US18();
        us18.us18Func(allPeople, allFamilies);
        System.out.println("-----------------------------"); 
        
        US21.CorrectGender(allPeople,allFamilies);
        System.out.println("-----------------------------"); 
        
        US22.UniqueId(allPeople,allFamilies);
        System.out.println("-----------------------------"); 
        
        US23 us23 = new US23();
        us23.Unique_name_and_birth_date(allPeople);
        System.out.println("-----------------------------"); 
        
        US24 us24 = new US24();
        us24.Unique_families_by_spouses(allFamilies);
        System.out.println("-----------------------------"); 
        
        US25.unique_first_name(allFamilies, allPeople);
        System.out.println("-----------------------------"); 
        
        US27.age_calculate(allPeople);
        System.out.println("-----------------------------"); 
        
        System.out.println();
        System.out.println("Sprint 4");
        System.out.println("--------------------------------------------------------------"); 
        
        US28.Order_siblings(allPeople, allFamilies);
        System.out.println("-----------------------------");
        
        us29.list_deceased(allPeople);
        System.out.println("-----------------------------"); 
        
        US30 us30 = new US30();
        us30.us30Func(allPeople);
        System.out.println("-----------------------------"); 
        
        US31 us31 = new US31();
        us31.us31Func(allPeople);
        System.out.println("-----------------------------");
        
        US32 us32 = new US32();
        us32.List_multiple_births(allPeople, allFamilies);
        System.out.println("-----------------------------");
        
        US33 us33 = new US33();
        us33.List_orphans(allPeople, allFamilies);
        System.out.println("-----------------------------");
        
        US34.large_differences(allPeople,allFamilies);
        System.out.println("-----------------------------");
        
        us35.list_recent_birth(allPeople);
        System.out.println("-----------------------------"); 
    }
    
    public static output_format main_output() throws IOException{
        Testing("MyFamily.ged");
        updatemerrage(allPeople , allFamilies);
        output_format out = new output_format(allPeople , allFamilies);
        return out;
    }
   
    
    public static void Testing(String pathname)throws IOException{
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = " ";
        //add something to save tags
        individual person = null;
        Family family = null;
        String[] tags = new String[3];
        while (line != null) {
            line = br.readLine();
            if(line == null) break;

            String[] temp = line.split(" ");
            String Tag;
            if(temp[temp.length-1].equals("INDI") || temp[temp.length-1].equals("FAM"))
                Tag = temp[temp.length-1];
            else Tag = temp[1];

            // LS: add to object
            switch (temp[0]){
                case "0":
                    tags[0] = Tag;
                    break;
                case "1":
                    tags[1] = Tag;
                    break;
                case "2":
                    tags[2] = Tag;
                    break;
                default:
                	break;
            }
            StringBuffer content = new StringBuffer();
            if(Tag.equals("INDI") || Tag.equals("FAM")) {
            	for(int x = 1 ; x < temp.length-1 ; x++) {
            		content.append(temp[x] + " ");
            	}
            }
            else {
            	for(int x = 2 ; x < temp.length ; x++) {
            		content.append(temp[x] + " ");
            	}
            }

            
            switch (temp[0]){
                case "0":
                    if(Tag.equals("INDI")){
                        if(person != null) allPeople.add(person);
                        person = new individual(content.toString());
                    }
                    
                    //LS: missing {} after else if here 
                    else if(Tag.equals("FAM")) {
                        if(family != null) allFamilies.add(family);
                        family = new Family(content.toString());
                    }
                    break;
                case "1":
                    if(tags[0].equals("INDI")) {
                        if(!Tag.equals("BIRT") && !Tag.equals("DEAT"))
                            person.update(Tag , content.toString());
                    }

                    else if(tags[0].equals("FAM")){
                        if(!Tag.equals("MARR") && !Tag.equals("DIV"))
                        	family.update(Tag , content.toString());
                    }
                    break;
                case "2":
                    if(tags[0].equals("INDI")){
                        if (tags[1].equals("BIRT") || tags[1].equals("DEAT")) {
                        	person.update(tags[1] , content.toString());
                        }
                    }

                    else if(tags[0].equals("FAM")){
                        if (tags[1].equals("MARR") || tags[1].equals("DIV")) {
                            family.update(tags[1] , content.toString());
                        }
                    }

                    break;
                default:
                   break;
            }


        }
        allPeople.add(person);
        allFamilies.add(family);

        br.close();
    }
    
    public static void updatemerrage(ArrayList<individual> people , ArrayList<Family> families) {
    	String hus,wif,chi;  // this is actually ID of hus,wif,chi
    	int hid,wid,cid; // id is the index of h or w or c in the people
    	for(int x = 0 ; x < families.size() ; x++) {
    		hus = families.get(x).husband;
    		wif = families.get(x).wife;
    		for(int y = 0 ; y < families.get(x).children.size() ; y++) {
    			chi = families.get(x).children.get(y);
    			cid = searchbyId(people,chi);
    			if(cid == -1) continue;
        		people.get(cid).update("CHIL", families.get(x).familyID);
    		}
    		hid = searchbyId(people,hus);
    		if(hid != -1) people.get(hid).update("SPOU" , families.get(x).familyID);
    		wid = searchbyId(people,wif);
    		if(wid != -1) people.get(wid).update("SPOU" , families.get(x).familyID);
    	}
    	
    	
    }
    
    
    public static int searchbyId(ArrayList<individual> people , String target) {
    	for(int x = 0 ; x < people.size() ; x++) {
    		if(target.equals(people.get(x).id)) {    		
    			return x;
    		}
    	}
    	
    	return -1;
    }
    
}
