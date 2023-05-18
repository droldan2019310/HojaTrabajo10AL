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

}
