package Parser;

/**
 * Created by andrewdickens on 5/30/16.
 */
public abstract class FeatureCategory {

		public String FeatureDescription;
		public Integer Rank;

		public FeatureCategory(String featureDescription) {
				FeatureDescription = featureDescription;
		}

		public String getFeatureDescription() {
				return FeatureDescription;
		}

		public void setFeatureDescription(String featureDescription) {
				FeatureDescription = featureDescription;
		}

		public Integer getRank() {
				return Rank;
		}

		public void setRank(Integer rank) {
				Rank = rank;
		}
}
