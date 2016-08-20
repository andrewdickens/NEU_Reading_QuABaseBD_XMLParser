package Parser; /**
 * Created by andrewdickens on 5/21/16.
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class DOM {

		public static Document readInXML(String filename) {
				Document doc = null;
				try {
						File inputFile = new File(filename);
						DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
						doc = dBuilder.parse(inputFile);
						doc.getDocumentElement().normalize();
						System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
						NodeList nList = doc.getElementsByTagName("page");
						System.out.println("----------------------------");
						for (int temp = 0; temp < nList.getLength(); temp++) {
								//								System.out.println("page number " + temp);
								Node nNode = nList.item(temp);
								System.out.println("\nCurrent Element :" + nNode.getNodeName());
								if (nNode.getNodeType() == Node.ELEMENT_NODE) {
										System.out.println("page number " + temp);
										Element eElement = (Element) nNode;
										System.out.println("Title : " + eElement.getElementsByTagName("title").item(0)
												.getTextContent());
										System.out.println(
												"Username : " + eElement.getElementsByTagName("username").item(0)
														.getTextContent());
										System.out.println(
												"Text : " + eElement.getElementsByTagName("text").item(0).getTextContent());
										System.out.println("Model : " + eElement.getElementsByTagName("model").item(0)
												.getTextContent());
										System.out.println("Format : " + eElement.getElementsByTagName("format").item(0)
												.getTextContent());
								}
						}
				} catch (Exception e) {
						e.printStackTrace();
				}
				return doc;
		}

		//		/**
		//		 *
		//		 * @param DOM
		//		 * @return
		//		 */
		//		public ArrayList<ArrayList<Node>> buildPageList(Document DOM){
		//				ArrayList<ArrayList<Node>> returnList = new ArrayList<>();
		//
		//				return returnList;
		//		}

		/**
		 * @param DOM
		 * @return
		 */
		public ArrayList<Database> buildDatabaseList(Document DOM) {
				ArrayList<Database> returnList = new ArrayList<>();


				return returnList;
		}

		/**
		 * @param DOM
		 * @return
		 */
		public ArrayList<FeatureCategory> buildFeatureCategoryList(Document DOM) {
				ArrayList<FeatureCategory> returnList = new ArrayList<>();

				return returnList;
		}

		/**
		 * @param DOM
		 * @return
		 */
		public ArrayList<String> buildFeatureList(ArrayList<Node> DOM) {
				ArrayList<String> returnList = new ArrayList<>();

				return returnList;
		}
}
