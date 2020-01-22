import com.intellij.ide.projectView.TreeStructureProvider
import com.intellij.ide.projectView.ViewSettings
import com.intellij.ide.projectView.impl.nodes.BasePsiNode
import com.intellij.ide.util.treeView.AbstractTreeNode
import java.nio.file.Path
import java.nio.file.Paths

class FocusLensFilter : TreeStructureProvider {

    val focus = "/home/toby/WORKSPACE/2n/my2n/communication/cti-connector/connector"
    val focusEnabled = true

    override fun modify(
        parent: AbstractTreeNode<*>,
        children: MutableCollection<AbstractTreeNode<Any>>,
        settings: ViewSettings?
    ): MutableCollection<AbstractTreeNode<Any>> {
        if (!focusEnabled) return children
        val nodes = mutableListOf<AbstractTreeNode<Any>>()
        for (child in children) {
            if (child is BasePsiNode) {
                val path = child.getVirtualFile()?.path ?: continue
                if (allowed(Paths.get(path))) {
                    nodes.add(child)
                }
            }
        }
        return nodes
    }

    private fun allowed(path: Path): Boolean {
        if (focus.startsWith(path.toString())) return true
        if (path.startsWith(focus)) return true
        return false
    }

}