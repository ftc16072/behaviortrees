package com.ftcteams.behaviortrees;

import androidx.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DebugTree {
    static class NodeInfo {
        int indent;
        public Node node;
        Node.State state = Node.State.RUNNING;

        NodeInfo(int indent, Node node){
            this.indent = indent;
            this.node = node;
        }

        @NonNull
        public String toString(){
            String prefix = new String(new char[indent]).replace("\0", "-");
            String name = node.getClass().getSimpleName();

            switch(state){
                case FAILURE:
                    return prefix + name + ":F\n";
                case SUCCESS:
                    return prefix + name + ":S\n";
                case RUNNING:
                    return prefix + name + ":R\n";
            }
            return "";
        }
    }
    List<NodeInfo> nodeInfoList;
    Stack<Node> parents = new Stack<>();

    public DebugTree(){
        reset();
    }
    public void reset(){
        nodeInfoList = new LinkedList<>();
        parents = new Stack<>();
    }
    public void startParent(Node parent){
        parents.push(parent);
    }

    public void addNode(Node node){
        nodeInfoList.add(new NodeInfo(parents.size(), node));
    }

    public void updateNode(Node node, Node.State newState){
        if (node == parents.peek()){
            parents.pop();
        }
        for (NodeInfo nodeInfo : nodeInfoList){
            if(nodeInfo.node == node){
                nodeInfo.state = newState;
            }
        }
    }

    @NonNull
    public String toString(){
        StringBuilder retString = new StringBuilder();
        for(NodeInfo nodeInfo : nodeInfoList){
            retString.append(nodeInfo.toString());
        }
        return retString.toString();
    }
}
