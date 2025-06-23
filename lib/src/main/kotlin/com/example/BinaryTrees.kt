package com.example

class BinaryTrees {

  fun levelOrder(root: TreeNode?): List<List<Int>> {
    val queue = ArrayDeque<NodeWithLevel>()
    val response = mutableListOf<MutableList<Int>>()

    queue.addLast(NodeWithLevel(root, 0))

    while (!queue.isEmpty()) {
      val container = queue.removeFirst()

      if (container.node == null) continue

      val level = container.level
      val node = container.node

      if (level >= response.size) response.add(mutableListOf())
      response[level].add(node.`val`)

      queue.addLast(NodeWithLevel(node.left, level + 1))
      queue.addLast(NodeWithLevel(node.right, level + 1))
    }

    return response
  }

  private data class NodeWithLevel(val node: TreeNode?, val level: Int)
}

class TreeNode(var `val`: Int) {
  var left: TreeNode? = null
  var right: TreeNode? = null
}