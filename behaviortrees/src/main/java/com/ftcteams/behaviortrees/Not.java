package com.ftcteams.behaviortrees;

/**
 * This reverses what a child node says.  Typically used on Conditions, but can be used anywhere
 */
public class Not extends Node {
    Node child;

    public Not(Node a) {
        this.child = a;
    }

    @Override
    public State tick(DebugTree debug, Object obj) {
        State state = child.tick(debug, obj);

        if (state == State.FAILURE) {
            return State.SUCCESS;
        } else if (state == State.SUCCESS) {
            return State.FAILURE;
        }

        return state;
    }
}
