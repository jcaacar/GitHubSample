package com.zeca.githubsample.common

interface Mapper<T : Any, R : Any> {
    fun map(from: T): R
}
