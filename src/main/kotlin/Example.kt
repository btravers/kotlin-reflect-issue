import Example.Example1
import Example.Example2
import kotlin.reflect.full.isSubclassOf

sealed class Example(val name: String) {

    companion object {

        @JvmStatic private val map = Example::class.nestedClasses
                .filter { klass -> klass.isSubclassOf(Example::class) }
                .map { klass -> klass.objectInstance }
                .filterIsInstance<Example>()
                .associateBy { value -> value.name }

        @JvmStatic fun valueOf(value: String) = requireNotNull(map[value]) {
            "No enum constant ${Example::class.java.name}.$value"
        }

    }

    object Example1: Example("example1")
    object Example2: Example("example2")

    override fun toString(): String {
        return name
    }


}

fun main(args: Array<String>) {
    println("Test")

    println("ValueOf: '${Example.valueOf("example1")}'")

    println("Example1: '$Example1'")
    println("Example2: '$Example2'")

}