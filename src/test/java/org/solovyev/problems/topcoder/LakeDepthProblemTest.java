package org.solovyev.problems.topcoder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.solovyev.problems.topcoder.LakeDepthProblem.depth;

public class LakeDepthProblemTest {

	@Test
	public void testSolve() throws Exception {
		assertEquals(1, depth(new String[]{"222", "212", "222"}));
		assertEquals(0, depth(new String[]{"222", "232", "222"}));
		assertEquals(4, depth(new String[]{"555", "515", "525", "555"}));
		assertEquals(1, depth(new String[]{"5255", "5225", "5525", "5515", "5555"}));
		assertEquals(8, depth(new String[]{"55555", "59995", "59595", "59195", "59995", "55555"}));
		assertEquals(0, depth(new String[]{"55555", "59995", "59A95", "59A95", "59995", "55555"}));
		assertEquals(61, depth(new String[]{"]]]", "] ]", "]]]"}));
		assertEquals(72, depth(new String[]{"asdkl;jhbgdsapo834ytwproiuenbvdflkuhg3908hbhg;sdlk", "4p9uihbnrtews;o84yht43q;puitbe;piughbv4we3['tih3e4", "4p9uihbnrtews;o84yht43q;puitbe;piughbv4we3['tih3e4", "4p9uihbnrtews;o84yht43q;puitbe;piughbv4we3['tih3e4", "4p9uihbnrtews;o84yht43q;puitbe;piughbv4we3['tih3e4", "4p9uihbnrtews;o84yht43q;puitbe;piughbv4we3['tih3e4", "4p9uihbnrtews;o84yht43q;puitbe;piughbv4we3['tih3e4", "4p9uihbnrtews;o84yht43q;puitbe;piughbv4we3['tih3e4", "4p9uihbnrtews;o84yht43q;puitbe;piughbv4we3['tih3e4", "4p9uihbnrtews;o84yht43q;puitbe;piughbv4we3['tih3e4", "4p9uihbnrtews;o84yht43q;puitbe;piughbv4we3['tih3e4", "pe498hgerp9ihge34w[o8hs[-0te34woighvnera;oibge4w3;", "pe498hgerp9ihge34w[o8hs[-0te34woighvnera;oibge4w3;", "pe498hgerp9ihge34w[o8hs[-0te34woighvnera;oibge4w3;", "pe498hgerp9ihge34w[o8hs[-0te34woighvnera;oibge4w3;", "pe498hgerp9ihge34w[o8hs[-0te34woighvnera;oibge4w3;", "pe498hgerp9ihge34w[o8hs[-0te34woighvnera;oibge4w3;", "pe498hgerp9ihge34w[o8hs[-0te34woighvnera;oibge4w3;", "pe498hgerp9ihge34w[o8hs[-0te34woighvnera;oibge4w3;", "pe498hgerp9ihge34w[o8hs[-0te34woighvnera;oibge4w3;", "pe498hgerp9ihge34w[o8hs[-0te34woighvnera;oibge4w3;", "pe3hgte34wohgte349ht23wujt-ujt3wsugtehngvero;n bvf", "pe3hgte34wohgte349ht23wujt-ujt3wsugtehngvero;n bvf", "pe3hgte34wohgte349ht23wujt-ujt3wsugtehngvero;n bvf", "pe3hgte34wohgte349ht23wujt-ujt3wsugtehngvero;n bvf", "pe3hgte34wohgte349ht23wujt-ujt3wsugtehngvero;n bvf", "pe3hgte34wohgte349ht23wujt-ujt3wsugtehngvero;n bvf", "pe3hgte34wohgte349ht23wujt-ujt3wsugtehngvero;n bvf", "pe3hgte34wohgte349ht23wujt-ujt3wsugtehngvero;n bvf", "pe3hgte34wohgte349ht23wujt-ujt3wsugtehngvero;n bvf", "pe3hgte34wohgte349ht23wujt-ujt3wsugtehngvero;n bvf", ";oijhbt32p49uhtgerlkjngvsa;dlkj398yr32poiuthger;lj", ";oijhbt32p49uhtgerlkjngvsa;dlkj398yr32poiuthger;lj", ";oijhbt32p49uhtgerlkjngvsa;dlkj398yr32poiuthger;lj", ";oijhbt32p49uhtgerlkjngvsa;dlkj398yr32poiuthger;lj", ";oijhbt32p49uhtgerlkjngvsa;dlkj398yr32poiuthger;lj", ";oijhbt32p49uhtgerlkjngvsa;dlkj398yr32poiuthger;lj", ";oijhbt32p49uhtgerlkjngvsa;dlkj398yr32poiuthger;lj", ";oijhbt32p49uhtgerlkjngvsa;dlkj398yr32poiuthger;lj", ";oijhbt32p49uhtgerlkjngvsa;dlkj398yr32poiuthger;lj", " tgp4398 4oiu3h t4398 yt3498y 43oih tpi4h t4p83y t", " tgp4398 4oiu3h t4398 yt3498y 43oih tpi4h t4p83y t", " tgp4398 4oiu3h t4398 yt3498y 43oih tpi4h t4p83y t", " tgp4398 4oiu3h t4398 yt3498y 43oih tpi4h t4p83y t", " tgp4398 4oiu3h t4398 yt3498y 43oih tpi4h t4p83y t", " tgp4398 4oiu3h t4398 yt3498y 43oih tpi4h t4p83y t", " tgp4398 4oiu3h t4398 yt3498y 43oih tpi4h t4p83y t", " tgp4398 4oiu3h t4398 yt3498y 43oih tpi4h t4p83y t", " tgp4398 4oiu3h t4398 yt3498y 43oih tpi4h t4p83y t", "fr4iojng 43598th43iu ht43qp98 yt4398y t34q htpoeh4"}));
	}
}
