// Q6 digraphmobile

import java.util.LinkedList;
import java.util.Stack;

// This class represents a directed graph using adjacency list representation
class Graph
{
    private final int V;
    private final LinkedList[] adj;

    //Constructor
    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    //Function to add an edge into the graph
    void addEdge(int v, int w) { adj[v].add(w); }

    // A recursive function to print DFS starting from v
    void DFSUtil(int v, boolean[] visited)
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        int n;

        // Recur for all the vertices adjacent to this vertex
        for (Object o : adj[v]) {
            n = (int) o;
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    // Function that returns reverse (or transpose) of this graph
    Graph getTranspose()
    {
        Graph g = new Graph(V);
        for (int v = 0; v < V; v++)
        {
            // Recur for all the vertices adjacent to this vertex
            for (Integer integer : (Iterable<Integer>) adj[v]) g.adj[integer].add(v);
        }
        return g;
    }

    void fillOrder(int v, boolean[] visited, Stack<Integer> stack)
    {
        // Mark the current node as visited and print it
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        for (Object o : adj[v]) {
            int n = (int) o;
            if (!visited[n])
                fillOrder(n, visited, stack);
        }

        // All vertices reachable from v are processed by now,
        // push v to Stack
        stack.push(v);
    }

    // The main function that finds and prints all strongly connected components
    void printSCCs()
    {
        Stack<Integer> stack = new Stack<>();

        // Mark all the vertices as not visited (For first DFS)
        boolean[] visited = new boolean[V];
        for(int i = 0; i < V; i++)
            visited[i] = false;

        // Fill vertices in stack according to their finishing times
        for (int i = 0; i < V; i++)
            if (!visited[i])
                fillOrder(i, visited, stack);

        // Create a reversed graph
        Graph gr = getTranspose();

        // Mark all the vertices as not visited (For second DFS)
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Now process all vertices in order defined by Stack
        while (!stack.empty())
        {
            // Pop a vertex from stack
            int v = stack.pop();

            // Print Strongly connected component of the popped vertex
            if (!visited[v])
            {
                gr.DFSUtil(v, visited);
                System.out.println();
            }
        }
    }

    // Driver method
    public static void main(String[] args)
    {
        // Create a graph given in the above diagram
        Graph g = new Graph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        System.out.println("Following are strongly connected components "+
                "in given graph ");
        g.printSCCs();
    }
}
