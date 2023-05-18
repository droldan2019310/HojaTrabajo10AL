import java.util.*;  
//the class stores the edges of the graph  

//a class to store adjacency list nodes  
class Node   {  
    public int value, weight;  
    //creating a constructor of the class Vertex  
    Node(int value, int weight){  
        this.value = value;  
        this.weight = weight;  
    }  
    //overrides the toString() method  
    @Override  
    public String toString(){  
        return this.value + " (" + this.weight + ")";  
    }  
}  

public class Graph {
    //note that we have created an adjacency list (i.e. List of List)      
    List<List<Node>> adjlist = new ArrayList<>();  
    //creating a constructor of the class Graph that creates graph  

    static int INF = 9999;

    public Graph(List<Edge> edges)  {  
        //find the maximum numbered vertex  
        int n = 0;  
        //iterates over the edges of the graph  
        for (Edge e: edges)   {  
            //determines the maximum numbered vertex           
            n = Integer.max(n, Integer.max(e.s, e.d));  
        }  
        //reserve the space for the adjacency list  
        for (int i = 0; i <= n; i++){  
            adjlist.add(i, new ArrayList<>());  
        }  

        //adds the edges to the undirected graph  
        for (Edge e: edges)  {  
            //creating a new node (from source to destination) in the adjacency list   
            adjlist.get(e.s).add(new Node(e.d, e.w));  
            //uncomment the following statement for undirected graph  
            //adj.get(e.dest).add(new Node(e.src, e.weight));  
        }  
    }  
    //method that prints adjacency list of a graph  
    public static void printGraph(Graph graph){  
        int src = 0;  
        int n = graph.adjlist.size();  
        System.out.println("Adjacency List for the Graph is: ");      
        while (src < n)  {  
            //for-each loop prints the neighboring vertices with current vertex     
            for (Node edge: graph.adjlist.get(src))   
            {  
                System.out.printf("%d -- > %s\t", src, edge);  
            }  
            System.out.println();  
            //increments the source by 1  
            src++;  
        }  
    }  

    public void addEdge(Edge edge){
        adjlist.get(edge.s).add(new Node(edge.d, edge.w));  

    }

    public int getEdge(int v,int k){
        return adjlist.get(v).get(k).weight;
    }

    public void updateEdge(Edge edge){
        adjlist.get(edge.s).get(edge.d).weight=edge.w;
    }


    public  int[][] floydWarshall() {
        int V = adjlist.size();
        int[][] dist = new int[V][V];

        // Inicializa la matriz de distancias con los valores del grafo original
        for (int i = 0; i < V; i++) {
            Arrays.fill(dist[i], INF);
            for (Node neighbor : adjlist.get(i)) {
                dist[i][neighbor.value] = neighbor.weight;
            }
            dist[i][i] = 0;
        }

        // Itera por cada vértice como un posible vértice intermedio
        for (int k = 0; k < V; k++) {
            // Itera por cada par de vértices como origen y destino
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    // Si el vértice k mejora la distancia de i a j, actualiza la distancia
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Imprime la matriz de distancias resultante
        printDistances(dist);
        return dist;
    }

    public static void printDistances(int[][] dist) {
        int V = dist.length;
        System.out.println("Matriz de distancias resultante:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public List<Integer> getCenter(){
        int[][] dist = floydWarshall();
        int n = adjlist.size();
        int[] eccentricity = new int[n];
        for (int i = 0; i < n; i++) {
            eccentricity[i] = 0;
            for (int j = 0; j < n; j++) {
                eccentricity[i] = Math.max(eccentricity[i], dist[i][j]);
            }
        }

        int minEccentricity = INF;
        for (int i = 0; i < n; i++) {
            minEccentricity = Math.min(minEccentricity, eccentricity[i]);
        }

        // Encuentra los vértices con excentricidad igual a la mínima
        List<Integer> center = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (eccentricity[i] == minEccentricity) {
                center.add(i);
            }
        }

        System.out.println("Centro del grafo: " + center);
        return center;
    }


}
