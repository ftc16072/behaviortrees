package com.ftcteams.behaviortrees;

import java.util.Arrays;
import java.util.List;

/**
 * This executes all of the children and if more than the required succcesses are successful  it returns
 * SUCCESS.  Otherwise it returns RUNNING until all are done and then it returns FAILURE.
 *
 * It will keep running even if there is no way it could be successful. If you want the program to bail early
 * if it cannot succeed, use Parallel instead.
 */
public class ParallelNoBail extends Node {
    List<Node> children;
    int requiredSuccesses;

    public ParallelNoBail(int requiredSuccesses, Node ... a) {
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
