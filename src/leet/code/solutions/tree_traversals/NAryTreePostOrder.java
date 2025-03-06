package leet.code.solutions.tree_traversals;

import leet.code.solutions.binary_tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class NAryTreePostOrder {

    public static void main(String[] args) {
      Node root = new Node(1);
      Node root1 = new Node(3);
      Node root2 = new Node(2);
      Node root3 = new Node(4);
      Node root4 = new Node(5);
      Node root5 = new Node(6);

      List<Node> root1Children = new ArrayList<>();

        root1Children.add(root1);
        root1Children.add(root2);
        root1Children.add(root3);

        root.children = root1Children;


        List<Node> root3Children = new ArrayList<>();

        root3Children.add(root4);
      root3Children.add(root5);

      root3.children = root3Children;

      List<Integer> postOrder  = postOrder(root);
      System.out.println(postOrder);

    }


    private static List<Integer> postOrder(Node root) {
        LinkedList<Integer> res = new LinkedList<>();
        if(root==null) return res;

        LinkedList<Node> stack = new LinkedList<>();
        stack.add(root);

        while(!stack.isEmpty()){

            Node node = stack.pollLast();
             res.addFirst(node.val);

            if(node.children!=null){
                for(Node child : node.children){
                    stack.add(child);
                }
            }
        }

        return res;
    }
}
