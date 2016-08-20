/**
 * Created by andrewdickens on 5/28/16.
 */

import Parser.*;
import org.w3c.dom.Document;

import java.util.ArrayList;

public class ParserMain {

		public static final String DATABASE_NAME = "DATABASE_TECH_AND_FEATURES";
		public static final String DATABASE_TYPE_TABLE_NAME = "DATABASE_TYPE";
		public static final String FEATURE_CATEGORY_TABLE_NAME = "FEATURE_CATEGORY";
		public static final String FEATURE_TABLE_NAME = "FEATURES";
		public static final String INPUT_XML = "input.xml";

		public static void main(String[] args) {

				Document inputDoc = DOM.readInXML("sqlInput.xml");
				SQLDatabase.deleteDatabase(DATABASE_NAME);

				createDatabase();
				createDatabaseTables();
				populateDatabaseTables(inputDoc);

		}

		private static void createDatabase() {
				SQLDatabase.createDatabase(DATABASE_NAME);
		}

		private static void createDatabaseTables() {
				SQLTable.createTable(DATABASE_NAME, DATABASE_TYPE_TABLE_NAME);
				SQLTable.createTable(DATABASE_NAME, FEATURE_CATEGORY_TABLE_NAME);
				SQLTable.createTable(DATABASE_NAME, FEATURE_TABLE_NAME);
				//todo create joining table/REDO TABLES
		}

		private static void populateDatabaseTables(Document inputDoc) {
				ArrayList<Database> databaseList = createDatabaseObjects(inputDoc);

				for(int i=0; i<databaseList.size(); i++){
						if(databaseList.get(i).getFeatures().getAdmin() == null){
								Admin nullValue = new Admin("NO VALUE");
								databaseList.get(i).getFeatures().setAdmin(nullValue);
						}
						if(databaseList.get(i).getFeatures().getConsistency() == null){
								Consistency nullValue = new Consistency("NO VALUE");
								databaseList.get(i).getFeatures().setConsistency(nullValue);
						}
						if(databaseList.get(i).getFeatures().getDataDistribution() == null){
								DataDistribution nullValue = new DataDistribution("NO VALUE");
								databaseList.get(i).getFeatures().setDataDistribution(nullValue);
						}
						if(databaseList.get(i).getFeatures().getQueryLanguages() == null){
								Language nullValue = new Language("NO VALUE");
								databaseList.get(i).getFeatures().setQueryLanguages(nullValue);
						}
						if(databaseList.get(i).getFeatures().getDataReplication() == null){
								Replication nullValue = new Replication("NO VALUE");
								databaseList.get(i).getFeatures().setDataReplication(nullValue);
						}
						if(databaseList.get(i).getFeatures().getScalability() == null){
								Scalability nullValue = new Scalability("NO VALUE");
								databaseList.get(i).getFeatures().setScalability(nullValue);
						}
						if(databaseList.get(i).getFeatures().getSecurity() == null){
								Security nullValue = new Security("NO VALUE");
								databaseList.get(i).getFeatures().setSecurity(nullValue);
						}
						if(databaseList.get(i).getFeatures().getDataModel() == null){
								DataModel nullValue = new DataModel("NO VALUE");
								databaseList.get(i).getFeatures().setDataModel(nullValue);
						}
				}

				for(int i=0; i<databaseList.size(); i++){
						System.out.println(databaseList.get(i).getFeatures().getAdmin().getFeatureDescription());
						System.out.println(databaseList.get(i).getFeatures().getConsistency().getFeatureDescription());
						System.out.println(databaseList.get(i).getFeatures().getQueryLanguages().getFeatureDescription());
						System.out.println(databaseList.get(i).getFeatures().getDataModel().getFeatureDescription());
						System.out.println(databaseList.get(i).getFeatures().getDataDistribution().getFeatureDescription());
						System.out.println(databaseList.get(i).getFeatures().getScalability().getFeatureDescription());
						System.out.println(databaseList.get(i).getFeatures().getSecurity().getFeatureDescription());
						System.out.println(databaseList.get(i).getFeatures().getDataReplication().getFeatureDescription());

				}

				SQLTable.populateTable(DATABASE_NAME, DATABASE_TYPE_TABLE_NAME, databaseList);
				SQLTable.populateTable(DATABASE_NAME, FEATURE_CATEGORY_TABLE_NAME, databaseList);
				SQLTable.populateTable(DATABASE_NAME, FEATURE_TABLE_NAME, databaseList);
		}

		private static void deleteDatabaseTables() {
				SQLTable.deleteTable(DATABASE_NAME, DATABASE_TYPE_TABLE_NAME);
				SQLTable.deleteTable(DATABASE_NAME, FEATURE_CATEGORY_TABLE_NAME);
				SQLTable.deleteTable(DATABASE_NAME, FEATURE_TABLE_NAME);
		}

		private static ArrayList<Database> createDatabaseObjects(Document inputDoc) {
				Database Accumulo = Database.createDatabase("Accumulo", inputDoc, 0);
				Database Cassandra = Database.createDatabase("Cassandra", inputDoc, 1);
				Database HBase = Database.createDatabase("HBase", inputDoc, 2);
				Database MongoDB = Database.createDatabase("MongoDB", inputDoc, 3);
				Database CouchDB = Database.createDatabase("CouchDB", inputDoc, 4);
				Database Couchbase = Database.createDatabase("Couchbase", inputDoc, 5);
				Database OrientDB = Database.createDatabase("OrientDB", inputDoc, 6);
				Database Neo4j = Database.createDatabase("Neo4j", inputDoc, 7);
				Database Redis = Database.createDatabase("Redis", inputDoc, 8);
				Database Riak = Database.createDatabase("Riak", inputDoc, 9);
				Database AmazonDynamoDB = Database.createDatabase("Amazon DynamoDB", inputDoc, 10);
				Database OracleNoSQL = Database.createDatabase("OracleNoSQL", inputDoc, 11);
				Database FoundationDB = Database.createDatabase("FoundationDB", inputDoc, 12);
				Database VoltDB = Database.createDatabase("VoltDB", inputDoc, 13);

				return buildDatabaseList(Accumulo, Cassandra, HBase, MongoDB, CouchDB, Couchbase, OrientDB,
						Neo4j, Redis, Riak, AmazonDynamoDB, OracleNoSQL, FoundationDB, VoltDB);
		}

		private static ArrayList<Database> buildDatabaseList(Database accumulo, Database cassandra,
				Database HBase, Database mongoDB, Database couchDB, Database couchbase, Database orientDB,
				Database neo4j, Database redis, Database riak, Database amazonDynamoDB,
				Database oracleNoSQL, Database foundationDB, Database voltDB) {
				ArrayList<Database> returnList = new ArrayList<>();

				returnList.add(accumulo);
				returnList.add(cassandra);
				returnList.add(HBase);
				returnList.add(mongoDB);
				returnList.add(couchDB);
				returnList.add(couchbase);
				returnList.add(orientDB);
				returnList.add(neo4j);
				returnList.add(redis);
				returnList.add(riak);
				returnList.add(amazonDynamoDB);
				returnList.add(oracleNoSQL);
				returnList.add(foundationDB);
				returnList.add(voltDB);

				return returnList;
		}
}
