package com.example.tree.service;

import com.example.tree.entity.Node;
import com.example.tree.repository.NodeRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NodeServiceTest {
    @Autowired
    private NodeService nodeService;

    @Autowired
    private NodeRepository nodeRepository;

    @Test
    public void testCreateNode() {
        Node node = nodeService.createNode(10);
        assertNotNull(node.getId());
        assertEquals(10, node.getValue());
    }

    @Test
    public void testDeleteNode() {
        Node node = nodeService.createNode(10);
        Long id = node.getId();
        nodeService.deleteNode(id);
        assertFalse(nodeRepository.existsById(id));
    }

    @Test
    public void testUpdateNode() {
        Node node = nodeService.createNode(10);
        Long id = node.getId();
        Node updatedNode = nodeService.updateNode(id, 20);
        assertEquals(20, updatedNode.getValue());
    }

    @Test
    public void testAddChildNode() {
        Node parentNode = nodeService.createNode(10);
        Node childNode = nodeService.addChildNode(parentNode.getId(), 20);
        assertNotNull(childNode.getId());
        assertEquals(20, childNode.getValue());
        assertEquals(parentNode, childNode.getParent());
    }
}