package com.ftcteams.behaviortrees_javalib;

//Not reverses a value
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
