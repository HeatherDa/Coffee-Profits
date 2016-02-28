package com.Heather;
        import java.util.*;
        import java.io.*;
public class Main {

    static Scanner scandoub;
    public static void main(String[] args) throws IOException {
        // write your code here
        scandoub=new Scanner(System.in);
        HashMap <String,ArrayList<Double>>drinkinfo=new HashMap<>();
        //writefile();
        System.out.println("Coffee Shop Profit calculator program.");
        //cups(drinkinfo);
        //System.out.println(//string output of price here);
        results();
    }
    public static HashMap<String, ArrayList<Double>> cups() throws IOException {
        HashMap<String, ArrayList<Double>> drinksold = null;
        try {
            drinksold = readfile("coffee.txt");
        } catch (FileNotFoundException ioe) {
            System.out.println("sorry, that file does not exist.");
        }
        Double num;
        for (String drink : drinksold.keySet()) {
            System.out.println("How many cups of " + drink + " did you sell today?");
            try {
                num = scandoub.nextDouble();
                ArrayList<Double> numarray = drinksold.get(drink);//get arraylist from HashMap
                numarray.add(num); //add total sold to end of ArrayList
                drinksold.put(drink, numarray);//add updated ArrayList to HashMap
            } catch (InputMismatchException ime) {
                System.out.println("Please enter an integer.  For example: 1");
                break;
            }


        }
        scandoub.close();
        return drinksold;
    }

    public static void results() throws IOException {
        HashMap<String, ArrayList<Double>>drinkinfo=cups();
        for (String item : drinkinfo.keySet()) {//item is String drink name

            ArrayList<Double> num=drinkinfo.get(item);
            Double tcost=num.get(0)*num.get(2);
            Double tprice=num.get(1)*num.get(2);
            Double tprofit=tprice-tcost;
            String total=String.format(item+": Sold "+num.get(2)+", Expenses $"+tcost+", Revenue $"+tprice+", Profit $"+tprofit);
            System.out.println(total);
            }
    }



    public static HashMap<String, ArrayList<Double>> readfile(String filename) throws IOException {

        FileReader reader = new FileReader(filename);
        BufferedReader bufReader = new BufferedReader(reader);
        String line = bufReader.readLine();
        HashMap<String, ArrayList<Double>> text=new HashMap<>();
        while (line != null) {
            String[] linetext = line.split(";");
            String drink=linetext[0];
            ArrayList<Double> doub=new ArrayList<>();
            doub.add(Double.parseDouble(linetext[1]));//cost to prepare beverage
            doub.add(Double.parseDouble(linetext[2]));//price to purchase beverage
            text.put(drink,doub);
            line = bufReader.readLine();

        }
        bufReader.close();   //This closes the inner FileReader too
        return text;
    }
    public static String writefile() throws IOException {
        String filename="coffee.txt";
        FileWriter writer = new FileWriter(filename);
        BufferedWriter bufWriter = new BufferedWriter(writer);

        bufWriter.write("\n");


        bufWriter.close();
        return filename;
    }
}

