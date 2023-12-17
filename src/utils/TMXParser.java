package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class TMXParser {

    public static void main(String[] args) {
        parseTmxFile("src/res/MapOne.tmx");
    }

    public static void parseTmxFile(String filePath) {
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse(new File(filePath));

            // Get the root element (usually <map>)
            Element rootElement = document.getDocumentElement();

            // Extract map properties
            int mapWidth = Integer.parseInt(rootElement.getAttribute("width"));
            int mapHeight = Integer.parseInt(rootElement.getAttribute("height"));
            int tileWidth = Integer.parseInt(rootElement.getAttribute("tilewidth"));
            int tileHeight = Integer.parseInt(rootElement.getAttribute("tileheight"));

            System.out.println("Map Size: " + mapWidth + "x" + mapHeight);
            System.out.println("Tile Size: " + tileWidth + "x" + tileHeight);

            // Iterate through the tilesets
            NodeList tilesets = rootElement.getElementsByTagName("tileset");
            for (int i = 0; i < tilesets.getLength(); i++) {
                Element tilesetElement = (Element) tilesets.item(i);
                String tilesetName = tilesetElement.getAttribute("name");
                int firstGID = Integer.parseInt(tilesetElement.getAttribute("firstgid"));

                System.out.println("Tileset: " + tilesetName);
                System.out.println("First GID: " + firstGID);

                // You can extract more tileset information here if needed
            }

            // Iterate through the layers
            NodeList layers = rootElement.getElementsByTagName("layer");
            for (int i = 0; i < layers.getLength(); i++) {
                Element layerElement = (Element) layers.item(i);
                String layerName = layerElement.getAttribute("name");

                System.out.println("Layer: " + layerName);

                // Extract layer data
                Element dataElement = (Element) layerElement.getElementsByTagName("data").item(0);
                String encoding = dataElement.getAttribute("encoding");

                if ("csv".equals(encoding)) {
                    // If data is encoded in CSV format
                    String csvData = dataElement.getTextContent();
                    System.out.println("CSV Data: " + csvData);
                } else if ("base64".equals(encoding)) {
                    // If data is encoded in base64 format
                    // You may need to decode the base64 data depending on your needs
                    String base64Data = dataElement.getTextContent();
                    System.out.println("Base64 Data: " + base64Data);
                }

                // You can extract more layer information here if needed
            }

            // Add more code to handle other elements like object groups, objects, etc.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}