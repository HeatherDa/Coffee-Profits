package com.Heather;
        import java.util.*;
        import java.io.*;
public class Main {

    static Scanner scandoub;
    public static void main(String[] args) throws IOException {
        // prints name of program and calls function
        scandoub=new Scanner(System.in);
        System.out.println("Coffee Shop Profit calculator program.");
        results();
    }
    public static HashMap<String, ArrayList<Double>> cups() throws IOException {
        HashMap<String, ArrayList<Double>> drinksold = null;
        try {
            drinksold = readfile("coffee.txt");
        } catch (FileNotFoundException fnfe) {
            System.out.println("sorry, that file does not exist.");
        } catch (IOException ioe){
            System.out.println("could not read from coffee.txt");
        }
        if(drinksold.isEmpty()){//trap an empty file
            System.out.println("The file is empty.");
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
                System.out.println("Please enter a number.");
                break;
            }


        }
        scandoub.close();
        return drinksold;
    }

    public static void results() throws IOException {
        HashMap<String, ArrayList<Double>>drinkinfo=cups();
        Double daytcost=0.0;
        Double daytprice=0.0;
        Double daytprofit=0.0;
        for (String item : drinkinfo.keySet()) {//item is String drink name

            ArrayList<Double> num=drinkinfo.get(item);
            Double tcost=num.get(0)*num.get(2);//cost to produce
            daytcost=daytcost+tcost;
            Double tprice=num.get(1)*num.get(2);//price charged
            daytprice=daytprice+tprice;
            Double tprofit=tprice-tcost;//profit
            daytprofit=daytprofit+tprofit;
            System.out.format(item+": Sold %.0f, Expenses $%.2f, Revenue $%.2f, Profit $%.2f \n", num.get(2), tcost, tprice, tprofit);
        }
        System.out.format("\nTotals for today: Expenses $%.2f, Revenue $%.2f, Profit$%.2f", daytcost, daytprice, daytprofit);
    }

    public static HashMap<String, ArrayList<Double>> readfile(String filename) throws IOException {

        FileReader reader = new FileReader(filename);
        BufferedReader bufReader = new BufferedReader(reader);
        String line = bufReader.readLine();
        HashMap<String, ArrayList<Double>> text=new HashMap<>();

        while (line != null) {
            String[] linetext = line.split(";");
            String drink=linetext[0];//name of beverage
            ArrayList<Double> doub=new ArrayList<>();
            doub.add(Double.parseDouble(linetext[1]));//cost to prepare beverage
            doub.add(Double.parseDouble(linetext[2]));//price to purchase beverage
            text.put(drink,doub);
            line = bufReader.readLine();

        }

        bufReader.close();   //This closes the inner FileReader too
        return text;
    }
    public static String writefile() throws IOException {//used this to create the coffee.txt file and copied the text into it, because it wouldn't work when I dragged it in like the instructions origianlly said to.
        String filename="coffee.txt";
        FileWriter writer = new FileWriter(filename);
        BufferedWriter bufWriter = new BufferedWriter(writer);

        bufWriter.write("\n");


        bufWriter.close();
        return filename;
    }
}

