package de.tukl.programmierpraktikum2020.p2.a1;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    @Test
    public void exampleTeam22() throws Exception {
        Graph<String, Integer> graph = new GraphImpl<>();
        assertTrue(graph.getNodeIds().isEmpty());

        assertEquals(1, graph.addNode("node1"));
        assertNotNull(graph);
        assertEquals(2, graph.addNode("node2"));
        assertEquals(3, graph.addNode("node3"));
        assertEquals(4, graph.addNode("node4"));
        assertThrows(InvalidNodeException.class, () -> graph.addEdge(0, 0, 0));

        assertEquals("node1", graph.getData(1));
        assertEquals("node2", graph.getData(2));
        assertEquals("node3", graph.getData(3));
        assertEquals("node4", graph.getData(4));
        assertThrows(InvalidNodeException.class, () -> graph.getData(graph.getNodeIds().size() + 1));
        assertThrows(InvalidNodeException.class, () -> graph.getData(0));

        graph.setData(4, "node444");
        assertEquals(graph.getData(4), "node444");
        assertThrows(InvalidNodeException.class, () -> graph.setData(0, "node2"));

        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(3, 3, 2);
        graph.addEdge(4, 3, 1);
        graph.addEdge(4, 2, 2);
        graph.addEdge(3, 2, 2);
        assertThrows(InvalidNodeException.class, () -> graph.addEdge(0, 0, 0));
        assertThrows(InvalidNodeException.class, () -> graph.addEdge(2, 6, 0));
        assertThrows(InvalidNodeException.class, () -> graph.addEdge(graph.getNodeIds().size() + 1, 3, 6));
        assertThrows(InvalidNodeException.class, () -> graph.addEdge(4, 0, new Random().nextInt()));
        assertThrows(DuplicateEdgeException.class, () -> graph.addEdge(1, 3, new Random().nextInt()));
        assertThrows(DuplicateEdgeException.class, () -> graph.addEdge(4, 2, new Random().nextInt()));

        assertEquals(graph.getWeight(1, 2), 1);
        assertEquals(graph.getWeight(1, 3), 1);
        assertEquals(graph.getWeight(3, 3), 2);
        assertEquals(graph.getWeight(4, 3), 1);
        assertEquals(graph.getWeight(4, 2), 2);
        assertEquals(graph.getWeight(3, 2), 2);
        assertThrows(InvalidEdgeException.class, () -> graph.getWeight(4, 4));
        assertThrows(InvalidEdgeException.class, () -> graph.getWeight(1, 15));
        assertThrows(InvalidEdgeException.class, () -> graph.getWeight(2, 4));
        assertThrows(InvalidEdgeException.class, () -> graph.getWeight(10, new Random().nextInt()));

        graph.setWeight(1, 2, 6);
        graph.setWeight(3, 3, 36);
        assertEquals(graph.getWeight(1, 2), 6);
        assertEquals(graph.getWeight(3, 3), 36);
        assertThrows(InvalidEdgeException.class, () -> graph.setWeight(1, 4, 0));
        assertThrows(InvalidEdgeException.class, () -> graph.setWeight(0, new Random().nextInt(), 7));
        assertThrows(InvalidEdgeException.class, () -> graph.setWeight(new Random().nextInt(), 32, 2));
        assertThrows(InvalidEdgeException.class, () -> graph.setWeight(4, 14, 7));

        graph.getNodeIds().add(1);
        assertFalse(graph.getNodeIds().isEmpty());
        assertTrue(graph.getNodeIds().contains(1));
        graph.getNodeIds().add(2);
        graph.getNodeIds().add(3);
        graph.getNodeIds().add(4);
        assertEquals(graph.getNodeIds().size(), 4);

        assertTrue(graph.getIncomingNeighbors(1).isEmpty());
        assertEquals(graph.getIncomingNeighbors(2), Set.of(1, 3, 4));
        assertEquals(graph.getIncomingNeighbors(3), Set.of(1, 3, 4));
        assertFalse(graph.getIncomingNeighbors(4).contains(new Random().nextInt()));

        assertThrows(InvalidNodeException.class, () -> graph.getIncomingNeighbors(0));
        assertThrows(InvalidNodeException.class, () -> graph.getIncomingNeighbors(graph.getNodeIds().size() + 1));

        assertEquals(graph.getOutgoingNeighbors(1), Set.of(2, 3));
        assertEquals(graph.getOutgoingNeighbors(2), Set.of());
        assertEquals(graph.getOutgoingNeighbors(3), Set.of(2, 3));
        assertEquals(graph.getOutgoingNeighbors(4), Set.of(2, 3));

        assertThrows(InvalidNodeException.class, () -> graph.getOutgoingNeighbors(0));
        assertThrows(InvalidNodeException.class, () -> graph.getIncomingNeighbors(graph.getNodeIds().size() + 1));

    }
}
