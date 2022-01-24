
package pers.hywel.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeUtils {

    /**
     * 根据array生成树
     */
    public static TreeNode genTreeFromArray(Integer[] array) {
        return genTreeRecursion(null, array, 0);
    }

    private static TreeNode genTreeRecursion(TreeNode root, Integer[] array, int index) {
        if (index < array.length) {
            if (array[index] == null) {
                return null;
            }
            root = new TreeNode(array[index]);
            root.left = genTreeRecursion(root.left, array, 2 * (index + 1) - 1);
            root.right = genTreeRecursion(root.left, array, 2 * (index + 1));
        }
        return root;
    }

    /**
     * 获取最大深度(1开始)
     *
     * @param root
     * @return
     */
    public static int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = treeDepth(root.left);
        int rightDepth = treeDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }


    /**
     * 树打印
     * 1
     * / \
     * 2   3
     * / \
     * 4   5
     */
    public static void printTree(TreeNode root, int layer) {
        if (root == null) {
            return;
        }
        // 父节点
        printRepeat(" ", 2 * layer);
        System.out.println(root.val);
        // 分层连线
        printRepeat(" ", 2 * layer - 1);
        if (root.left != null) {
            // 左竖线
            System.out.print("/");
        }
        printRepeat(" ", 1);
        if (root.right != null) {
            // 右竖线
            System.out.println("\\");
        }
        // 子节点
        printTree(root.left, layer - 1);
        printTree(root.right, layer - 1);
    }


    private static void printRepeat(String str, int repeats) {
        if (repeats <= 0) return;
        for (int i = 0; i < repeats; i++) {
            System.out.print(str);
        }
    }


    /**
     * 按树层次遍历
     */
    public static List<List<TreeNode>> treeLayerArray(TreeNode root) {
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        List<List<TreeNode>> result = new ArrayList<>();
        if (root != null) treeNodeQueue.offer(root);
        while (!treeNodeQueue.isEmpty()) {
            List<TreeNode> tmp = new ArrayList<>();
            for (int i = treeNodeQueue.size(); i > 0; i--) {
                TreeNode curNode = treeNodeQueue.poll();
                tmp.add(curNode);
                if (curNode != null) {
                    treeNodeQueue.offer(curNode.left);
                    treeNodeQueue.offer(curNode.right);
                }
            }
            result.add(tmp);
        }
        return result;
    }

    /**
     * 按照边遍历边打印方式
     */
    public static void prettyPrint(TreeNode root) {
        List<List<String>> lines = new ArrayList<List<String>>();

        List<TreeNode> level = new ArrayList<TreeNode>();
        List<TreeNode> next = new ArrayList<TreeNode>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<>();

            nn = 0;

            for (TreeNode n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    Integer aa = n.val;
                    if (aa != null) {
                        line.add(aa.toString());
                    }
//                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.left);
                    next.add(n.right);

                    if (n.left != null) nn++;
                    if (n.right != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<TreeNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }

    }
}