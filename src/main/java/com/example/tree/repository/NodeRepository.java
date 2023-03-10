package com.example.tree.repository;

import com.example.tree.entity.TreeNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends JpaRepository<TreeNode, Long> {
}
