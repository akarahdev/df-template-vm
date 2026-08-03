import dev.akarah.bytecode.TemplateCompiler
import dev.akarah.interpreter.CodeExecutor
import dev.akarah.interpreter.ExecutorContext
import dev.akarah.template.CodeTemplateData
import kotlin.time.measureTime


fun main() {
    val templateJson = """
        {"blocks":[{"id":"block","block":"func","slots":{"elements":[{"id":"singleton","data":{"code_item":{"id":"bl_tag","data":{"option":"False","tag":"Is Hidden","action":"dynamic","block":"func"}}},"slot":0}],"tree_version":0},"data":"main"},{"id":"block","block":"set_var","slots":{"elements":[{"id":"singleton","data":{"code_item":{"id":"var","data":{"name":"x","scope":"line"}}},"slot":0},{"id":"vararg","data":{"selection":{"mode":"INLINED","items":[{"id":"num","data":{"name":"5"}},{"id":"num","data":{"name":"4"}},{"id":"num","data":{"name":"3"}},{"id":"num","data":{"name":"2"}}]}},"slot":1}],"tree_version":0},"action":"+"},{"id":"block","block":"set_var","slots":{"elements":[{"id":"singleton","data":{"code_item":{"id":"var","data":{"name":"y","scope":"line"}}},"slot":0},{"id":"vararg","data":{"selection":{"mode":"INLINED","items":[{"id":"var","data":{"name":"x","scope":"line"}},{"id":"var","data":{"name":"x","scope":"line"}},{"id":"var","data":{"name":"x","scope":"line"}}]}},"slot":1}],"tree_version":0},"action":"+"},{"id":"block","block":"set_var","slots":{"elements":[{"id":"singleton","data":{"code_item":{"id":"var","data":{"name":"z","scope":"line"}}},"slot":0},{"id":"vararg","data":{"selection":{"mode":"INLINED","items":[{"id":"var","data":{"name":"x","scope":"line"}},{"id":"var","data":{"name":"x","scope":"line"}},{"id":"var","data":{"name":"y","scope":"line"}},{"id":"var","data":{"name":"y","scope":"line"}},{"id":"var","data":{"name":"y","scope":"line"}},{"id":"var","data":{"name":"x","scope":"line"}}]}},"slot":1}],"tree_version":0},"action":"+"},{"id":"block","block":"set_var","slots":{"elements":[{"id":"singleton","data":{"code_item":{"id":"var","data":{"name":"w","scope":"line"}}},"slot":0},{"id":"vararg","data":{"selection":{"mode":"INLINED","items":[{"id":"var","data":{"name":"z","scope":"line"}},{"id":"var","data":{"name":"z","scope":"line"}},{"id":"var","data":{"name":"x","scope":"line"}},{"id":"var","data":{"name":"x","scope":"line"}},{"id":"var","data":{"name":"y","scope":"line"}},{"id":"var","data":{"name":"y","scope":"line"}},{"id":"var","data":{"name":"y","scope":"line"}},{"id":"var","data":{"name":"x","scope":"line"}},{"id":"var","data":{"name":"x","scope":"line"}},{"id":"var","data":{"name":"x","scope":"line"}},{"id":"var","data":{"name":"x","scope":"line"}}]}},"slot":1}],"tree_version":0},"action":"+"}]}
        """.trimIndent()

    val template = CodeTemplateData.parse(templateJson)
    val compiled = TemplateCompiler.compile(template)

    val ctx = ExecutorContext()
    val executor = CodeExecutor(ctx)

    val time = measureTime {
        executor.execute(compiled)
    }
    println("Execution time: $time")
}