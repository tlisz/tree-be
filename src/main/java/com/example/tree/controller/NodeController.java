package com.example.tree.controller;

import com.example.tree.entity.TreeNode;
import com.example.tree.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nodes")
public class NodeController {
    @Autowired
    private NodeService nodeService;

    @PostMapping
    public ResponseEntity<TreeNode> createNode(@RequestBody int value) {
        TreeNode treeNode = nodeService.createNode(value);
        return new ResponseEntity<>(treeNode, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNode(@PathVariable Long id) {
        nodeService.deleteNode(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreeNode> updateNode(@PathVariable Long id, @RequestBody int value) {
        TreeNode treeNode = nodeService.updateNode(id, value);
        return ResponseEntity.ok(treeNode);
    }

    @PostMapping("/{parentId}")
    public ResponseEntity<TreeNode> addChildNode(@PathVariable Long parentId, @RequestBody int value) {
        TreeNode treeNode = nodeService.addChildNode(parentId, value);
        return new ResponseEntity<>(treeNode, HttpStatus.CREATED);
    }
}
