import java.util.*;
import java.io.*;
import java.math.*;

///////////////////////////////////////////
//////////////////////////////////////// //
///////////////////////////////////// // //
////////////////////////////////   
// Author : Samin Arefin           
// OS     : Manjaro Linux x86_64 
// JDK    : OpenJDK 1.7.0_45
// OJ     : 
// Problem: 
// Date   : 
////////////////////////////////   
///////////////////////////////////// // //
//////////////////////////////////////// //
///////////////////////////////////////////


/**
 * see the solution at the bottom
 */

/**
 * initializing inputreader and outputwriter and calling the solution class
 */
public class Main {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		Solution solution = new Solution();
		solution.solve(in,out);
		out.close();
	}
}

/**
 * custom reader to take input efficiently
 */
class InputReader {

	private BufferedReader reader;
	private StringTokenizer token;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream));
	}

	public String next() {
		while (token == null || !token.hasMoreElements()) {
			try {
				token = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return token.nextToken();
	}

	public String nextLine() {
		String string = "";
		try {
			string = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return string;
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}

	public boolean isNotEmpty() {
		boolean flag = false;
		try {
			flag = reader.ready();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

}    

/**
 * custom writer class
 */
class OutputWriter {
	private final StringBuilder appender;
	private final PrintWriter writer;

	public OutputWriter(OutputStream outputStream) {
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
		appender = new StringBuilder();
	}

	public OutputWriter(Writer writer) {
		this.writer = new PrintWriter(writer);
		appender = new StringBuilder();
	}

	public void close() {
		writer.close();
	}

	public void printLine(Object o) {
		writer.println(o.toString());
	}

	public void write(Object o) {
		writer.write(o.toString());
	}

	public void print(Object o) {
		writer.print(o.toString());
	}

	public StringBuilder append(int n) {
		return appender.append(n);
	}

	public StringBuilder append(long l) {
		return appender.append(l);
	}

	public StringBuilder append(float f) {
		return appender.append(f);
	}

	public StringBuilder append(double d) {
		return appender.append(d);
	}

	public StringBuilder append(char c) {
		return appender.append(c);
	}

	public StringBuilder append(char[] charseq) {
		return appender.append(charseq);
	}

	public StringBuilder append(String string) {
		return appender.append(string);
	}

	public StringBuilder append(CharSequence charSequence) {
		return appender.append(charSequence);
	}

	public StringBuilder append(Object obj) {
		return appender.append(obj);
	}

	public StringBuilder append(boolean b) {
		return appender.append(b);
	}

	public StringBuilder append(StringBuffer stringBuffer) {
		return appender.append(stringBuffer);
	}

	public void flush() {
		print(appender);
	}

}

/**
 * implementing different important mathematical operations
 */
class Mathematics {
    /**
     * finds square root of a big integer
     *
     * @param n the given BigInteger
     * @return square root of the given BigInteger
     */
    public static BigInteger sqrt(BigInteger n) {
    	BigInteger a = BigInteger.ONE;
    	BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
    	while (b.compareTo(a) >= 0) {
    		BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
    		if (mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
    		else a = mid.add(BigInteger.ONE);
    	}
    	return a.subtract(BigInteger.ONE);
    }

    /**
     * @param i an integer
     * @param j an integer
     * @return the greatest common divisor of the two given integer
     */
    public static int gcd(int i, int j) {
    	if (j == 0) return i;
    	else return gcd(j, i % j);
    }

    /**
     * @param i an integer
     * @param j an integer
     * @return the least common multiple of the two given integer
     */
    public static int lcm(int i, int j) {
    	return (i / gcd(i, j)) * j;
    }

    /**
     * @param i BigInteger
     * @param j BigInteger
     * @return lcm of this two BigInteger
     */
    public static BigInteger lcm(BigInteger i, BigInteger j) {
    	return i.divide(i.gcd(j)).multiply(j);
    }

    /**
     * http://www.mathblog.dk/uva-530-binomial-showdown/
     *
     * @param n
     * @param k
     * @return how many ways k can be taken out of n
     */
    public static long nCk(int n, int k) {

    	k = Math.min(k, n - k);
    	long res = 1;
    	for (int i = 1; i <= k; i++) res = res * (n - k + i) / i;
    		return res;
    }

}

/**
 * QuickUnion with path compression
 */
class QuickUnion {
	public int[] id;
	public int[] sz;

    /**
     * initializes a QuickUnion data structure with capacity n
     *
     * @param n capacity (0 based)
     */
    public QuickUnion(int n) {
    	id = new int[n];
    	sz = new int[n];
    	for (int i = 0; i < n; i++) {
    		id[i] = i;
    		sz[i] = 1;
    	}
    }

    /**
     * default empty constructor, do not use this unless extended
     */
    public QuickUnion() {
    }

    /**
     * finds the parent of i
     *
     * @param i an element
     * @return the parent of i
     */
    public int root(int i) {
    	while (i != id[i]) {
    		id[i] = id[id[i]];
    		i = id[i];
    	}
    	return i;
    }

    /**
     * decides if two elements are in the same set
     *
     * @param p an element
     * @param q an element
     * @return true if p and q are in the same set
     */
    public boolean find(int p, int q) {
    	return root(p) == root(q);
    }

    /**
     * unite two elements
     *
     * @param p an element
     * @param q an element
     */
    public void unite(int p, int q) {
    	int i = root(p);
    	int j = root(q);

    	if (sz[i] < sz[j]) {
    		id[i] = j;
    		sz[j] += sz[i];
    	} else {
    		id[j] = i;
    		sz[i] += sz[j];
    	}

    }

    /**
     * @param id the given id
     * @return the size of the united components rooted at id
     */
    public int size(int id) {
    	return sz[id];
    }
}

/**
 * Sieve of Eratosthenes
 */
class Sieve {

	public int[] status;
	public int length;

    /**     
     * @param length prime will be generated up to and including length
     */
    public Sieve(int length) {
    	this.length = length;
    	status = new int[(length >> 1) + 10];
    	for (int i = 3; i * i <= length; i += 2)
    		if (status[i >> 1] == 0)
    			for (int j = i * i; j <= length; j += 2 * i)
    				status[j >> 1] = 1;

    		}

    /**
     * @return a list of all the primes
     */
    public ArrayList<Integer> getAllPrimes() {
    	ArrayList<Integer> listOfPrime = new ArrayList<Integer>();
    	if (length >= 1) listOfPrime.add(2);
    	for (int i = 3; i <= length; i += 2)
    		if (isPrime(i))
    			listOfPrime.add(i);
    		return listOfPrime;
    	}

    /**
     * @param i an integer to check primality
     * @return true if i is prime
     */
    public boolean isPrime(int i) {
    	if (i == 1) return false;
    	if (i == 2) return true;
    	if (i % 2 == 0) return false;
    	if (status[i >> 1] == 1) return false;
    	return true;
    }

}

/**
 * implementing different operations related to factorizing
 */
class Divisors {

    /**
     * @param n an integer
     * @return a list of all the divisors of n
     */
    public static ArrayList<Integer> getAllDivisors(int n) {
    	ArrayList<Integer> list = new ArrayList<Integer>();

    	for (int i = 1; i * i <= n; i++) {
    		if (n % i == 0) {
    			list.add(i);
    			list.add(n / i);
    		}
    	}
    	if ((int) Math.sqrt(n) == Math.sqrt(n)) {
    		list.remove((Integer) (int) Math.sqrt(n));
    	}
    	return list;
    }

    /**
     * using sieve it determines the total number of divisors of n ( including 1 and the number itself)
     *
     * @param n integer to count the divisors
     * @return the total number of divisors of n
     */
    public static int countDivisorsBySieve(int n) {
    	ArrayList<Integer> list = primeFactorize(n);
    	int[] keep = new int[list.size()];
    	int i = 0;
    	int first = list.get(0);
    	for (int x : list) {
    		if (x != first) {
    			keep[++i]++;
    			first = x;
    		} else {
    			keep[i]++;
    		}
    	}

    	int sum = 1;
    	for (int x : keep)
    		sum *= x + 1;
    	return sum;
    }

    /**
     * without sieve it determines the total number of divisors of n ( including 1 and the number itself)
     *
     * @param n integer to count the divisors
     * @return the total number of divisors of n
     */
    public static int countDivisorsWithoutSieve(int n) {
    	int count = 1;
    	int sqrt = (int) Math.sqrt(n);

    	for (int i = 2; i <= sqrt; i = (i == 2 ? 3 : i + 2)) {
    		int pow = 0;
    		while (n % i == 0) {
    			pow++;
    			n /= i;
    		}

    		if (pow != 0) {
    			count *= pow + 1;
    			sqrt = (int) Math.sqrt(n);
    		}
    	}

    	if (n != 1)
    		count *= 1 + 1;

    	return count;
    }

    /**
     * @param n integer to find the sum of it's divisors
     * @return the sum of n's divisors
     */
    public static int sumOfDivisors(int n) {

    	ArrayList<Integer> list = primeFactorize(n);
    	int[] keep = new int[list.size()];
    	int i = 0;
    	int first = list.get(0);

    	for (int x : list) {

    		if (x != first) {
    			keep[++i]++;
    			first = x;
    		} else {
    			keep[i]++;
    		}

    	}

    	int sum = 1;
    	int c = 0;
    	int temp = 0;
    	for (int x : keep) {
    		if (x == 0)
    			break;
    		int listNum = list.get(c++);
    		while (listNum == temp) {
    			listNum = list.get(c++);
    		}
    		temp = listNum;
    		sum = (int) (sum * ((Math.pow(listNum, x + 1) - 1) / (listNum - 1)));
    	}
    	return sum;
    }

    /**
     * using sieve it determines all the prime factors of n
     *
     * @param n integer to primeFactorize
     * @return a list of all the prime factors of n
     */
    public static ArrayList<Integer> primeFactorize(int n) {

    	Sieve sieve = new Sieve((int) Math.sqrt(n) + 10);

    	ArrayList<Integer> listOfPrimes = sieve.getAllPrimes();
    	ArrayList<Integer> listOfPrimeFactors = new ArrayList<Integer>();

    	int sqrtN = (int) Math.sqrt(n);

    	for (int i = 0; listOfPrimes.get(i) <= sqrtN; i++) {

    		int currentPrime = listOfPrimes.get(i);

    		if (n % currentPrime == 0) {
    			while (n % currentPrime == 0) {
    				n /= currentPrime;
    				listOfPrimeFactors.add(currentPrime);
    			}
    			sqrtN = (int) Math.sqrt(n);
    		}
    	}

    	if (n > 1) listOfPrimeFactors.add(n);
    	return listOfPrimeFactors;
    }
}

/**
 * implementing different grpah operations
 */
class Graph {
    private final int vertices;  // number of vertices, 0 based
    private int edges;           // number of edges    
    private LinkedList<Integer>[] adj;

    /**
     * initializes a graph with given number of vertices
     *
     * @param vertices number of vertices (0 based)
     */
    @SuppressWarnings("unchecked")
    public Graph(int vertices) {
    	this.vertices = vertices;
    	this.edges = 0;       	
    	adj = (LinkedList<Integer>[]) new LinkedList[vertices];
    	for (int v = 0; v < vertices; v++)
    		adj[v] = new LinkedList<Integer>();
    }  // constructor taking the number of vertices, 0 based

    /**
     * @return the number of vertices
     */
    public int countVertices() {
    	return vertices;
    }  // count the number of vertices

    /**
     * @return the number of edges
     */
    public int countEdges() {
    	return edges;
    }     // count the number of edges

    /**
     * adds a directed edge
     *
     * @param from edge starts from
     * @param to   edge finishes at
     */
    public void addEdge(int from, int to) {
    	adj[from].add(to);
    	edges++;
    } // adding a directed edge between two points

    /**
     * enables for each loop to iterate through all the reachable vertices from the given vertex
     *
     * @param vertex the given vertex to find it's neighbor
     * @return an iterable type of the given vertex, useful to iterate through all the adjacent vertices
     */
    public Iterable<Integer> adjacentOf(int vertex) {
    	return adj[vertex];
    } // return an iterable type of the given vertex, useful to iterate through all the adjacent vertices

    /**
     * @return a total reverse graph of this graph
     */
    public Graph reverseGraph() {
    	Graph reverseGraph = new Graph(vertices);
    	for (int v = 0; v < vertices; v++) {
    		for (int a : adj[v]) {
    			reverseGraph.addEdge(a, v);
    		}
    	}
    	return reverseGraph;
    } // totally reverse the graph, like 1 -> 3 becomes 3 -> 1

    /**
     * removes an directed edge of two points
     *
     * @param from the edge starts from
     * @param to   edge finishes at
     */
    public void removeEdge(int from, int to) {
    	adj[from].remove((Integer) to);
    } // remove the edge between these two points

    /**
     * @param vertex the given vertex
     * @return the number of total directed edges reachable from the given vertex
     */
    public int outdegree(int vertex) {
    	return adj[vertex].size();
    }

    /**
     * removes all the directed edges from the given vertex
     *
     * @param vertex the given vertex
     */
    public void removeAllEdgesOf(int vertex) {
    	int len = outdegree(vertex);
    	while (len-- > 0) {
    		adj[vertex].removeFirst();
    	}
    }    // remove all the edges from the given vertex

    /**
     * @return a representation of the graph
     */
    public String toString() {
    	StringBuilder s = new StringBuilder();
    	String NEWLINE = System.getProperty("line.separator");
    	s.append(vertices + " " + edges + NEWLINE);
    	for (int v = 0; v < vertices; v++) {
    		s.append(String.format("%d: ", v));
    		for (int w : adj[v]) {
    			s.append(String.format("%d ", w));
    		}
    		s.append(NEWLINE);
    	}
    	return s.toString();
    }       // string presentation of the graph
}

/**
 * implementing different operations on weighted graph
 */
class GraphWeighted {
	private final int vertices;
	private int edges;
	private LinkedList<DirectedEdge>[] adj;

    /**
     * initializes a weighted graph
     *
     * @param vertices number of vertices of the graph (0 based)
     */
    @SuppressWarnings("unchecked")
    public GraphWeighted(int vertices) {
    	this.vertices = vertices;
    	this.edges = 0;
    	adj = (LinkedList<DirectedEdge>[]) new LinkedList[vertices];
    	for (int v = 0; v < vertices; v++) {
    		adj[v] = new LinkedList<DirectedEdge>();
    	}
    }

    /**
     * @return the number of vertices
     */
    public int countVertices() {
    	return vertices;
    }

    /**
     * @return the number of edges
     */
    public int Countedges() {
    	return edges;
    }

    /**
     * adds a direceted edge
     *
     * @param edge the directed edge
     */
    public void addEdge(DirectedEdge edge) {
    	int from = edge.from();
    	adj[from].add(edge);
    	edges++;
    }

    /**
     * enables for each loop to iterate through all the reachable vertices from the given vertex
     *
     * @param vertex the given vertex
     * @return an iterable type of the given vertex, useful to iterate through all the adjacent vertices
     */
    public Iterable<DirectedEdge> adjacentOf(int vertex) {
    	return adj[vertex];
    }

    /**
     * @return an iterable type of the graph, useful to iterate through all the edges
     */
    public Iterable<DirectedEdge> allEdges() {
    	LinkedList<DirectedEdge> list = new LinkedList<DirectedEdge>();
    	for (int v = 0; v < vertices; v++) {
    		for (DirectedEdge edge : adj[v]) {
    			list.add(edge);
    		}
    	}
    	return list;
    }

    /**
     * @param vertex the given vertex
     * @return the number of total directed edges reachable from the given vertex
     */
    public int outdegree(int vertex) {
    	return adj[vertex].size();
    }

    /**
     * @return a total reverse graph of this graph
     */
    public GraphWeighted reverseGraph() {
    	GraphWeighted rev = new GraphWeighted(vertices);
    	for (DirectedEdge e : allEdges()) {
    		rev.addEdge(new DirectedEdge(e.to, e.from, e.weight));
    	}
    	return rev;
    }

    /**
     * removes a directed edge
     *
     * @param from edge starts from
     * @param to   edge finishes at
     */
    public void removeEdge(int from, int to) {
    	for (DirectedEdge e : adjacentOf(from)) {
    		if (e.to() == to) {
    			adj[from].remove(e);
    		}
    	}
    }

    /**
     * removes all the directed edges from the given vertex
     *
     * @param vertex the given vertex
     */
    public void removeAllEdgesOf(int vertex) {
    	int len = adj[vertex].size();
    	while (len-- > 0) {
    		adj[vertex].removeFirst();
    	}
    }

    /**
     * @return a representation of the graph
     */
    public String toString() {
    	String NEWLINE = System.getProperty("line.separator");
    	StringBuilder s = new StringBuilder();
    	s.append(vertices + " " + edges + NEWLINE);
    	for (int v = 0; v < vertices; v++) {
    		s.append(v + ": ");
    		for (DirectedEdge e : adj[v]) {
    			s.append(e + "  ");
    		}
    		s.append(NEWLINE);
    	}
    	return s.toString();
    }

    /**
     * a directed edge with three properties: from, to and weight
     */
    public static class DirectedEdge {
    	private final int from;
    	private final int to;
    	private final double weight;

        /**
         * initializes a directed edge
         *
         * @param from   edge starts from
         * @param to     edge finishes at
         * @param weight the weight of the edge
         */
        public DirectedEdge(int from, int to, double weight) {
        	this.from = from;
        	this.to = to;
        	this.weight = weight;
        }

        public int from() {
        	return from;
        }

        public int to() {
        	return to;
        }

        public double weight() {
        	return weight;
        }


    }
}

/**
 * convenient way to traverse a graph
 */
class DirectionArray {

    public static final int fx8[] = {0, 0, 1, -1, -1, 1, -1, 1};
    public static final int fy8[] = {-1, 1, 0, 0, 1, 1, -1, -1};

    public static final int fx4[] = {0, 0, 1, -1};
    public static final int fy4[] = {-1, 1, 0, 0};

}

/**
 * solution of the problem
 */
class Solution {
	public void solve(InputReader in, OutputWriter out) {
		String line;
		while((line = in.nextLine()) != null) {
			
		}
	}
}
