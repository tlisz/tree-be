package com.example.tree.controller;

import com.example.tree.entity.Node;
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
    public ResponseEntity<Node> createNode(@RequestBody int value) {
        Node node = nodeService.createNode(value);
        return new ResponseEntity<>(node, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNode(@PathVariable Long id) {
        nodeService.deleteNode(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Node> updateNode(@PathVariable Long id, @RequestBody int value) {
        Node node = nodeService.updateNode(id, value);
        return ResponseEntity.ok(node);
    }

    @PostMapping("/{parentId}")
    public ResponseEntity<Node> addChildNode(@PathVariable Long parentId, @RequestBody int value) {
        Node node = nodeService.addChildNode(parentId, value);
        return new ResponseEntity<>(node, HttpStatus.CREATED);
    }
}
