package com.example.tree.service;

import com.example.tree.entity.TreeNode;
import com.example.tree.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NodeService {
    @Autowired
    private NodeRepository nodeRepository;

    public TreeNode createNode(int value) {
        TreeNode treeNode = new TreeNode();
        treeNode.setNodeValue(value);
        return nodeRepository.save(treeNode);
    }

    public void deleteNode(Long id) {
        TreeNode treeNode = nodeRepository.findById(id).orElseThrow(() -> new RuntimeException("Node not found"));
        if (treeNode.getParent() != null) {
            treeNode.getParent().getChildren().remove(treeNode);
        }
        nodeRepository.delete(treeNode);
    }

    public TreeNode updateNode(Long id, int value) {
        TreeNode treeNode = nodeRepository.findById(id).orElseThrow(() -> new RuntimeException("Node not found"));
        treeNode.setNodeValue(value);
        return nodeRepository.save(treeNode);
    }

    public TreeNode addChildNode(Long parentId, int value) {
        TreeNode parent = nodeRepository.findById(parentId).orElseThrow(() -> new RuntimeException("Parent node not found"));
        TreeNode child = new TreeNode();
        child.setNodeValue(value);
        child.setParent(parent);
        parent.getChildren().add(child);
        return nodeRepository.save(child);
    }
}
