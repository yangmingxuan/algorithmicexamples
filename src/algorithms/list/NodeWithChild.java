package algorithms.list;

public class NodeWithChild {

    public int val;
    public NodeWithChild prev;
    public NodeWithChild next;
    public NodeWithChild child;

    public NodeWithChild() {}

    public NodeWithChild(int _val,NodeWithChild _prev,NodeWithChild _next,NodeWithChild _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }

}
