package core;

import java.util.LinkedList;

public class Level2Tree {
    class Parent {
        boolean reverse;
        int size;
        int id; //WTF?

        Parent leftParent;
        Parent rightParent;

        Child leftChild;
        Child rightChild;
    }

    class Child {
        int id; //WTF?
        Vertex vertex; //Might change to index.

        Parent parent;

        Child leftChild;
        Child rightChild;
    }
}
