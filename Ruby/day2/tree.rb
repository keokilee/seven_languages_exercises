class Tree
  attr_accessor :children, :node_name
  
  def initialize(tree_hash)
    # Tree hash should only contain one root node.
    @node_name = tree_hash.keys[0] 
    @children = []
    tree_hash[@node_name].each do |key, child|
      @children << Tree.new(key => child)
    end
  end
  
  def visit_all(&block)
    visit &block
    children.each{|c| c.visit_all &block}
  end
  
  def visit(&block)
    block.call self
  end
end

tree = Tree.new({"grandpa" => {"dad" => {"child 1" => {}, "child 2" => {}}, 
                               "uncle" => {"child 3" => {}, "child 4" => {}}}})

puts "Visiting a node"
tree.visit{|node| puts node.node_name}
puts

puts "Visiting entire tree"
tree.visit_all{|node| puts node.node_name}