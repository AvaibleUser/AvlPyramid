package edu.mooncoder.pyramid.model.rederers;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.stream.GraphReplay;
import org.graphstream.stream.file.FileSinkImages;
import org.graphstream.stream.file.images.Filter;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.swing.util.SwingFileSinkImages;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TreeRenderer extends SwingFileSinkImages {
    private static final String STYLESHEET = """
            node {
                shape: circle;
                text-alignment: center;
                size: 40px;
                fill-mode: plain;
                fill-color: white;
                stroke-mode: plain;
                stroke-width: 3px;
                stroke-color: black;
                text-size: 16px;
                text-offset: 1px, -3px;
            }
            edge {
                size: 3px;
                fill-mode: plain;
            }
            """;

    private static TreeRenderer instance;

    private final Graph graph;

    static {
        System.setProperty("org.graphstream.ui", "swing");
    }

    public static TreeRenderer getInstance() {
        return instance;
    }

    public TreeRenderer() {
        graph = new MultiGraph("Avl Tree");

        graph.setAttribute("ui.stylesheet", STYLESHEET);
        graph.setAttribute("ui.antialias");
        graph.setAttribute("ui.quality");

        instance = this;
    }

    @Override
    public void writeAll(Graph g, OutputStream stream) throws IOException {
        clearGG();

        GraphReplay replay = new GraphReplay(String.format("file_sink_image-write_all-replay-%x", System.nanoTime()));

        replay.addSink(gg);
        replay.replay(g);
        replay.removeSink(gg);

        initImage();

        if (clearImageBeforeOutput || gg.getNodeCount() == 0) {
            clearImage(0x00000000);
        }

        if (gg.getNodeCount() > 0) {
            if (autofit) {
                gg.computeBounds();

                Point3 lo = gg.getMinPos();
                Point3 hi = gg.getMaxPos();

                getCamera().setBounds(lo.x, lo.y, lo.z, hi.x, hi.y, hi.z);
            }

            render();
        }

        BufferedImage image = getRenderedImage();

        for (Filter action : filters)
            action.apply(image);

        image.flush();

        byte[] imageInByte;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ImageIO.write(image, outputType.name(), outputStream);
        imageInByte = outputStream.toByteArray();

        stream.write(imageInByte);

        clearGG();
    }

    public void writeAll(OutputStream stream) throws IOException {
        FileSinkImages pic = new TreeRenderer();
        pic.setLayoutPolicy(LayoutPolicy.NO_LAYOUT);
        pic.setQuality(FileSinkImages.Quality.HIGH);
        pic.setOutputType(FileSinkImages.OutputType.jpg);

        pic.writeAll(graph, stream);
    }

    public void addNode(String card) {
        graph.addNode(card);
    }

    public void setAttributesToNode(String card, String parent, int depth, int rank) {
        if (parent != null) graph.addEdge(card + parent, parent, card);

        Node graphicCard = graph.getNode(card);

        graphicCard.setAttribute("xy", rank, -depth);
        graphicCard.setAttribute("label", card);
    }
}
