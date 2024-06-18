package com.ftcteams.behaviortrees.behaviortree_javalib;

import java.util.Arrays;
import java.util.List;

public class Sequence extends Node {
    List<Node> children;

    public Sequence(Node ... a) {
        this.children = Arrays.asList(a);
    }

    @Override
    public State tick(DebugTree debug, Object obj) {
        debug.startParent(this);

        for (Node child : children) {
            debug.addNode(child);
            State state = child.tick(debug, obj);
            debug.updateNode(child, state);

            if (state == State.FAILURE) {
                return State.FAILURE;
            } else if (state == State.RUNNING) {
                return State.RUNNING;
            }
        }
        return State.SUCCESS;
    }
}
