package com.eventloopsoftware.flink.streaming.kotlin

import org.apache.flink.api.common.JobExecutionResult
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment as JavaEnv

class StreamExecutionEnvironment(val javaEnv: JavaEnv) {

    inline fun <reified T> addSource(function: SourceFunction<T>): DataStream<T> =
        DataStream<T>(javaEnv.addSource(function, TypeInformation.of(T::class.java)))

    fun execute(jobName: String): JobExecutionResult = javaEnv.execute(jobName)


    companion object {
        fun getExecutionEnvironment() = StreamExecutionEnvironment(JavaEnv.getExecutionEnvironment())
    }
}