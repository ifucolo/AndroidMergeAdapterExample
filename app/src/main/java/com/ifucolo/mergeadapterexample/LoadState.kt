package com.ifucolo.mergeadapterexample

sealed class LoadState {
    object Loading: LoadState()
    object Done: LoadState()
}