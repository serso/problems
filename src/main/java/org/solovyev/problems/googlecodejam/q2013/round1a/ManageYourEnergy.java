package org.solovyev.problems.googlecodejam.q2013.round1a;

import org.solovyev.problems.googlecodejam.GoogleCodeJamProblem;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;

import static java.lang.Math.max;
import static java.lang.Math.min;

// todo serso: doesn't work properly

// http://code.google.com/codejam/contest/2418487/dashboard#s=p1&a=1
public class ManageYourEnergy extends GoogleCodeJamProblem {
	@Nonnull
	@Override
	protected CharSequence solve(@Nonnull String line, @Nonnull BufferedReader br) throws IOException {
		final String[] arguments = line.split(" ");

		final long E = Integer.valueOf(arguments[0]);
		final long R = Integer.valueOf(arguments[1]);
		final long N = Integer.valueOf(arguments[2]);
		final long vs[] = readValues(br.readLine());

		return String.valueOf(solve(E, R, vs));
	}

	private static class Value {
		private final int position;
		private final long value;

		private Value(int position, long value) {
			this.position = position;
			this.value = value;
		}
	}

	long solve(long E, long R, long[] vs) {
		final Value[] values = getValues(vs);

		final long[] es = new long[vs.length];
		long energyFree = E;
		for (int i = 0; i < values.length; i++) {
			final Value value = values[i];

			if(i == 0) {
				for (int j = 0; j < value.position; j++) {
					es[j] = R;
				}
			}

			if(i == values.length - 1) {
				es[value.position] = energyFree;
				for (int j = value.position + 1; j < es.length; j++) {
					es[j] = R;
				}
			} else {
				final Value nextValue = values[i + 1];
				if (value.value < nextValue.value) {
					long b = E - R * (nextValue.position - value.position);
					if(b <= 0) {
						es[value.position] = energyFree;
					} else {
						es[value.position] = min(energyFree, b);
					}

					energyFree = min(max(0, es[value.position] - energyFree) + R, E);
					for (int j = value.position + 1; j < nextValue.position; j++) {
						if (R >= E) {
							es[j] = R;
						} else {
							es[j] = 0;
						}
						energyFree = min(energyFree + R, E);
					}
				} else {
					es[value.position] = E;

					energyFree = min(R, E);
					for (int j = value.position + 1; j < nextValue.position; j++) {
						if (R >= E) {
							es[j] = R;
						} else {
							es[j] = 0;
						}
						energyFree = min(energyFree + R, E);
					}
				}
			}
		}

		long sum = 0;
		for (int i = 0; i < vs.length; i++) {
			sum += vs[i]*es[i];
		}

		return sum;
	}

	private Value[] getValues(long[] vs) {
		final Stack<Value> stack = new Stack<Value>();
		for (int i = 0; i < vs.length; i++) {
			final long v = vs[i];
			if(stack.isEmpty()) {
				stack.push(new Value(i, v));
			} else {
				final Value peek = stack.peek();
				if(peek.value < v) {
					stack.pop();
				}
				stack.push(new Value(i, v));
			}
		}
		return stack.toArray(new Value[stack.size()]);
	}

	private long[] readValues(@Nonnull String line) {
		final String[] arguments = line.split(" ");
		final long[] values = new long[arguments.length];
		for (int i = 0; i < values.length; i++) {
			values[i] = Long.valueOf(arguments[i]);
		}
		return values;
	}
}
