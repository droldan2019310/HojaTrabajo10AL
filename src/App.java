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
        menu();
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

    public void menu(){

        while(resp!=7){
            System.out.println("SELECCIONE UNA OPCIÓN");
            System.out.println("1. RUTA MÁS CORTA");
            System.out.println("2. CIUDAD DEL CENTRO");
            System.out.println("3. INTERRUPCIÓN DE TRÁFICO");
            System.out.println("4. CONEXIÓN ENTRE DOS CIUDADES");
            System.out.println("5. INDICAR CLIMA");
            System.out.println("6. MOSTRAR MATRIZ DE ADYACENCIA");
            System.out.println("7. SALIR");

            resp = sc.nextInt();


            switch (resp) {
                case 1:
                    floyd();
                    break;
                case 2:
                    center();
                    break;
                case 3:
                    setNewWeight();
                    break;
                case 4:
                    setNewEdge();
                    break;
                case 5:
                    setWeather();
                    break;
                case 6:
                    printGraph();
                    break;
                case 7:
                    System.out.println("GRACIAS POR UTILIZAR EL PROGRAMA");                
                    break;
                default:
                    break;
            }

        }
    }

    public void center(){
        switch (weather) {
            case 1:
                graphNormal.getCenter();
                break;
            case 2:
                graphRain.getCenter();
                break;
            case 3:
                graphSnow.getCenter();
                break;
            case 4:
                graphStorm.getCenter();
                break;
            default:
                System.out.println("OPCIÓN INVALIDA");
                break;
        }
    }

    public void floyd(){
        switch (weather) {
            case 1:
                graphNormal.floydWarshall();
                break;
            case 2:
                graphRain.floydWarshall();
                break;
            case 3:
                graphSnow.floydWarshall();
                break;
            case 4:
                graphStorm.floydWarshall();
                break;
            default:
                System.out.println("OPCIÓN INVALIDA");
                break;
        }
    }

    public void setNewWeight(){
        System.out.println("CIUDAD 1: ");
        String city1 = sc.next();
        System.out.println("CIUDAD 2: ");
        String city2 = sc.next();

        System.out.println("TIEMPO NUEVO: ");
        int weight = sc.nextInt();

        boolean flag = true;
        for (String city : cities) {
            if(city.equals(city1)){
                flag=false;
            }
            if(city.equals(city2)){
                flag=false;
            }
        }
        if(flag==true){
            
            int index1 = cities.indexOf(city1);
            int index2 = cities.indexOf(city2);
            Edge edge = new Edge(index1, index2, weight);

            switch (weather) {
                case 1:
                    graphNormal.updateEdge(edge);
                    break;
                case 2:
                    graphRain.updateEdge(edge);
                    break;
                case 3:
                    graphSnow.updateEdge(edge);
                    break;
                case 4:
                    graphStorm.updateEdge(edge);
                    break;
                default:
                    break;
            }
        }
    }

    public void printGraph(){
        switch (weather) {
            case 1:
                Graph.printGraph(graphNormal);
                break;
            case 2:
                Graph.printGraph(graphRain);
                break;
            case 3:
                Graph.printGraph(graphSnow);
                break;
            case 4:
                Graph.printGraph(graphStorm);
                break;
            default:
                break;
        }
    }

    public void setWeather(){
        System.out.println("1. CLIMA NORMAL");
        System.out.println("2. CLIMA LLUVIA");
        System.out.println("3. CLIMA NIEVE");
        System.out.println("4. CLIMA TORMENTA");
        weather = sc.nextInt();
    }

    public void setNewEdge(){
        System.out.print("INGRESE EL NOMBRE DE LA CIUDAD 1: ");
        String city1 = sc.next();
        System.out.print("INGRESE EL NOMBRE DE LA CIUDAD 2: ");
        String city2 = sc.next();

        System.out.print("TIEMPO CLIMA NORMAL: ");
        int weightNormal = sc.nextInt();

        System.out.print("TIEMPO CLIMA LLUVIA: ");
        int weightRain = sc.nextInt();

        System.out.print("TIEMPO CLIMA NIEVE: ");
        int weightSnow = sc.nextInt();

        System.out.print("TIEMPO CLIMA TORMENTA: ");
        int weightStorm = sc.nextInt();


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

        graphNormal.addEdge(new Edge(index1, index2, weightNormal));
        graphRain.addEdge(new Edge(index1, index2, weightRain));
        graphSnow.addEdge(new Edge(index1, index2, weightSnow));
        graphStorm.addEdge(new Edge(index1, index2, weightStorm));
    }


}
