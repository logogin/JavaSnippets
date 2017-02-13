package logogin.blogspot;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution2 {

    private static class Node {
        Node left, right;
        int data;

        Node(int newData) {
            left = right = null;
            data = newData;
        }
    }

    private static Node insert(Node node, int data) {
        if (node == null) {
            node = new Node(data);
        } else {
            if (data < node.data) {
                node.left = insert(node.left, data);
            } else {
                node.right = insert(node.right, data);
            }
        }
        return (node);
    }

    private static Node addRandomElement(Node node, int data, int index) {
        if (node == null) {
            node = new Node(data);
        } else {
            if (index <= 2) {
                node.left = addRandomElement(node.left, data, index);
            } else {
                node.right = addRandomElement(node.right, data, index);
            }

        }
        return (node);
    }

    static int diameterOfTree(Node root) {
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return leftDepth + rightDepth;
    }

    static int maxDepth(Node node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);
        if (leftDepth > rightDepth) {
            return leftDepth + 1;
        }
        return rightDepth + 1;
    }
}
