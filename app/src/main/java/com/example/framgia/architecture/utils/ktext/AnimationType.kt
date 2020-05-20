package com.thuanpx.ktext

import androidx.annotation.IntDef

/**
 * Created by ThuanPx on 5/21/20.
 */

@IntDef(FADE, SLIDE_TO_LEFT, SLIDE_TO_RIGHT, BOTTOM_UP)
@Retention(AnnotationRetention.SOURCE)
annotation class AnimationType

const val FADE = 0x01
const val SLIDE_TO_RIGHT = 0x02
const val SLIDE_TO_LEFT = 0x03
const val BOTTOM_UP = 0x04
