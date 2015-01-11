package pl.mczpk.gis.search;

public class BreadthFirstSearchTest extends GraphSearchTestBase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		testee = new BreadthFirstSearch();
	}
}
