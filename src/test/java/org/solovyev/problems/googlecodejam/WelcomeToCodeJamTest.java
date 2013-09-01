package org.solovyev.problems.googlecodejam;

import org.junit.Test;
import org.solovyev.common.text.Strings;

import java.math.BigInteger;

import static java.math.BigInteger.*;
import static org.junit.Assert.assertEquals;

/**
 * User: serso
 * Date: 9/1/13
 * Time: 7:00 PM
 */
public class WelcomeToCodeJamTest {
	@Test
	public void testSolve() throws Exception {
		assertEquals(ONE, WelcomeToCodeJam.solve("abc", "abc"));
		assertEquals(valueOf(2), WelcomeToCodeJam.solve("abb", "ab"));
		assertEquals(ONE, WelcomeToCodeJam.solve("a  bc", "abc"));
		assertEquals(valueOf(2), WelcomeToCodeJam.solve("a  b", "a b"));
		assertEquals(ZERO, WelcomeToCodeJam.solve("welcome to codejam"));
		assertEquals(ONE, WelcomeToCodeJam.solve("welcome to code jam"));
		assertEquals(valueOf(2), WelcomeToCodeJam.solve("welcome to code jjam"));
		assertEquals(ONE, WelcomeToCodeJam.solve("elcomew elcome to code jam"));
		assertEquals(valueOf(256), WelcomeToCodeJam.solve("wweellccoommee to code qps jam"));
		assertEquals(valueOf(400263727), WelcomeToCodeJam.solve("So you've registered. We sent you a welcoming email, to welcome you to code jam. But it's possible that you still don't feel welcomed to code jam. That's why we decided to name a problem \"welcome to code jam.\" After solving this problem, we hope that you'll feel very welcome. Very welcome, that is, to code jam."));
		assertEquals(new BigInteger("35067810624133695488"), WelcomeToCodeJam.solve("so dqmweawewwwwwewwweoeeecweeeeeeljeeem llleclljllcclccllcocdcccoocoeomc moommmojmm oom ommee e eeeeeceem     ee cj ttwetoe t  oo t  ttoowotootto oo  e oo do   ocl voc c ce cdooococodcmocoeodo ododddoodededddddedtecee de eeem j ee     jr jt jm jjcjjjjjjajoaaaaaaaademmaajmtmmmmmmmdm ommh ei"));
		assertEquals(new BigInteger("806578862640807"), WelcomeToCodeJam.solve("cmowdc emmjco vdtioe o sddwtot jel eewertj mjd owectrelocd  c licoteloeoleet weada ed  exdcwmocmlomcj ceoeoyleoemeacewelee  ceomwdehwm aeemwcmmcwc afmaaowo emeeellaooeod wceoeu a cooowwemol j m cemjtts eedjnoaoe eocooe txcw lcoccejoecwmt om deat omola ow coof ed  aeocdewoxjcdtmmtodmo twe ll eomtdoeto coedmac ooedleacodwfm mc  deooejo o ewloewmcmjpocc djgcaaoat loeccmzw  eoj oleceej cmoejecimcomeactxoetmj cajjocjmmgmmoamwlmeoedjyc eweo lloewq"));

		assertEquals(Strings.convertStream(getClass().getResourceAsStream("C-small-practice.out")), WelcomeToCodeJam.solve(getClass().getResourceAsStream("C-small-practice.in")));
		assertEquals(Strings.convertStream(getClass().getResourceAsStream("C-large-practice.out")), WelcomeToCodeJam.solve(getClass().getResourceAsStream("C-large-practice.in")));
	}
}
