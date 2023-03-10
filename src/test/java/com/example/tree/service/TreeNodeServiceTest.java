package com.example.tree.service;

import com.example.tree.entity.TreeNode;
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
public class TreeNodeServiceTest {

    @Mock
    private NodeRepository nodeRepository;

    @InjectMocks
    private NodeService nodeService;

    @Test
    public void testCreateNode() {

        // create a test Node object
        TreeNode treeNode = new TreeNode();
        treeNode.setNodeValue(10);

        when(nodeRepository.save(any(TreeNode.class))).thenReturn(treeNode);

        // call the createNode() method of the NodeService
        int expectedId = 10;
        TreeNode savedTreeNode = nodeService.createNode(expectedId);

        // verify that the NodeRepository save() method was called exactly once with the same Node object
        verify(nodeRepository, times(1)).save(any(TreeNode.class));

        // assert that the returned Node object is the same as the input Node object
        assertEquals(treeNode, savedTreeNode);
    }

    @Test
    public void testUpdateNode() {
        // create a test Node object
        TreeNode treeNode = new TreeNode();
        treeNode.setNodeValue(10);

        // configure the mock NodeRepository to return the same object when save() is called
        when(nodeRepository.save(any(TreeNode.class))).thenReturn(treeNode);
        when(nodeRepository.findById(any(Long.class))).thenReturn(Optional.of(treeNode));

        // call the updateNode() method of the NodeService
        TreeNode updatedTreeNode = nodeService.updateNode(10l, 2);

        // verify that the NodeRepository save() method was called exactly once with the same Node object
        verify(nodeRepository, times(1)).save(treeNode);

        // assert that the returned Node object is the same as the input Node object
        assertEquals(treeNode, updatedTreeNode);
    }

    @Test
    public void testDeleteNode() {
        // create a test Node object
        TreeNode treeNode = new TreeNode();
        treeNode.setNodeValue(10);

        // configure the mock NodeRepository to do nothing when delete() is called
        doNothing().when(nodeRepository).delete(treeNode);
        when(nodeRepository.findById(any(Long.class))).thenReturn(Optional.of(treeNode));

        // call the deleteNode() method of the NodeService
        nodeService.deleteNode(10l);

        // verify that the NodeRepository delete() method was called exactly once with the same Node object
        verify(nodeRepository, times(1)).delete(treeNode);
    }
}