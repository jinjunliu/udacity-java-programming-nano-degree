package com.udacity.webcrawler.profiler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A method interceptor that checks whether {@link Method}s are annotated with the {@link Profiled}
 * annotation. If they are, the method interceptor records how long the method invocation took.
 */
final class ProfilingMethodInterceptor implements InvocationHandler {

    private final Clock clock;
    private final Object delegate;
    private final ProfilingState state;
    private final ZonedDateTime startTime;

  ProfilingMethodInterceptor(Clock clock, Object delegate, ProfilingState state, ZonedDateTime startTime) {
    this.clock = Objects.requireNonNull(clock);
    this.delegate = Objects.requireNonNull(delegate);
    this.state = Objects.requireNonNull(state);
    this.startTime = Objects.requireNonNull(startTime);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) {
    Object result = null;
    if (method.isAnnotationPresent(Profiled.class)) {
      Instant start = clock.instant();
      try {
        result = method.invoke(delegate, args);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        Instant end = clock.instant();
        Duration duration = Duration.between(start, end);
        state.record(delegate.getClass(), method, duration);
      }
    }
    return result;
  }
}
