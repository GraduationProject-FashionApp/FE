package com.gradu.lookthat.util

import android.text.method.PasswordTransformationMethod
import android.view.View

class CustomPasswordTransformation : PasswordTransformationMethod() {

    override fun getTransformation(source: CharSequence, view: View): CharSequence {
        return PasswordCharSequence(super.getTransformation(source, view))
    }

    private inner class PasswordCharSequence(private val source: CharSequence) : CharSequence {
        // 이 부분에 원하는 문자(예: ●)로 대체하는 로직을 구현합니다.
        override val length: Int
            get() = source.length

        override fun get(index: Int): Char {
            // 비밀번호를 가려주기 위해 원하는 문자(예: ●)를 반환합니다.
            return '●'
        }

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
            // 부분 문자열을 반환합니다.
            return source.subSequence(startIndex, endIndex)
        }
    }
}
