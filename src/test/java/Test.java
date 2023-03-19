import burp.Main;
import burp.http.RequestHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Map owner = RequestHandler.parse("https://test154451321.obs.cn-south-1.myhuaweicloud.com/1.txt?acl", "Owner");
        System.out.println(owner.get("ID"));
    }
}
