import dev.akarah.template.CodeTemplateData
import kotlin.test.Test

object Playground {
    val TEST_TEMPLATE = """
{
  "blocks": [
    {
      "id": "block",
      "block": "func",
      "args": {
        "items": [
          {
            "item": {
              "id": "hint",
              "data": {
                "id": "function"
              }
            },
            "slot": 25
          },
          {
            "item": {
              "id": "bl_tag",
              "data": {
                "option": "False",
                "tag": "Is Hidden",
                "action": "dynamic",
                "block": "func"
              }
            },
            "slot": 26
          }
        ]
      },
      "data": "test"
    },
    {
      "id": "block",
      "block": "call_func",
      "args": {
        "items": [
          {
            "item": {
              "id": "txt",
              "data": {
                "name": "test"
              }
            },
            "slot": 0
          }
        ]
      },
      "data": "hostPrint"
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