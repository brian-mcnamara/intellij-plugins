// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.angular2.inspections.quickfixes;

import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.lang.javascript.refactoring.FormatFixer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.util.ObjectUtils;
import org.angular2.lang.Angular2Bundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class RemoveAttributeValueQuickFix implements LocalQuickFix {

  @Nls
  @NotNull
  @Override
  public String getName() {
    return Angular2Bundle.message("angular.quickfix.template.remove-attribute-value.name");
  }

  @Nls
  @NotNull
  @Override
  public String getFamilyName() {
    return Angular2Bundle.message("angular.quickfix.template.remove-attribute-value.family");
  }

  @Override
  public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
    final XmlAttribute attribute = ObjectUtils.tryCast(descriptor.getPsiElement().getParent(), XmlAttribute.class);
    if (attribute != null && attribute.getValueElement() != null) {
      attribute.deleteChildRange(attribute.getNameElement().getNextSibling(),
                                 attribute.getValueElement());
      FormatFixer.create(attribute, FormatFixer.Mode.Reformat).fixFormat();
    }
  }
}
