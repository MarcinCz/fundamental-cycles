package pl.mczpk.gis.IO;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pl.mczpk.gis.graph.Graph;

public class GraphReaderTest {

	@Test
	public void shouldReadGraphFromFile(){
		Graph graph = GraphReader.readGraphFromFile("src/test/resources/triangle.graph");
		assertEquals(3, graph.getNodesCount());
		assertEquals(3, graph.getEdgesCount());
	}
}
