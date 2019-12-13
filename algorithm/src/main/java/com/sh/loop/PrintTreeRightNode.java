package com.sh.loop;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Description: 打印二叉树最右节点
 * @Author: NickyRing
 * @Date: 2019-12-13 11:04
 */
public class PrintTreeRightNode {

    public static void main(String[] args) {

        int nums[] = {1, 2, 3, 4, 0, 5, 6, 0, 7};
        Node node = initBTree(nums, 9);
        printTreeRightNode(node);
    }

    public static Node initBTree(int elements[], int size) {
        if (size < 1) {
            return null;
        }
        // 放入数组
        Node[] nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            if (elements[i] == 0) {
                nodes[i] = null;
            } else {
                nodes[i] = new Node(elements[i]);
            }
        }
        //
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.offer(nodes[0]);

        Node node;
        int index = 1;
        while (index < size) {
            node = ((ArrayDeque<Node>) queue).getFirst();
            queue.poll();
            Node tmpNode = nodes[index++];
            if (tmpNode != null) {
                queue.offer(tmpNode);
                node.setLeft(((ArrayDeque<Node>) queue).getLast());
            }
            tmpNode = nodes[index++];
            if (tmpNode != null) {
                queue.offer(tmpNode);
                node.setRight(((ArrayDeque<Node>) queue).getLast());
            }
        }
        return nodes[0];
    }

    /**
     * @Description: 按层遍历使用队列。
     * @Author: NickyRing
     * @Date: 2019-12-13 15:11
     */
    public static void printTreeRightNode(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(head);
        int start = 0;//设置一层的开始位置
        int end = 1; //设置一层的结束位置
        System.out.println(((ArrayDeque<Node>) queue).peekLast().getValue());
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            start++;
            if (node.getLeft() != null) { //添加左孩子
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) { //添加右孩子
                queue.add(node.getRight());
            }
            if (start == end) { //当到达末尾时
                start = 0;
                end = queue.size();//这层完事时，因为存的都是下一层的孩子，所以队列的大小就是孩子的个数。
                Node tmpNode = ((ArrayDeque<Node>) queue).peekLast();
                if (tmpNode != null) {
                    System.out.println(tmpNode.getValue());
                }
            }
        }
    }
}
