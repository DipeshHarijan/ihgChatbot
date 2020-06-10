package com.ihg.hotel.model;

import java.util.List;

public class QueryResult {

	private Parameters parameters;
	private List<OutputContexts> outputContexts;

	public List<OutputContexts> getOutputContexts() {
		return outputContexts;
	}

	public void setOutputContexts(List<OutputContexts> outputContexts) {
		this.outputContexts = outputContexts;
	}

	private Intent intent;

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

}
