package com.example.tree.service;

import com.example.tree.entity.Node;
import com.example.tree.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NodeService {
    @Autowired
    private NodeRepository nodeRepository;

    public Node createNode(int value) {
        Node node = new Node();
        node.setValue(value);
        return nodeRepository.save(node);
    }

    public void deleteNode(Long id) {
        Node node = nodeRepository.findById(id).orElseThrow(() -> new RuntimeException("Node not found"));
        if (node.getParent() != null) {
            node.getParent().getChildren().remove(node);
        }
        nodeRepository.delete(node);
    }

    public Node updateNode(Long id, int value) {
        Node node = nodeRepository.findById(id).orElseThrow(() -> new RuntimeException("Node not found"));
        node.setValue(value);
        return node;
    }

    public Node addChildNode(Long parentId, int value) {
        Node parent = nodeRepository.findById(parentId).orElseThrow(() -> new RuntimeException("Parent node not found"));
        Node child = new Node();
        child.setValue(value);
        child.setParent(parent);
        parent.getChildren().add(child);
        return nodeRepository.save(child);
    }
}
