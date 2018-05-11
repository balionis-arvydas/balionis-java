package com.balionis.paint;

import java.util.List;

public interface Canvas extends Cloneable {

    void replace(int x, int y, char c);

    List<String> getLines();

    Object clone();

}

