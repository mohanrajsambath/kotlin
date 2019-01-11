/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.idea.vfilefinder

import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import org.jetbrains.kotlin.analyzer.ModuleInfo
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.idea.caches.project.IdeaModuleInfo
import org.jetbrains.kotlin.load.kotlin.VirtualFileFinder
import org.jetbrains.kotlin.load.kotlin.VirtualFileFinderFactory

class IDEVirtualFileFinderFactory : VirtualFileFinderFactory {
    override fun create(scope: GlobalSearchScope): VirtualFileFinder = IDEVirtualFileFinder(scope)

    override fun create(project: Project, module: ModuleDescriptor): VirtualFileFinder {
        val ideaModuleInfo = (module.getCapability(ModuleInfo.Capability) as? IdeaModuleInfo)
            ?: return IDEVirtualFileFinder(GlobalSearchScope.EMPTY_SCOPE)

        val scope = GlobalSearchScope.union(
            ideaModuleInfo.dependencies().map { it.contentScope() }.toTypedArray()
        )
        return IDEVirtualFileFinder(scope)
    }
}
