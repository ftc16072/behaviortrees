package com.ftcteams.behaviortrees;

import java.util.Arrays;
import java.util.List;

/**
 * This executes all of the children and if more than the required succcesses are successful  it returns
 * SUCCESS.  Otherwise it returns RUNNING until all are done and then it returns FAILURE.
 *
 * If it is impossible the required number of successes to be met, it will return FAILURE early.
 * 
 * If you need RUNNING children to finish even if Paralle will return FAILURE, instead use ParallelNoBail.
 */
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
        int numFailed = 0;
        
        debug.startParent(this);
        
        for (Node child : children) {
            debug.addNode(child);
            State state = child.tick(debug, obj);
            debug.updateNode(child, state);

            switch (state) {
                case SUCCESS:
                    numSuccessful++;
                    if (numSuccesful >= requiredSuccesses) {
                        return State.SUCCESS;
                    }
                    break;
                    
                case FAILURE:
                    numFailed++;
                    if (children.size() - numFailed > requiredSuccesses) {
                        return State.FAILURE;
                    }
                    break;
            }
        }
        
        return State.RUNNING;
    }
}
