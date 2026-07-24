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
                "option": "True",
                "tag": "Is Hidden",
                "action": "dynamic",
                "block": "func"
              }
            },
            "slot": 26
          }
        ]
      },
      "data": "main"
    },
    {
      "id": "block",
      "block": "set_var",
      "args": {
        "items": [
          {
            "item": {
              "id": "var",
              "data": {
                "name": "x",
                "scope": "line"
              }
            },
            "slot": 0
          },
          {
            "item": {
              "id": "num",
              "data": {
                "name": "10"
              }
            },
            "slot": 1
          }
        ]
      },
      "action": "="
    },
    {
      "id": "block",
      "block": "if_var",
      "args": {
        "items": [
          {
            "item": {
              "id": "var",
              "data": {
                "name": "x",
                "scope": "line"
              }
            },
            "slot": 0
          },
          {
            "item": {
              "id": "num",
              "data": {
                "name": "5"
              }
            },
            "slot": 1
          }
        ]
      },
      "action": ">="
    },
    {
      "id": "bracket",
      "direct": "open",
      "type": "norm"
    },
    {
      "id": "block",
      "block": "control",
      "args": {
        "items": [
          {
            "item": {
              "id": "comp",
              "data": {
                "name": "woahhh"
              }
            },
            "slot": 0
          },
          {
            "item": {
              "id": "bl_tag",
              "data": {
                "option": "Developer",
                "tag": "Permission",
                "action": "PrintDebug",
                "block": "control"
              }
            },
            "slot": 22
          },
          {
            "item": {
              "id": "bl_tag",
              "data": {
                "option": "Add Spaces",
                "tag": "Text Value Merging",
                "action": "PrintDebug",
                "block": "control"
              }
            },
            "slot": 23
          },
          {
            "item": {
              "id": "bl_tag",
              "data": {
                "option": "None",
                "tag": "Highlighting",
                "action": "PrintDebug",
                "block": "control"
              }
            },
            "slot": 24
          },
          {
            "item": {
              "id": "bl_tag",
              "data": {
                "option": "Default",
                "tag": "Sound",
                "action": "PrintDebug",
                "block": "control"
              }
            },
            "slot": 25
          },
          {
            "item": {
              "id": "bl_tag",
              "data": {
                "option": "Debug",
                "tag": "Message Style",
                "action": "PrintDebug",
                "block": "control"
              }
            },
            "slot": 26
          }
        ]
      },
      "action": "PrintDebug"
    },
    {
      "id": "bracket",
      "direct": "close",
      "type": "norm"
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