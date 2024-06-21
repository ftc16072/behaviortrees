package com.ftcteams.behaviortrees;

import java.util.Arrays;
import java.util.List;

public class Parallel extends Node {
    List<Node> children;
    int requiredSuccesses;

    public Parallel(int requiredSuccesses, Node ... a) {
        this.children = Arrays.asList(a);
        this.requiredSuccesses = requiredSuccesses;
    }

    @Override
    public State tick(DebugTree debug, Object obj) {
        int numSuccessful = 0;
        boolean anyRunning = false;
        debug.startParent(this);
        for (Node child : children) {
            debug.addNode(child);
            State state = child.tick(debug, obj);
            debug.updateNode(child, state);

            if (state == State.SUCCESS) {
                numSuccessful += 1;
                if (numSuccessful >= requiredSuccesses){
                    return State.SUCCESS;
                }
            } else if (state == State.RUNNING) {
                anyRunning = true;
            }
        }
        if (anyRunning){
            return State.RUNNING;
        }
        return State.FAILURE;
    }
}
