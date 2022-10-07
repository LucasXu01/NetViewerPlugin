package com.lucas.netviewer.okhttp

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.commons.AdviceAdapter

class OkHttpMethodAdapter(methodVisitor: MethodVisitor?, access: Int, name: String?, descriptor: String?) : AdviceAdapter(Opcodes.ASM7, methodVisitor, access, name, descriptor) {

    override fun onMethodExit(opcode: Int) {
        super.onMethodExit(opcode)
        mv?.let {
            it.visitVarInsn(ALOAD, 0)
            it.visitFieldInsn(GETFIELD, "okhttp3/OkHttpClient\$Builder", "interceptors", "Ljava/util/List;")
            it.visitFieldInsn(GETSTATIC, "com/lucas/netviewer/NetViewerHelper", "INSTANCE", "Lcom/lucas/netviewer/NetViewerHelper;")
            it.visitMethodInsn(INVOKEVIRTUAL, "com/lucas/netviewer/NetViewerHelper", "getHookInterceptors", "()Ljava/util/List;", false)
            it.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "addAll", "(Ljava/util/Collection;)Z", true)
            it.visitInsn(POP)
        }
    }
}