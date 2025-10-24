package graph.traversal;


import graph.entity.Graph;
import graph.entity.Vertex;

import java.util.*;

/**
 * A new alien language uses the English alphabet, but the order of letters is unknown. You are given a list of words[] from the alien language’s dictionary, where the words are claimed to be sorted lexicographically according to the language’s rules.
 * <p>
 * Your task is to determine the correct order of letters in this alien language based on the given words. If the order is valid, return a string containing the unique letters in lexicographically increasing order as per the new language's rules. If there are multiple valid orders, return any one of them.
 * <p>
 * However, if the given arrangement of words is inconsistent with any possible letter ordering, return an empty string ("").
 * <p>
 * A string a is lexicographically smaller than a string b if, at the first position where they differ, the character in a appears earlier in the alien language than the corresponding character in b. If all characters in the shorter word match the beginning of the longer word, the shorter word is considered smaller.
 * <p>
 * Note: Your implementation will be tested using a driver code. It will print true if your returned order correctly follows the alien language’s lexicographic rules; otherwise, it will print false.
 * <p>
 * Examples:
 * <p>
 * Input: words[] = ["baa", "abcd", "abca", "cab", "cad"]
 * Output: true
 * Explanation: A possible corrct order of letters in the alien dictionary is "bdac".
 * The pair "baa" and "abcd" suggests 'b' appears before 'a' in the alien dictionary.
 * The pair "abcd" and "abca" suggests 'd' appears before 'a' in the alien dictionary.
 * The pair "abca" and "cab" suggests 'a' appears before 'c' in the alien dictionary.
 * The pair "cab" and "cad" suggests 'b' appears before 'd' in the alien dictionary.
 * So, 'b' → 'd' → 'a' → 'c' is a valid ordering.
 * Input: words[] = ["caa", "aaa", "aab"]
 * Output: true
 * Explanation: A possible correct order of letters in the alien dictionary is "cab".
 * The pair "caa" and "aaa" suggests 'c' appears before 'a'.
 * The pair "aaa" and "aab" suggests 'a' appear before 'b' in the alien dictionary.
 * So, 'c' → 'a' → 'b' is a valid ordering.
 * Input: words[] = ["ab", "cd", "ef", "ad"]
 * Output: ""
 * Explanation: No valid ordering of letters is possible.
 * The pair "ab" and "ef" suggests "a" appears before "e".
 * The pair "ef" and "ad" suggests "e" appears before "a", which contradicts the ordering rules.
 */
public class AlienDictionaryTopoSort {

    public static void main(String[] args) {
        String[] words = new String[]{"baa", "abcd", "abca", "cab", "cad"};
        String [] dictionary = new String[] {"ab", "cd", "ef", "ad"};

        System.out.println("is dictionary correct : "+ new AlienDictionaryTopoSort().checkIfTheOrderIsCorrect(words));
        System.out.println("is dictionary correct : "+ new AlienDictionaryTopoSort().checkIfTheOrderIsCorrect(dictionary));
    }

    public boolean checkIfTheOrderIsCorrect(String[] words) {

        Graph<Long> graph = new Graph<>(true);
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            int len = Math.min(word1.length(), word2.length());
            for (int l = 0; l < len; l++) {
                if (word1.charAt(l) != word2.charAt(l)) {
                    graph.addEdge(word1.charAt(l) - 'a', word2.charAt(l) - 'a');
                    break;
                }
            }
        }

            Map<Long, Integer> inDegreeCounter = new HashMap<>();
            Collection<Vertex<Long>> allVertex = graph.getAllVertex();


            if (!allVertex.isEmpty()) {
                for (Vertex<Long> vertex : allVertex) {
                    Set<Vertex<Long>> adjacentVertex = vertex.getAdjacentVertex();
                    for (Vertex<Long> adjVertex : adjacentVertex) {
                        inDegreeCounter.put(adjVertex.getId(), inDegreeCounter.getOrDefault(adjVertex.getId(), 0) + 1);
                    }
                }

                Queue<Vertex<Long>> vertexQueue = new LinkedList<>();
                for (Vertex<Long> vertex : allVertex) {
                    if (inDegreeCounter.get(vertex.getId()) == null) vertexQueue.add(vertex);
                }

                Deque<Vertex<Long>> inOrderVertexStack = new ArrayDeque<>();

                while (!vertexQueue.isEmpty()) {
                    Vertex<Long> vertex = vertexQueue.poll();
                    inOrderVertexStack.addLast(vertex);
                    Set<Vertex<Long>> adjacentVertex = vertex.getAdjacentVertex();
                    if (!adjacentVertex.isEmpty()) {
                        for (Vertex<Long> adjVertex : adjacentVertex) {
                            inDegreeCounter.put(adjVertex.getId(), inDegreeCounter.get(adjVertex.getId()) - 1);
                            if (inDegreeCounter.get(adjVertex.getId()) == 0) vertexQueue.add(adjVertex);
                        }
                    }
                }

                if (inOrderVertexStack.size() != graph.getAllVertex().size()) {
                    return false;
                }

            }
        return true;
    }
}
