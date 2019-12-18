package com.balionis.java5;

// detect a cycle in a linked list
public class MyApp4 {

    static class MyEntry<T> {
        T value;
        MyEntry<T> next;

        public MyEntry(T value, MyEntry<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {

        MyEntry<String> good =
                new MyEntry<>("1",
                new MyEntry<>("2",
                new MyEntry<>("3", null)));

        System.out.println("main: test(true)=" + test(good));

        MyEntry<String> last = new MyEntry<>("3", null);
        MyEntry<String> bad =
                new MyEntry<>("1",
                new MyEntry<>("2", last));
        last.next = bad;

        System.out.println("main: test(false)=" + test(bad));

        System.out.println("main: done");
    }

    static boolean test(MyEntry<String> line) {
        MyEntry<String> slow = line;
        MyEntry<String> fast = line.next;
        while(true) {
            if (slow == null || fast == null || slow.next == null || fast.next == null) {
                return true;
            }
            if (slow.next == fast.next) {
                return false;
            }
            slow = slow.next;
            fast = fast.next != null ? fast.next.next : null;
        }
    }
}

