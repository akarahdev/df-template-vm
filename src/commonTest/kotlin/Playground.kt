import dev.akarah.bytecode.BytecodeBuilder
import dev.akarah.bytecode.TemplateCompiler
import dev.akarah.interpreter.CodeExecutor
import dev.akarah.interpreter.ExecutorContext
import dev.akarah.interpreter.values.DecimalLong
import dev.akarah.template.CodeTemplateData
import kotlin.test.Test
import kotlin.test.assertSame
import kotlin.test.asserter

object Playground {
    val TEST_TEMPLATE_2 = """
{
  "blocks": [
    {
      "id": "block",
      "block": "event",
      "slots": {
        "elements": [],
        "tree_version": -1
      },
      "action": "Join"
    },
    {
      "id": "block",
      "block": "set_var",
      "slots": {
        "elements": [
          {
            "id": "vararg",
            "data": {
              "selection": {
                "mode": "INLINED",
                "items": [
                  {
                    "id": "num",
                    "data": {
                      "name": "10"
                    }
                  },
                  {
                    "id": "num",
                    "data": {
                      "name": "15"
                    }
                  },
                  {
                    "id": "num",
                    "data": {
                      "name": "20"
                    }
                  }
                ]
              }
            },
            "slot": 1
          },
          {
            "id": "singleton",
            "data": {
              "code_item": {
                "id": "var",
                "data": {
                  "name": "adder",
                  "scope": "line"
                }
              }
            },
            "slot": 0
          }
        ],
        "tree_version": 0
      },
      "action": "+"
    },
    {
      "id": "block",
      "block": "control",
      "slots": {
        "elements": [
          {
            "id": "vararg",
            "data": {
              "selection": {
                "mode": "CODE_ITEM",
                "code_item": {
                  "id": "var",
                  "data": {
                    "name": "adder",
                    "scope": "line"
                  }
                }
              }
            },
            "slot": 5
          }
        ],
        "tree_version": 0
      },
      "action": "PrintDebug"
    }
  ]
}
    """.trimIndent()

    @Test
    fun testCompilationPipeline() {
        val template = CodeTemplateData.parse(TEST_TEMPLATE_2)
        val compiled = TemplateCompiler.compile(template)

        val ctx = ExecutorContext()
        val executor = CodeExecutor(ctx)
        executor.execute(compiled)
    }
}