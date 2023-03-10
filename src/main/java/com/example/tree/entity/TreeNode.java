package com.example.tree.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class TreeNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nodeValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private TreeNode parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TreeNode> children = new ArrayList<>();

    public int calculateValue() {
        int sum = this.nodeValue;
        TreeNode currentTreeNode = this.parent;
        while (currentTreeNode != null) {
            sum += currentTreeNode.nodeValue;
            currentTreeNode = currentTreeNode.parent;
        }
        return sum;
    }
}