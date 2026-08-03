import dev.akarah.template.CodeTemplateData
import kotlin.test.Test

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
    fun test() {
        val data = CodeTemplateData.parse(TEST_TEMPLATE)
        println(data)
    }
}