import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    int resp= 0;
    Scanner sc = new Scanner(System.in);

    ArrayList<String> cities = new ArrayList<String>();

    Graph graphNormal;
    Graph graphRain;
    Graph graphSnow;
    Graph graphStorm;
    
    List<Edge> edgesNormal = new ArrayList<>();
    List<Edge> edgesRain = new ArrayList<>();
    List<Edge> edgesSnow = new ArrayList<>();
    List<Edge> edgesStorm = new ArrayList<>();


    int weather = 1;


    public static void main(String[] args) throws Exception {
        App app = new App();
        app.start();
    }

    public void start(){
        readFile();
    }


    public void readFile(){
        File file = new File("src/logistica.txt");
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            
            String st;
            while ((st = br.readLine()) != null){
                validatecity(st);
            }

            graphNormal = new Graph(edgesNormal);
            graphRain = new Graph(edgesRain);
            graphSnow = new Graph(edgesSnow);
            graphStorm = new Graph(edgesStorm);
            
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("ARCHIVO NO ENCONTRADO");
        }

    }

    public void validatecity(String line){
        String[] part = line.split(" ");
        String city1 = part[0];
        String city2 = part[1];
        System.out.println(city1+" "+city2);
        boolean flag1 = true;
        boolean flag2 = true;
        for (String city : cities) {
            if(city.equals(city1)){
                flag1=false;
            }
            if(city.equals(city2)){
                flag2=false;
            }
        }
        if(flag1==true){
            cities.add(city1);
        }

        if(flag2==true){
            cities.add(city2);
        }
        
        int index1 = cities.indexOf(city1);
        int index2 = cities.indexOf(city2);
        int weightNormal=  Integer.parseInt(part[2]);
        int weightRain=  Integer.parseInt(part[3]);
        int weightSnow=  Integer.parseInt(part[4]);
        int weightStorm=  Integer.parseInt(part[5]);

        edgesNormal.add(new Edge(index1, index2, weightNormal));
        edgesRain.add(new Edge(index1, index2, weightRain));
        edgesSnow.add(new Edge(index1, index2, weightSnow));
        edgesStorm.add(new Edge(index1, index2, weightStorm));

       
    }


}
