package com.zeca.githubsample.common

interface Mapper<T : Any, R : Any> {
    suspend fun map(from: T): R
}
