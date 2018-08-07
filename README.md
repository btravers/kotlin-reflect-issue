# Kotlin reflect issue

I summed up my problem in this small project.

## Implementation

I am declaring a sealed class named `Example` and I have two objects that expand it: `Example1` and `Example2`.
I would like to be able to get the object instance corresponding to a given name using a function named `Example::valueOf`.

Here is my implementation.

```kotlin
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
```

## Failing scenario

Now, when I am using it in a main function such as:

```kotlin
fun main(args: Array<String>) {
    println("Test")
    
    println("Example1: '$Example1'")
    println("Example2: '$Example2'")

    println("ValueOf 'example1': '${Example.valueOf("example1")}'")
    println("ValueOf 'example2': '${Example.valueOf("example2")}'")
}
```

I am having the following issue:
```
java.lang.ExceptionInInitializerError
	at ExampleTests.test1(ExampleTests.kt:17)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:513)
	at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:115)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$6(TestMethodTestDescriptor.java:170)
	at org.junit.jupiter.engine.execution.ThrowableCollector.execute(ThrowableCollector.java:40)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:166)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:113)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:58)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.lambda$executeRecursively$3(HierarchicalTestExecutor.java:113)
	at org.junit.platform.engine.support.hierarchical.SingleTestExecutor.executeSafely(SingleTestExecutor.java:66)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.executeRecursively(HierarchicalTestExecutor.java:108)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.execute(HierarchicalTestExecutor.java:79)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.lambda$executeRecursively$2(HierarchicalTestExecutor.java:121)
	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:175)
	at java.util.Iterator.forEachRemaining(Iterator.java:116)
	at java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801)
	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.lambda$executeRecursively$3(HierarchicalTestExecutor.java:121)
	at org.junit.platform.engine.support.hierarchical.SingleTestExecutor.executeSafely(SingleTestExecutor.java:66)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.executeRecursively(HierarchicalTestExecutor.java:108)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.execute(HierarchicalTestExecutor.java:79)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.lambda$executeRecursively$2(HierarchicalTestExecutor.java:121)
	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
	at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:175)
	at java.util.Iterator.forEachRemaining(Iterator.java:116)
	at java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801)
	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.lambda$executeRecursively$3(HierarchicalTestExecutor.java:121)
	at org.junit.platform.engine.support.hierarchical.SingleTestExecutor.executeSafely(SingleTestExecutor.java:66)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.executeRecursively(HierarchicalTestExecutor.java:108)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.execute(HierarchicalTestExecutor.java:79)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:55)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:43)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:170)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:154)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:90)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:74)
	at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)
Caused by: kotlin.TypeCastException: null cannot be cast to non-null type T
	at kotlin.reflect.jvm.internal.KClassImpl$Data$objectInstance$2.invoke(KClassImpl.kt:114)
	at kotlin.reflect.jvm.internal.ReflectProperties$LazyVal.invoke(ReflectProperties.java:62)
	at kotlin.reflect.jvm.internal.ReflectProperties$Val.getValue(ReflectProperties.java:31)
	at kotlin.reflect.jvm.internal.KClassImpl$Data.getObjectInstance(KClassImpl.kt)
	at kotlin.reflect.jvm.internal.KClassImpl.getObjectInstance(KClassImpl.kt:225)
	at Example.<clinit>(Example.kt:11)
	... 55 more
```

## Working scenario

In the next scenario, I am doing the same treatments but in a different order and I have no issue:
```
fun main(args: Array<String>) {
    println("Test")
    
    println("ValueOf 'example1': '${Example.valueOf("example1")}'")
    println("ValueOf 'example2': '${Example.valueOf("example2")}'")
    
    println("Example1: '$Example1'")
    println("Example2: '$Example2'")
}
```