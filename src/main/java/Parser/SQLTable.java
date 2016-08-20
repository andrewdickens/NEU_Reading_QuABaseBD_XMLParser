package Parser;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by andrewdickens on 5/28/16.
 */
public class SQLTable {

		// JDBC driver name and database URL
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://localhost/";

		//  SQLDatabase credentials
		static final String USER = "root";
		static final String PASS = "andrew";

		public static void createTable(String databaseName,
				String tableName/*, String[] columnNames*/) {
				Connection conn = null;
				Statement stmt = null;
				try {
						//STEP 2: Register JDBC driver
						Class.forName(JDBC_DRIVER);

						StringBuilder databasePath = new StringBuilder();
						databasePath.append(DB_URL).append(databaseName);
						//STEP 3: Open a connection
						System.out.println("Connecting to a selected database, running createTable...");
						conn = DriverManager.getConnection(databasePath.toString(), USER, PASS);
						System.out.println("Connected database successfully...");

						//STEP 4: Execute a query
						System.out.println("Creating table in given database...");
						stmt = conn.createStatement();

						StringBuilder sqlQuery = new StringBuilder();

						setTableHeaders(tableName, sqlQuery);

						String sql = sqlQuery.toString();

						stmt.executeUpdate(sql);
						System.out.println("Created table in given database...");
				} catch (SQLException se) {
						//Handle errors for JDBC
						se.printStackTrace();
				} catch (Exception e) {
						//Handle errors for Class.forName
						e.printStackTrace();
				} finally {
						//finally block used to close resources
						try {
								if (stmt != null)
										conn.close();
						} catch (SQLException se) {
						}// do nothing
						try {
								if (conn != null)
										conn.close();
						} catch (SQLException se) {
								se.printStackTrace();
						}//end finally try
				}//end try
				System.out.println("Goodbye!");
		}//end createTable

		private static void setTableHeaders(String tableName, StringBuilder sqlQuery) {
				switch (tableName) {
						case "DATABASE_TYPE":
								sqlQuery.append("CREATE TABLE ").append(tableName).append(" ")
										.append(
												"(database_id VARCHAR(3000), " +
												" databaseName VARCHAR(3000), " +
												" databaseVersion VARCHAR(3000), " +
												" databaseDescription VARCHAR(3000))");
								break;
						case "FEATURE_CATEGORY":
								sqlQuery.append("CREATE TABLE ").append(tableName).append(" ")
										.append(
												"(featureCategoryName TEXT, " +
														" database_ID INTEGER not NULL, " +
														" category_ID INTEGER not NULL)");
								break;
						case "FEATURES":
								sqlQuery.append("CREATE TABLE ").append(tableName).append(" ")
										.append("(feature_ID INTEGER not NULL, " +
												" database_ID INTEGER not NULL, " +
												" category_ID INTEGER not NULL, " +
												" value VARCHAR(255))");
								break;
				}
		}

		public static void deleteTable(String databaseName, String tableName) {
				Connection conn = null;
				Statement stmt = null;
				try {
						//STEP 2: Register JDBC driver
						Class.forName("com.mysql.jdbc.Driver");

						StringBuilder databasePath = new StringBuilder();
						databasePath.append(DB_URL).append(databaseName);
						//STEP 3: Open a connection
						System.out.println("Connecting to a selected database, running deleteTable...");
						conn = DriverManager.getConnection(databasePath.toString(), USER, PASS);
						System.out.println("Connected database successfully...");

						//STEP 4: Execute a query
						System.out.println("Deleting table in given database...");
						stmt = conn.createStatement();

						StringBuilder sqlQuery = new StringBuilder();

						sqlQuery.append("DROP TABLE ").append(tableName).append(" ");

						String sql = sqlQuery.toString();

						stmt.executeUpdate(sql);
						System.out.println("Deleted table in given database...");
				} catch (SQLException se) {
						//Handle errors for JDBC
						se.printStackTrace();
				} catch (Exception e) {
						//Handle errors for Class.forName
						e.printStackTrace();
				} finally {
						//finally block used to close resources
						try {
								if (stmt != null)
										conn.close();
						} catch (SQLException se) {
						}// do nothing
						try {
								if (conn != null)
										conn.close();
						} catch (SQLException se) {
								se.printStackTrace();
						}//end finally try
				}//end try
				System.out.println("Goodbye!");
		}//end deleteTable

		public static void populateTable(String databaseName, String tableName,
				ArrayList<Database> databaseList) {
				Connection conn = null;
				Statement stmt = null;
				try {
						//STEP 2: Register JDBC driver
						Class.forName("com.mysql.jdbc.Driver");

						StringBuilder databasePath = new StringBuilder();
						databasePath.append(DB_URL).append(databaseName);
						//STEP 3: Open a connection
						System.out.println("Connecting to a selected database...");
						conn = DriverManager.getConnection(databasePath.toString(), USER, PASS);
						System.out.println("Connected database successfully...");

						//STEP 4: Execute a query
						System.out.println("Adding data to " + tableName + " in " + databaseName);
						stmt = conn.createStatement();

						for (int i = 0; i < databaseList.size(); i++) {
								StringBuilder sqlQuery = new StringBuilder();
								buildAndExecuteQuery(tableName, stmt, sqlQuery, databaseList, i, conn);
						}

//						PreparedStatement updateColumn = conn.prepareStatement("ALTER TABLE FEATURE_CATEGORY CHANGE COLUMN featureNameCategory featureNameCat LONGTEXT NULL DEFAULT NULL");
//						updateColumn.executeUpdate();

						System.out.println("Added data to " + tableName + " in " + databaseName);

				} catch (SQLException se) {
						//Handle errors for JDBC
						se.printStackTrace();
				} catch (Exception e) {
						//Handle errors for Class.forName
						e.printStackTrace();
				} finally {
						//finally block used to close resources
						try {
								if (stmt != null)
										conn.close();
						} catch (SQLException ignored) {
						}// do nothing
						try {
								if (conn != null)
										conn.close();
						} catch (SQLException se) {
								se.printStackTrace();
						}//end finally try
				}//end try
				System.out.println("Goodbye!");
		}//end populateTable



		private static void buildAndExecuteQuery(String tableName, Statement stmt,
				StringBuilder sqlQuery, ArrayList<Database> databaseList, int index, Connection conn) throws SQLException {

				switch (tableName) {
						case "DATABASE_TYPE": {

								Integer databaseID = databaseList.get(index).getDatabase_ID();
								String databaseName = databaseList.get(index).getName();
								String databaseVersion = databaseList.get(index).getVersion();
								String databaseOverview = databaseList.get(index).getOverview();

								PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DATABASE_TYPE VALUES (?,?,?,?)");
								pstmt.setInt(1, databaseID);
								pstmt.setString(2, databaseName);
								pstmt.setString(3, databaseVersion);
								pstmt.setString(4, databaseOverview);
								pstmt.executeUpdate();
								break;
						}
						case "FEATURE_CATEGORY": {
								conn.setAutoCommit(false);

								String featureCategory1 = databaseList.get(index).getFeatures().getAdmin().getFeatureDescription();
								PreparedStatement query1 = conn.prepareStatement("INSERT INTO FEATURE_CATEGORY VALUES (?,?,?)");
								query1.setString(1, featureCategory1);
								query1.setInt(2,databaseList.get(index).getDatabase_ID());
								query1.setInt(3,databaseList.get(index).getFeatures().getAdmin().getRank());
								query1.executeUpdate();
								conn.commit();

								sqlQuery.setLength(0);
								String featureCategory2 = databaseList.get(index).getFeatures().getConsistency().getFeatureDescription();
								PreparedStatement query2 = conn.prepareStatement("INSERT INTO FEATURE_CATEGORY VALUES (?,?,?)");
								query2.setString(1, featureCategory2);
								query2.setInt(2,databaseList.get(index).getDatabase_ID());
								query2.setInt(3,databaseList.get(index).getFeatures().getConsistency().getRank());
								query2.executeUpdate();
								conn.commit();

								sqlQuery.setLength(0);
								String featureCategory3 = databaseList.get(index).getFeatures().getDataDistribution().getFeatureDescription();
								PreparedStatement query3 = conn.prepareStatement("INSERT INTO FEATURE_CATEGORY VALUES (?,?,?)");
								query3.setString(1, featureCategory3);
								query3.setInt(2,databaseList.get(index).getDatabase_ID());
								query3.setInt(3,databaseList.get(index).getFeatures().getDataDistribution().getRank());
								query3.executeUpdate();
								conn.commit();

								sqlQuery.setLength(0);
								String featureCategory4 = databaseList.get(index).getFeatures().getDataModel().getFeatureDescription();
								PreparedStatement query4 = conn.prepareStatement("INSERT INTO FEATURE_CATEGORY VALUES (?,?,?)");
								query4.setString(1, featureCategory4);
								query4.setInt(2,databaseList.get(index).getDatabase_ID());
								query4.setInt(3,databaseList.get(index).getFeatures().getDataModel().getRank());
								query4.executeUpdate();
								conn.commit();

								sqlQuery.setLength(0);
								String featureCategory5 = databaseList.get(index).getFeatures().getQueryLanguages().getFeatureDescription();
								PreparedStatement query5 = conn.prepareStatement("INSERT INTO FEATURE_CATEGORY VALUES (?,?,?)");
								query5.setString(1, featureCategory5);
								query5.setInt(2,databaseList.get(index).getDatabase_ID());
								query5.setInt(3,databaseList.get(index).getFeatures().getQueryLanguages().getRank());
								query5.executeUpdate();
								conn.commit();

								sqlQuery.setLength(0);
								String featureCategory6 = databaseList.get(index).getFeatures().getDataReplication().getFeatureDescription();
								PreparedStatement query6 = conn.prepareStatement("INSERT INTO FEATURE_CATEGORY VALUES (?,?,?)");
								query6.setString(1, featureCategory6);
								query6.setInt(2,databaseList.get(index).getDatabase_ID());
								query6.setInt(3,databaseList.get(index).getFeatures().getDataReplication().getRank());
								query6.executeUpdate();
								conn.commit();

								sqlQuery.setLength(0);
								String featureCategory7 = databaseList.get(index).getFeatures().getSecurity().getFeatureDescription();
								PreparedStatement query7 = conn.prepareStatement("INSERT INTO FEATURE_CATEGORY VALUES (?,?,?)");
								query7.setString(1, featureCategory7);
								query7.setInt(2,databaseList.get(index).getDatabase_ID());
								query7.setInt(3,databaseList.get(index).getFeatures().getSecurity().getRank());
								query7.executeUpdate();
								conn.commit();

								sqlQuery.setLength(0);
								String featureCategory8 = databaseList.get(index).getFeatures().getScalability().getFeatureDescription();
								PreparedStatement query8 = conn.prepareStatement("INSERT INTO FEATURE_CATEGORY VALUES (?,?,?)");
								query8.setString(1, featureCategory8);
								query8.setInt(2,databaseList.get(index).getDatabase_ID());
								query8.setInt(3,databaseList.get(index).getFeatures().getScalability().getRank());
								query8.executeUpdate();
								conn.commit();
								break;
						}
						case "FEATURES": {
								sqlQuery.append("INSERT INTO ").append(tableName).append(" ").append("VALUES ")
										.append("(30, 30, 30,'add here')");
								String sql = sqlQuery.toString();//TODO FINISH ADD TABLE QUERIES
								System.out.println(sql);
								stmt.executeUpdate(sql);
								break;
						}
				}
		}
}
