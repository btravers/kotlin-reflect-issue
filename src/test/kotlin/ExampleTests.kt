import Example.Example1
import Example.Example2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExampleTests {

    /**
     * When I run this test, I am facing this issue:
     *
     * java.lang.ExceptionInInitializerError
     *      at ExampleTests.test1(ExampleTests.kt:17)
     *      at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     *      at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
     *      at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     *      at java.lang.reflect.Method.invoke(Method.java:498)
     *      at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:513)
     *      at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:115)
     *      at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$6(TestMethodTestDescriptor.java:170)
     *      at org.junit.jupiter.engine.execution.ThrowableCollector.execute(ThrowableCollector.java:40)
     *      at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:166)
     *      at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:113)
     *      at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:58)
     *      at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.lambda$executeRecursively$3(HierarchicalTestExecutor.java:113)
     *      at org.junit.platform.engine.support.hierarchical.SingleTestExecutor.executeSafely(SingleTestExecutor.java:66)
     *      at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.executeRecursively(HierarchicalTestExecutor.java:108)
     *      at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.execute(HierarchicalTestExecutor.java:79)
     *      at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.lambda$executeRecursively$2(HierarchicalTestExecutor.java:121)
     *      at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
     *      at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:175)
     *      at java.util.Iterator.forEachRemaining(Iterator.java:116)
     *      at java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801)
     *      at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
     *      at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
     *      at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
     *      at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
     *      at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
     *      at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:418)
     *      at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.lambda$executeRecursively$3(HierarchicalTestExecutor.java:121)
     *      at org.junit.platform.engine.support.hierarchical.SingleTestExecutor.executeSafely(SingleTestExecutor.java:66)
     *      at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.executeRecursively(HierarchicalTestExecutor.java:108)
     *      at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.execute(HierarchicalTestExecutor.java:79)
     *      at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor$NodeExecutor.lambda$executeRecursively$2(HierarchicalTestExecutor.java:121)
     *      at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
     *      at java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:175)
     *      at java.util.Iterator.forEachRemaining(Iterator.java:116)
     *      at java.util.Spliterators$IteratorSpliterator.forEachRemaining(Spliterators.java:1801)
     *      at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
     *      at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
     *      at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:151)
     *      at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:174)
     * Caused by: kotlin.TypeCastException: null cannot be cast to non-null type T
     *      at kotlin.reflect.jvm.internal.KClassImpl$Data$objectInstance$2.invoke(KClassImpl.kt:114)
     *      at kotlin.reflect.jvm.internal.ReflectProperties$LazyVal.invoke(ReflectProperties.java:62)
     *      at kotlin.reflect.jvm.internal.ReflectProperties$Val.getValue(ReflectProperties.java:31)
     *      at kotlin.reflect.jvm.internal.KClassImpl$Data.getObjectInstance(KClassImpl.kt)
     *      at kotlin.reflect.jvm.internal.KClassImpl.getObjectInstance(KClassImpl.kt:225)
     *      at Example.<clinit>(Example.kt:11)
     *      ... 55 more
     */
    @Test
    fun failingTest() {
        assertEquals(Example1.name, "example1")
        assertEquals(Example.valueOf("example1"), Example1)

        assertEquals(Example2.name, "example2")
        assertEquals(Example.valueOf("example2"), Example2)
    }

    /**
     * When I run this test alone, it passes
     */
    @Test
    fun passingTest() {
        assertEquals(Example.valueOf("example1"), Example1)
        assertEquals(Example1.name, "example1")

        assertEquals(Example.valueOf("example2"), Example2)
        assertEquals(Example2.name, "example2")
    }

}