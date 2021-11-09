package com.dj.cloud.test.jvm.thread;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(value = Scope.Benchmark)
public class Test {

    private int a[] = new int[5];

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public void badTry() {
        int result = 0;
        int i = 0;
        try {
            while (true) {
                result += this.a[i++];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // do nothing
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public void goodTry() {
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result += this.a[i];
        }
    }


    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()

                .include(Test.class.getSimpleName())

                .forks(0)

                .build();

        new Runner(opt).run();

    }
}
