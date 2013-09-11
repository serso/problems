package org.solovyev.problems.googlecodejam.q2013.round1a;

import org.solovyev.problems.googlecodejam.GoogleCodeJamProblem;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

// http://code.google.com/codejam/contest/2418487/dashboard#s=p0&a=0
public class Bullseye extends GoogleCodeJamProblem{

	public static final BigInteger TWO = BigInteger.valueOf(2l);
	public static final BigInteger THREE= BigInteger.valueOf(3l);
	public static final BigInteger FOUR = BigInteger.valueOf(4l);

	@Nonnull
	@Override
	protected CharSequence solve(@Nonnull String line, @Nonnull BufferedReader br) throws IOException {
		final String[] arguments = line.split(" ");

		final BigInteger r = new BigInteger(arguments[0]);
		final BigInteger paint = new BigInteger(arguments[1]);
		return solve(r, paint).toString();
	}

	@Nonnull
	BigInteger solve(@Nonnull BigInteger r, @Nonnull BigInteger paint) {
		return findK(ONE, k(r, paint), r, paint);
	}

	private BigInteger findK(@Nonnull BigInteger left, @Nonnull BigInteger right, @Nonnull BigInteger r, @Nonnull BigInteger paint) {
		final BigInteger center = left.add(right.subtract(left).divide(TWO));
		if(center.compareTo(ZERO) == 0) {
			return ONE;
		}

		if(center.compareTo(left) == 0) {
			/*if(left.add(ONE).compareTo(right) == 0) {
				final BigInteger paintRight = paintKRings(r, right);
				if(paintRight.compareTo(paint) <= 0) {
					return right;
				} else {
					return left;
				}
			} else {
				return left;
			}*/
			return left;
		}

		if(center.compareTo(right) == 0) {
			return right;
		}
		final BigInteger paintKRings = paintKRings(r, center);
		if(paintKRings.compareTo(paint) < 0) {
			return findK(center, right, r, paint);
		} else if (paintKRings.compareTo(paint) > 0) {
			return findK(left, center, r, paint);
		} else {
			return center;
		}
	}

	private static final BigInteger paintKRings(final BigInteger r, final BigInteger k) {
		final BigInteger r2 = TWO.multiply(r);
		final BigInteger k2 = TWO.multiply(k);
		return k.multiply(r2.add(k2).subtract(ONE));
	}

	private static final BigInteger s0(final BigInteger r) {
		return TWO.multiply(r).add(ONE);
	}

	private static final BigInteger paintKRing(final BigInteger r, final BigInteger k) {
		return TWO.multiply(r).add(FOUR.multiply(k)).subtract(THREE);
	}

	private static final BigInteger k(final BigInteger r, final BigInteger s) {
		return s.subtract(TWO.multiply(r)).add(THREE).divide(FOUR);
	}
}
