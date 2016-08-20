package Parser;

/**
 * Created by andrewdickens on 5/30/16.
 */
public class FeatureCategories {

		public FeatureCategory Consistency;
		public FeatureCategory DataModel;
		public FeatureCategory QueryLanguages;
		public FeatureCategory DataDistribution;
		public FeatureCategory DataReplication;
		public FeatureCategory Scalability;
		public FeatureCategory Security;
		public FeatureCategory Admin;

		public FeatureCategory getConsistency() {
				return Consistency;
		}

		public void setConsistency(FeatureCategory consistency) {
				Consistency = consistency;
		}

		public FeatureCategory getDataModel() {
				return DataModel;
		}

		public void setDataModel(FeatureCategory dataModel) {
				DataModel = dataModel;
		}

		public FeatureCategory getQueryLanguages() {
				return QueryLanguages;
		}

		public void setQueryLanguages(FeatureCategory queryLanguages) {
				QueryLanguages = queryLanguages;
		}

		public FeatureCategory getDataDistribution() {
				return DataDistribution;
		}

		public void setDataDistribution(FeatureCategory dataDistribution) {
				DataDistribution = dataDistribution;
		}

		public FeatureCategory getDataReplication() {
				return DataReplication;
		}

		public void setDataReplication(FeatureCategory dataReplication) {
				DataReplication = dataReplication;
		}

		public FeatureCategory getScalability() {
				return Scalability;
		}

		public void setScalability(FeatureCategory scalability) {
				Scalability = scalability;
		}

		public FeatureCategory getSecurity() {
				return Security;
		}

		public void setSecurity(FeatureCategory security) {
				Security = security;
		}

		public FeatureCategory getAdmin() {
				return Admin;
		}

		public void setAdmin(FeatureCategory admin) {
				Admin = admin;
		}
}
