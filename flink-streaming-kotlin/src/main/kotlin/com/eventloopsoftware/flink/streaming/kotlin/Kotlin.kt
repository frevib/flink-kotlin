package com.eventloopsoftware.flink.streaming.kotlin

import org.apache.flink.streaming.api.datastream.DataStream as JavaStream
import org.apache.flink.streaming.api.datastream.KeyedStream as KeyedJavaStream

object Kotlin {

    fun <R> asKotlinStream(stream: JavaStream<R>): DataStream<R> = DataStream(stream)

    fun <R, K> asKotlinStream(stream: KeyedJavaStream<R, K>): KeyedStream<R, K> = KeyedStream(stream)
}