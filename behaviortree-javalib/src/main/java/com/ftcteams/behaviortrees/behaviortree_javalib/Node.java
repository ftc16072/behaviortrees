package com.ftcteams.behaviortrees.behaviortree_javalib;

abstract public class Node {
    /**
     * 3 states of all nodes
     */
    public enum State {
        SUCCESS,
        FAILURE,
        RUNNING
    }

    /**
     * each tick will
     *
     * @return state of node
     */
    public abstract State tick(DebugTree debug, Object obj);


}


