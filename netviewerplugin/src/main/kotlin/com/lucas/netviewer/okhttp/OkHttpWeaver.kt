package com.lucas.netviewer.okhttp

import com.quinn.hunter.transform.asm.BaseWeaver
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter


class OkHttpWeaver : BaseWeaver() {

    override fun wrapClassWriter(classWriter: ClassWriter?): ClassVisitor {
        return OkHttpClassVisitor(classWriter)
    }
}