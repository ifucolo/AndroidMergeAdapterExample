package com.ifucolo.mergeadapterexample.extesions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

inline fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

inline fun View.hide() {
    visibility = View.GONE
}

inline fun View.show() {
    visibility = View.VISIBLE
}

inline fun View.visible(visible: Boolean = false, invisible: Boolean = false) {
    visibility = when {
        visible -> View.VISIBLE
        invisible -> View.INVISIBLE
        else -> View.GONE
    }
}