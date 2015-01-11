package pl.mczpk.gis.search;

public class DeepFirstSearchTest extends GraphSearchTestBase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		testee = new DeepFirstSearch();
	}
}
