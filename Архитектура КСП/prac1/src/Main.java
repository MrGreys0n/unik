import java.util.*;

public class Main {
    static Map<Integer, List<Integer>> graph = new HashMap<>();
    static Set<Integer> visited = new HashSet<>();
    static List<Integer> maxPath = new ArrayList<>();

    public static List<Integer> findLongestPath(int startNode) {
        List<Integer> path = new ArrayList<>();
        dfs(startNode, path);
        return maxPath;
    }

    public static void dfs(int node, List<Integer> path) {
        visited.add(node);
        path.add(node);

        if (graph.containsKey(node)) {
            for (int nextNode : graph.get(node)) {
                if (!visited.contains(nextNode)) {
                    dfs(nextNode, path);
                }
            }
        }

        if (path.size() > maxPath.size()) {
            maxPath = new ArrayList<>(path);
        }

        path.remove(path.size() - 1);
        visited.remove(node);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int r = scanner.nextInt();
            if (!graph.containsKey(r)) {
                graph.put(r, new ArrayList<>());
            }
            graph.get(r).add(i + 1);
        }

        List<Integer> result = findLongestPath(0);
        System.out.println(result.get(result.size() - 1));
    }
}
