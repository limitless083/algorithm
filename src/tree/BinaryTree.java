package tree;

import java.util.ArrayDeque;

/**
 * Created by Victor on 2017/10/16.
 */
public class BinaryTree {
    Node root;

    public void iterativePreorder1(Node root) {
        ArrayDeque<Node> stack = new ArrayDeque<>(16);
        stack.push(root);

        while (!stack.isEmpty()) {
            Node parent = stack.pop();
            System.out.println(parent.data);

            if (parent.right != null) {
                stack.push(parent.right);
            }
            if (parent.left != null) {
                stack.push(parent.left);
            }

        }
    }

    public void iterativePreorder2(Node root) {
        ArrayDeque<Node> stack = new ArrayDeque<>(16);

        Node p = root;
        while (p != null) {
            System.out.println(p.data);
            stack.push(p);
            p = p.left;
        }

        while (!stack.isEmpty()) {
            p = stack.pop();
            p = p.right;
            while (p != null) {
                System.out.println(p.data);
                p = p.left;
            }
        }
    }

    public void iterativePreorder3(Node root) {
        ArrayDeque<Node> stack = new ArrayDeque<>();
        Node p = root;
        while (!stack.isEmpty() || p != null) {
            while (p != null) {
                System.out.println(p.data);
                stack.push(p);
                p = p.left;
            }

            p = stack.pop();
            p = p.right;
        }
    }

    public void recursivePreorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.data);
        recursivePreorder(root.left);
        recursivePreorder(root.right);
    }

    public void iterativeInorder(Node root) {
        ArrayDeque<Node> stack = new ArrayDeque<>(16);
        Node p = root;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        while (!stack.isEmpty()) {
            p = stack.pop();
            System.out.println(p.data);
            p = p.right;
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
        }
    }

    public void iterativeInorder2(Node root) {
        ArrayDeque<Node> stack = new ArrayDeque<>();
        Node p = root;
        while (!stack.isEmpty() || p != null) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }

            p = stack.pop();
            System.out.println(p.data);
            p = p.right;
        }
    }

    public void recursiveInorder(Node root) {
        if (root == null) {
            return;
        }
        recursiveInorder(root.left);
        System.out.println(root.data);
        recursiveInorder(root.right);
    }

    public void iterativePostorder(Node root) {
        ArrayDeque<Node> stack = new ArrayDeque<>();
        Node p = root;
        Node lastVisitNode = null;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }

        while (!stack.isEmpty()) {
            p = stack.peek();
            if (p.right == null || p.right == lastVisitNode) {
                stack.pop();
                System.out.println(p.data);
                lastVisitNode = p;
            } else {
                p = p.right;
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
            }
        }
    }

    public void iterativePostorder2(Node root) {
        ArrayDeque<Node> stack = new ArrayDeque<>();
        Node p = root;
        Node lastVisitNode = null;
        while (!stack.isEmpty() || p != null) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }

            p = stack.peek();
            if (p.right == null || p.right == lastVisitNode) {
                p = stack.pop();
                System.out.println(p.data);
                lastVisitNode = p;
                p = null;
            } else {
                p = p.right;
            }
        }
    }

    public void iterativePostorder3(Node root) {
        ArrayDeque<Node> stack1 = new ArrayDeque<>();
        ArrayDeque<Node> stack2 = new ArrayDeque<>();
        Node p = root;
        while (!stack1.isEmpty() || p != null) {
            while (p != null) {
                stack2.push(p);
                stack1.push(p);
                p = p.right;
            }

            p = stack1.pop();
            p = p.left;
        }
        while (!stack2.isEmpty()) {
            System.out.println(stack2.pop().data);
        }
    }

    public void recursivePostorder(Node root) {
        if (root == null) {
            return;
        }
        recursivePostorder(root.left);
        recursivePostorder(root.right);
        System.out.println(root.data);
    }

    public void levelOrder(Node root) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        Node p;
        queue.add(root);
        while (!queue.isEmpty()) {
            p = queue.poll();
            System.out.println(p.data);
            if (p.left != null) {
                queue.add(p.left);
            }

            if (p.right != null) {
                queue.add(p.right);
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(3);
        tree.root.left = new Node(4);
        tree.root.right = new Node(5);
        tree.root.left.left = new Node(6);
        tree.root.left.right = new Node(7);
        tree.root.left.left.left = new Node(8);

        System.out.println("===== iterative preorder1 =====");
        tree.iterativePreorder1(tree.root);
        System.out.println("===== iterative preorder2 =====");
        tree.iterativePreorder2(tree.root);
        System.out.println("===== iterative preorder3 =====");
        tree.iterativePreorder3(tree.root);
        System.out.println("===== recursive preorder =====");
        tree.recursivePreorder(tree.root);
        System.out.println("===== iterative inorder =====");
        tree.iterativeInorder(tree.root);
        System.out.println("===== iterative inorder2 =====");
        tree.iterativeInorder2(tree.root);
        System.out.println("===== recursive inorder =====");
        tree.recursiveInorder(tree.root);
        System.out.println("===== iterative postorder =====");
        tree.iterativePostorder(tree.root);
        System.out.println("===== iterative postorder2 =====");
        tree.iterativePostorder2(tree.root);
        System.out.println("===== iterative postorder3 =====");
        tree.iterativePostorder3(tree.root);
        System.out.println("===== recursive postorder =====");
        tree.recursivePostorder(tree.root);
        System.out.println("===== iterative levelorder =====");
        tree.levelOrder(tree.root);
    }

    static class Node {
        Node left, right;
        int data;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }
}
