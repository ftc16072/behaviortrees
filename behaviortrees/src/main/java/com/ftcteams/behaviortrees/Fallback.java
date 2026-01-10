package com.ftcteams.behaviortrees;

import java.util.Arrays;
import java.util.List;

/**
 * Failover means that as soon as it hits a child with SUCCESS it returns SUCCESS.  If a child is RUNNING,
 * it returns RUNNING.  If all children return FAILURE, it returns FAILURE
 */
public class Fallback extends Node {
    List<Node> children;

    public Fallback(Node ... a) {
        this.children = Arrays.asList(a);
    }

    @Override
    public State tick(DebugTree debug, Object obj) {
        debug.startParent(this);
        State state = State.FAILURE;
        
        for (Node child : children) {
            debug.addNode(child);
            state = child.tick(debug, obj);
            debug.updateNode(child, state);

            if (state == State.SUCCESS || state == State.RUNNING) {
                break;
            }
        }
        return state;
    }
}
