package net.bytebuddy.implementation.attribute;

import net.bytebuddy.test.utility.MockitoRule;
import net.bytebuddy.test.utility.ObjectPropertyAssertion;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.TypePath;

import static org.mockito.Mockito.*;

public class AnnotationAppenderTargetTest {

    private static final String FOO = "foo", QUX = "qux";

    private static final int BAR = 42;

    @Rule
    public TestRule mockitoRule = new MockitoRule(this);

    @Mock
    private MethodVisitor methodVisitor;

    @Mock
    private FieldVisitor fieldVisitor;

    @Mock
    private ClassVisitor classVisitor;

    @Test
    public void testOnField() throws Exception {
        new AnnotationAppender.Target.OnField(fieldVisitor).visit(FOO, true);
        verify(fieldVisitor).visitAnnotation(FOO, true);
        verifyNoMoreInteractions(fieldVisitor);
    }

    @Test
    public void testOnType() throws Exception {
        new AnnotationAppender.Target.OnType(classVisitor).visit(FOO, true);
        verify(classVisitor).visitAnnotation(FOO, true);
        verifyNoMoreInteractions(classVisitor);
    }

    @Test
    public void testOnMethod() throws Exception {
        new AnnotationAppender.Target.OnMethod(methodVisitor).visit(FOO, true);
        verify(methodVisitor).visitAnnotation(FOO, true);
        verifyNoMoreInteractions(methodVisitor);
    }

    @Test
    public void testOnMethodParameter() throws Exception {
        new AnnotationAppender.Target.OnMethodParameter(methodVisitor, 0).visit(FOO, true);
        verify(methodVisitor).visitParameterAnnotation(0, FOO, true);
        verifyNoMoreInteractions(methodVisitor);
    }

    @Test
    public void testTypeAnnotationOnField() throws Exception {
        new AnnotationAppender.Target.OnField(fieldVisitor).visit(FOO, true, BAR, QUX);
        verify(fieldVisitor).visitTypeAnnotation(eq(BAR), any(TypePath.class), eq(FOO), eq(true));
        verifyNoMoreInteractions(fieldVisitor);
    }

    @Test
    public void testTypeAnnotationOnType() throws Exception {
        new AnnotationAppender.Target.OnType(classVisitor).visit(FOO, true, BAR, QUX);
        verify(classVisitor).visitTypeAnnotation(eq(BAR), any(TypePath.class), eq(FOO), eq(true));
        verifyNoMoreInteractions(classVisitor);
    }

    @Test
    public void testTypeAnnotationOnMethod() throws Exception {
        new AnnotationAppender.Target.OnMethod(methodVisitor).visit(FOO, true, BAR, QUX);
        verify(methodVisitor).visitTypeAnnotation(eq(BAR), any(TypePath.class), eq(FOO), eq(true));
        verifyNoMoreInteractions(methodVisitor);
    }

    @Test
    public void testTypeAnnotationOnMethodParameter() throws Exception {
        new AnnotationAppender.Target.OnMethodParameter(methodVisitor, 0).visit(FOO, true, BAR, QUX);
        verify(methodVisitor).visitTypeAnnotation(eq(BAR), any(TypePath.class), eq(FOO), eq(true));
        verifyNoMoreInteractions(methodVisitor);
    }

    @Test
    public void testObjectProperties() throws Exception {
        ObjectPropertyAssertion.of(AnnotationAppender.Target.OnField.class).apply();
        ObjectPropertyAssertion.of(AnnotationAppender.Target.OnMethod.class).apply();
        ObjectPropertyAssertion.of(AnnotationAppender.Target.OnMethodParameter.class).apply();
        ObjectPropertyAssertion.of(AnnotationAppender.Target.OnType.class).apply();
    }
}
