package leet.code.solutions.binary_tree;

import java.util.ArrayList;
import java.util.List;

public class TernaryTreePath {

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1);

        Node<Integer> root1 = new Node<>(2);
        Node<Integer> root2 = new Node<>(4);
        Node<Integer> root3 = new Node<>(6);

        Node<Integer> root4 = new Node<>(3);

        root.children = List.of( root1, root2, root3);

        root1.children = List.of(root4);

        List<List<Integer>> path = ternaryTreePaths(root);
        System.out.println(path);


    }

    public static List<List<Integer>> ternaryTreePaths(Node<Integer> root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> currPath = new ArrayList<>();
       // currPath.add(root.val);

        dfs(root, currPath, res);

        return res;
    }


    private static void dfs(Node<Integer> node, List<Integer> currPath, List<List<Integer>> res) {

        currPath.add(node.val);

        // exit condition, reached leaf node, append paths to results

        if(node.children.isEmpty()){
            res.add(new ArrayList<>(currPath));

        }else{
            for(Node<Integer> child : node.children){

                dfs(child, currPath, res);

            }
        }

        // Backtrack - remove current node from path before returning
        currPath.remove(currPath.size() - 1);

    }



    private static class Node<T> {
        public T val;
        public List<Node<T>> children;

        public Node(T val) {
            this(val, new ArrayList<>());
        }

        public Node(T val, List<Node<T>> children) {
            this.val = val;
            this.children = children;
        }
    }
}
