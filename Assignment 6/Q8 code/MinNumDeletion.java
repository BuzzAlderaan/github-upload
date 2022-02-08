// Java program to find min number of edge deletions
import java.util.*;

class MinNumDeletion {

    // Stores the adjacency list
    static Vector<Integer> []vec = new Vector[100001];

    // Stores if a vertex is visited or not
    static boolean []vis = new boolean[100001];
    static int cc = 1;

    // Function to perform DFS Traversal to count the number and size of all connected componentsz
    static void dfs(int node)
    {

        // Mark the current node as visited
        vis[node] = true;

        // Traverse the adjacency list of the current node
        for(int x : vec[node])
        {

            // For every unvisited node
            if (!vis[x])
            {
                cc++;

                // Recursive DFS call
                dfs(x);
            }
        }
    }

    // Function to add undirected edge in the graph
    static void addEdge(int u, int v)
    {
        vec[u].add(v);
        vec[v].add(u);
    }

    // Function to calculate minimum number of edges to be removed
    static void minEdgeRemoved(int N, int M, int[][] Edges)
    {

        // Create Adjacency list
        for(int i = 0; i < M; i++)
        {
            addEdge(Edges[i][0],
                    Edges[i][1]);
        }

        int k = 0;

        // Iterate over all the nodes
        for(int i = 1; i <= N; i++)
        {
            if (!vis[i])
            {
                cc = 1;
                dfs(i);
                k++;
            }
        }

        // Print the final count
        System.out.print(M - N + k + "\n");
    }

    // Driver Code
    public static void main(String[] args)
    {
        int N = 3, M = 3;

        int[][] Edges = { { 1, 2 }, { 2, 3 },{1,3} };

        for(int i = 0; i < vec.length; i++)
            vec[i] = new Vector<>();

        minEdgeRemoved(N, M, Edges);
    }
}
