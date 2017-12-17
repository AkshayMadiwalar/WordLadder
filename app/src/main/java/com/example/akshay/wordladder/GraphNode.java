package com.example.akshay.wordladder;

import java.util.ArrayList;

/**
 * Created by Akshay on 17-12-2017.
 */
public class GraphNode {
    String word;
    ArrayList<String> neighbours;
    GraphNode(String s)
    {
        word=s;
        neighbours=new ArrayList<>();
    }
}
