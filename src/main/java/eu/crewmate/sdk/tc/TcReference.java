package eu.crewmate.sdk.tc;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import services.ProjectUtilsService;

public abstract class TcReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    protected final SmartPsiElementPointer<PsiElement> elementPointer;
    protected final ProjectUtilsService ProjectInstance;

    public TcReference(@NotNull PsiElement element, TextRange rangeInElement) {
        super(element, rangeInElement);
        final Project project = element.getProject();
        this.elementPointer = SmartPointerManager.getInstance(project).createSmartPsiElementPointer(element);
        this.ProjectInstance = project.getService(ProjectUtilsService.class);
    }
}
