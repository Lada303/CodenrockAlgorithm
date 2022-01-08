package Lada303;

import java.util.*;

public class Task12v2 {

    class Node implements Comparable <Node>{
        final int idNode;
        int level;

        public Node(int idNode) {
            this.idNode = idNode;

        }

        void countLevel(int level) {
            this.level = level;
        }

        public int getLevel(int lookId) {
            return lookId == idNode ? level : 0;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(idNode, o.idNode);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idNode=" + idNode +
                    ", level=" + level +
                    '}';
        }
    }

    class ParentNode extends Node {
        Node left;
        Node right;

        public ParentNode(int idNode, Node left) {
            super(idNode);
            this.left = left;
            this.countLevel(1);
        }

        public ParentNode(int idNode, Node left, Node right) {
            super(idNode);
            this.left = left;
            this.right = right;
            this.countLevel(1);
        }

        @Override
        void countLevel(int level) {
            this.level = level++;
            if (left != null) {left.countLevel(level);};
            if (right != null) {right.countLevel(level);};
        }

        @Override
        public int getLevel(int lookId) {
            int leftAnswer = 0;
            int rightAnswer = 0;
            if (lookId == this.idNode) {
                return level;
            } else {
                if (left != null) {leftAnswer = left.getLevel(lookId);};
                if (right != null) {rightAnswer = right.getLevel(lookId);};
            }
            if (leftAnswer != 0) {return leftAnswer;}
            if (rightAnswer != 0) {return rightAnswer;}
            return 0;
        }

        @Override
        public String toString() {
            return "ParentNode{" +
                    "idNode=" + idNode +
                    ", level=" + level +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    class LeafNode extends Node {

        public LeafNode(int idNode) {
            super(idNode);
        }

        @Override
        void countLevel(int level) {
            this.level = level;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line;
        Deque<String> queue = new ArrayDeque<>();
        while (sc.hasNext()) {
            line = sc.next();
            if (line.isEmpty()) {
                continue;
            }
            queue.add(line);
        }
        System.out.println(queue);
        new Task12v2().run(queue);
    }

    private void run(Deque<String> queue) {

        Map<Integer, ParentNode> listNode = new TreeMap<>();
        int maxIdNode = 0;
        while (queue.iterator().hasNext()) {
            String[] arr = queue.pollLast().split("[ :,]");
            Node left = new LeafNode(Integer.parseInt(arr[1]));
            if (!listNode.isEmpty() && listNode.get(left.idNode) != null) {
                left = listNode.remove(left.idNode);
            }
            //System.out.println(left);
            ParentNode parent;
            if (arr.length == 3) {
                Node right = new LeafNode(Integer.parseInt(arr[2]));
                if (listNode.get(right.idNode) != null) {
                    right = listNode.remove(right.idNode);
                }
                //System.out.println(right);
                parent = new ParentNode(Integer.parseInt(arr[0]), left, right);
            } else {
                parent = new ParentNode(Integer.parseInt(arr[0]), left);
            }
            //System.out.println(parent);
            listNode.put(parent.idNode, parent);
            if(queue.isEmpty()) {
                maxIdNode = Integer.parseInt(arr[arr.length - 1]);
            }
        }
        System.out.println(listNode);
        ParentNode parent = listNode.remove(1);
        //parent.countLevel(1);
        System.out.println(parent);
        //System.out.println(nodeAndHisParent);

        System.out.println(parent.getLevel(17));
        System.out.println(parent.getLevel(5));
        System.out.println(parent.getLevel(50));




    }
}
