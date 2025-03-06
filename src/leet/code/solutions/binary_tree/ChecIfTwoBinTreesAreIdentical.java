package leet.code.solutions.binary_tree;

/*
https://www.techiedelight.com/check-if-two-binary-trees-are-identical-not-iterative-recursive/


 */
public class ChecIfTwoBinTreesAreIdentical {

    public static void main(String[] args) {
        TreeNode<Integer> firstTree = new TreeNode<>(1);
        TreeNode<Integer> firstTreeLeft = new TreeNode<>(2);
        TreeNode<Integer> firstTreeRight = new TreeNode<>(3);
        firstTree.left = firstTreeLeft;
        firstTree.right = firstTreeRight;

        TreeNode<Integer> secondTree = new TreeNode<>(1);
        TreeNode<Integer> secondTreeLeft = new TreeNode<>(2);
        TreeNode<Integer> secondTreeRight = new TreeNode<>(3);
        secondTree.left = secondTreeLeft;
        secondTree.right = secondTreeRight;

        boolean areTreesIdentical = isIdenticalRecursive(firstTree, secondTree);
        System.out.println(areTreesIdentical);
    }

    public static boolean isIdenticalRecursive(TreeNode<Integer> firstTree, TreeNode<Integer> secondTree){
        // if both trees are empty, return true
        if(firstTree == null && secondTree == null) return true;

        return (firstTree!= null && secondTree !=null)
                &&
                (firstTree.val.equals(secondTree.val))
                &&
       isIdenticalRecursive(firstTree.left, secondTree.left)
                        &&
       isIdenticalRecursive(firstTree.right, secondTree.right);
    }

    }
