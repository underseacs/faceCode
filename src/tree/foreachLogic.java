package tree;

import node.TreeNode;

import javax.swing.*;
import java.nio.file.StandardWatchEventKinds;
import java.util.Objects;
import java.util.Stack;

public class foreachLogic {

    public void beforeForeach(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();


        if (Objects.isNull(root)) {
            return;
        }

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            System.out.print(treeNode.getValue());

            if (Objects.nonNull(treeNode.getRight())) {
                stack.push(treeNode.getRight());
            }
            if (Objects.nonNull(treeNode.getLeft())) {
                stack.push(treeNode.getLeft());
            }
        }

        System.out.printf("\n");
        return;
    }


    public void middleForeach(TreeNode root) {

        if (Objects.isNull(root)) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);


        TreeNode last = null;

        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.peek();
            if (Objects.isNull(treeNode.getLeft()) || last == treeNode.getLeft()) {
                stack.pop();
                System.out.print(treeNode.getValue());
                last = treeNode;
                if (Objects.nonNull(treeNode.getRight())) {
                    stack.push(treeNode.getRight());
                } else {

                    if (!stack.isEmpty()) {
                        treeNode = stack.pop();
                        System.out.print(treeNode.getValue());
                        last = treeNode;

                        if (Objects.nonNull(treeNode.getRight())) {
                            stack.push(treeNode.getRight());
                        }
                    }
                }
                continue;
            }

            if (Objects.nonNull(treeNode.getLeft())) {
                stack.push(treeNode.getLeft());
                continue;
            }
        }

        System.out.printf("\n");

    }


    public void afterForeach(TreeNode root) {

        if (Objects.isNull(root)) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        TreeNode lastPop = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();

            if ((Objects.isNull(cur.getLeft()) && Objects.isNull(cur.getRight())) || (lastPop != null && (lastPop == cur.getLeft() || lastPop == cur.getRight()))) {
                stack.pop();
                System.out.print(cur.getValue());

                lastPop = cur;
                continue;
            }


            if (Objects.nonNull(cur.getRight())) {
                stack.push(cur.getRight());
            }

            if (Objects.nonNull(cur.getLeft())) {
                stack.push(cur.getLeft());
            }


        }
        System.out.print("\n");

    }


    public static void main(String[] args) {
        // 创建测试二叉树:
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5

        TreeNode root = new TreeNode();
        root.setValue(1);

        TreeNode node2 = new TreeNode();
        node2.setValue(2);
        TreeNode node3 = new TreeNode();
        node3.setValue(3);
        TreeNode node4 = new TreeNode();
        node4.setValue(4);
        TreeNode node5 = new TreeNode();
        node5.setValue(5);

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);

        foreachLogic logic = new foreachLogic();

        System.out.println("先序遍历结果:");
        logic.beforeForeach(root);
        logic.middleForeach(root);
        logic.afterForeach(root);
        System.out.println();

        // 测试空树
        System.out.println("空树遍历结果:");
        logic.beforeForeach(null);
        logic.middleForeach(null);
        logic.afterForeach(null);
        System.out.println();

        // 测试单节点树
        System.out.println("单节点树遍历结果:");
        TreeNode singleNode = new TreeNode();
        singleNode.setValue(100);

        logic.beforeForeach(singleNode);
        logic.middleForeach(singleNode);
        logic.afterForeach(singleNode);
    }
}
