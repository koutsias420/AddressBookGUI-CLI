import java.util.Scanner;
import java.io.*;
/**
 *
 * @author Orestes
 */
public class AddressBookCLI {

    public static void main(String[] args) {
        String filename = "book.txt";
        String[] arr = new String[1];
        int i = 0;
        int cntr = 0;
        try{
            File f = new File(filename);
            Scanner scnr = new Scanner(f);
            cntr = scnr.nextInt();
            scnr.nextLine();
            arr = new String[cntr];
            while (scnr.hasNextLine() && i<arr.length){
                arr[i] = scnr.nextLine();
                i++;
            }
            scnr.close();
        }
        catch (FileNotFoundException e){
            System.out.println("ERR_FILE_NOT_FOUND\nexiting....");
            System.exit(0);
        }
        
        showBook(arr);
        Scanner fscnr = new Scanner(System.in);
        while (true){
            arr = handleChoice(arr,fscnr,filename);
        }
    }
    
    private static void showBook(String[] arr){
        int i;
        System.out.println("Address Book:");
        for (i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
        System.out.println("\n");
    }
    
    private static int getChoice(Scanner scnr){
        int ans;
        System.out.println("Menu:");
        System.out.println("1. Add name");
        System.out.println("2. Delete name");
        System.out.println("3. Show list");
        System.out.println("4. Exit");
        System.out.print(">");
        
        ans = scnr.nextInt();
        scnr.nextLine();
                
        return ans;
    }
    
    private static String[] handleChoice(String[] arr,Scanner scnr,String filename){
        int ans;
        ans = getChoice(scnr);
        
        if (ans == 4){
            scnr.close();
            writeToFile(arr,filename);
            System.out.println("exiting....");
            System.exit(0);
        }
        else if (ans == 3){
            showBook(arr);
        }
        else if (ans == 2){
            arr = Log(arr,scnr,"delete");
        }
        else{
            arr = Log(arr,scnr,"add");
        }
        return arr;
    }
    
    private static String[] Log(String [] arr,Scanner scnr,String n){
        String n2;
        if (n == "delete"){
            int index;
            System.out.print("\nInput index number to " + n + ": \n>");
            index = scnr.nextInt();
            n2 = "removed"; 
            arr = removeElement(arr,index-1);
        }
        else{
            String input;
            n2 = "added";
            System.out.print("\nInput name,phone,address to " + n +":\n>");
            input = scnr.nextLine();
            arr = addElement(arr,input);
        }
        System.out.println("Element " + n2 +"!\n");
        return arr;
        }
    
    
    private static void writeToFile(String[] arr,String filename){
        int i;
        try{
            FileWriter wrtr = new FileWriter(filename);
            wrtr.write(arr.length + "\n");
            for (i=0;i<arr.length;i++){
                wrtr.write(arr[i] + "\n");
            }
            wrtr.close();
        }
        catch (IOException e){
            System.out.println("ERR_OUTPUT_FILE_NOT_FOUND\nexiting....");
            System.exit(0);
        }
    }
    
    private static String[] removeElement(String[] arr,int index){
        String[] updatedArr = new String[arr.length-1];
        
         for (int i = 0, k = 0; i < arr.length; i++) {
             
            if (i == index) {
                continue;
            }
            
            updatedArr[k++] = arr[i];
        }
        
        return updatedArr;
    }    
    
    private static String[] addElement(String[] arr,String input){
        int i = arr.length + 1;
        int j;
        String[] updatedArr = new String[i];
        
        for (j=0;j<updatedArr.length-1;j++){
            updatedArr[j] = arr[j];
        }
        updatedArr[i-1] = Integer.toString(i)+ ". " + input;
        
        return updatedArr;
    }
    
}
