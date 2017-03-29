import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by jamesgottshall2018 on 3/29/2017.
 */
public class ElectricBugaloo {
    String filename;
    ElectricBugaloo(String filename){
        this.filename = filename+".txt";
    }
    java.util.List read(){
        java.util.List<String> output = new java.util.ArrayList<>();
        try {
            java.util.Scanner scan = new java.util.Scanner(new java.io.File(filename));
            String input;
            int count=0;
            while (((input = scan.nextLine()) != null)) {
                if (!(input.startsWith("#"))) {
                    output.add(count,input);
                    count++;
                }
                System.out.println(input);
            }
            return output;
        } catch (Exception e){
            if (!(e.toString().startsWith("java.util.NoSuchElement")))
            output.add(e.toString());
            return output;
        }
    }
    java.util.List readHashes(){
        java.util.List<String> output = new java.util.ArrayList<>();
        try {
            java.util.Scanner scan = new java.util.Scanner(new java.io.File(filename));
            String input;
            int count=0;
            while (((input = scan.nextLine()) != null)) {
                if ((input.startsWith("#"))) {
                    output.add(count,input.replace("#",""));
                    count++;
                }
                System.out.println(input);
            }
            return output;
        } catch (Exception e){
            if (!(e.toString().startsWith("java.util.NoSuchElement")))
                output.add(e.toString());
            return output;
        }
    }
    boolean write(String input){
        try{
            PrintStream out = new PrintStream(new FileOutputStream(filename));
            out.append(input);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
