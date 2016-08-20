package Parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by andrewdickens on 5/30/16.
 */
public class Database {

		public String Name;
		public String Overview;
		public String Model;
		public String URL;
		public String Version;
		public FeatureCategories Features;
		public Integer Database_ID;

		public Database(String name) {
				Name = name;
		}

		public Database(String name, String overview, String model, String URL, String version,
				FeatureCategories features, Integer database_ID) {
				Name = name;
				Overview = overview;
				Model = model;
				this.URL = URL;
				Version = version;
				Features = features;
				Database_ID = database_ID;
		}

		public String getName() {
				return Name;
		}

		public void setName(String name) {
				Name = name;
		}

		public String getOverview() {
				return Overview;
		}

		public void setOverview(String overview) {
				Overview = overview;
		}

		public String getModel() {
				return Model;
		}

		public void setModel(String model) {
				Model = model;
		}

		public String getURL() {
				return URL;
		}

		public void setURL(String URL) {
				this.URL = URL;
		}

		public String getVersion() {
				return Version;
		}

		public void setVersion(String version) {
				Version = version;
		}

		public FeatureCategories getFeatures() {
				return Features;
		}

		public void setFeatures(FeatureCategories features) {
				Features = features;
		}

		public Integer getDatabase_ID() {
				return Database_ID;
		}

		public void setDatabase_ID(Integer database_ID) {
				Database_ID = database_ID;
		}

		public static Database createDatabase(String databaseName, Document DOM, int rank) {
				Database returnDatabase = new Database(databaseName);
				returnDatabase.setDatabase_ID(rank);

				nullValue nullDB = new nullValue("NO VALUE");
				nullDB.setRank(999);
				returnDatabase.setName(databaseName);
				FeatureCategories newFeatures = new FeatureCategories();
				returnDatabase.setFeatures(newFeatures);
				returnDatabase.getFeatures().setAdmin(nullDB);
//				returnDatabase.getFeatures().getAdmin().setRank(0);
				returnDatabase.getFeatures().setConsistency(nullDB);
//				returnDatabase.getFeatures().getConsistency().setRank(1);
				returnDatabase.getFeatures().setSecurity(nullDB);
//				returnDatabase.getFeatures().getSecurity().setRank(2);
				returnDatabase.getFeatures().setDataDistribution(nullDB);
//				returnDatabase.getFeatures().getDataDistribution().setRank(3);
				returnDatabase.getFeatures().setDataModel(nullDB);
//				returnDatabase.getFeatures().getDataModel().setRank(4);
				returnDatabase.getFeatures().setDataReplication(nullDB);
//				returnDatabase.getFeatures().getDataReplication().setRank(5);
				returnDatabase.getFeatures().setQueryLanguages(nullDB);
//				returnDatabase.getFeatures().getQueryLanguages().setRank(6);
				returnDatabase.getFeatures().setScalability(nullDB);
//				returnDatabase.getFeatures().getScalability().setRank(7);


				NodeList nList = DOM.getElementsByTagName("page");
				System.out.println("----------------------------");
				for (int temp = 0; temp < nList.getLength(); temp++) {
						Node nNode = nList.item(temp);
						//						System.out.println("\nCurrent Element :" + nNode.getNodeName());
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								//								System.out.println("page number " + temp);
								Element eElement = (Element) nNode;
								//								System.out.println(eElement.getElementsByTagName("title").item(0).getTextContent());
								String titleString =
										eElement.getElementsByTagName("title").item(0).getTextContent();
								System.out.println(titleString + " " + "<<<<<<------------------------------------");
								System.out.println(databaseName);
								parseAndSetDatabaseDetails(databaseName, returnDatabase, eElement, titleString);
								setFeatures(databaseName, returnDatabase, eElement, titleString);
						}
				}

//				System.out.println(returnDatabase.getFeatures().getConsistency().getFeatureDescription());
				return returnDatabase;
		}

		private static void parseAndSetDatabaseDetails(String databaseName, Database returnDatabase,
				Element eElement, String titleString) {
				if (titleString.contains(databaseName)) {
						System.out.println(titleString.contains(databaseName));
						returnDatabase
								.setOverview(eElement.getElementsByTagName("text").item(0).getTextContent());
						returnDatabase
								.setModel(eElement.getElementsByTagName("model").item(0).getTextContent());
//																returnDatabase.setVersion(eElement.getElementsByTagName("version").item(0).getTextContent());
						//										returnDatabase.setURL(eElement.getElementsByTagName("url").item(0).getTextContent());

				}
		}

		private static void setFeatures(String databaseName, Database returnDatabase, Element eElement,
				String titleString) {
				if (titleString.contains(databaseName)) {
						ParseFeatures(returnDatabase, eElement, titleString);
				}
		}

		private static void ParseFeatures(Database returnDatabase, Element eElement,
				String titleString) {
				if (isConsistency(titleString)) {
						System.out.println("Setting Consistency value for " + titleString);
						Consistency newValue =
								new Consistency(eElement.getElementsByTagName("text").item(0).getTextContent());
						newValue.setRank(1);
						returnDatabase.getFeatures().setConsistency(newValue);
				} else if (isModel(titleString)) {
						System.out.println("Setting Model value for " + titleString);
						DataModel newValue =
								new DataModel(eElement.getElementsByTagName("text").item(0).getTextContent());
						newValue.setRank(3);
						returnDatabase.getFeatures().setDataModel(newValue);
				} else if (isLanguage(titleString)) {
						System.out.println("Setting Language value for " + titleString);

						Language newValue =
								new Language(eElement.getElementsByTagName("text").item(0).getTextContent());
						newValue.setRank(4);
						returnDatabase.getFeatures().setQueryLanguages(newValue);

				} else if (isDistribution(titleString)) {
						System.out.println("Setting Distribution value for " + titleString);

						DataDistribution newValue = new DataDistribution(
								eElement.getElementsByTagName("text").item(0).getTextContent());
						newValue.setRank(2);
						returnDatabase.getFeatures().setDataDistribution(newValue);

				} else if (isReplication(titleString)) {
						System.out.println("Setting Replication value for " + titleString);

						Replication newValue =
								new Replication(eElement.getElementsByTagName("text").item(0).getTextContent());
						newValue.setRank(5);
						returnDatabase.getFeatures().setDataReplication(newValue);

				} else if (isScalability(titleString)) {
						System.out.println("Setting Scalability value for " + titleString);

						Scalability newValue =
								new Scalability(eElement.getElementsByTagName("text").item(0).getTextContent());
						newValue.setRank(6);
						returnDatabase.getFeatures().setScalability(newValue);

				} else if (isSecurity(titleString)) {
						System.out.println("Setting Security value for " + titleString);

						Security newValue =
								new Security(eElement.getElementsByTagName("text").item(0).getTextContent());
						newValue.setRank(7);
						returnDatabase.getFeatures().setSecurity(newValue);

				} else if (isAdmin(titleString)) {
						System.out.println("Setting Admin value for " + titleString);

						Admin newValue =
								new Admin(eElement.getElementsByTagName("text").item(0).getTextContent());
						newValue.setRank(0);
						returnDatabase.getFeatures().setAdmin(newValue);
				}else if (titleString == null){
						nullValue newValue = new nullValue("NO VALUE");
						newValue.setRank(999);
						returnDatabase.getFeatures().setAdmin(newValue);
				}
		}

		private static boolean isAdmin(String titleString) {
				return titleString.contains("Admin");
		}

		private static boolean isSecurity(String titleString) {
				return titleString.contains("Security");
		}

		private static boolean isScalability(String titleString) {
				return titleString.contains("Scalability");
		}

		private static boolean isReplication(String titleString) {
				return titleString.contains("Replication");
		}

		private static boolean isDistribution(String titleString) {
				return titleString.contains("Distribution");
		}

		private static boolean isModel(String titleString) {
				return titleString.contains("Model");
		}

		private static boolean isLanguage(String titleString) {
				return titleString.contains("Language");
		}

		private static boolean isConsistency(String titleString) {
				return titleString.contains("Consistency");
		}
}
