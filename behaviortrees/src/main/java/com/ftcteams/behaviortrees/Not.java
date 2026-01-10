package com.ftcteams.behaviortrees;

/**
 * This reverses what a child node says.  Typically used on Conditions, but can be used anywhere
 */
public class Not extends Node {
    Node child;

    public Not(Node child) {
        this.child = child;
    }

    @Override
    public State tick(DebugTree debug, Object obj) {
        State state = child.tick(debug, obj);
        
        switch (state) {
            case State.SUCCESS:
                return State.FAILURE;
            case State.FAILURE:
                return State.SUCCESS;
            case State.RUNNING:
                return State.RUNNING;
        }
    }
}
