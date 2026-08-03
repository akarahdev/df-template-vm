import dev.akarah.bytecode.TemplateCompiler
import dev.akarah.interpreter.CodeExecutor
import dev.akarah.interpreter.ExecutorContext
import dev.akarah.template.CodeTemplateData
import kotlin.jvm.JvmStatic
import kotlin.time.measureTime

fun main() {
    val templateJson = """
{
  "blocks": [
    {
      "id": "block",
      "block": "func",
      "slots": {
        "elements": [
          {
            "id": "singleton",
            "data": {
              "code_item": {
                "id": "bl_tag",
                "data": {
                  "option": "False",
                  "tag": "Is Hidden",
                  "action": "dynamic",
                  "block": "func"
                }
              }
            },
            "slot": 0
          }
        ],
        "tree_version": 0
      },
      "data": "main"
    },
    {
      "id": "block",
      "block": "set_var",
      "slots": {
        "elements": [
          {
            "id": "singleton",
            "data": {
              "code_item": {
                "id": "txt",
                "data": {
                  "name": "Hello, world!"
                }
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
                  "name": "test_str",
                  "scope": "line"
                }
              }
            },
            "slot": 0
          }
        ],
        "tree_version": 1
      },
      "action": "="
    },
    {
      "id": "block",
      "block": "set_var",
      "args": {
        "items": [
          {
            "item": {
              "id": "bl_tag",
              "data": {
                "option": "All occurrences",
                "tag": "Replacement Type",
                "action": "ReplaceString",
                "block": "set_var"
              }
            },
            "slot": 25
          },
          {
            "item": {
              "id": "bl_tag",
              "data": {
                "option": "Disable",
                "tag": "Regular Expressions",
                "action": "ReplaceString",
                "block": "set_var"
              }
            },
            "slot": 26
          }
        ]
      },
      "slots": {
        "elements": [
          {
            "id": "singleton",
            "data": {
              "code_item": {
                "id": "txt",
                "data": {
                  "name": "world"
                }
              }
            },
            "slot": 4
          },
          {
            "id": "singleton",
            "data": {
              "code_item": {
                "id": "txt",
                "data": {
                  "name": "compiler"
                }
              }
            },
            "slot": 5
          },
          {
            "id": "singleton",
            "data": {
              "code_item": {
                "id": "var",
                "data": {
                  "name": "test_str",
                  "scope": "line"
                }
              }
            },
            "slot": 3
          },
          {
            "id": "singleton",
            "data": {
              "code_item": {
                "id": "var",
                "data": {
                  "name": "test_str",
                  "scope": "line"
                }
              }
            },
            "slot": 2
          }
        ],
        "tree_version": 0
      },
      "action": "ReplaceString"
    },
    {
      "id": "block",
      "block": "set_var",
      "slots": {
        "elements": [
          {
            "id": "singleton",
            "data": {
              "code_item": {
                "id": "num",
                "data": {
                  "name": "3"
                }
              }
            },
            "slot": 2
          },
          {
            "id": "singleton",
            "data": {
              "code_item": {
                "id": "num",
                "data": {
                  "name": "5"
                }
              }
            },
            "slot": 3
          },
          {
            "id": "singleton",
            "data": {
              "code_item": {
                "id": "var",
                "data": {
                  "name": "test_str",
                  "scope": "line"
                }
              }
            },
            "slot": 0
          },
          {
            "id": "singleton",
            "data": {
              "code_item": {
                "id": "var",
                "data": {
                  "name": "test_str",
                  "scope": "line"
                }
              }
            },
            "slot": 1
          }
        ],
        "tree_version": 0
      },
      "action": "TrimString"
    }
  ]
}
"""
    val template = CodeTemplateData.parse(templateJson)
    val compiled = TemplateCompiler.compile(template)

    val ctx = ExecutorContext()
    val executor = CodeExecutor(ctx)

    val time = measureTime {
        executor.execute(compiled)
    }
    println("Execution time: $time")
}