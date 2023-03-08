package com.example.tree.service;

import com.example.tree.entity.Node;
import com.example.tree.repository.NodeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NodeServiceTest {

    @Mock
    private NodeRepository nodeRepository;

    @InjectMocks
    private NodeService nodeService;

    @Test
    public void testCreateNode() {

        // create a test Node object
        Node node = new Node();
        node.setValue(10);

        when(nodeRepository.save(any(Node.class))).thenReturn(node);

        // call the createNode() method of the NodeService
        int expectedId = 10;
        Node savedNode = nodeService.createNode(expectedId);

        // verify that the NodeRepository save() method was called exactly once with the same Node object
        verify(nodeRepository, times(1)).save(any(Node.class));

        // assert that the returned Node object is the same as the input Node object
        assertEquals(node, savedNode);
    }

    @Test
    public void testUpdateNode() {
        // create a test Node object
        Node node = new Node();
        node.setValue(10);

        // configure the mock NodeRepository to return the same object when save() is called
        when(nodeRepository.save(any(Node.class))).thenReturn(node);
        when(nodeRepository.findById(any(Long.class))).thenReturn(Optional.of(node));

        // call the updateNode() method of the NodeService
        Node updatedNode = nodeService.updateNode(10l, 2);

        // verify that the NodeRepository save() method was called exactly once with the same Node object
        verify(nodeRepository, times(1)).save(node);

        // assert that the returned Node object is the same as the input Node object
        assertEquals(node, updatedNode);
    }

    @Test
    public void testDeleteNode() {
        // create a test Node object
        Node node = new Node();
        node.setValue(10);

        // configure the mock NodeRepository to do nothing when delete() is called
        doNothing().when(nodeRepository).delete(node);
        when(nodeRepository.findById(any(Long.class))).thenReturn(Optional.of(node));

        // call the deleteNode() method of the NodeService
        nodeService.deleteNode(10l);

        // verify that the NodeRepository delete() method was called exactly once with the same Node object
        verify(nodeRepository, times(1)).delete(node);
    }
}