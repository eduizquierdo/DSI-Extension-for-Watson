package com.ibm.dsi.watson;

import java.util.HashMap;
import java.util.Map;

import com.ibm.ia.common.ComponentException;
import com.ibm.ia.extension.DataProvider;
import com.ibm.dsi.model.WatsonSentimentProvider;
import com.ibm.dsi.model.WatsonSentimentProviderRequest;
import com.ibm.dsi.model.WatsonSentimentProviderResponse;
import com.ibm.ia.extension.annotations.DataProviderDescriptor;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;
import com.ibm.dsi.model.ConceptFactory;


@DataProviderDescriptor(dataProvider = WatsonSentimentProvider.class, responseCacheTimeout = 500)
public class SentimentProviderService extends DataProvider<WatsonSentimentProviderRequest, WatsonSentimentProviderResponse> implements WatsonSentimentProvider {
    
    @Override
    public WatsonSentimentProviderResponse processRequest(WatsonSentimentProviderRequest request) throws ComponentException {
        ConceptFactory factory = getConceptFactory(ConceptFactory.class);
        WatsonSentimentProviderResponse response = factory.createWatsonSentimentProviderResponse();
        
        // default response
        response.setSentiment("NEUTRAL");
        response.setScore(0);
        
        if(request.getText()==null || request.getText().length() <= 20) {
        	// returns default response
        	return response;
        }
        
        try {
            AlchemyLanguage service = new AlchemyLanguage();
            String apiKey = this.getSolutionProperty("watson.alchemy_api.key");
            service.setApiKey(apiKey);

            Map<String,Object> params = new HashMap<String, Object>();
            params.put(AlchemyLanguage.TEXT, request.getText());
            DocumentSentiment docSentiment =  service.getSentiment(params).execute();
            if(docSentiment!=null && docSentiment.getSentiment() != null) {                	
            	response.setSentiment(docSentiment.getSentiment().getType().name());
            	response.setScore(docSentiment.getSentiment().getScore());
            }
        } catch(Exception e) {
        	System.out.println("*** Caught exception calling Alchemy API.");
        	e.printStackTrace();
        }
       
        return response;
    }
    
}