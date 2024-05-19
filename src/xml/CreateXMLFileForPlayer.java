package xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

public class CreateXMLFileForPlayer {
    public static List<Player> _listPlayers = new CopyOnWriteArrayList<Player>();

    public static void addPlayer(Player player) {
        _listPlayers.add(player);
    }

    public static void CallXMLForPlayer() {
        try {
            System.out.println("Danh sách người chơi:");
            for (Player p : _listPlayers) {
                System.out.println("ID: " + p.getNumber() + ", Name: " + p.getName() + ", Score: " + p.getScore());
            }

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element GuessGame = doc.createElement("guessgame");

            for (Player p : _listPlayers) {
                AddPlayer(doc, GuessGame, p);
            }

            doc.appendChild(GuessGame);

            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult("src/xml/playertest.xml");
            tf.transform(source, result);
            System.out.println("Ghi file thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static private void AddPlayer(Document doc, Element GuessGame, Player p) {
        Element Player = doc.createElement("player");

        Element Name = doc.createElement("name");
        Name.setTextContent(p.getName());

        Element Peak = doc.createElement("peak");

        Element Score = doc.createElement("score");
        Score.setTextContent(p.getScore());

        Peak.appendChild(Score);

        Player.setAttribute("number", p.getNumber());
        Player.appendChild(Name);
        Player.appendChild(Peak);

        GuessGame.appendChild(Player);
    }
}
