package org.apilytic.currency.persistence.domain;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("chart")
public class YahooChart {

	private List<Result> result;

	public List<Result> getResult() {
		return result;
	}

	public static class Holder {
		private YahooChart chart;

		public YahooChart getChart() {
			return chart;
		}
	}

	public static class Result {
		private Indicators indicators;

		public Indicators getIndicators() {
			return indicators;
		}

		public static class Indicators {
			private List<AdjClose> adjclose;

			public List<AdjClose> getAdjclose() {
				return adjclose;
			}

			public static class AdjClose {
				private List<String> adjclose;

				public List<String> getAdjclose() {
					return adjclose;
				}
			}
		}
	}

}

