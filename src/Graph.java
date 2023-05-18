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
    
}
