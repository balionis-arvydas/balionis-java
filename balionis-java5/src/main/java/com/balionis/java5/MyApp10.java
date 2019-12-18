package com.balionis.java5;

import java.util.function.Function;

// create a simple binary tree, populate it with some values and then write a Depth-first search algorithm to get all
// the values of the tree and return the sum of it
public class MyApp10 {

    static class MyLeaf<Integer> {
        Integer value;
        MyLeaf<Integer> left;
        MyLeaf<Integer> right;

        public MyLeaf(Integer value, MyLeaf<Integer> left, MyLeaf<Integer> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Integer eval(Function<MyLeaf<Integer>, Integer> f) {
            return f.apply(this);
        }

        @Override
        public String toString() {
            return "{ value=" + value + ", left=" + left + ", right=" + right + "}";
        }
    }

    static class MyDFS implements Function<MyLeaf<Integer>, Integer> {

        public Integer apply(MyLeaf<Integer> leaf) {
            Integer res = 0;
            if (leaf.left != null) {
                res += leaf.left.eval(this);
            }
            if (leaf.right != null) {
                res += leaf.right.eval(this);
            }
            return (res + leaf.value);
        }
    };

    static class MyBFS implements Function<MyLeaf<Integer>, Integer> {

        public Integer apply(MyLeaf<Integer> leaf) {
            Integer res = leaf.value;
            if (leaf.left != null) {
                res += leaf.left.eval(this);
            }
            if (leaf.right != null) {
                res += leaf.right.eval(this);
            }
            return res;
        }
    };

    public static void main(String[] args) {

        MyLeaf<Integer> tree1 = buildTree1();

        Integer dfs = tree1.eval(new MyDFS());
        System.out.println("main: dfs=" + dfs);

        Integer bfs = tree1.eval(new MyBFS());
        System.out.println("main: bfs=" + bfs);

        System.out.println("main: done");
    }

    public static MyLeaf<Integer> buildTree1() {
        MyLeaf<Integer> l4 = new MyLeaf<>(4, null, null);
        MyLeaf<Integer> l2 = new MyLeaf<>(2, null, l4);
        MyLeaf<Integer> l3 = new MyLeaf<>(3, null, null);
        MyLeaf<Integer> l1 = new MyLeaf<>(1, l2, l3);
        return l1;
    }
}

