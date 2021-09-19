package com.eventloopsoftware.flink.streaming.kotlin

import com.eventloopsoftware.flink.streaming.kotlin.Kotlin.asKotlinStream
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.streaming.api.datastream.KeyedStream as JavaKeyedStream

class KeyedStream<T, K>(val javaStream: JavaKeyedStream<T, K>) : DataStream<T>(javaStream) {

    inline fun <reified R> process(processFunction: KeyedProcessFunction<K, T, R>): DataStream<R> =
        asKotlinStream(
            javaStream.process(processFunction, TypeInformation.of(R::class.java))
        )
}