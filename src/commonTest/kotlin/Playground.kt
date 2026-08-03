import dev.akarah.bytecode.BytecodeBuilder
import dev.akarah.interpreter.CodeExecutor
import dev.akarah.interpreter.ExecutorContext
import dev.akarah.interpreter.values.DecimalLong
import dev.akarah.template.CodeTemplateData
import kotlin.test.Test
import kotlin.test.assertSame
import kotlin.test.asserter

object Playground {
    val TEST_TEMPLATE = """
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
                      "name": "15"
                    }
                  },
                  {
                    "id": "txt",
                    "data": {
                      "name": "lol"
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
                  "name": "meowkibby",
                  "scope": "line"
                }
              }
            },
            "slot": 0
          }
        ],
        "tree_version": 0
      },
      "action": "CreateList"
    }
  ]
}
    """.trimIndent()

    @Test
    fun testJsonParsing() {
        val data = CodeTemplateData.parse(TEST_TEMPLATE)
        println(data)
    }

    @Test
    fun testBytecode() {
        val bc = BytecodeBuilder()
        bc.mov(0, DecimalLong(15))
        bc.mov(1, DecimalLong(30))
        bc.dumpRegisters()
        bc._return()

        val code = bc.build()

        val ctx = ExecutorContext()
        val executor = CodeExecutor(ctx)

        executor.execute(code)
    }
}