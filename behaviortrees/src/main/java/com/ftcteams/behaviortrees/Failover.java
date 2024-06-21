package com.ftcteams.behaviortrees;

import java.util.Arrays;
import java.util.List;

public class Failover extends Node {
    List<Node> children;

    public Failover(Node ... a) {
        this.children = Arrays.asList(a);
    }

    @Override
    public State tick(DebugTree debug, Object obj) {
        debug.startParent(this);
        for (Node child : children) {
            debug.addNode(child);
            State state = child.tick(debug, obj);
            debug.updateNode(child, state);

            if (state == State.SUCCESS) {
                return State.SUCCESS;
            } else if (state == State.RUNNING) {
                return State.RUNNING;
            }
        }
        return State.FAILURE;
    }
}
