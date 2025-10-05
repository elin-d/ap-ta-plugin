package eu.crewmate.sdk.tc;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.xml.XmlAttributeValue;
import com.jetbrains.python.psi.PyFunction;
import eu.crewmate.sdk.tc.psi.impl.TcKeyImpl;
import eu.crewmate.sdk.tc.psi.impl.TcPausedLineImpl;
import eu.crewmate.sdk.tc.psi.impl.TcPrnameImpl;
import org.jetbrains.annotations.NotNull;
import services.ProjectUtilsService;

import java.util.List;

public class TcAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        ProjectUtilsService projectService = element.getProject().getService(ProjectUtilsService.class);
        if (element instanceof TcPrnameImpl) {
            final List<PyFunction> keys = projectService.findProcedure(element.getText());
            String prName = element.getText();
            if (keys.isEmpty()) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Procedure " + prName + " not found")
                        .range(element.getTextRange()).withFix(new TcReplaceOldProceduresAction(element)).create();
            }
        }
        if (element instanceof TcKeyImpl) {
            final List<XmlAttributeValue> tokens = projectService.findMapping(element);
            final String token = element.getText();
            if (tokens.isEmpty()) {
                holder.newAnnotation(HighlightSeverity.ERROR, "XML definition for " + token + " not found")
                        .range(element.getTextRange()).create();
            }
        }
        if (element instanceof TcPausedLineImpl) {
            holder.newAnnotation(HighlightSeverity.WEAK_WARNING, "Breakpoint").
                    textAttributes(TcSyntaxHighlighter.PAUSE)
                    .range(element.getTextRange()).create();
        }
    }
}
