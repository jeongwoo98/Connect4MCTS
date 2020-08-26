package MCTree;

import MonteCarlo.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Node in a MCTS tree.
public class Node {
    State state;
    Node parent;
    List<Node> children;

    public Node(){
        state = new State();
        children = new ArrayList<>();
    }

    public Node(State pState){
        state = pState;
        children = new ArrayList<>();
    }

    public Node(State pState, Node pParent, List<Node> pChildren){
        state = pState;
        parent = pParent;
        children = pChildren;
    }

    public Node(Node pNode){
        children = new ArrayList<>();
        state = new State(pNode.getState());
        if(pNode.getParent()!=null){
            parent = pNode.getParent();
        }
        List<Node> children = pNode.getChildren();
        for(Node child: children){
            children.add(new Node(child));
        }
    }

    public State getState(){
        return state;
    }

    public void setState(State pState) {
        state = pState;
    }

    public Node getParent(){
        return parent;
    }

    public void setParent(Node pParent){
        parent = pParent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> pChildren) {
        children = pChildren;
    }

    public Node getRandomChild(){
        int nbOfPossibleMoves = children.size();
        int selectRandom = (int) (Math.random() * nbOfPossibleMoves);
        return children.get(selectRandom);
    }

    //TODO: don't really understand why highest visit count --> max score.
    public Node getChildWithMaxScore() {
        return Collections.max(children, Comparator.comparing(c -> {
            return c.getState().getVisitCount();
        }));
    }
}
