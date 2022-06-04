package com.zeca.githubsample.common.extensions

import com.zeca.githubsample.common.Mapper

fun <T: Any, R: Any> List<T>.takeMap(count: Int, mapper: Mapper<T, R>): List<R> {
    return take(count).map { mapper.map(it) }
}
