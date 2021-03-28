package de.tukl.programmierpraktikum2020.p2.a1;

import java.util.*;

public class GraphImpl<D, W> implements Graph<D, W> {
    private static class Edge<D, W> {
        W weightOfEdge;
        Knoten<D> startKnoten, zielKnoten;

        public Edge(Knoten<D> startKnoten, Knoten<D> zielKnoten) {
            this.startKnoten = startKnoten;
            this.zielKnoten = zielKnoten;
        }
    }

    private static class Knoten<D> {
        D dataKnoten;
        int identifier;

        public Knoten(int identifier) {
            this.identifier = identifier;
        }
    }


    Map<Integer, Knoten<D>> nodeMap = new HashMap<>();
    Map<Integer, Set<Integer>> inComing = new HashMap<>();
    Map<Integer, Set<Integer>> outGoing = new HashMap<>();
    Set<Integer> nodeIdSet = new HashSet<>();
    Set<Edge<D, W>> edgeSet = new HashSet<>();

    @Override
    public int addNode(D data) {
        int idNode = nodeIdSet.size() + 1;
        Knoten<D> dKnoten = new Knoten<>(idNode);
        dKnoten.dataKnoten = data;
        inComing.put(idNode, new HashSet<>());
        outGoing.put(idNode, new HashSet<>());
        nodeMap.put(idNode, dKnoten);
        nodeIdSet.add(idNode);
        return idNode;
    }

    @Override
    public D getData(int nodeId) throws InvalidNodeException {
        if (!nodeIdSet.contains(nodeId)) throw new InvalidNodeException(nodeId);
        D dataToGet = null;
        //Alternativ-Implementierung für Foreach-loop (Methode 1)
       for (int elem:nodeIdSet) {
            if (elem==nodeId){
                Knoten<D> tempNode = nodeMap.get(elem);
                dataToGet = tempNode.dataKnoten;
            }
        }
       //Methode 2
      /*  for (Map.Entry<Integer, Knoten<D>> entry : nodeMap.entrySet()) {
            int idDataToFind = entry.getKey();
            if (nodeId == idDataToFind) {
                Knoten<D> currNode = nodeMap.get(idDataToFind);
                dataToGet = currNode.dataKnoten;
            }
        } */
        //Methode 3
       /* Knoten<D> m = nodeMap.get(nodeId);
        if (m == null) throw new InvalidNodeException(nodeId);  //Diese Zeile bereitet Probleme.
        else dataToGet = m.dataKnoten; */
       return dataToGet;
    }

    @Override
    public void setData(int nodeId, D data) throws InvalidNodeException {
        if (!nodeIdSet.contains(nodeId)) throw new InvalidNodeException(nodeId);
        for (Map.Entry<Integer, Knoten<D>> entry : nodeMap.entrySet()) {
            int idDataToFind = entry.getKey();
            if (nodeId == idDataToFind) {
                Knoten<D> currNode = nodeMap.get(idDataToFind);
                currNode.dataKnoten = data;
            }
        }
    }

    @Override
    public void addEdge(int fromId, int toId, W weight) throws InvalidNodeException, DuplicateEdgeException {
        if (!nodeIdSet.contains(fromId)) throw new InvalidNodeException(fromId);
        if (!nodeIdSet.contains(toId)) throw new InvalidNodeException(toId);
        Knoten<D> nodeFromId = new Knoten<>(fromId);
        Knoten<D> nodeToId = new Knoten<>(toId);
        Edge<D, W> currEdge = new Edge<>(nodeFromId, nodeToId);
        currEdge.weightOfEdge = weight;
        currEdge.startKnoten = nodeFromId;
        currEdge.zielKnoten = nodeToId;
        /*funktionniert nicht, d.h Exception would'nt be throw.
        if (edgeSet.contains(currEdge)) throw new DuplicateEdgeException(fromId, toId); */

        /*just 87% in the missed Branches
        if (getIncomingNeighbors(toId).contains(fromId) && getOutgoingNeighbors(fromId).contains(toId)){
            throw new DuplicateEdgeException(fromId, toId);
        } */

        /*Just 87% in the missed Branches
        if (inComing.get(toId).contains(fromId) && outGoing.get(fromId).contains(toId)){
            throw new DuplicateEdgeException(fromId,toId);
        } */

        for (Edge<D,W> edge : edgeSet){
            if (edge.startKnoten.identifier==fromId && edge.zielKnoten.identifier==toId){
                throw new DuplicateEdgeException(fromId, toId);
            }
        }
        edgeSet.add(currEdge);
        nodeIdSet.add(fromId);
        nodeIdSet.add(toId);
        inComing.get(toId).add(fromId);
        outGoing.get(fromId).add(toId);


    }

    @Override
    public W getWeight(int fromId, int toId) throws InvalidEdgeException {
        if (!nodeIdSet.contains(fromId) || !nodeIdSet.contains(toId)) throw new InvalidEdgeException(fromId, toId);

        W weightToGet = null;
        for (Edge<D, W> elem : edgeSet) {
            if ((elem.zielKnoten.identifier == toId) && (elem.startKnoten.identifier == fromId)) {
                weightToGet = elem.weightOfEdge;
            }
        }
        if (weightToGet == null) throw new InvalidEdgeException(fromId, toId);
        return weightToGet;
    }

    @Override
    public void setWeight(int fromId, int toId, W weight) throws InvalidEdgeException {
        if (!nodeIdSet.contains(fromId) || !nodeIdSet.contains(toId)) throw new InvalidEdgeException(fromId, toId);
        W currWeight = null;
        for (Edge<D, W> elem : edgeSet) {
            if ((elem.zielKnoten.identifier == toId) && (elem.startKnoten.identifier == fromId)) {
                currWeight = elem.weightOfEdge;
                elem.weightOfEdge = weight;
            }
        }
        if (currWeight == null) throw new InvalidEdgeException(fromId, toId);
    }

    @Override
    public Set<Integer> getNodeIds() {
        return nodeIdSet;
    }

    @Override
    public Set<Integer> getIncomingNeighbors(int nodeId) throws InvalidNodeException {
        if (!nodeIdSet.contains(nodeId)) throw new InvalidNodeException(nodeId);
        return inComing.get(nodeId);
    }

    @Override
    public Set<Integer> getOutgoingNeighbors(int nodeId) throws InvalidNodeException {
        if (!nodeIdSet.contains(nodeId)) throw new InvalidNodeException(nodeId);
        return outGoing.get(nodeId);
    }
}