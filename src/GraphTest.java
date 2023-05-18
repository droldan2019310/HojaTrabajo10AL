

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class GraphTest{
    @Test
    public void addTest(){
        List<Edge> edges  = new ArrayList();
        edges.add(0,new Edge(0,1,10));
        edges.add(0,new Edge(0,2,20));
        Graph graph = new Graph(edges);
        

        assertEquals(graph.getEdge(0, 1),10);

    }

    @Test
    public void updateTest(){
        List<Edge> edges  = new ArrayList();
        edges.add(0,new Edge(0,1,10));
        edges.add(0,new Edge(0,2,20));
        Graph graph = new Graph(edges);

        graph.updateEdge(new Edge(0, 1, 40));

        assertEquals( 40, graph.getEdge(0, 1));
    }


    @Test
    public void getCenter(){
        List<Edge> edges  = new ArrayList();
        edges.add(0,new Edge(0,1,10));
        edges.add(0,new Edge(0,2,20));
        edges.add(0,new Edge(3,2,20));
        Graph graph = new Graph(edges);
        List<Integer> center =graph.getCenter();
        int centersub0 = center.get(0);
        assertEquals(0,centersub0);
    }
}